package com.example.hufko.components.Account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R

data class AccountItem(
    val title: String,
    val imageRes: Int,
    val description: String = ""
)

data class AccountInfoConfig(
    val itemsPerRow: Int = 3,
    val showSingleItemCentered: Boolean = true,
    val cardElevation: Dp = 2.dp,
    val cornerRadius: Dp = 12.dp,
    val itemSpacing: Dp = 12.dp,
    val rowSpacing: Dp = 16.dp,
    val imageSize: Dp = 48.dp,
    val textSpacing: Dp = 12.dp,
    val textColor: Color = Color(0xFF333333),
    val backgroundColor: Color = Color.White,
    val cardColor: Color = Color.White,
    val borderColor: Color? = null,
    val borderWidth: Dp = 1.dp,
    val useCards: Boolean = true,
    val padding: Dp = 16.dp,
    val fontSize: Int = 14,
    val fontWeight: FontWeight = FontWeight.Medium,
    val lineHeight: Int = 16
)

@Composable
fun AccountInfo(
    items: List<AccountItem>,
    config: AccountInfoConfig = AccountInfoConfig(),
    headerTitle: String? = null,
    onItemClick: ((AccountItem) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(config.backgroundColor)
    ) {
        // Optional Header
        headerTitle?.let {
            Text(
                text = it,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = config.padding, top = config.padding, bottom = 8.dp)
            )
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(config.padding)
        ) {
            val rows = items.chunked(config.itemsPerRow)

            rows.forEachIndexed { rowIndex, rowItems ->
                if (rowIndex > 0) {
                    Spacer(modifier = Modifier.height(config.rowSpacing))
                }

                // For the last row with single item and showSingleItemCentered is true
                val shouldCenter = config.showSingleItemCentered &&
                        rowItems.size == 1 &&
                        rowIndex == rows.size - 1

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (shouldCenter) {
                        Arrangement.Center
                    } else {
                        Arrangement.SpaceEvenly
                    }
                ) {
                    rowItems.forEachIndexed { index, item ->
                        if (index > 0) {
                            Spacer(modifier = Modifier.width(config.itemSpacing))
                        }

                        AccountCategoryItem(
                            item = item,
                            config = config,
                            onItemClick = onItemClick,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Add empty weight for remaining slots if needed
                    if (!shouldCenter && rowItems.size < config.itemsPerRow) {
                        repeat(config.itemsPerRow - rowItems.size) {
                            Spacer(modifier = Modifier.width(config.itemSpacing))
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AccountCategoryItem(
    item: AccountItem,
    config: AccountInfoConfig,
    onItemClick: ((AccountItem) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val itemContent: @Composable () -> Unit = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.description.ifEmpty { item.title },
                modifier = Modifier.size(config.imageSize)
            )

            Spacer(modifier = Modifier.height(config.textSpacing))

            Text(
                text = item.title,
                textAlign = TextAlign.Center,
                fontSize = config.fontSize.sp,
                fontWeight = config.fontWeight,
                lineHeight = config.lineHeight.sp,
                color = config.textColor
            )
        }
    }

    val clickModifier = if (onItemClick != null) {
        Modifier.clickable { onItemClick(item) }
    } else {
        Modifier
    }

    if (config.useCards) {
        Card(
            modifier = modifier
                .padding(4.dp)
                .then(clickModifier),
            elevation = CardDefaults.cardElevation(defaultElevation = config.cardElevation),
            shape = RoundedCornerShape(config.cornerRadius),
            colors = CardDefaults.cardColors(containerColor = config.cardColor),
            onClick = onItemClick?.let { { it(item) } } ?: {}
        ) {
            itemContent()
        }
    } else {
        Box(
            modifier = modifier
                .padding(4.dp)
                .background(
                    color = config.cardColor,
                    shape = RoundedCornerShape(config.cornerRadius)
                )
                .border(
                    width = config.borderWidth,
                    color = config.borderColor ?: Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(config.cornerRadius)
                )
                .then(clickModifier)
        ) {
            itemContent()
        }
    }
}

// Convenience functions for specific use cases
@Composable
fun AccountInfoSwiggyStyle(
    items: List<AccountItem>,
    onItemClick: ((AccountItem) -> Unit)? = null
) {
    AccountInfo(
        items = items,
        config = AccountInfoConfig(
            itemsPerRow = 3,
            showSingleItemCentered = true,
            cardElevation = 2.dp,
            cornerRadius = 12.dp,
            itemSpacing = 12.dp,
            rowSpacing = 16.dp,
            imageSize = 48.dp,
            textSpacing = 12.dp,
            textColor = Color(0xFF333333),
            useCards = true
        ),
        onItemClick = onItemClick
    )
}

@Composable
fun AccountInfoBorderStyle(
    items: List<AccountItem>,
    onItemClick: ((AccountItem) -> Unit)? = null
) {
    AccountInfo(
        items = items,
        config = AccountInfoConfig(
            itemsPerRow = 3,
            showSingleItemCentered = true,
            cornerRadius = 8.dp,
            itemSpacing = 8.dp,
            rowSpacing = 12.dp,
            imageSize = 40.dp,
            textSpacing = 8.dp,
            textColor = Color(0xFF424242),
            useCards = false,
            borderColor = Color(0xFFE0E0E0),
            borderWidth = 0.5.dp,
            fontSize = 13,
            fontWeight = FontWeight.SemiBold
        ),
        onItemClick = onItemClick
    )
}

// Compact version
@Composable
fun AccountInfoCompact(
    items: List<AccountItem>,
    onItemClick: ((AccountItem) -> Unit)? = null
) {
    AccountInfo(
        items = items,
        config = AccountInfoConfig(
            itemsPerRow = 4,
            showSingleItemCentered = false,
            cardElevation = 0.dp,
            cornerRadius = 0.dp,
            itemSpacing = 4.dp,
            rowSpacing = 12.dp,
            imageSize = 36.dp,
            textSpacing = 6.dp,
            textColor = Color(0xFF666666),
            useCards = false,
            padding = 8.dp,
            fontSize = 12,
            fontWeight = FontWeight.Normal
        ),
        onItemClick = onItemClick
    )
}

// Usage Examples
@Composable
fun ExampleUsage() {
    val accountItems = listOf(
        AccountItem("Saved\nAddress", R.drawable.ic_address, "Manage your saved addresses"),
        AccountItem("Payment\nModes", R.drawable.ic_payment, "View payment methods"),
        AccountItem("My\nRefunds", R.drawable.ic_refunds, "Check refund status"),
        AccountItem("Swiggy\nMoney", R.drawable.ic_money, "Swiggy wallet balance")
    )

    // Example 1: Default Swiggy style
    AccountInfoSwiggyStyle(
        items = accountItems,
        onItemClick = { item ->
            // Handle item click
            println("Clicked on ${item.title}")
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Example 2: With header
    AccountInfo(
        items = accountItems,
        config = AccountInfoConfig(),
        headerTitle = "Account Information",
        onItemClick = { item ->
            // Handle item click
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Example 3: Custom configuration
    val customConfig = AccountInfoConfig(
        itemsPerRow = 2,
        showSingleItemCentered = false,
        cardElevation = 4.dp,
        cornerRadius = 16.dp,
        itemSpacing = 16.dp,
        rowSpacing = 20.dp,
        imageSize = 56.dp,
        textSpacing = 16.dp,
        textColor = Color(0xFF1A1A1A),
        backgroundColor = Color(0xFFF8F9FA),
        cardColor = Color(0xFFFFFFFF),
        fontSize = 16,
        fontWeight = FontWeight.Bold
    )

    AccountInfo(
        items = accountItems,
        config = customConfig,
        onItemClick = { item ->
            // Handle item click
        }
    )
}