import org.w3c.dom.ranges.Range

data class Coordinate(val x: Int, val y: Int)

fun main() {
  fun createCoordinateRangeDiagonal(from: Coordinate, to: Coordinate): List<Coordinate> {
    val horizontal = if (from.x < to.x) from.x..to.x else from.x downTo to.x
    val vertical = if (from.y < to.y) from.y..to.y else from.y downTo to.y

    return horizontal.mapIndexed { i, x -> Coordinate(x, vertical.elementAt(i)) }
  }

  fun createCoordinateRange(from: Coordinate, to: Coordinate, withDiagonal: Boolean = false): List<Coordinate> {
    val result = mutableListOf<Coordinate>()
    if (from.x == to.x) {
      if (from.y < to.y) for (y in from.y..to.y) result.add(Coordinate(from.x, y))
      else for (y in to.y..from.y) result.add(Coordinate(from.x, y))
    } else if (from.y == to.y) {
      if (from.x < to.x) for (x in from.x..to.x) result.add(Coordinate(x, from.y))
      else for (x in to.x..from.x) result.add(Coordinate(x, from.y))
    } else if(withDiagonal) {
      return createCoordinateRangeDiagonal(from, to)
    }
    return result
  }

  fun part1(input: List<String>): Int {
    val results = input.toCoordinatePairs().map { (from, to) ->
      createCoordinateRange(from, to)
    }.flatten()

    return results.groupBy { it }.filter { it.value.size > 1 }.size
  }

  fun part2(input: List<String>): Int {
    val results = input.toCoordinatePairs().map { (from, to) ->
      createCoordinateRange(from, to, true)
    }.flatten()

    return results.groupBy { it }.filter { it.value.size > 1 }.size
  }

  val testInput = readInput("Day05")
  println(part2(testInput))
}

fun List<String>.toCoordinatePairs(): List<Pair<Coordinate, Coordinate>> =
  this.map { line ->
    line.split(" -> ")
      .map { pair ->
        pair.split(",")
          .windowed(2, 2, false)
          { l -> Coordinate(l.first().toInt(), l.last().toInt()) }.single()
      }.zipWithNext().single()
  }
