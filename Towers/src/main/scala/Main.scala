import logic.Grid
import logic.ProblemCreator

object Main {
    def main(args: Array[String]) = {
/*        println("Hello, world")
        val grid = new Grid(5)
        println(grid)

*/      
        val n = 5
        val pc = new ProblemCreator(n)

        pc.init_problem()

        println("RESULT AFTER INIT:")
        for (i <- 0 until n) {
            for (j <- 0 until n) {
                print(pc.result(i)(j))
            }
            println()
        }

    }
}
