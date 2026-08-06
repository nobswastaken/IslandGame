package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.islandgame.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun HomeScreen(
    onPlayClick:() -> Unit
) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        Column(modifier = Modifier.padding(16.dp).offset(0.dp, -120.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.islandlogo),
                contentDescription = "Island Logo",
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )

            Image(
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = "Play Button",
                modifier = Modifier.offset(0.dp, 16.dp).clickable(
                    onClick = { onPlayClick() }
                )
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Image(
                painter = painterResource(id = R.drawable.treasure_shadow),
                contentDescription = "Logo",
                modifier = Modifier.offset(40.dp, 330.dp).height(100.dp).width(200.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.treasure_chest),
                contentDescription = "Logo",
                modifier = Modifier.offset(80.dp, 260.dp)
            )

        }
    }
}


