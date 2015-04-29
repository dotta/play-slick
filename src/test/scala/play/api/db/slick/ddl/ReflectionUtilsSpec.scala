//package play.api.db.slick.ddl
//
//import org.specs2.mutable.Specification
//
//class ReflectionUtilsSpec extends Specification {
//  sequential
//
//  import scala.reflect.runtime.{ universe => ru }
//  import ru._
//  implicit val mirror = ru.runtimeMirror(this.getClass.getClassLoader)
//
//  import scala.tools.reflect.ToolBox
//  val tb = mirror.mkToolBox()
//
//  def test(path: String, decl: tb.u.ImplDef) = {
//    val declSym = tb.define(decl)
//    val dotPos = declSym.fullName.indexOf('.')
//    val prefix = declSym.fullName.substring(0, dotPos)
//    val sym = ReflectionUtils.symbolFor(prefix + "." + path)
//
//    declSym mustEqual sym
//  }
//
//      import scala.language.reflectiveCalls
//  "ReflectionUtils.symbolFor" should {
//    "class C" in {
//      test("C", q"class C")
//    }
//    "trait A {class B}" in {
//      test("A.B", q"trait A {class B}")
//    }
//  }
//}