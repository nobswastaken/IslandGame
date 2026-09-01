package com.example.islandgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islandgame.R
import com.example.islandgame.components.ArrowButtonRight
import com.example.islandgame.components.ArrowButtonLeft
import com.example.islandgame.components.Booster
import com.example.islandgame.components.BottomNavbar
import com.example.islandgame.components.EditProfilePopup
import com.example.islandgame.components.LevelButton
import com.example.islandgame.components.LockedLevelCard
import com.example.islandgame.components.PreLevelPopup
import com.example.islandgame.components.SettingsPopup
import com.example.islandgame.components.TopNavBar
import com.example.islandgame.components.UnlockedLevelCard
import com.example.islandgame.data.levels
import com.example.islandgame.databasestuff.LevelProgressEntity
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel

@Composable
fun LevelScreen(
    onHomeClick: () -> Unit,
    onLevelClick: () -> Unit,
    onThisLevelClick: (Int, Booster?) -> Unit,
    levelProgressRepo: LevelProgressRepo,
    profileViewModel: ProfileViewmodel = viewModel(),
    settingsVM: SettingsViewmodel = viewModel(),
    soundManager: SoundManager
) {
    var showSettingsPopup by remember { mutableStateOf(false) }
    var showEditProfilePopup by remember { mutableStateOf(false) }
    var showPrelevelPopup by remember { mutableStateOf(false) }
    var selectedBooster by remember { mutableStateOf<Booster?>(null) }
    var selectedLevel by remember { mutableStateOf(1) }

    val username by profileViewModel.username.collectAsState()
    val countryId by profileViewModel.country.collectAsState()

    var levelStars by remember { mutableStateOf<Map<Int, Int>>(emptyMap()) }
    var levelProgress by remember { mutableStateOf<List<LevelProgressEntity>>(emptyList()) }

    var currentPage by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        levelStars = levelProgressRepo
            .getAllLevelProgress()
            .associate { it.levelNumber to it.stars }
        levelProgress = levelProgressRepo
            .getAllLevelProgress()
    }
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
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                val levelsperPage = 12

                val currentPageLevels = levels
                    .drop((currentPage - 1) * levelsperPage)
                    .take(levelsperPage)
                for (levelRow in currentPageLevels.chunked(4)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        for (level in levelRow) {
                            val savedProgress =
                                levelProgress.firstOrNull { it.levelNumber == level.levelNumber }
                            val stars = savedProgress?.stars ?: 0
                            val isUnlocked =
                                level.levelNumber == 1 ||
                                        levelProgress.any {
                                            it.levelNumber == level.levelNumber - 1 &&
                                                    it.stars > 0
                                        }
                            if (isUnlocked) {
                                UnlockedLevelCard(
                                    number = level.levelNumber.toString(),
                                    stars = stars,
                                    onThisLevelClick = { selectedLevel = level.levelNumber
                                                       selectedBooster = null
                                                       showPrelevelPopup = true}
                                )
                            } else {
                                LockedLevelCard()
                            }
                        }
                    }
                }



                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        LevelButton(
                            text = "Back",
                            onClick = { onHomeClick()},
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp)
                        )
                        ArrowButtonLeft(onClick = {
                            if(currentPage > 1){
                                currentPage--
                            }
                        }
                    )
                        ArrowButtonRight(onClick = {
                            if(currentPage < 2){
                                currentPage++
                            }
                        }
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (showPrelevelPopup) {
        val levelConfig = levels.first{ it.levelNumber == selectedLevel }
        PreLevelPopup(
            levelConfig = levelConfig,
            onPlayClick = {
                showPrelevelPopup = false
                onThisLevelClick(selectedLevel,selectedBooster)
            },
            selectedBooster = selectedBooster,
            onBoosterSelected = { booster ->
                selectedBooster = booster
            },
            onDismiss = { showPrelevelPopup = false },
            stars = 0,
            soundManager = soundManager
        )
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
}



