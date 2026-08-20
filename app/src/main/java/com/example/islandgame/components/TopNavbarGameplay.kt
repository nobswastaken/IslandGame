package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.data.Gems
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun TopNavbarGameplay(
    score: Int,
    targetGem: Gems,
    targetCount: Int,
    targetRequired: Int,
    movesLeft: Int,
    stars: Int,
    starProgress: Float,
    modifier: Modifier = Modifier
) {
    val brownThemeColor = Color(0xFF954B25)
    val cardBackgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF1C1), Color(0xFFFCD37F))
    )
    val orangeBorderColor = Color(0xFFDF7B00)


    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        //BACKGROUND IMAGE
        Image(
            painter = painterResource(id = R.drawable.bottom_navbar),
            contentDescription = "Navbar Background Graphic",
            modifier = Modifier.fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 6.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                // SCORE CARD
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Score",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .background(brush = cardBackgroundBrush, shape = RoundedCornerShape(18.dp))
                            .border(width = 4.dp, color = orangeBorderColor, shape = RoundedCornerShape(18.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = score.toString(), color = brownThemeColor, fontSize = 20.sp, fontWeight = FontWeight.Black)
                    }
                }

                // TARGET CARD
                Column(
                    modifier = Modifier.weight(1.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Target",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .background(brush = cardBackgroundBrush, shape = RoundedCornerShape(18.dp))
                            .border(width = 4.dp, color = orangeBorderColor, shape = RoundedCornerShape(18.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(targetGem.drawableId),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = (-4).dp, y = (-4).dp)
                                .size(30.dp)
                                .background(color = Color.White, shape = CircleShape)
                                .border(width = 1.dp, color = orangeBorderColor, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${targetCount}/${targetRequired}",
                                color = brownThemeColor,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }

                // MOVES CARD
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Moves",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .background(brush = cardBackgroundBrush, shape = RoundedCornerShape(18.dp))
                            .border(width = 4.dp, color = orangeBorderColor, shape = RoundedCornerShape(18.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = movesLeft.toString(),
                            color = brownThemeColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            // PROGRESS BAR
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                        .border(width = 2.dp, color = Color(0xFFD6C8AF), shape = RoundedCornerShape(50.dp))
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(starProgress)
                        .height(12.dp)
                        .background(
                            brush = Brush.linearGradient(colors = listOf(Color(0xFFFFDD63), Color(0xFFFFB802))),
                            shape = RoundedCornerShape(50.dp)
                        )
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    for (i in 1..3) {
                        val starResource =
                            if (i <= stars) {
                                R.drawable.property_1_full
                            } else {
                                R.drawable.property_1_free
                            }

                        Image(
                            painter = painterResource(id = starResource),
                            contentDescription = null,
                            modifier = Modifier
                                .align(
                                    when (i) {
                                        1 -> Alignment.CenterStart
                                        2 -> Alignment.Center
                                        else -> Alignment.CenterEnd
                                    }
                                )
                                .size(34.dp)
                        )
                    }
                }
            }
        }
    }
}



//@Preview
//@Composable
//fun TopNavbarGameplayPreview(){
//    IslandGameTheme() {
//        TopNavbarGameplay(
//            score = 100,
//            targetCount = 5,
//            movesLeft = 10,
//            starProgress = 0.5f,
//            stars = 2,
//            targetRequired = 10,
//        )
//    }
//}