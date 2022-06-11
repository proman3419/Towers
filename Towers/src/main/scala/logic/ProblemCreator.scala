import scala.util.Random
import scala.collection
import scala.util.control.Breaks._

class ProblemCreator(val n: Int){
    var result: Array[Array[Int]] = Array.ofDim[Int](n, n)

    def init_problem() = {
        for (i <- 0 until n) {
            var tmp = Random.shuffle(List.range(1, n+1))
            for (j <- 0 until tmp.size) {
                result(i)(j) = tmp(j)
            }
        }

        println("RESULT BEFORE INIT:")
        for (i <- 0 until n) {
            for (j <- 0 until n) {
                print(result(i)(j))
            }
            println()
        }
        println()

        var impossibles_per_col = Array.ofDim[collection.mutable.Set[Int]](n)
        for (i <- 0 until n) {
            impossibles_per_col(i) = collection.mutable.Set(result(0)(i))
        }

        for (row <- 1 until n){
            for (col <- 0 until n){
                if (impossibles_per_col(col).contains(result(row)(col))){
                    var i = 1
                    var changing_col = 0
                    breakable{
                        while (i < n){
                            changing_col = (i + col) % n
                            if (!(impossibles_per_col(changing_col).contains(result(row)(col))) && !(impossibles_per_col(col).contains(result(row)(changing_col)))){
                                val tmp = result(row)(col)
                                result(row).updated(col, result(row)(changing_col))
                                result(row).updated(changing_col, tmp)
                                break
                            }
                            i = i + 1
                        }
                    }
                }
            }

            for (col <- 0 until n){
                impossibles_per_col(col).add(result(row)(col))
            }
        }
    }
}

object Appl {
    def main(args: Array[String]) = {
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
