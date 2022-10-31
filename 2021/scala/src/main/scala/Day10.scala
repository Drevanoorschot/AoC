import scala.collection.mutable

object Day10 extends Day {

  case class OpenClosePair(opener: Char, closer: Char, errorScore:Int, autoCompleteScore:Int)

  val openClosePairs: Set[OpenClosePair] = Set(
    OpenClosePair('(', ')', 3, 1),
    OpenClosePair('[', ']', 57, 2),
    OpenClosePair('{', '}', 1197, 3),
    OpenClosePair('<', '>', 25137, 4)
  )
  val openers:Set[Char] = openClosePairs.map(p => p.opener)
  val closers:Set[Char] = openClosePairs.map(p => p.closer)

  val evaluated: Array[Either[Char, mutable.Stack[Char]]] = input
    .split('\n')
    .map(line => findErrorOrRemainingStack(line))

  override def q1(): Long = {
    evaluated.map {
      case Left(char) => openClosePairs.find(p => p.closer == char).get.errorScore
      case Right(_) => 0
    }.sum
  }

  override def q2(): Long = {
    val res:Array[Long] = evaluated
      .map {
      case Left(_) => 0
      case Right(stack) => stack.foldLeft(0:Long)((score, char) => score * 5 + openClosePairs.find(p => p.opener == char).get.autoCompleteScore)
    }
      .filter(score => score != 0)
      .sortInPlace
      .toArray
    res(res.length / 2)
  }

  def findErrorOrRemainingStack(line: String): Either[Char, mutable.Stack[Char]] = {
    val callStack: mutable.Stack[Char] = new mutable.Stack[Char]()
    for (char <- line) {
      if(openers.contains(char)) callStack.push(char)
      else if (openClosePairs.find(p => p.closer == char).get.opener != callStack.pop()) return Left(char)
    }
    Right(callStack)
  }
}
