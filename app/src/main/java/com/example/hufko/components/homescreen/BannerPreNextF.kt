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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.hufko.ui.theme.customColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.isNotEmpty
import kotlin.math.absoluteValue
@Composable
fun BannerPreNextF(
    images: List<Painter>,
    onImageClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 240.dp,
    roundedCornerShape: Dp = 18.dp,
    peekPercentage: Float = 0.10f,
    contentScale: ContentScale = ContentScale.Crop,
    autoScrollDelay: Long = 5000,
    autoScrollEnabled: Boolean = true
) {
    require(images.isNotEmpty()) { "Images list cannot be empty" }

    val actualCount = images.size

    // Use a virtual huge count for INFINITE SCROLLING
    val infiniteCount = Int.MAX_VALUE

    // Start in middle so previous/next wrap works perfectly
    val startPage = infiniteCount / 2 - ((infiniteCount / 2) % actualCount)

    val pagerState = rememberPagerState(
        pageCount = { infiniteCount },
        initialPage = startPage
    )

    val coroutineScope = rememberCoroutineScope()

    // AutoScroll
    LaunchedEffect(autoScrollEnabled) {
        if (!autoScrollEnabled) return@LaunchedEffect

        while (true) {
            delay(autoScrollDelay)
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }

    val shape = RoundedCornerShape(roundedCornerShape)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val peekWidth = screenWidth * peekPercentage

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = peekWidth),
            pageSpacing = 1.dp
        ) { page ->

            // Map virtual page index → real index
            val realIndex = page % actualCount

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - page) +
                                    pagerState.currentPageOffsetFraction).absoluteValue

                        val scale = lerp(0.88f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                        scaleX = scale
                        scaleY = scale
                        alpha = lerp(0.6f, 1f, 1f - pageOffset)
                    }
                    .clip(shape)
                    .clickable { onImageClick(realIndex) }
            ) {
                Image(
                    painter = images[realIndex],
                    contentDescription = "Banner ${realIndex + 1}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale
                )
            }
        }
    }
}
