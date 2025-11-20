package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
    // From the images provided
    object All : CategoryPage("All", R.drawable.all_food)
    object Pizzas : CategoryPage("Pizzas", R.drawable.pizzas_food)
    object Cakes : CategoryPage("Cakes", R.drawable.cakes_food)
    object Momos : CategoryPage("Momos", R.drawable.momos_food)
    object Rolls : CategoryPage("Rolls", R.drawable.rolls_food)
    object Burgers : CategoryPage("Burgers", R.drawable.burgers_food)
    object CholeBhature : CategoryPage("Chole Bhature", R.drawable.chole_bhature_food)
    object Salad : CategoryPage("Salad", R.drawable.salad_food)
    object Party : CategoryPage("Patty", R.drawable.patty_food)
    object Chinese : CategoryPage("Chinese", R.drawable.chinese_food)
    object IceCream : CategoryPage("Ice Cream", R.drawable.ice_cream_food)
    object Appam : CategoryPage("Appam", R.drawable.appam_food)
    object Bath : CategoryPage("Bath", R.drawable.bath_food)
    object Bonda : CategoryPage("Bonda", R.drawable.bonda_food)
    object Cutlet : CategoryPage("Cutlet", R.drawable.cutlet_food)
    object Dessert : CategoryPage("Dessert", R.drawable.dessert_food)
    object Dhokla : CategoryPage("Dhokla", R.drawable.dhokla_food)
    object Dosa : CategoryPage("Dosa", R.drawable.dosa_food)
    object Dholda : CategoryPage("Dholda", R.drawable.dholda_food)
    object GulabJamun : CategoryPage("Gulab Jamun", R.drawable.gulab_jamun_food)
    object Idli : CategoryPage("Idli", R.drawable.idli_food)
    object Biryani : CategoryPage("Biryani", R.drawable.biryani_food)
    object Thali : CategoryPage("Thali", R.drawable.thali_food)
    object Chicken : CategoryPage("Chicken", R.drawable.chicken_food)
    object VegMeal : CategoryPage("Veg Meal", R.drawable.veg_meal_food)
    object NorthIndian : CategoryPage("North Indian", R.drawable.north_indian_food)
    object Paneer : CategoryPage("Paneer", R.drawable.paneer_food)
    object FriedRice : CategoryPage("Fried Rice", R.drawable.fried_rice_food)
    object Noodles : CategoryPage("Noodles", R.drawable.noodles_food)
    object Paratha : CategoryPage("Paratha", R.drawable.paratha_food)
    object Shawarma : CategoryPage("Shawarma", R.drawable.shawarma_food)
    object SeeAll : CategoryPage("See All", R.drawable.see_all_food)
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
        CategoryPage.All,
        CategoryPage.Pizzas,
        CategoryPage.Cakes,
        CategoryPage.Momos,
        CategoryPage.Rolls,
        CategoryPage.Burgers,
        CategoryPage.CholeBhature,
        CategoryPage.Salad,
        CategoryPage.Party,
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
        CategoryPage.SeeAll,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors.header)
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.white
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
                        .padding(horizontal = 2.dp)
                        .background(Color.Transparent)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = categoryPage.iconRes),
                            contentDescription = categoryPage.title,
                            modifier = Modifier
                                .width(65.dp)
                                .height(45.dp),
                            contentScale = ContentScale.FillBounds
                        )

                        Text(
                            text = categoryPage.title,
                            fontSize = 15.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.customColors.white
                            } else {
                                MaterialTheme.customColors.white
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
                0 -> AllCategoryPage()
                1 -> PizzasCategoryPage()
                2 -> CakesCategoryPage()
                3 -> MomosCategoryPage()
                4 -> RollsCategoryPage()
                5 -> BurgersCategoryPage()
                6 -> CholeBhatureCategoryPage()
                7 -> SaladCategoryPage()
                8 -> PartyCategoryPage()
                9 -> ChineseCategoryPage()
                10 -> IceCreamCategoryPage()
                11 -> AppamCategoryPage()
                12 -> BathCategoryPage()
                13 -> BondaCategoryPage()
                14 -> CutletCategoryPage()
                15 -> DessertCategoryPage()
                16 -> DhoklaCategoryPage()
                17 -> DosaCategoryPage()
                18 -> DholdaCategoryPage()
                19 -> GulabJamunCategoryPage()
                20 -> IdliCategoryPage()
                21 -> BiryaniCategoryPage()
                22 -> ThaliCategoryPage()
                23 -> ChickenCategoryPage()
                24 -> VegMealCategoryPage()
                25 -> NorthIndianCategoryPage()
                26 -> PaneerCategoryPage()
                27 -> FriedRiceCategoryPage()
                28 -> NoodlesCategoryPage()
                29 -> ParathaCategoryPage()
                30 -> ShawarmaCategoryPage()
                31 -> SeeAllCategoryPage()
                else -> AllCategoryPage()
            }
        }
    }
}

