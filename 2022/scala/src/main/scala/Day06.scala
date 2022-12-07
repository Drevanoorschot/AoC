object Day06 extends Day {
  def detect_distinct(n:Int): Long = {
    for (i <- n to input.length) {
      if (input.substring(i - n, i).toSet.size == n)
        return i
    }
    -1
  }

  override def q1(): Long = {
    detect_distinct(4)
  }

  override def q2(): Long = {
    detect_distinct(14)
  }
}
