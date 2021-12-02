fun main() {
  var horizontal = 0
  var depth = 0
  var aim = 0

  fun part1(input: List<String>): Int {
    input.mapInputToDirectionPair().forEach {
      if (it.first == "forward") horizontal += it.second
      if (it.first == "down") depth += it.second
      if (it.first == "up") depth -= it.second
    }

    return horizontal * depth
  }

  fun part2(input: List<String>): Int {
    input.mapInputToDirectionPair().forEach {
      if (it.first == "forward") {
        horizontal += it.second
        if (aim != 0) depth += aim * it.second
      }
      if (it.first == "down") aim += it.second
      if (it.first == "up") aim -= it.second
    }

    return horizontal * depth
  }

  val testInput = readInput("Day02")
  println(part2(testInput))
}

fun List<String>.mapInputToDirectionPair(): List<Pair<String, Int>> = this.map {
  it.split(" ").let { (direction, value) ->
    Pair(direction, +value.toInt())
  }
}
