package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun BottomNavbar(
    onLevelClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLevelsButton: Boolean = true,
){


    var showSettingsPopup by remember { mutableStateOf(false) }

        Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bottom_navbar),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings Button",
                modifier = Modifier.clickable {
                    showSettingsPopup = true
                    onSettingsClick()
                }.size(60.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.levels),
                contentDescription = "Levels Button",
                modifier = Modifier
                    .alpha(if (showLevelsButton) 1f else 0.0f)
                    .clickable(enabled = showLevelsButton) { onLevelClick() }
                    .size(60.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home Button",
                modifier = Modifier.clickable { onHomeClick() }.size(60.dp)
            )
        }
    }
}

@Preview
@Composable
fun BottomNavbarPreview(){
    IslandGameTheme() {
        BottomNavbar(
            onLevelClick = {},
            onHomeClick = {},
            onSettingsClick = {},
        )
    }
}