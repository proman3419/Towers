package visualization

import logic._
import visualization._
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label, TextField, ChoiceBox}
import scalafx.scene.layout.VBox
import scalafx.collections.ObservableBuffer

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

    def displayMenu(): Unit = {
        val infoLabel = new Label("MENU")
        infoLabel.style = "-fx-font-size: 36px; -fx-font-weight: bold;"
        val buttonEasy = new Button("Start Easy")
        buttonEasy.style = "-fx-font-size: 24px;"
        buttonEasy.onAction = startEasyGame
        val buttonMiddle = new Button("Start Middle")
        buttonMiddle.style = "-fx-font-size: 24px;"
        buttonMiddle.onAction = startMiddleGame
        val buttonHard = new Button("Start Hard")
        buttonHard.style = "-fx-font-size: 24px;"
        buttonHard.onAction = startHardGame

        this.children = Array(infoLabel, buttonEasy, buttonMiddle, buttonHard)
    }

    def displayTowers(): Unit = {
        val gridVisualization = new GridVisualization(grid, problem, this)
        this.children = Array(gridVisualization)
    }


    def setSizeAndDisplayTowers(): Unit = {
        this.gameSize = 5
        this.displayTowers()
    }

    def checkWin(): Unit = {
        if (problem.verifyPlayerSolution(grid)) {
            this.children = Array(new WinScene)
        }
    }

    def startEasyGame(event: ActionEvent): Unit = {
        this.difficulty = Difficulties.Easy
        setSizeAndDisplayTowers()
    }

    def startMiddleGame(event: ActionEvent): Unit = {
        this.difficulty = Difficulties.Middle
        setSizeAndDisplayTowers()
    }

    def startHardGame(event: ActionEvent): Unit = {
        this.difficulty = Difficulties.Hard
        setSizeAndDisplayTowers()
    }
}
