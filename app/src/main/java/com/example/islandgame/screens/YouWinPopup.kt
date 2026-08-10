package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.islandgame.components.CoinsDisplay
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun YouWinPopup(
    onDismiss: () -> Unit,
    modifier: Modifier,
    stars: Int
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
            // 1. Core Background Base (Wood Board)
            Image(
                painter = painterResource(id = R.drawable.popup_body),
                contentDescription = "background",
                modifier = Modifier.wrapContentSize()
            )

            Row(
                modifier = Modifier.offset(0.dp, -170.dp).padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings_text),
                    contentDescription = "Settings Button",
                )

                Image(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Close Button",
                    modifier = Modifier.clickable {
                        onDismiss()
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = modifier,
            ) {
                // Star row: loops 1 to 3 to be filled or empty
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                    modifier = Modifier.padding(bottom = 0.dp)
                ) {
                    for (i in 1..3) {
                        val starResource =
                            if (i <= stars) R.drawable.property_1_full else R.drawable.property_1_free

                        Image(
                            painter = painterResource(id = starResource),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.Start, // Aligns labels to match beautifully on the left edge
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Target:",
                            color = Color(0xFF954B25),
                            maxLines = 2,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "42",
                            style = TextStyle(
                                brush = textGradient
                            ),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Your Score:",
                            color = Color(0xFF954B25),
                            maxLines = 2,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "48552",
                            style = TextStyle(
                                brush = textGradient
                            ),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
                CoinsDisplay()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun YouWinPopupPreview(){
    IslandGameTheme() {
        YouWinPopup (onDismiss = {}, modifier = Modifier, stars = 3)
    }
}