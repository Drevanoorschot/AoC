import scala.collection.mutable

object Day12 extends Day {
  val caves = new mutable.HashMap[String, Cave]()
  for (connection <- input.split("\n")) {
    val ids = connection.split("-")
    ids
      .filter(id => !caves.keys.exists(key => key == id))
      .foreach(id => caves.addOne(id, new Cave(id)))
    // connect
    caves(ids(0)).neighbours.add(caves(ids(1)))
    caves(ids(1)).neighbours.add(caves(ids(0)))
  }
  val start: Cave = caves("start")


  override def q1(): Long = {
    makePaths(Seq.empty, start).size
  }

  override def q2(): Long = {
    makePathsOneSmallTwice(Seq.empty, start).size
  }

  def makePaths(path: Seq[String], current: Cave): Set[Seq[String]] = {
    if (current.isEnd) return Set(path :+ current.id)
    if (path.contains(current.id) && current.isSmall) return Set.empty
    current
      .neighbours.flatMap(n => makePaths(path :+ current.id, n))
      .toSet
  }

  def makePathsOneSmallTwice(path: Seq[String], current: Cave): Set[Seq[String]] = {
    if (current.isEnd) return Set(path :+ current.id)
    if (current.isStart && path.contains("start")) return Set.empty
    if (path.contains(current.id) && current.isSmall) return current
      .neighbours.flatMap(n => makePaths(path :+ current.id, n))
      .toSet
    current
      .neighbours.flatMap(n => makePathsOneSmallTwice(path :+ current.id, n))
      .toSet
  }

  class Cave(val id: String) {
    val neighbours: mutable.HashSet[Cave] = new mutable.HashSet[Cave]()
    val isSmall: Boolean = id.charAt(0).isLower
    val isStart: Boolean = id == "start"
    val isEnd: Boolean = id == "end"
  }
}
