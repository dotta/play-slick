package play.api.db.slick.internal

import java.sql.Connection

import javax.inject.Inject
import javax.sql.DataSource
import play.api.Logger
import play.api.PlayException
import play.api.db.DBApi
import play.api.db.{Database => PlayDatabase}
import play.api.db.slick.DbName
import play.api.db.slick.IssueTracker
import play.api.db.slick.SlickApi
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.jdbc.DataSourceJdbcDataSource
import slick.jdbc.HikariCPJdbcDataSource

private[slick] class DBApiAdapter @Inject() (slickApi: SlickApi) extends DBApi {
  private lazy val databasesByName: Map[DbName, PlayDatabase] = slickApi.dbConfigs[JdbcProfile]().map {
    case (name, dbConfig) => (name, new DBApiAdapter.DatabaseAdapter(name, dbConfig))
  }(collection.breakOut)

  override def databases: Seq[PlayDatabase] = databasesByName.values.toSeq

  def database(name: String): PlayDatabase =
    databasesByName.getOrElse(DbName(name), throw new IllegalArgumentException(s"Could not find database for $name"))

  def shutdown(): Unit = {
    // no-op: shutting down dbs is automatically managed by `slickApi`
    ()
  }
}

private[slick] object DBApiAdapter {
  // I don't really like this adapter as it can be used as a trojan horse. Let's keep things simple for the moment,
  // but in the future we may need to become more defensive and provide custom implementation for `java.sql.Connection`
  // and `java.sql.DataSource` to prevent the ability of closing a database connection or database when using this
  // adapter class.
  private class DatabaseAdapter(_name: DbName, dbConfig: DatabaseConfig[JdbcProfile]) extends PlayDatabase {
    private val logger = Logger(classOf[DatabaseAdapter])
    def name: String = _name.value
    def dataSource: DataSource = {
      dbConfig.db.source match {
        case ds: DataSourceJdbcDataSource => ds.ds
        case hds: HikariCPJdbcDataSource => hds.ds
        case other =>
          logger.error(s"Unexpected data source type ${other.getClass}. Please, file a ticket ${IssueTracker}.")
          throw new UnsupportedOperationException
      }
    }
    def url: String = dbConfig.db.createSession().metaData.getURL
    def getConnection(): Connection = {
      val session = dbConfig.db.createSession()
      session.conn
    }
    def getConnection(autocommit: Boolean): Connection = getConnection() // FIXME: auto-commit is ignored (in slick, I believe auto-commit is on by default, but need to double check)
    def withConnection[A](block: Connection => A): A = {
      dbConfig.db.withSession { session =>
        val conn = session.conn
        block(conn)
      }
    }
    def withConnection[A](autocommit: Boolean)(block: Connection => A): A = withConnection(block) // FIXME: auto-commit is ignored (in slick, I believe auto-commit is on by default, but need to double check)
    def withTransaction[A](block: Connection => A): A = {
      dbConfig.db.withTransaction { session =>
        val conn = session.conn
        block(conn)
      }
    }
    def shutdown(): Unit = {
      // no-op. The rationale is that play-slick already takes care of closing the database on application shutdown
      ()
    }
  }
}