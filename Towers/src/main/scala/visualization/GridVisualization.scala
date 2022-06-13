package visualization

import logic.Grid
import scalafx.geometry.Pos
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints}
import scala.collection.mutable

class GridVisualization(
    var grid: Grid
) extends GridPane {
    val cellSize = 100

    alignment = Pos.Center
    this.drawGrid()

    def drawGrid(): Unit = {
        this.gridLinesVisible = false
        this.columnConstraints = for (i <- 0 until grid.size) yield new ColumnConstraints(cellSize)
        this.rowConstraints = for (i <- 0 until grid.size) yield new RowConstraints(cellSize)
        this.children = Array()
        this.gridLinesVisible = true

        drawCells()
    }

    def drawCells(): Unit = {
        for (cell <- grid.cells.flatMap(_.toList)) {
            this.add(new CellVisualization(cell, cellSize), cell.row, cell.col)
        }
    }
}
