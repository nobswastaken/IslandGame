package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MainBooster(
    drawableId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = contentDescription,
        modifier = modifier
            .size(56.dp)
            .clickable{ onClick()}
    )
}