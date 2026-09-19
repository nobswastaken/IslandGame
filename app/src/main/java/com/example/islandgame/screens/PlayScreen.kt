package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islandgame.R
import com.example.islandgame.ads.RewardedAdManager
import com.example.islandgame.components.Booster
import com.example.islandgame.components.BottomNavbar
import com.example.islandgame.popups.EditProfilePopup
import com.example.islandgame.popups.GoldenKeyPopup
import com.example.islandgame.components.LevelButton
import com.example.islandgame.popups.PreLevelPopup
import com.example.islandgame.popups.SettingsPopup
import com.example.islandgame.popups.TasksPopup
import com.example.islandgame.components.TopNavBar
import com.example.islandgame.components.Zone
import com.example.islandgame.data.BoostStore
import com.example.islandgame.data.levels
import com.example.islandgame.repository.KeyRepo
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.repository.TaskRepo
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.viewmodel.KeyViewmodel
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel

@Composable
fun PlayScreen(
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
    onThisLevelClick: (Int, Booster?) -> Unit,
    soundManager: SoundManager,
    profileViewModel: ProfileViewmodel,
    keyRepo: KeyRepo,
    taskRepo: TaskRepo,
    keyViewModel: KeyViewmodel,
    settingsVM: SettingsViewmodel,
    levelProgressRepo: LevelProgressRepo,
    boosterstore: BoostStore,
    rewardedAdManager: RewardedAdManager
) {

    var showSettingsPopup by remember { mutableStateOf(false) }
    var showEditProfilePopup by remember { mutableStateOf(false) }
    var showKeysPopup by remember { mutableStateOf(false) }
    var showTaskPopup by remember { mutableStateOf(false) }
    var showPrelevelPopup by remember { mutableStateOf(false) }
    var selectedBooster by remember { mutableStateOf<Booster?>(null) }

    var nextLevel by remember { mutableStateOf(1) }
    var selectedLevel by remember { mutableStateOf(1) }

    val username by profileViewModel.username.collectAsState()
    val countryId by profileViewModel.country.collectAsState()

    val currentKeyCount by keyViewModel.keyCounter.collectAsState()
    val tasks by taskRepo.tasksFlow.collectAsState(initial = emptyList())
    val completedTaskCount = tasks.count { it.completed }


    LaunchedEffect(Unit) {
        nextLevel = levelProgressRepo.getNextLevel()
    }

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopNavBar(
                    modifier = Modifier,
                    onKeysClick = { showKeysPopup = true },
                    currentCountryId = countryId,
                    currentName = username,
                    onEditProfileClick = { showEditProfilePopup = true },
                    keyCount = currentKeyCount
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

            Box(modifier = Modifier.fillMaxSize()) {


                Image(
                    painter = painterResource(id = R.drawable.playscreen_bg),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

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
                            text = "Level $nextLevel",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            onClick = { showPrelevelPopup = true},
                            modifier = Modifier.weight(1f)
                        )

                        Zone(
                            modifier = Modifier.weight(1f),
                            currentZone = 2,
                            totalZone = 10,
                            completedTasks = completedTaskCount,
                            totalTasks = 2,
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
                },
                settingsVM = settingsVM,
                soundManager = soundManager
            )
        }

        if (showEditProfilePopup) {

            EditProfilePopup(
                currentName = username,
                currentCountryid = countryId,
                soundManager = soundManager,
                onDismiss = { showEditProfilePopup = false },
                onAccept = { newName, selectedFlagId ->
                    profileViewModel.updateprofile(newName, selectedFlagId)
                    soundManager.playSound()

                }
            )
        }

        if (showKeysPopup) {
            GoldenKeyPopup(
                onDismiss = { showKeysPopup = false },
                soundManager = soundManager
            )
        }

        if (showTaskPopup) {
            TasksPopup(
                onDismiss = { showTaskPopup = false },
                soundManager = soundManager,
                keyRepo = keyRepo,
                taskRepo = taskRepo,
                keyCount = currentKeyCount
            )
        }

            if (showPrelevelPopup) {
                val levelConfig = levels.first{ it.levelNumber == nextLevel }
                PreLevelPopup(
                    levelConfig = levelConfig,
                    onPlayClick = {
                        showPrelevelPopup = false
                        onThisLevelClick(nextLevel,selectedBooster)
                    },
                    selectedBooster = selectedBooster,
                    onBoosterSelected = { booster ->
                        selectedBooster = booster
                    },
                    onDismiss = { showPrelevelPopup = false },
                    stars = 0,
                    soundManager = soundManager,
                    boosterstore = boosterstore,
                    rewardedAdManager = rewardedAdManager
                )
            }

    }
}
