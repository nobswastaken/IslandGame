package com.example.islandgame.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.data.Countryflags
import com.example.islandgame.R
import com.example.islandgame.components.FlagIcon
import com.example.islandgame.components.LevelButton
import com.example.islandgame.components.TextInput
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun EditProfilePopup(
    onDismiss:() -> Unit
) {
    var selectedFlagId by remember { mutableStateOf<Int?>(null) }


    val flags = remember {
        listOf(
            Countryflags(R.drawable.flag_of_turkmenistan, "Turkmekistan flag"),
            Countryflags(R.drawable.flag_brazil, "Brazil flag"),
            Countryflags(R.drawable.flag_canada, "Canada flag"),
            Countryflags(R.drawable.flag_mexico, "Mexico flag"),
            Countryflags(R.drawable.flag_of_germany_svg, "Germany flag"),
            Countryflags(R.drawable.flag_of_the_netherlands_svg, "Netherlands flag"),
            Countryflags(R.drawable.flag_united_arab_emirates, "UAE flag"),
            Countryflags(R.drawable.switzerland, "Switzerland flag"),
            Countryflags(R.drawable.uk_flag, "UK flag")

        )
    }


    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)),
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
                    modifier = Modifier.align(Alignment.TopCenter).offset(y = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Edit Profile",
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
                            .clickable { onDismiss() }
                    )
                }
            }

            // 2. Main Content Container
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth(0.60f),
//                        .weight(1f, fill = false),
                    horizontalAlignment = Alignment.CenterHorizontally, // Center everything down the paper axis
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    //Name input stack
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start // Keeps the small label left-aligned relative to the field
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.change_name),
                            contentDescription = "Change Name Label",
                            modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                        )
                        TextInput()
                    }

                    //Flag layout grid
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.change_profile_picture),
                            contentDescription = "Change Profile Picture Label",
                            modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                        )


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(2.dp, Color(0xFFE5A91A)),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                modifier = Modifier.fillMaxSize().padding(12.dp),
                                contentPadding = PaddingValues(4.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(flags) { flag ->
                                    FlagIcon(
                                        drawableId = flag.id,
                                        contentDescription = flag.contentDescription,
                                        isSelected = selectedFlagId == flag.id,
                                        onClick = { selectedFlagId = flag.id }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(1.dp))

                    // Accept Button
                    LevelButton(
                        text = "Accept",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        onClick = {},
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                    )
                }
            }
        }
    }






@Preview
@Composable
fun editpreview(){
    IslandGameTheme() {
        EditProfilePopup {  }
    }
}
