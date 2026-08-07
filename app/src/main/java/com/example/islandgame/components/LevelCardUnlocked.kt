package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.islandgame.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.ui.theme.IslandGameTheme


@Composable
fun UnlockedLevelCard(
    modifier: Modifier = Modifier,
    number: String,
    stars: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        // Star row: loops 1 to 3 to color them orange or dark grey
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.padding(bottom = 0.dp)
        ) {
            for (i in 1..3) {
                val starResource = if (i <= stars) R.drawable.property_1_full else R.drawable.property_1_free

                Image(
                    painter = painterResource(id = starResource),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Yellow main button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.unlockedlevel),
                contentDescription = "Level Button",
                modifier = Modifier.size(60.dp)
            )

            Text(
                text = number,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun UnlockedLevelCardPreview(){
    IslandGameTheme() {
        UnlockedLevelCard(
            modifier = Modifier,
            number = "1",
            stars = 3,
        )
    }
}