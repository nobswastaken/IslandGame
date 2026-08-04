package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun PlayScreen() {

//BACKGROUND IMAGE
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.playscreen_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
//TOP NAVIGATION BAR
        Box(modifier = Modifier.offset(0.dp, -370.dp), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.wood_topnavbg),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )


            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.flag),
                    contentDescription = "Flag Button",
                    modifier = Modifier.clickable(
                        onClick = { /* TODO */ }
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.coins),
                    contentDescription = "Coins Button",
                    modifier = Modifier.clickable(
                        onClick = { /* TODO */ }
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.keys),
                    contentDescription = "Key Button",
                    modifier = Modifier.clickable(
                        onClick = { /* TODO */ }
                    )
                )
            }
        }
//BOTTOM NAVIGATION BAR
        Box(modifier = Modifier.offset(0.dp, 380.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.bottom_navbar),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings Button",
                    modifier = Modifier.clickable(
                        onClick = { /* TODO */ }
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.levels),
                    contentDescription = "Levels Button",
                    modifier = Modifier.clickable(
                        onClick = { /* TODO */ }
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home Button",
                    modifier = Modifier.clickable(
                        onClick = { /* TODO */ }
                    )
                )
            }
        }

//LEVEL AND ZONE BUTTONS
        Row(
            modifier = Modifier.fillMaxWidth().offset(0.dp, 280.dp).padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = R.drawable.level_btn),
                contentDescription = "Level Button",
                modifier = Modifier.weight(1f).clickable(
                    onClick = { /* TODO */ }
                )
            )
            Image(
                painter = painterResource(id = R.drawable.default_zone),
                contentDescription = "Default Zone Button",
                modifier = Modifier.weight(1f).clickable(
                    onClick = { /* TODO */ }
                )
            )
        }
    }
}


@Preview
@Composable
fun PlayScreenPreview() {
    IslandGameTheme {
        PlayScreen()
    }
}