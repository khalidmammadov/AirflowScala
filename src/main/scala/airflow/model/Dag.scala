package airflow.model

import airflow.tasks.BaseTask

class Dag(id: String, desc: String) {

  def traverseTasks(tasks: List[BaseTask]): Unit = tasks match {
    case List() =>
    case task::otherTasks =>
      //Execute the task!
      if (task.executeTask() == 0)
        traverseTasks(task.downstreams.toList)
      else throw new RuntimeException(s"Task ${task.id} failed")
      //Run others
      traverseTasks(otherTasks)
  }


  def start(initTask: BaseTask):Unit = {
    println(s"Staring Dag: $id")
    println(s"Description: $desc")
    val initList = List(initTask)

    traverseTasks(initList)
//    initTask.downstream.foreach(_.execute())
  }

}


object Dag {

  def apply(dagId: String): Dag = new Dag(dagId, "Not supplied")
  def apply(dagId: String, desc: String): Dag = new Dag(dagId, desc)

}