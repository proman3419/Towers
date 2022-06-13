package visualization

import logic.Cell
import scalafx.geometry.Pos
import scalafx.scene.control.Button
import scala.collection.mutable
import scalafx.event.ActionEvent
import scalafx.Includes._

class CellVisualization(
    var cell: Cell, 
    val size: Int
) extends Button {
    this.text = cell.value.toString
    this.onAction = onButtonClick
    this.prefWidth = size
    this.prefHeight = size
    this.style = (if (cell.isMutable) "-fx-background-color: #9595b7;" 
        else "-fx-background-color: #7676a2;") + "-fx-font-size: 32px;"

    def onButtonClick(event: ActionEvent): Unit = {
        cell.nextValue()
        this.text = cell.value.toString
    }
}
