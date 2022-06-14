package visualization

import logic.Grid
import logic.Problem
import visualization.Visualization
import scalafx.geometry.Pos
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints}
import scala.collection.mutable

class GridVisualization(
    var grid: Grid,
    var problem: Problem,
    val visualizationMain: Visualization
) extends GridPane {
    val cellSize = 100
    alignment = Pos.Center
    this.drawGrid()
    this.gridLinesVisible = false
    
    def drawGrid(): Unit = {
        this.columnConstraints = for (i <- 0 until grid.size) yield new ColumnConstraints(cellSize)
        this.rowConstraints = for (i <- 0 until grid.size) yield new RowConstraints(cellSize)
        this.children = Array()

        drawCells()
    }

    def drawCells(): Unit = {
        for (cell <- grid.cells.flatMap(_.toList)) {
            this.add(new CellVisualization(cell, cellSize, visualizationMain), 
                cell.row, cell.col)
        }
    }
}
