package com.example.islandgame.components

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit

@Composable
fun LevelButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    fontSize: TextUnit,
    fontWeight: FontWeight
) {
    val btngradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF65BA09), Color(0xFF8CE30B))
    )

    val textGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF4F8100), Color(0xFF304F00))
    )

    Button(
        onClick = onClick,
        modifier = modifier.background(btngradient, shape = RoundedCornerShape(4.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(12.dp),
    ) {
            Text(
                text = text,
                fontWeight = FontWeight.Black,
                fontSize = fontSize,
                style = TextStyle(
                    brush = textGradient
                )
            )
        }
    }

@Composable
@Preview
fun LevelButtonPreview(){
    IslandGameTheme() {
        LevelButton(
            text = "Level 1",
            onClick = {},
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}