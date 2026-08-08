package com.example.islandgame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun Coins(modifier: Modifier = Modifier){

    val fillGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF782B00), Color(0xFFC95B0C))
    )


    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .width(120.dp)
                .height(40.dp)
                .background(
                    color = Color(0xFF913E00),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    border = BorderStroke(width = 4.dp, color = Color(0xFFFFA800)),
                    shape = RoundedCornerShape(12.dp)
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){

            Text(
                text = "100",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 45.dp,end = 8.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.addbtn),
                contentDescription = "Add Coins Icon",
                modifier = Modifier.size(24.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = "Coins Icon",
            modifier = Modifier.size(40.dp).align(Alignment.CenterStart)
        )
    }
}

@Preview
@Composable
fun CoinsPreview(){
    IslandGameTheme() {
        Coins()
    }
}