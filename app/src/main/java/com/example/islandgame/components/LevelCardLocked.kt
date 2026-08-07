package com.example.islandgame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.islandgame.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LockedLevelCard(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        // Darkened stars row
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(bottom = 0.dp)
        ) {
            repeat(3) {
                Image(
                    painter = painterResource(id = R.drawable.property_1_free), // 👈 Uses your grey star graphic
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Dark red maroon button with lock icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lockedlevel),
                contentDescription = "Level Button",
                modifier = Modifier.size(60.dp)
            )
            // If the lock icon is also a drawable asset, swap this out for an Image() too!
            Image(
                painter = painterResource(id = R.drawable.vector__1_),
                contentDescription = "Locked",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
