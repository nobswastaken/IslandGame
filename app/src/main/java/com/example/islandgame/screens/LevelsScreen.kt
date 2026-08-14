package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.example.islandgame.components.ArrowButtonRight
import com.example.islandgame.components.ArrowButtonLeft
import com.example.islandgame.components.BottomNavbar
import com.example.islandgame.components.FlagIcon
import com.example.islandgame.components.LevelButton
import com.example.islandgame.components.LockedLevelCard
import com.example.islandgame.components.TopNavBar
import com.example.islandgame.components.UnlockedLevelCard

@Composable
fun LevelScreen(
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
) {
    var showSettingsPopup by remember { mutableStateOf(false) }
    var showEditProfilePopup by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopNavBar(
                modifier = Modifier,
                showFlagsButton = false,
                showCoinsButton = true,
                showKeysButton = false,
            )
        },
        bottomBar = {
            BottomNavbar(
                modifier = Modifier,
                showLevelsButton = false,
                onLevelClick = onLevelClick,
                onHomeClick = onHomeClick,
                onSettingsClick = { showSettingsPopup = true },
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.isle),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // MAIN CONTENT
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                // Row 1
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnlockedLevelCard(number = "1", stars = 3)
                    UnlockedLevelCard(number = "2", stars = 3)
                    UnlockedLevelCard(number = "3", stars = 3)
                    UnlockedLevelCard(number = "4", stars = 2)
                }
                // Row 2
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnlockedLevelCard(number = "5", stars = 2)
                    UnlockedLevelCard(number = "6", stars = 2)
                    UnlockedLevelCard(number = "7", stars = 1)
                    UnlockedLevelCard(number = "8", stars = 2)
                }
                // Row 3
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    UnlockedLevelCard(number = "9", stars = 1)
                    UnlockedLevelCard(number = "10", stars = 0)
                    LockedLevelCard()
                    LockedLevelCard()
                }
                // Row 4
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LockedLevelCard()
                    LockedLevelCard()
                    LockedLevelCard()
                    LockedLevelCard()
                }
                // Row 5
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LockedLevelCard()
                    LockedLevelCard()
                    LockedLevelCard()
                    LockedLevelCard()
                }


                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LevelButton(
                        text = "Back",
                        onClick = {},
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(100.dp)
                    )
                    ArrowButtonLeft(onClick = {})
                    ArrowButtonRight(onClick = {})
                }


                Spacer(modifier = Modifier.height(16.dp))
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



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LevelScreenCleanPreview() {
    LevelScreen(onHomeClick = {}, onLevelClick = {})
}
