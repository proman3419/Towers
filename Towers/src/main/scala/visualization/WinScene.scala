package visualization

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Label, Button}
import scalafx.scene.layout.VBox

import scala.collection.mutable

class WinScene() extends VBox {
    this.alignment = Pos.Center

    val victoryLabel = new Label("VICTORY");
    victoryLabel.style = "-fx-font-size: 36px; -fx-text-fill: green; -fx-font-weight: bold;"

    val returnButton = new Button("Main menu")
    returnButton.style = "-fx-font-size: 24px;"
    returnButton.onAction = onReturnButtonClick

    def onReturnButtonClick(event: ActionEvent): Unit = {
        this.children = Array(new Visualization)
    }

    this.children = Array(victoryLabel, returnButton)
}
