package com.example.islandgame.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.components.GameBottomNavbar
import com.example.islandgame.components.GamePlay
import com.example.islandgame.data.Gems
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.times
import com.example.islandgame.components.Boost
import com.example.islandgame.components.Booster
import com.example.islandgame.components.LevelCompletePopup
import com.example.islandgame.components.MainBooster
import com.example.islandgame.components.PreLevelPopup
import com.example.islandgame.components.TopNavbarGameplay
import com.example.islandgame.data.levels
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.sounds.SoundManager
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    levelNumber: Int,
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
    onNextLevelClick: (Booster?) -> Unit,
    levelProgressRepo: LevelProgressRepo,
    soundManager: SoundManager,
    startingBooster: Booster?,
) {
    val levelConfig = levels.first { it.levelNumber == levelNumber }
    val engine = remember(levelNumber, startingBooster) { GamePlay(levelConfig = levelConfig, startingBooster = startingBooster) }
    var progressSaved by remember { mutableStateOf(false) }
    var showPrelevelPopup by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var selectedBooster by remember { mutableStateOf<Booster?>(null) }
    var bombShockwave by remember { mutableFloatStateOf(0f) }

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

            val cellSize = 40.dp
            val cellSpacing = 4.dp
            val cellStep = cellSize + cellSpacing

            Box(
                modifier = Modifier
                    .size(
                        width = (cellSize * engine.columns) +
                                (cellSpacing * (engine.columns - 1)) +
                                16.dp,
                        height = (cellSize * engine.rows) +
                                (cellSpacing * (engine.rows - 1)) +
                                16.dp
                    )
                    .background(
                        Color(0xFF1A252F),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            ) {


                for (row in 0 until engine.rows) {
                    for (col in 0 until engine.columns) {

                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .offset(
                                    x = col * cellStep,
                                    y = row * cellStep
                                )
                                .background(
                                    Color(0xFF1A252F),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable(
                                    enabled = !engine.isAnimating
                                ) {
                                    scope.launch {
                                        engine.selectGem(row, col)
                                    }
                                }
                        )
                    }
                }

                for (row in 0 until engine.rows) {
                    for (col in 0 until engine.columns) {

                        val position = Pair(row, col)
                        val isBombPosition =
                            engine.bombPosition == position && !engine.usedBomb
                        val swap = engine.swappingGems


                        val isFirst = swap?.first == position
                        val isSecond = swap?.second == position

                        val gem = when {
                            isFirst -> engine.boardState[swap!!.first.first][swap.first.second]
                            isSecond -> engine.boardState[swap!!.second.first][swap.second.second]
                            else -> engine.boardState[row][col]
                        }

                        if (gem == Gems.Empty || isBombPosition ) {
                            continue
                        }

                        val targetRow = when {
                            isFirst -> swap!!.second.first
                            isSecond -> swap!!.first.first
                            else -> row
                        }

                        val targetCol = when {
                            isFirst -> swap!!.second.second
                            isSecond -> swap!!.first.second
                            else -> col
                        }

                        val animatedX by animateDpAsState(
                            targetValue = targetCol * cellStep,
                            animationSpec = tween(
                                durationMillis = 250
                            ),
                            label = "gemX"
                        )

                        val animatedY by animateDpAsState(
                            targetValue = targetRow * cellStep,
                            animationSpec = tween(
                                durationMillis = 250
                            ),
                            label = "gemY"
                        )

                        val isSelected =
                            engine.selectedGem == position

                        val isMatched =
                            position in engine.matchedGems

                        val matchScale by animateFloatAsState(
                            targetValue = if(isMatched) 0.2f else 1f,
                            animationSpec = keyframes {
                                durationMillis = 200
                                1f at 0
                                1.4f at 100
                                0.2f at 250
                            },
                            label = "matchScale"
                        )

                        val matchAlpha by animateFloatAsState(
                            targetValue = if(isMatched) 0f else 1f,
                            animationSpec = tween(
                                durationMillis = 250
                            ),
                            label = "matchAlpha"
                        )

                        Image(
                            painter = painterResource(
                                id = gem.drawableId
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(cellSize)
                                .offset(
                                    x = animatedX,
                                    y = animatedY
                                )
                                .padding(4.dp)
                                .alpha(matchAlpha)
                                .scale(matchScale)
                                .border(
                                    width = if (isSelected) 2.dp else 0.dp,
                                    color = if (isSelected)
                                        Color.White
                                    else
                                        Color.Transparent,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                    }
                }
                engine.bombPosition?.let { (bombRow, bombCol) ->

                    if (!engine.usedBomb) {

                        Image(
                            painter = painterResource(id = R.drawable.bomb),
                            contentDescription = "Bomb booster",
                            modifier = Modifier
                                .size(cellSize)
                                .offset(
                                    x = bombCol * cellStep,
                                    y = bombRow * cellStep
                                )
                                .clickable(
                                    enabled = !engine.isAnimating && !engine.usedBomb
                                ) {
                                    scope.launch {
                                        engine.useBombAtPosition()
                                    }
                                }
                            )
                        }
                    }
                    LaunchedEffect(engine.isBombExplode) {
                        if (engine.isBombExplode) {
                            bombShockwave = 0f

                            animate(
                                initialValue = 0f,
                                targetValue = 1f,
                                animationSpec = tween(
                                    durationMillis = 350,
                                    easing = FastOutSlowInEasing
                                )
                            ) { value, _ ->
                                bombShockwave = value
                            }
                            bombShockwave = 0f
                        }
                    }

                if(engine.isBombExplode){

                    engine.bombPosition?.let { (bombRow, bombCol) ->

                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            val centerX =
                                8.dp.toPx() +
                                        bombCol * cellStep.toPx() +
                                        cellSize.toPx() / 2

                            val centerY =
                                8.dp.toPx() +
                                        bombRow * cellStep.toPx() +
                                        cellSize.toPx() / 2

                            val maxRadius =
                                cellStep.toPx() * 2.2f

                            val radius =
                                bombShockwave * maxRadius

                            val alpha =
                                (1f - bombShockwave).coerceIn(0f,1f)

                            // Outer shockwave
                            drawCircle(
                                color = Color.White.copy(alpha = alpha),
                                radius = radius,
                                center = Offset(centerX, centerY),
                                style = Stroke(
                                    width = 5.dp.toPx()
                                )
                            )

                            // Inner glow
                            drawCircle(
                                color = Color.White.copy(
                                    alpha = alpha * 0.25f
                                ),
                                radius = radius * 0.65f,
                                center = Offset(centerX, centerY)
                            )
                        }
                    }
                }

            if (engine.isLevelCompleted) {
                LevelCompletePopup(
                    onHomeClick = { onHomeClick() },
                    onLevelClick = { onLevelClick() },
                    onNextLevelClick = { showPrelevelPopup = true },
                    score = engine.score,
                    stars = engine.getStars(),
                    targetRequired = engine.targetRequired
                )
            }

            if (showPrelevelPopup){
                val nextLevelNumber = levelNumber + 1
                val nextLevelConfig = levels.firstOrNull {
                    it.levelNumber == nextLevelNumber
                }

                if(nextLevelConfig != null){
                    PreLevelPopup(
                        levelConfig = nextLevelConfig,
                        stars = 0,
                        onDismiss = {showPrelevelPopup = false},
                        selectedBooster = selectedBooster,
                        onBoosterSelected = { booster ->
                            selectedBooster = booster
                        },
                        onPlayClick = {
                            showPrelevelPopup = false
                            onNextLevelClick(selectedBooster)
                        },
                        soundManager = soundManager
                    )
                }
            }
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