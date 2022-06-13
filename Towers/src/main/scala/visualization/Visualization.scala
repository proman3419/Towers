package visualization

import logic._
import visualization._
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox

import scala.collection.mutable

class Visualization() extends VBox {
    this.alignment = Pos.Center
    this.prefWidth = 800
    this.prefHeight = 600
    this.spacing = 20

    val problemCreator = new ProblemCreator()
    val problem = problemCreator.createProblem(5, Difficulties.Easy)
    val grid = new Grid(5, problem.problem.arr)

    val gridVisualization = new GridVisualization(grid)

    var infoText = "Some info text"
    val info = new Label(infoText)
    val button = new Button("Click me")
    button.onAction = onButtonClick

    def onButtonClick(event: ActionEvent): Unit = {
        println("Clicked a button")
    }

    children = Array(gridVisualization)
}
