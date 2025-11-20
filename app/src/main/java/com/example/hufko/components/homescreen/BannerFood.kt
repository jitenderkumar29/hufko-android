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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hufko.ui.theme.customColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.isNotEmpty

@Composable
fun BannerFood(
    images: List<Painter>,
    onImageClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 270.dp,
    roundedCornerShape: Dp = 16.dp, // New parameter for dynamic rounded corners
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = MaterialTheme.customColors.linkColor,
    unselectedDotColor: Color = Color.White.copy(alpha = 0.9f),
    backgroundColor: Color = Color.White,
    contentScale: ContentScale = ContentScale.FillBounds,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true
) {
    require(images.isNotEmpty()) { "Images list cannot be empty" }

    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    // Create rounded corner shape
    val bannerShape = RoundedCornerShape(roundedCornerShape)

    // ✅ Auto-scroll effect with smooth animation
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
                .padding(horizontal = 0.dp) // Add horizontal padding for rounded corners
        ) {
            // Pager with images and rounded corners
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(bannerShape) // Apply rounded corners to the pager
            ) { page ->
                Image(
                    painter = images[page],
                    contentDescription = "Banner ${page + 1}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(bannerShape) // Apply rounded corners to each image
                        .clickable { onImageClick(page) },
                    contentScale = contentScale
                )
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
                .fillMaxWidth()
                .background(backgroundColor)
        )

        // Show dots only if dotSize is explicitly set to a positive value
        if (dotSize == null || dotSize > 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 1.dp)
            ) {
                OverlayDotsFashion(
                    pagerState = pagerState,
                    imageCount = images.size,
                    dotSize = dotSize,
                    dotPadding = dotPadding,
                    selectedDotColor = MaterialTheme.customColors.onPrimaryContainer,
                    unselectedDotColor = Color.Gray,
                    indicatorDuration = autoScrollDelay
                )
            }
        }

        Spacer(
            modifier = Modifier.height(3.dp)
                .fillMaxWidth()
                .background(backgroundColor)
        )
    }
}

// Dot in Circle shape
@Composable
fun OverlayDotsFashion(
    pagerState: PagerState,
    imageCount: Int,
    dotSize: Dp? = null,
    dotPadding: Dp? = null,
    selectedDotColor: Color = Color.White,
    unselectedDotColor: Color = Color.Gray,
    indicatorDuration: Long = 3000L
) {
    val coroutineScope = rememberCoroutineScope()

    // Default values when null
    val defaultDotSize = 8.dp
    val defaultDotPadding = 6.dp

    val actualDotSize = dotSize ?: defaultDotSize
    val actualDotPadding = dotPadding ?: defaultDotPadding

    Box(
        modifier = Modifier
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

                // Animate circle slightly bigger when selected
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