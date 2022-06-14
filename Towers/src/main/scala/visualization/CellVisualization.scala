package visualization

import logic.Cell
import logic.Grid
import logic.Problem
import visualization.Visualization
import scalafx.geometry.Pos
import scalafx.scene.control.Button
import scala.collection.mutable
import scalafx.event.ActionEvent
import scalafx.Includes._

class CellVisualization(
    var cell: Cell, 
    val size: Int,
    val visualizationMain: Visualization
) extends Button {
    this.text = cell.value.toString
    this.onAction = onButtonClick
    this.prefWidth = size
    this.prefHeight = size
    this.style = this.createStyle()

    def createStyle(): String = {
        if (cell.isCorner)
            this.text = ""
        if (cell.isBorder)
            return "-fx-background-color: #dea0be; -fx-font-size: 32px"
        
        var style: String = "-fx-font-size: 32px; -fx-border-size: 20px; -fx-border-style: solid;"
        if (cell.isMutable)
            style = style + "-fx-background-color: #9595b7;" 
        else 
            style = style + "-fx-background-color: #7676a2;"
        
        return style
    }

    def onButtonClick(event: ActionEvent): Unit = {
        cell.nextValue()
        this.text = cell.value.toString
        visualizationMain.checkWin()
    }
}
