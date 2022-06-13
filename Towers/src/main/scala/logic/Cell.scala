package logic

class Cell(
    val row: Int, 
    val col: Int, 
    val gridSize: Int,
    val isMutable: Boolean,
    var value: Int
) {
    def setValue(value: Int): Unit = {
        if (isMutable) {
            this.value = value
        }
    }

    def nextValue(): Unit = {
        if (isMutable) {
            value = (value + 1) % (gridSize + 1)
        }
    }

    override
    def toString: String = {
        value.toString()
    }
}
