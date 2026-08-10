package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.components.BottomNavbar
import com.example.islandgame.components.FlagIcon
import com.example.islandgame.components.LevelButton
import com.example.islandgame.components.TopNavBar
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun PlayScreen(
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
) {
    var showSettingsPopup by remember { mutableStateOf(false) }
    var showEditProfilePopup by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopNavBar(
                modifier = Modifier,
                onEditProfileClick = { showEditProfilePopup = true },
            )
        },
        bottomBar = {
            BottomNavbar(
                // Removed the manual .offset() so it naturally sits at the absolute bottom
                modifier = Modifier,
                onLevelClick = onLevelClick,
                onHomeClick = onHomeClick,
                onSettingsClick = { showSettingsPopup = true },
            )
        },
        // Optional: keeps your custom background color transparent if needed
    ) { innerPadding ->

        // 2. Wrap the middle content in a Box to keep your background image under everything
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), // Prevents bars from covering your level content
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.playscreen_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom // Elements stack starting from the bottom
            ) {
            // LEVEL AND ZONE BUTTONS
            Row(
                // Removed massive 280.dp offset so components scale correctly on all screen sizes
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LevelButton(
                    text = "Level 1",
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                LevelButton(
                    text = "Level 2",
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            }
        }
    }


        if (showSettingsPopup) {
            SettingsPopup(
                onDismiss = { showSettingsPopup = false },
                onEditProfileClick = {
                    showSettingsPopup = false
                    showEditProfilePopup = true
                }
            )
        }

        if (showEditProfilePopup) {
            EditProfilePopup(
                onDismiss = { showEditProfilePopup = false }
            )
        }
    }



@Preview
@Composable
fun PlayScreenPreview(){
    IslandGameTheme() {
        PlayScreen(
            onHomeClick = {},
            onLevelClick = {},
        )
    }
}

