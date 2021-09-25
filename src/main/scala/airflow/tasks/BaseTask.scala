package airflow.tasks

import collection.mutable.ListBuffer


trait BaseTask {

  val id: String

  private[airflow] var downstreams = new ListBuffer[BaseTask]()

  def setUpstream(other: BaseTask): Unit = {
    if (other != this) {
      other.setDownstream(this)
    } else throw new IllegalArgumentException("Can't set upstream to myself!")

  }

  def setDownstream(other: BaseTask): Unit = {
    if (other != this) {
      downstreams.addOne(other)
    } else throw new IllegalArgumentException("Can't set downstream to myself!")

  }

  def >>(other: BaseTask): Unit = {
    setUpstream(other)
  }

  def <<(other: BaseTask): Unit = {
    setDownstream(other)
  }

  def logTaskExecution():Unit = {
    println(s"Executing Task: ${this.id}")
  }

  private[airflow] def executeTask():Int = {
    logTaskExecution()
    execute()
  }

  def execute(): Int

}
