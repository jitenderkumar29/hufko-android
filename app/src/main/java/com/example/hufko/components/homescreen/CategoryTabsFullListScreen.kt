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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun CategoryTabsFullListScreen(
    navController: NavHostController,
    onCategorySelected: (CategoryPage, Int) -> Unit
) {
    // Get categories from savedStateHandle or use default if empty
    val allCategories = remember(navController.currentBackStackEntry) {
        @Suppress("UNCHECKED_CAST")
        val categories = navController.currentBackStackEntry?.savedStateHandle?.get<List<CategoryPage>>("allCategories")
        Log.d("CategoryTabsFullListScreen", "Retrieved categories: ${categories?.size ?: 0}")
        categories ?: getSeeAllCategoryPages() // Fallback to all categories if none passed
    }

    var isLoading by remember { mutableStateOf(allCategories.isEmpty()) }

    // Log when categories are loaded
    LaunchedEffect(allCategories) {
        if (allCategories.isNotEmpty()) {
            isLoading = false
            Log.d("CategoryTabsFullListScreen", "Categories loaded: ${allCategories.size}")
            allCategories.forEachIndexed { index, category ->
                Log.d("CategoryTabsFullListScreen", "Category $index: ${category.title} - ${category.iconUrl}")
            }
        } else {
            Log.e("CategoryTabsFullListScreen", "No categories received from savedStateHandle!")
            isLoading = true
        }
    }

    val currentMainTabIndex = remember(navController.currentBackStackEntry) {
        navController.currentBackStackEntry?.savedStateHandle?.get<Int>("currentMainTabIndex")
            ?: 0
    }

    var selectedCategoryIndex by remember { mutableIntStateOf(currentMainTabIndex) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColors.white)
    ) {
        // ---------- HEADER ----------
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
                // Back button
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Title
                Text(
                    text = "All Categories",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.customColors.white
                )

                Spacer(modifier = Modifier.weight(1f))

                // Search
                IconCircleScreen {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.customColors.black
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Cart
                IconCircleScreen {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Cart",
                        tint = MaterialTheme.customColors.black
                    )
                }
            }
        }

        // Main Content
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

            // Show loading indicator while categories are loading
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.customColors.primary
                    )
                }
            } else if (allCategories.isEmpty()) {
                // Show error message if no categories
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No categories available",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            } else {
                // Categories Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(allCategories) { index, category ->
                        val isAlreadySelected = index == currentMainTabIndex

                        FoodCategoryItem2(
                            name = category.title,
                            iconUrl = category.iconUrl,
                            isSelected = index == selectedCategoryIndex,
                            isClickable = true,
                            isAlreadySelected = isAlreadySelected,
                            onClick = {
                                selectedCategoryIndex = index
                                // Store the selected index in savedStateHandle
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    "selectedTabFromSeeAll",
                                    index
                                )
                                // Notify parent
                                onCategorySelected(category, index)
                                // Navigate back
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IconCircleScreen(content: @Composable () -> Unit) {
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
fun FoodCategoryItem2(
    name: String,
    iconUrl: String,
    isSelected: Boolean = false,
    isClickable: Boolean = true,
    isAlreadySelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var imageLoading by remember { mutableStateOf(true) }
    var imageError by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .then(
                if (isClickable) Modifier.clickable { onClick() }
                else Modifier
            )
    ) {
        // Icon Container
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .background(MaterialTheme.customColors.surface.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            // Show loading indicator
            if (imageLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.customColors.primary
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iconUrl)
                    .crossfade(true)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .build(),
                contentDescription = name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                onLoading = {
                    imageLoading = true
                    imageError = false
                },
                onSuccess = {
                    imageLoading = false
                    imageError = false
                },
                onError = {
                    imageLoading = false
                    imageError = true
                    Log.e("FoodCategoryItem2", "Failed to load image for $name from URL: $iconUrl")
                }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Category Name
        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.customColors.primary
            else MaterialTheme.customColors.black,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(70.dp)
        )

        // Selection indicator for already selected category
        if (isAlreadySelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(20.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.customColors.header)
            )
        }

        // Visual feedback for selected category (different from already selected)
        if (isSelected && !isAlreadySelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(20.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.customColors.primary)
            )
        }
    }
}
// Helper function to get all CategoryPage instances for the "See All" screen
fun getSeeAllCategoryPages(): List<CategoryPage> {
    return listOf(
        CategoryPage.All,
        CategoryPage.Diet,
        CategoryPage.Pizzas,
        CategoryPage.Cakes,
        CategoryPage.Momos,
        CategoryPage.Rolls,
        CategoryPage.Burgers,
        CategoryPage.CholeBhature,
        CategoryPage.Salad,
        CategoryPage.Patty,
        CategoryPage.Chinese,
        CategoryPage.IceCream,
        CategoryPage.Appam,
        CategoryPage.Bath,
        CategoryPage.Bonda,
        CategoryPage.Cutlet,
        CategoryPage.Dessert,
        CategoryPage.Dhokla,
        CategoryPage.Dosa,
        CategoryPage.Dholda,
        CategoryPage.GulabJamun,
        CategoryPage.Idli,
        CategoryPage.Biryani,
        CategoryPage.Thali,
        CategoryPage.Chicken,
        CategoryPage.VegMeal,
        CategoryPage.NorthIndian,
        CategoryPage.Paneer,
        CategoryPage.FriedRice,
        CategoryPage.Noodles,
        CategoryPage.Paratha,
        CategoryPage.Shawarma,
        CategoryPage.SouthIndian,
        CategoryPage.AlooTikki,
        CategoryPage.Pasta,
        CategoryPage.Pastry,
        CategoryPage.PavBhaji,
        CategoryPage.Sandwich,
        CategoryPage.Shake,
        CategoryPage.Samosa,
        CategoryPage.Poori,
        CategoryPage.Bowl,
        CategoryPage.Poha,
        CategoryPage.Sweets,
        CategoryPage.CholePoori,
        CategoryPage.Khichdi,
        CategoryPage.ChilliChicken,
        CategoryPage.Tea,
        CategoryPage.VadaPav,
        CategoryPage.MasalaMaggi,
        CategoryPage.Kulche,
        CategoryPage.Wings,
        CategoryPage.AlooPoori,
        CategoryPage.Omelette,
        CategoryPage.NonVegMeal,
        CategoryPage.BreadPakoda,
        CategoryPage.Coffee,
        CategoryPage.PooriBhaji,
        CategoryPage.Pulao,
        CategoryPage.ChurChurNaan,
        CategoryPage.Kebabs,
        CategoryPage.Panipuri,
        CategoryPage.Rasmalai,
        CategoryPage.Mutton,
        CategoryPage.Fish,
        CategoryPage.Pakoda,
        CategoryPage.Halwa,
        CategoryPage.ChopSuey,
        CategoryPage.Korma,
        CategoryPage.Namkeen,
        CategoryPage.Mushrooms,
        CategoryPage.Keema,
        CategoryPage.Sundae,
        CategoryPage.Rasgulla,
        CategoryPage.ButterChicken,
        CategoryPage.RajKachori,
        CategoryPage.Chaat,
        CategoryPage.Uttapam,
        CategoryPage.Doughnut,
        CategoryPage.Juice,
        CategoryPage.Lassi,
        CategoryPage.MalaiKofta,
        CategoryPage.DahiBalle,
        CategoryPage.Rajma,
        CategoryPage.ChickenHandi,
        CategoryPage.Cupcake,
        CategoryPage.Bhel,
        CategoryPage.Muffin,
        CategoryPage.Cookies,
        CategoryPage.ChickenCha,
        CategoryPage.PaneerKulche,
        CategoryPage.Chaach,
        CategoryPage.VegLollipop,
        CategoryPage.Sub,
        CategoryPage.Pancake,
        CategoryPage.Nihari,
        CategoryPage.Tacos,
        CategoryPage.Thepla,
        CategoryPage.Fafda,
        CategoryPage.Chocolate,
        CategoryPage.CurdRice,
        CategoryPage.Pudding,
        CategoryPage.Croissant,
        CategoryPage.Khandvi,
        CategoryPage.Gajak,
        CategoryPage.SambarRice,
        CategoryPage.Tart,
        CategoryPage.Tiramisu,
        CategoryPage.Pie,
        CategoryPage.Custard,
        CategoryPage.SevPoori,
        CategoryPage.Mousse,
        CategoryPage.DalKachori,
        CategoryPage.Jalebi,
        CategoryPage.PyaajKachori,
        CategoryPage.RajmaRice,
        CategoryPage.Upma,
        CategoryPage.Manchurian,
        CategoryPage.PaneerPakoda,
        CategoryPage.Cheesecake,
        CategoryPage.Brownie,
        CategoryPage.Chaap,
        CategoryPage.Dal,
        CategoryPage.Waffles,
        CategoryPage.AlooKachori,
        CategoryPage.CholeKulche,
        CategoryPage.Fries,
        CategoryPage.ColdCoffee,
        CategoryPage.Soup,
        CategoryPage.Bhurji,
        CategoryPage.KhastaKachori,
        CategoryPage.HotDog
    ).filter { it.isActive && it.title != "See All" } // Exclude "See All" from the list to prevent recursion
}