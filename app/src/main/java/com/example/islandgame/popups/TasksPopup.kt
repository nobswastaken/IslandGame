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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.components.DoItButton
import com.example.islandgame.components.TaskDone
import com.example.islandgame.components.Tasks
import com.example.islandgame.components.WorldProgress
import com.example.islandgame.databasestuff.KeysEntity
import com.example.islandgame.repository.KeyRepo
import com.example.islandgame.repository.TaskRepo
import com.example.islandgame.sounds.SoundManager
import kotlinx.coroutines.launch

@Composable
fun TasksPopup(
    onDismiss: () -> Unit,
    soundManager: SoundManager,
    keyRepo: KeyRepo,
    taskRepo: TaskRepo,
    keyCount: Int,
    modifier: Modifier = Modifier,
) {

    val scope = rememberCoroutineScope()
    val keys by keyRepo.keysFlow.collectAsState(initial = KeysEntity())

    val tasks by taskRepo.tasksFlow.collectAsState(initial = emptyList())
    val lampCompleted = tasks.firstOrNull { it.id == 1 }?.completed == true
    val wellCompleted = tasks.firstOrNull { it.id == 2 }?.completed == true

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.wrapContentSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.popup_body),
                contentDescription = null,
                modifier = Modifier.wrapContentSize(),
            )

            Box(
                modifier = Modifier.matchParentSize()
            ) {
                Row(
                    modifier = Modifier.align(Alignment.TopCenter).offset(y = 28.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tasks",
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


                // Main content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(top = 70.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {


                    WorldProgress(
                        currentProgress = 2,
                        totalProgress = 10,
                        modifier = Modifier
                    )



                    Tasks(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
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
                            if (lampCompleted) {
                                TaskDone()
                            } else {
                                DoItButton(
                                    enabled = keyCount > 0,
                                    onClick = {
                                        if (keyCount > 0) {
                                            scope.launch {
                                                keyRepo.spendKey()
                                                taskRepo.completeTask(1)
                                                soundManager.playSound()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }


                    Tasks(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
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
                            if (wellCompleted) {
                                TaskDone()
                            } else {
                                DoItButton(
                                    enabled = keyCount > 0,
                                    onClick = {
                                        if (keyCount > 0) {
                                            scope.launch {
                                                keyRepo.spendKey()
                                                taskRepo.completeTask(2)
                                                soundManager.playSound()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



//@Preview
//@Composable
//fun TasksPopupPreview(){
//    IslandGameTheme() {
//        TasksPopup(onDismiss = {})
//    }
//}