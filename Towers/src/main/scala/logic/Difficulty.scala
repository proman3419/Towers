package logic

object Difficulties extends Enumeration {
  type Difficulty = Value
  val Easy, Middle, Hard = Value

  def getPercent(difficulty: Difficulty): Double = difficulty match {
      case Easy => 0.4
      case Middle => 0.7
      case Hard => 1
  }
}
