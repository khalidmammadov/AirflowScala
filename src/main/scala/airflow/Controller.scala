package airflow

import scala.reflect.runtime.{universe => ru}
import java.lang.reflect.Constructor

class Controller {


  def executeDag(dagId: String):Unit = {

//    val clazz = Class.forName(dagId).getClassLoader
//    val m = ru.runtimeMirror()
    val mirror = ru.runtimeMirror(getClass.getClassLoader)
    val classInstance = Class.forName(dagId)
    val classSymbol = mirror.classSymbol(classInstance)
    println(classSymbol)
    val classType = classSymbol.toType

    val cm = mirror.reflectClass(classSymbol)

    val ctor = classType.decl(ru.termNames.CONSTRUCTOR).asMethod
    val ctorm = cm.reflectConstructor(ctor)

    val dag = ctorm()

  }


}
