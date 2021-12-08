fun main() {
  fun part1(input: List<String>): Int {
    val columnMap = input.switchLinesToColumns()
    val epsilonRate = columnMap.calculateRate(0, 1)
    val gammaRate = columnMap.calculateRate(1, 0)

    return gammaRate * epsilonRate
  }

  fun part2(input: List<String>): Int {
    val columnMap = input.switchLinesToColumns()
    val oxygenGeneratorRating = columnMap.calculateRating(1, 0)
    println(oxygenGeneratorRating)
    val co2ScrubberRating = columnMap.calculateRating(0, 1)
    println(co2ScrubberRating)
    return oxygenGeneratorRating*co2ScrubberRating
  }

  val testInput = readInput("Day03");
  println(part2(testInput))
}

fun List<String>.switchLinesToColumns(): List<MutableList<Int>> {
  val charList = mutableListOf<MutableList<Int>>()
  this.map { line ->
    line.toCharArray().forEachIndexed { index, bit ->
      charList.getOrNull(index)?.add(bit.digitToInt()) ?: charList.add(index, mutableListOf(bit.digitToInt()))
    }
  }
  return charList
}

fun List<MutableList<Int>>.calculateRate(small: Int, big: Int) = this.map { item ->
  if (item.count { it == big } > item.count { it == small }) 0 else 1
}.toDec()

/**
 * don't even look at it...
 */
fun List<MutableList<Int>>.calculateRating(small: Int, big: Int): Int {
  val indexes = mutableListOf<Int>()
  return this.map { item ->
    val filtered = item.filterIndexed { i, _ -> !indexes.contains(i) }

    if (filtered.size == 1) {
      return this.map { it.filterIndexed { i, _ -> !indexes.contains(i) }.first() }.toDec()
    }

    val keepBit = if (filtered.count { it == 0 } > filtered.count { it == 1 }) big else small

    item.filterIndexed { i, bit ->
      if (bit != keepBit) {
        indexes.add(i)
      }
      false
    }

    keepBit
  }.toDec()
}

fun List<Int>.toDec() = this.joinToString("", "").toInt(2)
