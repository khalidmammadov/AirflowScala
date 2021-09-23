
trait BaseCommand

trait Command extends BaseCommand {
  def execute(args: Array[String])
}

object AirflowApp extends App {

  case class ParentCommand(sub_commands: Map[String, Command]) extends Command {
    override def execute(args: Array[String]): Unit = {
        parse_commands(args, sub_commands)
    }
  }

  case class ChildCommand(action: String) extends Command {
    override def execute(args: Array[String]): Unit = {
      print(action)
    }
  }

  val dagsCommand = ParentCommand(Map("trigger"-> ChildCommand("Triggering dag"),
      "list"->ChildCommand("Listing dags")))

  val tasksCommand = ParentCommand(Map("run"->ChildCommand("Running app"),
      "list"->ChildCommand("Listing tasks")))

  val commands = Map("tasks"->tasksCommand,
    "dags"->dagsCommand)


  def parse_commands(args: Array[String], commands: Map[String, Command]): Unit = {

    if (args.length > 0) {
      commands.get(args(0)) match {
        case Some(command) => command.execute(args.drop(1))
        case None => throw new IllegalArgumentException(s"Invalid parameter supplied: \"${args(0)}\"")
      }
    } else {
      val possible_args = commands.keys.mkString(", ")
      throw new IllegalAccessException(s"Missing positional arguments: $possible_args")
    }

  }

  // Parse command arguments and run
  parse_commands(args, commands)


}
