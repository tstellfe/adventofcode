fun main() {
  var horizontal = 0
  var depth = 0

  fun part1(input: List<String>): Int {
    input.map {
      it.split(" ").let { (direction, value) ->
        Pair(direction, +value.toInt())
      }
    }.forEach {
      if (it.first == "forward") horizontal += it.second
      if (it.first == "down") depth += it.second
      if (it.first == "up") depth -= it.second
    }

    return horizontal * depth
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  val testInput = readInput("Day02")
  println(part1(testInput))
}
