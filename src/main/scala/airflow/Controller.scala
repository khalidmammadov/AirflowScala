package airflow

import scala.reflect.runtime.{universe => ru}

class Controller {


  def executeDag(dagId: String):Unit = {

    //Using Reflection to find Dag by String name and create an instance
    val runtimeMirror = ru.runtimeMirror(getClass.getClassLoader)
    val classSymbol = runtimeMirror.classSymbol(Class.forName(dagId))
    val classMirror = runtimeMirror.reflectClass(classSymbol)
    val dagConstructor = classMirror.symbol.info.member(ru.termNames.CONSTRUCTOR).asMethod
    val constructorMethod = classMirror.reflectConstructor(dagConstructor)

    // Create Dag instance
    val dag = constructorMethod()

    val im = runtimeMirror.reflect(dag)
    //Find Start method
    val startDeclaration = im.symbol.info.member(ru.TermName("start")).asMethod
    val start = im.reflectMethod(startDeclaration)

    // Start the party!
    start()
  }


}
