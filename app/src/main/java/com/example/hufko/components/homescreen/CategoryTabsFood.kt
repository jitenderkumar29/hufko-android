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
                painterResource(id = R.drawable.all_food_banner1),
                painterResource(id = R.drawable.all_food_banner2),
                painterResource(id = R.drawable.all_food_banner3),
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
            modifier = Modifier
                .fillMaxWidth()
        )

        // Filter Button
        FilterButtonFood()

        val sampleRestaurantList = listOf(
            RestaurantItemFull(
                id = 1,
                imageRes = R.drawable.restaurant_image,
                title = "Paneer Delight Momos",
                price = "159",
                restaurantName = "Goblins",
                rating = "4.0",
                deliveryTime = "30-35 mins",
                distance = "5.8 km",
                address = "Givindpuram",
                discount = "50%",
                discountAmount = "100"
            ),
            RestaurantItemFull(
                id = 2,
                imageRes = R.drawable.restaurant_image,
                title = "Veg Roll",
                price = "99",
                restaurantName = "Roll Hub",
                rating = "4.3",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                address = "Shastri Nagar",
                discount = "40%",
                discountAmount = "80"
            ),
            RestaurantItemFull(
                id = 3,
                imageRes = R.drawable.restaurant_image,
                title = "Chicken Biryani",
                price = "229",
                restaurantName = "Biryani House",
                rating = "4.5",
                deliveryTime = "35-40 mins",
                distance = "6.1 km",
                address = "Rajnagar",
                discount = "30%",
                discountAmount = "120"
            )
        )

        MaterialTheme {
            RestaurantListScreen()
        }

//        LazyColumn {
//            items(sampleRestaurantList) { item ->
//                RestaurantItemListFull(
//                    restaurantItem = item,
//                    onWishlistClick = { /* handle */ },
//                    onThreeDotClick = { /* handle */ },
//                    onItemClick = { /* handle */ }
//                )
//            }
//        }
//        val sampleRestaurantItem = RestaurantItemFull(
//            id = 1,
//            imageRes = R.drawable.restaurant_image, // Use your actual drawable resource
//            title = "Paneer Delight Momos",
//            price = "159",
//            restaurantName = "Goblins",
//            rating = "4.0",
//            deliveryTime = "30-35 mins",
//            distance = "5.8 km",
//            address = "Givindpuram",
//            discount = "50%",
//            discountAmount = "100",
//            isWishlisted = false
//        )
//        RestaurantItemListFull(
//            restaurantItem = sampleRestaurantItem, // Fixed parameter name
//            onWishlistClick = { },
//            onThreeDotClick = { },
//            onItemClick = { }
//        )
    }
}


val sampleRestaurantList = listOf(
    RestaurantItemFull(
        id = 1,
        imageRes = R.drawable.restaurant_image,
        title = "Paneer Delight Momos",
        price = "159",
        restaurantName = "Goblins",
        rating = "4.0",
        deliveryTime = "30-35 mins",
        distance = "5.8 km",
        address = "Givindpuram",
        discount = "50%",
        discountAmount = "100",
        isWishlisted = false
    ),
    RestaurantItemFull(
        id = 2,
        imageRes = R.drawable.restaurant_image,
        title = "Veg Roll",
        price = "99",
        restaurantName = "Roll Hub",
        rating = "4.3",
        deliveryTime = "25-30 mins",
        distance = "3.2 km",
        address = "Shastri Nagar",
        discount = "40%",
        discountAmount = "80",
        isWishlisted = false
    ),
    RestaurantItemFull(
        id = 3,
        imageRes = R.drawable.restaurant_image,
        title = "Chicken Biryani",
        price = "229",
        restaurantName = "Biryani House",
        rating = "4.5",
        deliveryTime = "35-40 mins",
        distance = "6.1 km",
        address = "Rajnagar",
        discount = "30%",
        discountAmount = "120",
        isWishlisted = false
    )
)

@Composable
fun RestaurantListScreen(
    restaurants: List<RestaurantItemFull> = sampleRestaurantList,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(restaurants, key = { it.id }) { item ->
            // call the composable that accepts RestaurantItemFull
            RestaurantItemListFull(
                restaurantItem = item,
                onWishlistClick = { id -> /* handle wishlist for id */ },
                onThreeDotClick = { id -> /* handle 3-dot */ },
                onItemClick = { id -> /* handle item click */ }
            )
        }
    }
}


