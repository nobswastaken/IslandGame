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
import androidx.compose.foundation.layout.width
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
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun GoldenKey(
    onDismiss: () -> Unit
){

    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)) , contentAlignment = Alignment.Center) {
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
                painter = painterResource(id = R.drawable.golden_key),
                contentDescription = "Golden Key",
            )

            Image(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = "Close Button",
                modifier = Modifier.clickable {
                    onDismiss()
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                Image(
                    painter = painterResource(id = R.drawable.frame_1),
                    contentDescription = "Image",
                    modifier = Modifier.width(100.dp).height(100.dp)
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
                modifier = Modifier
                    .width(220.dp)
                    .padding(all = 16.dp),
                color = Color(0xFFE5A91A),
                maxLines = 2, // Fixed capital 'L'
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )


            Image(
                painter = painterResource(id = R.drawable.continue_button),
                contentDescription = "Continue Button",
                modifier = Modifier.clickable { /* TODO: Save logic */ }
            )
        }
    }
}

@Composable
@Preview
fun GoldenKeyprev(){
    IslandGameTheme() {
        GoldenKey(onDismiss = {})
    }
}