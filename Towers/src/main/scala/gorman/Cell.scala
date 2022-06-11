package gorman

class Cell(
    val x: Int, 
    val y: Int, 
    val isMutable: Boolean
) {
    private var Int value

    def setValue(newValue: Int) {
        if (isMutable) {
            this.value = value
        }
    }
}
