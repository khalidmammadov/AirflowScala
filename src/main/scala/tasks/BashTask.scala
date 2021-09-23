package tasks

import scala.sys.process._

class BashTask(override val id: String,
               val bashCommand: String) extends BaseTask {

  def execute():Int = {
    bashCommand !
  }
}
