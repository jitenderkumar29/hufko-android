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
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun CategoryTabsFList(
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
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
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
                            // Update the parent with selected index before navigating back
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

                // Wishlist Icon
//                Box(
//                    modifier = Modifier
//                        .size(35.dp)
//                        .shadow(
//                            elevation = 2.dp,
//                            shape = CircleShape,
//                            clip = true
//                        )
//                        .clip(CircleShape)
//                        .background(Color.White)
//                        .border(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
//                            shape = CircleShape
//                        ),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_wishlist_outline),
//                        contentDescription = "Wishlist",
//                        tint = MaterialTheme.customColors.black,
//                        modifier = Modifier.size(20.dp)
//                    )
//                }

//                Spacer(modifier = Modifier.width(12.dp))

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

        WelcomeContent(
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
fun WelcomeContent(
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

        FoodCategoriesGrid(
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = onCategorySelected
        )
    }
}

@Composable
fun FoodCategoriesGrid(
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    val categories = listOf(
        FoodCategory("All", R.drawable.all_food),
        FoodCategory("Diet", R.drawable.diet_food),
        FoodCategory("Pizzas", R.drawable.pizzas_food),
        FoodCategory("Cakes", R.drawable.cakes_food),
        FoodCategory("Momos", R.drawable.momos_food),
        FoodCategory("Rolls", R.drawable.rolls_food),
        FoodCategory("Burgers", R.drawable.burgers_food),
        FoodCategory("Chole Bhature", R.drawable.chole_bhature_food),
        FoodCategory("Salad", R.drawable.salad_food),
        FoodCategory("Patty", R.drawable.patty_food),
        FoodCategory("Chinese", R.drawable.chinese_food),
        FoodCategory("Ice Cream", R.drawable.ice_cream_food),
        FoodCategory("Appam", R.drawable.appam_food),
        FoodCategory("Bath", R.drawable.bath_food),
        FoodCategory("Bonda", R.drawable.bonda_food),
        FoodCategory("Cutlet", R.drawable.cutlet_food),
        FoodCategory("Dessert", R.drawable.dessert_food),
        FoodCategory("Dhokla", R.drawable.dhokla_food),
        FoodCategory("Dosa", R.drawable.dosa_food),
        FoodCategory("Dholda", R.drawable.dholda_food),
        FoodCategory("Gulab Jamun", R.drawable.gulab_jamun_food),
        FoodCategory("Idli", R.drawable.idli_food),
        FoodCategory("Biryani", R.drawable.biryani_food),
        FoodCategory("Thali", R.drawable.thali_food),
        FoodCategory("Chicken", R.drawable.chicken_food),
        FoodCategory("Veg Meal", R.drawable.veg_meal_food),
        FoodCategory("North Indian", R.drawable.north_indian_food),
        FoodCategory("Paneer", R.drawable.paneer_food),
        FoodCategory("Fried Rice", R.drawable.fried_rice_food),
        FoodCategory("Noodles", R.drawable.noodles_food),
        FoodCategory("Paratha", R.drawable.paratha_food),
        FoodCategory("Shawarma", R.drawable.shawarma_food),
        FoodCategory("South Indian", R.drawable.south_indian_food),
        FoodCategory("Aloo Tikki", R.drawable.aloo_tikki_food),
        FoodCategory("Pasta", R.drawable.pasta_food),
        FoodCategory("Pastry", R.drawable.pastry_food),
        FoodCategory("Pav Bhaji", R.drawable.pav_bhaji_food),
        FoodCategory("Sandwich", R.drawable.sandwich_food),
        FoodCategory("Shake", R.drawable.shake_food),
//        FoodCategory("See All", R.drawable.see_all_food)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            FoodCategoryItem(
                category = category,
                isSelected = index == selectedCategoryIndex,
                onClick = {
                    onCategorySelected(index)
                }
            )
        }
    }
}

data class FoodCategory(
    val name: String,
    val iconRes: Int
)

@Composable
fun FoodCategoryItem(
    category: FoodCategory,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(
                    if (isSelected) MaterialTheme.customColors.lightAccent.copy(alpha = 0.2f)
                    else Color.Transparent
                )
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) MaterialTheme.customColors.primary
                    else Color.Transparent,
//                    else MaterialTheme.customColors.lightAccent.copy(alpha = 0.3f),
                    shape = CircleShape
                ),
                    contentAlignment = Alignment . Center
        ) {
            Image(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.customColors.primary
            else MaterialTheme.customColors.black,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(70.dp)
        )
    }
}

@Composable
fun FoodItemCard(name: String, price: String, iconRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { /* Handle food item click */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.customColors.lightAccent.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = name,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.customColors.black
                    )
                    Text(
                        text = price,
                        fontSize = 16.sp,
                        color = MaterialTheme.customColors.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(
                onClick = { /* Add to cart */ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                    contentDescription = "Add to Cart",
                    tint = MaterialTheme.customColors.primary
                )
            }
        }
    }
}