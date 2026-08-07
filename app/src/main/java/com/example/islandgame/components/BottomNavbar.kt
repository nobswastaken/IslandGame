package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.islandgame.R

@Composable
fun BottomNavbar(
    onLevelClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
){


    var showSettingsPopup by remember { mutableStateOf(false) }

        Box(modifier = modifier.offset(0.dp, 380.dp), contentAlignment = Alignment.Center) {
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
                }
            )
            Image(
                painter = painterResource(id = R.drawable.levels),
                contentDescription = "Levels Button",
                modifier = Modifier.clickable { onLevelClick() }
            )
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Home Button",
                modifier = Modifier.clickable { onHomeClick() }
            )
        }
    }
}