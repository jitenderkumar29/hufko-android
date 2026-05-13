package com.example.hufko.components.homescreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.hufko.api.services.models.Banner
import coil.compose.AsyncImage
import androidx.compose.runtime.Composable

enum class DotPosition {
    OVERLAY,      // Dots overlay on bottom of image
    BELOW_IMAGE,  // Dots below the image
    NONE          // No dots indicator
}

data class BannerPadding(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
) {
    companion object {
        val Zero = BannerPadding(0.dp, 0.dp, 0.dp, 0.dp)

        fun all(padding: Dp) = BannerPadding(padding, padding, padding, padding)

        fun horizontal(horizontal: Dp) = BannerPadding(start = horizontal, end = horizontal)

        fun vertical(vertical: Dp) = BannerPadding(top = vertical, bottom = vertical)

        fun symmetric(horizontal: Dp = 0.dp, vertical: Dp = 0.dp) =
            BannerPadding(start = horizontal, top = vertical, end = horizontal, bottom = vertical)
    }
}

// ==================== BANNER IMAGE DATA SEALED CLASS ====================
sealed class BannerImageData {
    data class Remote(val url: String, val placeholder: Int = R.drawable.ic_broken_image) : BannerImageData()
    data class Local(val resId: Int) : BannerImageData()
}

// ==================== ORIGINAL BANNER FOOD (Static Images) ====================
@Composable
fun BannerFood(
    images: List<Painter>,
    onImageClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 270.dp,
    roundedCornerShape: Dp = 16.dp,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = MaterialTheme.customColors.linkColor,
    unselectedDotColor: Color = Color.White.copy(alpha = 0.9f),
    backgroundColor: Color = Color.White,
    contentScale: ContentScale = ContentScale.FillBounds,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true,
    dotPosition: DotPosition = DotPosition.BELOW_IMAGE,
    overlayGradient: Boolean = true,
    showDots: Boolean = true,
    padding: BannerPadding = BannerPadding.Zero
) {
    require(images.isNotEmpty()) { "Images list cannot be empty" }

    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    val bannerShape = RoundedCornerShape(roundedCornerShape)

    // Auto-scroll effect
    LaunchedEffect(autoScrollEnabled, showDots) {
        if (!autoScrollEnabled) return@LaunchedEffect

        while (true) {
            delay(autoScrollDelay)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
            .padding(
                start = padding.start,
                top = padding.top,
                end = padding.end,
                bottom = padding.bottom
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(bannerShape)
            ) { page ->
                Image(
                    painter = images[page],
                    contentDescription = "Banner ${page + 1}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(bannerShape)
                        .clickable { onImageClick(page) },
                    contentScale = contentScale
                )
            }

            if (dotPosition == DotPosition.OVERLAY && showDots && (dotSize == null || dotSize > 0.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (overlayGradient) {
                                Modifier.drawWithContent {
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.3f)
                                            ),
                                            startY = size.height * 0.6f,
                                            endY = size.height
                                        )
                                    )
                                }
                            } else {
                                Modifier
                            }
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    OverlayDotsFashion(
                        pagerState = pagerState,
                        imageCount = images.size,
                        dotSize = dotSize,
                        dotPadding = dotPadding,
                        selectedDotColor = Color.White,
                        unselectedDotColor = Color.White.copy(alpha = 0.9f),
                        indicatorDuration = autoScrollDelay,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }

        if (dotPosition == DotPosition.BELOW_IMAGE && showDots && (dotSize == null || dotSize > 0.dp)) {
            Column {
                Spacer(
                    modifier = Modifier.height(8.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                )

                OverlayDotsFashion(
                    pagerState = pagerState,
                    imageCount = images.size,
                    dotSize = dotSize,
                    dotPadding = dotPadding,
                    selectedDotColor = MaterialTheme.customColors.onPrimaryContainer,
                    unselectedDotColor = Color.Gray,
                    indicatorDuration = autoScrollDelay
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                )
            }
        }
    }
}