// Category Page Composables for all categories
@Composable
fun AllCategoryPage() {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 0.dp, start = 20.dp, end = 20.dp
                    )
            )
            {
                val bannerImages = listOf(
                    painterResource(id = R.drawable.all_food_banner1),
                    painterResource(id = R.drawable.all_food_banner2),
                    painterResource(id = R.drawable.all_food_banner3),
                )
                BannerFood(
                    images = bannerImages,
                    onImageClick = { page ->
                        when (page) {
                            0 -> onBanner1Click()
                            1 -> onBanner2Click()
                            2 -> onBanner3Click()
                        }
                    },
                    autoScrollDelay = 2000,
                    height = 250.dp,
                    roundedCornerShape = 20.dp,
                    contentScale = ContentScale.FillBounds,
                    dotSize = 8.dp,
                    modifier = Modifier.padding(bottom = 0.dp)
                )
            }
            FilterButtonFood()


// Sample data for preview
//            val sampleRestaurants = listOf(
//                Restaurant(
//                    id = 1,
//                    imageRes = R.drawable.restaurant_1, // Replace with your image resource
//                    restaurantName = "Burger King",
//                    bestIn = "Best In Burger",
//                    rating = 4.3,
//                    totalRating = "26K+",
//                    deliverTime = "30–35 mins",
//                    categories = "Burgers, American",
//                    address = "Sector 35",
//                    distance = "5.0 km",
//                    isWishlisted = false
//                ),
//                Restaurant(
//                    id = 2,
//                    imageRes = R.drawable.restaurant_2, // Replace with your image resource
//                    restaurantName = "Burger Singh(Big Punjabi...)",
//                    bestIn = "",
//                    rating = 4.2,
//                    totalRating = "868",
//                    deliverTime = "25–30 mins",
//                    categories = "Burgers, Snacks, Desserts, Beverages",
//                    address = "DMRC Metro Station",
//                    distance = "21 km",
//                    isWishlisted = false
//                ),
//                Restaurant(
//                    id = 3,
//                    imageRes = R.drawable.restaurant_3, // Replace with your image resource
//                    restaurantName = "McDonald's",
//                    bestIn = "Best In Burger",
//                    rating = 4.4,
//                    totalRating = "10K+",
//                    deliverTime = "35–40 mins",
//                    categories = "American",
//                    address = "Sector 35",
//                    distance = "5.0 km",
//                    isWishlisted = false
//                )
//            )
            val sampleRestaurants = listOf(
                Restaurant(
                    id = 1,
                    imageRes = R.drawable.paratha_corner,
                    restaurantName = "Paratha corner",
                    bestIn = "Best In Paratha",
                    rating = 4.3,
                    totalRating = "227",
                    deliverTime = "55–65 mins",
                    categories = "Indian",
                    address = "Raj Nagar",
                    distance = "6.4 km",
                    isWishlisted = false
                ),
                Restaurant(
                    id = 2,
                    imageRes = R.drawable.new_rati_masala,
                    restaurantName = "New Rati Masala",
                    bestIn = "Best In Spicy",
                    rating = 4.2,
                    totalRating = "318",
                    deliverTime = "50–60 mins",
                    categories = "Tandoor, Chinese",
                    address = "Govind Puram",
                    distance = "6.5 km",
                    isWishlisted = false
                ),
                Restaurant(
                    id = 3,
                    imageRes = R.drawable.fauji_dhaba,
                    restaurantName = "Fauji Dhaba",
                    bestIn = "Best In All",
                    rating = 4.4,
                    totalRating = "249",
                    deliverTime = "30–35 mins",
                    categories = "North Indian, Chinese, Tandoor, ...",
                    address = "Govindpuram",
                    distance = "6.8 km",
                    isWishlisted = false,
                    hasFlatDeal = true,
                    flatDealText = "FLAT DEAL • ¥200 OFF ABOVE $799",
                    itemsPrice = "ITEMS AT ¥119"
                ),
                Restaurant(
                    id = 4,
                    imageRes = R.drawable.indian_tadka,
                    restaurantName = "Indian Tadka",
                    bestIn = "Best In Thali",
                    rating = 4.0,
                    totalRating = "<3",
                    deliverTime = "50–60 mins",
                    categories = "North Indian",
                    address = "Raj Nagar",
                    distance = "6.1 km",
                    isWishlisted = false
                ),
                Restaurant(
                    id = 5,
                    imageRes = R.drawable.bikanervala,
                    restaurantName = "Bikanervala",
                    bestIn = "Best In Mithai",
                    rating = 4.3,
                    totalRating = "3.5K+",
                    deliverTime = "55–65 mins",
                    categories = "Bakery, Chinese, North Indian, Street Food",
                    address = "Govind Puram",
                    distance = "7.7 km",
                    isWishlisted = false
                ),
                Restaurant(
                    id = 6,
                    imageRes = R.drawable.restaurant_1,
                    restaurantName = "Burger King",
                    bestIn = "Best In Burger",
                    rating = 4.3,
                    totalRating = "26K+",
                    deliverTime = "30–35 mins",
                    categories = "Burgers, American",
                    address = "Sector 35",
                    distance = "5.0 km",
                    isWishlisted = false
                )
            )

            RestaurantList(
                restaurants = sampleRestaurants,
                onRestaurantClick = { restaurant ->
                    // Handle restaurant click
                    println("Clicked on: ${restaurant.restaurantName}")
                },
                onWishlistToggle = { restaurant ->
                    // Handle wishlist toggle
                    println("Toggled wishlist for: ${restaurant.restaurantName}")
                }
            )
        }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp)
