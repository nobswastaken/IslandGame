package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun LevelCompletePopup(
    onLevelClick: () -> Unit,
    onHomeClick: () -> Unit,
    onNextLevelClick: () -> Unit,
    score: Int,
    targetRequired: Int,
    stars: Int,
) {

    val textGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFEE8801), Color(0xFFFFBD14))
    )

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            // BACKGROUND IMAGE
            Image(
                painter = painterResource(id = R.drawable.popup_body),
                contentDescription = "background",
                modifier = Modifier.wrapContentSize()
            )
            Row(
                modifier = Modifier.align(Alignment.TopCenter).offset(y = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Level Complete!",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) { index ->
                        Image(
                            painter = painterResource(
                                id = if (index < stars) {
                                    R.drawable.property_1_full
                                } else {
                                    R.drawable.property_1_free
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Target:",
                        color = Color(0xFF954B25),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = targetRequired.toString(),
                        style = TextStyle(
                            brush = textGradient
                        ),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                Text(
                    text = "Score:",
                    color = Color(0xFF954B25),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = score.toString(),
                    style = TextStyle(
                        brush = textGradient
                    ),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.levels_small),
                    contentDescription = "Levels Button",
                    modifier = Modifier.size(50.dp).clickable { onLevelClick() }
                )


                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home Button",
                        modifier = Modifier.size(50.dp).clickable { onHomeClick() }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.playbtn),
                        contentDescription = "Next Level Button",
                        modifier = Modifier.size(50.dp).clickable { onNextLevelClick() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LevelCompletePopupPreview(){
    IslandGameTheme() {
        LevelCompletePopup(
            onHomeClick = {},
            onLevelClick = {},
            onNextLevelClick = {},
            score = 100,
            stars = 3,
            targetRequired = 42,
        )
    }
}