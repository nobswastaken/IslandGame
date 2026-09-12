package com.example.islandgame.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.islandgame.components.Booster
import com.example.islandgame.repository.KeyRepo
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.repository.TaskRepo
import com.example.islandgame.screens.GameScreen
import com.example.islandgame.screens.HomeScreen
import com.example.islandgame.screens.LevelScreen
import com.example.islandgame.screens.PlayScreen
import com.example.islandgame.sounds.MusicManager
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.viewmodel.KeyViewmodel
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel
import kotlin.let

@Composable
fun AppNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewmodel,
    settingsViewModel: SettingsViewmodel,
    keyViewModel: KeyViewmodel,
    levelProgressRepo: LevelProgressRepo,
    soundManager: SoundManager,
    boosterstore: BoostStore,
    keyRepo: KeyRepo,
    taskRepo: TaskRepo,
    keyCount: Int
    ) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val musicManager = remember { MusicManager(context) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver {_, event ->
            when (event){
                Lifecycle.Event.ON_START -> {
                    musicManager.play()
                }
                Lifecycle.Event.ON_STOP -> {
                    musicManager.pause()
                }
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            musicManager.stop()
        }
    }





    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                onPlayClick = {
                    navController.navigate("play")
                },
            )
        }

        composable("play") {
            PlayScreen(
                onHomeClick = {
                    navController.navigate("home")
                },
                onLevelClick = {
                    navController.navigate("levels")
                },
                onThisLevelClick = { levelNumber, booster ->
                    navController.navigate("games/$levelNumber/${booster?.name?: "NONE"}")
                },
                profileViewModel = profileViewModel,
                settingsVM = settingsViewModel,
                soundManager = soundManager,
                levelProgressRepo = levelProgressRepo,
                boosterstore = boosterstore,
                keyViewModel = keyViewModel,
                taskRepo = taskRepo,
                keyRepo = keyRepo,

            )
        }

        composable("levels") {
            LevelScreen(
                onHomeClick = {
                    navController.navigate("play")
                },
                onLevelClick = {
                    navController.navigate("levels")
                },
                onThisLevelClick = { levelNumber, booster ->
                    navController.navigate("games/$levelNumber/${booster?.name?: "NONE"}"
                    )
                },
                profileViewModel = profileViewModel,
                settingsVM = settingsViewModel,
                soundManager = soundManager,
                levelProgressRepo = levelProgressRepo,
                boosterstore = boosterstore
            )
        }

        composable(
            route = "games/{levelNumber}/{booster}",
            arguments = listOf(
                navArgument("levelNumber"){
                    type = NavType.IntType
                },
                navArgument("booster"){
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val levelNumber = backStackEntry.arguments?.getInt("levelNumber") ?: 1
            val boosterName = backStackEntry.arguments?.getString("booster")

            val startingBooster =
                if(boosterName == "NONE"){
                    null
                }else{
                    boosterName?.let{
                        Booster.valueOf(it) }
                }
            GameScreen(
                levelNumber = levelNumber,
                startingBooster = startingBooster,
                onHomeClick = {
                    navController.navigate("play")
                },
                onLevelClick = {
                    navController.navigate("levels")
                },
                onNextLevelClick = { booster ->
                    val nextLevel = levelNumber + 1
                    navController.navigate("games/$nextLevel/${booster?.name?: "NONE"}")
                },
                levelProgressRepo = levelProgressRepo,
                soundManager = soundManager,
                boosterstore = boosterstore,
                keyRepo = keyRepo
            )
        }
    }

}