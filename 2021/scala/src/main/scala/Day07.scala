object Day07 extends Day {
  val positions: Array[Int] = input
    .split(",")
    .map(x => x.toInt)
  val min: Int = positions.min
  val max: Int = positions.max

  override def q1(): Long = {
    (min to max)
      .map(x => calculateLinearFuel(positions, x))
      .min
  }

  override def q2(): Long = {
    (min to max)
      .map(x => calculateIncrementalFuel(positions, x))
      .min
  }

  def calculateLinearFuel(positions: Array[Int], alignPos: Int): Int = {
    positions
      .map(x => Math.abs(alignPos - x))
      .sum
  }

  def calculateIncrementalFuel(positions: Array[Int], alignPos: Int): Int = {
    positions
      .map(x => (0 to Math.abs(alignPos - x)).sum)
      .sum
  }
}
