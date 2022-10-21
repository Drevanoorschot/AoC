object Main {
  private var example_mode:Boolean = false
  def main(args: Array[String]): Unit = {
    example_mode = args.length >= 2 && args(1) == "-e"
    val dayClass = Class.forName(s"Day${args(0)}" + "$")
    val dayObj = dayClass.getField("MODULE$").get(classOf[Day]).asInstanceOf[Day]
    println(s"Q1 answer: ${dayObj.q1()}")
    println(s"Q2 answer: ${dayObj.q2()}")
  }

  def EXAMPLE_MODE:Boolean = example_mode
}