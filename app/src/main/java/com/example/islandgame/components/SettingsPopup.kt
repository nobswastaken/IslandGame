package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun SettingsPopup() {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.popup_body),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            modifier = Modifier.offset(0.dp, -160.dp).fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.settings_text),
                contentDescription = "Settings Button",
            )

            Image(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = "Close Button",
                modifier = Modifier.clickable(
                    onClick = { /* TODO */ }
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.sound_icon),
                        contentDescription = "Sound Button",
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sound),
                        contentDescription = "Sound text",
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.music_icon),
                        contentDescription = "Music Button",
                    )
                    Image(
                        painter = painterResource(id = R.drawable.music),
                        contentDescription = "Music text",
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.line),
                contentDescription = "Divider",
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.edit_profile_icon),
                    contentDescription = "Edit Profile Button",
                )
                Image(
                    painter = painterResource(id = R.drawable.edit_profile),
                    contentDescription = "Music text",
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsPopupPreview() {
    IslandGameTheme {
        SettingsPopup()
    }
}