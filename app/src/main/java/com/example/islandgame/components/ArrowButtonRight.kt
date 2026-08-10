package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.ui.theme.IslandGameTheme
import com.example.islandgame.R

@Composable
fun ArrowButtonRight(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){
    val btngradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF65BA09), Color(0xFF8CE30B))
    )

    val textGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF304F00), Color(0xFF4F8100))
    )

    Button(
        onClick = onClick,
        modifier = modifier.background(btngradient, shape = RoundedCornerShape(4.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(12.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "Arrow",
            modifier = Modifier.width(20.dp).height(20.dp)
        )
    }
}

@Composable
@Preview
fun ArrowButtonRightPreview(){
    IslandGameTheme() {
        ArrowButtonRight(onClick = {})
    }
}