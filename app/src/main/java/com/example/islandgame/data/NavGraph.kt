package com.example.islandgame.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.islandgame.components.Booster
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.screens.GameScreen
import com.example.islandgame.screens.HomeScreen
import com.example.islandgame.screens.LevelScreen
import com.example.islandgame.screens.PlayScreen
import com.example.islandgame.sounds.MusicManager
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel
import kotlin.let

@Composable
fun AppNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewmodel,
    settingsViewModel: SettingsViewmodel,
    levelProgressRepo: LevelProgressRepo,
    soundManager: SoundManager,
    boosterstore: BoostStore
    ) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                onPlayClick = {
                    navController.navigate("play")
                }
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
                boosterstore = boosterstore

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
                boosterstore = boosterstore
            )
        }
    }

}