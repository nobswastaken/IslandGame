package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun WorldProgress(
    modifier: Modifier = Modifier,
    currentProgress: Int ,
    totalProgress: Int,
) {
    val progressPercentage = if (totalProgress > 0) currentProgress.toFloat() / totalProgress.toFloat() else 0f

    val mainbackgroundColor = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFE792), Color(0xFFFFC369))
    )
    val progressColor = Brush.radialGradient(
        colors = listOf(Color(0xFFFFC62D), Color(0xFFFF9200)),
        center = Offset(0.2f, 0.5f),
        radius = 0.1f,
    )
    val bordercolor = Brush.verticalGradient(colors = listOf(Color(0xFFFFA800), Color(0xFFB55700)))
    val textcolor  = Brush.verticalGradient(colors = listOf(Color(0xFF304F00), Color(0xFF4F8100))
    )

    Box(modifier.wrapContentSize(), contentAlignment = Alignment.Center){

        Box(
            modifier
                .width(220.dp)
                .height(80.dp)
                .background(mainbackgroundColor, shape = RoundedCornerShape(12.dp))
                .border(width = 2.dp, brush = bordercolor, shape = RoundedCornerShape(12.dp))
                .padding(2.dp)
        ){
            Column(
                modifier.fillMaxSize().padding(start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
                ){

                Text(
                    text = "World Progress",
                    style = TextStyle(
                        brush = textcolor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Box(
                    modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center,
                ) {

                    LinearProgressIndicator(
                        progress = {progressPercentage},
                        modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        color = Color.Transparent,
                        trackColor = Color.White,
                        gapSize = 0.dp,
                        drawStopIndicator = { }
                    )

                    Box(modifier
                        .fillMaxWidth(progressPercentage)
                        .fillMaxHeight()
                        .padding(2.dp)
                        .align(Alignment.CenterStart)
                        .background(brush = progressColor, shape = RoundedCornerShape(10.dp))
                        .border(width = 2.dp, color = Color.Transparent, shape = RoundedCornerShape(10.dp))
                    )

                    Text(
                        text = "$currentProgress/$totalProgress",
                        style = TextStyle(
                            brush = textcolor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                        )
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.treasure_small),
            contentDescription = "Treasure",
            modifier.size(60.dp).align(Alignment.CenterEnd).offset(x = 0.dp, y = 12.dp)
        )
    }

}

@Preview
@Composable
fun WorldProgressPreview(){
    IslandGameTheme() {
        WorldProgress(
            modifier = Modifier,
            currentProgress = 2,
            totalProgress = 10,
        )
    }
}