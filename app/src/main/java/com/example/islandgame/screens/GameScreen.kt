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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.components.GameBottomNavbar
import com.example.islandgame.components.GamePlay
import com.example.islandgame.data.Gems
import com.example.islandgame.ui.theme.IslandGameTheme

import androidx.compose.material3.Scaffold // Ensure you have this import
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.islandgame.components.LevelCompletePopup
import com.example.islandgame.components.TopNavbarGameplay
import com.example.islandgame.data.levels
import com.example.islandgame.repository.LevelProgressRepo
import kotlin.text.toFloat

@Composable
fun GameScreen(
    levelNumber: Int,
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
    onNextLevelClick: () -> Unit,
    levelProgressRepo: LevelProgressRepo,
) {
    val levelConfig = levels.first { it.levelNumber == levelNumber }
    val engine = remember(levelNumber) { GamePlay(levelConfig) }
    var progressSaved by remember { mutableStateOf(false) }

    LaunchedEffect(engine.isLevelCompleted) {
        if (!progressSaved && engine.isLevelCompleted) {
            val stars = engine.getStars()
            levelProgressRepo.saveStars(
                levelNumber = levelNumber,
                stars = stars
            )
            progressSaved = true
        }
    }
    val progress = (
            engine.targetCount.toFloat()/engine.targetRequired.toFloat()
            ).coerceIn(0f,1f)

    val stars = when {
        progress >= 1f -> 3
        progress >= 0.50f -> 2
        progress >= 0.30f -> 1
        else -> 0
    }

    Scaffold(
        topBar = {
            TopNavbarGameplay(
                score = engine.score,
                targetGem = engine.targetGem,
                targetRequired = engine.targetRequired,
                targetCount = engine.targetCount,
                movesLeft = engine.movesLeft,
                starProgress = progress,
                stars = stars,
            )
        },


        bottomBar = {
            GameBottomNavbar(
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.game_bg),
                contentDescription = "Game Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )


            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color(0xFF1A252F), shape = RoundedCornerShape(16.dp))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
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
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (engine.isLevelCompleted) {
                LevelCompletePopup(
                    onHomeClick = { onHomeClick() },
                    onLevelClick = { onLevelClick() },
                    onNextLevelClick = { onNextLevelClick() },
                    score = engine.score,
                    stars = engine.getStars(),
                    targetRequired = engine.targetRequired
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GameScreenPreview(){
//    IslandGameTheme {
//        GameScreen()
//    }
//}