// ==================== DYNAMIC BANNER FOOD FOR DATA (Using BannerImageData) ====================
@Composable
fun DynamicBannerFoodFromData(
    images: List<BannerImageData>,
    onImageClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 270.dp,
    roundedCornerShape: Dp = 16.dp,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = MaterialTheme.customColors.linkColor,
    unselectedDotColor: Color = Color.White.copy(alpha = 0.9f),
    backgroundColor: Color = Color.White,
    contentScale: ContentScale = ContentScale.Crop,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true,
    dotPosition: DotPosition = DotPosition.OVERLAY,
    overlayGradient: Boolean = false,
    showDots: Boolean = true,
    padding: BannerPadding = BannerPadding.Zero
) {
    require(images.isNotEmpty()) { "Images list cannot be empty" }

    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val bannerShape = RoundedCornerShape(roundedCornerShape)

    // Auto-scroll effect
    LaunchedEffect(autoScrollEnabled, showDots) {
        if (!autoScrollEnabled) return@LaunchedEffect

        while (true) {
            delay(autoScrollDelay)
            val nextPage = (pagerState.currentPage + 1) % images.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
            .padding(
                start = padding.start,
                top = padding.top,
                end = padding.end,
                bottom = padding.bottom
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(bannerShape)
            ) { page ->
                val imageData = images[page]

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(bannerShape)
                        .clickable { onImageClick(page) }
                ) {
                    when (imageData) {
                        is BannerImageData.Remote -> {
                            // Log the URL being loaded
                            println("🖼️ Loading image $page: ${imageData.url}")

                            val painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(context)
                                    .data(imageData.url)
                                    .crossfade(true)
                                    .build()
                            )

                            val imageState = painter.state

                            // Log image state
                            when (imageState) {
                                is AsyncImagePainter.State.Success -> {
                                    println("✅ Image $page loaded successfully")
                                    Image(
                                        painter = painter,
                                        contentDescription = "Banner ${page + 1}",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = contentScale
                                    )
                                }
                                is AsyncImagePainter.State.Error -> {
                                    println("❌ Image $page failed to load: ${imageState.result.throwable.message}")
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0xFFF5F5F5)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "🖼️",
                                                fontSize = 48.sp
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Failed to load image",
                                                fontSize = 12.sp,
                                                color = Color.Gray
                                            )
                                            Text(
                                                text = imageData.url.take(50),
                                                fontSize = 10.sp,
                                                color = Color.Gray,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                                is AsyncImagePainter.State.Loading -> {
                                    println("⏳ Image $page loading...")
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0xFFF5F5F5)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(40.dp),
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Loading...",
                                                fontSize = 12.sp,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }
                                else -> {
                                    println("⚠️ Image $page unknown state")
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0xFFF5F5F5)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "📷",
                                            fontSize = 48.sp
                                        )
                                    }
                                }
                            }
                        }
                        is BannerImageData.Local -> {
                            Image(
                                painter = painterResource(imageData.resId),
                                contentDescription = "Banner ${page + 1}",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = contentScale
                            )
                        }
                    }
                }
            }

            // Overlay dots
            if (dotPosition == DotPosition.OVERLAY && showDots && (dotSize == null || dotSize > 0.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (overlayGradient) {
                                Modifier.drawWithContent {
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.3f)
                                            ),
                                            startY = size.height * 0.6f,
                                            endY = size.height
                                        )
                                    )
                                }
                            } else {
                                Modifier
                            }
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    OverlayDotsFashion(
                        pagerState = pagerState,
                        imageCount = images.size,
                        dotSize = dotSize,
                        dotPadding = dotPadding,
                        selectedDotColor = selectedDotColor,
                        unselectedDotColor = unselectedDotColor,
                        indicatorDuration = autoScrollDelay,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }

        // Dots below image
        if (dotPosition == DotPosition.BELOW_IMAGE && showDots && (dotSize == null || dotSize > 0.dp)) {
            Column {
                Spacer(
                    modifier = Modifier.height(8.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                )

                OverlayDotsFashion(
                    pagerState = pagerState,
                    imageCount = images.size,
                    dotSize = dotSize,
                    dotPadding = dotPadding,
                    selectedDotColor = selectedDotColor,
                    unselectedDotColor = unselectedDotColor,
                    indicatorDuration = autoScrollDelay
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                        .fillMaxWidth()
                        .background(backgroundColor)
                )
            }
        }
    }
}

