object Main {
  def main(args: Array[String]): Unit = {
    val dayClass = Class.forName(s"Day${args(0)}" + "$")
    val dayObj = dayClass.getField("MODULE$").get(classOf[Day]).asInstanceOf[Day]
    println(s"Q1 answer: ${dayObj.q1()}")
    println(s"Q2 answer: ${dayObj.q2()}")
  }
}
