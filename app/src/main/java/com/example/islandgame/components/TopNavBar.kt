package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.data.flags
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun TopNavBar(
    modifier: Modifier = Modifier,
    showFlagsButton: Boolean = true,
    showKeysButton: Boolean = true,
    showCoinsButton: Boolean = true,
    onKeysClick: () -> Unit = {},
    currentCountryId: String = "Brazil",
    currentName: String = "Player 1",
    onEditProfileClick: () -> Unit = {}

){
    val activeFlag = flags.find { it.countryname.equals(currentCountryId, ignoreCase = true) }?.drawable
        ?: R.drawable.flag_united_arab_emirates

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.wood_topnavbg),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlagIcon(
                drawableId = activeFlag,
                contentDescription = "Flag",
                isSelected = false,
                onClick = onEditProfileClick,
                modifier = Modifier.alpha(if (showFlagsButton) 1f else 0f)
            )
            Coins(
                modifier = Modifier.alpha(if (showCoinsButton) 1f else 0f)
            )
            Keys(
                modifier = Modifier.alpha(if (showKeysButton) 1f else 0f),
                onKeysClick = onKeysClick,
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