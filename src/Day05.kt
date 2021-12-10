data class Coordinate(val x: Int, val y: Int)

fun main() {
  fun createCoordinateRange(from: Coordinate, to: Coordinate): List<Coordinate> {
    val result = mutableListOf<Coordinate>()
    if (from.x == to.x) {
      if (from.y < to.y) for (y in from.y..to.y) result.add(Coordinate(from.x, y))
      else for (y in to.y..from.y) result.add(Coordinate(from.x, y))
    } else if (from.y == to.y) {
      if (from.x < to.x) for (x in from.x..to.x) result.add(Coordinate(x, from.y))
      else for (x in to.x..from.x) result.add(Coordinate(x, from.y))
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
    return 0
  }

  val testInput = readInput("Day05")
  println(part1(testInput))
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
