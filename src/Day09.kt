fun main() {
  fun part1(input: List<String>): Int {
    var result = 0

    input.forEachIndexed { lineIndex, line ->
      line.forEachIndexed col@ { columnIndex, column ->
        val col = column.digitToInt()

        if (lineIndex != 0) {
          if (col >= input[lineIndex - 1][columnIndex].digitToInt()) return@col
        }
        if (lineIndex != input.size - 1) {
          if (col >= input[lineIndex + 1][columnIndex].digitToInt()) return@col
        }
        if (columnIndex != 0) {
          if (col >= input[lineIndex][columnIndex - 1].digitToInt()) return@col
        }
        if (columnIndex != line.length - 1) {
          if (col >= input[lineIndex][columnIndex + 1].digitToInt()) return@col
        }
        result += col + 1
      }
    }

    return result
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  val testInput = readInput("Day09_test")

  println(part1(testInput))
}
