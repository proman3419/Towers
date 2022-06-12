package logic

import scala.util.Random
import scala.collection
import scala.collection.mutable
import scala.util.control.Breaks._

class ProblemCreator(val n: Int){
    var result: Array[Array[Int]] = Array.ofDim[Int](n, n)

    def validateProblem() : Boolean = {
        var rows = Array.fill[mutable.Set[Int]](n)(mutable.Set.empty)
        var cols = Array.fill[mutable.Set[Int]](n)(mutable.Set.empty)

        for (row <- 1 until n) {
            for (col <- 0 until n) {
                val currVal = result(row)(col)
                if (rows(row).contains(currVal) ||
                    cols(col).contains(currVal))
                    return false
                rows(row).add(currVal)
                cols(col).add(currVal)
            }
        }

        return true
    }

    def init_problem() : Unit = {
        for (i <- 0 until n) {
            var tmp = Random.shuffle(List.range(1, n+1))
            for (j <- 0 until tmp.size) {
                result(i)(j) = tmp(j)
            }
        }

/*        println("RESULT BEFORE INIT:")
        for (i <- 0 until n) {
            for (j <- 0 until n) {
                print(result(i)(j))
            }
            println()
        }
        println()*/

        var impossibles_per_col = Array.ofDim[mutable.Set[Int]](n)
        for (i <- 0 until n) {
            impossibles_per_col(i) = mutable.Set(result(0)(i))
        }

        for (row <- 1 until n){
            for (col <- 0 until n){
                if (impossibles_per_col(col).contains(result(row)(col))){
                    var i = 1
                    var changing_col = 0
                    breakable{
                        while (i < n){
                            changing_col = (i + col) % n
                            if (!(impossibles_per_col(changing_col)(result(row)(col))) && 
                                !(impossibles_per_col(col)(result(row)(changing_col)))){
                                var tmp = result(row)(col)
                                result(row)(col) = result(row)(changing_col)
                                result(row)(changing_col) = tmp
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

            // println("SETS IN INIT:")
            // for (i <- 0 until n) {
            //     for (j <- 0 until row) {
            //         print(impossibles_per_col(i)(j))
            //     }
            // println(impossibles_per_col(0))
            // }
            // println()
            
        }

        val fullSet = (1 to n).toSet
        for (col <- 0 until n) {
            // println("fullset", fullSet)
            // println("imp", impossibles_per_col(col))
            val diffSet = fullSet &~ impossibles_per_col(col)
            // println("diff", diffSet)
            if (!diffSet.isEmpty) {
                result(n-1)(col) = diffSet.head
            }
        }

        val isProblemValid = validateProblem()
        println(isProblemValid)
        if (!isProblemValid)
            init_problem()
    }
}
