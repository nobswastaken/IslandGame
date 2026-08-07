package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.components.BottomNavbar
import com.example.islandgame.components.LockedLevelCard
import com.example.islandgame.components.UnlockedLevelCard
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun LevelScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        // 1. Background Image Layer
        Image(
            painter = painterResource(id = R.drawable.playscreen_bg), // 👈 Replace with your ship image name
            contentDescription = "Game Background",
            contentScale = ContentScale.Crop, // 👈 Stretches image cleanly to fill the screen
            modifier = Modifier.fillMaxSize()
        )

        // 2. Scrollable Row Assembler
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp, horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()), // 👈 Allows scrolling down if you add rows
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Row 1 (Levels 1 to 4)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnlockedLevelCard(number = "1", stars = 3)
                    UnlockedLevelCard(number = "2", stars = 3)
                    UnlockedLevelCard(number = "3", stars = 3)
                    UnlockedLevelCard(number = "4", stars = 2)
                }

                // Row 2 (Levels 5 to 8)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnlockedLevelCard(number = "5", stars = 2)
                    UnlockedLevelCard(number = "6", stars = 2)
                    UnlockedLevelCard(number = "7", stars = 1)
                    UnlockedLevelCard(number = "8", stars = 2)
                }

                // Row 3 (Levels 9 to 12)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnlockedLevelCard(number = "9", stars = 1)
                    UnlockedLevelCard(number = "10", stars = 0)
                    LockedLevelCard()
                    LockedLevelCard()
                }

                // Row 4 (Levels 13 to 16)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LockedLevelCard()
                    LockedLevelCard()
                    LockedLevelCard()
                    LockedLevelCard()
                }
            }
        }
        BottomNavbar(
            onLevelClick = {},
            onHomeClick = {},
            onSettingsClick = {}, modifier = Modifier.offset(0.dp, 360.dp))
    }
}

@Preview
@Composable
fun LevelScreenPreview(){
    IslandGameTheme() {
        LevelScreen()
    }
}