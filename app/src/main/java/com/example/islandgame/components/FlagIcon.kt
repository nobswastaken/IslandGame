package com.example.islandgame.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun FlagIcon(
    drawableId: Int,             // Pass the R.drawable.your_flag id here
    contentDescription: String,  // Accessibility description
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    size: Dp = 56.dp,    // Easily adjust the overall width
    isSelected: Boolean
) {
    val bordercolor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF9CFF00) else Color (0xFFFFA800)
    )

    Card(
        modifier = modifier
            .width(size)
            .aspectRatio(3f / 2f)
            // Creates the subtle outer drop shadow
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, bordercolor),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = contentDescription,
            // Crops the flag cleanly into the rounded frame
            contentScale = ContentScale.Crop,
            modifier = Modifier
                // Keeps standard flag aspect ratio proportions
                .aspectRatio(3f / 2f)
        )
    }
}

@Preview
@Composable
fun flagpreview(){
    IslandGameTheme() {
        FlagIcon(
            drawableId = 1,
            contentDescription = "a flag",
            isSelected = true,
            onClick = {}
        )
    }
}