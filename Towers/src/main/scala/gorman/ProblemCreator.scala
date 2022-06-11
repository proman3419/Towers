import scala.util.Random
import scala.collection
import scala.util.control.Breaks._

class ProblemCreator(val n: Int){
    var result: List[List[Int]] = List.empty[List[Int]]

    def init_problem(){
        for (i <- 0 until n) {
            result :+ Random.shuffle(List.range(1, n+1))
        }

        var impossibles_per_col = List.empty[collection.mutable.Set[Int]]
        for (i <- 0 until n) {
            impossibles_per_col :+ collection.mutable.Set(result(0)(i))
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