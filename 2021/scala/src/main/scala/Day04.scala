import scala.collection.mutable
import scala.util.control.Breaks.break


object Day04 extends Day {
  val call_order: Array[Int] = input.split("\n")(0).split(",").map(s => s.toInt)
  val cards: Set[Card] = input.split("\n\n")
    .drop(1)
    .map(c => c.split("\\s")
      .filter(i => i.nonEmpty)
      .map(i => i.toInt))
    .map(is => new Card(is)).toSet

  override def q1(): Int = {
    for (call <- call_order) {
      cards.foreach(c => c.crossOut(call))
      for (card <- cards) {
        if (card.fullRow != null || card.fullCol != null) {
          return card.rows
            .flatMap(_.toArray)
            .filter(x => x != -1)
            .sum * call
        }
      }
    }
    -1
  }

  override def q2(): Int = {
    var last_card:Card = null
    var unfinished_cards = cards
    for (call <- call_order) {
      cards.foreach(c => c.crossOut(call))
      unfinished_cards = unfinished_cards.filter(c => c.fullCol == null && c.fullRow == null)
      if(unfinished_cards.size == 1) {
        last_card = unfinished_cards.head
      }
      if(unfinished_cards.isEmpty) {
        return call * last_card.rows
          .flatMap(_.toArray)
          .filter(x => x != -1)
          .sum
      }
    }
    -1
  }

  class Card(val values: Array[Int]) {
    val ROW_LENGTH = 5
    val rows: Array[Array[Int]] = values.grouped(ROW_LENGTH).toArray

    def crossOut(value: Int): Unit = {
      rows
        .filter(r => r.contains(value))
        .foreach(r => r.update(r.indexOf(value), -1))
    }

    def fullRow: Array[Int] = {
      for (row <- rows) {
        if (row.forall(x => x == -1)) return row
      }
      null
    }

    def fullCol: Array[Int] = {
      for (i <- 0 until ROW_LENGTH) {
        val row = rows.map(r => r(i))
        if (row.forall(x => x == -1)) return row
      }
      null
    }
  }
}