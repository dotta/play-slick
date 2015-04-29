object sheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  println("Welcome to the Scala worksheet")

  import scala.reflect.runtime.{ universe => ru }
  import ru._;$skip(127); 
  def mirror = ru.runtimeMirror(this.getClass.getClassLoader)
  import scala.tools.reflect.ToolBox;System.out.println("""mirror: => reflect.runtime.universe.Mirror""");$skip(67); 
  val tb = mirror.mkToolBox();System.out.println("""tb  : scala.tools.reflect.ToolBox[reflect.runtime.universe.type] = """ + $show(tb ));$skip(45); 
  val csym = tb.define(q"trait A {class B}");System.out.println("""csym  : sheet.tb.u.Symbol = """ + $show(csym ));$skip(19); val res$0 = 
  
  csym.fullName;System.out.println("""res0: String = """ + $show(res$0));$skip(42); 
  val dotPos = csym.fullName.indexOf('.');System.out.println("""dotPos  : Int = """ + $show(dotPos ));$skip(50); 
  val prefix = csym.fullName.substring(0, dotPos);System.out.println("""prefix  : String = """ + $show(prefix ));$skip(41); 
  val sym = mirror.staticPackage(prefix);System.out.println("""sym  : reflect.runtime.universe.ModuleSymbol = """ + $show(sym ));$skip(36); 
  sym.info.members.foreach(println);$skip(47); 
  val aSym = sym.info.member(ru.TypeName("A"));System.out.println("""aSym  : reflect.runtime.universe.Symbol = """ + $show(aSym ));$skip(43); val res$1 = 
  
  
  aSym.info.member(ru.TypeName("B"));System.out.println("""res1: reflect.runtime.universe.Symbol = """ + $show(res$1));$skip(58); val res$2 = 
  
  sym.info.member(ru.TypeName("A$B")).info.baseClasses;System.out.println("""res2: List[reflect.runtime.universe.Symbol] = """ + $show(res$2))}
}
