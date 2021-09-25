package airflow.model

import collection.mutable.ListBuffer
import airflow.tasks.BaseTask

class Dag(id: String, desc: String) {

  val tasksForRun = new ListBuffer[BaseTask]

  def traverseDagTasksRun(tasks: List[BaseTask]): Unit = tasks match {
    case List() =>
    case task::otherTasks =>
      //Execute the task!
      if (task.executeTask() == 0)
        traverseDagTasksRun(task.downstreams.toList)
      else throw new RuntimeException(s"Task ${task.id} failed")
      //Run others
      traverseDagTasksRun(otherTasks)
  }

  def addToRunQueue(task: BaseTask):Unit = {
    // Add if not added already
    if ( !tasksForRun.exists(t=> t.id == task.id) ) {
      tasksForRun.addOne(task)
    } else throw new ExceptionInInitializerError(s"You cant add task \"$task\" twice! ")
  }


  //TO DO set below
  //private[airflow]
  def start():Unit = {
    println(s"Staring Dag1: $id")
    println(s"Description1: $desc")

    traverseDagTasksRun(tasksForRun.toList)

  }

}


object Dag {

  def apply(dagId: String): Dag = new Dag(dagId, "Not supplied")
  def apply(dagId: String, desc: String): Dag = new Dag(dagId, desc)

}