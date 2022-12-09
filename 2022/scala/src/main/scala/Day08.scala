import scala.collection.mutable

object Day08 extends Day {
  val DIM_R: Int = input.split("\n")(0).length
  val DIM_C: Int = input.split("\n").length

  val input_string_rows: Array[String] = input.split("\n")

  val coordinate_height_map: mutable.Map[(Int, Int), Int] = mutable.HashMap[(Int, Int), Int]()
  for (r <- 0 until DIM_R) {
    for (c <- 0 until DIM_C) {
      coordinate_height_map.update((r, c), input_string_rows(r).charAt(c).asDigit)
    }
  }

  override def q1(): Long = {
    var visible = 0
    for (r <- 0 until DIM_R) {
      for (c <- 0 until DIM_C) {
        val height = coordinate_height_map(r, c)
        // up
        if ((0 until r)
          .map(x => coordinate_height_map((x, c)))
          .forall(v => height > v)) visible += 1
        // down
        else if (((r + 1) until DIM_R)
          .map(x => coordinate_height_map((x, c)))
          .forall(v => height > v)) visible += 1
        // left
        else if ((0 until c)
          .map(x => coordinate_height_map((r, x)))
          .forall(v => height > v)) visible += 1
        // right
        else if (((c + 1) until DIM_C)
          .map(x => coordinate_height_map((r, x)))
          .forall(v => height > v)) visible += 1
      }
    }
    visible
  }

  override def q2(): Long = {
    var high_score = 0
    for (r <- 0 until DIM_R) {
      for (c <- 0 until DIM_C) {
        val height = coordinate_height_map(r, c)
        var score = 1
        // up
        val up = (0 until r)
          .reverse
          .takeWhile(x => coordinate_height_map((x, c)) < height)
          .length
        // down
        val down = ((r + 1) until DIM_R)
          .takeWhile(x => coordinate_height_map((x, c)) < height)
          .length
        // left
        val left = (0 until c)
          .reverse
          .takeWhile(x => coordinate_height_map((r, x)) < height)
          .length
        // right
        val right = ((c + 1) until DIM_C)
          .takeWhile(x => coordinate_height_map((r, x)) < height)
          .length
        score *= (if (up - r == 0) up else up + 1)
        score *= (if (down - (DIM_R - r - 1) == 0) down else down + 1)
        score *= (if (left - c == 0) left else left + 1)
        score *= (if (right - (DIM_C - c - 1) == 0) right else right + 1)
        high_score = if (score > high_score) score else high_score
      }
    }
    high_score
  }
}
