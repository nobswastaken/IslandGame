package com.example.islandgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.islandgame.data.AppNavGraph
import com.example.islandgame.data.BoostStore
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.repository.ProfileRepo
import com.example.islandgame.repository.SettingsRepo
import com.example.islandgame.sounds.MusicManager
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.ui.theme.IslandGameTheme
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val profileRepository = ProfileRepo(applicationContext)
        val profileViewmodel = ProfileViewmodel(profileRepository)

        val levelProgressRepo = LevelProgressRepo(applicationContext)
        val settingsRepository = SettingsRepo(applicationContext)
        val settingsViewModel = SettingsViewmodel(settingsRepository)

        val musicManager = MusicManager(applicationContext)
        val soundManager = SoundManager(applicationContext)


        setContent {
            IslandGameTheme {
                val settings by settingsViewModel.settingsFlow.collectAsState()
                val boosterstore = remember { BoostStore() }


                LaunchedEffect(settings.music) {
                    if (settings.music) musicManager.play()
                    else musicManager.pause()
                }

                LaunchedEffect(settings.sound) {
                    soundManager.soundEnabled = settings.sound
                }

                val navController = rememberNavController()

                AppNavGraph(
                    navController = navController,
                    profileViewModel = profileViewmodel,
                    settingsViewModel = settingsViewModel,
                    soundManager = soundManager,
                    levelProgressRepo = LevelProgressRepo(applicationContext),
                    boosterstore = boosterstore
                )
            }
        }
    }
}
