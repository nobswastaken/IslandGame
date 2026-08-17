package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.sounds.SoundManager
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun GoldenKeyPopup(
    onDismiss: () -> Unit,
    soundManager: SoundManager,

    ) {

    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)) , contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.popup_body),
                contentDescription = "background",
                modifier = Modifier.wrapContentSize()
            )
            Box(
                modifier = Modifier.matchParentSize()
            ) {
                Row(
                    modifier = Modifier.align(Alignment.TopCenter).offset(y = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Golden Key",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
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
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.frame_1),
                        contentDescription = "Grid",
                        modifier = Modifier.size(100.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.group),
                        contentDescription = "Arrow",
                    )

                    Image(
                        painter = painterResource(id = R.drawable.key),
                        contentDescription = "Golden Key",
                    )
                }


                Text(
                    text = "Beat more levels to earn a Golden Key!",
                    modifier = Modifier.width(220.dp),
                    color = Color(0xFFE5A91A),
                    maxLines = 2,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )


                LevelButton(
                    text = "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    onClick = onDismiss,
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(40.dp)
                )
            }
        }
    }
}


//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun GoldenKeyprev(){
//    IslandGameTheme() {
//        GoldenKeyPopup(onDismiss = {})
//    }
//}