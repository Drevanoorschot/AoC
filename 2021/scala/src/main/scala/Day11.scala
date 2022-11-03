import scala.collection.mutable

object Day11 extends Day {
  var flashes = 0
  var steps = 0

  val octos: Array[Array[Octopus]] = input
    .split('\n')
    .map(line => line.toCharArray.map(
      char => new Octopus(char.asDigit)
    ))
  // it's reference time
  for (i <- octos.indices) for (j <- octos(i).indices) for (x <- -1 to 1) for (y <- -1 to 1) {
    try {
      if (x != 0 || y != 0) try {
        octos(i)(j).neighbours.add(octos(i + x)(j + y))
      } catch {
        case _: ArrayIndexOutOfBoundsException =>
      }
    }
  }

  override def q1(): Long = {
    for (_ <- 0 until 100) {
      simulateStep()
    }
    this.flashes
  }

  override def q2(): Long = {
    while (octos.flatten.exists(octo => octo.energyLevel != 0)) {
      simulateStep()
    }
    this.steps
  }

  def simulateStep(): Unit = {
    octos
      .foreach(octoRow => octoRow
        .foreach(octo => octo.increaseEnergy())
      )
    while (octos.flatten.exists(octo => octo.energyLevel > 9 && !octo.flashed)) {
      octos
        .flatten
        .filter(octo => octo.energyLevel > 9 && !octo.flashed)
        .foreach(octo => octo.flash())
    }
    steps += 1
    flashes += octos
      .flatten
      .filter(octo => octo.flashed)
      .map(octo => {
        octo.flashed = false
        octo.energyLevel = 0
      })
      .length
  }

  class Octopus(var energyLevel: Int) {

    var flashed = false
    val neighbours = new mutable.HashSet[Octopus]()

    def increaseEnergy(): Unit = this.energyLevel = this.energyLevel + 1

    def flash(): Unit = {
      this.flashed = true
      neighbours
        .filter(octo => !octo.flashed)
        .foreach(octo => octo.increaseEnergy())
    }
  }
}
