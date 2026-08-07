package com.example.islandgame.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.islandgame.R
import com.example.islandgame.components.DoItButton
import com.example.islandgame.components.Tasks
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun TasksPopup(
    onDismiss:() -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .clickable(enabled = true, onClick = {})
        ) {
            Image(
                painter = painterResource(id = R.drawable.popup_body),
                contentDescription = null,
                modifier = Modifier.wrapContentSize(),
            )

            Column(
                modifier = Modifier
                    .matchParentSize()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tasks),
                        contentDescription = "Tasks Title",
                        Modifier.padding(12.dp)
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
                    modifier = Modifier
                        .wrapContentSize()
                ) {
                    Tasks(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.lamp),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.lamp_text),
                                contentDescription = null,
                                modifier = Modifier.height(30.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            DoItButton(onClick = {})
                        }
                    }

                    Tasks(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.well),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.well_text),
                                contentDescription = null,
                                modifier = Modifier.height(30.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            DoItButton(onClick = {})
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun TasksPopupPreview(){
    IslandGameTheme() {
        TasksPopup(onDismiss = {})
    }
}