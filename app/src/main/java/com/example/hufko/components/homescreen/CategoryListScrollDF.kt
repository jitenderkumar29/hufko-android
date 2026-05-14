package com.example.hufko.components.homescreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hufko.R

/* -------------------- DATA MODEL -------------------- */

data class ProductListDGrid(
    val name: String,
    val price: String,
    val imageRes: Int,
    val backgroundColor: Color? = null
)

/* -------------------- MAIN GRID -------------------- */

@Composable
fun CategoryListScrollDF(
    products: List<ProductListDGrid>,
    modifier: Modifier = Modifier,
    itemWidth: Dp = 140.dp,
    itemHeight: Dp = 120.dp,
    rowSpacing: Dp = 0.dp,
    itemSpacing: Dp = 0.dp,
    backgroundColor: Color = Color.White,
    defaultCardColor: Color = Color.White,
    onItemClick: (ProductListDGrid) -> Unit = {}
) {
    val pairedProducts = products.chunked(2)

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(pairedProducts) { pair ->
            Column(
                modifier = Modifier.width(itemWidth),
                verticalArrangement = Arrangement.spacedBy(rowSpacing)
            ) {
                pair.forEach { product ->
                    CategoryScrollItem(
                        product = product,
                        itemWidth = itemWidth,
                        itemHeight = itemHeight,
                        containerColor = product.backgroundColor ?: backgroundColor ?: defaultCardColor,
                        onClick = { onItemClick(product) }
                    )
                }
            }
        }
    }
}

/* -------------------- CARD ITEM -------------------- */

