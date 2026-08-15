package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.components.BottomNavbar
import com.example.islandgame.components.FlagIcon
import com.example.islandgame.components.GoldenKeyPopup
import com.example.islandgame.components.LevelButton
import com.example.islandgame.components.TopNavBar
import com.example.islandgame.components.Zone
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun PlayScreen(
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
) {
    var showSettingsPopup by remember { mutableStateOf(false) }
    var showEditProfilePopup by remember { mutableStateOf(false) }
    var showKeysPopup by remember { mutableStateOf(false) }
    var showTaskPopup by remember { mutableStateOf(false) }
    var showPrelevelPopup by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(id = R.drawable.playscreen_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopNavBar(
                    modifier = Modifier,
                    onEditProfileClick = { showEditProfilePopup = true },
                    onKeysClick = { showKeysPopup = true }
                )
            },
            bottomBar = {
                BottomNavbar(
                    modifier = Modifier,
                    onLevelClick = onLevelClick,
                    onHomeClick = onHomeClick,
                    onSettingsClick = { showSettingsPopup = true },
                )
            },
        ) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        LevelButton(
                            text = "Level 1",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            onClick = { showPrelevelPopup = true},
                            modifier = Modifier.weight(1f)
                        )

                        Zone(
                            modifier = Modifier.weight(1f),
                            currentZone = 2,
                            totalZone = 10,
                            onZoneClick = { showTaskPopup = true }
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

        if (showKeysPopup) {
            GoldenKeyPopup(
                onDismiss = { showKeysPopup = false }
            )
        }

        if (showTaskPopup) {
            TasksPopup(
                onDismiss = { showTaskPopup = false }
            )
        }

        if (showPrelevelPopup) {
            PreLevelPopup(
                onDismiss = { showPrelevelPopup = false },
                stars = 0
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