// ==================== DYNAMIC BANNER FOOD (Works with Banner model) ====================
@Composable
fun DynamicBannerFood(
    banners: List<Banner>,
    onBannerClick: (Banner) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 270.dp,
    roundedCornerShape: Dp = 16.dp,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = MaterialTheme.customColors.linkColor,
    unselectedDotColor: Color = Color.White.copy(alpha = 0.9f),
    backgroundColor: Color = Color.White,
    contentScale: ContentScale = ContentScale.Crop,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true,
    dotPosition: DotPosition = DotPosition.OVERLAY,
    overlayGradient: Boolean = true,
    showDots: Boolean = true,
    padding: BannerPadding = BannerPadding.Zero
) {
    require(banners.isNotEmpty()) { "Banners list cannot be empty" }

    val pagerState = rememberPagerState(
        pageCount = { banners.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val bannerShape = RoundedCornerShape(roundedCornerShape)

    // Auto-scroll effect
    LaunchedEffect(autoScrollEnabled) {
        if (!autoScrollEnabled) return@LaunchedEffect

        while (true) {
            delay(autoScrollDelay)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
            .padding(
                start = padding.start,
                top = padding.top,
                end = padding.end,
                bottom = padding.bottom
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(bannerShape)
            ) { page ->
                val banner = banners[page]

                // Load image with error handling
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(banner.imageUrl)
                        .crossfade(true)
                        .build()
                )

                val imageState = painter.state

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(bannerShape)
                        .clickable { onBannerClick(banner) }
                ) {
                    // Show image when loaded successfully
                    when (imageState) {
                        is AsyncImagePainter.State.Success -> {
                            Image(
                                painter = painter,
                                contentDescription = banner.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = contentScale
                            )
                        }
                        is AsyncImagePainter.State.Error -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                banner.title?.let {
                                    Text(
                                        text = it,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        else -> {
                            // Loading state
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(32.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }

                    // Overlay gradient and text
                    if (overlayGradient) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            banner.title?.let {
                                Text(
                                    text = it,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            if (!banner.description.isNullOrEmpty()) {
                                Text(
                                    text = banner.description,
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }

                            // Banner type indicator
                            Box(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .background(
                                        color = when (banner.bannerType) {
                                            "HOME_PAGE" -> Color(0xFFFF5722)
                                            "PROMOTIONAL" -> Color(0xFF4CAF50)
                                            "FLASH_SALE" -> Color(0xFFF44336)
                                            else -> Color(0xFF9C27B0)
                                        },
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = banner.bannerType?.replace("_", " ") ?: "",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // Overlay dots
            if (dotPosition == DotPosition.OVERLAY && showDots && (dotSize == null || dotSize > 0.dp)) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    OverlayDotsFashion(
                        pagerState = pagerState,
                        imageCount = banners.size,
                        dotSize = dotSize,
                        dotPadding = dotPadding,
                        selectedDotColor = Color.White,
                        unselectedDotColor = Color.White.copy(alpha = 0.5f),
                        indicatorDuration = autoScrollDelay,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OverlayDotsFashion(
    pagerState: PagerState,
    imageCount: Int,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = Color.White,
    unselectedDotColor: Color = Color.Gray,
    indicatorDuration: Long = 3000L,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val defaultDotSize = 8.dp
    val defaultDotPadding = 6.dp

    val actualDotSize = dotSize ?: defaultDotSize
    val actualDotPadding = dotPadding ?: defaultDotPadding

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 1.dp)
        ) {
            repeat(imageCount) { index ->
                val isSelected = pagerState.currentPage == index
                val isHighlighted = index <= pagerState.currentPage

                val progress = remember { Animatable(0f) }
                if (isSelected) {
                    LaunchedEffect(pagerState.currentPage) {
                        progress.snapTo(0f)
                        progress.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(
                                durationMillis = indicatorDuration.toInt(),
                                easing = LinearEasing
                            )
                        )
                    }
                } else {
                    LaunchedEffect(pagerState.currentPage) {
                        progress.snapTo(0f)
                    }
                }

                val targetSize = if (isSelected) actualDotSize * 1.2f else actualDotSize
                val animatedSize = animateDpAsState(targetValue = targetSize)

                Box(
                    modifier = Modifier
                        .padding(horizontal = actualDotPadding)
                        .size(animatedSize.value)
                        .background(
                            color = if (isHighlighted) selectedDotColor else unselectedDotColor,
                            shape = CircleShape
                        )
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                )
            }
        }
    }
}

// Overloads for backward compatibility
@Composable
fun BannerFood(
    images: List<Painter>,
    onImageClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 270.dp,
    roundedCornerShape: Dp = 16.dp,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = MaterialTheme.customColors.linkColor,
    unselectedDotColor: Color = Color.White.copy(alpha = 0.9f),
    backgroundColor: Color = Color.White,
    contentScale: ContentScale = ContentScale.FillBounds,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true,
    dotPosition: DotPosition = DotPosition.BELOW_IMAGE,
    overlayGradient: Boolean = true
) {
    BannerFood(
        images = images,
        onImageClick = onImageClick,
        modifier = modifier,
        height = height,
        roundedCornerShape = roundedCornerShape,
        dotSize = dotSize,
        dotPadding = dotPadding,
        selectedDotColor = selectedDotColor,
        unselectedDotColor = unselectedDotColor,
        backgroundColor = backgroundColor,
        contentScale = contentScale,
        autoScrollDelay = autoScrollDelay,
        autoScrollEnabled = autoScrollEnabled,
        dotPosition = dotPosition,
        overlayGradient = overlayGradient,
        showDots = true,
        padding = BannerPadding.Zero
    )
}

@Composable
fun BannerFoodWithoutDots(
    images: List<Painter>,
    onImageClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 270.dp,
    roundedCornerShape: Dp = 16.dp,
    backgroundColor: Color = Color.White,
    contentScale: ContentScale = ContentScale.FillBounds,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true,
    padding: BannerPadding = BannerPadding.Zero
) {
    BannerFood(
        images = images,
        onImageClick = onImageClick,
        modifier = modifier,
        height = height,
        roundedCornerShape = roundedCornerShape,
        dotSize = 0.dp,
        backgroundColor = backgroundColor,
        contentScale = contentScale,
        autoScrollDelay = autoScrollDelay,
        autoScrollEnabled = autoScrollEnabled,
        dotPosition = DotPosition.NONE,
        overlayGradient = false,
        showDots = false,
        padding = padding
    )
}

@Composable
fun DynamicBannerCarousel(
    banners: List<Banner>,
    onBannerClick: (Banner) -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    // Auto-scroll effect
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(3000)
            currentIndex = (currentIndex + 1) % banners.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        if (banners.isNotEmpty()) {
            val banner = banners[currentIndex]

            // Load image with error handling
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(banner.imageUrl)
                    .crossfade(true)
                    .build()
            )

            val imageState = painter.state

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onBannerClick(banner) }
            ) {
                // Show image when loaded successfully
                if (imageState is AsyncImagePainter.State.Success) {
                    Image(
                        painter = painter,
                        contentDescription = banner.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                // Show error when image fails to load
                else if (imageState is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(R.drawable.ic_broken_image),
                                contentDescription = "Image Error",
                                modifier = Modifier.size(48.dp),
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Failed to load image",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            banner.title?.let {
                                Text(
                                    text = it,
                                    color = Color.DarkGray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
                // Show loading placeholder
                else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Loading...",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Banner overlay text and gradient
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                            )
                        )
                        .padding(16.dp)
                ) {
                    Column {
                        banner.title?.let {
                            Text(
                                text = it,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        banner.description?.let {
                            Text(
                                text = it,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp,
                                maxLines = 2,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        // Banner type indicator
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .background(
                                    color = when (banner.bannerType) {
                                        "HOME_PAGE" -> Color(0xFFFF5722)
                                        "PROMOTIONAL" -> Color(0xFF4CAF50)
                                        "FLASH_SALE" -> Color(0xFFF44336)
                                        else -> Color(0xFF9C27B0)
                                    },
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = banner.bannerType?.replace("_", " ") ?: "",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }

            // Dot indicators
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                banners.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (index == currentIndex) Color.White
                                else Color.White.copy(alpha = 0.5f)
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun DynamicBannerCard(
    banner: Banner,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 160.dp,
    height: Dp = 200.dp,
    showTypeTag: Boolean = true,
    showPriority: Boolean = true
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(banner.imageUrl)
            .crossfade(true)
            .build()
    )

    val imageState = painter.state

    Card(
        modifier = modifier
            .width(width)
            .height(height)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Show image when loaded successfully
            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    painter = painter,
                    contentDescription = banner.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            // Show error when image fails to load
            else if (imageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(R.drawable.ic_broken_image),
                            contentDescription = "Image Error",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = banner.title?.take(20) ?: "",
                            color = Color.Gray,
                            fontSize = 10.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Text(
                            text = "Image not found",
                            color = Color.Gray,
                            fontSize = 9.sp
                        )
                    }
                }
            }
            // Show loading placeholder
            else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Loading...",
                            color = Color.Gray,
                            fontSize = 10.sp
                        )
                    }
                }
            }

            // Overlay gradient
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f))
                        )
                    )
            )

            // Banner text
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                banner.title?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Banner type tag
                if (showTypeTag) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .background(
                                color = when (banner.bannerType) {
                                    "HOME_PAGE" -> Color(0xFFFF5722)
                                    "PROMOTIONAL" -> Color(0xFF4CAF50)
                                    "FLASH_SALE" -> Color(0xFFF44336)
                                    else -> Color(0xFF9C27B0)
                                },
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = banner.bannerType?.replace("_", " ") ?: "",
                            color = Color.White,
                            fontSize = 9.sp
                        )
                    }
                }

                // Priority indicator - FIXED NULL SAFETY
                if (showPriority) {
                    val priorityValue = banner.priority ?: 0
                    if (priorityValue in 1..3) {
                        Text(
                            text = when (priorityValue) {
                                1 -> "🔥 Most Popular"
                                2 -> "🔥 Very Popular"
                                3 -> "🔥 Popular"
                                else -> "🔥 Popular"
                            },
                            color = Color(0xFFFF9800),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DynamicBannerWithAsyncImage(
    images: List<BannerImageData.Remote>,
    onImageClick: (Int) -> Unit = {},
    autoScrollDelay: Long = 5000,  // Changed to Long to match other components
    height: Dp = 200.dp,
    roundedCornerShape: Dp = 16.dp,
    contentScale: ContentScale = ContentScale.Crop,
    dotSize: Dp? = null,  // Changed to nullable to match
    dotPadding: Dp? = null,  // Changed to nullable to match
    dotPosition: DotPosition = DotPosition.OVERLAY,
    overlayGradient: Boolean = false,
    selectedDotColor: Color = Color.White,
    unselectedDotColor: Color = Color.White.copy(alpha = 0.5f),  // Added to match
    modifier: Modifier = Modifier,
    padding: BannerPadding = BannerPadding.Zero  // Changed to match other components
) {
    if (images.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

    val bannerShape = RoundedCornerShape(roundedCornerShape)

    // Auto-scroll effect
    LaunchedEffect(autoScrollDelay, images.size) {
        if (autoScrollDelay > 0 && images.size > 1) {
            while (true) {
                delay(autoScrollDelay)
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = padding.start,
                top = padding.top,
                end = padding.end,
                bottom = padding.bottom
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(bannerShape)
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(bannerShape)
                        .clickable { onImageClick(page) },
                    shape = bannerShape,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[page].url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Banner ${page + 1}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        error = painterResource(R.drawable.ic_error)
                    )
                }
            }

            // Overlay dots (same as DynamicBannerFoodFromData)
            if (dotPosition == DotPosition.OVERLAY && (dotSize == null || dotSize > 0.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (overlayGradient) {
                                Modifier.drawWithContent {
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.3f)
                                            ),
                                            startY = size.height * 0.6f,
                                            endY = size.height
                                        )
                                    )
                                }
                            } else {
                                Modifier
                            }
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    OverlayDotsFashion(
                        pagerState = pagerState,
                        imageCount = images.size,
                        dotSize = dotSize,
                        dotPadding = dotPadding,
                        selectedDotColor = selectedDotColor,
                        unselectedDotColor = unselectedDotColor,
                        indicatorDuration = autoScrollDelay,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            }
        }

        // Dots below image (same as DynamicBannerFoodFromData)
        if (dotPosition == DotPosition.BELOW_IMAGE && (dotSize == null || dotSize > 0.dp)) {
            Column {
                Spacer(
                    modifier = Modifier.height(8.dp)
                        .fillMaxWidth()
                )

                OverlayDotsFashion(
                    pagerState = pagerState,
                    imageCount = images.size,
                    dotSize = dotSize,
                    dotPadding = dotPadding,
                    selectedDotColor = selectedDotColor,
                    unselectedDotColor = unselectedDotColor,
                    indicatorDuration = autoScrollDelay
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
