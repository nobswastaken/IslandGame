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
    onClick: () -> Unit,
    isSelected: Boolean
) {
    val brownTextColor = Color(0xFF954B25)

    // Inner tile soft-yellow gradient background
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFE792), Color(0xFFFFC369))
    )

    // Smooth outer border frame gradient matching your selection states
    val borderBrush = if (isSelected) {
        Brush.verticalGradient(colors = listOf(Color(0xFF40BE0F), Color(0xFF1D5B00))) // Rich Green border
    } else {
        Brush.verticalGradient(colors = listOf(Color(0xFFFFA800), Color(0xFFB55700))) // Rich Orange border
    }

    Box(
        modifier = modifier
            .wrapContentSize()
            .clickable { onClick() }, // FIXED: Integrated click actions safely
        contentAlignment = Alignment.Center
    ) {
        // 1. MAIN ITEM TILE FRAME
        Box(
            modifier = Modifier
                .size(48.dp) // FIXED: Increased layout bounds slightly so the asset frame renders legibly
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

        // 2. CORNER NUMBER BADGE OVERLAY
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                // Small offset adjustments to make it look organically clipped past corner lines
                .offset(x = 4.dp, y = 4.dp)
                .size(24.dp) // FIXED: Scaled down relative to your 72.dp parent frame size
                .background(
                    color = Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 2.5.dp, // Thinned border trace slightly to match the asset scale width
                    brush = borderBrush,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                color = brownTextColor,
                fontSize = 13.sp, // FIXED: Balanced font scaling to fit circles neatly
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Preview
@Composable
fun BoostPreview(){
    IslandGameTheme {
        // Using an Android default placeholder drawable icon to prevent preview engine crash logs
        Boost(
            drawableId = android.R.drawable.ic_menu_gallery,
            contentDescription = "a boost",
            isSelected = false, // Toggle true / false right here to test your green vs orange frames!
            count = 3,
            onClick = {}
        )
    }
}
