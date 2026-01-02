package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    coinIconSize: androidx.compose.ui.unit.Dp = 30.dp,
    coinOffset: androidx.compose.ui.unit.Dp = 5.dp
) {
    val digitCount = amount.length

    // Calculate dynamic font size (more aggressive reduction for more digits)
    val fontSize = when {
        digitCount <= 3 -> 14.sp
        digitCount == 4 -> 13.sp
        digitCount == 5 -> 12.sp
        digitCount == 6 -> 11.sp
        digitCount == 7 -> 10.sp
        else -> 9.sp
    }

    // Calculate width based on digit count (more width for more digits)
    val containerWidth = when {
        digitCount <= 2 -> 60.dp
        digitCount <= 3 -> 70.dp
        digitCount == 4 -> 72.dp
        digitCount == 5 -> 110.dp
        digitCount == 6 -> 120.dp
        digitCount == 7 -> 130.dp
        digitCount == 8 -> 140.dp
        else -> 150.dp
    }

    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        // Amount field with proper width
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(containerWidth)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.25.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .padding(
                    start = 8.dp,
                    end = 8.dp, // Space for coin
//                    end = if (showCoinIcon) 25.dp else 8.dp, // Space for coin
                    top = 8.dp,
                    bottom = 8.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹",
                        style = TextStyle(
                            fontSize = fontSize,
                            fontWeight = FontWeight.Medium,
                            color = textColor
                        )
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = amount,
                        style = TextStyle(
                            fontSize = fontSize,
                            fontWeight = FontWeight.Medium,
                            color = textColor
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                }

                // Flexible spacer to push content left
//                Spacer(modifier = Modifier.weight(10.sp))
            }
        }

        // Coin image
        if (showCoinIcon) {
            Image(
                painter = painterResource(id = R.drawable.ic_cashback),
                contentDescription = "Amount",
                modifier = Modifier
                    .size(coinIconSize)
                    .offset(x = coinOffset)
                    .zIndex(1f)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

// Optimized version with better digit handling
@Composable
fun CashbackButtonOptimized(
    amount: String = "0",
    modifier: Modifier = Modifier,
    showCoinIcon: Boolean = true,
    borderColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.white,
    textColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
    coinIconSize: androidx.compose.ui.unit.Dp = 30.dp
) {
    val digitCount = amount.length

    // More precise width calculation
    val baseWidthPerDigit = 8.dp // Width per digit including spacing
    val baseWidth = 40.dp // Width for ₹ symbol and padding
    val calculatedWidth = baseWidth + (baseWidthPerDigit * digitCount)
    val maxWidth = 150.dp
    val finalWidth = if (calculatedWidth > maxWidth) maxWidth else calculatedWidth

    // Font size calculation with smooth scaling
    val fontSize = when (digitCount) {
        1 -> 14.sp
        2 -> 14.sp
        3 -> 13.5.sp
        4 -> 13.sp
        5 -> 12.5.sp
        6 -> 12.sp
        7 -> 11.5.sp
        8 -> 11.sp
        9 -> 10.5.sp
        else -> 10.sp
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentSize()
    ) {
        // Text container
        Box(
            modifier = Modifier
                .width(finalWidth)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.25.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Amount text
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹",
                        fontSize = fontSize,
                        fontWeight = FontWeight.Medium,
                        color = textColor
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = amount,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Medium,
                        color = textColor,
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                }
            }
        }

        // Coin icon with proper spacing
        if (showCoinIcon) {
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_cashback),
                contentDescription = "Amount",
                modifier = Modifier
                    .size(coinIconSize)
            )
        }
    }
}

// Compact version with no overlap
@Composable
fun CashbackButtonCompact(
    amount: String = "0",
    modifier: Modifier = Modifier,
    showCoinIcon: Boolean = true,
    borderColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
    backgroundColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.white,
    textColor: androidx.compose.ui.graphics.Color = MaterialTheme.customColors.black,
    coinIconSize: androidx.compose.ui.unit.Dp = 24.dp
) {
    val digitCount = amount.length

    // Compact width calculation
    val containerWidth = when (digitCount) {
        1 -> 60.dp
        2 -> 70.dp
        3 -> 80.dp
        4 -> 90.dp
        5 -> 100.dp
        6 -> 110.dp
        7 -> 120.dp
        8 -> 130.dp
        else -> 140.dp
    }

    // Compact font sizes
    val fontSize = when (digitCount) {
        in 1..3 -> 12.sp
        in 4..5 -> 11.sp
        in 6..7 -> 10.sp
        else -> 9.sp
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Text container
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .width(containerWidth)
                .clip(RoundedCornerShape(6.dp))
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(6.dp)
                )
                .background(backgroundColor, RoundedCornerShape(6.dp))
                .padding(horizontal = 6.dp, vertical = 3.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "₹",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = amount,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Medium,
                    color = textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
            }
        }

        // Coin icon
        if (showCoinIcon) {
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_cashback),
                contentDescription = "Amount",
                modifier = Modifier
                    .size(coinIconSize)
            )
        }
    }
}

// Usage examples:
/*
// Test with different digit counts
CashbackButton(amount = "1")       // 1 digit
CashbackButton(amount = "12")      // 2 digits
CashbackButton(amount = "123")     // 3 digits
CashbackButton(amount = "1234")    // 4 digits
CashbackButton(amount = "12345")   // 5 digits
CashbackButton(amount = "123456")  // 6 digits
CashbackButton(amount = "1234567") // 7 digits

// Optimized version
CashbackButtonOptimized(amount = "12345678")

// Compact version for tight spaces
CashbackButtonCompact(amount = "123456789")
*/