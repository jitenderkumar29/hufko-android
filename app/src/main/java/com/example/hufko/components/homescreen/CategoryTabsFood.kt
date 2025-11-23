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
        FilterButtonFood()
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
    Column(
        modifier = Modifier
            .fillMaxSize()
//                .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        // Style Guide 101
        val styleGuideCategoriesSimple = listOf(
            CategoryItemF(0, "", R.drawable.ic_pizzas_food_1, ""),
            CategoryItemF(1, "", R.drawable.ic_pizzas_food_2, ""),
            CategoryItemF(2, "", R.drawable.ic_pizzas_food_3, ""),
            CategoryItemF(3, "", R.drawable.ic_pizzas_food_4, ""),
            CategoryItemF(4, "", R.drawable.ic_pizzas_food_5, ""),
            CategoryItemF(5, "", R.drawable.ic_pizzas_food_6, ""),
        )
//        Image(
//            painter = painterResource(R.drawable.ic_style_guide_header_casual_men),
//            contentDescription = "Banner",
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(
//                    min = 100.dp,
//                    max = 300.dp
//                ), // Height between min and max, // 30% of screen height, // Sets height based on width and aspect ratio
//            contentScale = ContentScale.FillBounds
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        Text(
//            text = "Recommended for you",
//            style = MaterialTheme.typography.bodySmall.copy(
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.customColors.black
//            ),
////            textAlign = TextAlign.Center,
//            maxLines = 1,
//            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        CategoryListDoubleF(
//            items = styleGuideCategoriesSimple,
//            onItemClick = { item -> println("Selected: ${item.name}") },
//            showOverlayOnImage = false,
//            showItemName = false,
//            itemWidth = 140.dp,
//            itemHeight = 150.dp,
//            horizontalSpacing = 12.dp,
//            verticalPadding = 12.dp,
//            backgroundColor = Color(0xFFFFFFFF),
////                        showOverlayOnImage = false,
////                        overlayBackground = Color.Black.copy(alpha = 0.6f),
//        )
        Spacer(modifier = Modifier.height(10.dp))
        FilterButtonFood()

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