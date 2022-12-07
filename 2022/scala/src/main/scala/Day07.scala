import scala.collection.mutable

object Day07 extends Day {
  val dir_size_map: mutable.Map[Seq[String], Long] = mutable.Map[Seq[String], Long]()
  val curr_dir: mutable.Stack[String] = mutable.Stack[String]()
  for (line <- input.split("\n")) {
    line match {
      case "$ ls" =>
      case "$ cd /" =>
      case _ if line.startsWith("dir ") =>
      case "$ cd .." => curr_dir.pop()
      case _ if line.startsWith("$ cd ") => curr_dir.push(line.substring(5))
      case _ =>
        val fileSize: Long = line.split(" ")(0).toInt
        val path = curr_dir.toSeq.reverse
        for (i <- 0 to path.length) {
          val key = path.slice(0, path.length - i)
          val curr_val: Long = dir_size_map.getOrElseUpdate(key, 0)
          dir_size_map.update(key, curr_val + fileSize)
        }
    }
  }

  override def q1(): Long = {
    dir_size_map.values
      .filter(value => value <= 100_000)
      .sum
  }

  override def q2(): Long = {
    val available: Long = 70_000_000 - dir_size_map(Seq())
    val missing: Long = 30_000_000 - available
    dir_size_map
      .values
      .toSeq
      .sorted
      .reverse
      .takeWhile(value => value > missing)
      .last
  }
}
