package com.example.islandgame.popups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.components.Booster
import com.example.islandgame.components.DoItButton
import com.example.islandgame.components.LevelButton
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.ui.theme.IslandGameTheme


@Composable
fun BoosterRewardPopup(
    booster: Booster,
    onWatchAdClick: () -> Unit,
    onDismiss: () -> Unit,
    soundManager: SoundManager
) {
    val boosterName = when (booster) {
        Booster.BOMB -> "Bomb"
        Booster.POTION -> "Potion"
        Booster.DIAMOND -> "Diamond"
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.wrapContentSize()
        ) {

            // THE POPUP IMAGE — this stays as the base
            Image(
                painter = painterResource(id = R.drawable.group_4),
                contentDescription = "background",
                modifier = Modifier.wrapContentSize()
            )

            // EVERYTHING BELOW IS PLACED ON TOP OF THE IMAGE
            Box(
                modifier = Modifier.matchParentSize()
            ) {

                // TITLE — positioned ON the green ribbon
                Text(
                    text = "Out of $boosterName",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 20.dp)

                )

                // BODY — positioned in the middle of the paper
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterVertically
                    )
                ) {

                    Text(
                        text = "Watch a short ad to \nget 1 $boosterName.",
                        color = Color(0xFF954B25),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 30.dp)
                    )

                    LevelButton(
                        text = "Watch Ad",
                        onClick = {
                            onWatchAdClick()
                            soundManager.playSound()
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .width(120.dp)
                    )

                    Text(
                        text = "Maybe later",
                        color = Color(0xFF954B25),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onDismiss()
                            soundManager.playSound()
                        }
                    )
                }
            }
        }
}}


@Preview(showBackground = true)
@Composable
fun BoosterRewardPopupPreview() {
    BoosterRewardPopup(
        booster = Booster.BOMB,
        onWatchAdClick = {},
        onDismiss = {},
        soundManager = SoundManager(LocalContext.current)
    )
}

