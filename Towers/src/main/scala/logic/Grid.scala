package logic

class Grid(
    val size: Int, 
    val arr: Array[Array[Int]]
) {
    var cells: Array[Array[Cell]] = Array.ofDim[Cell](size, size)

    for (row <- 0 until size) {
        for (col <- 0 until size) {
            val isBorder: Boolean = row % (size-1) == 0 || 
                                    col % (size-1) == 0
            val isEmpty: Boolean = arr(row)(col) == 0
            cells(row)(col) = new Cell(row, col, size, !isBorder && isEmpty, isBorder, arr(row)(col))
        }
    }

    override
    def toString: String = {
        cells.map(_.mkString(" ")).mkString("\n")
    }

    override
    def equals(other: Any): Boolean = {
        other match {
            case o: Grid => {
                var equal: Boolean = true
                for (row <- 0 until size) {
                    for (col <- 0 until size) {
                        equal = equal && (this.cells(row)(col).value == o.cells(row)(col).value)
                    }
                }
                equal
            }
            case _ => false
        }
    }
}
