package airflow

import scala.reflect.runtime.{universe => ru}

class Controller {


  def executeDag(dagId: String):Unit = {

    //Using Reflection: Find Dag by String name and create an instance
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classInstance = Class.forName(dagId)
    val classSymbol = mirror.classSymbol(classInstance)
    val classType = classSymbol.toType
    val cm = mirror.reflectClass(classSymbol)
    val ctor = classType.decl(ru.termNames.CONSTRUCTOR).asMethod
    val ctorm = cm.reflectConstructor(ctor)

    val dag = ctorm()

  }


}
