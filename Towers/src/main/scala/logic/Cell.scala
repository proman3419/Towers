package logic

class Cell(
    val row: Int, 
    val col: Int, 
    val isMutable: Boolean,
    var value: Int
) {
    def setValue(value: Int) = {
        if (isMutable) {
            this.value = value
        }
    }

    override
    def toString: String = {
        value.toString()
    }
}
