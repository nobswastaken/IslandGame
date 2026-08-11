package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.components.GamePlay
import com.example.islandgame.data.Gems
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun GameScreen() {
    val engine = remember { GamePlay() }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFD0D9E2)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF1A252F), shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            for (row in 0 until engine.rows) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (col in 0 until engine.columns) {
                        val gem = engine.boardState[row][col]
                        val isSelected = engine.selectedGem == Pair(row, col)

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFF1A252F), shape = RoundedCornerShape(8.dp))
                                .border(
                                    width = if (isSelected) 2.dp else 0.dp,
                                    color = if (isSelected) Color(0xFFFFFFFF) else Color.Transparent
                                )
                                .clickable { engine.selectGem(row, col) },
                            contentAlignment = Alignment.Center
                        ) {
                            if (gem != Gems.Empty) {
                                Image(
                                    painter = painterResource(id = gem.drawableId),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize().padding(4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview(){
    IslandGameTheme {
        GameScreen()
    }
}