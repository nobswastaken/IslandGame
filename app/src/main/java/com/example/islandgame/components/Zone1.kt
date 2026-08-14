package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun Zone(
    modifier: Modifier = Modifier,
    currentZone: Int,
    totalZone: Int,

) {
    val progressPercentage = if (totalZone > 0) currentZone.toFloat() / totalZone.toFloat() else 0f

    val btngradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF65BA09), Color(0xFF8CE30B))
    )
    val progressColor = Brush.linearGradient(
        colors = listOf(Color(0xFFFFE792), Color(0xFFFFBC58))
    )
    val bordercolor = Brush.verticalGradient(colors = listOf(Color(0xFFFFA800), Color(0xFFB55700)))
    val textcolor  = Brush.verticalGradient(colors = listOf(Color(0xFF304F00), Color(0xFF4F8100))
    )

        Box(
            modifier = Modifier
                .width(160.dp)
                .height(50.dp)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(btngradient, shape = RoundedCornerShape(12.dp))
                    .padding(6.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "Zone $currentZone",
                    style = TextStyle(
                        brush = textcolor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                    )
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if(progressPercentage > 0f){
                       Box(
                            modifier = Modifier
                                .fillMaxWidth(progressPercentage)
                                .fillMaxHeight()
                                .background(brush = progressColor, shape = RoundedCornerShape(10.dp)),
                        )
                    }
                        Text(
                            text = "$currentZone/$totalZone",
                            style = TextStyle(
                                brush = textcolor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                       }
                    }

            Image(
                painter = painterResource(id = R.drawable.treasure_small),
                contentDescription = "Treasure",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = -4.dp, y = 4.dp)
            )
        }
    }


@Preview
@Composable
fun ZonePreview(){
    IslandGameTheme() {
        Zone(
            modifier = Modifier,
            currentZone = 2,
            totalZone = 10,
        )
    }
}