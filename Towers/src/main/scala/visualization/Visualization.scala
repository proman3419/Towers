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
    this.prefHeight = 800
    this.spacing = 20
    val problemCreator = new ProblemCreator()
    var difficulty: Difficulties.Difficulty = Difficulties.Easy
    var gameSize: Int = 5
    var problem: Problem = problemCreator.createProblem(this.gameSize, this.difficulty)
    val grid = new Grid(this.gameSize+2, problem.problem.arr)
    displayMenu()

    def displayMenu() {
        var infoText = "Some info text"
        val info = new Label(infoText)
        val button = new Button("Start")
        button.onAction = onButtonClick
        this.children = Array(info, button)
    }

    def displayTowers() {
        val gridVisualization = new GridVisualization(grid, problem, this)
        this.children = Array(gridVisualization)
    }

    def checkWin() {
        if (problem.verifyPlayerSolution(grid)) {
            this.children = Array(new WinScene)
        }
    }

    def onButtonClick(event: ActionEvent): Unit = {
        // this.difficulty = "something"
        // this.gameSize = 12
        displayTowers()
    }
}
