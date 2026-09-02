package com.example.islandgame.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.example.islandgame.components.Booster

class BoostStore {

    var bombCount by mutableIntStateOf(3)
        private set

    var potionCount by mutableIntStateOf(3)
        private set

    var diamondCount by mutableIntStateOf(3)
        private set

    fun getCount(booster: Booster): Int{
        return when (booster){
            Booster.BOMB -> bombCount
            Booster.POTION -> potionCount
            Booster.DIAMOND -> diamondCount
        }
    }

    fun consume(booster: Booster){
        when (booster){
            Booster.BOMB -> {
                if(bombCount > 0) bombCount--
            }
            Booster.POTION -> {
                if(potionCount > 0) potionCount--
            }
            Booster.DIAMOND -> {
                if(diamondCount > 0) diamondCount--
            }
        }
    }
}