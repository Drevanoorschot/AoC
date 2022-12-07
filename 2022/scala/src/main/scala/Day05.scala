import scala.collection.mutable

object Day05 extends Day {
  val stackCount: Int = input
    .split("\n\n")(0)
    .split("\n")
    .reverse
    .head
    .count(c => c.isDigit)
  val crateStacks: Array[mutable.Stack[Char]] = new Array[mutable.Stack[Char]](stackCount)
  val moveInstructions: Array[MoveInstr] = input
    .split("\n\n")(1)
    .split("\n")
    .map(line => new MoveInstr(
      line.split(" from")(0).replace("move ", "").toInt,
      line.split("from ")(1).split(" to")(0).toInt,
      line.split("to ")(1).toInt
    ))

  def init_crates(): Unit = {
    (0 until stackCount).foreach(i => crateStacks(i) = new mutable.Stack[Char]())
    input
      .split("\n\n")(0)
      .split("\n")
      .reverse
      .tail
      .foreach(row => row
        .zipWithIndex
        .filter(p => p._1.isUpper)
        .foreach(p => crateStacks(p._2 / 4).push(p._1))
      )
  }

  def print_top(): Unit = {
    println(crateStacks
      .map(stack => stack.top)
      .foldLeft("")((s, c) => s + c)
    )
  }


  override def q1(): Long = {
    init_crates()
    moveInstructions
      .foreach(instr => (0 until instr.count)
        .foreach(_ => crateStacks(instr.to - 1).push(crateStacks(instr.from - 1).pop()))
      )
    print_top()
    0
  }

  override def q2(): Long = {
    init_crates()
    moveInstructions
      .foreach(instr => (0 until instr.count)
        .map(_ => crateStacks(instr.from - 1).pop())
        .reverse
        .map(char => crateStacks(instr.to - 1).push(char))
      )
    print_top()
    0
  }
}

class MoveInstr(val count: Int, val from: Int, val to: Int)