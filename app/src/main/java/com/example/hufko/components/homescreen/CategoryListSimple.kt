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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

/**
 * Simple category list component without arrow icons and optional headings
 */
data class CategoryItem(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val subtitle: String? = null, // Optional subtitle like "View products"
    val overlayTitle: String? = null, // Optional title for image overlay
    val overlaySubtitle: String? = null // Optional subtitle for image overlay
)
@Composable
fun CategoryListSimple(
    items: List<CategoryItem>,
    onItemClick: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    itemBackgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    showOverlayOnImage: Boolean = false,
    overlayBackground: Color = Color.Black.copy(alpha = 0.6f),
    overlayTextColor: Color = Color.White,
    showItemName: Boolean = true,
    customListItem: @Composable ((CategoryItem, () -> Unit) -> Unit)? = null,
    itemWidth: Dp = 150.dp, // Dynamic width
    itemHeight: Dp = 200.dp, // Dynamic height
    horizontalSpacing: Dp = 16.dp, // Dynamic spacing between items
    verticalPadding: Dp = 12.dp, // Dynamic vertical padding
    horizontalPadding: Dp = 16.dp // Dynamic horizontal padding
) {
    require(items.isNotEmpty()) { "Items list cannot be empty" }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        // Horizontal scrollable list of category items
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                if (customListItem != null) {
                    customListItem(item, { onItemClick(item) })
                } else {
                    CategoryListItemSimple(
                        item = item,
                        onClick = { onItemClick(item) },
                        backgroundColor = itemBackgroundColor,
                        textColor = textColor,
                        showOverlayOnImage = showOverlayOnImage,
                        overlayBackground = overlayBackground,
                        overlayTextColor = overlayTextColor,
                        showItemName = showItemName,
                        itemWidth = itemWidth,
                        itemHeight = itemHeight
                    )
                }
            }
        }
    }
}

/**
 * Simple category list item without arrow icon
 */
@Composable
fun CategoryListItemSimple(
    item: CategoryItem,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    showOverlayOnImage: Boolean = false,
    overlayBackground: Color = Color.Black.copy(alpha = 0.6f),
    overlayTextColor: Color = Color.White,
    showItemName: Boolean = true,
    itemWidth: Dp = 150.dp, // Dynamic width
    itemHeight: Dp = 200.dp // Dynamic height
) {
    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .background(backgroundColor),
        contentAlignment = Alignment.BottomStart
    ) {
        // Image
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            contentScale = ContentScale.FillBounds,
//            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )

        // Overlay (title only - no subtitle or arrow)
        if (showItemName) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(overlayBackground)
//                    .background(
//                        Brush.verticalGradient(
//                            colors = listOf(Color.Transparent, overlayBackground),
//                            startY = 80f
//                        )
//                    )
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = overlayTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

/**
 * Simple category list with optional heading
 */
@Composable
fun CategoryListSimpleWithHeading(
    heading: String? = null,
    subtitle: String? = null,
    items: List<CategoryItem>,
    onItemClick: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    itemBackgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    showOverlayOnImage: Boolean = false,
    overlayBackground: Color = Color.Black.copy(alpha = 0.6f),
    overlayTextColor: Color = Color.White,
    showItemName: Boolean = true,
    customListItem: @Composable ((CategoryItem, () -> Unit) -> Unit)? = null,
    itemWidth: Dp = 150.dp, // Dynamic width
    itemHeight: Dp = 200.dp, // Dynamic height
    horizontalSpacing: Dp = 16.dp, // Dynamic spacing between items
    verticalPadding: Dp = 12.dp, // Dynamic vertical padding
    horizontalPadding: Dp = 16.dp, // Dynamic horizontal padding
    headingBottomPadding: Dp = 12.dp // Dynamic padding below heading
) {
    require(items.isNotEmpty()) { "Items list cannot be empty" }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        // Optional header with heading and subtitle
        heading?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = headingBottomPadding)
            ) {
                Text(
                    text = heading,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                // Optional subtitle
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        // Horizontal scrollable list of category items
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                if (customListItem != null) {
                    customListItem(item, { onItemClick(item) })
                } else {
                    CategoryListItemSimple(
                        item = item,
                        onClick = { onItemClick(item) },
                        backgroundColor = itemBackgroundColor,
                        textColor = textColor,
                        showOverlayOnImage = showOverlayOnImage,
                        overlayBackground = overlayBackground,
                        overlayTextColor = overlayTextColor,
                        showItemName = showItemName,
                        itemWidth = itemWidth,
                        itemHeight = itemHeight
                    )
                }
            }
        }
    }
}

