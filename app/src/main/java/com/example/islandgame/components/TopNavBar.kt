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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.screens.EditProfilePopup
import com.example.islandgame.screens.SettingsPopup
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun TopNavBar(
    modifier: Modifier = Modifier,
    showFlagsButton: Boolean = true,
    showKeysButton: Boolean = true,
    showCoinsButton: Boolean = true,
    onEditProfileClick: () -> Unit = {},
){
    var showEditProfilePopup by remember { mutableStateOf(false) }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.wood_topnavbg),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlagIcon(
                drawableId = R.drawable.flag_united_arab_emirates,
                contentDescription = "Flag 1",
                isSelected = false,
                onClick = onEditProfileClick,
                modifier = Modifier.alpha(if (showFlagsButton) 1f else 0f)
            )
            Coins(
                modifier = Modifier.alpha(if (showCoinsButton) 1f else 0f)
            )
            Keys(
                modifier = Modifier.alpha(if (showKeysButton) 1f else 0f)
            )
        }

        if (showEditProfilePopup) {
            EditProfilePopup(
                onDismiss = { showEditProfilePopup = false }
            )
        }

    }
}

@Preview
@Composable
fun TopNavBarPreview(){
    IslandGameTheme() {
        TopNavBar()
    }
}