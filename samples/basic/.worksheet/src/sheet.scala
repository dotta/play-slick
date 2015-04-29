object sheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  println("Welcome to the Scala worksheet")

  import scala.reflect.runtime.{ universe => ru }
  import ru._;$skip(127); 
  def mirror = ru.runtimeMirror(this.getClass.getClassLoader);System.out.println("""mirror: => reflect.runtime.universe.Mirror""");$skip(42); 

  val p = mirror.staticPackage("tables");System.out.println("""p  : reflect.runtime.universe.ModuleSymbol = """ + $show(p ));$skip(50); 
  val ct = p.info.member(ru.TypeName("CatTable"));System.out.println("""ct  : reflect.runtime.universe.Symbol = """ + $show(ct ));$skip(38); val res$0 = 
  ct.info.member(ru.TypeName("Cats"));System.out.println("""res0: reflect.runtime.universe.Symbol = """ + $show(res$0));$skip(32); val res$1 = 
  mirror.staticModule("tables");System.out.println("""res1: reflect.runtime.universe.ModuleSymbol = """ + $show(res$1));$skip(73); val res$2 = 
  mirror.staticClass("tables.CatTable").info.member(ru.TypeName("Cats"));System.out.println("""res2: reflect.runtime.universe.Symbol = """ + $show(res$2))}


}
