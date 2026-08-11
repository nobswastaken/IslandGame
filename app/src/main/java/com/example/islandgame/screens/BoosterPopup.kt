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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.components.Boost
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun BoosterPopup(
    levelNumber: Int = 1, // Added parameter to cleanly print your "Level X" ribbon text
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    stars: Int
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.popup_body),
                contentDescription = "background",
                modifier = Modifier.wrapContentSize()
            )


            Box(
                modifier = Modifier.matchParentSize()
            ) {
                Row(
                    modifier = Modifier.align(Alignment.TopCenter).offset(y = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Level $levelNumber",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Close Button",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { onDismiss() }
                    )
                }
//





                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 44.dp, bottom = 44.dp) // Safety margin clearance
                ) {
                    // STARS ROW
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 1..3) {
                            val starResource = if (i <= stars) R.drawable.property_1_full else R.drawable.property_1_free
                            Image(
                                painter = painterResource(id = starResource),
                                contentDescription = null,
                                modifier = Modifier.size(54.dp)
                            )
                        }
                    }


                    Text(
                        text = "Select boosts:",
                        color = Color(0xFF954B25),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    //Booster row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {

                        Boost(
                            drawableId = R.drawable.bomb,
                            contentDescription = "Bomb booster",
                            isSelected = true,
                            count = 3,
                            size = 56.dp,
                            onClick = { /* Handle selection toggle logic */ }
                        )

                        Boost(
                            drawableId = R.drawable.potion,
                            contentDescription = "Potion booster",
                            isSelected = false,
                            count = 3,
                            size = 56.dp,
                            onClick = { /* Handle selection toggle logic */ }
                        )

                        Boost(
                            drawableId = R.drawable.diamond,
                            contentDescription = "Diamond booster",
                            isSelected = false,
                            count = 3,
                            size = 56.dp,
                            onClick = { /* Handle selection toggle logic */ }
                        )
                    }
                }


                Image(
                    painter = painterResource(id = R.drawable.playbtn),
                    contentDescription = "Play Button",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = (-40).dp)
                        .clickable { /* Trigger level game scene loop start */ }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoosterPopupPreview(){
    IslandGameTheme {
        BoosterPopup(
            levelNumber = 1,
            onDismiss = {},
            modifier = Modifier,
            stars = 0 // Using 0 to check the default gray stars configuration layout
        )
    }
}
