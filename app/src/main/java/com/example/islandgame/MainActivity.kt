package com.example.islandgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.islandgame.screens.HomeScreen
import com.example.islandgame.screens.LevelScreen
import com.example.islandgame.screens.PlayScreen
import com.example.islandgame.ui.theme.IslandGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IslandGameTheme {
                var currentScreen by remember { mutableStateOf("home") }

                // 2. Control which screen is visible based on that variable
                when (currentScreen) {
                    "home" -> HomeScreen(
                        onPlayClick = { currentScreen = "play" } // Changes screen
                    )
                    "play" -> { PlayScreen(
                        onHomeClick = { currentScreen = "home"},
                        onLevelClick = { currentScreen = "levels"}
                    )}
                    "levels" -> LevelScreen(
                        onHomeClick = { currentScreen = "home"},
                        onLevelClick = { currentScreen = "play"}
                    )
                }

            }
        }
    }
}
