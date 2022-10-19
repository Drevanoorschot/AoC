import scala.collection.mutable

object Day05 extends Day {
  val lines: Array[Line] = input
    .split("\n")
    .map(line => line.split(" -> |,"))
    .map(intArray => new Line(
      new Point(intArray(0).toInt, intArray(1).toInt),
      new Point(intArray(2).toInt, intArray(3).toInt)))

  override def q1(): Int = {
    val pointMap = new mutable.HashMap[Int, Int]
    for (line <- lines.filter(line => line.orientation != Orientation.DIAGONAL)) {
      for (point <- line.points) {
        pointMap.update(point.hash, pointMap.getOrElse(point.hash, 0) + 1)
      }
    }
    pointMap count (x => x._2 > 1)
  }


  override def q2(): Int = {
    val pointMap = new mutable.HashMap[Int, Int]
    for (line <- lines) {
      for (point <- line.points) {
        pointMap.update(point.hash, pointMap.getOrElse(point.hash, 0) + 1)
      }
    }
    pointMap count (x => x._2 > 1)
  }
}

class Point(val x: Int, val y: Int) {
  val hash: Int = (x + y) * (x + y + 1) / 2 + x //cantor pairing
}

sealed trait Orientation

object Orientation {
  case object HORIZONTAL extends Orientation

  case object VERTICAL extends Orientation

  case object DIAGONAL extends Orientation

}

class Line(val p1: Point, val p2: Point) {
  val orientation: Orientation = {
    if (p1.x == p2.x) Orientation.HORIZONTAL
    else if (p1.y == p2.y) Orientation.VERTICAL
    else Orientation.DIAGONAL
  }
  val points: Array[Point] = {
    orientation match {
      case Orientation.HORIZONTAL =>
        if (p1.y < p2.y) (p1.y to p2.y).map(y => new Point(p1.x, y)).toArray
        else (p2.y to p1.y).map(y => new Point(p1.x, y)).toArray
      case Orientation.VERTICAL =>
        if (p1.x < p2.x) (p1.x to p2.x).map(x => new Point(x, p1.y)).toArray
        else (p2.x to p1.x).map(x => new Point(x, p1.y)).toArray
      case Orientation.DIAGONAL =>
        val beginPoint = if (p1.y < p2.y) p1 else p2
        val endPoint = if (p1.y < p2.y) p2 else p1
        if (beginPoint.x < endPoint.x) (0 to endPoint.y - beginPoint.y).map(n => new Point(beginPoint.x + n, beginPoint.y + n)).toArray
        else (0 to endPoint.y - beginPoint.y).map(n => new Point(beginPoint.x - n, beginPoint.y + n)).toArray
    }
  }
}

