fun main() {
  fun part1(input: List<Int>): Int {
    val min = input.minOrNull()!!
    val max = input.maxOrNull()!!

    val possibilities = mutableListOf<Int>()
    for (i in min..max) {
      possibilities.add(
        input.sumOf {
          val result = (it - i)
          if (result < 0) result.unaryMinus() else result
        }
      )
    }

    return possibilities.minOrNull()!!
  }

  fun part2(input: List<Int>): Int {
    return 0
  }

  val testInput = readInput("Day04")

  val testInput1 = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
  val testInput2 = listOf(1,2,3)

  println(part1(testInput2))
}
