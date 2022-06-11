package logic

class Grid(val size: Int) {
    val borderedSize: Int = size + 2
    var cells: Array[Array[Cell]] = Array.ofDim[Cell](borderedSize, borderedSize)

    for (y <- 0 until borderedSize) {
        for (x <- 0 until borderedSize) {
            cells(y)(x) = new Cell(x, y, true, x)
        }
    }

    override
    def toString: String = {
        cells.map(_.mkString(" ")).mkString("\n")
    }
}
