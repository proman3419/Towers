package visualization

import visualization._
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox

object VisualizationWrapper extends JFXApp3 {
    override def start(): Unit = {
        stage = new JFXApp3.PrimaryStage {
            // initStyle(StageStyle.Unified)
            title = "Towers"
            scene = new Scene {
                fill = Color.rgb(222, 160, 190)
                content = new Visualization
            }
        }
    }
}
