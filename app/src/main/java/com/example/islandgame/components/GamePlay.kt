package com.example.islandgame.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.islandgame.data.Gems
import kotlin.collections.List
import kotlin.math.abs

class GamePlay(val rows: Int = 8, val columns: Int = 8){
    var boardState by mutableStateOf(List(rows) {List(columns) { Getgem() }})

    var selectedGem by mutableStateOf<Pair<Int, Int>?>(null)

    private fun Getgem(): Gems {
        return Gems.values().filter { it != Gems.Empty }.random()
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

    private fun swapGems(p1: Pair<Int, Int>, p2: Pair<Int, Int>) {
        val nextboard = boardState.map{it.toMutableList()}.toMutableList()
        val temp = nextboard[p1.first][p1.second]
        nextboard[p1.first][p1.second] = nextboard[p2.first][p2.second]
        nextboard[p2.first][p2.second] = temp
        boardState = nextboard
    }
}