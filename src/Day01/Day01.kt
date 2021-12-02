fun main() {
    fun part1(input: List<String>): Int {
        var countIncreases = 0
        input.map { it.toInt() }.reduce { first, second ->
            if (second > first) countIncreases++
            second
        }
        return countIncreases
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day01", "Day01")
    println(part1(testInput))

}
