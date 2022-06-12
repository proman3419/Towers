import logic.ProblemCreator
import logic.Problem

object Main {
    def main(args: Array[String]) = {
        val pc: ProblemCreator = new ProblemCreator()
        val problem: Problem = pc.createProblem(5)
        println(problem.solution)
    }
}