@Composable
fun PizzasCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//                .padding(20.dp)
    ) {

//
//        val sampleFoodItems = listOf(
//            FoodItem(
//                price = "2329",
//                title = "Classic Chicken Salad",
//                restaurantName = "Caterspoint",
//                deliveryTime = "60-65 mins",
//                rating = "4.5★",
//                calorieInfo = "576 kcal",
//                proteinInfo = "42g protein",
//                additionalInfo1 = "32.9",
//                additionalInfo2 = "Chick",
//                additionalInfo3 = "Pret A",
//                tags = listOf("High", "44%", "Hi"),
//                imageRes = R.drawable.paratha_corner,
//            ),
//            FoodItem(
//                price = "1899",
//                title = "Mediterranean Bowl",
//                restaurantName = "Healthy Eats",
//                deliveryTime = "45-50 mins",
//                rating = "4.7★",
//                calorieInfo = "480 kcal",
//                proteinInfo = "35g protein",
//                additionalInfo1 = "28.5",
//                additionalInfo2 = "Veg",
//                additionalInfo3 = "Fresh",
//                tags = listOf("Medium", "38%"),
//                imageRes = R.drawable.paratha_corner,
//            ),
//            FoodItem(
//                price = "2799",
//                title = "Protein Power Meal",
//                restaurantName = "Fit Kitchen",
//                deliveryTime = "55-60 mins",
//                rating = "4.8★",
//                calorieInfo = "650 kcal",
//                proteinInfo = "55g protein",
//                additionalInfo1 = "35.2",
//                additionalInfo2 = "Non-Veg",
//                additionalInfo3 = "Power",
//                tags = listOf("High", "52%"),
//                imageRes = R.drawable.paratha_corner,
//            )
//        )
//
//        FoodCarousel(foodItems = sampleFoodItems)

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 0.dp, start = 20.dp, end = 20.dp
                )
        )
        {
            val bannerImages = listOf(
                painterResource(id = R.drawable.all_pizzas_banner1),
                painterResource(id = R.drawable.all_pizzas_banner2),
                painterResource(id = R.drawable.all_pizzas_banner3),
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


        val sampleRestaurants = listOf(
            Restaurant(
                id = 1,
                imageRes = R.drawable.le_fusion_bakery_pizzas,
                restaurantName = "LE FUSION BAKERY",
                bestIn = "",
                rating = 4.4,
                totalRating = "3",
                deliverTime = "40-45 mins",
                categories = "Snacks, Pizzas, Burgers",
                address = "Raj Nagar",
                distance = "6.0 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 2,
                imageRes = R.drawable.la_pinoz_pizza_pizzas,
                restaurantName = "La Pino'z Pizza",
                bestIn =  "",
                rating = 4.2,
                totalRating = "10K+",
                deliverTime = "55-65 mins",
                categories = "Pizzas, Pastas, Italian, Desserts",
                address = "Raj Nagar",
                distance = "10.9 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 3,
                imageRes = R.drawable.bikanervala_pizzas,
                restaurantName = "Bikanervala",
                bestIn = "Best In Mithai",
                rating = 4.3,
                totalRating = "3.5K+",
                deliverTime = "55-65 mins",
                categories = "Bakery, Chinese, North Indian",
                address = "Govind Puram",
                distance = "7.7 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 4,
                imageRes = R.drawable.rominus_pizza_and_burger_pizzas,
                restaurantName = "Rominus Pizza And Burger",
                bestIn =  "",
                rating = 4.1,
                totalRating = "3.5K+",
                deliverTime = "55-65 mins",
                categories = "Pizzas, Italian-American, American",
                address = "Raj Nagar",
                distance = "11.1 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 5,
                imageRes = R.drawable.bros_pizza_pizzas,
                restaurantName = "Bros Pizza",
                bestIn = "",
                rating = 3.8,
                totalRating = "227",
                deliverTime = "45-55 mins",
                categories = "Pizzas, Burgers, Italian, Beverages",
                address = "SHASTRI NAGAR",
                distance = "96 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 6,
                imageRes = R.drawable.burger_king_pizzas,
                restaurantName = "Burger King",
                bestIn = "Best In Burger",
                rating = 4.4,
                totalRating = "32K+",
                deliverTime = "50-60 mins",
                categories = "Burgers, American",
                address = "Raj Nagar",
                distance = "11.2 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 7,
                imageRes = R.drawable.rockers_pizza_pizzas,
                restaurantName = "Rockers Pizza",
                bestIn =  "",
                rating = 4.0,
                totalRating = "175",
                deliverTime = "65-75 mins",
                categories = "Pizzas, Fast Food",
                address = "Raj Nagar",
                distance = "11.7 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 8,
                imageRes = R.drawable.roms_pizza_pizzas,
                restaurantName = "Roms Pizza",
                bestIn = "Pure Veg",
                rating = 4.3,
                totalRating = "5.9K+",
                deliverTime = "48-53 mins",
                categories = "Pizzas, Burgers, sandwich, Fast Food",
                address = "Shastri Nagar",
                distance = "8.1 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 9,
                imageRes = R.drawable.pizza_4_ever_pizzas,
                restaurantName = "Pizza 4 Ever",
                bestIn =  "",
                rating = 4.4,
                totalRating = "385",
                deliverTime = "60-70 mins",
                categories = "Pizzas, Italian",
                address = "Raj Nagar",
                distance = "11.7 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 10,
                imageRes = R.drawable.the_pizza_cafe_pizzas,
                restaurantName = "The Pizza Cafe",
                bestIn =  "",
                rating = 3.9,
                totalRating = "8.4K+",
                deliverTime = "50-60 mins",
                categories = "Pizzas, Fast Food, Italian, Pastas",
                address = "Shastri Nagar",
                distance = "96 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 11,
                imageRes = R.drawable.madan_sweets_pizzas,
                restaurantName = "Madan Sweets and Restaurant",
                bestIn = "Best In Local Gems",
                rating = 4.4,
                totalRating = "17K+",
                deliverTime = "65-75 mins",
                categories = "North Indian, Indian, Punjabi, Sweets",
                address = "Nehru Nagar",
                distance = "12.7 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 12,
                imageRes = R.drawable.pizza_plus_pizzas,
                restaurantName = "Pizza Plus",
                bestIn = "Pure Veg",
                rating = 3.7,
                totalRating = "566",
                deliverTime = "50-60 mins",
                categories = "Continental, Italian",
                address = "Raj Nagar",
                distance = "96 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 13,
                imageRes = R.drawable.samosa_party_pizzas,
                restaurantName = "Samosa Party",
                bestIn =  "",
                rating = 4.4,
                totalRating = "439",
                deliverTime = "60-70 mins",
                categories = "Fast Food, Snacks, Beverages",
                address = "Raj Nagar",
                distance = "12.6 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 14,
                imageRes = R.drawable.ankur_food_services_pizzas,
                restaurantName = "Ankur Food Services",
                bestIn = "Pure Veg",
                rating = 4.6,
                totalRating = "66",
                deliverTime = "60-70 mins",
                categories = "North Indian, Chinese, Continental",
                address = "Ashok Nagar",
                distance = "12.3 km",
                isWishlisted = false
            ),
            Restaurant(
                id = 15,
                imageRes = R.drawable.nathus_sweets_pizzas,
                restaurantName = "Nathu's Sweets",
                bestIn =  "",
                rating = 4.4,
                totalRating = "57K+",
                deliverTime = "65-75 mins",
                categories = "Sweets, North Indian, Chinese, Snacks",
                address = "Raj Nagar",
                distance = "11.2 km",
                isWishlisted = false
            )
        )

//        RestaurantList(
//            restaurants = sampleRestaurants,
//            onRestaurantClick = { restaurant ->
//                // Handle restaurant click
//                println("Clicked on: ${restaurant.restaurantName}")
//            },
//            onWishlistToggle = { restaurant ->
//                // Handle wishlist toggle
//                println("Toggled wishlist for: ${restaurant.restaurantName}")
//            }
//        )
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