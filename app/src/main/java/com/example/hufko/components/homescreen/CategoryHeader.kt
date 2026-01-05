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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
import androidx.navigation.NavHostController
import com.example.hufko.ui.theme.customColors
import com.example.hufko.R
import com.example.hufko.components.navigation.TabNavigationApp

data class CategoryHeaderClass(
    val id: Int,
    val name: String,
    val iconRes: Int,
    val backgroundColor: Color,
    val selectedColor: Color
)

// ----------------------------------------------------------
// CATEGORY HEADER
// ----------------------------------------------------------
@Composable
fun CategoryHeader(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    onCategorySelected: (String) -> Unit = {}
) {
    val categories = remember {
        listOf(
            CategoryHeaderClass(1, "Food", R.drawable.food, Color.White, Color(0xFFD4AF44)),
            CategoryHeaderClass(2, "Dine Out", R.drawable.dine_out, Color.White, Color(0xFFFF4B33)),
            CategoryHeaderClass(4, "Grocery", R.drawable.grocery, Color.White, Color(0xFF2DC137)),
            CategoryHeaderClass(5, "Shopping", R.drawable.shopping, Color.White, Color(0xFFCA5353)),
            CategoryHeaderClass(6, "Flower", R.drawable.flower, Color.White, Color(0xFF31BC3B)),
            CategoryHeaderClass(7, "Care", R.drawable.care, Color.White, Color(0xFF64CD44)),
            CategoryHeaderClass(8, "Pharma", R.drawable.pharma, Color.White, Color(0xFFC6374D)),
        )
    }

    val selectedCategory = remember { mutableStateOf(categories.first()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.customColors.header)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories.size) { index ->
                    val category = categories[index]
                    CategoryItemHeader(
                        category = category,
                        isSelected = category == selectedCategory.value,
                        navController = navController,
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
    navController: NavHostController? = null,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(90.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) category.selectedColor else category.backgroundColor)
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
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ----------------------------------------------------------
// MAIN CATEGORY SCREEN — SMOOTH HEADER HIDE/SHOW
// ----------------------------------------------------------
@Composable
fun CategoryScreen(navController: NavHostController? = null,
                   onOpenFashion: () -> Unit) {

    val selectedCategory = remember { mutableStateOf("Food") }

    val scrollOffset = remember { mutableStateOf(0f) }
    val isHeaderVisible = remember { mutableStateOf(true) }

    val savedStateHandle =
        navController?.currentBackStackEntry?.savedStateHandle

    val accountOpen = savedStateHandle?.get<Boolean>("accountOpen") ?: false

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y

                scrollOffset.value = (scrollOffset.value - delta).coerceAtLeast(0f)

                when {
                    delta < 0f -> isHeaderVisible.value = false      // hide fast
                    delta > 0f -> isHeaderVisible.value = true       // show fast
                }

                if (scrollOffset.value <= 0f) isHeaderVisible.value = true

                return Offset.Zero
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.customColors.header)
    ) {
        // Smooth + Fast Header Animation
        AnimatedVisibility(
            visible = isHeaderVisible.value,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(120)
            ) + fadeIn(tween(120)),
            exit = slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(120)
            ) + fadeOut(tween(120))
        ) {
            CategoryHeader(
                navController = navController,
                onCategorySelected = { name ->
                    selectedCategory.value = name
                    if (name == "Fashion") onOpenFashion()
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            when (selectedCategory.value) {
                "Food" -> FoodScreen(navController = navController)
                "Dine Out" -> DineOutScreen(navController = navController)
                "Pay" -> PayScreen(navController = navController)
                "Grocery" -> GroceryScreen(navController = navController)
                "Shopping" -> ShoppingScreen(navController = navController)
                "Flower" -> FlowerScreen(navController = navController)
                "Care" -> CareScreen(navController = navController)
                "Pharma" -> PharmaScreen(navController = navController)
            }
        }
    }
}

// ----------------------------------------------------------
// CATEGORY SCREENS
// ----------------------------------------------------------
@Composable fun FoodScreen(navController: NavHostController? = null) {
    TabNavigationApp(navController = navController)
}

@Composable fun DineOutScreen(navController: NavHostController? = null) {
    ScreenTemplate("Dine Out", navController = navController)
}

@Composable fun PayScreen(navController: NavHostController? = null) {
    ScreenTemplate("Pay", navController = navController)
}

@Composable fun GroceryScreen(navController: NavHostController? = null) {
    ScreenTemplate("Grocery", navController = navController)
}

@Composable fun ShoppingScreen(navController: NavHostController? = null) {
    ScreenTemplate("Shopping", navController = navController)
}

@Composable fun FlowerScreen(navController: NavHostController? = null) {
    ScreenTemplate("Flower", navController = navController)
}

@Composable fun CareScreen(navController: NavHostController? = null) {
    ScreenTemplate("Care", navController = navController)
}

@Composable fun PharmaScreen(navController: NavHostController? = null) {
    ScreenTemplate("Pharma", navController = navController)
}

@Composable
fun ScreenTemplate(
    title: String,
    navController: NavHostController? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("$title Screen Loaded!")

        // Example of using navController for navigation
        // You can add navigation buttons here if needed
        // Example:
        // Button(
        //     onClick = { navController?.navigate("someRoute") }
        // ) {
        //     Text("Navigate to Details")
        // }
    }
}