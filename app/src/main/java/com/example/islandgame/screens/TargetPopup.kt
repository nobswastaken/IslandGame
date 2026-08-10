package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun TargetPopup(
    onDismiss: () -> Unit,
){
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
            // Removed clickable from the root background box to prevent accidental background intercepts
        ) {
            // 1. Core Background Base (Wood Board)
            Image(
                painter = painterResource(id = R.drawable.group_4),
                contentDescription = null,
                modifier = Modifier.wrapContentSize(),
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

//                Image(
//                    painter = painterResource(id = R.drawable.cancel),
//                    contentDescription = "Close Button",
//                    modifier = Modifier
//                        .padding(end = 40.dp)
//                        .clickable { onDismiss() }
//                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TargetPopupPreview(){
    IslandGameTheme() {
        TargetPopup(onDismiss = {})
    }
}