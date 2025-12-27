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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun CategoryDietTabsFList(
    onBackClick: () -> Unit = {},
    onTabIndexChanged: (Int) -> Unit = {},
    name: String = "All Food Categories",
    initialSelectedIndex: Int = 0
) {
    var selectedCategoryIndex by remember { mutableStateOf(initialSelectedIndex) }

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

                // Spacer to push the icons to the right
                Spacer(modifier = Modifier.weight(1f))

                // Search Icon
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            clip = true
                        )
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
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            clip = true
                        )
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
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = { index ->
                selectedCategoryIndex = index
                onTabIndexChanged(index)
                onBackClick() // Automatically navigate back after selection
            }
        )
    }
}

@Composable
fun WelcomeContentDietFlist(
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cuisines and dishes",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.customColors.black
        )

        Spacer(modifier = Modifier.height(16.dp))

        FoodCategoriesGridDietFlist(
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = onCategorySelected
        )
    }
}

@Composable
fun FoodCategoriesGridDietFlist(
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    val categories = listOf(
        // First 8 categories that ARE clickable
        FoodCategoryDietFlist("Chicken", R.drawable.all_food, true),
        FoodCategoryDietFlist("Salad", R.drawable.diet_food, true),
        FoodCategoryDietFlist("Mutton", R.drawable.pizzas_food, true),
        FoodCategoryDietFlist("Kebabs", R.drawable.cakes_food, true),
        FoodCategoryDietFlist("Snacks", R.drawable.momos_food, true),
        FoodCategoryDietFlist("Low Calorie", R.drawable.rolls_food, true),
        FoodCategoryDietFlist("Vegan", R.drawable.burgers_food, true),
        FoodCategoryDietFlist("Protein Rich", R.drawable.chole_bhature_food, true),
        // Categories below Protein Rich that are NOT clickable
        FoodCategoryDietFlist("Dessert", R.drawable.dessert_food, false),
        FoodCategoryDietFlist("Veg Meal", R.drawable.veg_meal_food, false),
        FoodCategoryDietFlist("Bowl", R.drawable.bowl_food, false),
        FoodCategoryDietFlist("Sweets", R.drawable.sweets_food, false),
        FoodCategoryDietFlist("Khichdi", R.drawable.khichdi_food, false),
        FoodCategoryDietFlist("Sundae", R.drawable.sundae_food, false),
        FoodCategoryDietFlist("Juice", R.drawable.juice_food, false),
        FoodCategoryDietFlist("Lassi", R.drawable.lassi_food, false),
        FoodCategoryDietFlist("Curd Rice", R.drawable.curd_rice_food, false),
        FoodCategoryDietFlist("Pudding", R.drawable.pudding_food, false),
        FoodCategoryDietFlist("Custard", R.drawable.custard_food, false),
        FoodCategoryDietFlist("Soup", R.drawable.soup_food, false),
        FoodCategoryDietFlist("Brownie", R.drawable.brownie_food, false),
        FoodCategoryDietFlist("Waffles", R.drawable.waffles_food, false),
        FoodCategoryDietFlist("Cold Coffee", R.drawable.cold_coffee_food, false)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            FoodCategoryItemDietFList(
                category = category,
                isSelected = index == selectedCategoryIndex,
                onClick = {
                    // Only perform action if category is clickable
                    if (category.isClickable) {
                        onCategorySelected(index)
                    }
                    // If category is not clickable, do nothing
                }
            )
        }
    }
}

data class FoodCategoryDietFlist(
    val name: String,
    val iconRes: Int,
    val isClickable: Boolean = true // Added parameter to control clickability
)

@Composable
fun FoodCategoryItemDietFList(
    category: FoodCategoryDietFlist,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable(
                enabled = category.isClickable, // Only enable click if category is clickable
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(
                    if (isSelected) MaterialTheme.customColors.lightAccent.copy(alpha = 0.2f)
                    else Color.Transparent
                ),
//                .border(
//                    width = if (isSelected) 2.dp else 1.dp,
//                    color = if (isSelected) MaterialTheme.customColors.primary
//                    else if (!category.isClickable) Color.LightGray // Gray border for non-clickable
//                    else Color.Transparent,
//                    shape = CircleShape
//                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier
                    .size(70.dp)
//                    .alpha(if (!category.isClickable) 0.6f else 1f) // Slightly transparent for non-clickable
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.customColors.primary
//            else if (!category.isClickable) Color.Gray // Gray text for non-clickable
            else MaterialTheme.customColors.black,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(70.dp)
        )

        // Optional: Show "Coming Soon" for non-clickable items
//        if (!category.isClickable) {
//            Text(
//                text = "Coming Soon",
//                fontSize = 8.sp,
//                color = Color.Gray,
//                textAlign = TextAlign.Center,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier.width(70.dp)
//            )
//        }
    }
}