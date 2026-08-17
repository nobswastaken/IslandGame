package com.example.islandgame.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.islandgame.data.Gems
import kotlin.collections.List
import kotlin.math.abs

class GamePlay(
    val rows: Int = 8,
    val columns: Int = 8
) {

    var boardState by mutableStateOf(generateBoard())

    var selectedGem by mutableStateOf<Pair<Int, Int>?>(null)

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
    fun findMatches(): Set<Pair<Int, Int>> {
        val matches = mutableSetOf<Pair<Int, Int>>()

        // Horizontal matches
        for (row in 0 until rows) {
            for (col in 0 until columns - 2) {

                val gem = boardState[row][col]

                if (
                    gem != Gems.Empty &&
                    gem != Gems.Crystal_Ball &&
                    gem == boardState[row][col + 1] &&
                    gem == boardState[row][col + 2]
                ) {
                    matches.add(Pair(row, col))
                    matches.add(Pair(row, col + 1))
                    matches.add(Pair(row, col + 2))
                }
            }
        }

        // Vertical matches
        for (row in 0 until rows - 2) {
            for (col in 0 until columns) {

                val gem = boardState[row][col]

                if (
                    gem != Gems.Empty &&
                    gem != Gems.Crystal_Ball &&
                    gem == boardState[row + 1][col] &&
                    gem == boardState[row + 2][col]
                ) {
                    matches.add(Pair(row, col))
                    matches.add(Pair(row + 1, col))
                    matches.add(Pair(row + 2, col))
                }
            }
        }
        return matches
    }

    private fun removeMatches(matches: Set<Pair<Int, Int>>) {
        val newBoard = boardState
            .map { it.toMutableList() }
            .toMutableList()

        for ((row, col) in matches) {
            newBoard[row][col] = Gems.Empty
        }

        boardState = newBoard
    }

    fun selectGem(row: Int, col: Int) {
        val firstSelect = selectedGem

        if (firstSelect == null) {
            selectedGem = Pair(row, col)
        } else {
            if (isAdjacent(firstSelect, Pair(row, col))) {
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

        println("MATCHES AFTER SWAP: $matches")

        if (matches.isEmpty()) {
            println("NO MATCH - REVERTING")
            boardState = originalBoard
        } else {
            println("MATCH FOUND - KEEPING SWAP")
            removeMatches(matches)
            collapseBoard()
            fillEmptySpaces()
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


}
