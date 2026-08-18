package com.example.islandgame.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.islandgame.data.Gems
import com.example.islandgame.data.Match
import kotlin.collections.List
import kotlin.math.abs

class GamePlay(
    val rows: Int = 8,
    val columns: Int = 8
) {

    var boardState by mutableStateOf(generateBoard())

    var selectedGem by mutableStateOf<Pair<Int, Int>?>(null)
    var movesLeft by mutableStateOf(10)

    var score by mutableStateOf(0)

    private fun generateBoard(): List<List<Gems>> {
        val board = MutableList(rows) {
            MutableList<Gems>(columns) { Gems.Empty }
        }

        for (row in 0 until rows) {
            for (col in 0 until columns) {

                val availableGems = listOf(
                    Gems.Pink_Gem,
                    Gems.Blue_Gem,
                    Gems.Green_Gem,
                    Gems.Yellow_Gem
                ).filter { gem ->

                    val createsHorizontalMatch =
                        col >= 2 &&
                                board[row][col - 1] == gem &&
                                board[row][col - 2] == gem

                    val createsVerticalMatch =
                        row >= 2 &&
                                board[row - 1][col] == gem &&
                                board[row - 2][col] == gem

                    !createsHorizontalMatch && !createsVerticalMatch
                }

                board[row][col] = availableGems.random()
            }
        }

        return board
    }
    fun findMatches(): Set<Match> {
        val matches = mutableSetOf<Match>()

        // Horizontal matches
        for (row in 0 until rows) {
            var col = 0

            while (col < columns) {
                val gem = boardState[row][col]

                if (gem == Gems.Empty || gem == Gems.Crystal_Ball) {
                    col++
                    continue
                }

                var endCol = col + 1

                while (
                    endCol < columns &&
                    boardState[row][endCol] == gem
                ) {
                    endCol++
                }

                val length = endCol - col

                if (length >= 3) {
                    val positions = (col until endCol)
                        .map { currentCol -> Pair(row, currentCol) }
                        .toSet()

                    matches.add(
                        Match(
                            gems = positions,
                            size = positions.size
                        )
                    )
                }

                col = endCol
            }
        }

        // Vertical matches
        for (col in 0 until columns) {
            var row = 0

            while (row < rows) {
                val gem = boardState[row][col]

                if (gem == Gems.Empty || gem == Gems.Crystal_Ball) {
                    row++
                    continue
                }

                var endRow = row + 1

                while (
                    endRow < rows &&
                    boardState[endRow][col] == gem
                ) {
                    endRow++
                }

                val length = endRow - row

                if (length >= 3) {
                    val positions = (row until endRow)
                        .map { currentRow -> Pair(currentRow, col) }
                        .toSet()

                    matches.add(
                        Match(
                            gems = positions,
                            size = positions.size
                        )
                    )
                }
                row = endRow
            }
        }
        return matches
    }

    private fun removeMatches(matches: Set<Match>) {

        val newBoard = boardState
            .map { it.toMutableList() }
            .toMutableList()

        for (match in matches) {
            for ((row, col) in match.gems) {
                newBoard[row][col] = Gems.Empty
            }
        }

        boardState = newBoard
    }

    fun selectGem(row: Int, col: Int) {
        if (movesLeft <= 0) {
            return
        }

        val firstSelect = selectedGem

        if (firstSelect == null) {
            selectedGem = Pair(row, col)
        } else {
            if (isAdjacent(firstSelect, Pair(row, col))) {
                movesLeft--
                swapGems(firstSelect, Pair(row, col))
            }
            selectedGem = null
        }
    }

    private fun isAdjacent(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Boolean {
        return (p1.first == p2.first && abs(p1.second - p2.second) == 1) ||
                (p1.second == p2.second && abs(p1.first - p2.first) == 1)
    }

    private fun swapGems(
        p1: Pair<Int, Int>,
        p2: Pair<Int, Int>
    ) {
        val originalBoard = boardState

        val newBoard = boardState
            .map { it.toMutableList() }
            .toMutableList()

        val temp = newBoard[p1.first][p1.second]

        newBoard[p1.first][p1.second] =
            newBoard[p2.first][p2.second]

        newBoard[p2.first][p2.second] =
            temp

        boardState = newBoard

        val matches = findMatches()

        if (matches.isEmpty()) {
            println("NO MATCH - REVERTING")
            boardState = originalBoard
        } else {
            println("MATCH FOUND")
            resolveMatches()
        }
    }

    private fun collapseBoard() {
        val newBoard = MutableList(rows) {
            MutableList(columns) { Gems.Empty }
        }

        for (col in 0 until columns) {

            var newRow = rows - 1

            for (row in rows - 1 downTo 0) {

                val gem = boardState[row][col]

                if (gem != Gems.Empty) {
                    newBoard[newRow][col] = gem
                    newRow--
                }
            }
        }

        boardState = newBoard
    }

    private fun fillEmptySpaces() {
        val newBoard = boardState
            .map { it.toMutableList() }
            .toMutableList()

        for (row in 0 until rows) {
            for (col in 0 until columns) {

                if (newBoard[row][col] == Gems.Empty) {
                    newBoard[row][col] = listOf(
                        Gems.Pink_Gem,
                        Gems.Blue_Gem,
                        Gems.Green_Gem,
                        Gems.Yellow_Gem
                    ).random()
                }
            }
        }

        boardState = newBoard
    }

    private fun resolveMatches() {
        var cascade = 1

        while (true) {

            val matches = findMatches()

            if (matches.isEmpty()) {
                println("NO MORE MATCHES")
                break
            }

            val totalGems = matches.sumOf { it.size }

            println("CASCADE $cascade: $totalGems gems matched")

            score += totalGems * 10

            removeMatches(matches)

            collapseBoard()

            fillEmptySpaces()

            cascade++
        }
    }
}
