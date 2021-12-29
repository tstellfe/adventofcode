fun main() {
  // digit to segments
  val uniqueDigitMap = mapOf(
    1 to 2,
    4 to 4,
    7 to 3,
    8 to 7
  )

  val digitMap = mapOf(
    0 to 6,
    1 to 2,
    2 to 5,
    3 to 5,
    4 to 4,
    5 to 5,
    6 to 6,
    7 to 3,
    8 to 7,
    9 to 6
  )

  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      line.toSegmentDigits().second.mapNotNull { d ->
        uniqueDigitMap.entries.find { it.value == d.length }?.key
      }.size
    }
  }

  fun part2(input: List<String>): Int {
    input.forEach { line ->
      line.toSegmentDigits().first
    }

    return 0
  }

  val testInput = readInput("Day08_test")

  println(part2(testInput))
}

fun String.toSegmentDigits(): Pair<List<String>, List<String>> {
  return this.split(" | ").map { col -> col.split(" ") }.zipWithNext().single()
}
