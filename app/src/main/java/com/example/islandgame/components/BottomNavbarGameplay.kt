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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun GameBottomNavbar(
    modifier: Modifier = Modifier,
){

    var showSettingsPopup by remember { mutableStateOf(false) }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.bottom_navbar),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {

            Boost(
                drawableId = R.drawable.hammer,
                size = 60.dp,
                contentDescription = "Hammer booster",
                isSelected = false,
                count = 4,
                onClick = { /* Handle selection toggle logic */ }
            )

            Boost(
                drawableId = R.drawable.arrowp,
                contentDescription = "Arrow booster",
                isSelected = false,
                count = 1,
                size = 60.dp,
                onClick = { /* Handle selection toggle logic */ }
            )

            Boost(
                drawableId = R.drawable.missile,
                contentDescription = "Missile booster",
                isSelected = false,
                count = 6,
                size = 60.dp,
                onClick = { /* Handle selection toggle logic */ }
            )
            Boost(
                drawableId = R.drawable.dice,
                contentDescription = "Dice booster",
                isSelected = false,
                count = 6,
                size = 60.dp,
                onClick = { /* Handle selection toggle logic */ }
            )
        }
    }
}

@Preview
@Composable
fun GameBottomNavbarPreview(){
    IslandGameTheme {
        GameBottomNavbar(
        )
    }
}