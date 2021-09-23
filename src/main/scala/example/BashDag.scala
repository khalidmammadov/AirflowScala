package example

import airflow.model.Dag
import tasks.BashTask


class BashDag extends Dag(id="My Bash Dag", desc="Super cool dag") {

  val dag = new Dag("Bash_Dag", "Desc bash dag")

  val bashOp1: BashTask = new BashTask(id = "My first bash",
    bashCommand = "echo Hello world")

  val bashOp2: BashTask = new BashTask(id = "My first bash",
    bashCommand = "echo Hello Planets")

  bashOp2 >> bashOp1
  //  final def setUpstream
}

