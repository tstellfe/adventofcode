import java.math.BigInteger

fun main() {
  val characterMap = mapOf(
    Pair('(', ')'),
    Pair('[', ']'),
    Pair('{', '}'),
    Pair('<', '>'),
  )

  fun part1(input: List<String>): Int {
    val scores = mapOf(
      Pair(')', 3),
      Pair(']', 57),
      Pair('}', 1197),
      Pair('>', 25137),
    )

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

  fun part2(input: List<String>): Long {
    val scores = mapOf(
      Pair(')', 1),
      Pair(']', 2),
      Pair('}', 3),
      Pair('>', 4),
    )

    val results: List<Long> = input.map line@{ line ->
      var openStack = mutableListOf<Char>()

      line.forEach { c ->
        if (characterMap.keys.contains(c)) openStack.add(c)
        else if (c == characterMap.get(openStack.last())) openStack = openStack.dropLast(1).toMutableList()
        else return@line 0
      }

      openStack.map { characterMap.get(it) }.reversed()
        .fold(0L) { pre, char -> 5 * pre + scores.getOrDefault(char, 0) }
    }.filter { it != 0L }

    println(results.sorted())
    return results.sorted()[(results.size - 1) / 2]
  }

  val testInput = readInput("Day10")

  println(part2(testInput))
}
