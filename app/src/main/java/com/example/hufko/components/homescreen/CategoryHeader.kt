package com.example.hufko.components.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.ui.theme.customColors
import com.example.hufko.R
import com.example.hufko.components.navigation.TabNavigationApp
import kotlin.collections.first
import kotlin.ranges.coerceAtLeast

data class CategoryHeaderClass(
    val id: Int,
    val name: String,
    val iconRes: Int,
    val backgroundColor: Color,
    val selectedColor: Color
)

@Composable
fun CategoryHeader(
    modifier: Modifier = Modifier,
    onCategorySelected: (String) -> Unit = {}
) {
    val categories = remember {
        listOf(
            CategoryHeaderClass(
                1, "Food", R.drawable.food, Color(0xFFFFFFFF), selectedColor = Color(
                    0xFFD4AF44
                )
            ),
            CategoryHeaderClass(
                2, "Dine Out", R.drawable.dine_out, Color(0xFFFFFFFF), selectedColor = Color(
                    0xFFFF4B33
                )
            ),
//        CategoryHeaderClass(
//                3, "Pay", R.drawable.pay_food, Color(0xFFFFFFFF), selectedColor = Color(
//                    0xFF3A81F1
//                )
//            ),
            CategoryHeaderClass(
                4, "Grocery", R.drawable.grocery, Color(0xFFFFFFFF), selectedColor = Color(
                    0xFF2DC137
                )
            ),
            CategoryHeaderClass(
                5, "Shopping", R.drawable.shopping, Color(0xFFFFFFFF), selectedColor = Color(
                    0xFFCA5353
                )
            ),
            CategoryHeaderClass(
                6,
                "Flower",
                R.drawable.flower,
                Color(0xFFFFFFFF),
                selectedColor = Color(
                    0xFF31BC3B
                )
            ),
//            CategoryHeaderClass(4, "Economy", R.drawable.deal_economy, Color(0xFFFFF9C4), selectedColor = Color(
//                0xFFD2C54C
//            )
//            ),
            CategoryHeaderClass(
                7, "Care", R.drawable.care, Color(0xFFFFFFFF), selectedColor = Color(
                    0xFF64CD44
                )
            ),
            CategoryHeaderClass(
                8, "Pharma", R.drawable.pharma, Color(0xFFFFFFFF), selectedColor = Color(
                    0xFFC6374D
                )
            ),

        )
    }

    val selectedCategory = remember { mutableStateOf(categories.first()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        MaterialTheme.customColors.lightAccent,
//                        Color(0xFF9BCDFE)
//                    )
//                )
//            )
            .background(MaterialTheme.customColors.header)

    ) {
        // Fixed height for the LazyRow container to avoid infinite constraints
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            )       {
                items(categories.size) { index ->
                    val category = categories[index]
                    CategoryItemHeader(
                        category = category,
                        isSelected = category == selectedCategory.value,
                        onClick = {
                            selectedCategory.value = category
                            onCategorySelected(category.name)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItemHeader(
    category: CategoryHeaderClass,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) category.selectedColor else category.backgroundColor)
//            .background(category.backgroundColor)
//            .border(
//                width = if (isSelected) 5.dp else 0.dp,
//                color = if (isSelected) MaterialTheme.customColors.white else Color.Transparent,
//                shape = RoundedCornerShape(12.dp)
//            )
    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(top = 2.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) category.selectedColor else category.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = category.name,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp, top = 0.dp)
        )
    }
}

// Main composable that handles category selection and displays appropriate content

@Composable
fun CategoryScreen(onOpenFashion: () -> Unit) {
    val selectedCategory = remember { mutableStateOf("Food") }

    val scrollOffset = remember { mutableStateOf(0f) }
    val isHeaderVisible = remember { mutableStateOf(true) }

    val density = LocalDensity.current

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                scrollOffset.value = (scrollOffset.value - delta).coerceAtLeast(0f)
                if (delta < 0f) isHeaderVisible.value = false
                if (delta > 0f || scrollOffset.value <= 0f) isHeaderVisible.value = true
                return Offset.Zero
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.customColors.white)) {
        AnimatedVisibility(
            visible = isHeaderVisible.value,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 200)
            ) + fadeIn(animationSpec = tween(durationMillis = 200)),
            exit = slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(durationMillis = 120)
            ) + fadeOut(animationSpec = tween(durationMillis = 120))
        ) {
            CategoryHeader(
                onCategorySelected = { categoryName ->
                    selectedCategory.value = categoryName
                    if (categoryName == "Fashion") {
                        onOpenFashion() // 🚀 navigate to new screen
                    }
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            when (selectedCategory.value) {
                "Food" -> FoodScreen()
                "Dine Out" -> DineOutScreen()
                "Pay" -> PayScreen()
                "Grocery" -> GroceryScreen()
                "Shopping" -> ShoppingScreen()
                "Flower" -> FlowerScreen()
                "Care" -> CareScreen()
                "Pharma" -> PharmaScreen()
            }
        }
    }
}


