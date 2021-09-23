package airflow.model

import tasks.BaseTask

class Dag(id: String, desc: String) {



}


object Dag {

  def apply(dagId: String): Dag = new Dag(dagId, "Not supplied")
  def apply(dagId: String, desc: String): Dag = new Dag(dagId, desc)

}