/**
 * Example custom list item - Square shape with circular image and text below
 */
@Composable
fun CustomCategoryListItem(
    item: CategoryItem,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    itemWidth: Dp = 100.dp, // Dynamic width
    itemHeight: Dp = 120.dp, // Dynamic height
    imageSize: Dp = 80.dp, // Dynamic image size
    textSpacing: Dp = 8.dp // Dynamic text spacing
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clickable(onClick = onClick)
    ) {
        // Circular image
        Box(
            modifier = Modifier
                .size(imageSize)
                .clip(RoundedCornerShape(0.dp))
                .background(backgroundColor)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                contentScale = ContentScale.FillBounds,
//                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp))
            )
        }

        // Text below image
        Spacer(modifier = Modifier.height(textSpacing))
        Text(
            text = item.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

/**
 * Another example custom list item - Card style with shadow
 */
@Composable
fun CustomCardCategoryListItem(
    item: CategoryItem,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    itemWidth: Dp = 120.dp, // Dynamic width
    itemHeight: Dp = 160.dp, // Dynamic height
    imageSize: Dp = 70.dp, // Dynamic image size
    textSpacing: Dp = 12.dp, // Dynamic text spacing
    cardPadding: Dp = 8.dp // Dynamic card padding
) {
    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(cardPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(textSpacing))
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

/**
 * Convenience composable for simple room categories
 */
@Composable
fun SimpleRoomCategories(
    onItemClick: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier,
    showOverlayOnImage: Boolean = true,
    showItemName: Boolean = true,
    withHeading: Boolean = false,
    customListItem: @Composable ((CategoryItem, () -> Unit) -> Unit)? = null,
    itemWidth: Dp = 150.dp,
    itemHeight: Dp = 200.dp,
    horizontalSpacing: Dp = 16.dp,
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp,
    backgroundColor: Color = MaterialTheme.customColors.white
) {
    val roomCategoriesSimple = listOf(
        CategoryItem(0, "Kitchen", R.drawable.ic_newly_launched, "View products"),
        CategoryItem(1, "Bathroom", R.drawable.ic_credit_card_week, "View products"),
        CategoryItem(2, "Bedroom", R.drawable.ic_price_drop, "View products"),
    )
    val items = roomCategoriesSimple

    if (withHeading) {
        CategoryListSimpleWithHeading(
            heading = "Shop by Room",
            items = items,
            onItemClick = onItemClick,
            modifier = modifier,
            showOverlayOnImage = showOverlayOnImage,
            showItemName = showItemName,
            customListItem = customListItem,
            itemWidth = itemWidth,
            itemHeight = itemHeight,
            horizontalSpacing = horizontalSpacing,
            verticalPadding = verticalPadding,
            horizontalPadding = horizontalPadding,
            backgroundColor = backgroundColor
        )
    } else {
        CategoryListSimple(
            items = items,
            onItemClick = onItemClick,
            modifier = modifier,
            showOverlayOnImage = showOverlayOnImage,
            showItemName = showItemName,
            customListItem = customListItem,
            itemWidth = itemWidth,
            itemHeight = itemHeight,
            horizontalSpacing = horizontalSpacing,
            verticalPadding = verticalPadding,
            horizontalPadding = horizontalPadding,
            backgroundColor = backgroundColor
        )
    }
}

/**
 * Preview with different dynamic configurations
 */
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun CategoryListSimplePreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Small items version
            CategoryListSimple(
                items = roomCategoriesSimple,
                onItemClick = { item -> println("Selected: ${item.name}") },
                showOverlayOnImage = true,
                showItemName = true,
                itemWidth = 120.dp,
                itemHeight = 160.dp,
                horizontalSpacing = 12.dp,
                backgroundColor = Color.LightGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Medium items version
            CategoryListSimple(
                items = roomCategoriesSimple,
                onItemClick = { item -> println("Selected: ${item.name}") },
                customListItem = { item, onClick ->
                    CustomCategoryListItem(
                        item = item,
                        onClick = onClick,
                        itemWidth = 200.dp,
                        itemHeight = 300.dp,
                        imageSize = 300.dp,
                        backgroundColor = Color.White
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Large items with heading
            CategoryListSimpleWithHeading(
                heading = "Popular Categories",
                items = roomCategoriesSimple,
                onItemClick = { item -> println("Selected: ${item.name}") },
                customListItem = { item, onClick ->
                    CustomCardCategoryListItem(
                        item = item,
                        onClick = onClick,
                        itemWidth = 140.dp,
                        itemHeight = 180.dp,
                        imageSize = 80.dp,
                        backgroundColor = Color(0xFFF5F5F5)
                    )
                },
                backgroundColor = Color(0xFFE8F5E8)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Compact version
            CategoryListSimple(
                items = roomCategoriesSimple,
                onItemClick = { item -> println("Selected: ${item.name}") },
                itemWidth = 100.dp,
                itemHeight = 140.dp,
                horizontalSpacing = 8.dp,
                verticalPadding = 8.dp,
                horizontalPadding = 12.dp,
                backgroundColor = Color(0xFFFFF8E1)
            )
        }
    }
}

// Keep your existing roomCategoriesSimple list
val roomCategoriesSimple = listOf(
    CategoryItem(0, "Kitchen", R.drawable.ic_newly_launched, "View products"),
    CategoryItem(1, "Bathroom", R.drawable.ic_credit_card_week, "View products"),
    CategoryItem(2, "Bedroom", R.drawable.ic_price_drop, "View products"),
)

/**
 * Dynamic category list component with enhanced features
 * Similar to CategoryListSimple but with additional dynamic capabilities
 */


/**
 * New data class for URL-based images
 */
data class CategoryItemUrl(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val subtitle: String? = null,
    val overlayTitle: String? = null,
    val overlaySubtitle: String? = null
)

/**
 * Dynamic category list component for URL images
 */
@Composable
fun CategoryListSimpleDynamicUrl(
    items: List<CategoryItemUrl>,
    onItemClick: (CategoryItemUrl) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    itemBackgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    showOverlayOnImage: Boolean = false,
    overlayBackground: Color = Color.Black.copy(alpha = 0.6f),
    overlayTextColor: Color = Color.White,
    showItemName: Boolean = true,
    itemWidth: Dp = 150.dp,
    itemHeight: Dp = 200.dp,
    horizontalSpacing: Dp = 16.dp,
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp,
    dynamicItemSizes: Boolean = false,
    sizeMultiplier: (CategoryItemUrl) -> Float = { 1f },
    enableGradientOverlay: Boolean = false,
    gradientColors: List<Color> = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
    showSubtitle: Boolean = true,
    subtitleTextColor: Color = Color.White,
    imageContentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Dp = 8.dp,
    maxLinesForName: Int = 1,
    fontSizeForName: Int = 16
) {
    require(items.isNotEmpty()) { "Items list cannot be empty" }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                val finalWidth = if (dynamicItemSizes) {
                    itemWidth * (sizeMultiplier(item) ?: 1f)
                } else {
                    itemWidth
                }

                val finalHeight = if (dynamicItemSizes) {
                    itemHeight * (sizeMultiplier(item) ?: 1f)
                } else {
                    itemHeight
                }

                CategoryListItemDynamicUrl(
                    item = item,
                    onClick = { onItemClick(item) },
                    backgroundColor = itemBackgroundColor,
                    textColor = textColor,
                    showOverlayOnImage = showOverlayOnImage,
                    overlayBackground = overlayBackground,
                    overlayTextColor = overlayTextColor,
                    showItemName = showItemName,
                    itemWidth = finalWidth,
                    itemHeight = finalHeight,
                    enableGradientOverlay = enableGradientOverlay,
                    gradientColors = gradientColors,
                    showSubtitle = showSubtitle,
                    subtitleTextColor = subtitleTextColor,
                    imageContentScale = imageContentScale,
                    cornerRadius = cornerRadius,
                    maxLinesForName = maxLinesForName,
                    fontSizeForName = fontSizeForName
                )
            }
        }
    }
}

/**
 * Dynamic category list item for URL images
 */
@Composable
fun CategoryListItemDynamicUrl(
    item: CategoryItemUrl,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    showOverlayOnImage: Boolean = false,
    overlayBackground: Color = Color.Black.copy(alpha = 0.6f),
    overlayTextColor: Color = Color.White,
    showItemName: Boolean = true,
    itemWidth: Dp = 150.dp,
    itemHeight: Dp = 200.dp,
    enableGradientOverlay: Boolean = false,
    gradientColors: List<Color> = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
    showSubtitle: Boolean = true,
    subtitleTextColor: Color = Color.White,
    imageContentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Dp = 8.dp,
    maxLinesForName: Int = 1,
    fontSizeForName: Int = 16
) {
    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick)
            .background(backgroundColor),
        contentAlignment = Alignment.BottomStart
    ) {
        // Remote image using Coil
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            contentScale = imageContentScale,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
        )

        // Gradient overlay (if enabled)
        if (enableGradientOverlay) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = gradientColors,
                            startY = 80f
                        )
                    )
            )
        }

        // Content overlay
        if (showItemName || (showSubtitle && item.subtitle != null)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Column {
                    if (showItemName && item.name.isNotEmpty()) {
                        Text(
                            text = item.name,
                            fontSize = fontSizeForName.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (showOverlayOnImage || enableGradientOverlay) overlayTextColor else textColor,
                            maxLines = maxLinesForName,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    if (showSubtitle && item.subtitle != null) {
                        Text(
                            text = item.subtitle!!,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = if (showOverlayOnImage || enableGradientOverlay) subtitleTextColor else textColor.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

/**
 * With heading support for URL images
 */
@Composable
fun CategoryListSimpleDynamicUrlWithHeading(
    heading: String? = null,
    subtitle: String? = null,
    items: List<CategoryItemUrl>,
    onItemClick: (CategoryItemUrl) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    itemBackgroundColor: Color = MaterialTheme.customColors.white,
    textColor: Color = Color.Black,
    showOverlayOnImage: Boolean = false,
    overlayBackground: Color = Color.Black.copy(alpha = 0.6f),
    overlayTextColor: Color = Color.White,
    showItemName: Boolean = true,
    itemWidth: Dp = 150.dp,
    itemHeight: Dp = 200.dp,
    horizontalSpacing: Dp = 16.dp,
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp,
    headingBottomPadding: Dp = 12.dp,
    dynamicItemSizes: Boolean = false,
    sizeMultiplier: (CategoryItemUrl) -> Float = { 1f },
    enableGradientOverlay: Boolean = false,
    gradientColors: List<Color> = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
    showSubtitle: Boolean = true,
    subtitleTextColor: Color = Color.White,
    imageContentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Dp = 8.dp,
    maxLinesForName: Int = 1,
    fontSizeForName: Int = 16
) {
    require(items.isNotEmpty()) { "Items list cannot be empty" }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        // Optional header with heading and subtitle
        heading?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = headingBottomPadding)
            ) {
                Text(
                    text = heading,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        CategoryListSimpleDynamicUrl(
            items = items,
            onItemClick = onItemClick,
            modifier = Modifier,
            backgroundColor = Color.Transparent,
            itemBackgroundColor = itemBackgroundColor,
            textColor = textColor,
            showOverlayOnImage = showOverlayOnImage,
            overlayBackground = overlayBackground,
            overlayTextColor = overlayTextColor,
            showItemName = showItemName,
            itemWidth = itemWidth,
            itemHeight = itemHeight,
            horizontalSpacing = horizontalSpacing,
            verticalPadding = 0.dp,
            horizontalPadding = 0.dp,
            dynamicItemSizes = dynamicItemSizes,
            sizeMultiplier = sizeMultiplier,
            enableGradientOverlay = enableGradientOverlay,
            gradientColors = gradientColors,
            showSubtitle = showSubtitle,
            subtitleTextColor = subtitleTextColor,
            imageContentScale = imageContentScale,
            cornerRadius = cornerRadius,
            maxLinesForName = maxLinesForName,
            fontSizeForName = fontSizeForName
        )
    }
}