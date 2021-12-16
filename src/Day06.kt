data class Lanternfish(var day: Int, val lifetime: Int) {
  val INITIAL_DAY_UNTIL_REPRODUCE = 8
  val REPRODUCED_INIT = 6

  fun getLifeTimeProducedLanternfishes(): List<Lanternfish> {
    val fishs = mutableListOf<Lanternfish>()
    for (i in 1..lifetime) {
      nextDay(lifetime - i)?.let {
        fishs.add(it)
      }
    }
    return fishs
  }

  fun nextDay(lifetime: Int): Lanternfish? {
    return if (day == 0) {
      day = REPRODUCED_INIT
      Lanternfish(INITIAL_DAY_UNTIL_REPRODUCE, lifetime)
    } else {
      day--
      null
    }
  }
}

fun main() {
  fun part1(input: List<Int>): Int {
    val days = 80
    val fishList = input.map { Lanternfish(it, days) }.toMutableList()

    for (i in 1..days) {
      val newFishes = fishList.map { it.nextDay(days - i) }.filterNotNull()
      fishList.addAll(newFishes)
    }

    return fishList.size
  }

  // Does not work. It is waaaaay to slow.
  fun part2(input: List<Int>): Int {
    val days = 256
    return input.map { Lanternfish(it, days) }.iterateThroughLifes()
  }

  val testInput = listOf(3, 4, 3, 1, 2)
  val testInput2 = listOf(1, 2, 3)

  println(part2(testInput2))
}

fun List<Lanternfish>.iterateThroughLifes(): Int {
  var count = this.count()
  this.forEach { count += it.getLifeTimeProducedLanternfishes().iterateThroughLifes() }
  return count
}
