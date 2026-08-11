package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islandgame.R
import com.example.islandgame.ui.theme.IslandGameTheme

@Composable
fun TextInput() {
    // State to hold the user's typed text
    var textInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.size(width = 200.dp, height = 50.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = textInput,
            onValueChange = { textInput = it },
            modifier = Modifier.fillMaxSize().background(Color.White, shape = RoundedCornerShape(12.dp)).border(3.dp, Color(0xFFFFA800), shape = RoundedCornerShape(12.dp)),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            singleLine = true
        )
    }
}

@Preview
@Composable
fun InputPreview() {
    IslandGameTheme {
        TextInput()
    }
}