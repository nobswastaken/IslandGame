package com.example.islandgame.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.islandgame.repository.LevelProgressRepo
import com.example.islandgame.screens.GameScreen
import com.example.islandgame.screens.HomeScreen
import com.example.islandgame.screens.LevelScreen
import com.example.islandgame.screens.PlayScreen
import com.example.islandgame.sounds.MusicManager
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.viewmodel.ProfileViewmodel
import com.example.islandgame.viewmodel.SettingsViewmodel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewmodel,
    settingsViewModel: SettingsViewmodel,
    levelProgressRepo: LevelProgressRepo,
    soundManager: SoundManager
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
                onThisLevelClick = { levelNumber ->
                    navController.navigate("games/$levelNumber")
                },
                profileViewModel = profileViewModel,
                settingsVM = settingsViewModel,
                soundManager = soundManager,
                levelProgressRepo = levelProgressRepo

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
                onThisLevelClick = { levelNumber ->
                    navController.navigate("games/$levelNumber")
                },
                profileViewModel = profileViewModel,
                settingsVM = settingsViewModel,
                soundManager = soundManager,
                levelProgressRepo = levelProgressRepo,
                onClick ={}
            )
        }

        composable(
            route = "games/{levelNumber}",
            arguments = listOf(
                navArgument("levelNumber"){
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val levelNumber = backStackEntry.arguments?.getInt("levelNumber") ?: 1
            GameScreen(
                levelNumber = levelNumber,
                onHomeClick = {
                    navController.navigate("play")
                },
                onLevelClick = {
                    navController.navigate("levels")
                },
                onNextLevelClick = {
                    navController.navigate("games/${levelNumber + 1}")
                },
                levelProgressRepo = levelProgressRepo,
                soundManager = soundManager
            )
        }
    }

}