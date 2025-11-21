package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun CashbackButton(
    amount: String = "0",
    modifier: Modifier = Modifier,
    showCoinIcon: Boolean = true,
    borderColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.white,
    textColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
//    borderColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.white,
//    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
//    textColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.white,
    coinIconSize: androidx.compose.ui.unit.Dp = 30.dp,
    coinOffset: androidx.compose.ui.unit.Dp = 5.dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        // Circular amount field
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = 1.25.dp,
                    color = borderColor,
                    shape = CircleShape
                )
                .background(backgroundColor, CircleShape)
                .padding(horizontal = 12.dp, vertical = 0.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "₹",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
                Spacer(modifier = Modifier.width(0.dp))
                Text(
                    text = amount,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            }
            // Add extra space for the overlapping image
            if (showCoinIcon) {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        // Coin image placed outside the bordered container but overlapping it
        if (showCoinIcon) {
            Image(
                painter = painterResource(id = R.drawable.ic_cashback),
                contentDescription = "Amount",
                modifier = Modifier
                    .size(coinIconSize)
                    .offset(x = coinOffset)
                    .zIndex(1f)
            )
        }
    }
}
