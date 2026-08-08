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

    // BACKGROUND IMAGE ROOT
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.playscreen_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // TOP NAVIGATION BAR
        TopNavBar(
            modifier = Modifier.offset(0.dp, (-380).dp),
            onEditProfileClick = { showEditProfilePopup = true },
        )

        // BOTTOM NAVIGATION BAR
        BottomNavbar(
            modifier = Modifier.offset(0.dp, 380.dp),
            onLevelClick = onLevelClick,
            onHomeClick = onHomeClick,
            onSettingsClick = { showSettingsPopup = true },
        )

        // LEVEL AND ZONE BUTTONS
        Row(
            modifier = Modifier.fillMaxWidth().offset(0.dp, 280.dp).padding(16.dp),
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

