import scala.collection.mutable

object Day09 extends Day {
  val movements: Array[Movement] = input
    .split('\n')
    .map(l => {
      val steps = l.substring(2).toInt
      l.charAt(0) match {
        case 'U' => new Movement(Up(), steps)
        case 'D' => new Movement(Down(), steps)
        case 'L' => new Movement(Left(), steps)
        case 'R' => new Movement(Right(), steps)
      }
    })

  def debug(tail_visited: mutable.HashSet[(Int, Int)]): Unit = {
    for (y <- (0 to 40).reverse) {
      for (x <- (0 to 40)) {
        if ((x, y) == (0, 0)) print('s')
        else if (tail_visited.contains((x, y))) print('#') else print('.')
      }
      print('\n')
    }
  }

  override def q1(): Long = {
    var head: (Int, Int) = (0, 0)
    var tail: (Int, Int) = (0, 0)
    val tail_visited = new mutable.HashSet[(Int, Int)]()
    for (move <- movements) {
      for (_ <- 0 until move.steps) {
        head = move.direction.move_head(head)
        tail = follow(head, tail)
        tail_visited.add(tail)
      }
    }
    tail_visited.size
  }

  override def q2(): Long = {
    val knots: Array[(Int, Int)] = (0 to 9).map(_ => (0, 0)).toArray
    val tail_visited = new mutable.HashSet[(Int, Int)]()
    for (move <- movements) {
      for (_ <- 0 until move.steps) {
        knots(0) = move.direction.move_head(knots(0))
        for (i <- 1 to 9) {
          knots(i) = follow(knots(i - 1), knots(i))
        }
        tail_visited.add(knots(9))
      }
    }
    tail_visited.size
  }

  def touching(head: (Int, Int), tail: (Int, Int)): Boolean = {
    (tail._1 - 1 to tail._1 + 1).contains(head._1) && (tail._2 - 1 to tail._2 + 1).contains(head._2)
  }

  def follow(head: (Int, Int), tail: (Int, Int)): (Int, Int) = {
    if (touching(head, tail)) return tail
    var new_tail = tail
    if (head._1 != tail._1 && head._1 > tail._1) new_tail = (new_tail._1 + 1, new_tail._2)
    if (head._1 != tail._1 && head._1 < tail._1) new_tail = (new_tail._1 - 1, new_tail._2)

    if (head._2 != tail._2 && head._2 > tail._2) new_tail = (new_tail._1, new_tail._2 + 1)
    if (head._2 != tail._2 && head._2 < tail._2) new_tail = (new_tail._1, new_tail._2 - 1)
    new_tail
  }


}


sealed trait Direction {
  def move_head(pos: (Int, Int)): (Int, Int)
}

case class Up() extends Direction {
  override def move_head(pos: (Int, Int)): (Int, Int) = (pos._1, pos._2 + 1)
}

case class Down() extends Direction {
  override def move_head(pos: (Int, Int)): (Int, Int) = (pos._1, pos._2 - 1)
}

case class Left() extends Direction {
  override def move_head(pos: (Int, Int)): (Int, Int) = (pos._1 - 1, pos._2)
}

case class Right() extends Direction {
  override def move_head(pos: (Int, Int)): (Int, Int) = (pos._1 + 1, pos._2)
}


class Movement(val direction: Direction, val steps: Int)