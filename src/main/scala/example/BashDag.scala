package example

import airflow.model.Dag
import airflow.tasks.BashTask


class BashDag extends Dag(id="My Bash Dag", desc="Super cool dag") {

  val dag = new Dag("Bash_Dag", "Desc bash dag")

  val bashOp1: BashTask = new BashTask(id = "My first bash",
    bashCommand = "echo Hello world \n ")

  val bashOp2: BashTask = new BashTask(id = "My first bash",
    bashCommand = "echo Hello Planets \n ")

  bashOp2 >> bashOp1

  // Start the Dag
  start(bashOp2)

}

