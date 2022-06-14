package logic

class Cell(
    val row: Int, 
    val col: Int, 
    val gridSize: Int,
    val isMutable: Boolean,
    val isBorder: Boolean,
    var value: Int
) {
    val problemSize: Int = gridSize - 2
    val isCorner: Boolean = this.row % (this.gridSize-1) == 0 && this.col % (this.gridSize-1) == 0

    def setValue(value: Int): Unit = {
        if (isMutable) {
            this.value = value
        }
    }

    def nextValue(): Unit = {
        if (isMutable) {
            value = (value + 1) % (problemSize + 1)
        }
    }

    override
    def toString: String = {
        value.toString()
    }
}
