import scala.io.BufferedSource

trait Day {
  val input: String = input("\\d".r.findAllMatchIn(getClass.getName).mkString)

  def input(fileName: String): String = {
    var source:BufferedSource = null
    try {
      source = io.Source.fromFile(s"inputs/$fileName.txt")
      source.mkString
    } finally source.close()
  }

  def q1(): Long

  def q2(): Long
}
