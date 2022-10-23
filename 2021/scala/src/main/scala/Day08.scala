import scala.collection.mutable
import scala.util.control.Breaks.break

object Day08 extends Day {
  val entries: Array[String] = input.split("\n")

  override def q1(): Long = {
    entries
      .map(x => x.split(" \\| ")(1).split(" ")
        .filter(x => Set(2, 4, 3, 7).contains(x.length)))
      .map(x => x.length)
      .sum
  }

  override def q2(): Long = {
    var sum = 0
    for(entry <- entries) {
      val mappings = deduce(entry.split(" \\|")(0).split(" "))
      val values = entry.split(" \\| ")(1).split(" ")
      sum += values
        .map(x => mappings.indexOf(mappings.find(y => y.toSet.equals(x.toSet)).get).toString)
        .reduce((x,y) => x + y)
        .toInt
    }
    sum
  }


  def deduce(values: Array[String]): Array[mutable.HashSet[Char]] = {
    val charSetArray:Array[mutable.HashSet[Char]] = (0 to 9).map(_ => new mutable.HashSet[Char]).toArray
    // easy ones
    charSetArray(1).addAll(values.find(s => s.length == 2).get)
    charSetArray(4).addAll(values.find(s => s.length == 4).get)
    charSetArray(7).addAll(values.find(s => s.length == 3).get)
    charSetArray(8).addAll(values.find(s => s.length == 7).get)
    // 6 has 6 segments and is the only one not sharing entire structure of 1
    charSetArray(6).addAll(values.find(s => s.length == 6 && !charSetArray(1).subsetOf(s.toSet)).get)
    // 9 has 6 segments and is the only one sharing entire structure of 4
    charSetArray(9).addAll(values.find(s => s.length == 6 && charSetArray(4).subsetOf(s.toSet)).get)
    // 0 has 6 segments and is not 6 or 9
    charSetArray(0).addAll(values.find(s => s.length == 6 &&
      (!s.toSet.equals(charSetArray(6).toSet) && !s.toSet.equals(charSetArray(9).toSet))).get)
    // 3 has 5 segments and is the only number sharing entire structure of 7
    charSetArray(3).addAll(values.find(s => s.length == 5 && charSetArray(7).subsetOf(s.toSet)).get)
    // 5 has 5 segments and is 6 with one extra segment
    charSetArray(5).addAll(values.find(s => s.length == 5 && charSetArray(6).diff(s.toSet).size == 1).get)
    // 2 is the only one left
    charSetArray(2).addAll(values.find(string => !charSetArray.map(set => set.toSet).contains(string.toSet)).get)
    charSetArray
  }
}
