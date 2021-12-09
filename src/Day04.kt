fun main() {
  data class BingoMatrixItem(val index: Int, val number: Int, var checked: Boolean = false)
  data class BingoMatrixRow(val index: Int, val items: List<BingoMatrixItem>) {
    fun hasFullRow() = items.all { it.checked }
  }

  data class BingoMatrix(val rows: List<BingoMatrixRow>) {
    fun hasWon(row: BingoMatrixRow, item: BingoMatrixItem): Boolean =
      row.hasFullRow() || hasCompleteColumn(item.index, row.index)
    // || hasCompleteDiagonal(item.index, row.index)

    fun hasCompleteColumn(columnIndex: Int, rowIndex: Int): Boolean {
      for (i in 0..4) {
        if (!this.rows[i].items[columnIndex].checked) {
          return false
        }
      }

      return true
    }

//    fun hasCompleteDiagonal(columnIndex: Int, rowIndex: Int): Boolean {
//      for (i in 0..4) {
//        if (!this.rows[i].items[i].checked || !this.rows[4-i].items[i].checked) {
//          return false
//        }
//      }
//
//      return true
//    }

    fun sumUnmarkedItems(): Int {
      var sum = 0
      rows.forEach { row ->
        row.items.forEach { item ->
          if (!item.checked) {
            sum += item.number
          }
        }
      }
      return sum
    }

//    recursive == kotlin stackoverflow :(
//    fun hasCompleteColumn(columnIndex: Int, rowIndex: Int): Boolean {
//      if (!this.rows[rowIndex].items[columnIndex].checked) {
//        return false
//      }
//
//      val top = if (rowIndex > 0) hasCompleteColumn(columnIndex, rowIndex - 1) else true
//      val bottom = if (rowIndex < 4) hasCompleteColumn(columnIndex, rowIndex + 1) else true
//
//      return top && bottom
//    }

  }

  fun part1(input: List<String>): Int {
    val drawnNumbers = input[0].split(",").map { it.toInt() }
    val bingoMatrix = input.drop(2).windowed(5, step = 6, false) { matrix ->
      BingoMatrix(matrix.mapIndexed { i, row ->
        BingoMatrixRow(i,
          row.trim().split("\\s+".toRegex()).mapIndexed { ii, number ->
            BingoMatrixItem(ii, number.toInt())
          }
        )
      })
    }

    drawnNumbers.forEach { drawnNumber ->
      bingoMatrix.forEach { matrix ->
        var foundItem: BingoMatrixItem? = null
        val foundRow = matrix.rows.find { row ->
          foundItem = row.items.find { it.number == drawnNumber }
          foundItem != null
        }

        if (foundRow != null && foundItem != null) {
          matrix.rows[foundRow.index].items[foundItem!!.index].checked = true
          if (matrix.hasWon(foundRow, foundItem!!)) {
            return matrix.sumUnmarkedItems() * drawnNumber
          }
        }
      }
    }

    return -1
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  val testInput = readInput("Day04")
  println(part1(testInput))
}
