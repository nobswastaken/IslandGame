package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun DoItButton(
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
        modifier = modifier.defaultMinSize(minWidth = 80.dp, minHeight = 50.dp).background(btngradient, shape = RoundedCornerShape(4.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(12.dp),
    ) {

        Row(){
            Text(
                text = "Do It",
                style = TextStyle(
                    brush = textGradient
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Image(
                painter = painterResource(id = R.drawable.key),
                contentDescription = "key",
                modifier = Modifier.width(20.dp).height(20.dp)
            )
            Text(
                text = "1",
                style = TextStyle(
                    brush = textGradient
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
@Preview
fun DoItButtonPreview(){
    IslandGameTheme() {
        DoItButton(onClick = {})
    }
}

