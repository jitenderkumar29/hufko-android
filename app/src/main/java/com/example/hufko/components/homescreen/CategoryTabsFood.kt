package com.example.hufko.components.homescreen

import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
    object Diet : CategoryPage("Diet", R.drawable.diet_food)
    object Pizzas : CategoryPage("Pizzas", R.drawable.pizzas_food)
    object Cakes : CategoryPage("Cakes", R.drawable.cakes_food)
    object Momos : CategoryPage("Momos", R.drawable.momos_food)
    object Rolls : CategoryPage("Rolls", R.drawable.rolls_food)
    object Burgers : CategoryPage("Burgers", R.drawable.burgers_food)
    object CholeBhature : CategoryPage("Chole Bhature", R.drawable.chole_bhature_food)
    object Salad : CategoryPage("Salad", R.drawable.salad_food)
    object Patty : CategoryPage("Patty", R.drawable.patty_food)
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
        CategoryPage.SeeAll,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors.skyBlue)
//            .background(MaterialTheme.customColors.header)
    ) {
        ScrollableTabRow(  // Active tab bottom color
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.header
//                    color = MaterialTheme.customColors.white
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
                                MaterialTheme.customColors.header
                            } else {
                                MaterialTheme.customColors.black
//                                MaterialTheme.customColors.white
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
                1 -> DietCategoryPage()
                2 -> PizzasCategoryPage()
                3 -> CakesCategoryPage()
                4 -> MomosCategoryPage()
                5 -> RollsCategoryPage()
                6 -> BurgersCategoryPage()
                7 -> CholeBhatureCategoryPage()
                8 -> SaladCategoryPage()
                9 -> PattyCategoryPage()
                10 -> ChineseCategoryPage()
                11 -> IceCreamCategoryPage()
                12 -> AppamCategoryPage()
                13 -> BathCategoryPage()
                14 -> BondaCategoryPage()
                15 -> CutletCategoryPage()
                16 -> DessertCategoryPage()
                17 -> DhoklaCategoryPage()
                18 -> DosaCategoryPage()
                19 -> DholdaCategoryPage()
                20 -> GulabJamunCategoryPage()
                21 -> IdliCategoryPage()
                22 -> BiryaniCategoryPage()
                23 -> ThaliCategoryPage()
                24 -> ChickenCategoryPage()
                25 -> VegMealCategoryPage()
                26 -> NorthIndianCategoryPage()
                27 -> PaneerCategoryPage()
                28 -> FriedRiceCategoryPage()
                29 -> NoodlesCategoryPage()
                30 -> ParathaCategoryPage()
                31 -> ShawarmaCategoryPage()
                32 -> SeeAllCategoryPage()
                else -> AllCategoryPage()
            }
        }
    }
}

