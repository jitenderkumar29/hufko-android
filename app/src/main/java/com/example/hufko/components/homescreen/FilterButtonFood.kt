package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun FilterButtonFood(
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit = {},
    onSortClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Filter Button
        Row(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = true
                )
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.customColors.white)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.customColors.gray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp)
                .clickable { onFilterClick() },
            horizontalArrangement = Arrangement.SpaceBetween, // Space between text and icon
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filter",
                fontSize = 16.sp, // Slightly smaller for better balance
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.customColors.black
            )
            Image(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Filter",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Sort Button
        Row(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = true
                )
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.customColors.white)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.customColors.gray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp)
                .clickable { onSortClick() },
            horizontalArrangement = Arrangement.SpaceBetween, // Space between text and icon
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sort by",
                fontSize = 16.sp, // Slightly smaller for better balance
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.customColors.black
            )
            Icon(
                painter = painterResource(id = R.drawable.outline_keyboard_arrow_down_24),
                contentDescription = "Sort Dropdown",
                tint = MaterialTheme.customColors.black,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// Alternative version with enhanced shadow and border