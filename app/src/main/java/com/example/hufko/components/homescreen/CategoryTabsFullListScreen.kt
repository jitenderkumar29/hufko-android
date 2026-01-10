package com.example.hufko.components.homescreen

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CategoryTabsFullListScreen(
    navController: NavHostController,
    onCategorySelected: (CategoryPage, Int) -> Unit
) {
    val allCategories = remember(navController.currentBackStackEntry) {
        navController.currentBackStackEntry?.savedStateHandle?.get<List<CategoryPage>>("allCategories")
            ?: listOf(
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
                // Add all new categories
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
                CategoryPage.HotDog,
            )
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

            // Categories Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(allCategories) { index, category ->
                    val isInitiallyVisible = index < 19
                    val isAlreadySelected = index == currentMainTabIndex

                    FoodCategoryItem2(
                        category = FoodCategory(category.title, category.iconRes),
                        isSelected = index == selectedCategoryIndex,
                        isClickable = true,
                        isInitiallyVisible = isInitiallyVisible,
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
    category: FoodCategory,
    isSelected: Boolean = false,
    isClickable: Boolean = true,
    isInitiallyVisible: Boolean = false,
    isAlreadySelected: Boolean = false,
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
        // Icon Container
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
//                .background(
//                    when {
//                        isAlreadySelected -> MaterialTheme.customColors.header
//                        !isInitiallyVisible -> MaterialTheme.customColors.skyBlue
//                        else -> Color.White
//                    }
//                )
//                .border(
//                    width = if (isAlreadySelected) 2.dp else 1.dp,
//                    color = if (isAlreadySelected) {
//                        MaterialTheme.customColors.header
//                    } else if (!isInitiallyVisible) {
//                        MaterialTheme.customColors.header.copy(alpha = 0.5f)
//                    } else {
//                        Color.LightGray.copy(alpha = 0.3f)
//                    },
//                    shape = CircleShape
//                )
            ,
            contentAlignment = Alignment.Center
        ) {
            // Icon
            Image(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(70.dp)
            )

            // "Add to Tabs" badge for non-initial categories
//            if (!isInitiallyVisible && !isAlreadySelected) {
//                Box(
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .size(20.dp)
//                        .clip(CircleShape)
//                        .background(MaterialTheme.customColors.header)
//                        .padding(2.dp)
//                ) {
//                    Text(
//                        text = "+",
//                        fontSize = 10.sp,
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Category Name
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
//        Text(
//            text = category.name,
//            fontSize = 12.sp,
//            fontWeight = if (isSelected || isAlreadySelected) FontWeight.Bold else FontWeight.Medium,
//            color = when {
//                isSelected || isAlreadySelected -> MaterialTheme.customColors.header
//                !isInitiallyVisible -> MaterialTheme.customColors.header
//                else -> MaterialTheme.customColors.black
//            },
//            textAlign = TextAlign.Center,
//            maxLines = 2,
//            overflow = TextOverflow.Ellipsis,
//            modifier = Modifier.width(70.dp)
//        )

        // Selection indicator
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
    }
}