//
//@Composable
//fun CategoryScreen() {
//    val selectedCategory = remember { mutableStateOf("Shopping") }
//
//    // Accumulated scroll offset (px). Keeping >= 0 so we can force header visible at top.
//    val scrollOffset = remember { mutableStateOf(0f) }
//    val isHeaderVisible = remember { mutableStateOf(true) }
//
//    val density = LocalDensity.current
//    val hideThresholdPx = with(density) { 50.dp.toPx() } // still useful if you ever want to add smooth hide
//
//    val nestedScrollConnection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                val delta = available.y
//
//                // Track scroll offset so we know when we're at top
//                scrollOffset.value = (scrollOffset.value - delta).coerceAtLeast(0f)
//
//                when {
//                    delta < 0f -> {
//                        // User is scrolling UP (swipe up) → hide header immediately
//                        isHeaderVisible.value = false
//                    }
//                    delta > 0f -> {
//                        // User is scrolling DOWN (swipe down) → show header immediately
//                        isHeaderVisible.value = true
//                    }
//                }
//
//                // Force header visible when at very top
//                if (scrollOffset.value <= 0f) {
//                    isHeaderVisible.value = true
//                }
//
//                return Offset.Zero
//            }
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.customColors.white)) {
//        AnimatedVisibility(
//            visible = isHeaderVisible.value,
//            enter = slideInVertically(
//                initialOffsetY = { -it },
//                animationSpec = tween(durationMillis = 200) // faster show
//            ) + fadeIn(animationSpec = tween(durationMillis = 200)),
//            exit = slideOutVertically(
//                targetOffsetY = { -it },
//                animationSpec = tween(durationMillis = 120) // very fast hide
//            ) + fadeOut(animationSpec = tween(durationMillis = 120))
//        ) {
//            CategoryHeader(
//                onCategorySelected = { categoryName ->
//                    selectedCategory.value = categoryName
//                }
//            )
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .nestedScroll(nestedScrollConnection)
//        ) {
//            when (selectedCategory.value) {
//                "Shopping" -> ShoppingScreen()
//                "Fashion" -> FashionScreen()
//                "Beauty" -> BeautyScreen()
//                "Economy" -> EconomyScreen()
//                "Deals" -> DealsScreen()
//                "Bridal" -> BrideScreen()
//                "Jewellery" -> JewelleryScreen()
////                "Groom" -> GroomScreen()
//                "Airport" -> AirportScreen()
//                "Electric" -> ElectricScreen()
//                "Industry" -> IndustryScreen()
//                "Wholesale" -> WholesaleScreen()
//                "Sell" -> SellScreen()
//                "Medical" -> MedicalScreen()
//                "Fresh" -> FreshScreen()
//                "Pay" -> PayScreen()
//                else -> ShoppingScreen()
//            }
//        }
//    }
//}

// Separate screen composables for each category
@Composable
fun FoodScreen() {
    TabNavigationApp()
}
@Composable
fun DineOutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dine Out",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Dine Out!")
        // Add beauty-specific content
    }
}
@Composable
fun PayScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pay",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Pay!")
        // Add beauty-specific content
    }
}

@Composable
fun GroceryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Grocery",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Grocery products and essentials!")
        // Add beauty-specific content
    }
}

@Composable
fun ShoppingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Budget-friendly options and Shopping deals!")
    }
}

@Composable
fun FlowerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Flower",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Special offers and limited-time deals!")
    }
}

@Composable
fun CareScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Care",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Care Services!")
    }
}

@Composable
fun PharmaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pharma",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Pharma Services Screen!")
    }
}
