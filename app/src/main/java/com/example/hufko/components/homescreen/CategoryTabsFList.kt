package com.example.hufko.components.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.ui.theme.customColors

@Composable
fun CategoryTabsFList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColors.white)
            .padding(16.dp)
    ) {
        Text(
            text = "All Food Categories",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Browse all available food categories here",
            fontSize = 14.sp,
            color = MaterialTheme.customColors.black.copy(alpha = 0.7f)
        )

        // 🔜 You can add LazyColumn / Grid of categories here
    }
}
