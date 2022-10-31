import scala.collection.mutable

object Day09 extends Day {
  val points: Array[Array[Point]] = input
    .split("\n")
    .map(line => line.toCharArray
      .map(char => new Point(char.asDigit))
    )
  // it's reference time
  for (i <- points.indices) {
    for (j <- points(i).indices) {
      if (i != 0) points(i)(j).top = Some(points(i - 1)(j))
      if (j != 0) points(i)(j).left = Some(points(i)(j - 1))
      if (j != points(i).length - 1) points(i)(j).right = Some(points(i)(j + 1))
      if (i != points.length - 1) points(i)(j).bottom = Some(points(i + 1)(j))
    }
  }
  val lowPoints: Array[Point] = points
    .flatten
    .filter(p => p.isLowest)

  override def q1(): Long = {
    lowPoints
      .map(p => p.riskLevel)
      .sum
  }


  override def q2(): Long = {
    lowPoints
      .sortBy(p => -p.basin.size)
      .take(3)
      .map(p => p.basin.size)
      .product
  }

  trait AbstractPoint {
    val altitude: Int
    val basin:mutable.Set[AbstractPoint]
  }

  class Point(val altitude: Int) extends AbstractPoint {
    val riskLevel: Int = this.altitude + 1

    var top: Option[AbstractPoint] = None
    var left: Option[AbstractPoint] = None
    var right: Option[AbstractPoint] = None
    var bottom: Option[AbstractPoint] = None


    def isLowest: Boolean = {
      top.getOrElse(OutOfBouncePoint).altitude > this.altitude &&
        left.getOrElse(OutOfBouncePoint).altitude > this.altitude &&
        right.getOrElse(OutOfBouncePoint).altitude > this.altitude &&
        bottom.getOrElse(OutOfBouncePoint).altitude > this.altitude
    }

    override lazy val basin: mutable.Set[AbstractPoint] = {
      val pointSet: mutable.Set[AbstractPoint] = new mutable.HashSet[AbstractPoint]()
      pointSet.add(this)
      Array(this.top, this.bottom, this.left, this.right).foreach(side => {
        val alt = side.getOrElse(OutOfBouncePoint).altitude
        if (alt < 9 && alt > this.altitude) pointSet.addAll(side.get.basin) else new mutable.HashSet[AbstractPoint]()
      })
      pointSet
    }
  }

  object OutOfBouncePoint extends AbstractPoint {
    override val altitude: Int = 10

    override val basin: mutable.Set[AbstractPoint] = new mutable.HashSet[AbstractPoint]()
  }
}
