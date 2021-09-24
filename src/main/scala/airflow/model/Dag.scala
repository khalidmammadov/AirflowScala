package airflow.model

import airflow.tasks.BaseTask

import scala.collection.mutable.ListBuffer

class Dag(id: String, desc: String) {

  def traverseTasks(tasks: List[BaseTask]): Unit = tasks match {
    case List(task) =>
      task.execute()
      traverseTasks(task.downstreams.toList)
    case task::otherTasks =>
      traverseTasks(List(task))
      traverseTasks(otherTasks)
    case List() =>
  }


  def start(initTask: BaseTask):Unit = {
    print(s"Staring Dag: $id")
    print(s"Description: $desc")
    val initList = List(initTask)

    traverseTasks(initList)
//    initTask.downstream.foreach(_.execute())
  }

}


object Dag {

  def apply(dagId: String): Dag = new Dag(dagId, "Not supplied")
  def apply(dagId: String, desc: String): Dag = new Dag(dagId, desc)

}