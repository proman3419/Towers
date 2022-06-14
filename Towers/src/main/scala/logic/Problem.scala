package logic

import logic.Grid

class Problem(
    val problem: Grid,
    val solution: Grid
) {
    def verifyPlayerSolution(playerSolution: Grid): Boolean = {
        return playerSolution equals solution
    }
}
