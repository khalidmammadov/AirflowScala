package tasks

import collection.mutable.ListBuffer


trait BaseTask {

  val id: String

  private var upstream = new ListBuffer[BaseTask]()
  private var downstream = new ListBuffer[BaseTask]()

  def setUpstream(other: BaseTask): Unit = {
    if (other != this) {
      upstream.addOne(other)
    } else throw new IllegalArgumentException("Can't set upstream to myself!")

  }

  def setDownstream(other: BaseTask): Unit = {
    if (other != this) {
      downstream.addOne(other)
    } else throw new IllegalArgumentException("Can't set downstream to myself!")

  }

  def >>(other: BaseTask): Unit = {
    setUpstream(other)
  }

  def <<(other: BaseTask): Unit = {
    setDownstream(other)
  }

  def execute(): Int

}