// Category Page Composables for all categories
@Composable
fun DietCategoryPage(
    onBanner1Click: () ->
//        Text(
//            text = "Diet",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.customColors.black
//        )
    Unit = {},
    onBanner2Click: () -> Unit = {},
    onBanner3Click: () -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .padding(16.dp)
    ) {
        val allDietItems = listOf(
            FoodItemBannerPreNextF(
                id = 1,
                imageRes = R.drawable.diet_food_banner1,
                title = "Grilled Chicken Salad",
                price = "180",
                restaurantName = "Fit Feast",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.1 km",
                discount = "15%",
                discountAmount = "up to ₹40",
                address = "Rohini, Delhi",
                calories = "320",
                protein = "28",
                isHighProtein = true
            ),
            FoodItemBannerPreNextF(
                id = 2,
                imageRes = R.drawable.diet_food_banner2,
                title = "Paneer Quinoa Bowl",
                price = "160",
                restaurantName = "Healthy Mash",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "3.0 km",
                discount = "10%",
                discountAmount = "up to ₹30",
                address = "Janakpuri, Delhi",
                calories = "350",
                protein = "22",
                isHighProtein = true
            ),
            FoodItemBannerPreNextF(
                id = 3,
                imageRes = R.drawable.diet_food_banner3,
                title = "Oats Peanut Butter Bowl",
                price = "110",
                restaurantName = "Muscle Bowl",
                rating = "4.6",
                deliveryTime = "12-18 mins",
                distance = "1.4 km",
                discount = null,
                discountAmount = null,
                address = "Paschim Vihar, Delhi",
                calories = "390",
                protein = "17",
                isHighProtein = true
            ),
        )
        Image(
            painter = painterResource(R.drawable.ic_diet_header),
            contentDescription = "Banner",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = 100.dp,
                    max = 300.dp
                ), // Height between min and max, // 30% of screen height, // Sets height based on width and aspect ratio
            contentScale = ContentScale.FillBounds
        )
        BannerPreNextF(
            foodItems = allDietItems,
            onItemClick = { foodItem ->
                println("Clicked on: ${foodItem.title}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp),
            backgroundColor1 = Color(0xFF131709),
            backgroundColor2 = Color(0xFFFFFFFF) // Dark Purple
//            backgroundColor2 = Color(0xFFE5E5E3) // Dark Purple
        )
        Spacer(modifier = Modifier.height(10.dp))
        var selectedDietTabIndex by remember { mutableIntStateOf(0) }

        CategoryDietTabsFood(
            onCategorySelected= {}
        )
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            stickyHeader {
//                CategoryDietTabsFood(
//                    onCategorySelected = {
//                        println("Selected Diet tab")
//                    }
//                )
//            }
//        }
    }
}
@Composable
fun AllCategoryPage(
    onBanner1Click: () -> Unit = {},
    onBanner2Click: () -> Unit = {},
    onBanner3Click: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Banner Section
        BannerFood(
            images = listOf(
                painterResource(id = R.drawable.all_food_banner3),
                painterResource(id = R.drawable.all_food_banner1),
                painterResource(id = R.drawable.all_food_banner2),
                painterResource(id = R.drawable.all_food_banner4),
                painterResource(id = R.drawable.all_food_banner5),
                painterResource(id = R.drawable.all_food_banner6),
                painterResource(id = R.drawable.all_food_banner7),
                painterResource(id = R.drawable.all_food_banner8),
                painterResource(id = R.drawable.all_food_banner9),
                painterResource(id = R.drawable.all_food_banner10),
                painterResource(id = R.drawable.all_food_banner11),
                painterResource(id = R.drawable.all_food_banner12),
                painterResource(id = R.drawable.all_food_banner13),
                painterResource(id = R.drawable.all_food_banner14),
                painterResource(id = R.drawable.all_food_banner15),
                painterResource(id = R.drawable.all_food_banner16),
                painterResource(id = R.drawable.all_food_banner17),
            ),
            onImageClick = { page ->
                when (page) {
                    0 -> onBanner1Click()
                    1 -> onBanner2Click()
                    2 -> onBanner3Click()
                }
            },
            autoScrollDelay = 2000,
            height = 250.dp,
            roundedCornerShape = 0.dp,
            contentScale = ContentScale.FillBounds,
            dotSize = 8.dp,
            dotPadding = 4.dp,
            dotPosition = DotPosition.OVERLAY,
            overlayGradient = true, // Adds gradient for better visibility
            selectedDotColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
        )

        val sampleProducts = listOf(
            ProductListGrid(
                name = "Product 1",
                price = "FLAT 10% OFF",
                imageRes = R.drawable.popular_chain_1,
//                backgroundColor = Color(0xFFFFF8E1) // Light amber
            ),
            ProductListGrid(
                name = "Product 2",
                price = "FLAT 25% OFF",
                imageRes = R.drawable.popular_chain_2,
//                backgroundColor = Color(0xFFE8F5E8) // Light green
            ),
            ProductListGrid(
                name = "Product 3",
                price = "FLAT 10% OFF",
                imageRes = R.drawable.popular_chain_3,
//                backgroundColor = Color(0xFFE3F2FD) // Light blue
            ),
            ProductListGrid(
                name = "Product 4",
                price = "FLAT 20% OFF",
                imageRes = R.drawable.popular_chain_4
                // No background color - will use default
            ),
            ProductListGrid(
                name = "Product 5",
                price = "FLAT 10% OFF",
                imageRes = R.drawable.popular_chain_5
                // No background color - will use default
            ),
            ProductListGrid(
                name = "Product 6",
                price = "FLAT 30% OFF",
                imageRes = R.drawable.popular_chain_6
                // No background color - will use default
            )
        )

        // Display CategoryListGrid showing **name only**
        Image(
            painter = painterResource(R.drawable.ic_popular_chain_header),
            contentDescription = "Banner",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = 100.dp,
                    max = 300.dp
                ), // Height between min and max, // 30% of screen height, // Sets height based on width and aspect ratio
            contentScale = ContentScale.FillBounds
        )
        // Example 1: Fixed image height (original behavior)
        CategoryListGridF(
            products = sampleProducts,
            columns = 3,
            gridHeight = 350.dp, // fixed height to avoid crashes
            showName = false,
            showPrice = true,   // hide price
            imageAspectRatio = 3f / 3f,
            defaultCardColor = Color(0xFF023726),
            textColor = Color.White,
            onItemClick = { product ->
                println("Clicked on ${product.name}")
            }
        )

        Spacer(modifier = Modifier.height(15.dp))
        // Filter Button

        val allFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rating_4",
                    text = "Rating 4.0+",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pure_veg",
                    text = "Pure Veg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 1
        )
        FilterButtonFood(
            filterConfig = allFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        // Sample data based on the provided images
        val sampleRestaurantItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.restaurant_image_all_food_1,
                title = "Paneer Delight Momos",
                price = "159",
                restaurantName = "Goblins",
                rating = "4.0",
                deliveryTime = "30-35 mins",
                distance = "5.8 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Govindpuram",
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.restaurant_image_all_food_2,
                title = "Egg Curry",
                price = "90",
                restaurantName = "Bori & Rori Junction",
                rating = "3.5",
                deliveryTime = "40-45 mins",
                distance = "6.8 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Noida",
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.restaurant_image_all_food_3,
                title = "Tawa Chicken",
                price = "299",
                restaurantName = "FFC Express",
                rating = "3.9",
                deliveryTime = "40-45 mins",
                distance = "8.2 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Delhi",
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.restaurant_image_all_food_4,
                title = "Pasta",
                price = "300 for one",
                restaurantName = "Gustaro Pasta",
                rating = "4.5",
                deliveryTime = "70-75 mins",
                distance = "14.3 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Bengaluru",
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.restaurant_image_all_food_5,
                title = "Burger",
                price = "200 for one",
                restaurantName = "Ayub Chaumin Wale",
                rating = "4.3",
                deliveryTime = "25-30 mins",
                distance = "4.3 km",
                discount = "Upto 40%",
                discountAmount = "on select items",
                address = "Govindpuram",
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.restaurant_image_all_food_6,
                title = "Biryani",
                price = "400 for one",
                restaurantName = "Charcoal Eats - Biryani & Beyond",
                rating = "4.2",
                deliveryTime = "50-55 mins",
                distance = "13.8 km",
                discount = "FLAT 50%",
                discountAmount = "OFF",
                address = "Meerut",
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.restaurant_image_all_food_7,
                title = "Honey Chilli Porero",
                price = "160",
                restaurantName = "Desi Mugz",
                rating = "4.1",
                deliveryTime = "60-65 mins",
                distance = "14.8 km",
                discount = "",
                discountAmount = "OFF",
                address = "Badarpur",
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.restaurant_image_all_food_8,
                title = "Paneer Paratha",
                price = "119",
                restaurantName = "Swaad Aaya",
                rating = "3.5",
                deliveryTime = "55-60 mins",
                distance = "13.8 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Meerut",
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.restaurant_image_all_food_9,
                title = "Arhar Dal Tadka",
                price = "180",
                restaurantName = "Uncle Ke Rasoi",
                rating = "4.0",
                deliveryTime = "55-60 mins",
                distance = "13 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Agra",
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.restaurant_image_all_food_10,
                title = "Pure Veg",
                price = "200 for one",
                restaurantName = "Shree Krishna Baker's",
                rating = "3.5",
                deliveryTime = "45-50 mins",
                distance = "10.9 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Aligarh",
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.restaurant_image_all_food_11,
                title = "Paneer Manchurian Dry",
                price = "110",
                restaurantName = "King Of Spices",
                rating = "4.0",
                deliveryTime = "55-60 mins",
                distance = "13 km",
                discount = "",
                discountAmount = "",
                address = "Hydrabad",
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.restaurant_image_all_food_12,
                title = "North Indian",
                price = "450 for one",
                restaurantName = "The Food Workshop",
                rating = "4.1",
                deliveryTime = "50-55 mins",
                distance = "12.6 km",
                discount = "Flat ₹125",
                discountAmount = "",
                address = "Chennai",
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.restaurant_image_all_food_13,
                title = "Veg Fried Rice with Veg Manchurian",
                price = "250",
                restaurantName = "Old Veg Rasoi",
                rating = "4.0",
                deliveryTime = "55-60 mins",
                distance = "13 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Agra",
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.restaurant_image_all_food_14,
                title = "Pure Veg",
                price = "",
                restaurantName = "Radha Rani Ki Rasoi",
                rating = "3.8",
                deliveryTime = "65-70 mins",
                distance = "13.9 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Aligarh",
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.restaurant_image_all_food_15,
                title = "Farmers Choice Pizza",
                price = "124",
                restaurantName = "The Hot Pizza",
                rating = "3.6",
                deliveryTime = "50-55 mins",
                distance = "12 km",
                discount = "Upto 50%",
                discountAmount = "on select items",
                address = "Hydrabad",
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.restaurant_image_all_food_16,
                title = "Chaap Biryani",
                price = "210",
                restaurantName = "Annapurna Thali",
                rating = "3.7",
                deliveryTime = "55-60 mins",
                distance = "14.1 km",
                discount = "60%",
                discountAmount = "up to ₹120",
                address = "Hydrabad",
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.restaurant_image_all_food_17,
                title = "Chinese",
                price = "200 for one",
                restaurantName = "Chinese Wokology",
                rating = "4.1",
                deliveryTime = "45-50 mins",
                distance = "14.1 km",
                discount = "",
                discountAmount = "up to ₹120",
                address = "Badarpur",
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.restaurant_image_all_food_18,
                title = "Eggless Red Velvet Cake",
                price = "599",
                restaurantName = "Bakincake",
                rating = "4.0",
                deliveryTime = "55-60 mins",
                distance = "13.8 km",
                discount = "Buy 1 get 1",
                discountAmount = "free",
                address = "Meerut",
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.restaurant_image_all_food_19,
                title = "Pure Veg",
                price = "200 for one",
                restaurantName = "mahapooran",
                rating = "3.7",
                deliveryTime = "30-35 mins",
                distance = "5.6 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Badarpur",
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.restaurant_image_all_food_20,
                title = "Paneer Handi",
                price = "180",
                restaurantName = "Shree Jee Restaurant",
                rating = "4.1",
                deliveryTime = "45-50 mins",
                distance = "7.3 km",
                discount = "60%",
                discountAmount = "up to ₹120",
                address = "Delhi",
            )
        )

        Column {
            sampleRestaurantItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun PizzasCategoryPage() {
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }
    // Additional helper functions
    fun filterByDeliveryTime(maxTime: Int) {
        println("Filter by max delivery time: $maxTime minutes")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
//                .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
            val pizzaFilters = FilterConfig(
                filters = listOf(
                    FilterChip(
                        id = "filters",
                        text = "Filters",
                        type = FilterType.FILTER_DROPDOWN,
                        icon = R.drawable.ic_filter,
                        rightIcon = R.drawable.outline_keyboard_arrow_down_24
                    ),
                    FilterChip(
                        id = "cheese_burst",
                        text = "Cheese Burst",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_pizza_cheese_burst
                    ),
                    FilterChip(
                        id = "farmhouse",
                        text = "Farmhouse",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_pizza_farmhouse
                    ),
                    FilterChip(
                        id = "margherita",
                        text = "Margherita",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_pizza_margherita
                    ),
                    FilterChip(
                        id = "multigrain",
                        text = "Multigrain",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_pizza_multigrain
                    ),
                    FilterChip(
                        id = "pan",
                        text = "Pan",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_pizza_pan
                    ),
                    FilterChip(
                        id = "under_150",
                        text = "Under ₹150",
                        type = FilterType.TEXT_ONLY
                    ),
                    FilterChip(
                        id = "under_30_mins",
                        text = "Under 30 mins",
                        type = FilterType.TEXT_ONLY
                    ),
                    FilterChip(
                        id = "rating_4",
                        text = "Rating 4.0+",
                        type = FilterType.TEXT_ONLY
                    ),
                    FilterChip(
                        id = "pure_veg",
                        text = "Pure Veg",
                        type = FilterType.TEXT_ONLY
                    ),
                    FilterChip(
                        id = "schedule",
                        text = "Schedule",
                        type = FilterType.SORT_DROPDOWN,
                        rightIcon = R.drawable.outline_keyboard_arrow_down_24
                    ),
                    FilterChip(
                        id = "paneer",
                        text = "Paneer",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_veg_paneer
                    ),
                    FilterChip(
                        id = "pepperoni",
                        text = "Pepperoni",
                        type = FilterType.WITH_LEFT_ICON,
                        icon = R.drawable.ic_non_veg_pepperoni
                    ),
                ),
        rows = 2
        )
        FilterButtonFood(
            filterConfig = pizzaFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Show active filters
        if (selectedFilters.isNotEmpty()) {
            Text(
                text = "Active: ${selectedFilters.joinToString(", ")}",
                modifier = Modifier.padding(16.dp)
            )
        }
            // Sample data with all fields
            val completePizzaFoodItems = listOf(
                FoodItemDoubleF(
                    id = 1,
                    imageRes = R.drawable.ic_pizzas_food_1,
                    title = "Paneer Handi",
                    price = "180",
                    restaurantName = "Shree Jee Restaurant",
                    rating = "4.1",
                    deliveryTime = "45-50 mins",
                    distance = "7.3 km",
                    discount = "60%",
                    discountAmount = "up to ₹120",
                    address = "Delhi"
                ),
        FoodItemDoubleF(
            id = 2,
            imageRes = R.drawable.ic_pizzas_food_2,
            title = "Butter Chicken",
            price = "220",
            restaurantName = "Amiche Pizza",
            rating = "4.3",
            deliveryTime = "60-65 mins",
            distance = "5.2 km",
            discount = "50%",
            discountAmount = "up to ₹100",
            address = "Delhi"
        ),
        FoodItemDoubleF(
            id = 3,
            imageRes = R.drawable.ic_pizzas_food_3,
            title = "Vegetable Biryani",
            price = "150",
            restaurantName = "Spice Garden",
            rating = "4.0",
            deliveryTime = "30-35 mins",
            distance = "3.8 km",
            discount = "40%",
            discountAmount = "up to ₹80",
            address = "Delhi"
        ),
        FoodItemDoubleF(
            id = 4,
            imageRes = R.drawable.ic_pizzas_food_4,
            title = "Margherita Pizza",
            price = "199",
            restaurantName = "Amiche Pizza",
            rating = "4.2",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "30%",
            discountAmount = "up to ₹60",
            address = "Delhi"
        ),
        FoodItemDoubleF(
            id = 5,
            imageRes = R.drawable.ic_pizzas_food_5,
            title = "Margherita Pizza",
            price = "199",
            restaurantName = "Amiche Pizza",
            rating = "4.2",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "30%",
            discountAmount = "up to ₹60",
            address = "Delhi"
        ),
        FoodItemDoubleF(
            id = 6,
            imageRes = R.drawable.ic_pizzas_food_6,
            title = "Margherita Pizza",
            price = "199",
            restaurantName = "Amiche Pizza",
            rating = "4.2",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "30%",
            discountAmount = "up to ₹60",
            address = "Delhi"
        )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completePizzaFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }
//        FilterButtonFood()

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        // Sample data based on the provided images
        val sampleRestaurantItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.restaurant_image_pizzas_food_1,
                title = "Cheese And Paneer Pizza",
                price = "206",
                restaurantName = "Gangs Of Goli",
                rating = "3.9",
                deliveryTime = "50-55 mins",
                distance = "8.3 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Main Street, Downtown",
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.restaurant_image_pizzas_food_2,
                title = "Farm House Chicken Pizza",
                price = "390",
                restaurantName = "FFC Express",
                rating = "5.9",
                deliveryTime = "55-60 mins",
                distance = "8.2 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Food Court, Mall Road",
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.restaurant_image_pizzas_food_3,
                title = "Onion and Capsicum Pizza",
                price = "264",
                restaurantName = "Pizza Cafe",
                rating = "3.4",
                deliveryTime = "50-55 mins",
                distance = "6.9 km",
                discount = "Buy 1 get 1 free",
                discountAmount = "On all pizzas",
                address = "Central Plaza",
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.restaurant_image_pizzas_food_4,
                title = "Farm House Pizza",
                price = "799",
                restaurantName = "Eddy's Restaurant",
                rating = "4.5",
                deliveryTime = "50-55 mins",
                distance = "9 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "North End Circle",
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.restaurant_image_pizzas_food_5,
                title = "Capsicum Paneer Pizza",
                price = "115",
                restaurantName = "Aapka Apna Restaurant",
                rating = "5.43",
                deliveryTime = "45-50 mins",
                distance = "6.9 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "South Extension",
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.restaurant_image_pizzas_food_6,
                title = "Paneer Olives Jalapeno Combo",
                price = "299",
                restaurantName = "Pizza Lover's",
                rating = "4.3",
                deliveryTime = "40-45 mins",
                distance = "7.5 km",
                discount = "Buy 1 get 1 free",
                discountAmount = "On combo pizzas",
                address = "West Gate Market",
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.restaurant_image_pizzas_food_7,
                title = "Country Feast Pizza",
                price = "199",
                restaurantName = "Cafe Desilicious",
                rating = "4.0",
                deliveryTime = "50-55 mins",
                distance = "8.3 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "East Side Plaza",
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.restaurant_image_pizzas_food_8,
                title = "Onion Com Pizza",
                price = "245",
                restaurantName = "Pizza House",
                rating = "3.1",
                deliveryTime = "45-50 mins",
                distance = "6.9 km",
                discount = "Buy 1 get 1 free",
                discountAmount = "On medium pizzas",
                address = "City Center",
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.restaurant_image_pizzas_food_9,
                title = "Paneer and Onion Pizza",
                price = "100",
                restaurantName = "Hot Spot Pizza",
                rating = "4.0",
                deliveryTime = "50-55 mins",
                distance = "8.2 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Commercial Street",
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.restaurant_image_pizzas_food_10,
                title = "Onion and Paneer Pizza",
                price = "129",
                restaurantName = "Rockers Pizza",
                rating = "4.0",
                deliveryTime = "55-60 mins",
                distance = "9.1 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Entertainment District",
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.restaurant_image_pizzas_food_11,
                title = "Exotic Paneer Pizza",
                price = "170",
                restaurantName = "New Roi Masala Restaurant",
                rating = "4.1",
                deliveryTime = "50-55 mins",
                distance = "8.2 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Royal Market",
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.restaurant_image_pizzas_food_12,
                title = "Onion and Paneer Pizza",
                price = "159",
                restaurantName = "Roms Pizza",
                rating = "4.1",
                deliveryTime = "55-60 mins",
                distance = "8.6 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Business Park Area",
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.restaurant_image_pizzas_food_13,
                title = "Veggie Paradise Pizza",
                price = "299",
                restaurantName = "Pizza Point",
                rating = "3.2",
                deliveryTime = "50-55 mins",
                distance = "6.9 km",
                discount = "Buy 1 get 1 free",
                discountAmount = "On large pizzas",
                address = "Shopping Complex",
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.restaurant_image_pizzas_food_14,
                title = "Farmfresh Pizza",
                price = "240",
                restaurantName = "Roms Pizza",
                rating = "4.1",
                deliveryTime = "40-45 mins",
                distance = "5.8 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Residential Area",
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.restaurant_image_pizzas_food_15,
                title = "Paneer And Onion Pizza",
                price = "115",
                restaurantName = "Amiche Pizza",
                rating = "3.7",
                deliveryTime = "60-65 mins",
                distance = "8.6 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "University Road",
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.restaurant_image_pizzas_food_16,
                title = "Cheese & Corn Pizza",
                price = "145",
                restaurantName = "Pepper's Pizza",
                rating = "4.2",
                deliveryTime = "40-45 mins",
                distance = "5.8 km",
                discount = "Flat ₹175 OFF",
                discountAmount = "above ₹349",
                address = "Tech Park Zone",
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.restaurant_image_pizzas_food_17,
                title = "Veg Extravaganza Pizza",
                price = "350",
                restaurantName = "SEKAI MISE",
                rating = "New",
                deliveryTime = "45-50 mins",
                distance = "7.9 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "International Street",
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.restaurant_image_pizzas_food_18,
                title = "Classic Margherita Pizza",
                price = "109",
                restaurantName = "La Peto's Pizza",
                rating = "4.0",
                deliveryTime = "40-45 mins",
                distance = "5.6 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Italian Corner",
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.restaurant_image_pizzas_food_19,
                title = "Special Veg Platter",
                price = "350",
                restaurantName = "Shree Jee Restaurant",
                rating = "4.4",
                deliveryTime = "45-50 mins",
                distance = "7.3 km",
                discount = "60% OFF",
                discountAmount = "up to ₹120",
                address = "Traditional Market",
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.restaurant_image_pizzas_food_20,
                title = "Rock Special Pizza",
                price = "275",
                restaurantName = "Rock's Pizza",
                rating = "4.2",
                deliveryTime = "50-55 mins",
                distance = "9.1 km",
                discount = "50% OFF",
                discountAmount = "up to ₹100",
                address = "Music Street",
            )
        )

        Column {
            sampleRestaurantItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }


@Composable
fun CakesCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        // Filter Button
        val cakesFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "zero_spill_guarantee",
                    text = "Zero-Spill Guarantee",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_cheese_burst
                ),
                FilterChip(
                    id = "hazelnut",
                    text = "Hazelnut",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_farmhouse
                ),
                FilterChip(
                    id = "ice_cream",
                    text = "Ice Cream",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_margherita
                ),
                FilterChip(
                    id = "photo",
                    text = "Photo",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_multigrain
                ),
                FilterChip(
                    id = "pineapple",
                    text = "Pineapple",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_pan
                ),
                FilterChip(
                    id = "under_500",
                    text = "Under ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rating_4",
                    text = "Rating 4.0+",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pure_veg",
                    text = "Pure Veg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "kitkat",
                    text = "Kitkat",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_paneer
                ),
                FilterChip(
                    id = "mango",
                    text = "Mango",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_non_veg_pepperoni
                ),
                FilterChip(
                    id = "mango",
                    text = "Flavour: Rasmalai",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_non_veg_pepperoni
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = cakesFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        val completeCakesFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.le_fusion_bakery_cakes, // You'll need to add this image
                title = "Le Fusion Bakery",
                price = "180",
                restaurantName = "Le Fusion Bakery",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "7.3 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.kavya_bakery_cakes, // You'll need to add this image
                title = "Kavya Bakery",
                price = "220",
                restaurantName = "Kavya Bakery",
                rating = "3.8",
                deliveryTime = "35-40 mins",
                distance = "5.2 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.havmor_icecream_cakes, // You'll need to add this image
                title = "Havmor Icecream",
                price = "150",
                restaurantName = "Havmor Icecream",
                rating = "4.5",
                deliveryTime = "45-50 mins",
                distance = "3.8 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.cake_for_you_cakes, // You'll need to add this image
                title = "Cake For You",
                price = "199",
                restaurantName = "Cake For You",
                rating = "3.7",
                deliveryTime = "40-45 mins",
                distance = "2.5 km",
                discount = "60%",
                discountAmount = "up to ₹120",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.memory_lane_cakes, // You'll need to add this image
                title = "MemoryLane",
                price = "199",
                restaurantName = "MemoryLane",
                rating = "4.0",
                deliveryTime = "45-50 mins",
                distance = "2.5 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_delights_food_cakes, // Keep existing or replace
                title = "Sweet Delights",
                price = "199",
                restaurantName = "Sweet Delights",
                rating = "4.2",
                deliveryTime = "25-30 mins",
                distance = "2.5 km",
                discount = "50%",
                discountAmount = "up to ₹100",
                address = "Delhi"
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeCakesFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        // Sample data based on the provided images
        val sampleRestaurantItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.cake_chocolate_fudge,
                title = "Chocolate Fudge Cake",
                price = "450",
                restaurantName = "Sweet Delights Bakery",
                rating = "4.5",
                deliveryTime = "30-40 mins",
                distance = "2.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹80",
                address = "Bakery Street, City Center"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.cake_red_velvet,
                title = "Red Velvet Cake",
                price = "520",
                restaurantName = "Cake Paradise",
                rating = "4.7",
                deliveryTime = "25-35 mins",
                distance = "1.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹75",
                address = "MG Road, Downtown"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.cake_black_forest,
                title = "Black Forest Cake",
                price = "480",
                restaurantName = "German Bakery House",
                rating = "4.6",
                deliveryTime = "40-50 mins",
                distance = "3.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹120",
                address = "European Quarter"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.cake_vanilla_cream,
                title = "Vanilla Cream Cake",
                price = "380",
                restaurantName = "Buttercup Cakes",
                rating = "4.3",
                deliveryTime = "20-30 mins",
                distance = "1.5 km",
                discount = "10% OFF",
                discountAmount = "up to ₹50",
                address = "Market Lane, South End"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.cake_strawberry,
                title = "Fresh Strawberry Cake",
                price = "550",
                restaurantName = "Fruit Fantasy",
                rating = "4.8",
                deliveryTime = "35-45 mins",
                distance = "4.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹150",
                address = "Garden Road, East Side"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.cake_blueberry_cheese,
                title = "Blueberry Cheesecake",
                price = "620",
                restaurantName = "Cheese Cake Factory",
                rating = "4.9",
                deliveryTime = "45-55 mins",
                distance = "5.3 km",
                discount = "20% OFF",
                discountAmount = "up to ₹120",
                address = "Food Court, Mega Mall"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.cake_carrot_walnut,
                title = "Carrot Walnut Cake",
                price = "420",
                restaurantName = "Healthy Bites",
                rating = "4.4",
                deliveryTime = "30-40 mins",
                distance = "2.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹60",
                address = "Wellness Street"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.cake_coffee_mocha,
                title = "Coffee Mocha Cake",
                price = "490",
                restaurantName = "Brew & Bake",
                rating = "4.5",
                deliveryTime = "25-35 mins",
                distance = "2.1 km",
                discount = "25% OFF",
                discountAmount = "up to ₹100",
                address = "Cafe District"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.cake_pineapple,
                title = "Pineapple Upside Down Cake",
                price = "440",
                restaurantName = "Tropical Treats",
                rating = "4.2",
                deliveryTime = "40-50 mins",
                distance = "3.7 km",
                discount = "10% OFF",
                discountAmount = "up to ₹40",
                address = "Beach Road"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.cake_lemon_drizzle,
                title = "Lemon Drizzle Cake",
                price = "390",
                restaurantName = "Citrus Delights",
                rating = "4.6",
                deliveryTime = "20-30 mins",
                distance = "1.9 km",
                discount = "20% OFF",
                discountAmount = "up to ₹70",
                address = "Fresh Market"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.cake_rainbow,
                title = "Rainbow Layer Cake",
                price = "680",
                restaurantName = "Colorful Cakes",
                rating = "4.7",
                deliveryTime = "50-60 mins",
                distance = "4.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹100",
                address = "Art District"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.cake_coconut,
                title = "Coconut Cream Cake",
                price = "470",
                restaurantName = "Island Bakery",
                rating = "4.3",
                deliveryTime = "35-45 mins",
                distance = "3.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹110",
                address = "Palm Street"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.cake_marble,
                title = "Chocolate Marble Cake",
                price = "410",
                restaurantName = "Classic Confections",
                rating = "4.4",
                deliveryTime = "25-35 mins",
                distance = "2.3 km",
                discount = "10% OFF",
                discountAmount = "up to ₹40",
                address = "Heritage Road"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.cake_banana_walnut,
                title = "Banana Walnut Cake",
                price = "360",
                restaurantName = "Homemade Goodness",
                rating = "4.5",
                deliveryTime = "30-40 mins",
                distance = "2.6 km",
                discount = "20% OFF",
                discountAmount = "up to ₹70",
                address = "Residential Area"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.cake_raspberry,
                title = "Raspberry Delight Cake",
                price = "580",
                restaurantName = "Berry Bliss",
                rating = "4.8",
                deliveryTime = "40-50 mins",
                distance = "4.3 km",
                discount = "30% OFF",
                discountAmount = "up to ₹160",
                address = "Orchard Road"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.cake_mango,
                title = "Mango Mousse Cake",
                price = "540",
                restaurantName = "Summer Sweets",
                rating = "4.6",
                deliveryTime = "35-45 mins",
                distance = "3.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹80",
                address = "Seasonal Market"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.cake_honey,
                title = "Honey Almond Cake",
                price = "520",
                restaurantName = "Nature's Best",
                rating = "4.4",
                deliveryTime = "30-40 mins",
                distance = "2.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹120",
                address = "Organic Street"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.cake_cinnamon,
                title = "Cinnamon Spice Cake",
                price = "430",
                restaurantName = "Spice Route Bakery",
                rating = "4.3",
                deliveryTime = "25-35 mins",
                distance = "2.4 km",
                discount = "10% OFF",
                discountAmount = "up to ₹40",
                address = "Spice Market"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.cake_oreo,
                title = "Oreo Cookies Cake",
                price = "590",
                restaurantName = "Cookie Monster",
                rating = "4.7",
                deliveryTime = "40-50 mins",
                distance = "4.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹110",
                address = "Kids Zone"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.cake_salted_caramel,
                title = "Salted Caramel Cake",
                price = "560",
                restaurantName = "Gourmet Treats",
                rating = "4.9",
                deliveryTime = "45-55 mins",
                distance = "5.1 km",
                discount = "15% OFF",
                discountAmount = "up to ₹80",
                address = "Luxury Lane"
            )
        )

        Column {
            sampleRestaurantItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun MomosCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        // Filter Button
        val momosFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "chicken_momos",
                    text = "Chicken Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_cheese_burst
                ),
                FilterChip(
                    id = "veg_momos",
                    text = "Veg Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_farmhouse
                ),
                FilterChip(
                    id = "chilli_momos",
                    text = "Chilli Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_margherita
                ),
                FilterChip(
                    id = "paneer_momos",
                    text = "Paneer Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_multigrain
                ),
                FilterChip(
                    id = "afghani_momos",
                    text = "Afghani Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_pan
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rating_4",
                    text = "Rating 4.0+",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "fried_momos",
                    text = "Fried Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_paneer
                ),
                FilterChip(
                    id = "steam_momos",
                    text = "Steam Momos",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_non_veg_pepperoni
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = momosFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        val completeMomoFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.momo_king_momos,
                title = "Steam Chicken Momos",
                price = "120",
                restaurantName = "Momos King",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "30%",
                discountAmount = "up to ₹60",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.delhi_momo_point_momos,
                title = "Fried Veg Momos",
                price = "90",
                restaurantName = "Delhi Momo Point",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.tibetan_momo_house_momos,
                title = "Tandoori Momos",
                price = "150",
                restaurantName = "Tibetan Momo House",
                rating = "4.7",
                deliveryTime = "30-35 mins",
                distance = "4.5 km",
                discount = "25%",
                discountAmount = "up to ₹75",
                address = "Majnu Ka Tila, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.spicy_momo_corner_momos,
                title = "Cheese Chilli Momos",
                price = "140",
                restaurantName = "Spicy Momo Corner",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "3.8 km",
                discount = "15%",
                discountAmount = "up to ₹50",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.himalayan_momo_zone_momos,
                title = "Paneer Momos",
                price = "110",
                restaurantName = "Himalayan Momo Zone",
                rating = "4.6",
                deliveryTime = "35-40 mins",
                distance = "5.2 km",
                discount = "30%",
                discountAmount = "up to ₹80",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.momo_express_momos,
                title = "Chicken Kothey Momos",
                price = "130",
                restaurantName = "Momo Express",
                rating = "4.2",
                deliveryTime = "20-25 mins",
                distance = "2.8 km",
                discount = "20%",
                discountAmount = "up to ₹60",
                address = "Karol Bagh, Delhi"
            ),
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeMomoFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        // Sample data based on the provided images
        val sampleMomoItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.momo_steam_chicken,
                title = "Steam Chicken Momos",
                price = "120",
                restaurantName = "Momos King",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "30% OFF",
                discountAmount = "up to ₹60",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.momo_fried_veg,
                title = "Fried Veg Momos",
                price = "90",
                restaurantName = "Delhi Momo Point",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.momo_tandoori,
                title = "Tandoori Momos",
                price = "150",
                restaurantName = "Tibetan Momo House",
                rating = "4.7",
                deliveryTime = "30-35 mins",
                distance = "4.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹75",
                address = "Majnu Ka Tila, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.momo_cheese_chilli,
                title = "Cheese Chilli Momos",
                price = "140",
                restaurantName = "Spicy Momo Corner",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "3.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹50",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.momo_paneer,
                title = "Paneer Momos",
                price = "110",
                restaurantName = "Himalayan Momo Zone",
                rating = "4.6",
                deliveryTime = "35-40 mins",
                distance = "5.2 km",
                discount = "30% OFF",
                discountAmount = "up to ₹80",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.momo_kothey,
                title = "Chicken Kothey Momos",
                price = "130",
                restaurantName = "Momo Express",
                rating = "4.2",
                deliveryTime = "20-25 mins",
                distance = "2.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹60",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.momo_schezwan,
                title = "Schezwan Momos",
                price = "160",
                restaurantName = "Dragon Momo",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "4.1 km",
                discount = "25% OFF",
                discountAmount = "up to ₹90",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.momo_jhol,
                title = "Jhol Momos",
                price = "125",
                restaurantName = "Momo World",
                rating = "4.3",
                deliveryTime = "25-30 mins",
                distance = "3.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹70",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.momo_buff,
                title = "Buff Momos",
                price = "170",
                restaurantName = "Kathmandu Momo",
                rating = "4.8",
                deliveryTime = "40-45 mins",
                distance = "6.3 km",
                discount = "30% OFF",
                discountAmount = "up to ₹100",
                address = "CR Park, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.momo_afghani,
                title = "Afghani Momos",
                price = "145",
                restaurantName = "Momo Magic",
                rating = "4.4",
                deliveryTime = "30-35 mins",
                distance = "4.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹55",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.momo_crispy,
                title = "Chicken Crispy Momos",
                price = "135",
                restaurantName = "Spice Momo",
                rating = "4.1",
                deliveryTime = "25-30 mins",
                distance = "3.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹65",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.momo_veg_fried,
                title = "Veg Fried Momos",
                price = "85",
                restaurantName = "Momo Factory",
                rating = "4.0",
                deliveryTime = "20-25 mins",
                distance = "2.4 km",
                discount = "25% OFF",
                discountAmount = "up to ₹50",
                address = "Vikas Puri, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.momo_chilli_garlic,
                title = "Chilli Garlic Momos",
                price = "125",
                restaurantName = "Hot Momo",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "3.9 km",
                discount = "30% OFF",
                discountAmount = "up to ₹75",
                address = "Paschim Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.momo_paneer_fried,
                title = "Paneer Fried Momos",
                price = "115",
                restaurantName = "Momo Love",
                rating = "4.3",
                deliveryTime = "25-30 mins",
                distance = "2.7 km",
                discount = "20% OFF",
                discountAmount = "up to ₹60",
                address = "Patel Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.momo_chicken_steam,
                title = "Chicken Steam Momos",
                price = "110",
                restaurantName = "Quick Momo",
                rating = "4.2",
                deliveryTime = "15-20 mins",
                distance = "1.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹40",
                address = "Rajendra Place, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.momo_chicken_cheese,
                title = "Chicken Cheese Momos",
                price = "155",
                restaurantName = "Premium Momo",
                rating = "4.7",
                deliveryTime = "35-40 mins",
                distance = "4.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹85",
                address = "Vasant Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.momo_veg_steam,
                title = "Veg Steam Momos",
                price = "80",
                restaurantName = "Momo Hut",
                rating = "4.1",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "30% OFF",
                discountAmount = "up to ₹45",
                address = "Shalimar Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.momo_platter,
                title = "Mix Momos Platter",
                price = "200",
                restaurantName = "Momo Paradise",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "3.6 km",
                discount = "20% OFF",
                discountAmount = "up to ₹80",
                address = "Model Town, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.momo_chicken_fried,
                title = "Chicken Fried Momos",
                price = "125",
                restaurantName = "Momo Bite",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "2.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹70",
                address = "Kirti Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.momo_veg_kothey,
                title = "Veg Kothey Momos",
                price = "95",
                restaurantName = "Fresh Momo",
                rating = "4.0",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Naraina, Delhi"
            )
        )

        Column {
            sampleMomoItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun RollsCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val rollsFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "chaap",
                    text = "Chaap",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_cheese_burst
                ),
                FilterChip(
                    id = "thai",
                    text = "Thai",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_farmhouse
                ),
                FilterChip(
                    id = "chicken",
                    text = "Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_margherita
                ),
                FilterChip(
                    id = "frankie",
                    text = "Frankie",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_multigrain
                ),
                FilterChip(
                    id = "kaathi",
                    text = "Kaathi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_pan
                ),
                FilterChip(
                    id = "base_lachcha",
                    text = "Base: Lachcha",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_100",
                    text = "Under 100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rating_4",
                    text = "Rating 4.0+",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pure_veg",
                    text = "Pure Veg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "paneer",
                    text = "Paneer",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_paneer
                ),
                FilterChip(
                    id = "soya",
                    text = "Soya",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_non_veg_pepperoni
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = rollsFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        val completeRollFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.rolls_king_rolls,
                title = "Chicken Kathi Roll",
                price = "110",
                restaurantName = "Rolls King",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "30%",
                discountAmount = "up to ₹70",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.delhi_roll_point_rolls,
                title = "Paneer Tikka Roll",
                price = "95",
                restaurantName = "Delhi Roll Point",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹45",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.mughlai_roll_house_rolls,
                title = "Mughlai Chicken Roll",
                price = "130",
                restaurantName = "Mughlai Roll House",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "25%",
                discountAmount = "up to ₹80",
                address = "Chandni Chowk, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.spicy_roll_corner_rolls,
                title = "Egg Chicken Roll",
                price = "120",
                restaurantName = "Spicy Roll Corner",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.9 km",
                discount = "15%",
                discountAmount = "up to ₹55",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.kolkata_roll_zone_rolls,
                title = "Kolkata Egg Roll",
                price = "85",
                restaurantName = "Kolkata Roll Zone",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "4.1 km",
                discount = "30%",
                discountAmount = "up to ₹60",
                address = "CR Park, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.roll_express_rolls,
                title = "Mutton Seekh Roll",
                price = "150",
                restaurantName = "Roll Express",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "3.5 km",
                discount = "20%",
                discountAmount = "up to ₹75",
                address = "Karol Bagh, Delhi"
            ),
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeRollFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleRollItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.roll_chicken_kathi,
                title = "Chicken Kathi Roll",
                price = "110",
                restaurantName = "Rolls King",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "30% OFF",
                discountAmount = "up to ₹70",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.roll_paneer_tikka,
                title = "Paneer Tikka Roll",
                price = "95",
                restaurantName = "Delhi Roll Point",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.roll_mughlai_chicken,
                title = "Mughlai Chicken Roll",
                price = "130",
                restaurantName = "Mughlai Roll House",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Chandni Chowk, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.roll_egg_chicken,
                title = "Egg Chicken Roll",
                price = "120",
                restaurantName = "Spicy Roll Corner",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹55",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.roll_kolkata_egg,
                title = "Kolkata Egg Roll",
                price = "85",
                restaurantName = "Kolkata Roll Zone",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "4.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹60",
                address = "CR Park, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.roll_mutton_seekh,
                title = "Mutton Seekh Roll",
                price = "150",
                restaurantName = "Roll Express",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "3.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹75",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.roll_tandoori_paneer,
                title = "Tandoori Paneer Roll",
                price = "105",
                restaurantName = "Tandoori Roll Factory",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.7 km",
                discount = "25% OFF",
                discountAmount = "up to ₹65",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.roll_afghani_chicken,
                title = "Afghani Chicken Roll",
                price = "140",
                restaurantName = "Afghani Roll House",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "4.3 km",
                discount = "30% OFF",
                discountAmount = "up to ₹85",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.roll_veg_schezwan,
                title = "Veg Schezwan Roll",
                price = "90",
                restaurantName = "Veg Roll Paradise",
                rating = "4.2",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.roll_double_egg_chicken,
                title = "Double Egg Chicken Roll",
                price = "135",
                restaurantName = "Non-Veg Roll Masters",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.roll_cheese_chilli,
                title = "Cheese Chilli Chicken Roll",
                price = "125",
                restaurantName = "Cheese Roll Corner",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "2.4 km",
                discount = "15% OFF",
                discountAmount = "up to ₹50",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.roll_hyderabadi_chicken,
                title = "Hyderabadi Chicken Roll",
                price = "115",
                restaurantName = "Hyderabadi Roll House",
                rating = "4.3",
                deliveryTime = "30-35 mins",
                distance = "4.6 km",
                discount = "30% OFF",
                discountAmount = "up to ₹70",
                address = "Jasola, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.roll_butter_chicken,
                title = "Butter Chicken Roll",
                price = "145",
                restaurantName = "Butter Chicken Roll Shop",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "3.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹75",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.roll_malai_chicken,
                title = "Malai Chicken Roll",
                price = "120",
                restaurantName = "Malai Roll Kitchen",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "2.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹65",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.roll_spicy_mutton,
                title = "Spicy Mutton Roll",
                price = "160",
                restaurantName = "Spicy Mutton Roll House",
                rating = "4.7",
                deliveryTime = "35-40 mins",
                distance = "5.2 km",
                discount = "30% OFF",
                discountAmount = "up to ₹95",
                address = "Mehrauli, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.roll_double_egg,
                title = "Double Egg Roll",
                price = "75",
                restaurantName = "Egg Special Rolls",
                rating = "4.1",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Shahdara, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.roll_chicken_tikka,
                title = "Chicken Tikka Roll",
                price = "135",
                restaurantName = "Chicken Tikka Roll Center",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "3.4 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Paschim Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.roll_paneer_butter,
                title = "Paneer Butter Masala Roll",
                price = "110",
                restaurantName = "Paneer Roll Specialists",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.6 km",
                discount = "15% OFF",
                discountAmount = "up to ₹45",
                address = "Patel Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.roll_fish_tikka,
                title = "Fish Tikka Roll",
                price = "170",
                restaurantName = "Fish Roll Experts",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "4.8 km",
                discount = "30% OFF",
                discountAmount = "up to ₹100",
                address = "Mayur Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.roll_mixed_combo,
                title = "Mixed Combo Roll",
                price = "155",
                restaurantName = "Roll Combo House",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "3.7 km",
                discount = "25% OFF",
                discountAmount = "up to ₹90",
                address = "Nehru Place, Delhi"
            )
        )

        Column {
            sampleRollItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun BurgersCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val burgersFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "egg",
                    text = "Egg",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_cheese_burst
                ),
                FilterChip(
                    id = "fish",
                    text = "Fish",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_farmhouse
                ),
                FilterChip(
                    id = "margherita",
                    text = "Cheese: Mozzarella",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_margherita
                ),
                FilterChip(
                    id = "multigrain",
                    text = "Multigrain",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_multigrain
                ),
                FilterChip(
                    id = "pan",
                    text = "Fried Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_pan
                ),
                FilterChip(
                    id = "paneer",
                    text = "Paneer",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_pan
                ),
                FilterChip(
                    id = "dressing_tandoori",
                    text = "Dressing: Tandoori",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pizza_pan
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rating_4",
                    text = "Rating 4.0+",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pure_veg",
                    text = "Pure Veg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "dressing_peri_peri",
                    text = "Dressing: Peri Peri",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_paneer
                ),
                FilterChip(
                    id = "pepperoni",
                    text = "Dressing: Salsa",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_non_veg_pepperoni
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = burgersFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        val completeBurgerFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.burger_king_burgers,
                title = "Classic Chicken Burger",
                price = "145",
                restaurantName = "Burger King",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "30%",
                discountAmount = "up to ₹80",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.mcdonalds_burgers,
                title = "McAloo Tikki Burger",
                price = "65",
                restaurantName = "McDonald's",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹30",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.burger_singh_burgers,
                title = "Butter Chicken Burger",
                price = "175",
                restaurantName = "Burger Singh",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "3.1 km",
                discount = "25%",
                discountAmount = "up to ₹95",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.wendys_burgers,
                title = "Bacon Cheeseburger",
                price = "195",
                restaurantName = "Wendy's",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "2.8 km",
                discount = "15%",
                discountAmount = "up to ₹60",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.local_burger_corner_burgers,
                title = "Veg Supreme Burger",
                price = "85",
                restaurantName = "Local Burger Corner",
                rating = "4.2",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "30%",
                discountAmount = "up to ₹45",
                address = "Karol Bagh, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.premium_burger_house_burgers,
                title = "Double Cheese Burger",
                price = "165",
                restaurantName = "Premium Burger House",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.4 km",
                discount = "20%",
                discountAmount = "up to ₹75",
                address = "Greater Kailash, Delhi"
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeBurgerFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val sampleBurgerItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.burger_classic_chicken,
            title = "Classic Chicken Burger",
            price = "120",
            restaurantName = "Burger King",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.3 km",
            discount = "30% OFF",
            discountAmount = "up to ₹75",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.burger_cheese_blast,
            title = "Cheese Blast Burger",
            price = "135",
            restaurantName = "Cheese Burger Hub",
            rating = "4.6",
            deliveryTime = "15-20 mins",
            distance = "1.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Lajpat Nagar, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.burger_mcspicy_chicken,
            title = "McSpicy Chicken Burger",
            price = "110",
            restaurantName = "McDonald's",
            rating = "4.4",
            deliveryTime = "10-15 mins",
            distance = "1.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹65",
            address = "Saket, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.burger_double_pattie,
            title = "Double Pattie Burger",
            price = "160",
            restaurantName = "Double Patty House",
            rating = "4.7",
            deliveryTime = "25-30 mins",
            distance = "3.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹70",
            address = "Rajouri Garden, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.burger_veg_maharaja,
            title = "Veg Maharaja Burger",
            price = "95",
            restaurantName = "Veg Burger Palace",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "2.5 km",
            discount = "30% OFF",
            discountAmount = "up to ₹55",
            address = "CR Park, Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.burger_chicken_tikka,
            title = "Chicken Tikka Burger",
            price = "125",
            restaurantName = "Tikka Burger Point",
            rating = "4.5",
            deliveryTime = "18-22 mins",
            distance = "2.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Karol Bagh, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.burger_bacon_cheese,
            title = "Bacon Cheese Burger",
            price = "175",
            restaurantName = "Bacon Burger Factory",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "3.4 km",
            discount = "25% OFF",
            discountAmount = "up to ₹85",
            address = "Greater Kailash, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.burger_mushroom_swiss,
            title = "Mushroom Swiss Burger",
            price = "140",
            restaurantName = "Gourmet Burger Co.",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "4.2 km",
            discount = "30% OFF",
            discountAmount = "up to ₹80",
            address = "Vasant Kunj, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.burger_spicy_veg,
            title = "Spicy Veg Burger",
            price = "85",
            restaurantName = "Spicy Burger Corner",
            rating = "4.2",
            deliveryTime = "15-20 mins",
            distance = "1.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Pitampura, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.burger_triple_cheese,
            title = "Triple Cheese Burger",
            price = "150",
            restaurantName = "Cheese Lovers Burger",
            rating = "4.7",
            deliveryTime = "22-27 mins",
            distance = "2.9 km",
            discount = "25% OFF",
            discountAmount = "up to ₹75",
            address = "Rohini, Delhi"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.burger_crispy_chicken,
            title = "Crispy Chicken Burger",
            price = "115",
            restaurantName = "Crispy Burger Joint",
            rating = "4.4",
            deliveryTime = "20-25 mins",
            distance = "2.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹50",
            address = "Dwarka, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.burger_bbq_chicken,
            title = "BBQ Chicken Burger",
            price = "130",
            restaurantName = "BBQ Burger House",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.3 km",
            discount = "30% OFF",
            discountAmount = "up to ₹75",
            address = "Jasola, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.burger_aloo_tikki,
            title = "Aloo Tikki Burger",
            price = "70",
            restaurantName = "Desi Burger Point",
            rating = "4.1",
            deliveryTime = "15-20 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹35",
            address = "Shahdara, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.burger_jalapeno_cheese,
            title = "Jalapeno Cheese Burger",
            price = "145",
            restaurantName = "Jalapeno Burger Co.",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.7 km",
            discount = "25% OFF",
            discountAmount = "up to ₹70",
            address = "Janakpuri, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.burger_mutton_pattie,
            title = "Mutton Pattie Burger",
            price = "165",
            restaurantName = "Mutton Burger Specialists",
            rating = "4.7",
            deliveryTime = "30-35 mins",
            distance = "4.5 km",
            discount = "30% OFF",
            discountAmount = "up to ₹95",
            address = "Mehrauli, Delhi"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.burger_fish_fillet,
            title = "Fish Fillet Burger",
            price = "155",
            restaurantName = "Fish Burger Experts",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "3.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹75",
            address = "Mayur Vihar, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.burger_paneer_tikka,
            title = "Paneer Tikka Burger",
            price = "105",
            restaurantName = "Paneer Burger Center",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "2.6 km",
            discount = "25% OFF",
            discountAmount = "up to ₹60",
            address = "Paschim Vihar, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.burger_chicken_premium,
            title = "Chicken Premium Burger",
            price = "180",
            restaurantName = "Premium Burger House",
            rating = "4.8",
            deliveryTime = "30-35 mins",
            distance = "4.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹80",
            address = "Nehru Place, Delhi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.burger_veg_crunchy,
            title = "Veg Crunchy Burger",
            price = "90",
            restaurantName = "Crunchy Burger Spot",
            rating = "4.2",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹50",
            address = "Patel Nagar, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.burger_combo_special,
            title = "Combo Special Burger",
            price = "195",
            restaurantName = "Burger Combo Masters",
            rating = "4.6",
            deliveryTime = "25-30 mins",
            distance = "3.6 km",
            discount = "25% OFF",
            discountAmount = "up to ₹95",
            address = "Dwarka Sector 21, Delhi"
        )
    )
    Column {
        sampleBurgerItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun CholeBhatureCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val choleBhatureFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rating_4",
                    text = "Rating 4.0+",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pure_veg",
                    text = "Pure Veg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 1
        )
        FilterButtonFood(
            filterConfig = choleBhatureFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        val completeCholeBhatureFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.sitaram_chole_bhature,
                title = "Special Chole Bhature",
                price = "120",
                restaurantName = "Sitaram Diwan Chand",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹50",
                address = "Paharganj, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.bille_di_hatti_chole_bhature,
                title = "Classic Chole Bhature",
                price = "90",
                restaurantName = "Bille Di Hatti",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "15%",
                discountAmount = "up to ₹30",
                address = "Kamla Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.chache_di_hatti_chole_bhature,
                title = "Punjabi Chole Bhature",
                price = "110",
                restaurantName = "Chache Di Hatti",
                rating = "4.5",
                deliveryTime = "15-18 mins",
                distance = "1.2 km",
                discount = "25%",
                discountAmount = "up to ₹55",
                address = "Shakti Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.nathu_chole_bhature,
                title = "Deluxe Chole Bhature",
                price = "150",
                restaurantName = "Nathu's Sweets",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "30%",
                discountAmount = "up to ₹75",
                address = "Bengali Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.bengali_sweet_chole_bhature,
                title = "Masala Chole Bhature",
                price = "100",
                restaurantName = "Bengali Sweet House",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Chandni Chowk, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.premium_chole_bhature,
                title = "Royal Chole Bhature",
                price = "180",
                restaurantName = "Premium Chole Corner",
                rating = "4.7",
                deliveryTime = "30-35 mins",
                distance = "4.0 km",
                discount = "15%",
                discountAmount = "up to ₹60",
                address = "South Extension, Delhi"
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeCholeBhatureFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )


        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleCholeBhatureItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.chole_bhature_classic,
                title = "Classic Chole Bhature",
                price = "120",
                restaurantName = "Sitaram Diwan Chand",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Paharganj, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.chole_bhature_premium,
                title = "Premium Chole Bhature",
                price = "150",
                restaurantName = "Bille Di Hatti",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹45",
                address = "Kamla Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.chole_bhature_punjabi,
                title = "Punjabi Chole Bhature",
                price = "110",
                restaurantName = "Chache Di Hatti",
                rating = "4.5",
                deliveryTime = "15-18 mins",
                distance = "1.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹55",
                address = "Shakti Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.chole_bhature_deluxe,
                title = "Deluxe Chole Bhature",
                price = "180",
                restaurantName = "Nathu's Sweets",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "30% OFF",
                discountAmount = "up to ₹90",
                address = "Bengali Market, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.chole_bhature_masala,
                title = "Masala Chole Bhature",
                price = "100",
                restaurantName = "Bengali Sweet House",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Chandni Chowk, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.chole_bhature_royal,
                title = "Royal Chole Bhature",
                price = "200",
                restaurantName = "Premium Chole Corner",
                rating = "4.7",
                deliveryTime = "30-35 mins",
                distance = "4.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹60",
                address = "South Extension, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.chole_bhature_spicy,
                title = "Spicy Chole Bhature",
                price = "95",
                restaurantName = "Spicy Chole Point",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹50",
                address = "Laxmi Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.chole_bhature_butter,
                title = "Butter Chole Bhature",
                price = "140",
                restaurantName = "Butter Chole House",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.8 km",
                discount = "30% OFF",
                discountAmount = "up to ₹70",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.chole_bhature_amritsari,
                title = "Amritsari Chole Bhature",
                price = "130",
                restaurantName = "Amritsari Kulcha Hut",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹55",
                address = "Paschim Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.chole_bhature_combo,
                title = "Chole Bhature Combo",
                price = "160",
                restaurantName = "Combo Chole Center",
                rating = "4.5",
                deliveryTime = "18-22 mins",
                distance = "2.3 km",
                discount = "25% OFF",
                discountAmount = "up to ₹75",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.chole_bhature_family,
                title = "Family Pack Chole Bhature",
                price = "220",
                restaurantName = "Family Chole House",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "4.2 km",
                discount = "30% OFF",
                discountAmount = "up to ₹110",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.chole_bhature_extra,
                title = "Extra Bhature Chole",
                price = "135",
                restaurantName = "Extra Bhature Point",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.6 km",
                discount = "15% OFF",
                discountAmount = "up to ₹40",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.chole_bhature_special,
                title = "Special Chole Bhature",
                price = "125",
                restaurantName = "Special Chole Corner",
                rating = "4.6",
                deliveryTime = "15-20 mins",
                distance = "1.9 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Patel Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.chole_bhature_tandoori,
                title = "Tandoori Bhature Chole",
                price = "145",
                restaurantName = "Tandoori Bhature Co.",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "3.1 km",
                discount = "25% OFF",
                discountAmount = "up to ₹70",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.chole_bhature_cheese,
                title = "Cheese Chole Bhature",
                price = "170",
                restaurantName = "Cheese Chole Specialists",
                rating = "4.7",
                deliveryTime = "30-35 mins",
                distance = "4.3 km",
                discount = "30% OFF",
                discountAmount = "up to ₹85",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.chole_bhature_egg,
                title = "Egg Chole Bhature",
                price = "155",
                restaurantName = "Egg Chole Experts",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "2.9 km",
                discount = "20% OFF",
                discountAmount = "up to ₹65",
                address = "Mayur Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.chole_bhature_paneer,
                title = "Paneer Chole Bhature",
                price = "165",
                restaurantName = "Paneer Chole Center",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "3.4 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Nehru Place, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.chole_bhature_express,
                title = "Express Chole Bhature",
                price = "90",
                restaurantName = "Express Chole Spot",
                rating = "4.2",
                deliveryTime = "10-15 mins",
                distance = "1.3 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Shahdara, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                    imageRes = R.drawable.chole_bhature_gourmet,
                title = "Gourmet Chole Bhature",
                price = "190",
                restaurantName = "Gourmet Chole Masters",
                rating = "4.8",
                deliveryTime = "35-40 mins",
                distance = "4.8 km",
                discount = "30% OFF",
                discountAmount = "up to ₹95",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.chole_bhature_mega,
                title = "Mega Chole Bhature",
                price = "240",
                restaurantName = "Mega Chole House",
                rating = "4.7",
                deliveryTime = "30-35 mins",
                distance = "3.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹120",
                address = "Saket, Delhi"
            )
        )
        Column {
            sampleCholeBhatureItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun SaladCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val saladFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "vegetarian",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein",
                    text = "High-Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_cal",
                    text = "Low-Cal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten-Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 1
        )
        FilterButtonFood(
            filterConfig = saladFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        val completeSaladFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.greek_salad,
                title = "Classic Greek Salad",
                price = "320",
                restaurantName = "Mediterranean Delight",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹80",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.caesar_salad,
                title = "Chicken Caesar Salad",
                price = "280",
                restaurantName = "Fresh Greens Cafe",
                rating = "4.6",
                deliveryTime = "12-15 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹50",
                address = "Hauz Khas, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.quinoa_salad,
                title = "Protein Quinoa Bowl",
                price = "350",
                restaurantName = "Health Hub",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.2 km",
                discount = "25%",
                discountAmount = "up to ₹90",
                address = "Greater Kailash, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.fruit_salad,
                title = "Tropical Fruit Salad",
                price = "240",
                restaurantName = "Nature's Basket",
                rating = "4.5",
                deliveryTime = "10-15 mins",
                distance = "1.0 km",
                discount = "30%",
                discountAmount = "up to ₹75",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.veg_salad,
                title = "Garden Fresh Salad",
                price = "190",
                restaurantName = "Green Leaf Restaurant",
                rating = "4.4",
                deliveryTime = "15-18 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.premium_salad,
                title = "Premium Avocado Salad",
                price = "420",
                restaurantName = "Organic Kitchen",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "3.0 km",
                discount = "15%",
                discountAmount = "up to ₹65",
                address = "Vasant Kunj, Delhi"
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeSaladFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )


        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleSaladItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.salad_greek,
                title = "Greek Salad",
                price = "180",
                restaurantName = "Mediterranean Delight",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Hauz Khas, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.salad_caesar,
                title = "Classic Caesar Salad",
                price = "220",
                restaurantName = "Italian Bistro",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹50",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.salad_quinoa,
                title = "Quinoa Power Salad",
                price = "250",
                restaurantName = "Health Hub",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹75",
                address = "Vasant Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.salad_fruit,
                title = "Fresh Fruit Salad",
                price = "160",
                restaurantName = "Fruit Paradise",
                rating = "4.5",
                deliveryTime = "10-15 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Green Park, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.salad_chicken,
                title = "Grilled Chicken Salad",
                price = "280",
                restaurantName = "Protein House",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹60",
                address = "Defence Colony, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.salad_avocado,
                title = "Avocado Spinach Salad",
                price = "240",
                restaurantName = "Green Garden",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹85",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.salad_thai,
                title = "Thai Green Papaya Salad",
                price = "190",
                restaurantName = "Asian Flavors",
                rating = "4.4",
                deliveryTime = "22-27 mins",
                distance = "3.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.salad_tuna,
                title = "Tuna Nicoise Salad",
                price = "320",
                restaurantName = "Seafood Specialists",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "3.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹100",
                address = "Nehru Place, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.salad_cobb,
                title = "Cobb Salad",
                price = "270",
                restaurantName = "American Diner",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "2.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹55",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.salad_waldorf,
                title = "Waldorf Salad",
                price = "210",
                restaurantName = "Classic Cuisine",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.salad_caprese,
                title = "Caprese Salad",
                price = "230",
                restaurantName = "Italian Express",
                rating = "4.7",
                deliveryTime = "18-23 mins",
                distance = "1.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹70",
                address = "Chanakyapuri, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.salad_mexican,
                title = "Mexican Fiesta Salad",
                price = "200",
                restaurantName = "Mexican Grill",
                rating = "4.6",
                deliveryTime = "22-28 mins",
                distance = "3.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹75",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.salad_kale,
                title = "Kale Caesar Salad",
                price = "260",
                restaurantName = "Superfood Cafe",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.7 km",
                discount = "20% OFF",
                discountAmount = "up to ₹65",
                address = "Lodhi Road, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.salad_beetroot,
                title = "Beetroot & Feta Salad",
                price = "195",
                restaurantName = "Healthy Bites",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.6 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Malviya Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.salad_pasta,
                title = "Pasta Salad",
                price = "180",
                restaurantName = "Italian Corner",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "25% OFF",
                discountAmount = "up to ₹55",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.salad_mediterranean,
                title = "Mediterranean Bowl",
                price = "290",
                restaurantName = "Bowl Company",
                rating = "4.7",
                deliveryTime = "28-33 mins",
                distance = "3.8 km",
                discount = "30% OFF",
                discountAmount = "up to ₹95",
                address = "South Extension, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.salad_sprouts,
                title = "Sprout & Veggie Salad",
                price = "170",
                restaurantName = "Sprout Heaven",
                rating = "4.5",
                deliveryTime = "12-17 mins",
                distance = "1.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.salad_egg,
                title = "Egg & Bacon Salad",
                price = "240",
                restaurantName = "Breakfast Club",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹45",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.salad_asian,
                title = "Asian Chicken Salad",
                price = "270",
                restaurantName = "Pan Asian",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "3.3 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.salad_detox,
                title = "Detox Green Salad",
                price = "220",
                restaurantName = "Detox Kitchen",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹75",
                address = "Kailash Colony, Delhi"
            )
        )
        Column {
            sampleSaladItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun PattyCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val pattyFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "beef",
                    text = "Beef",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chicken",
                    text = "Chicken",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegetarian",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quick",
                    text = "Quick",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten-Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Schedule",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = pattyFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completePattyFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.classic_beef_patty,
                title = "Classic Beef Burger",
                price = "320",
                restaurantName = "Burger Junction",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹80",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.chicken_patty,
                title = "Grilled Chicken Patty",
                price = "280",
                restaurantName = "Chicken Express",
                rating = "4.6",
                deliveryTime = "12-15 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹50",
                address = "Hauz Khas, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.veg_patty,
                title = "Spicy Veg Patty",
                price = "190",
                restaurantName = "Green Bites",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.2 km",
                discount = "25%",
                discountAmount = "up to ₹90",
                address = "Greater Kailash, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.cheese_patty,
                title = "Cheese Stuffed Patty",
                price = "350",
                restaurantName = "Cheese Lovers",
                rating = "4.5",
                deliveryTime = "10-15 mins",
                distance = "1.0 km",
                discount = "30%",
                discountAmount = "up to ₹75",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.turkey_patty,
                title = "Lean Turkey Burger",
                price = "310",
                restaurantName = "Healthy Grill",
                rating = "4.4",
                deliveryTime = "15-18 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.premium_patty,
                title = "Premium Angus Beef",
                price = "420",
                restaurantName = "Gourmet Burgers",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "3.0 km",
                discount = "15%",
                discountAmount = "up to ₹65",
                address = "Vasant Kunj, Delhi"
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completePattyFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )


        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val samplePattyItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.patty_classic_beef,
                title = "Classic Beef Patty",
                price = "180",
                restaurantName = "Butcher's Best",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Hauz Khas, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.patty_chicken_grill,
                title = "Grilled Chicken Patty",
                price = "220",
                restaurantName = "Chicken Masters",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹50",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.patty_veg_spicy,
                title = "Spicy Veg Patty",
                price = "150",
                restaurantName = "Vegetarian Delight",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹75",
                address = "Vasant Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.patty_cheese_stuffed,
                title = "Cheese Stuffed Patty",
                price = "250",
                restaurantName = "Cheese Lovers",
                rating = "4.5",
                deliveryTime = "10-15 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Green Park, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.patty_turkey_lean,
                title = "Lean Turkey Patty",
                price = "280",
                restaurantName = "Healthy Protein",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹60",
                address = "Defence Colony, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.patty_lamb_herb,
                title = "Herb Lamb Patty",
                price = "320",
                restaurantName = "Middle Eastern Grill",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹85",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.patty_fish_crispy,
                title = "Crispy Fish Patty",
                price = "290",
                restaurantName = "Seafood Express",
                rating = "4.4",
                deliveryTime = "22-27 mins",
                distance = "3.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.patty_pork_bbq,
                title = "BBQ Pork Patty",
                price = "340",
                restaurantName = "Smoke House",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "3.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹100",
                address = "Nehru Place, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.patty_mushroom_veg,
                title = "Mushroom Veg Patty",
                price = "170",
                restaurantName = "Mushroom Magic",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "2.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹55",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.patty_chicken_tandoori,
                title = "Tandoori Chicken Patty",
                price = "240",
                restaurantName = "Indian Grill",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.patty_beef_pepper,
                title = "Pepper Beef Patty",
                price = "260",
                restaurantName = "Pepper Mill",
                rating = "4.7",
                deliveryTime = "18-23 mins",
                distance = "1.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹70",
                address = "Chanakyapuri, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.patty_veg_corn,
                title = "Sweet Corn Patty",
                price = "130",
                restaurantName = "Corn Factory",
                rating = "4.6",
                deliveryTime = "22-28 mins",
                distance = "3.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹75",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.patty_chicken_herb,
                title = "Herb Chicken Patty",
                price = "230",
                restaurantName = "Herb Kitchen",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.7 km",
                discount = "20% OFF",
                discountAmount = "up to ₹65",
                address = "Lodhi Road, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.patty_paneer_spicy,
                title = "Spicy Paneer Patty",
                price = "195",
                restaurantName = "Paneer Special",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.6 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Malviya Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.patty_beef_cheese,
                title = "Beef Cheese Patty",
                price = "300",
                restaurantName = "Cheese & Beef Co.",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "25% OFF",
                discountAmount = "up to ₹55",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.patty_veg_mix,
                title = "Mixed Veg Patty",
                price = "140",
                restaurantName = "Veggie World",
                rating = "4.7",
                deliveryTime = "28-33 mins",
                distance = "3.8 km",
                discount = "30% OFF",
                discountAmount = "up to ₹95",
                address = "South Extension, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.patty_chicken_crunchy,
                title = "Crunchy Chicken Patty",
                price = "210",
                restaurantName = "Crispy Corner",
                rating = "4.5",
                deliveryTime = "12-17 mins",
                distance = "1.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.patty_lamb_spicy,
                title = "Spicy Lamb Patty",
                price = "350",
                restaurantName = "Lamb Experts",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹45",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.patty_fish_herb,
                title = "Herb Fish Patty",
                price = "270",
                restaurantName = "Ocean Fresh",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "3.3 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.patty_veg_soya,
                title = "Soya Protein Patty",
                price = "160",
                restaurantName = "Protein Plus",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹75",
                address = "Kailash Colony, Delhi"
            )
        )
        Column {
            samplePattyItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun ChineseCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val chineseFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "sichuan",
                    text = "Sichuan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sichuan
                ),
                FilterChip(
                    id = "cantonese",
                    text = "Cantonese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hunan",
                    text = "Hunan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "shanghai",
                    text = "Shanghai",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "beijing",
                    text = "Beijing",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dim_sum",
                    text = "Dim Sum",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "noodles",
                    text = "Noodles",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rice",
                    text = "Rice Dishes",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegetarian",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "seafood",
                    text = "Seafood",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "soup",
                    text = "Soups",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quick",
                    text = "Quick",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy",
                    text = "Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "schedule",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = chineseFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeChineseFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.kung_pao_chicken_chinese,
                title = "Kung Pao Chicken",
                price = "320",
                restaurantName = "Sichuan Delight",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹80",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.sweet_sour_pork_chinese,
                title = "Sweet and Sour Pork",
                price = "280",
                restaurantName = "Cantonese Express",
                rating = "4.6",
                deliveryTime = "12-15 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹50",
                address = "Hauz Khas, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.vegetable_noodles_chinese,
                title = "Vegetable Hakka Noodles",
                price = "190",
                restaurantName = "Green Wok",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.2 km",
                discount = "25%",
                discountAmount = "up to ₹90",
                address = "Greater Kailash, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.peking_duck_chinese,
                title = "Peking Duck",
                price = "350",
                restaurantName = "Beijing Palace",
                rating = "4.5",
                deliveryTime = "10-15 mins",
                distance = "1.0 km",
                discount = "30%",
                discountAmount = "up to ₹75",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.steamed_fish_with_ginger_chinese,
                title = "Steamed Fish with Ginger",
                price = "310",
                restaurantName = "Healthy Wok",
                rating = "4.4",
                deliveryTime = "15-18 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.dim_sum_platter_chinese,
                title = "Dim Sum Platter",
                price = "420",
                restaurantName = "Gourmet Chinese",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "3.0 km",
                discount = "15%",
                discountAmount = "up to ₹65",
                address = "Vasant Kunj, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeChineseFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )


        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleChineseItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.chinese_manchurian,
                title = "Veg Manchurian",
                price = "220",
                restaurantName = "Dragon Wok",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.chinese_hakka_noodles,
                title = "Hakka Noodles",
                price = "180",
                restaurantName = "Noodle House",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Hauz Khas, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.chinese_schezwan_rice,
                title = "Schezwan Fried Rice",
                price = "200",
                restaurantName = "Spice Garden",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.1 km",
                discount = "25% OFF",
                discountAmount = "up to ₹60",
                address = "Vasant Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.chinese_dimsum,
                title = "Steamed Dimsums",
                price = "160",
                restaurantName = "Dimsum Delight",
                rating = "4.8",
                deliveryTime = "12-15 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Green Park, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.chinese_chilli_chicken,
                title = "Chilli Chicken",
                price = "280",
                restaurantName = "Chicken Express",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "15% OFF",
                discountAmount = "up to ₹55",
                address = "Defence Colony, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.chinese_spring_rolls,
                title = "Crispy Spring Rolls",
                price = "140",
                restaurantName = "Rolling Wok",
                rating = "4.4",
                deliveryTime = "15-18 mins",
                distance = "1.5 km",
                discount = "30% OFF",
                discountAmount = "up to ₹65",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.chinese_sweet_sour,
                title = "Sweet & Sour Vegetables",
                price = "190",
                restaurantName = "Wok This Way",
                rating = "4.5",
                deliveryTime = "22-27 mins",
                distance = "2.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.chinese_peking_duck,
                title = "Peking Duck",
                price = "450",
                restaurantName = "Peking Palace",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "3.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹120",
                address = "Nehru Place, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.chinese_momos,
                title = "Chicken Momos",
                price = "120",
                restaurantName = "Momos Point",
                rating = "4.3",
                deliveryTime = "10-15 mins",
                distance = "0.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹25",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.chinese_hot_soup,
                title = "Hot & Sour Soup",
                price = "110",
                restaurantName = "Soup Nation",
                rating = "4.6",
                deliveryTime = "12-16 mins",
                distance = "1.3 km",
                discount = "20% OFF",
                discountAmount = "up to ₹30",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.chinese_kung_pao,
                title = "Kung Pao Chicken",
                price = "320",
                restaurantName = "Kung Pao Express",
                rating = "4.7",
                deliveryTime = "18-23 mins",
                distance = "2.0 km",
                discount = "25% OFF",
                discountAmount = "up to ₹85",
                address = "Chanakyapuri, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.chinese_manchow_soup,
                title = "Manchow Soup",
                price = "100",
                restaurantName = "Soup Delight",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.7 km",
                discount = "30% OFF",
                discountAmount = "up to ₹40",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.chinese_ginger_chicken,
                title = "Ginger Chicken",
                price = "270",
                restaurantName = "Ginger House",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "2.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹60",
                address = "Lodhi Road, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.chinese_paneer_manchurian,
                title = "Paneer Manchurian",
                price = "240",
                restaurantName = "Paneer Wok",
                rating = "4.5",
                deliveryTime = "16-21 mins",
                distance = "1.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹40",
                address = "Malviya Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.chinese_fried_rice,
                title = "Egg Fried Rice",
                price = "170",
                restaurantName = "Rice Bowl",
                rating = "4.3",
                deliveryTime = "14-19 mins",
                distance = "1.4 km",
                discount = "25% OFF",
                discountAmount = "up to ₹50",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.chinese_american_chopsuey,
                title = "American Chopsuey",
                price = "290",
                restaurantName = "Chopsuey Corner",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.9 km",
                discount = "30% OFF",
                discountAmount = "up to ₹95",
                address = "South Extension, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.chinese_chicken_lollipop,
                title = "Chicken Lollipop",
                price = "330",
                restaurantName = "Lollipop Express",
                rating = "4.6",
                deliveryTime = "18-22 mins",
                distance = "2.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹70",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.chinese_triple_rice,
                title = "Triple Schezwan Rice",
                price = "260",
                restaurantName = "Triple Dragon",
                rating = "4.5",
                deliveryTime = "22-27 mins",
                distance = "2.6 km",
                discount = "15% OFF",
                discountAmount = "up to ₹45",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.chinese_cantonese,
                title = "Cantonese Chicken",
                price = "310",
                restaurantName = "Canton Kitchen",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "3.1 km",
                discount = "25% OFF",
                discountAmount = "up to ₹80",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.chinese_veg_noodles,
                title = "Veg Hakka Noodles",
                price = "150",
                restaurantName = "Veggie Wok",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.6 km",
                discount = "30% OFF",
                discountAmount = "up to ₹55",
                address = "Kailash Colony, Delhi"
            )
        )
        Column {
            sampleChineseItems .forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun IceCreamCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val iceCreamFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "chocolate",
                    text = "Chocolate",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chocolate_ice_cream
                ),
                FilterChip(
                    id = "vanilla",
                    text = "Vanilla",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vanilla_ice_cream
                ),
                FilterChip(
                    id = "strawberry",
                    text = "Strawberry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_strawberry_ice_cream
                ),
                FilterChip(
                    id = "butterscotch",
                    text = "Butterscotch",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mint_choco",
                    text = "Mint Choco",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookie_cream",
                    text = "Cookie Cream",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fruit",
                    text = "Fruit",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruit_ice_cream
                ),
                FilterChip(
                    id = "nutty",
                    text = "Nutty",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_nutty_ice_cream
                ),
                FilterChip(
                    id = "cone",
                    text = "Cone",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cup",
                    text = "Cup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_pack",
                    text = "Family Pack",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free_ice_cream
                ),
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_ice_cream
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quick_delivery",
                    text = "Quick Delivery",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_quick_ice_cream
                ),
                FilterChip(
                    id = "schedule",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = iceCreamFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeIceCreamItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.chocolate_fudge_icecream,
                title = "Chocolate Fudge Sundae",
                price = "180",
                restaurantName = "Cream & Cone",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.8 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.vanilla_bean_icecream,
                title = "Vanilla Bean Delight",
                price = "150",
                restaurantName = "Ice Cream Parlor",
                rating = "4.6",
                deliveryTime = "12-18 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹30",
                address = "Hauz Khas, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.strawberry_blast_icecream,
                title = "Strawberry Blast",
                price = "170",
                restaurantName = "Berry Creamery",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "25%",
                discountAmount = "up to ₹45",
                address = "Greater Kailash, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.butterscotch_crunch_icecream,
                title = "Butterscotch Crunch",
                price = "190",
                restaurantName = "Sweet Treats",
                rating = "4.5",
                deliveryTime = "8-12 mins",
                distance = "0.5 km",
                discount = "30%",
                discountAmount = "up to ₹60",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.mint_choco_chip_icecream,
                title = "Mint Chocolate Chip",
                price = "160",
                restaurantName = "Chill Factory",
                rating = "4.4",
                deliveryTime = "18-22 mins",
                distance = "2.0 km",
                discount = "20%",
                discountAmount = "up to ₹35",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.cookie_cream_icecream,
                title = "Cookies & Cream Dream",
                price = "200",
                restaurantName = "Dream Cream",
                rating = "4.9",
                deliveryTime = "14-19 mins",
                distance = "1.8 km",
                discount = "15%",
                discountAmount = "up to ₹35",
                address = "Vasant Vihar, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeIceCreamItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleIceCreamItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.icecream_chocolate,
                title = "Chocolate Fudge",
                price = "180",
                restaurantName = "Cream & Cone",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.icecream_vanilla,
                title = "Classic Vanilla",
                price = "150",
                restaurantName = "Ice Cream Parlor",
                rating = "4.5",
                deliveryTime = "10-15 mins",
                distance = "0.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Hauz Khas, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.icecream_strawberry,
                title = "Fresh Strawberry",
                price = "170",
                restaurantName = "Berry Delight",
                rating = "4.6",
                deliveryTime = "12-17 mins",
                distance = "1.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹45",
                address = "Vasant Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.icecream_mint_choco,
                title = "Mint Chocolate Chip",
                price = "190",
                restaurantName = "Cool Cones",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "2.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Green Park, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.icecream_cookies_cream,
                title = "Cookies & Cream",
                price = "200",
                restaurantName = "Cookie Monster",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "30% OFF",
                discountAmount = "up to ₹65",
                address = "Defence Colony, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.icecream_butterscotch,
                title = "Butterscotch Crunch",
                price = "175",
                restaurantName = "Sweet Scoops",
                rating = "4.4",
                deliveryTime = "14-19 mins",
                distance = "1.7 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.icecream_pistachio,
                title = "Pistachio Almond",
                price = "220",
                restaurantName = "Nutty Cones",
                rating = "4.6",
                deliveryTime = "16-21 mins",
                distance = "1.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹60",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.icecream_mango,
                title = "Mango Tango",
                price = "160",
                restaurantName = "Tropical Treats",
                rating = "4.9",
                deliveryTime = "13-18 mins",
                distance = "1.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Nehru Place, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.icecream_rocky_road,
                title = "Rocky Road",
                price = "210",
                restaurantName = "Rocky's Ice Cream",
                rating = "4.5",
                deliveryTime = "22-27 mins",
                distance = "2.8 km",
                discount = "35% OFF",
                discountAmount = "up to ₹80",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.icecream_coffee,
                title = "Coffee Caramel",
                price = "185",
                restaurantName = "Caffeine Cream",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.6 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.icecream_cotton_candy,
                title = "Cotton Candy",
                price = "165",
                restaurantName = "Candy Land",
                rating = "4.3",
                deliveryTime = "11-16 mins",
                distance = "1.1 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Chanakyapuri, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.icecream_salted_caramel,
                title = "Salted Caramel",
                price = "195",
                restaurantName = "Caramel Corner",
                rating = "4.8",
                deliveryTime = "19-24 mins",
                distance = "2.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹55",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.icecream_bubblegum,
                title = "Bubblegum Blast",
                price = "155",
                restaurantName = "Fun Flavors",
                rating = "4.2",
                deliveryTime = "10-15 mins",
                distance = "0.9 km",
                discount = "30% OFF",
                discountAmount = "up to ₹50",
                address = "Lodhi Road, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.icecream_rainbow_sherbet,
                title = "Rainbow Sherbet",
                price = "140",
                restaurantName = "Colorful Cones",
                rating = "4.4",
                deliveryTime = "12-17 mins",
                distance = "1.3 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Malviya Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.icecream_chocolate_chip,
                title = "Chocolate Chip Cookie Dough",
                price = "205",
                restaurantName = "Dough & Cream",
                rating = "4.7",
                deliveryTime = "21-26 mins",
                distance = "2.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹55",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.icecream_neapolitan,
                title = "Neapolitan Trio",
                price = "230",
                restaurantName = "Triple Scoop",
                rating = "4.6",
                deliveryTime = "18-23 mins",
                distance = "2.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "South Extension, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.icecream_tiramisu,
                title = "Tiramisu Gelato",
                price = "240",
                restaurantName = "Italian Delights",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "3.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹75",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.icecream_blackberry,
                title = "Blackberry Swirl",
                price = "175",
                restaurantName = "Berry Blast",
                rating = "4.5",
                deliveryTime = "14-19 mins",
                distance = "1.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.icecream_coconut,
                title = "Coconut Dream",
                price = "160",
                restaurantName = "Tropical Paradise",
                rating = "4.6",
                deliveryTime = "16-21 mins",
                distance = "2.0 km",
                discount = "25% OFF",
                discountAmount = "up to ₹45",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.icecream_rum_raisin,
                title = "Rum & Raisin",
                price = "190",
                restaurantName = "Adult Scoops",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "2.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Kailash Colony, Delhi"
            )
        )
        Column {
            sampleIceCreamItems .forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun AppamCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val appamFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "egg_appam",
                    text = "Egg Appam",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_egg_appam
                ),
                FilterChip(
                    id = "plain_appam",
                    text = "Plain Appam",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_plain_appam
                ),
                FilterChip(
                    id = "kerala_style",
                    text = "Kerala Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tamil_style",
                    text = "Tamil Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_stew",
                    text = "With Stew",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_stew_appam
                ),
                FilterChip(
                    id = "with_chicken_curry",
                    text = "Chicken Curry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_appam
                ),
                FilterChip(
                    id = "vegetarian",
                    text = "Vegetarian",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_appam
                ),
                FilterChip(
                    id = "coconut_milk",
                    text = "Coconut Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rice_appam",
                    text = "Rice Appam",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "instant_mix",
                    text = "Instant Mix",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "traditional",
                    text = "Traditional",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_ishtu",
                    text = "With Ishtu",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_ishtu_appam
                ),
                FilterChip(
                    id = "egg_roast",
                    text = "Egg Roast",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sweet_appam",
                    text = "Sweet Appam",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sweet_appam
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hot_fresh",
                    text = "Hot & Fresh",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_hot_appam
                ),
                FilterChip(
                    id = "schedule",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = appamFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeAppamItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.egg_appam,
                title = "Egg Appam with Stew",
                price = "120",
                restaurantName = "Kerala Kitchen",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "20%",
                discountAmount = "up to ₹25",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.plain_appam,
                title = "Plain Appam with Veg Ishtu",
                price = "100",
                restaurantName = "South Spice",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "15%",
                discountAmount = "up to ₹20",
                address = "Karol Bagh, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.sweet_appam,
                title = "Sweet Coconut Appam",
                price = "90",
                restaurantName = "Traditional Tastes",
                rating = "4.6",
                deliveryTime = "18-22 mins",
                distance = "1.5 km",
                discount = "25%",
                discountAmount = "up to ₹25",
                address = "Malviya Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.appam_chicken_curry,
                title = "Appam with Chicken Curry",
                price = "150",
                restaurantName = "Coastal Delights",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.1 km",
                discount = "30%",
                discountAmount = "up to ₹50",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.appam_kadala,
                title = "Appam with Kadala Curry",
                price = "110",
                restaurantName = "Veggie Haven",
                rating = "4.4",
                deliveryTime = "12-17 mins",
                distance = "0.8 km",
                discount = "20%",
                discountAmount = "up to ₹25",
                address = "Green Park, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.instant_mix_appam,
                title = "Instant Appam Mix",
                price = "180",
                restaurantName = "Ready to Eat",
                rating = "4.3",
                deliveryTime = "30-40 mins",
                distance = "3.0 km",
                discount = "15%",
                discountAmount = "up to ₹30",
                address = "Dwarka, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeAppamItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleAppamItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.appam_egg_stew,
                title = "Egg Appam with Vegetable Stew",
                price = "140",
                restaurantName = "Kerala Kitchen",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹30",
                address = "Lajpat Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.appam_plain_ishtu,
                title = "Plain Appam with Veg Ishtu",
                price = "120",
                restaurantName = "South Spice",
                rating = "4.6",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹25",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.appam_sweet_coconut,
                title = "Sweet Coconut Appam",
                price = "110",
                restaurantName = "Traditional Tastes",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹30",
                address = "Malviya Nagar, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.appam_with_chicken_curry,
                title = "Appam with Chicken Curry",
                price = "160",
                restaurantName = "Coastal Delights",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "2.1 km",
                discount = "30% OFF",
                discountAmount = "up to ₹50",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.appam__with_kadala,
                title = "Appam with Kadala Curry",
                price = "130",
                restaurantName = "Veggie Haven",
                rating = "4.5",
                deliveryTime = "12-17 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹30",
                address = "Green Park, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.appam_fish_molly,
                title = "Appam with Fish Molee",
                price = "180",
                restaurantName = "Seafood Special",
                rating = "4.7",
                deliveryTime = "28-33 mins",
                distance = "2.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹45",
                address = "Defence Colony, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.appam_mutton_stew,
                title = "Appam with Mutton Stew",
                price = "200",
                restaurantName = "Meat Masters",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "2.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹45",
                address = "Greater Kailash, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.appam_prawn_curry,
                title = "Appam with Prawn Curry",
                price = "220",
                restaurantName = "Coastal Flavors",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.3 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Vasant Vihar, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.appam_egg_roast,
                title = "Appam with Egg Roast",
                price = "150",
                restaurantName = "Egg Station",
                rating = "4.4",
                deliveryTime = "18-23 mins",
                distance = "1.7 km",
                discount = "30% OFF",
                discountAmount = "up to ₹50",
                address = "Rajouri Garden, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.appam_veg_kurma,
                title = "Appam with Vegetable Kurma",
                price = "125",
                restaurantName = "Kurma Corner",
                rating = "4.5",
                deliveryTime = "16-21 mins",
                distance = "1.4 km",
                discount = "20% OFF",
                discountAmount = "up to ₹30",
                address = "Nehru Place, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.appam_mushroom_stew,
                title = "Appam with Mushroom Stew",
                price = "145",
                restaurantName = "Mushroom Magic",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.9 km",
                discount = "25% OFF",
                discountAmount = "up to ₹40",
                address = "Hauz Khas, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.appam_paneer_butter,
                title = "Appam with Paneer Butter Masala",
                price = "155",
                restaurantName = "Paneer Palace",
                rating = "4.6",
                deliveryTime = "22-27 mins",
                distance = "2.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.appam_banana_jaggery,
                title = "Banana Appam with Jaggery",
                price = "95",
                restaurantName = "Healthy Bites",
                rating = "4.3",
                deliveryTime = "14-19 mins",
                distance = "1.1 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Chanakyapuri, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.appam_instant_mix,
                title = "Instant Appam Mix (500g)",
                price = "180",
                restaurantName = "Ready to Eat",
                rating = "4.4",
                deliveryTime = "30-40 mins",
                distance = "3.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Dwarka, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.appam_jackfruit,
                title = "Appam with Jackfruit Curry",
                price = "135",
                restaurantName = "Jackfruit Junction",
                rating = "4.7",
                deliveryTime = "24-29 mins",
                distance = "2.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹35",
                address = "Lodhi Road, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.appam_chettinad_chicken,
                title = "Appam with Chettinad Chicken",
                price = "175",
                restaurantName = "Chettinad Spice",
                rating = "4.8",
                deliveryTime = "26-31 mins",
                distance = "2.4 km",
                discount = "30% OFF",
                discountAmount = "up to ₹55",
                address = "South Extension, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.appam_milk_payasam,
                title = "Appam with Milk Payasam",
                price = "115",
                restaurantName = "Sweet Endings",
                rating = "4.5",
                deliveryTime = "19-24 mins",
                distance = "1.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Pitampura, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.appam_ularthiyathu,
                title = "Appam with Ularthiyathu",
                price = "190",
                restaurantName = "Appam Special",
                rating = "4.9",
                deliveryTime = "35-40 mins",
                distance = "3.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Rohini, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.appam_godambu,
                title = "Godambu Appam (Wheat)",
                price = "105",
                restaurantName = "Health Kitchen",
                rating = "4.6",
                deliveryTime = "17-22 mins",
                distance = "1.6 km",
                discount = "25% OFF",
                discountAmount = "up to ₹30",
                address = "Janakpuri, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.appam_combo_meal,
                title = "Appam Combo Meal (2 pieces with curry)",
                price = "160",
                restaurantName = "Combo Meals",
                rating = "4.7",
                deliveryTime = "22-27 mins",
                distance = "2.1 km",
                discount = "35% OFF",
                discountAmount = "up to ₹60",
                address = "Kailash Colony, Delhi"
            )
        )
        Column {
            sampleAppamItems .forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun BathCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val bathFoodFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                // Filters WITH left icons
                FilterChip(
                    id = "biryani",
                    text = "Biryani",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_biryani_bath
                ),
                FilterChip(
                    id = "fried_rice",
                    text = "Fried Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fried_rice_bath
                ),
                FilterChip(
                    id = "lemon_rice",
                    text = "Lemon Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_lemon_rice_bath
                ),
                FilterChip(
                    id = "tomato_rice",
                    text = "Tomato Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_tomato_rice_bath
                ),
                // Filters WITHOUT left icons (text only)
                FilterChip(
                    id = "veg_bath",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "non_veg",
                    text = "Non-Veg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_bath",
                    text = "Egg Bath",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chicken",
                    text = "Chicken",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mutton",
                    text = "Mutton",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "prawns",
                    text = "Prawns",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hyderabadi",
                    text = "Hyderabadi Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lucknowi",
                    text = "Lucknowi Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mild",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_raita",
                    text = "With Raita",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_salad",
                    text = "With Salad",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_pack",
                    text = "Family Pack",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2
        )
         FilterButtonFood(
            filterConfig = bathFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeBathFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.hyderabadi_biryani,
                title = "Hyderabadi Chicken Dum Biryani",
                price = "280",
                restaurantName = "Hyderabad House",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "2.5 km",
                discount = "25%",
                discountAmount = "up to ₹75",
                address = "Karol Bagh, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.veg_biryani,
                title = "Vegetable Dum Biryani",
                price = "190",
                restaurantName = "Green Garden",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹40",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.mutton_biryani,
                title = "Awadhi Mutton Biryani",
                price = "350",
                restaurantName = "Lucknowi Flavours",
                rating = "4.9",
                deliveryTime = "35-40 mins",
                distance = "3.2 km",
                discount = "30%",
                discountAmount = "up to ₹105",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.egg_biryani,
                title = "Egg Biryani with Raita",
                price = "220",
                restaurantName = "Eggcellent Meals",
                rating = "4.5",
                deliveryTime = "18-22 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹35",
                address = "Malviya Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.prawn_fried_rice,
                title = "Prawn Fried Rice",
                price = "260",
                restaurantName = "Coastal Spice",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "20%",
                discountAmount = "up to ₹55",
                address = "Dwarka, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.lemon_rice,
                title = "Lemon Rice with Papad",
                price = "120",
                restaurantName = "South Indian Delights",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "10%",
                discountAmount = "up to ₹15",
                address = "Green Park, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeBathFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleBathItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.bath_egg_stew,
                title = "Rice Bath with Egg Curry",
                price = "120",
                restaurantName = "Kerala Bhavan",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.bath_chicken,
                title = "Chicken Bath Biryani",
                price = "180",
                restaurantName = "Hyderabadi Spice",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.bath_veg,
                title = "Vegetable Bath Pulao",
                price = "110",
                restaurantName = "Green Leaf",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹20",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.bath_mutton,
                title = "Mutton Bath Biryani",
                price = "220",
                restaurantName = "Hyderabadi Spice",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "2.0 km",
                discount = "10% OFF",
                discountAmount = "up to ₹40",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.bath_fish,
                title = "Fish Curry Bath",
                price = "160",
                restaurantName = "Coastal Flavors",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "2.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹30",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.bath_prawn,
                title = "Prawn Bath Curry",
                price = "200",
                restaurantName = "Coastal Flavors",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.bath_combo,
                title = "Bath Combo (Rice + 2 Curries)",
                price = "150",
                restaurantName = "Kerala Bhavan",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹30",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.bath_paneer,
                title = "Paneer Bath Pulao",
                price = "140",
                restaurantName = "Green Leaf",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "1.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.bath_egg_biryani,
                title = "Egg Bath Biryani",
                price = "130",
                restaurantName = "Hyderabadi Spice",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.bath_mushroom,
                title = "Mushroom Bath Pulao",
                price = "135",
                restaurantName = "Green Leaf",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "1.0 km",
                discount = "25% OFF",
                discountAmount = "up to ₹25",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.bath_sambar,
                title = "Sambar Bath",
                price = "100",
                restaurantName = "Kerala Bhavan",
                rating = "4.2",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "30% OFF",
                discountAmount = "up to ₹20",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.bath_chicken_curry,
                title = "Chicken Curry Bath",
                price = "170",
                restaurantName = "Kerala Bhavan",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "1.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.bath_family_pack,
                title = "Family Bath Pack (Rice + 3 Curries)",
                price = "300",
                restaurantName = "Coastal Flavors",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "2.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹60",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.bath_veg_biryani,
                title = "Veg Bath Biryani",
                price = "125",
                restaurantName = "Hyderabadi Spice",
                rating = "4.3",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "25% OFF",
                discountAmount = "up to ₹25",
                address = "Saket, Delhi"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.bath_fish_fry,
                title = "Fish Fry Bath",
                price = "175",
                restaurantName = "Coastal Flavors",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.5 km",
                discount = "10% OFF",
                discountAmount = "up to ₹35",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.bath_mixed_veg,
                title = "Mixed Vegetable Bath",
                price = "115",
                restaurantName = "Green Leaf",
                rating = "4.2",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹20",
                address = "Connaught Place, Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.bath_chicken_roast,
                title = "Chicken Roast Bath",
                price = "190",
                restaurantName = "Kerala Bhavan",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "1.5 km",
                discount = "10% OFF",
                discountAmount = "up to ₹35",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.bath_prawn_fry,
                title = "Prawn Fry Bath",
                price = "210",
                restaurantName = "Coastal Flavors",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹40",
                address = "Vasant Kunj, Delhi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.bath_veg_curry,
                title = "Vegetable Curry Bath",
                price = "105",
                restaurantName = "Kerala Bhavan",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "25% OFF",
                discountAmount = "up to ₹20",
                address = "Karol Bagh, Delhi"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.bath_combo_special,
                title = "Special Bath Combo (Rice + Curries + Dessert)",
                price = "180",
                restaurantName = "Hyderabadi Spice",
                rating = "4.9",
                deliveryTime = "30-35 mins",
                distance = "2.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Saket, Delhi"
            )
        )
         Column {
            sampleBathItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun BondaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val bondaFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                // Essential Bonda Types WITH left icons
                FilterChip(
                    id = "aloo_bonda",
                    text = "Aloo Bonda",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_aloo_bonda
                ),
                FilterChip(
                    id = "onion_bonda",
                    text = "Onion Bonda",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_onion_bonda
                ),
                FilterChip(
                    id = "mixed_veg_bonda",
                    text = "Mixed Veg",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mixed_veg_bonda
                ),
                // Most popular regional style WITH left icon
                FilterChip(
                    id = "mysore_bonda",
                    text = "Mysore Bonda",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mysore_bonda
                ),
                // Essential Accompaniment WITH left icon
                FilterChip(
                    id = "with_chutney",
                    text = "With Chutney",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chutney_bonda
                ),
                // Essential text-only filters
                FilterChip(
                    id = "veg",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fresh",
                    text = "Fresh",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_30_mins",
                    text = "Under 30 Mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2  // Single row for minimal look
        )
        FilterButtonFood(
            filterConfig = bondaFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeBondaItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.aloo_bonda,
                title = "Aloo Bonda with Coconut Chutney",
                price = "80",
                restaurantName = "South Indian Delights",
                rating = "4.6",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "20%",
                discountAmount = "up to ₹20",
                address = "Green Park, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.mysore_bonda,
                title = "Mysore Bonda with Sambar",
                price = "95",
                restaurantName = "Karnataka Kitchen",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "15%",
                discountAmount = "up to ₹15",
                address = "Karol Bagh, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.onion_bonda,
                title = "Crispy Onion Bonda with Chutney",
                price = "85",
                restaurantName = "Udupi Sagar",
                rating = "4.5",
                deliveryTime = "18-22 mins",
                distance = "1.5 km",
                discount = "25%",
                discountAmount = "up to ₹25",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.mixed_veg_bonda,
                title = "Mixed Vegetable Bonda Platter",
                price = "110",
                restaurantName = "Vegetarian Hub",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "10%",
                discountAmount = "up to ₹12",
                address = "Malviya Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.paneer_bonda,
                title = "Paneer Bonda with Tomato Chutney",
                price = "120",
                restaurantName = "Chennai Express",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "30%",
                discountAmount = "up to ₹40",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.cheese_bonda,
                title = "Cheese Stuffed Bonda Combo",
                price = "130",
                restaurantName = "Fusion Foods",
                rating = "4.3",
                deliveryTime = "22-28 mins",
                distance = "1.9 km",
                discount = "20%",
                discountAmount = "up to ₹30",
                address = "Dwarka, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeBondaItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleBondaItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.bonda_masala,
                title = "Masala Bonda",
                price = "60",
                restaurantName = "Udupi Cafe",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "10% OFF",
                discountAmount = "up to ₹15",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.bonda_onion,
                title = "Onion Bonda",
                price = "65",
                restaurantName = "Chennai Spices",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.bonda_alu,
                title = "Alu Bonda",
                price = "55",
                restaurantName = "Tamil Nadu Mess",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹15",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.bonda_egg,
                title = "Egg Bonda",
                price = "75",
                restaurantName = "Kerala Bhavan",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "10% OFF",
                discountAmount = "up to ₹20",
                address = "Kochi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.bonda_vegetable,
                title = "Mixed Vegetable Bonda",
                price = "70",
                restaurantName = "Udupi Cafe",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹25",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.bonda_chicken,
                title = "Chicken Bonda",
                price = "85",
                restaurantName = "Andhra Spice",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹25",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.bonda_paneer,
                title = "Paneer Bonda",
                price = "80",
                restaurantName = "Udupi Cafe",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.bonda_mushroom,
                title = "Mushroom Bonda",
                price = "75",
                restaurantName = "Chennai Spices",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.bonda_spicy,
                title = "Spicy Mirchi Bonda",
                price = "50",
                restaurantName = "Andhra Spice",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "10% OFF",
                discountAmount = "up to ₹10",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.bonda_combo,
                title = "Bonda Combo (4 pieces + chutney)",
                price = "100",
                restaurantName = "Tamil Nadu Mess",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹40",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.bonda_sweet,
                title = "Sweet Bonda (Mysore Bonda)",
                price = "55",
                restaurantName = "Udupi Cafe",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹20",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.bonda_mutton,
                title = "Mutton Bonda",
                price = "95",
                restaurantName = "Andhra Spice",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.bonda_cheese,
                title = "Cheese Bonda",
                price = "85",
                restaurantName = "Chennai Spices",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹30",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.bonda_family_pack,
                title = "Family Bonda Pack (12 pieces)",
                price = "200",
                restaurantName = "Tamil Nadu Mess",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "1.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.bonda_fish,
                title = "Fish Bonda",
                price = "90",
                restaurantName = "Kerala Bhavan",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "10% OFF",
                discountAmount = "up to ₹25",
                address = "Kochi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.bonda_corn,
                title = "Corn Bonda",
                price = "65",
                restaurantName = "Udupi Cafe",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹15",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.bonda_schezwan,
                title = "Schezwan Bonda",
                price = "70",
                restaurantName = "Chennai Spices",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.bonda_prawn,
                title = "Prawn Bonda",
                price = "100",
                restaurantName = "Kerala Bhavan",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "1.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Kochi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.bonda_medu,
                title = "Medu Bonda",
                price = "60",
                restaurantName = "Tamil Nadu Mess",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "25% OFF",
                discountAmount = "up to ₹25",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.bonda_combo_special,
                title = "Special Bonda Platter (Mixed varieties)",
                price = "150",
                restaurantName = "Andhra Spice",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Hyderabad"
            )
        )
        Column {
            sampleBondaItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun CutletCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val cutletFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                // Popular cutlet types WITH left icons
                FilterChip(
                    id = "vegetable_cutlet",
                    text = "Vegetable Cutlet",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegetable_cutlet
                ),
                FilterChip(
                    id = "chicken_cutlet",
                    text = "Chicken Cutlet",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_cutlet
                ),
                FilterChip(
                    id = "fish_cutlet",
                    text = "Fish Cutlet",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fish_cutlet
                ),
                FilterChip(
                    id = "potato_cutlet",
                    text = "Potato Cutlet",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_potato_cutlet
                ),
                // Cooking style WITH left icon
                FilterChip(
                    id = "deep_fried",
                    text = "Deep Fried",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_deep_fried
                ),
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_air_fried
                ),
                FilterChip(
                    id = "pan_fried",
                    text = "Pan Fried",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pan_fried
                ),
                // Text-only filters (no left icons)
                FilterChip(
                    id = "crispy",
                    text = "Crispy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_sauce",
                    text = "With Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "served_hot",
                    text = "Served Hot",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ready_in_20",
                    text = "Ready in 20 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2  // Single row for minimal look
        )
         FilterButtonFood(
            filterConfig = cutletFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeCutletItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.vegetable_cutlet,
                title = "Crispy Vegetable Cutlet with Sauce",
                price = "80",
                restaurantName = "Classic Bites",
                rating = "4.6",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "20%",
                discountAmount = "up to ₹20",
                address = "Green Park, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.chicken_cutlet,
                title = "Spicy Chicken Cutlet Platter",
                price = "150",
                restaurantName = "Chicken Specials",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "15%",
                discountAmount = "up to ₹25",
                address = "Karol Bagh, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.fish_cutlet,
                title = "Fish Cutlet with Lemon Mayo",
                price = "160",
                restaurantName = "Sea Delights",
                rating = "4.5",
                deliveryTime = "18-22 mins",
                distance = "1.5 km",
                discount = "25%",
                discountAmount = "up to ₹40",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.potato_cutlet,
                title = "Potato Cutlet with Chutney",
                price = "70",
                restaurantName = "Simple Eats",
                rating = "4.4",
                deliveryTime = "12-15 mins",
                distance = "0.8 km",
                discount = "10%",
                discountAmount = "up to ₹10",
                address = "Malviya Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.gravy_cutlet,
                title = "Cutlet with Gravy",
                price = "180",
                restaurantName = "Premium Grill",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "30%",
                discountAmount = "up to ₹55",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.cheese_cutlet,
                title = "Cheese Stuffed Cutlet Combo",
                price = "130",
                restaurantName = "Fusion Kitchen",
                rating = "4.3",
                deliveryTime = "22-28 mins",
                distance = "1.9 km",
                discount = "20%",
                discountAmount = "up to ₹30",
                address = "Dwarka, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeCutletItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleCutletItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.cutlet_vegetable,
                title = "Vegetable Cutlet",
                price = "60",
                restaurantName = "Classic Bites",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "10% OFF",
                discountAmount = "up to ₹15",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.cutlet_chicken,
                title = "Chicken Cutlet",
                price = "85",
                restaurantName = "Chicken Specials",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.cutlet_fish,
                title = "Fish Cutlet",
                price = "95",
                restaurantName = "Sea Delights",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.cutlet_potato,
                title = "Potato Cutlet",
                price = "55",
                restaurantName = "Simple Eats",
                rating = "4.6",
                deliveryTime = "12-18 mins",
                distance = "0.7 km",
                discount = "10% OFF",
                discountAmount = "up to ₹10",
                address = "Kochi"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.cutlet_corn,
                title = "Corn Cutlet",
                price = "70",
                restaurantName = "Corn Specials",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹25",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.cutlet_gravy,
                title = "Gravy Cutlet",
                price = "110",
                restaurantName = "Premium Grill",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.cutlet_paneer,
                title = "Paneer Cutlet",
                price = "80",
                restaurantName = "Paneer House",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.cutlet_mushroom,
                title = "Mushroom Cutlet",
                price = "75",
                restaurantName = "Mushroom Magic",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.cutlet_spicy,
                title = "Spicy Cutlet",
                price = "65",
                restaurantName = "Spice Hub",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "10% OFF",
                discountAmount = "up to ₹12",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.cutlet_combo,
                title = "Cutlet Combo (4 pieces + sauce)",
                price = "120",
                restaurantName = "Combo Meals",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.0 km",
                discount = "30% OFF",
                discountAmount = "up to ₹50",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.cutlet_sweet,
                title = "Sweet Potato Cutlet",
                price = "65",
                restaurantName = "Healthy Bites",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹20",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.cutlet_mutton,
                title = "Mutton Cutlet",
                price = "120",
                restaurantName = "Mutton Specials",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹35",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.cutlet_cheese,
                title = "Cheese Stuffed Cutlet",
                price = "85",
                restaurantName = "Cheese Lovers",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "25% OFF",
                discountAmount = "up to ₹30",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.cutlet_family_pack,
                title = "Family Cutlet Pack (12 pieces)",
                price = "250",
                restaurantName = "Family Meals",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "1.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹60",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.cutlet_prawn,
                title = "Prawn Cutlet",
                price = "105",
                restaurantName = "Sea Food Hub",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "10% OFF",
                discountAmount = "up to ₹25",
                address = "Kochi"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.cutlet_breaded,
                title = "Breaded Cutlet",
                price = "75",
                restaurantName = "Crispy Bites",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.cutlet_schezwan,
                title = "Schezwan Cutlet",
                price = "80",
                restaurantName = "Chinese Fusion",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "20% OFF",
                discountAmount = "up to ₹25",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.cutlet_egg,
                title = "Egg Cutlet",
                price = "70",
                restaurantName = "Egg Specials",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Kochi"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.cutlet_air_fried,
                title = "Air Fried Cutlet",
                price = "85",
                restaurantName = "Healthy Kitchen",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "1.0 km",
                discount = "25% OFF",
                discountAmount = "up to ₹30",
                address = "Coimbatore"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.cutlet_combo_special,
                title = "Special Cutlet Platter (Mixed varieties)",
                price = "180",
                restaurantName = "Fusion Restaurant",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "2.0 km",
                discount = "20% OFF",
                discountAmount = "up to ₹50",
                address = "Hyderabad"
            )
        )
        Column {
            sampleCutletItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun DessertCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val dessertFilters = FilterConfig(
            filters = listOf(
                // Main filter dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // Dessert types WITH left icons (visual categories)
                FilterChip(
                    id = "cake",
                    text = "Cake",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cake_dessert
                ),
                FilterChip(
                    id = "ice_cream",
                    text = "Ice Cream",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_ice_cream_dessert
                ),
                FilterChip(
                    id = "pastry",
                    text = "Pastry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pastry_dessert
                ),
                FilterChip(
                    id = "chocolate",
                    text = "Chocolate",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chocolate_dessert
                ),
                FilterChip(
                    id = "pudding",
                    text = "Pudding",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pudding_dessert
                ),

                // Dessert styles WITH left icons
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_dessert
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_gluten_free_dessert
                ),

                // Text-only filters (attributes/characteristics)
                FilterChip(
                    id = "cold",
                    text = "Cold",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "warm",
                    text = "Warm",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chilled",
                    text = "Chilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "nut_free",
                    text = "Nut Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),

                // Text-only filters (serving/price)
                FilterChip(
                    id = "serves_2",
                    text = "Serves 2",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ready_in_15",
                    text = "Ready in 15 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "freshly_made",
                    text = "Freshly Made",
                    type = FilterType.TEXT_ONLY
                ),

                // Sort dropdown
                FilterChip(
                    id = "sort",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2  // Maintains minimal look
        )
        FilterButtonFood(
            filterConfig = dessertFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeDessertItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.dessert_chocolate_cake,
                title = "Chocolate Lava Cake with Ice Cream",
                price = "250",
                restaurantName = "Sweet Heaven",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹40",
                address = "Green Park, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.dessert_ice_cream,
                title = "Vegan Coconut Ice Cream Sundae",
                price = "180",
                restaurantName = "Nature's Treat",
                rating = "4.6",
                deliveryTime = "8-12 mins",
                distance = "1.1 km",
                discount = "20%",
                discountAmount = "up to ₹35",
                address = "Malviya Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.dessert_pastry,
                title = "Gluten-Free Blueberry Pastry",
                price = "140",
                restaurantName = "Healthy Bakes",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "1.8 km",
                discount = "10%",
                discountAmount = "up to ₹15",
                address = "Dwarka, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.dessert_chocolate,
                title = "Belgian Chocolate Fondue Platter",
                price = "320",
                restaurantName = "Chocolate Haven",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "2.2 km",
                discount = "25%",
                discountAmount = "up to ₹80",
                address = "Saket, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.dessert_pudding,
                title = "Warm Bread Pudding with Custard",
                price = "160",
                restaurantName = "Comfort Desserts",
                rating = "4.4",
                deliveryTime = "12-18 mins",
                distance = "1.5 km",
                discount = "15%",
                discountAmount = "up to ₹25",
                address = "Lajpat Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.dessert_cheesecake,
                title = "Eggless New York Cheesecake",
                price = "280",
                restaurantName = "Premium Patisserie",
                rating = "4.7",
                deliveryTime = "18-22 mins",
                distance = "2.5 km",
                discount = "30%",
                discountAmount = "up to ₹85",
                address = "Karol Bagh, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeDessertItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Restaurants delivering to you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Featured restaurants",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Sample data based on the provided images
        val sampleDessertItems = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.dessert_chocolate_lava_cake,
                title = "Chocolate Lava Cake",
                price = "180",
                restaurantName = "Sweet Heaven",
                rating = "4.6",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹30",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.dessert_new_york_cheesecake,
                title = "New York Cheesecake",
                price = "220",
                restaurantName = "Cheese Delights",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "10% OFF",
                discountAmount = "up to ₹25",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.dessert_gulab_jamun,
                title = "Gulab Jamun (2 pieces)",
                price = "90",
                restaurantName = "Indian Mithai House",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "20% OFF",
                discountAmount = "up to ₹20",
                address = "Delhi"
            ),
            RestaurantItemFull(
                id = 4,
                imageRes = R.drawable.dessert_tiramisu,
                title = "Classic Tiramisu",
                price = "250",
                restaurantName = "Italian Bistro",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "1.5 km",
                discount = "15% OFF",
                discountAmount = "up to ₹40",
                address = "Mumbai"
            ),
            RestaurantItemFull(
                id = 5,
                imageRes = R.drawable.dessert_ice_cream_sundae,
                title = "Chocolate Sundae",
                price = "150",
                restaurantName = "Ice Cream World",
                rating = "4.4",
                deliveryTime = "12-18 mins",
                distance = "0.7 km",
                discount = "25% OFF",
                discountAmount = "up to ₹40",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 6,
                imageRes = R.drawable.dessert_rasmalai,
                title = "Fresh Rasmalai",
                price = "120",
                restaurantName = "Royal Sweets",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.1 km",
                discount = "10% OFF",
                discountAmount = "up to ₹15",
                address = "Kolkata"
            ),
            RestaurantItemFull(
                id = 7,
                imageRes = R.drawable.dessert_brownie,
                title = "Walnut Brownie with Ice Cream",
                price = "190",
                restaurantName = "Brownie Factory",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹40",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 8,
                imageRes = R.drawable.dessert_creme_brulee,
                title = "Vanilla Crème Brûlée",
                price = "280",
                restaurantName = "French Patisserie",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "1.8 km",
                discount = "15% OFF",
                discountAmount = "up to ₹45",
                address = "Pune"
            ),
            RestaurantItemFull(
                id = 9,
                imageRes = R.drawable.dessert_jalebi,
                title = "Hot Jalebi with Rabri",
                price = "110",
                restaurantName = "Street Food Hub",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "10% OFF",
                discountAmount = "up to ₹12",
                address = "Jaipur"
            ),
            RestaurantItemFull(
                id = 10,
                imageRes = R.drawable.dessert_mousse,
                title = "Dark Chocolate Mousse",
                price = "200",
                restaurantName = "Chocolate Studio",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "30% OFF",
                discountAmount = "up to ₹65",
                address = "Hyderabad"
            ),
            RestaurantItemFull(
                id = 11,
                imageRes = R.drawable.dessert_fruit_tart,
                title = "Mixed Fruit Tart",
                price = "170",
                restaurantName = "Fresh Bakes",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "20% OFF",
                discountAmount = "up to ₹35",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 12,
                imageRes = R.drawable.dessert_kulfi,
                title = "Malai Kulfi (2 pieces)",
                price = "95",
                restaurantName = "Traditional Kulfi",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹15",
                address = "Lucknow"
            ),
            RestaurantItemFull(
                id = 13,
                imageRes = R.drawable.dessert_pancakes,
                title = "Berry Pancakes with Maple Syrup",
                price = "210",
                restaurantName = "Breakfast Cafe",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.3 km",
                discount = "25% OFF",
                discountAmount = "up to ₹55",
                address = "Chennai"
            ),
            RestaurantItemFull(
                id = 14,
                imageRes = R.drawable.dessert_rasgulla,
                title = "Bengali Rasgulla (4 pieces)",
                price = "130",
                restaurantName = "Sweet Bengal",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.1 km",
                discount = "20% OFF",
                discountAmount = "up to ₹30",
                address = "Kolkata"
            ),
            RestaurantItemFull(
                id = 15,
                imageRes = R.drawable.dessert_profiteroles,
                title = "Chocolate Profiteroles",
                price = "240",
                restaurantName = "French Bakery",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "1.6 km",
                discount = "10% OFF",
                discountAmount = "up to ₹25",
                address = "Mumbai"
            ),
            RestaurantItemFull(
                id = 16,
                imageRes = R.drawable.dessert_halwa,
                title = "Gajar Ka Halwa",
                price = "140",
                restaurantName = "North Indian Kitchen",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "15% OFF",
                discountAmount = "up to ₹22",
                address = "Delhi"
            ),
            RestaurantItemFull(
                id = 17,
                imageRes = R.drawable.dessert_macarons,
                title = "Assorted Macarons (6 pieces)",
                price = "320",
                restaurantName = "Parisian Cafe",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "1.7 km",
                discount = "20% OFF",
                discountAmount = "up to ₹65",
                address = "Goa"
            ),
            RestaurantItemFull(
                id = 18,
                imageRes = R.drawable.dessert_shrikhand,
                title = "Kesar Shrikhand",
                price = "125",
                restaurantName = "Gujarati Thali",
                rating = "4.5",
                deliveryTime = "18-22 mins",
                distance = "1.0 km",
                discount = "15% OFF",
                discountAmount = "up to ₹20",
                address = "Ahmedabad"
            ),
            RestaurantItemFull(
                id = 19,
                imageRes = R.drawable.dessert_waffles,
                title = "Belgian Waffles with Fruits",
                price = "190",
                restaurantName = "Waffle House",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "25% OFF",
                discountAmount = "up to ₹50",
                address = "Bengaluru"
            ),
            RestaurantItemFull(
                id = 20,
                imageRes = R.drawable.dessert_combo,
                title = "Dessert Platter (Assorted)",
                price = "350",
                restaurantName = "Grand Sweets",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "1.5 km",
                discount = "20% OFF",
                discountAmount = "up to ₹75",
                address = "Mumbai"
            )
        )
        Column {
            sampleDessertItems.forEach { restaurantItem ->
                RestaurantItemListFull(
                    restaurantItem = restaurantItem,
                    onWishlistClick = { },
                    onThreeDotClick = { },
                    onItemClick = { }
                )
            }
        }
    }
}

