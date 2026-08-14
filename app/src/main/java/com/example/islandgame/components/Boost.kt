package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun Boost(
    drawableId: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    count: Int,
    size: Dp,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    val brownTextColor = Color(0xFF954B25)


    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFE792), Color(0xFFFFC369))
    )


    val borderBrush = if (isSelected) {
        Brush.verticalGradient(colors = listOf(Color(0xFF40BE0F), Color(0xFF1D5B00))) // Rich Green border
    } else {
        Brush.verticalGradient(colors = listOf(Color(0xFFFFA800), Color(0xFFB55700))) // Rich Orange border
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .background(backgroundBrush, shape = RoundedCornerShape(8.dp))
                .border(width = 2.dp, brush = borderBrush, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = contentDescription,
                modifier = Modifier.size(44.dp) // Limits image size cleanly within borders
            )
        }

        //NUMBER BADGE
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 4.dp, y = 4.dp)
                .size(24.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 2.5.dp,
                    brush = borderBrush,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                color = brownTextColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Preview
@Composable
fun BoostPreview(){
    IslandGameTheme {
        Boost(
            drawableId = R.drawable.bomb,
            contentDescription = "a boost",
            isSelected = false,
            count = 3,
            onClick = {},
            size = 56.dp
        )
    }
}
