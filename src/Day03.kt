import java.io.File
import java.util.BitSet

fun main() {
  fun part1(input: List<String>): Int {
    val columnMap = input.switchLinesToColumns()
    val epsilonRate = columnMap.calculateRate('0', '1')
    val gammaRate = columnMap.calculateRate('1', '0')

    return gammaRate*epsilonRate
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  val testInput = readInput("Day03");
  println(part1(testInput))
}

fun List<String>.switchLinesToColumns(): MutableMap<Int, MutableList<Char>> {
  val map = mutableMapOf<Int, MutableList<Char>>()
  this.map { line ->
    line.toCharArray().forEachIndexed { index, bit ->
      map[index]?.add(bit) ?: map.putIfAbsent(index, mutableListOf(bit))
    }
  }
  return map
}

fun MutableMap<Int, MutableList<Char>>.calculateRate(small: Char, big: Char) = this.map { item ->
  if (item.value.count { it == big } > item.value.count { it == small }) 0 else 1
}.joinToString("", "").toInt(2)
