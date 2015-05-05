package play.api.db.slick.evolutions

import org.specs2.mutable.Specification

import com.typesafe.config.ConfigFactory

import play.api.Configuration
import play.api.db.DBApi
import play.api.db.slick.TestData
import play.api.db.slick.evolutions.internal.DBApiAdapter
import play.api.inject.guice.GuiceApplicationBuilder

class EvolutionsModuleSpec extends Specification {

  val evolutionsConfig = Configuration(ConfigFactory.load("slick-evolutions.conf"))

  "SlickModule" should {
    val appBuilder = new GuiceApplicationBuilder(configuration = (TestData.configuration ++ evolutionsConfig))
    val injector = appBuilder.injector()

    "bind DBApi to DBApiAdapter" in {
      val api = injector.instanceOf[DBApi]
      api must beAnInstanceOf[DBApiAdapter]
    }
    "bind DBApi as a singleton" in {
      val api1 = injector.instanceOf[DBApi]
      val api2 = injector.instanceOf[DBApi]
      api1 mustEqual api2
    }
  }
}