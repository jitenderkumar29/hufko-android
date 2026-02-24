// homescreen/CategoryDietTabsFList.kt
package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun CategoryDietTabsFList(
    navController: NavHostController? = null,
    onBackClick: () -> Unit = {},
    onTabIndexChanged: (Int) -> Unit = {},
    name: String = "Diet Categories",
    initialSelectedIndex: Int = 0
) {
    var selectedCategoryIndex by remember { mutableStateOf(initialSelectedIndex) }

    // Get all diet categories from savedStateHandle if available
    val allDietCategories = remember(navController?.currentBackStackEntry) {
        navController?.currentBackStackEntry?.savedStateHandle?.get<List<DietCategoryPage>>("allDietCategories")
            ?: listOf(
                DietCategoryPage.Chicken,
                DietCategoryPage.Salad,
                DietCategoryPage.Mutton,
                DietCategoryPage.Kebabs,
                DietCategoryPage.HealthySnacks,
                DietCategoryPage.LowCalorie,
                DietCategoryPage.Vegan,
                DietCategoryPage.ProteinRich,

                DietCategoryPage.Dessert,
                DietCategoryPage.VegMeal,
                DietCategoryPage.Bowl,
                DietCategoryPage.Sweets,
                DietCategoryPage.Khichdi,
                DietCategoryPage.Sundae,
                DietCategoryPage.Juice,
                DietCategoryPage.Lassi,
                DietCategoryPage.CurdRice,
                DietCategoryPage.Pudding,
                DietCategoryPage.Custard,
                DietCategoryPage.Soup,
                DietCategoryPage.Brownie,
                DietCategoryPage.Waffles,
                DietCategoryPage.ColdCoffee,
                // Additional diet categories
                DietCategoryPage.GrilledChicken,
                DietCategoryPage.SteamedFish,
                DietCategoryPage.QuinoaBowl,
                DietCategoryPage.AvocadoToast,
                DietCategoryPage.GreenSmoothie,
                DietCategoryPage.Oatmeal,
                DietCategoryPage.GreekYogurt,
                DietCategoryPage.EggWhiteOmelette,
                DietCategoryPage.TunaSalad,
                DietCategoryPage.LentilSoup,
                DietCategoryPage.CottageCheese,
                DietCategoryPage.SproutsSalad,
                DietCategoryPage.BrownRiceBowl,
                DietCategoryPage.SteamedVeggies,
                DietCategoryPage.FruitBowl,
                DietCategoryPage.DetoxWater,
                DietCategoryPage.HerbalTea,
                DietCategoryPage.ProteinBar,
                DietCategoryPage.BoiledEggs,
                DietCategoryPage.HummusPlate,
                DietCategoryPage.SushiRolls,
                DietCategoryPage.TofuStirFry,
                DietCategoryPage.ChiaPudding,
                DietCategoryPage.MilletBowl,
            )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColors.white)
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.customColors.header)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            onTabIndexChanged(selectedCategoryIndex)
                            onBackClick()
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.customColors.white
                )

                Spacer(modifier = Modifier.weight(1f))

                // Search Icon
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .shadow(2.dp, CircleShape, clip = true)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.customColors.black,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Cart Icon
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .shadow(2.dp, CircleShape, clip = true)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Cart",
                        tint = MaterialTheme.customColors.black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        WelcomeContentDietFlist(
            allDietCategories = allDietCategories,
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = { index ->
                selectedCategoryIndex = index
                // Pass the selected index back to the main screen
                navController?.previousBackStackEntry?.savedStateHandle?.set(
                    "selectedDietTabFromSeeAll",
                    index
                )
                onTabIndexChanged(index)
                onBackClick()
            }
        )
    }
}

@Composable
fun WelcomeContentDietFlist(
    allDietCategories: List<DietCategoryPage>,
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Diet Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.customColors.black
        )

        Spacer(modifier = Modifier.height(16.dp))

        DietCategoriesGrid(
            allDietCategories = allDietCategories,
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = onCategorySelected
        )
    }
}

@Composable
fun DietCategoriesGrid(
    allDietCategories: List<DietCategoryPage>,
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    // ALL CATEGORIES ARE NOW CLICKABLE - removed the clickable restriction
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(allDietCategories) { index, categoryPage ->
            // Skip the "See All" page in the grid
            if (categoryPage !is DietCategoryPage.SeeAll) {
                // All items are now clickable - removed the isClickable condition
                DietCategoryItem(
                    categoryPage = categoryPage,
                    isSelected = index == selectedCategoryIndex,
                    onClick = {
                        onCategorySelected(index) // All items can now be clicked
                    }
                )
            }
        }
    }
}

@Composable
fun DietCategoryItem(
    categoryPage: DietCategoryPage,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}  // Removed isClickable parameter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }  // Always clickable
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = categoryPage.iconRes),
                contentDescription = categoryPage.title,
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = categoryPage.title,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.customColors.primary
            else MaterialTheme.customColors.black,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(70.dp)
        )

        // Removed the "New" indicator since all items are now clickable
    }
}