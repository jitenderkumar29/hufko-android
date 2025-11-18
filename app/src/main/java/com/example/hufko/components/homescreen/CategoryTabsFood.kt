package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors
import androidx.compose.ui.text.style.TextAlign

// Sealed class for different category pages
sealed class CategoryPage(val title: String, val iconRes: Int) {
    object MasalaBhelpuri : CategoryPage("Masala Bhelpuri", R.drawable.masala_bhelpuri)
    object DoubleEggSandwich : CategoryPage("Double Egg Sandwich", R.drawable.double_egg_sandwich)
    object TiramisuOrder : CategoryPage("Tiramisu Order", R.drawable.tiramisu_order)
    object ChickenPuff : CategoryPage("Chicken Puff", R.drawable.chicken_puff)
    object VegPuffOrder : CategoryPage("Veg Puff Order", R.drawable.veg_puff_order)
    object ChocoWalnut : CategoryPage("Choco Walnut", R.drawable.choco_walnut)
    object MasalaMaggi : CategoryPage("Masala Maggi", R.drawable.masala_maggi)
    object BullsEyeEgg : CategoryPage("Bull's-eye Egg", R.drawable.bulls_eye_egg)
    object PlainCurdMatka : CategoryPage("Plain Curd Matka", R.drawable.plain_curd_matka)
    object MintMojitoFizzyCooler : CategoryPage("Mint Mojito", R.drawable.mint_mojito)
    object PepperMaggi : CategoryPage("Pepper Maggi", R.drawable.pepper_maggi)
    object ColdCoffee : CategoryPage("Cold Coffee", R.drawable.cold_coffee)
}

data class FoodItem(
    val id: Int,
    val name: String,
    val description: String? = null,
    val imageRes: Int,
    val isVeg: Boolean = true,
    val category: String = "All"
)
@Composable
fun CategoryTabsFood(
    onCategorySelected: (CategoryPage) -> Unit = {}
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val categoryPages = listOf(
        CategoryPage.MasalaBhelpuri,
        CategoryPage.DoubleEggSandwich,
        CategoryPage.TiramisuOrder,
        CategoryPage.ChickenPuff,
        CategoryPage.VegPuffOrder,
        CategoryPage.ChocoWalnut,
        CategoryPage.MasalaMaggi,
        CategoryPage.BullsEyeEgg,
        CategoryPage.PlainCurdMatka,
        CategoryPage.MintMojitoFizzyCooler,
        CategoryPage.PepperMaggi,
        CategoryPage.ColdCoffee
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDF6FF),
                        Color(0xFFFDFEFF)
                    )
                )
            )
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent, // Make transparent to show gradient
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.onPrimaryContainer
                )
            }
        ) {
            categoryPages.forEachIndexed { index, categoryPage ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onCategorySelected(categoryPage)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .background(Color.Transparent) // Make tab transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = categoryPage.iconRes),
                            contentDescription = categoryPage.title,
                            modifier = Modifier.size(24.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = categoryPage.title,
                            fontSize = 12.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.customColors.onPrimaryContainer
                            } else {
                                MaterialTheme.customColors.black
                            },
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }

        // Show content for each tab - also with gradient background
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFEDF6FF),
                            Color(0xFFFDFEFF)
                        )
                    )
                )
        ) {
            when (selectedTabIndex) {
                0 -> MasalaBhelpuriCategoryPage()
                1 -> DoubleEggSandwichCategoryPage()
                2 -> TiramisuOrderCategoryPage()
                3 -> ChickenPuffCategoryPage()
                4 -> VegPuffOrderCategoryPage()
                5 -> ChocoWalnutCategoryPage()
                6 -> MasalaMaggiCategoryPage()
                7 -> BullsEyeEggCategoryPage()
                8 -> PlainCurdMatkaCategoryPage()
                9 -> MintMojitoFizzyCoolerCategoryPage()
                10 -> PepperMaggiCategoryPage()
                11 -> ColdCoffeeCategoryPage()
                else -> MasalaBhelpuriCategoryPage()
            }
        }
    }
}


// Safe version with fallback handling
@Composable
fun CategoryTabsFoodSafe(
    onCategorySelected: (CategoryPage) -> Unit = {}
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val categoryPages = listOf(
        CategoryPage.MasalaBhelpuri,
        CategoryPage.DoubleEggSandwich,
        CategoryPage.TiramisuOrder,
        CategoryPage.ChickenPuff,
        CategoryPage.VegPuffOrder,
        CategoryPage.ChocoWalnut,
        CategoryPage.MasalaMaggi,
        CategoryPage.BullsEyeEgg,
        CategoryPage.PlainCurdMatka,
        CategoryPage.MintMojitoFizzyCooler,
        CategoryPage.PepperMaggi,
        CategoryPage.ColdCoffee
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.customColors.skyBlue,
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.onPrimaryContainer
                )
            }
        ) {
            categoryPages.forEachIndexed { index, categoryPage ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onCategorySelected(categoryPage)
                    },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        // Safe image loading - if the resource doesn't exist, it will show empty space
                        // but won't crash
                        Image(
                            painter = painterResource(id = categoryPage.iconRes),
                            contentDescription = categoryPage.title,
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = categoryPage.title,
                            fontSize = 12.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.customColors.onPrimaryContainer
                            } else {
                                MaterialTheme.customColors.black
                            },
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }

        // Show content for each tab
        when (selectedTabIndex) {
            0 -> MasalaBhelpuriCategoryPage()
            1 -> DoubleEggSandwichCategoryPage()
            2 -> TiramisuOrderCategoryPage()
            3 -> ChickenPuffCategoryPage()
            4 -> VegPuffOrderCategoryPage()
            5 -> ChocoWalnutCategoryPage()
            6 -> MasalaMaggiCategoryPage()
            7 -> BullsEyeEggCategoryPage()
            8 -> PlainCurdMatkaCategoryPage()
            9 -> MintMojitoFizzyCoolerCategoryPage()
            10 -> PepperMaggiCategoryPage()
            11 -> ColdCoffeeCategoryPage()
        }
    }
}

