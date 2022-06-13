package logic

import logic.Grid
import logic.Problem
import scala.util.Random
import scala.collection
import scala.collection.mutable
import scala.util.control.Breaks._

class ProblemCreator {
    def validateProblem(problemArr: Array[Array[Int]], size: Int): Boolean = {
        var rows = Array.fill[mutable.Set[Int]](size)(mutable.Set.empty)
        var cols = Array.fill[mutable.Set[Int]](size)(mutable.Set.empty)

        for (row <- 1 until size) {
            for (col <- 0 until size) {
                val currVal = problemArr(row)(col)
                if (rows(row).contains(currVal) ||
                    cols(col).contains(currVal))
                    return false
                rows(row).add(currVal)
                cols(col).add(currVal)
            }
        }
        return true
    }

    def createProblemCore(size: Int): Array[Array[Int]] = {
        var problemArr: Array[Array[Int]] = Array.ofDim[Int](size, size)

        for (i <- 0 until size) {
            var tmp = Random.shuffle(List.range(1, size+1))
            for (j <- 0 until tmp.size) {
                problemArr(i)(j) = tmp(j)
            }
        }

        var impossiblesCol = Array.ofDim[mutable.Set[Int]](size)
        for (i <- 0 until size) {
            impossiblesCol(i) = mutable.Set(problemArr(0)(i))
        }

        for (row <- 1 until size) {
            for (col <- 0 until size) {
                if (impossiblesCol(col).contains(problemArr(row)(col))) {
                    var i = 1
                    var changing_col = 0
                    breakable{
                        while (i < size) {
                            changing_col = (i + col) % size
                            if (!(impossiblesCol(changing_col)(problemArr(row)(col))) && 
                                !(impossiblesCol(col)(problemArr(row)(changing_col)))) {
                                var tmp = problemArr(row)(col)
                                problemArr(row)(col) = problemArr(row)(changing_col)
                                problemArr(row)(changing_col) = tmp
                                break
                            }
                            i = i + 1
                        }
                    }
                }
            }

            for (col <- 0 until size) {
                impossiblesCol(col).add(problemArr(row)(col))
            }            
        }

        val fullSet = (1 to size).toSet
        for (col <- 0 until size) {
            val diffSet = fullSet &~ impossiblesCol(col)
            if (!diffSet.isEmpty) {
                problemArr(size-1)(col) = diffSet.head
            }
        }

        if (!validateProblem(problemArr, size))
            return createProblemCore(size)
        return problemArr
    }

    def addBorderValues(problemArr: Array[Array[Int]], size: Int): Array[Array[Int]] = {
        val borderedSize: Int = size + 2
        var borderedProblemArr: Array[Array[Int]] = Array.ofDim[Int](borderedSize, borderedSize)

        for (row <- 0 until size) {
            for (col <- 0 until size) {
                borderedProblemArr(row+1)(col+1) = problemArr(row)(col)
            }
        }

        for (i <- 1 to size) {
            var maxValues: Array[Int] = Array.fill[Int](4)(0)
            for (j <- 1 to size) {
                //left border
                if (maxValues(0) < borderedProblemArr(i)(j)){
                    borderedProblemArr(i)(0) += 1
                    maxValues(0) = borderedProblemArr(i)(j)
                }
                //right border
                if (maxValues(1) < borderedProblemArr(i)(size + 1 - j)){
                    borderedProblemArr(i)(size+1) += 1
                    maxValues(1) = borderedProblemArr(i)(size + 1 - j)
                }
                //top border
                if (maxValues(2) < borderedProblemArr(j)(i)){
                    borderedProblemArr(0)(i) += 1
                    maxValues(2) = borderedProblemArr(j)(i)
                }
                //down border
                if (maxValues(3) < borderedProblemArr(size + 1 - j)(i)){
                    borderedProblemArr(size+1)(i) += 1
                    maxValues(3) = borderedProblemArr(size + 1 - j)(i)
                }
            }
        }

        return borderedProblemArr
    }

    def eraseValues(borderedProblemArr: Array[Array[Int]], gameSize: Int, difficulty: Difficulties.Difficulty): Array[Array[Int]] = {
        var shuffledIndxes1D = Random.shuffle(List.range(0, gameSize*gameSize))
        var row: Int = 0
        var col: Int = 0
        var eraseValuesCnt = (gameSize * gameSize * Difficulties.getPercent(difficulty)).toInt
        for (i <- 0 until eraseValuesCnt){
            row = (shuffledIndxes1D(i) / gameSize) + 1 //bordered
            col = (shuffledIndxes1D(i) % gameSize) + 1 //bordered
            borderedProblemArr(row)(col) = -1
        }
        return borderedProblemArr
    }

    def createProblem(size: Int, difficulty: Difficulties.Difficulty): Problem = {
        var problemArr: Array[Array[Int]] = createProblemCore(size)
        var borderedProblemArr: Array[Array[Int]] = addBorderValues(problemArr, size)
        var solutionGrid: Grid = new Grid(size+2, borderedProblemArr)
        var erasedProblemArr: Array[Array[Int]] = eraseValues(borderedProblemArr, size, difficulty)
        var problemGrid: Grid = new Grid(size+2, erasedProblemArr)
        return new Problem(problemGrid, solutionGrid)
    }
}
