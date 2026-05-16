package com.example.hufko.components.homescreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.customColors.header)
                .padding(12.dp)
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

                IconCircle {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.customColors.black
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                IconCircle {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Cart",
                        tint = MaterialTheme.customColors.black
                    )
                }
            }
        }

        WelcomeContent(
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = { index ->
                selectedCategoryIndex = index
                onTabIndexChanged(index)
                onBackClick()
            }
        )
    }
}

@Composable
fun IconCircle(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .shadow(2.dp, CircleShape, clip = true)
            .clip(CircleShape)
            .background(Color.White)
            .border(
                1.dp,
                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
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
    // Get all active categories from CategoryPage sealed class
    val categories = remember {
        getAllCategoryPages()
    }

    // Debug logging
    LaunchedEffect(categories) {
        Log.d("FoodCategoriesGrid", "Total categories loaded: ${categories.size}")
        categories.forEachIndexed { index, category ->
            Log.d("FoodCategoriesGrid", "$index: ${category.title} - ${category.iconUrl}")
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            FoodCategoryItemDynamic(
                category = category,
                isSelected = index == selectedCategoryIndex,
                isClickable = true,
                onClick = {
                    onCategorySelected(index)
                }
            )
        }
    }
}

@Composable
fun FoodCategoryItemDynamic(
    category: CategoryPage,
    isSelected: Boolean = false,
    isClickable: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .then(
                if (isClickable && category.isActive) Modifier.clickable { onClick() }
                else Modifier
            )
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(MaterialTheme.customColors.surface.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = category.iconUrl,
                contentDescription = category.title,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.title,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.customColors.primary
            else if (!category.isActive) MaterialTheme.customColors.black.copy(alpha = 0.5f)
            else MaterialTheme.customColors.black,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(70.dp)
        )

        // Selection indicator for selected category
        if (isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(20.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.customColors.header)
            )
        }

        // Show inactive indicator
        if (!category.isActive) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(30.dp)
                    .background(Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}

// Helper function to get all CategoryPage instances
fun getAllCategoryPages(): List<CategoryPage> {
    return listOf(
        CategoryPage.All,
        CategoryPage.Pizzas,
        CategoryPage.Burgers,
        CategoryPage.Pasta,
        CategoryPage.Noodles,
        CategoryPage.FriedRice,
        CategoryPage.Momos,
        CategoryPage.Rolls,
        CategoryPage.Shake,
        CategoryPage.IceCream,
        CategoryPage.Dosa,
        CategoryPage.Idli,
        CategoryPage.Dessert,
        CategoryPage.Cakes,
        CategoryPage.Pastry,
        CategoryPage.Sandwich,
        CategoryPage.Biryani,
        CategoryPage.NorthIndian,
        CategoryPage.SouthIndian,
        CategoryPage.Chinese,
        CategoryPage.Chicken,
        CategoryPage.Mutton,
        CategoryPage.Fish,
        CategoryPage.Paneer,
        CategoryPage.VegMeal,
        CategoryPage.NonVegMeal,
        CategoryPage.Paratha,
        CategoryPage.Kulche,
        CategoryPage.CholeBhature,
        CategoryPage.CholePoori,
        CategoryPage.AlooPoori,
        CategoryPage.PooriBhaji,
        CategoryPage.PavBhaji,
        CategoryPage.VadaPav,
        CategoryPage.Samosa,
        CategoryPage.Pakoda,
        CategoryPage.BreadPakoda,
        CategoryPage.Chaat,
        CategoryPage.DahiBalle,
        CategoryPage.SevPoori,
        CategoryPage.RajKachori,
        CategoryPage.AlooTikki,
        CategoryPage.Bhel,
        CategoryPage.Poha,
        CategoryPage.Upma,
        CategoryPage.Uttapam,
        CategoryPage.Appam,
        CategoryPage.Bonda,
        CategoryPage.Cutlet,
        CategoryPage.Patty,
        CategoryPage.MasalaMaggi,
        CategoryPage.Shawarma,
        CategoryPage.Kebabs,
        CategoryPage.Tacos,
        CategoryPage.Wings,
        CategoryPage.HotDog,
        CategoryPage.Sub,
        CategoryPage.Pancake,
        CategoryPage.Waffles,
        CategoryPage.Doughnut,
        CategoryPage.Muffin,
        CategoryPage.Cupcake,
        CategoryPage.Brownie,
        CategoryPage.Cheesecake,
        CategoryPage.Cookies,
        CategoryPage.Chocolate,
        CategoryPage.Pie,
        CategoryPage.Tart,
        CategoryPage.Croissant,
        CategoryPage.Pudding,
        CategoryPage.Custard,
        CategoryPage.Mousse,
        CategoryPage.Tiramisu,
        CategoryPage.Sundae,
        CategoryPage.Rasmalai,
        CategoryPage.Rasgulla,
        CategoryPage.GulabJamun,
        CategoryPage.Jalebi,
        CategoryPage.Halwa,
        CategoryPage.Gajak,
        CategoryPage.Khichdi,
        CategoryPage.Dal,
        CategoryPage.Rajma,
        CategoryPage.Korma,
        CategoryPage.MalaiKofta,
        CategoryPage.PaneerKulche,
        CategoryPage.ChurChurNaan,
        CategoryPage.ButterChicken,
        CategoryPage.ChickenHandi,
        CategoryPage.ChilliChicken,
        CategoryPage.ChickenCha,
        CategoryPage.Keema,
        CategoryPage.Nihari,
        CategoryPage.Mushrooms,
        CategoryPage.Salad,
        CategoryPage.Soup,
        CategoryPage.Tea,
        CategoryPage.Coffee,
        CategoryPage.ColdCoffee,
        CategoryPage.Juice,
        CategoryPage.Lassi,
        CategoryPage.Chaach,
        CategoryPage.Bowl,
        CategoryPage.Thali,
        CategoryPage.Pulao,
        CategoryPage.CurdRice,
        CategoryPage.SambarRice,
        CategoryPage.Fafda,
        CategoryPage.Khandvi,
        CategoryPage.Dhokla,
        CategoryPage.Thepla,
        CategoryPage.Namkeen,
        CategoryPage.Diet
    ).filter { it.isActive } // Only return active categories
}

// Legacy data class and composable for backward compatibility
data class FoodCategory(
    val name: String,
    val iconRes: Int
)

@Composable
fun FoodCategoryItem(
    category: FoodCategory,
    isSelected: Boolean = false,
    isClickable: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .then(
                if (isClickable) Modifier.clickable { onClick() }
                else Modifier
            )
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
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