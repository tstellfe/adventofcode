fun main() {
  fun part1(input: List<String>): Int {
    var result = 0

    input.forEachIndexed { lineIndex, line ->
      line.forEachIndexed col@{ columnIndex, column ->
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

  fun findBottom(pos: Pair<Int, Int>, matrix: List<List<Int>>, first: Boolean): Int {
    if (matrix[pos.first][pos.second] == 9) return 0
    var bottom = if (pos.first != matrix.size - 1) findBottom(Pair(pos.first + 1, pos.second), matrix, first) else 0

    if (first && pos.second != 0) {
      bottom += findBottom(Pair(pos.first, pos.second - 1), matrix, first)
    }

    return bottom + 1
  }

  fun addMostLeft(point: Pair<Int, Int>, matrix: List<List<Int>>): List<Pair<Int, Int>> {
    if (point.second == 0) return listOf()

    if (matrix[point.first][point.second - 1] != 9) {
      val foundPoint = Pair(point.first, point.second - 1)
      val result = mutableListOf<Pair<Int, Int>>()
      result.addAll(addMostLeft(foundPoint, matrix))
      result.add(foundPoint)
      return result
    }

    return listOf()
  }

  fun addMostRight(point: Pair<Int, Int>, matrix: List<List<Int>>): List<Pair<Int, Int>> {
    if (point.second == matrix[0].size - 1) return listOf()

    if (matrix[point.first][point.second + 1] != 9) {
      val foundPoint = Pair(point.first, point.second + 1)
      val result = mutableListOf<Pair<Int, Int>>()
      result.addAll(addMostRight(foundPoint, matrix))
      result.add(foundPoint)
      return result
    }

    return listOf()
  }

  fun findBasin(points: List<Pair<Int, Int>>, matrix: List<List<Int>>): List<Pair<Int, Int>> {
    if (points[0].first == matrix.size - 1) return listOf()

    val belowPoints = mutableListOf<Pair<Int, Int>>()
    points.forEachIndexed { index, p ->
      val pointBelow = Pair(p.first + 1, p.second)
      if (matrix[pointBelow.first][pointBelow.second] != 9) {
        // add left
        if (index == 0) {
          belowPoints.addAll(addMostLeft(pointBelow, matrix))
        }

        belowPoints.add(pointBelow)

        //add most right
        if (index == points.size - 1) {
          belowPoints.addAll(addMostRight(pointBelow, matrix))
        }
      }
    }

    return if (belowPoints.size > 0) {
      findBasin(belowPoints, matrix) + belowPoints
    } else belowPoints
  }

  /**
   * Does not work correctly - I am definitely missing some rule
   */
  fun part2(input: List<String>): Int {
    val alreadyUsedPoints = mutableSetOf<Pair<Int, Int>>()
    val basins = mutableListOf<Int>()
    val matrix = input.map { line -> line.map { it.digitToInt() } }

    matrix.forEachIndexed { lineIndex, lines ->
      var points = mutableListOf<Pair<Int, Int>>()

      lines.forEachIndexed { colIndex, col ->
        if (col != 9 && !alreadyUsedPoints.contains(Pair(lineIndex, colIndex))) {
          points.add(Pair(lineIndex, colIndex))
        }

        if ((col == 9 || colIndex == lines.size - 1) && points.size > 0) {
          val basin = findBasin(points, matrix) + points
          basins.add(basin.size)
          alreadyUsedPoints.addAll(basin)
          points = mutableListOf()
        }
      }
    }

    println(basins)

    basins.sortDescending()
    return basins[0] * basins[1] * basins[2]
  }

  val testInput = readInput("Day09_test")

  println(part2(testInput))
}