@Composable
fun CategoryScrollItem(
    product: ProductListDGrid,
    itemWidth: Dp,
    itemHeight: Dp,
    containerColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(containerColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* IMAGE */
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(1.dp),
                contentScale = ContentScale.Fit
            )

            /* PRICE STRIP (SAME BACKGROUND) */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(containerColor)
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = product.price,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

/* -------------------- PREVIEW -------------------- */

@Composable
fun CategoryListScrollPreview() {
    val products = listOf(
        ProductListDGrid("Chai Point", "FLAT 10% OFF", R.drawable.popular_chain_1),
        ProductListDGrid("abCoffee", "FLAT 10% OFF", R.drawable.popular_chain_2),
        ProductListDGrid("Good Flippin", "FLAT 10% OFF", R.drawable.popular_chain_3),
        ProductListDGrid("Chelvies", "FLAT 25% OFF", R.drawable.popular_chain_4),
        ProductListDGrid("Cafe Rue", "FLAT 20% OFF", R.drawable.popular_chain_5),
        ProductListDGrid("Barista", "FLAT 30% OFF", R.drawable.popular_chain_6)
    )

    MaterialTheme {
        CategoryListScrollDF(
            products = products,
            backgroundColor = Color(0xFFF2F2F2)
        )
    }
}

/* -------------------- DATA MODEL FOR URL -------------------- */

data class ProductListDGridUrl(
    val name: String,
    val price: String,
    val imageUrl: String,
    val backgroundColor: Color? = null,
    val placeholderRes: Int? = null,
    val errorRes: Int? = null
)
/* -------------------- MAIN GRID WITH URL SUPPORT -------------------- */

@Composable
fun CategoryListScrollDFDynamic(
    products: List<ProductListDGridUrl>,
    modifier: Modifier = Modifier,
    itemWidth: Dp = 140.dp,
    itemHeight: Dp = 120.dp, // This is image height only
    priceStripHeight: Dp = 40.dp, // New parameter for price strip height
    rowSpacing: Dp = 0.dp,
    itemSpacing: Dp = 0.dp,
    backgroundColor: Color = Color.White,
    defaultCardColor: Color = Color.White,
    priceStripBackgroundColor: Color = Color.White, // Dynamic price strip background color
    showLoadingIndicator: Boolean = true,
    onItemClick: (ProductListDGridUrl) -> Unit = {},
    dynamicItemSizes: Boolean = false,
    sizeMultiplier: (ProductListDGridUrl) -> Float = { 1f },
    enableGradientOverlay: Boolean = false,
    gradientColors: List<Color> = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
    cornerRadius: Dp = 14.dp,
    imageContentScale: ContentScale = ContentScale.FillBounds,
    showPriceStrip: Boolean = true,
    priceTextColor: Color = Color.Black,
    priceTextSize: Int = 12,
    priceTextWeight: FontWeight = FontWeight.Bold
) {
    val pairedProducts = products.chunked(2)

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(pairedProducts) { pair ->
            Column(
                modifier = Modifier.width(itemWidth),
                verticalArrangement = Arrangement.spacedBy(rowSpacing)
            ) {
                pair.forEach { product ->
                    val finalWidth = if (dynamicItemSizes) {
                        itemWidth * (sizeMultiplier(product) ?: 1f)
                    } else {
                        itemWidth
                    }

                    val finalHeight = if (dynamicItemSizes) {
                        itemHeight * (sizeMultiplier(product) ?: 1f)
                    } else {
                        itemHeight
                    }

                    CategoryScrollItemDynamic(
                        product = product,
                        itemWidth = finalWidth,
                        itemHeight = finalHeight,
                        priceStripHeight = priceStripHeight,
                        containerColor = product.backgroundColor ?: defaultCardColor,
                        priceStripBackgroundColor = priceStripBackgroundColor, // Pass dynamic color
                        showLoadingIndicator = showLoadingIndicator,
                        enableGradientOverlay = enableGradientOverlay,
                        gradientColors = gradientColors,
                        cornerRadius = cornerRadius,
                        imageContentScale = imageContentScale,
                        showPriceStrip = showPriceStrip,
                        priceTextColor = priceTextColor,
                        priceTextSize = priceTextSize,
                        priceTextWeight = priceTextWeight,
                        onClick = { onItemClick(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryScrollItemDynamic(
    product: ProductListDGridUrl,
    itemWidth: Dp,
    itemHeight: Dp,
    priceStripHeight: Dp = 40.dp,
    containerColor: Color,
    priceStripBackgroundColor: Color = Color.White, // Dynamic price strip background
    showLoadingIndicator: Boolean = true,
    enableGradientOverlay: Boolean = false,
    gradientColors: List<Color> = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
    cornerRadius: Dp = 14.dp,
    imageContentScale: ContentScale = ContentScale.FillBounds,
    showPriceStrip: Boolean = true,
    priceTextColor: Color = Color.Black,
    priceTextSize: Int = 12,
    priceTextWeight: FontWeight = FontWeight.Bold,
    onClick: () -> Unit
) {
    val totalHeight = itemHeight + (if (showPriceStrip) priceStripHeight else 0.dp)

    Card(
        modifier = Modifier
            .width(itemWidth)
            .height(totalHeight)
            .clickable { onClick() },
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(containerColor)
        ) {
            // Image Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = imageContentScale,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius))
                )

                // Loading indicator
                if (showLoadingIndicator) {
                    var isLoading by remember { mutableStateOf(true) }

                    LaunchedEffect(Unit) {
                        isLoading = false
                    }

                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.Gray,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }

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
            }

            // Price Strip BELOW the image with dynamic background color
            if (showPriceStrip) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(priceStripHeight)
                        .background(priceStripBackgroundColor) // Dynamic background color
                        .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Product Name (if needed)
                    if (product.name.isNotEmpty()) {
                        Text(
                            text = product.name,
                            fontSize = (priceTextSize - 2).sp,
                            fontWeight = FontWeight.Medium,
                            color = priceTextColor.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }

                    // Price
                    Text(
                        text = product.price,
                        fontSize = priceTextSize.sp,
                        fontWeight = priceTextWeight,
                        color = priceTextColor,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

/* -------------------- SIMPLIFIED VERSION THAT SHOULD WORK -------------------- */

@Composable
fun SimpleCategoryScrollItemDynamic(
    product: ProductListDGridUrl,
    itemWidth: Dp,
    itemHeight: Dp,
    containerColor: Color,
    priceStripBackgroundColor: Color = Color.White, // Dynamic price strip background
    cornerRadius: Dp = 14.dp,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clickable { onClick() },
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Simplified AsyncImage
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Price strip with dynamic background color
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(priceStripBackgroundColor) // Dynamic background color
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = product.price,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}