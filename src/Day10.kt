fun main() {
  val characterMap = mapOf(
    Pair('(', ')'),
    Pair('[', ']'),
    Pair('{', '}'),
    Pair('<', '>'),
  )

  val scores = mapOf(
    Pair(')', 3),
    Pair(']', 57),
    Pair('}', 1197),
    Pair('>', 25137),
  )

  fun part1(input: List<String>): Int {
    val corruptedChars = mutableListOf<Char>()
    var openStack = mutableListOf<Char>()
    input.forEach line@{ line ->
      line.forEach { c ->
        if (characterMap.keys.contains(c)) openStack.add(c)
        else if (c == characterMap.get(openStack.last())) openStack = openStack.dropLast(1).toMutableList()
        else {
          corruptedChars.add(c)
          return@line
        }
      }
    }

    println(corruptedChars)
    return corruptedChars.sumOf { c -> scores.getOrDefault(c, 0) }
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  val testInput = readInput("Day10")

  println(part1(testInput))
}
