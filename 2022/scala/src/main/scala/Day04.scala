object Day04 extends Day {
  val pairs: Array[Pair] = input
    .split('\n')
    .map(l => new Pair(
      new Range(l.split(',')(0).split("-")(0).toInt, l.split(',')(0).split("-")(1).toInt),
      new Range(l.split(',')(1).split("-")(0).toInt, l.split(',')(1).split("-")(1).toInt),
    ))

  override def q1(): Long = {
    pairs.count(p => p.contained)
  }

  override def q2(): Long = {
    pairs.count(p => p.overlap)
  }
}

class Range(val left: Int, val right: Int)

class Pair(val r1: Range, val r2: Range) {
  val contained: Boolean =
    (r1.right <= r2.right && r1.left >= r2.left) || // r2 in r1
      (r2.right <= r1.right && r2.left >= r1.left) // r1 in r2
  val overlap: Boolean = r1.left <= r2.right && r2.left <= r1.right
}