@Composable
fun DhoklaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        // Filter Button
        val dhoklaFilters = FilterConfig(
            filters = listOf(
                // Main filter dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // Dhokla types WITH left icons (visual categories)
                FilterChip(
                    id = "khaman",
                    text = "Khaman Dhokla",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_khaman_dhokla
                ),
                FilterChip(
                    id = "khatta",
                    text = "Khatta Dhokla",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_khatta_dhokla
                ),
                FilterChip(
                    id = "rasawala",
                    text = "Rasawala Dhokla",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_rasawala_dhokla
                ),
                FilterChip(
                    id = "sandwich",
                    text = "Sandwich Dhokla",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sandwich_dhokla
                ),

                // Cooking style WITH left icon
                FilterChip(
                    id = "steamed",
                    text = "Steamed",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_steamed_dhokla
                ),

                // Dietary preferences WITH left icons
                FilterChip(
                    id = "veg",
                    text = "Vegetarian",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegetarian_dhokla
                ),
                FilterChip(
                    id = "jain",
                    text = "Jain",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_jain_food_dhokla
                ),

                // Text-only filters (preparation/serving attributes)
                FilterChip(
                    id = "instant",
                    text = "Instant",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fermented",
                    text = "Fermented",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sweet_sour",
                    text = "Sweet-Sour",
                    type = FilterType.TEXT_ONLY
                ),

                // Accompaniments as text-only
                FilterChip(
                    id = "with_chutney",
                    text = "With Chutney",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_sev",
                    text = "With Sev",
                    type = FilterType.TEXT_ONLY
                ),

                // Time/price text-only filters
                FilterChip(
                    id = "ready_in_20",
                    text = "Ready in 20 mins",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "serves_4",
                    text = "Serves 4",
                    type = FilterType.TEXT_ONLY
                ),

                // Sort dropdown
                FilterChip(
                    id = "sort",
                    text = "Sort",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
            ),
            rows = 2  // Maintains minimal look
        )
        FilterButtonFood(
            filterConfig = dhoklaFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )

        val completeDhoklaItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.dhokla_khaman,
                title = "Khaman Dhokla with Green Chutney",
                price = "120",
                restaurantName = "Gujarati Rasoi",
                rating = "4.7",
                deliveryTime = "12-15 mins",
                distance = "0.9 km",
                discount = "20%",
                discountAmount = "up to ₹25",
                address = "Rajouri Garden, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.dhokla_khatta,
                title = "Khatta Dhokla with Tamarind Chutney",
                price = "110",
                restaurantName = "Spice Street",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "1.3 km",
                discount = "15%",
                discountAmount = "up to ₹17",
                address = "Janakpuri, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.dhokla_rasawala,
                title = "Rasawala Dhokla in Tangy Curry",
                price = "150",
                restaurantName = "Curry Point",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "25%",
                discountAmount = "up to ₹38",
                address = "Pitampura, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.dhokla_sandwich,
                title = "Sandwich Dhokla with Chutney Layers",
                price = "130",
                restaurantName = "Innovative Bites",
                rating = "4.4",
                deliveryTime = "10-15 mins",
                distance = "1.1 km",
                discount = "10%",
                discountAmount = "up to ₹13",
                address = "Rohini, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.dhokla_steamed,
                title = "Steamed Instant Dhokla with Sev",
                price = "100",
                restaurantName = "Quick Snacks",
                rating = "4.3",
                deliveryTime = "8-12 mins",
                distance = "0.7 km",
                discount = "30%",
                discountAmount = "up to ₹30",
                address = "Vikas Puri, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.dhokla_jain,
                title = "Jain Style Dhokla (No Onion Garlic)",
                price = "140",
                restaurantName = "Pure Veg Corner",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "20%",
                discountAmount = "up to ₹28",
                address = "Shalimar Bagh, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = completeDhoklaItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

//        Spacer(modifier = Modifier.height(15.dp))
//        Text(
//            text = "Restaurants delivering to you",
//            style = MaterialTheme.typography.bodySmall.copy(
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color =  MaterialTheme.customColors.black
//            ),
////            textAlign = TextAlign.Center,
//            maxLines = 1,
//            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        Text(
//            text = "Featured restaurants",
//            style = MaterialTheme.typography.bodySmall.copy(
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.customColors.black
//            ),
////            textAlign = TextAlign.Center,
//            maxLines = 1,
//            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
//        )
//        Spacer(modifier = Modifier.height(5.dp))
//
//        // Sample data based on the provided images
//        val sampleDessertItems = listOf(
//            RestaurantItemFull(
//                id = 1,
//                imageRes = R.drawable.dessert_chocolate_lava_cake,
//                title = "Chocolate Lava Cake",
//                price = "180",
//                restaurantName = "Sweet Heaven",
//                rating = "4.6",
//                deliveryTime = "15-20 mins",
//                distance = "0.8 km",
//                discount = "15% OFF",
//                discountAmount = "up to ₹30",
//                address = "Bengaluru"
//            ),
//            RestaurantItemFull(
//                id = 2,
//                imageRes = R.drawable.dessert_new_york_cheesecake,
//                title = "New York Cheesecake",
//                price = "220",
//                restaurantName = "Cheese Delights",
//                rating = "4.7",
//                deliveryTime = "20-25 mins",
//                distance = "1.2 km",
//                discount = "10% OFF",
//                discountAmount = "up to ₹25",
//                address = "Chennai"
//            ),
//            RestaurantItemFull(
//                id = 3,
//                imageRes = R.drawable.dessert_gulab_jamun,
//                title = "Gulab Jamun (2 pieces)",
//                price = "90",
//                restaurantName = "Indian Mithai House",
//                rating = "4.5",
//                deliveryTime = "15-20 mins",
//                distance = "0.9 km",
//                discount = "20% OFF",
//                discountAmount = "up to ₹20",
//                address = "Delhi"
//            ),
//            RestaurantItemFull(
//                id = 4,
//                imageRes = R.drawable.dessert_tiramisu,
//                title = "Classic Tiramisu",
//                price = "250",
//                restaurantName = "Italian Bistro",
//                rating = "4.8",
//                deliveryTime = "25-30 mins",
//                distance = "1.5 km",
//                discount = "15% OFF",
//                discountAmount = "up to ₹40",
//                address = "Mumbai"
//            ),
//            RestaurantItemFull(
//                id = 5,
//                imageRes = R.drawable.dessert_ice_cream_sundae,
//                title = "Chocolate Sundae",
//                price = "150",
//                restaurantName = "Ice Cream World",
//                rating = "4.4",
//                deliveryTime = "12-18 mins",
//                distance = "0.7 km",
//                discount = "25% OFF",
//                discountAmount = "up to ₹40",
//                address = "Bengaluru"
//            ),
//            RestaurantItemFull(
//                id = 6,
//                imageRes = R.drawable.dessert_rasmalai,
//                title = "Fresh Rasmalai",
//                price = "120",
//                restaurantName = "Royal Sweets",
//                rating = "4.7",
//                deliveryTime = "20-25 mins",
//                distance = "1.1 km",
//                discount = "10% OFF",
//                discountAmount = "up to ₹15",
//                address = "Kolkata"
//            ),
//            RestaurantItemFull(
//                id = 7,
//                imageRes = R.drawable.dessert_brownie,
//                title = "Walnut Brownie with Ice Cream",
//                price = "190",
//                restaurantName = "Brownie Factory",
//                rating = "4.5",
//                deliveryTime = "15-20 mins",
//                distance = "0.8 km",
//                discount = "20% OFF",
//                discountAmount = "up to ₹40",
//                address = "Bengaluru"
//            ),
//            RestaurantItemFull(
//                id = 8,
//                imageRes = R.drawable.dessert_creme_brulee,
//                title = "Vanilla Crème Brûlée",
//                price = "280",
//                restaurantName = "French Patisserie",
//                rating = "4.9",
//                deliveryTime = "25-30 mins",
//                distance = "1.8 km",
//                discount = "15% OFF",
//                discountAmount = "up to ₹45",
//                address = "Pune"
//            ),
//            RestaurantItemFull(
//                id = 9,
//                imageRes = R.drawable.dessert_jalebi,
//                title = "Hot Jalebi with Rabri",
//                price = "110",
//                restaurantName = "Street Food Hub",
//                rating = "4.3",
//                deliveryTime = "15-20 mins",
//                distance = "0.9 km",
//                discount = "10% OFF",
//                discountAmount = "up to ₹12",
//                address = "Jaipur"
//            ),
//            RestaurantItemFull(
//                id = 10,
//                imageRes = R.drawable.dessert_mousse,
//                title = "Dark Chocolate Mousse",
//                price = "200",
//                restaurantName = "Chocolate Studio",
//                rating = "4.6",
//                deliveryTime = "20-25 mins",
//                distance = "1.2 km",
//                discount = "30% OFF",
//                discountAmount = "up to ₹65",
//                address = "Hyderabad"
//            ),
//            RestaurantItemFull(
//                id = 11,
//                imageRes = R.drawable.dessert_fruit_tart,
//                title = "Mixed Fruit Tart",
//                price = "170",
//                restaurantName = "Fresh Bakes",
//                rating = "4.5",
//                deliveryTime = "15-20 mins",
//                distance = "0.8 km",
//                discount = "20% OFF",
//                discountAmount = "up to ₹35",
//                address = "Bengaluru"
//            ),
//            RestaurantItemFull(
//                id = 12,
//                imageRes = R.drawable.dessert_kulfi,
//                title = "Malai Kulfi (2 pieces)",
//                price = "95",
//                restaurantName = "Traditional Kulfi",
//                rating = "4.8",
//                deliveryTime = "15-20 mins",
//                distance = "1.0 km",
//                discount = "15% OFF",
//                discountAmount = "up to ₹15",
//                address = "Lucknow"
//            ),
//            RestaurantItemFull(
//                id = 13,
//                imageRes = R.drawable.dessert_pancakes,
//                title = "Berry Pancakes with Maple Syrup",
//                price = "210",
//                restaurantName = "Breakfast Cafe",
//                rating = "4.6",
//                deliveryTime = "20-25 mins",
//                distance = "1.3 km",
//                discount = "25% OFF",
//                discountAmount = "up to ₹55",
//                address = "Chennai"
//            ),
//            RestaurantItemFull(
//                id = 14,
//                imageRes = R.drawable.dessert_rasgulla,
//                title = "Bengali Rasgulla (4 pieces)",
//                price = "130",
//                restaurantName = "Sweet Bengal",
//                rating = "4.7",
//                deliveryTime = "20-25 mins",
//                distance = "1.1 km",
//                discount = "20% OFF",
//                discountAmount = "up to ₹30",
//                address = "Kolkata"
//            ),
//            RestaurantItemFull(
//                id = 15,
//                imageRes = R.drawable.dessert_profiteroles,
//                title = "Chocolate Profiteroles",
//                price = "240",
//                restaurantName = "French Bakery",
//                rating = "4.8",
//                deliveryTime = "25-30 mins",
//                distance = "1.6 km",
//                discount = "10% OFF",
//                discountAmount = "up to ₹25",
//                address = "Mumbai"
//            ),
//            RestaurantItemFull(
//                id = 16,
//                imageRes = R.drawable.dessert_halwa,
//                title = "Gajar Ka Halwa",
//                price = "140",
//                restaurantName = "North Indian Kitchen",
//                rating = "4.4",
//                deliveryTime = "15-20 mins",
//                distance = "0.9 km",
//                discount = "15% OFF",
//                discountAmount = "up to ₹22",
//                address = "Delhi"
//            ),
//            RestaurantItemFull(
//                id = 17,
//                imageRes = R.drawable.dessert_macarons,
//                title = "Assorted Macarons (6 pieces)",
//                price = "320",
//                restaurantName = "Parisian Cafe",
//                rating = "4.9",
//                deliveryTime = "25-30 mins",
//                distance = "1.7 km",
//                discount = "20% OFF",
//                discountAmount = "up to ₹65",
//                address = "Goa"
//            ),
//            RestaurantItemFull(
//                id = 18,
//                imageRes = R.drawable.dessert_shrikhand,
//                title = "Kesar Shrikhand",
//                price = "125",
//                restaurantName = "Gujarati Thali",
//                rating = "4.5",
//                deliveryTime = "18-22 mins",
//                distance = "1.0 km",
//                discount = "15% OFF",
//                discountAmount = "up to ₹20",
//                address = "Ahmedabad"
//            ),
//            RestaurantItemFull(
//                id = 19,
//                imageRes = R.drawable.dessert_waffles,
//                title = "Belgian Waffles with Fruits",
//                price = "190",
//                restaurantName = "Waffle House",
//                rating = "4.7",
//                deliveryTime = "15-20 mins",
//                distance = "0.8 km",
//                discount = "25% OFF",
//                discountAmount = "up to ₹50",
//                address = "Bengaluru"
//            ),
//            RestaurantItemFull(
//                id = 20,
//                imageRes = R.drawable.dessert_combo,
//                title = "Dessert Platter (Assorted)",
//                price = "350",
//                restaurantName = "Grand Sweets",
//                rating = "4.9",
//                deliveryTime = "25-30 mins",
//                distance = "1.5 km",
//                discount = "20% OFF",
//                discountAmount = "up to ₹75",
//                address = "Mumbai"
//            )
//        )
//        Column {
//            sampleDessertItems.forEach { restaurantItem ->
//                RestaurantItemListFull(
//                    restaurantItem = restaurantItem,
//                    onWishlistClick = { },
//                    onThreeDotClick = { },
//                    onItemClick = { }
//                )
//            }
//        }
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
                    CategoryPage.Diet -> 1
                    CategoryPage.Pizzas -> 2
                    CategoryPage.Cakes -> 3
                    CategoryPage.Momos -> 4
                    CategoryPage.Rolls -> 5
                    CategoryPage.Burgers -> 6
                    CategoryPage.CholeBhature -> 7
                    CategoryPage.Salad -> 8
                    CategoryPage.Patty -> 9
                    CategoryPage.Chinese -> 10
                    CategoryPage.IceCream -> 11
                    CategoryPage.Appam -> 12
                    CategoryPage.Bath -> 13
                    CategoryPage.Bonda -> 14
                    CategoryPage.Cutlet -> 15
                    CategoryPage.Dessert -> 16
                    CategoryPage.Dhokla -> 17
                    CategoryPage.Dosa -> 18
                    CategoryPage.Dholda -> 19
                    CategoryPage.GulabJamun -> 20
                    CategoryPage.Idli -> 21
                    CategoryPage.Biryani -> 22
                    CategoryPage.Thali -> 23
                    CategoryPage.Chicken -> 24
                    CategoryPage.VegMeal -> 25
                    CategoryPage.NorthIndian -> 26
                    CategoryPage.Paneer -> 27
                    CategoryPage.FriedRice -> 28
                    CategoryPage.Noodles -> 29
                    CategoryPage.Paratha -> 30
                    CategoryPage.Shawarma -> 31
                    CategoryPage.SeeAll -> 32
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