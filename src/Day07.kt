fun main() {
  fun part1(input: List<Int>): Int {
    val min = input.minOrNull()!!
    val max = input.maxOrNull()!!

    val possibilities = mutableListOf<Int>()
    for (i in min..max) {
      possibilities.add(
        input.sumOf {
          val fuel = (it - i)
          if (fuel < 0) fuel.unaryMinus() else fuel
        }
      )
    }

    return possibilities.minOrNull()!!
  }

  fun part2(input: List<Int>): Int {
    val min = input.minOrNull()!!
    val max = input.maxOrNull()!!

    val possibilities = mutableListOf<Int>()
    for (i in min..max) {
      possibilities.add(
        input.sumOf {
          val fuel = (it - i)
          if (fuel < 0) (1..fuel.unaryMinus()).sum() else (1..fuel).sum()
        }
      )
    }

    return possibilities.minOrNull()!!
  }

  val testInput = readInput("Day04")

  val testInput1 = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
  val testInput2 = listOf(1,2,3)

  println(part2(testInput2))
}
