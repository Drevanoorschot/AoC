object Day03 extends Day {
  val sacks: Array[RuckSack] = input
    .split('\n')
    .map(s => new RuckSack(s))

  override def q1(): Long = {
    sacks
      .map(s => RuckSack.priority(s.duplicate_char))
      .sum
  }

  override def q2(): Long = {
    sacks
      .grouped(3)
      .map(group => group
        .map(sack => sack.comp1.union(sack.comp2))
        .reduce((s1, s2) => s1.intersect(s2)))
      .map(char_set => RuckSack.priority(char_set.head))
      .sum
  }
}

class RuckSack(content: String) {
  val comp1: Set[Char] = content
    .substring(0, content.length / 2)
    .toCharArray
    .toSet
  val comp2: Set[Char] = content
    .substring(content.length / 2)
    .toCharArray
    .toSet
  val duplicate_char: Char = comp1.intersect(comp2).head

}

object RuckSack {
  def priority(char: Char): Int = char.asDigit - 9 + (if (char.isUpper) 26 else 0)
}



