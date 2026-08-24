package com.example.islandgame.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.islandgame.data.Gems
import com.example.islandgame.data.LevelConfig
import com.example.islandgame.data.Match
import kotlinx.coroutines.delay
import kotlin.collections.List
import kotlin.collections.emptySet
import kotlin.math.abs
import kotlin.random.Random

class GamePlay(
    val levelConfig: LevelConfig,
    val rows: Int = 8,
    val columns: Int = 8
) {

    var boardState by mutableStateOf(generateBoard())
    var selectedGem by mutableStateOf<Pair<Int, Int>?>(null)
    var swappingGems by mutableStateOf<Pair<Pair<Int, Int>, Pair<Int, Int>>?>(null)
        private set
    var movesLeft by mutableStateOf(levelConfig.moves)
    var score by mutableStateOf(0)
    val targetGem: Gems
        get() = levelConfig.targetGem
    var targetCount by mutableIntStateOf(0)
    val targetRequired: Int
        get() = levelConfig.targetRequired
    var isLevelCompleted by mutableStateOf(false)
        private set

    var isAnimating by mutableStateOf(false)
        private set

    var matchedGems by mutableStateOf<Set<Pair<Int, Int>>>(emptySet())

    fun clearSwappingGems() {
        swappingGems = null
    }

    private fun generateBoard(): List<List<Gems>> {
        val board = MutableList(rows) {
            MutableList<Gems>(columns) { Gems.Empty }
        }

        val normalGems = listOf(
            Gems.Pink_Gem,
            Gems.Blue_Gem,
            Gems.Green_Gem,
            Gems.Yellow_Gem
        )

        val isCrystalBallLevel =
            levelConfig.targetGem == Gems.Crystal_Ball

        for (row in 0 until rows) {
            for (col in 0 until columns) {

                val availableGems = normalGems.filter { gem ->

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

                board[row][col] =
                    if (isCrystalBallLevel && Random.nextFloat() < 0.10f) {
                        Gems.Crystal_Ball
                    } else {
                        availableGems.random()
                    }
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
                        .map { currentCol ->
                            Pair(row, currentCol)
                        }
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
                        .map { currentRow ->
                            Pair(currentRow, col)
                        }
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
        val positionsToRemove = mutableSetOf<Pair<Int, Int>>()

        for (match in matches) {
            positionsToRemove.addAll(match.gems)
        }


        if (targetGem == Gems.Crystal_Ball) {

            for (match in matches) {

                for ((row, col) in match.gems) {

                    val adjacentPositions =
                        getAdjacentPositions(row, col)

                    for (position in adjacentPositions) {

                        if (
                            boardState[position.first][position.second] ==
                            Gems.Crystal_Ball
                        ) {
                            positionsToRemove.add(position)
                        }
                    }
                }
            }
        }


        for ((row, col) in positionsToRemove) {
            newBoard[row][col] = Gems.Empty
        }

        boardState = newBoard
    }

    suspend fun selectGem(row: Int, col: Int) {
        if (movesLeft <= 0 || isLevelCompleted) {
            return
        }

        val firstSelect = selectedGem

        if (firstSelect == null) {
            selectedGem = Pair(row, col)
        } else {
            if (isAdjacent(firstSelect, Pair(row, col))) {
                movesLeft--

                val secondSelect = Pair(row, col)

                swappingGems = Pair(firstSelect, secondSelect)

                swapGems(firstSelect, secondSelect)
            }

            selectedGem = null
        }
    }

    private fun isAdjacent(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Boolean {
        return (p1.first == p2.first && abs(p1.second - p2.second) == 1) ||
                (p1.second == p2.second && abs(p1.first - p2.first) == 1)
    }

    private fun getAdjacentPositions(
        row: Int,
        col: Int
    ): List<Pair<Int, Int>>{
        return listOf(
            Pair(row - 1, col),
            Pair(row + 1, col),
            Pair(row, col - 1),
            Pair(row, col + 1)
        ).filter { (r,c) ->
            r in 0 until rows && c in 0 until columns
        }
    }
    private suspend fun swapGems(
        p1: Pair<Int, Int>,
        p2: Pair<Int, Int>
    ) {
        if (isAnimating) return

        isAnimating = true

        val originalBoard = boardState

        val newBoard = boardState
            .map { it.toMutableList() }
            .toMutableList()

        val temp = newBoard[p1.first][p1.second]

        newBoard[p1.first][p1.second] =
            newBoard[p2.first][p2.second]

        newBoard[p2.first][p2.second] =
            temp


        swappingGems = Pair(p1, p2)


        delay(250)


        boardState = newBoard


        val matches = findMatches()

        if (matches.isEmpty()) {

            println("NO MATCHES FOUND")

            swappingGems = Pair(p2, p1)

            delay(250)


            boardState = originalBoard

        } else {

            println("MATCHES FOUND")

            resolveMatches()
        }

        swappingGems = null
        isAnimating = false
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

        val normalGems = listOf(
            Gems.Pink_Gem,
            Gems.Blue_Gem,
            Gems.Green_Gem,
            Gems.Yellow_Gem
        )

        val isCrystalBallLevel =
            levelConfig.targetGem == Gems.Crystal_Ball

        for (row in 0 until rows) {
            for (col in 0 until columns) {

                if (newBoard[row][col] == Gems.Empty) {

                    newBoard[row][col] =
                        if (isCrystalBallLevel && Random.nextFloat() < 0.10f) {
                            Gems.Crystal_Ball
                        } else {
                            normalGems.random()
                        }
                }
            }
        }

        boardState = newBoard
    }

    private fun calculateScore(matchSize: Int): Int {
        return when (matchSize) {
            3 -> 30
            4 -> 50
            5 -> 75
            6 -> 100
            else -> 125
        }
    }

    fun getStars(): Int {
        return when{
            movesLeft >= 7 -> 3
            movesLeft >= 4 -> 2
            else -> 1

        }
    }

    fun resetLevel() {
        boardState = generateBoard()
        selectedGem = null
        swappingGems = null
        matchedGems = emptySet()
        movesLeft = levelConfig.moves
        score = 0
        targetCount = 0
        isLevelCompleted = false
    }
    private suspend fun resolveMatches() {
        var cascade = 1

        while (true) {

            val matches = findMatches()

            if (matches.isEmpty()) {
                println("NO MORE MATCHES")
                break
            }

            val totalGems = matches.sumOf { it.size }

            println("CASCADE $cascade: $totalGems gems matched")

            for (match in matches) {

                score += calculateScore(match.size)

                if (targetGem != Gems.Crystal_Ball) {

                    targetCount += match.gems.count { position ->
                        boardState[position.first][position.second] == targetGem
                    }

                } else {

                    val crystalBallsCollected = mutableSetOf<Pair<Int, Int>>()

                    for ((row, col) in match.gems) {

                        val adjacentPositions =
                            getAdjacentPositions(row, col)

                        for (position in adjacentPositions) {

                            val adjacentGem =
                                boardState[position.first][position.second]

                            if (adjacentGem == Gems.Crystal_Ball) {
                                crystalBallsCollected.add(position)
                            }
                        }
                    }

                    targetCount += crystalBallsCollected.size
                }
            }

            matchedGems = matches
                .flatMap { it.gems }
                .toSet()

            delay(300)
            removeMatches(matches)
            matchedGems = emptySet()

            collapseBoard()
            fillEmptySpaces()

            cascade++
        }
        if(targetCount >= targetRequired){
            isLevelCompleted = true
            println("LEVEL COMPLETED")
        }
    }
}
