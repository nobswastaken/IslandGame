package com.example.islandgame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun Tasks(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
){
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(Color(0xFFFFE792), shape = RoundedCornerShape(12.dp))
            .border(3.dp, Color(0xFFFFA800), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Preview
@Composable
fun TasksPreview(){
    IslandGameTheme() {
        Tasks(
            modifier = Modifier,
            content = {},
        )
    }
}