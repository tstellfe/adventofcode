fun main() {
  fun part1(input: List<Int>): Int {
    var countIncreases = 0
    input.reduce { first, second ->
      if (second > first) countIncreases++
      second
    }
    return countIncreases
  }

  fun part2(input: List<Int>): Int {
    val summedInput = input.windowed(3, 1, true) {
      it.sum()
    }

    return part1(summedInput)
  }

  val testInput = readInput("Day01").map { it.toInt() }
  println(part2(testInput))
}
