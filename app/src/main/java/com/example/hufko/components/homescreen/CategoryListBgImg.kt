package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.ui.theme.customColors

/* -------------------- DATA MODEL -------------------- */

data class CategoryItemBgImg(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val subtitle: String? = null
)

/* -------------------- MAIN COMPONENT -------------------- */

@Composable
fun CategoryListBgImg(
    backgroundImageRes: Int,
    items: List<CategoryItemBgImg>,
    onItemClick: (CategoryItemBgImg) -> Unit,
    modifier: Modifier = Modifier,
    backgroundImageHeight: Dp = 200.dp,
    listItemWidth: Dp = 140.dp,
    listItemHeight: Dp = 180.dp,
    overlayItemSize: Dp = 72.dp,
    overlayTextSize: TextUnit = 12.sp,
    backgroundOverlay: Color = Color.Black.copy(alpha = 0.3f),
    title: String? = null,
    showHorizontalList: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors.white)
    ) {

        /* -------- BACKGROUND IMAGE WITH OVERLAY ITEMS -------- */

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(backgroundImageHeight)
        ) {
            val boxHeight = maxHeight

            Image(
                painter = painterResource(backgroundImageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundOverlay)
            )

            // Container for overlay items at the bottom half
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxHeight / 2)
                    .align(Alignment.BottomCenter)
            ) {
                // Scrollable overlay items in the bottom half
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(28.dp),
                    verticalAlignment = Alignment.CenterVertically // Changed to center for better alignment when no text
                ) {
                    items(items) { item ->
                        OverlayItemOnBackground(
                            item = item,
                            size = overlayItemSize,
                            textSize = overlayTextSize,
                            onClick = { onItemClick(item) }
                        )
                    }
                }
            }
        }

        /* -------- OPTIONAL TITLE -------- */

        if (!title.isNullOrEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        /* -------- CONDITIONAL HORIZONTAL SCROLLABLE LIST -------- */

        if (showHorizontalList) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    CategoryListItemBgImg(
                        item = item,
                        width = listItemWidth,
                        height = listItemHeight,
                        onClick = { onItemClick(item) }
                    )
                }
            }
        }
    }
}

/* -------------------- OVERLAY ITEM -------------------- */

@Composable
fun OverlayItemOnBackground(
    item: CategoryItemBgImg,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
    textSize: TextUnit = 12.sp,
    elevation: Dp = 6.dp
) {
    Column(
        modifier = modifier
            .width(if (item.name.isNotEmpty()) 100.dp else size) // Adjust width based on name presence
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (item.name.isNotEmpty()) Arrangement.Top else Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .shadow(elevation, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = item.name.ifEmpty { "Category item ${item.id}" }, // Use fallback description
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        if (item.name.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = item.name,
                fontSize = textSize,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/* -------------------- LIST ITEM -------------------- */

@Composable
fun CategoryListItemBgImg(
    item: CategoryItemBgImg,
    onClick: () -> Unit,
    width: Dp = 140.dp,
    height: Dp = 140.dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(item.imageRes),
            contentDescription = item.name.ifEmpty { "Category item ${item.id}" },
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (item.name.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(8.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}