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
import com.example.islandgame.ads.InterstitialAdManager
import com.example.islandgame.ads.RewardedAdManager
import com.example.islandgame.data.AppNavGraph
import com.example.islandgame.data.BoostStore
import com.example.islandgame.databasestuff.KeysEntity
import com.example.islandgame.repository.KeyRepo
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.repository.ProfileRepo
import com.example.islandgame.repository.SettingsRepo
import com.example.islandgame.repository.TaskRepo
import com.example.islandgame.sounds.MusicManager
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.ui.theme.IslandGameTheme
import com.example.islandgame.viewmodel.KeyViewmodel
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel
import com.google.android.libraries.ads.mobile.sdk.MobileAds
import com.google.android.libraries.ads.mobile.sdk.initialization.InitializationConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        val interstitialAdManager = InterstitialAdManager()
        val rewardedAdManager = RewardedAdManager()

        backgroundScope.launch {
            MobileAds.initialize(
                this@MainActivity,
                InitializationConfig.Builder("ca-app-pub-3940256099942544~3347511713").build()
            ) {
                interstitialAdManager.startPreloading()
                rewardedAdManager.startPreloading()
            }
        }

        val profileRepository = ProfileRepo(applicationContext)
        val profileViewmodel = ProfileViewmodel(profileRepository)

        val levelProgressRepo = LevelProgressRepo(applicationContext)

        val keyRepo = KeyRepo(applicationContext)
        val keyViewModel = KeyViewmodel(keyRepo)

        val taskRepo = TaskRepo(applicationContext)

        val settingsRepository = SettingsRepo(applicationContext)
        val settingsViewModel = SettingsViewmodel(settingsRepository)

        val musicManager = MusicManager(applicationContext)
        val soundManager = SoundManager(applicationContext)


        setContent {
            IslandGameTheme {
                val settings by settingsViewModel.settingsFlow.collectAsState()
                val boosterstore = remember { BoostStore() }

                val keys by keyRepo.keysFlow.collectAsState(initial = KeysEntity())

                LaunchedEffect(Unit) {
                    keyRepo.initializeKeys()
                    taskRepo.initializeTasks()
                }

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
                    keyViewModel = keyViewModel,
                    soundManager = soundManager,
                    levelProgressRepo = LevelProgressRepo(applicationContext),
                    boosterstore = boosterstore,
                    keyRepo = keyRepo,
                    keyCount = keys.count,
                    taskRepo = taskRepo,
                    interstitialAdManager = interstitialAdManager,
                    musicManager = musicManager,
                    rewardedAdManager = rewardedAdManager
                )
            }
        }
    }
}
