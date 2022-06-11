package gorman

class Grid {
    private var Int size
    private var Int borderedSize
    private var Array[Array[Cell]] cells

    public Grid {
        this.size = size
        this.borderedSize = size + 2 // 0, size+1 for borders
        this.cells = Array.ofDim[Cell](borderedSize, borderedSize)

        for (y <- 0 until borderedSize) {
            for (x <- 0 until borderedSize+1) {
                this.cells(y)(x) = new Class(x, y)
            }
        }
    }
}
