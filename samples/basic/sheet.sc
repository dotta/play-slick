object sheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  import scala.reflect.runtime.{ universe => ru }
  import ru._
  def mirror = ru.runtimeMirror(this.getClass.getClassLoader)
                                                  //> mirror: => reflect.runtime.universe.Mirror

  val p = mirror.staticPackage("tables")          //> p  : reflect.runtime.universe.ModuleSymbol = package tables
  val ct = p.info.member(ru.TypeName("CatTable")) //> ct  : reflect.runtime.universe.Symbol = trait CatTable
  ct.info.member(ru.TypeName("Cats"))             //> res0: reflect.runtime.universe.Symbol = class Cats
  mirror.staticModule("tables")                   //> scala.ScalaReflectionException: object tables not found.
                                                  //| 	at scala.reflect.internal.Mirrors$RootsBase.staticModule(Mirrors.scala:1
                                                  //| 62)
                                                  //| 	at scala.reflect.internal.Mirrors$RootsBase.staticModule(Mirrors.scala:2
                                                  //| 2)
                                                  //| 	at sheet$$anonfun$main$1.apply$mcV$sp(sheet.scala:11)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at sheet$.main(sheet.scala:1)
                                                  //| 	at sheet.main(sheet.scala)
  mirror.staticClass("tables.CatTable").info.member(ru.TypeName("Cats"))


}