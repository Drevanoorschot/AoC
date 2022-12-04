object Day01 extends Day {
  val calorie_lists: Array[Array[Int]] = input
    .split("\n\n")
    .map(ss => ss.split("\n")
      .map(s => s.toInt))

  override def q1(): Long = {
    calorie_lists
      .map(arr => arr.sum)
      .max
  }

  override def q2(): Long = {
    calorie_lists
      .map(arr => arr.sum)
      .sorted
      .reverse
      .take(3)
      .sum
  }
}

