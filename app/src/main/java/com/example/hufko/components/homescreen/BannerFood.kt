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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hufko.ui.theme.customColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.isNotEmpty

enum class DotPosition {
    OVERLAY,      // Dots overlay on bottom of image
    BELOW_IMAGE   // Dots below the image
}

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
    dotPosition: DotPosition = DotPosition.BELOW_IMAGE, // New parameter for dot position
    overlayGradient: Boolean = true // Gradient effect for overlay dots
) {
    require(images.isNotEmpty()) { "Images list cannot be empty" }

    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    val bannerShape = RoundedCornerShape(roundedCornerShape)

    // Auto-scroll effect
    LaunchedEffect(autoScrollEnabled) {
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
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            // Pager with images and rounded corners
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

            // Overlay dots - shown only when dotPosition is OVERLAY
            if (dotPosition == DotPosition.OVERLAY && (dotSize == null || dotSize > 0.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (overlayGradient) {
                                Modifier.drawWithContent {
                                    drawContent()
                                    // Draw gradient overlay for better dot visibility
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
                        selectedDotColor = Color.White, // White for better visibility on overlay
                        unselectedDotColor = Color.White.copy(alpha = 0.9f),
                        indicatorDuration = autoScrollDelay,
                        modifier = Modifier.padding(bottom = 12.dp) // Padding from bottom
                    )
                }
            }
        }

        // Below-image dots - shown only when dotPosition is BELOW_IMAGE
        if (dotPosition == DotPosition.BELOW_IMAGE && (dotSize == null || dotSize > 0.dp)) {
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

// Enhanced Dot indicator with modifier support
@Composable
fun OverlayDotsFashion(
    pagerState: PagerState,
    imageCount: Int,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = Color.White,
    unselectedDotColor: Color = Color.Gray,
    indicatorDuration: Long = 3000L,
    modifier: Modifier = Modifier // Added modifier parameter
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