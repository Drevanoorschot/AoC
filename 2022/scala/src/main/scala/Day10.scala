import scala.collection.mutable

object Day10 extends Day {
  val instructions: Array[Instruction] = input
    .split('\n')
    .map(l =>
      if (l == "noop") noop()
      else if (l.startsWith("addx")) addX(l.substring(5).toInt)
      else throw new RuntimeException("Unknown Instruction")
    )
  var x = 1
  val history: mutable.Map[Int, Int] = mutable.HashMap[Int, Int]((1, x), (0, x))
  var cycle = 1
  for (instruction <- instructions) {
    instruction match {
      case addX(value) =>
        cycle += 1
        history.update(cycle, x)
        cycle += 1
        x += value
        history.update(cycle, x)
      case _ =>
        cycle += 1
        history.update(cycle, x)
    }
  }

  override def q1(): Long = {
    Seq(20, 60, 100, 140, 180, 220)
      .map(k => history(k) * k)
      .sum
  }

  override def q2(): Long = {
    for (i <- 1 to 240) {
      if ((history(i) - 1 to history(i) + 1).contains((i - 1) % 40)) print('▮') else print(' ')
      if (i % 40 == 0) print('\n')
    }
    0
  }
}

trait Instruction

case class noop() extends Instruction

case class addX(value: Int) extends Instruction