//    ) {
//
//    }

//        Spacer(
//            modifier = Modifier
//                .height(2.dp)
//                .fillMaxWidth()
//                .background(MaterialTheme.customColors.spacerColor)
//        )

}

@Composable
fun PizzasCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pizzas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
        // Add your pizza items here
    }
}

@Composable
fun CakesCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cakes & Pastries",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun MomosCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Momos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun RollsCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rolls & Wraps",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun BurgersCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Burgers",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun CholeBhatureCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chole Bhature",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun SaladCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Salads",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun PartyCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Party Food",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun ChineseCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chinese Cuisine",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun IceCreamCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ice Cream & Desserts",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun AppamCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Appam",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun BathCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bath Specialties",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun BondaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bonda",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun CutletCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cutlets",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun DessertCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Desserts",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun DhoklaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dhokla",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun DosaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dosa",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun DholdaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dholda",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun GulabJamunCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gulab Jamun",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun IdliCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Idli",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun BiryaniCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Biryani",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun ThaliCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Thali Meals",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun ChickenCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chicken Dishes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun VegMealCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Vegetarian Meals",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun NorthIndianCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "North Indian Cuisine",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun PaneerCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Paneer Dishes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun FriedRiceCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Fried Rice",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun NoodlesCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Noodles",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun ParathaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Paratha",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun ShawarmaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shawarma",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun SeeAllCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "All Categories",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
        // Add comprehensive list of all food items
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    var currentPage by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.fillMaxWidth()) {
        CategoryTabsFood(
            onCategorySelected = { categoryPage ->
                currentPage = when (categoryPage) {
                    CategoryPage.All -> 0
                    CategoryPage.Pizzas -> 1
                    CategoryPage.Cakes -> 2
                    CategoryPage.Momos -> 3
                    CategoryPage.Rolls -> 4
                    CategoryPage.Burgers -> 5
                    CategoryPage.CholeBhature -> 6
                    CategoryPage.Salad -> 7
                    CategoryPage.Party -> 8
                    CategoryPage.Chinese -> 9
                    CategoryPage.IceCream -> 10
                    CategoryPage.Appam -> 11
                    CategoryPage.Bath -> 12
                    CategoryPage.Bonda -> 13
                    CategoryPage.Cutlet -> 14
                    CategoryPage.Dessert -> 15
                    CategoryPage.Dhokla -> 16
                    CategoryPage.Dosa -> 17
                    CategoryPage.Dholda -> 18
                    CategoryPage.GulabJamun -> 19
                    CategoryPage.Idli -> 20
                    CategoryPage.Biryani -> 21
                    CategoryPage.Thali -> 22
                    CategoryPage.Chicken -> 23
                    CategoryPage.VegMeal -> 24
                    CategoryPage.NorthIndian -> 25
                    CategoryPage.Paneer -> 26
                    CategoryPage.FriedRice -> 27
                    CategoryPage.Noodles -> 28
                    CategoryPage.Paratha -> 29
                    CategoryPage.Shawarma -> 30
                    CategoryPage.SeeAll -> 31
                }
            }
        )
    }
}


private fun onBanner1Click() {
    // Handle banner 1 click
    println("Banner 1 clicked")
}

private fun onBanner2Click() {
    // Handle banner 2 click
    println("Banner 2 clicked")
}

private fun onBanner3Click() {
    // Handle banner 3 click
    println("Banner 3 clicked")
}