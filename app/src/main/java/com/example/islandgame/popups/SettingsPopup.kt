package com.example.islandgame.popups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.viewmodel.SettingsViewmodel
import androidx.compose.runtime.getValue
import androidx.compose.ui.input.pointer.motionEventSpy
import com.example.islandgame.sounds.SoundManager

@Composable
fun SettingsPopup(
    onDismiss: () -> Unit,
    soundManager: SoundManager,
    onEditProfileClick: () -> Unit,
    settingsVM: SettingsViewmodel
) {
    val settings by settingsVM.settingsFlow.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                // 1. BASE BACKGROUND LAYER: Wood board and scroll
                Image(
                    painter = painterResource(id = R.drawable.popup_body),
                    contentDescription = "background",
                    modifier = Modifier.wrapContentSize()
                )
                Box(
                    modifier = Modifier.matchParentSize()
                ) {

                    Row(
                        modifier = Modifier.align(Alignment.TopCenter).offset(y = 28.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            24.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Settings",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "Close Button",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { onDismiss()
                                            soundManager.playSound()
                                        }
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            32.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                settingsVM.toggleSound(!settings.sound)
                            }
                        ) {
                            Image(
                                painter = painterResource(id = if(settings.sound) R.drawable.sound_icon
                                else R.drawable.no_sound_icon),
                                contentDescription = "Sound Button",
                                modifier = Modifier.size(70.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.sound),
                                contentDescription = "Sound text",
                                modifier = Modifier.size(70.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable {
                                settingsVM.toggleMusic(!settings.music)
                            }

                        ) {
                            Image(
                                painter = painterResource(id = if(settings.music) R.drawable.music_icon
                                else R.drawable.no_music_icon),
                                contentDescription = "Music Button",
                                modifier = Modifier.size(70.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.music),
                                contentDescription = "Music text",
                                modifier = Modifier.size(70.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(21.dp))
                    Image(
                        painter = painterResource(id = R.drawable.line),
                        contentDescription = "Divider",
                        modifier = Modifier.width(200.dp)
                    )
                    Spacer(modifier = Modifier.height(21.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Image(
                            painter = painterResource(id = R.drawable.edit_profile_icon),
                            contentDescription = "Edit Profile Button",
                            modifier = Modifier.clickable(
                                onClick = { onEditProfileClick() }
                            ).size(70.dp)

                        )
                        Image(
                            painter = painterResource(id = R.drawable.edit_profile),
                            contentDescription = "Edit Profile text",
                            modifier = Modifier.width(120.dp)
                        )
                    }
                }
            }
        }
}


