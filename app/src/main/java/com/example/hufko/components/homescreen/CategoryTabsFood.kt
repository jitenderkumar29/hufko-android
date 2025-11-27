package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.hufko.components.homescreen.RestaurantItemFull

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
                    id = "sort",
                    text = "Sort by",
                    type = FilterType.SORT_DROPDOWN,
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
                        id = "sort",
                        text = "Sort by",
                        type = FilterType.SORT_DROPDOWN,
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
        rows = 1
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
//                when (filterName) {
//                    "Filters" -> showFilterDialog()
//                    "Cheese Burst" -> filterByCrustType("cheese_burst")
//                    "Farmhouse" -> filterByPizzaType("farmhouse")
//                    "Margherita" -> filterByPizzaType("margherita")
//                    "Multigrain" -> filterByCrustType("multigrain")
//                    "Pan" -> filterByCrustType("pan")
//                    "Under ₹150" -> filterByPrice(150)
//                    "Under 30 mins" -> filterByDeliveryTime(30)
//                    "Rating 4.0+" -> filterByRating(4.0)
//                    "Pure Veg" -> filterByVeg(true)
//                    "Schedule" -> showScheduleDialog()
//                    "Paneer" -> filterByTopping("paneer")
//                    "Pepperoni" -> filterByTopping("pepperoni")
//                }

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
                    id = "sort",
                    text = "Sort by",
                    type = FilterType.SORT_DROPDOWN,
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
                    id = "sort",
                    text = "Sort by",
                    type = FilterType.SORT_DROPDOWN,
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
                    id = "sort",
                    text = "Sort by",
                    type = FilterType.SORT_DROPDOWN,
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
                    id = "sort",
                    text = "Sort by",
                    type = FilterType.SORT_DROPDOWN,
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
                    id = "sort",
                    text = "Sort by",
                    type = FilterType.SORT_DROPDOWN,
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
            .padding(16.dp)
    ) {
        Text(
            text = "Salad Food",
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