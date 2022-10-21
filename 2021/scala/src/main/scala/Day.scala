import scala.io.BufferedSource

trait Day {
  val input: String = input(
    s"inputs/${if (Main.EXAMPLE_MODE) "example" else "puzzle"}",
    "\\d".r.findAllMatchIn(getClass.getName).mkString
  )

  def input(folder: String, fileName: String): String = {
    var source: BufferedSource = null
    try {
      source = io.Source.fromFile(s"$folder/$fileName.txt")
      source.mkString
    } finally source.close()
  }

  def q1(): Long

  def q2(): Long
}
