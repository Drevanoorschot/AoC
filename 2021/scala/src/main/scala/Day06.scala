import scala.collection.mutable

object Day06 extends Day {
  val fishes: Array[Fish] = input
    .split(",")
    .map(x => new Fish(x.toInt))

  override def q1(): Long = {
    var fishesCopy = fishes
    for (_ <- 0 until 80) {
      fishesCopy.foreach(fish => fish.tick())
      val newFishes: Array[Fish] = fishesCopy
        .filter(x => x.isExpecting)
        .map(x => x.duplicate())
      fishesCopy = fishesCopy.appendedAll(newFishes)
    }
    fishesCopy.length
  }

  override def q2(): Long = {
    var state: Array[Long] = Array.fill(9) {
      0
    }
    input
      .split(",")
      .map(x => x.toInt)
      .foreach(i => state(i) += 1)
    for(_ <- 0 until 256) {
      val newState:Array[Long] = Array.fill(9){0}
      for(i <- newState.indices) {
        if (i == 6) newState(6) = state(0) + state(7)
        else if(i == 8) newState(8) = state(0)
        else newState(i) = state(i + 1)
      }
      state = newState
    }
    state.sum
  }
}

class Fish(var timer: Int) {

  def tick(): Unit = {
    timer -= 1
  }

  def isExpecting: Boolean = {
    timer == -1
  }

  def duplicate(): Fish = {
    this.timer = 6
    new Fish(8)
  }
}