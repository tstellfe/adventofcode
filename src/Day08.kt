fun main() {
  // digit to segments
  val uniqueDigitMap = mapOf(
    1 to 2,
    4 to 4,
    7 to 3,
    8 to 7
  )

  fun part1(input: List<String>): Int {
    return input.sumOf { line ->
      line.toSegmentDigits().second.mapNotNull { d ->
        uniqueDigitMap.entries.find { it.value == d.length }?.key
      }.size
    }
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  val testInput = readInput("Day08")

  println(part1(testInput))
}

fun String.toSegmentDigits(): Pair<List<String>, List<String>> {
  return this.split(" | ").map { col -> col.split(" ") }.zipWithNext().single()
}