// Simple version with text-only fallback
@Composable
fun CategoryTabsFoodSimple(
    onCategorySelected: (CategoryPage) -> Unit = {}
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val categoryPages = listOf(
        CategoryPage.MasalaBhelpuri,
        CategoryPage.DoubleEggSandwich,
        CategoryPage.TiramisuOrder,
        CategoryPage.ChickenPuff,
        CategoryPage.VegPuffOrder,
        CategoryPage.ChocoWalnut,
        CategoryPage.MasalaMaggi,
        CategoryPage.BullsEyeEgg,
        CategoryPage.PlainCurdMatka,
        CategoryPage.MintMojitoFizzyCooler,
        CategoryPage.PepperMaggi,
        CategoryPage.ColdCoffee
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.customColors.skyBlue,
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.onPrimaryContainer
                )
            }
        ) {
            categoryPages.forEachIndexed { index, categoryPage ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onCategorySelected(categoryPage)
                    },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = categoryPage.title,
                            fontSize = 12.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.customColors.onPrimaryContainer
                            } else {
                                MaterialTheme.customColors.black
                            },
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // Show content for each tab
        when (selectedTabIndex) {
            0 -> MasalaBhelpuriCategoryPage()
            1 -> DoubleEggSandwichCategoryPage()
            2 -> TiramisuOrderCategoryPage()
            3 -> ChickenPuffCategoryPage()
            4 -> VegPuffOrderCategoryPage()
            5 -> ChocoWalnutCategoryPage()
            6 -> MasalaMaggiCategoryPage()
            7 -> BullsEyeEggCategoryPage()
            8 -> PlainCurdMatkaCategoryPage()
            9 -> MintMojitoFizzyCoolerCategoryPage()
            10 -> PepperMaggiCategoryPage()
            11 -> ColdCoffeeCategoryPage()
        }
    }
}

// Add the missing category page composables

@Composable
fun MasalaBhelpuriCategoryPage() {
    // Add your content here
    Text("Masala Bhelpuri Content")
}

@Composable
fun DoubleEggSandwichCategoryPage() {
    Text("Double Egg Sandwich Content")
}

@Composable
fun TiramisuOrderCategoryPage() {
    Text("Tiramisu Order Content")
}

@Composable
fun ChickenPuffCategoryPage() {
    Text("Chicken Puff Content")
}

@Composable
fun VegPuffOrderCategoryPage() {
    Text("Veg Puff Order Content")
}

@Composable
fun ChocoWalnutCategoryPage() {
    Text("Choco Walnut Content")
}

@Composable
fun MasalaMaggiCategoryPage() {
    Text("Masala Maggi Content")
}

@Composable
fun BullsEyeEggCategoryPage() {
    Text("Bull's Eye Egg Content")
}

@Composable
fun PlainCurdMatkaCategoryPage() {
    Text("Plain Curd Matka Content")
}

@Composable
fun MintMojitoFizzyCoolerCategoryPage() {
    Text("Mint Mojito Fizzy Cooler Content")
}

@Composable
fun PepperMaggiCategoryPage() {
    Text("Pepper Maggi Content")
}

@Composable
fun ColdCoffeeCategoryPage() {
    Text("Cold Coffee Content")
}

@Composable
fun MainScreen(navController: NavHostController) {
    var currentPage by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.fillMaxWidth()) {
        // Use one of these implementations:
        CategoryTabsFood(
            onCategorySelected = { categoryPage ->
                currentPage = when (categoryPage) {
                    CategoryPage.MasalaBhelpuri -> 0
                    CategoryPage.DoubleEggSandwich -> 1
                    CategoryPage.TiramisuOrder -> 2
                    CategoryPage.ChickenPuff -> 3
                    CategoryPage.VegPuffOrder -> 4
                    CategoryPage.ChocoWalnut -> 5
                    CategoryPage.MasalaMaggi -> 6
                    CategoryPage.BullsEyeEgg -> 7
                    CategoryPage.PlainCurdMatka -> 8
                    CategoryPage.MintMojitoFizzyCooler -> 9
                    CategoryPage.PepperMaggi -> 10
                    CategoryPage.ColdCoffee -> 11
                }
            }
        )
    }
}