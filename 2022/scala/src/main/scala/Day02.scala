object Day02 extends Day {


  override def q1(): Long = {
    val match_ups: Array[MatchUp1] = input
      .split("\n")
      .map(s => new MatchUp1(
        s.charAt(0), s.charAt(2)))
    match_ups
      .map(m => m.score)
      .sum
  }

  override def q2(): Long = {
    val match_ups: Array[MatchUp2] = input
      .split("\n")
      .map(s => new MatchUp2(
        s.charAt(0), s.charAt(2)))
    match_ups
      .map(m => m.score)
      .sum
  }
}

sealed trait Symbol {
  def points: Int
}

case object Rock extends Symbol {
  override def points: Int = 1
}

case object Paper extends Symbol {
  override def points: Int = 2
}

case object Scissors extends Symbol {
  override def points: Int = 3
}

sealed trait Result {
  def points: Int
}

case object Win extends Result {
  override def points: Int = 6
}


case object Draw extends Result {
  override def points: Int = 3
}

case object Loss extends Result {
  override def points: Int = 0
}


class MatchUp1(char_against: Char, char_played: Char) {
  val symbol_against: Symbol = char_against match {
    case 'A' => Rock
    case 'B' => Paper
    case 'C' => Scissors
  }
  val symbol_played: Symbol = char_played match {
    case 'X' => Rock
    case 'Y' => Paper
    case 'Z' => Scissors
  }
  val result: Result = (symbol_played, symbol_against) match {
    case (Rock, Rock) => Draw
    case (Rock, Paper) => Loss
    case (Rock, Scissors) => Win

    case (Paper, Rock) => Win
    case (Paper, Paper) => Draw
    case (Paper, Scissors) => Loss

    case (Scissors, Rock) => Loss
    case (Scissors, Paper) => Win
    case (Scissors, Scissors) => Draw
  }
  val score: Int = result.points + symbol_played.points
}

class MatchUp2(char_against: Char, char_result: Char) {
  val symbol_against: Symbol = char_against match {
    case 'A' => Rock
    case 'B' => Paper
    case 'C' => Scissors
  }
  val result: Result = char_result match {
    case 'X' => Loss
    case 'Y' => Draw
    case 'Z' => Win
  }
  val symbol_played: Symbol = (symbol_against, result) match {
    case (Rock, Loss) => Scissors
    case (Rock, Draw) => Rock
    case (Rock, Win) => Paper

    case (Paper, Loss) => Rock
    case (Paper, Draw) => Paper
    case (Paper, Win) => Scissors

    case (Scissors, Loss) => Paper
    case (Scissors, Draw) => Scissors
    case (Scissors, Win) => Rock
  }
  val score: Int = symbol_played.points + result.points
}