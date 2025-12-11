package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
sealed class DietCategoryPage(val title: String, val iconRes: Int) {
    // From the image provided
    object Chicken : DietCategoryPage("Chicken", R.drawable.chicken_food_diet)
    object Salad : DietCategoryPage("Salad", R.drawable.salad_food_diet)
    object Mutton : DietCategoryPage("Mutton", R.drawable.mutton_food_diet)
    object Kebabs : DietCategoryPage("Kebabs", R.drawable.kebabs_food_diet)
    object HealthySnacks : DietCategoryPage("Snacks", R.drawable.healthy_snacks_food_diet)
    object LowCalorie : DietCategoryPage("Low Calorie", R.drawable.low_calorie_food_diet)
    object Vegan : DietCategoryPage("Vegan", R.drawable.vegan_food_diet)
    object ProteinRich : DietCategoryPage("Protein Rich", R.drawable.protein_food_diet)
    object SeeAll : DietCategoryPage("See All", R.drawable.see_all_food)
}

@Composable
fun CategoryDietTabsFood(
    selectedDietTabIndex: Int = 0,
    onCategorySelected: (DietCategoryPage) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val dietCategoryPages = listOf(
        DietCategoryPage.Chicken,
        DietCategoryPage.Salad,
        DietCategoryPage.Mutton,
        DietCategoryPage.Kebabs,
        DietCategoryPage.HealthySnacks,
        DietCategoryPage.LowCalorie,
        DietCategoryPage.Vegan,
        DietCategoryPage.ProteinRich,
        DietCategoryPage.SeeAll
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors.orangeLight)
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
                    color = MaterialTheme.customColors.header
                )
            }
        ) {
            dietCategoryPages.forEachIndexed { index, dietCategoryPage ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        onCategorySelected(dietCategoryPage)
                    },
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 5.dp)
//                        .padding(horizontal = 2.dp)
                        .background(Color.Transparent)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                            .width(80.dp)   // 👈 REQUIRED for ellipsis
                            .background(
                                color = MaterialTheme.customColors.white,
                                shape = RoundedCornerShape(15.dp)  // ← Rounded corners applied here
                            )
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = dietCategoryPage.iconRes),
                            contentDescription = dietCategoryPage.title,
                            modifier = Modifier
                                .width(65.dp)
                                .height(55.dp),
                            contentScale = ContentScale.FillBounds
                        )

                        Text(
                            text = dietCategoryPage.title,
                            fontSize = 15.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.customColors.header
                            } else {
                                MaterialTheme.customColors.black
                            },
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 2.dp)
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
            // Map index to corresponding page function using a when statement
            when (selectedTabIndex) {
                0 -> ChickenDietPage()
                1 -> SaladDietPage()
                2 -> MuttonDietPage()
                3 -> KebabsDietPage()
                4 -> HealthySnacksPage()  // Fixed: index 4 corresponds to HealthySnacks
                5 -> LowCaloriePage()     // Fixed: index 5 corresponds to LowCalorie
                6 -> VeganPage()          // Fixed: index 6 corresponds to Vegan
                7 -> ProteinRichPage()    // Fixed: index 7 corresponds to ProteinRich
                8 -> SeeAllPage()    // Fixed: index 8 corresponds to ProteinRich
            }
        }
    }
}

// Category Page Composables for diet tabs

@Composable
fun ChickenDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val chickenDietFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "grilled",
                    text = "Grilled Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_grilled
                ),
                FilterChip(
                    id = "fried",
                    text = "Fried Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_fried
                ),
                FilterChip(
                    id = "curry",
                    text = "Chicken Curry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_curry
                ),
                FilterChip(
                    id = "roast",
                    text = "Roast Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_roast
                ),
                FilterChip(
                    id = "butter_chicken",
                    text = "Butter Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_butter
                ),
                FilterChip(
                    id = "tandoori",
                    text = "Tandoori",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_tandoori
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "boneless",
                    text = "Boneless Only",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_250",
                    text = "Under ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "protein_rich",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy",
                    text = "Healthy Options",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
         FilterButtonFood(
            filterConfig = chickenDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val chickenDietFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_chicken_grilled_diet,
                title = "Grilled Chicken",
                price = "250",
                restaurantName = "Chicken Kingdom",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "4.2 km",
                discount = "30%",
                discountAmount = "up to ₹75",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_chicken_fried_diet,
                title = "Crispy Fried Chicken",
                price = "220",
                restaurantName = "Chicken Express",
                rating = "4.3",
                deliveryTime = "25-30 mins",
                distance = "3.5 km",
                discount = "25%",
                discountAmount = "up to ₹55",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_chicken_curry_diet,
                title = "Chicken Curry",
                price = "280",
                restaurantName = "Spice Hub",
                rating = "4.4",
                deliveryTime = "40-45 mins",
                distance = "5.8 km",
                discount = "20%",
                discountAmount = "up to ₹56",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_chicken_roast_diet,
                title = "Roast Chicken",
                price = "320",
                restaurantName = "Royal Chicken",
                rating = "4.6",
                deliveryTime = "50-55 mins",
                distance = "6.3 km",
                discount = "15%",
                discountAmount = "up to ₹48",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_chicken_butter_diet,
                title = "Butter Chicken",
                price = "260",
                restaurantName = "Dhaba Style",
                rating = "4.7",
                deliveryTime = "35-40 mins",
                distance = "4.7 km",
                discount = "40%",
                discountAmount = "up to ₹104",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_chicken_tandoori_diet,
                title = "Tandoori Chicken",
                price = "240",
                restaurantName = "Tandoor Special",
                rating = "4.2",
                deliveryTime = "30-35 mins",
                distance = "3.9 km",
                discount = "35%",
                discountAmount = "up to ₹84",
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
            foodItems = chickenDietFoodItems,
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
    val chickenDietRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_chicken_food_1,
            title = "Grilled Chicken Platter",
            price = "325",
            restaurantName = "Chicken Kingdom",
            rating = "4.5",
            deliveryTime = "30-35 mins",
            distance = "4.2 km",
            discount = "30% OFF",
            discountAmount = "up to ₹100",
            address = "Grill Street, Downtown"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_chicken_food_2,
            title = "Crispy Fried Chicken Bucket",
            price = "450",
            restaurantName = "Chicken Express",
            rating = "4.3",
            deliveryTime = "25-30 mins",
            distance = "3.5 km",
            discount = "25% OFF",
            discountAmount = "up to ₹120",
            address = "Fast Food Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_chicken_food_3,
            title = "Chicken Butter Masala",
            price = "280",
            restaurantName = "Spice Hub",
            rating = "4.4",
            deliveryTime = "40-45 mins",
            distance = "5.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹80",
            address = "Curry Corner"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_chicken_food_4,
            title = "Whole Roast Chicken",
            price = "550",
            restaurantName = "Royal Chicken",
            rating = "4.6",
            deliveryTime = "50-55 mins",
            distance = "6.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹85",
            address = "Royal Feast Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_chicken_food_5,
            title = "Butter Chicken with Naan",
            price = "320",
            restaurantName = "Dhaba Style",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.7 km",
            discount = "40% OFF",
            discountAmount = "up to ₹130",
            address = "Highway Dhaba"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_chicken_food_6,
            title = "Tandoori Chicken Platter",
            price = "380",
            restaurantName = "Tandoor Special",
            rating = "4.2",
            deliveryTime = "30-35 mins",
            distance = "3.9 km",
            discount = "35% OFF",
            discountAmount = "up to ₹135",
            address = "Tandoori Street"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_chicken_food_7,
            title = "Chilli Chicken Combo",
            price = "290",
            restaurantName = "Chinese Wok",
            rating = "4.1",
            deliveryTime = "20-25 mins",
            distance = "2.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹90",
            address = "Chinatown Plaza"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_chicken_food_8,
            title = "Hyderabadi Chicken Biryani",
            price = "260",
            restaurantName = "Biryani House",
            rating = "4.8",
            deliveryTime = "35-40 mins",
            distance = "4.5 km",
            discount = "50% OFF",
            discountAmount = "up to ₹130",
            address = "Biryani Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_chicken_food_9,
            title = "Chicken Shawarma Platter",
            price = "220",
            restaurantName = "Arabian Nights",
            rating = "4.3",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹55",
            address = "Middle Eastern Quarter"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_chicken_food_10,
            title = "Chicken 65 Special",
            price = "240",
            restaurantName = "Spicy Corner",
            rating = "4.0",
            deliveryTime = "30-35 mins",
            distance = "4.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Spice Market"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_chicken_food_11,
            title = "Chicken Tikka Masala",
            price = "310",
            restaurantName = "Tikka Palace",
            rating = "4.4",
            deliveryTime = "40-45 mins",
            distance = "5.2 km",
            discount = "30% OFF",
            discountAmount = "up to ₹95",
            address = "Royal Tikka Street"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_chicken_food_12,
            title = "Chicken Korma Deluxe",
            price = "270",
            restaurantName = "Mughlai Darbar",
            rating = "4.6",
            deliveryTime = "45-50 mins",
            distance = "6.1 km",
            discount = "35% OFF",
            discountAmount = "up to ₹95",
            address = "Mughlai Avenue"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_chicken_food_13,
            title = "Chicken Lollipop Basket",
            price = "290",
            restaurantName = "Starters Heaven",
            rating = "4.2",
            deliveryTime = "25-30 mins",
            distance = "3.7 km",
            discount = "40% OFF",
            discountAmount = "up to ₹120",
            address = "Appetizer Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_chicken_food_14,
            title = "Chicken Burger Combo",
            price = "199",
            restaurantName = "Burger King",
            rating = "4.1",
            deliveryTime = "20-25 mins",
            distance = "2.5 km",
            discount = "50% OFF",
            discountAmount = "up to ₹100",
            address = "Fast Food Court"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_chicken_food_15,
            title = "Chicken Wings Platter",
            price = "350",
            restaurantName = "Wings World",
            rating = "4.3",
            deliveryTime = "30-35 mins",
            distance = "4.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹90",
            address = "Wings Street"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_chicken_food_16,
            title = "Chicken Sandwich Meal",
            price = "180",
            restaurantName = "Subway Delights",
            rating = "4.0",
            deliveryTime = "15-20 mins",
            distance = "1.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹55",
            address = "Quick Bite Center"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_chicken_food_17,
            title = "Chicken Teriyaki Bowl",
            price = "320",
            restaurantName = "Tokyo Kitchen",
            rating = "4.5",
            deliveryTime = "35-40 mins",
            distance = "5.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹65",
            address = "Japanese Quarter"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_chicken_food_18,
            title = "Chicken Popcorn Bucket",
            price = "210",
            restaurantName = "Movie Munchies",
            rating = "4.1",
            deliveryTime = "25-30 mins",
            distance = "3.4 km",
            discount = "Buy 1 get 1 free",
            discountAmount = "On large buckets",
            address = "Entertainment District"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_chicken_food_19,
            title = "Chicken Pasta Alfredo",
            price = "280",
            restaurantName = "Italian Bistro",
            rating = "4.4",
            deliveryTime = "30-35 mins",
            distance = "4.6 km",
            discount = "25% OFF",
            discountAmount = "up to ₹70",
            address = "Italian Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_chicken_food_20,
            title = "Chicken Salad Bowl",
            price = "230",
            restaurantName = "Healthy Bites",
            rating = "4.2",
            deliveryTime = "20-25 mins",
            distance = "2.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹70",
            address = "Wellness Center"
        )
    )

    Column {
        chickenDietRestaurantItems.forEach { restaurantItem ->
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
fun SaladDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
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
                    id = "greek_salad",
                    text = "Greek Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_greek
                ),
                FilterChip(
                    id = "caesar_salad",
                    text = "Caesar Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_caesar
                ),
                FilterChip(
                    id = "fruit_salad",
                    text = "Fruit Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_fruit
                ),
                FilterChip(
                    id = "chicken_salad",
                    text = "Chicken Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_chicken
                ),
                FilterChip(
                    id = "quinoa_salad",
                    text = "Quinoa Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_quinoa
                ),
                FilterChip(
                    id = "pasta_salad",
                    text = "Pasta Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_pasta
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
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fresh_made",
                    text = "Fresh Made",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
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
        // Sample data with all fields
        val saladDietFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_salad_greek_diet,
                title = "Greek Salad",
                price = "180",
                restaurantName = "Mediterranean Delight",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "3.2 km",
                discount = "25%",
                discountAmount = "up to ₹45",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_salad_caesar_diet,
                title = "Caesar Salad",
                price = "220",
                restaurantName = "Salad Bar",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "2.8 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_salad_fruit_diet,
                title = "Fruit Salad",
                price = "150",
                restaurantName = "Fruit Paradise",
                rating = "4.6",
                deliveryTime = "10-15 mins",
                distance = "1.5 km",
                discount = "30%",
                discountAmount = "up to ₹45",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_salad_chicken_diet,
                title = "Chicken Salad",
                price = "240",
                restaurantName = "Protein Hub",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "4.1 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_salad_quinoa_diet,
                title = "Quinoa Salad",
                price = "200",
                restaurantName = "Healthy Bites",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "3.5 km",
                discount = "25%",
                discountAmount = "up to ₹50",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_salad_pasta_diet,
                title = "Pasta Salad",
                price = "190",
                restaurantName = "Italian Greens",
                rating = "4.2",
                deliveryTime = "25-30 mins",
                distance = "3.8 km",
                discount = "20%",
                discountAmount = "up to ₹38",
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
            foodItems = saladDietFoodItems,
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
//
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

    val saladDietRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_salad_food_1,
            title = "Mediterranean Quinoa Bowl",
            price = "285",
            restaurantName = "Green Leaf Cafe",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Health Street, Downtown"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_salad_food_2,
            title = "Protein Power Salad",
            price = "320",
            restaurantName = "FitFuel Kitchen",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹80",
            address = "Fitness Hub Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_salad_food_3,
            title = "Caesar Salad with Grilled Chicken",
            price = "250",
            restaurantName = "Salad Bar Express",
            rating = "4.4",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹75",
            address = "Quick Bite Corner"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_salad_food_4,
            title = "Greek Salad Platter",
            price = "220",
            restaurantName = "Mediterranean Delight",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹35",
            address = "Olive Oil Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_salad_food_5,
            title = "Rainbow Veggie Bowl",
            price = "195",
            restaurantName = "Vegan Vibes",
            rating = "4.8",
            deliveryTime = "30-35 mins",
            distance = "4.1 km",
            discount = "40% OFF",
            discountAmount = "up to ₹80",
            address = "Plant-Based District"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_salad_food_6,
            title = "Avocado & Chickpea Salad",
            price = "240",
            restaurantName = "Superfood Kitchen",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.4 km",
            discount = "35% OFF",
            discountAmount = "up to ₹85",
            address = "Nutrition Street"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_salad_food_7,
            title = "Thai Mango Salad",
            price = "210",
            restaurantName = "Asian Greens",
            rating = "4.3",
            deliveryTime = "30-35 mins",
            distance = "4.3 km",
            discount = "30% OFF",
            discountAmount = "up to ₹65",
            address = "Asian Fusion Plaza"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_salad_food_8,
            title = "Kale & Quinoa Super Bowl",
            price = "275",
            restaurantName = "Clean Eats",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.7 km",
            discount = "50% OFF",
            discountAmount = "up to ₹140",
            address = "Wellness Center"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_salad_food_9,
            title = "Tuna Nicoise Salad",
            price = "290",
            restaurantName = "French Bistro",
            rating = "4.4",
            deliveryTime = "35-40 mins",
            distance = "5.1 km",
            discount = "25% OFF",
            discountAmount = "up to ₹75",
            address = "European Quarter"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_salad_food_10,
            title = "Roasted Veggie & Feta Bowl",
            price = "230",
            restaurantName = "Farm to Table",
            rating = "4.7",
            deliveryTime = "25-30 mins",
            distance = "3.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Organic Market"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_salad_food_11,
            title = "Spinach & Berry Salad",
            price = "205",
            restaurantName = "Berry Fresh",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹65",
            address = "Berry Lane"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_salad_food_12,
            title = "Mexican Fiesta Bowl",
            price = "245",
            restaurantName = "Mexi-Greens",
            rating = "4.3",
            deliveryTime = "30-35 mins",
            distance = "4.2 km",
            discount = "35% OFF",
            discountAmount = "up to ₹90",
            address = "Mexican Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_salad_food_13,
            title = "Pomegranate & Walnut Salad",
            price = "225",
            restaurantName = "Nutrient Hub",
            rating = "4.6",
            deliveryTime = "25-30 mins",
            distance = "3.5 km",
            discount = "40% OFF",
            discountAmount = "up to ₹90",
            address = "Health District"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_salad_food_14,
            title = "Detox Green Salad",
            price = "180",
            restaurantName = "Juice & Salad Bar",
            rating = "4.2",
            deliveryTime = "15-20 mins",
            distance = "1.7 km",
            discount = "50% OFF",
            discountAmount = "up to ₹90",
            address = "Detox Center"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_salad_food_15,
            title = "Warm Lentil & Veggie Bowl",
            price = "195",
            restaurantName = "Comfort Greens",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "3.1 km",
            discount = "25% OFF",
            discountAmount = "up to ₹50",
            address = "Comfort Food Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_salad_food_16,
            title = "Caprese Salad Platter",
            price = "235",
            restaurantName = "Italian Greens",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.6 km",
            discount = "30% OFF",
            discountAmount = "up to ₹70",
            address = "Italian Quarter"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_salad_food_17,
            title = "Asian Crunch Salad",
            price = "215",
            restaurantName = "Tokyo Greens",
            rating = "4.4",
            deliveryTime = "30-35 mins",
            distance = "4.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹45",
            address = "Asian District"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_salad_food_18,
            title = "Beetroot & Goat Cheese Salad",
            price = "255",
            restaurantName = "Gourmet Greens",
            rating = "4.7",
            deliveryTime = "30-35 mins",
            distance = "4.0 km",
            discount = "Buy 1 get 1 free",
            discountAmount = "On all salads",
            address = "Gourmet Street"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_salad_food_19,
            title = "Southwest Chicken Salad",
            price = "265",
            restaurantName = "Grill & Greens",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.6 km",
            discount = "25% OFF",
            discountAmount = "up to ₹70",
            address = "Grill Avenue"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_salad_food_20,
            title = "Moroccan Couscous Salad",
            price = "240",
            restaurantName = "Spice & Greens",
            rating = "4.3",
            deliveryTime = "35-40 mins",
            distance = "4.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹75",
            address = "Spice Market Lane"
        )
    )
    Column {
        saladDietRestaurantItems.forEach { restaurantItem ->
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
fun MuttonDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val muttonDietFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "mutton_curry",
                    text = "Mutton Curry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_curry
                ),
                FilterChip(
                    id = "mutton_biryani",
                    text = "Mutton Biryani",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_biryani
                ),
                FilterChip(
                    id = "mutton_kebabs",
                    text = "Mutton Kebabs",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_kebab
                ),
                FilterChip(
                    id = "mutton_rogan_josh",
                    text = "Rogan Josh",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_rogan_josh
                ),
                FilterChip(
                    id = "mutton_keema",
                    text = "Mutton Keema",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_keema
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
                    id = "bone_in",
                    text = "Bone-In",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "boneless",
                    text = "Boneless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_friendly",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_500",
                    text = "Under ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "traditional",
                    text = "Traditional",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "premium",
                    text = "Premium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = muttonDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val muttonFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_mutton_biryani_diet,
                title = "Mutton Biryani",
                price = "450",
                restaurantName = "Royal Mughlai",
                rating = "4.8",
                deliveryTime = "35-40 mins",
                distance = "4.2 km",
                discount = "20%",
                discountAmount = "up to ₹90",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mutton_korma_diet,
                title = "Mutton Korma",
                price = "420",
                restaurantName = "Darbar Kitchen",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "3.5 km",
                discount = "15%",
                discountAmount = "up to ₹63",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_mutton_rogan_josh_diet,
                title = "Mutton Rogan Josh",
                price = "480",
                restaurantName = "Kashmiri Delight",
                rating = "4.7",
                deliveryTime = "40-45 mins",
                distance = "5.1 km",
                discount = "25%",
                discountAmount = "up to ₹120",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_mutton_curry_diet,
                title = "Mutton Curry",
                price = "380",
                restaurantName = "Spice Nation",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "10%",
                discountAmount = "up to ₹38",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mutton_kebabs_diet,
                title = "Mutton Seekh Kebabs",
                price = "350",
                restaurantName = "Kebab Junction",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "20%",
                discountAmount = "up to ₹70",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_mutton_handi_diet,
                title = "Mutton Handi",
                price = "550",
                restaurantName = "Handi House",
                rating = "4.8",
                deliveryTime = "35-40 mins",
                distance = "4.5 km",
                discount = "15%",
                discountAmount = "up to ₹82",
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
            foodItems = muttonFoodItems,
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
//
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

    val muttonRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_mutton_food_1,
            title = "Royal Mughlai Mutton Biryani",
            price = "450",
            restaurantName = "Royal Mughlai",
            rating = "4.8",
            deliveryTime = "35-40 mins",
            distance = "4.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹90",
            address = "Mughlai Street, Old Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_mutton_food_2,
            title = "Darbar Kitchen Mutton Korma",
            price = "420",
            restaurantName = "Darbar Kitchen",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹63",
            address = "Royal Palace Road"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_mutton_food_3,
            title = "Kashmiri Rogan Josh",
            price = "480",
            restaurantName = "Kashmiri Delight",
            rating = "4.7",
            deliveryTime = "40-45 mins",
            distance = "5.1 km",
            discount = "25% OFF",
            discountAmount = "up to ₹120",
            address = "Kashmiri Valley Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_mutton_food_4,
            title = "Spice Nation Mutton Curry",
            price = "380",
            restaurantName = "Spice Nation",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "2.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹38",
            address = "Spice Market Area"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_mutton_food_5,
            title = "Kebab Junction Seekh Kebabs",
            price = "350",
            restaurantName = "Kebab Junction",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹70",
            address = "Kebab Street Corner"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_mutton_food_6,
            title = "Handi House Mutton Handi",
            price = "550",
            restaurantName = "Handi House",
            rating = "4.8",
            deliveryTime = "35-40 mins",
            distance = "4.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹82",
            address = "Handi Special Area"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_mutton_food_7,
            title = "Keema Special Mutton Keema",
            price = "320",
            restaurantName = "Keema Special",
            rating = "4.3",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "Keema Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_mutton_food_8,
            title = "Kadai King Special Mutton",
            price = "440",
            restaurantName = "Kadai King",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹88",
            address = "Kadai Special Road"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_mutton_food_9,
            title = "Pulao Paradise Mutton Pulao",
            price = "400",
            restaurantName = "Pulao Paradise",
            rating = "4.4",
            deliveryTime = "30-35 mins",
            distance = "4.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹60",
            address = "Pulao Street"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_mutton_food_10,
            title = "Punjabi Dhaba Mutton Saag",
            price = "370",
            restaurantName = "Punjabi Dhaba",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹37",
            address = "Highway Dhaba Road"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_mutton_food_11,
            title = "Kolhapuri Mutton Special",
            price = "460",
            restaurantName = "Maharashtrian Tadka",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹92",
            address = "Maharashtrian Food Street"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_mutton_food_12,
            title = "Afghani Mutton Delicacy",
            price = "520",
            restaurantName = "Afghan Kitchen",
            rating = "4.8",
            deliveryTime = "40-45 mins",
            distance = "5.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹130",
            address = "Afghan Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_mutton_food_13,
            title = "Nihari House Special Mutton",
            price = "490",
            restaurantName = "Nihari House",
            rating = "4.9",
            deliveryTime = "45-50 mins",
            distance = "5.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹73",
            address = "Nihari Street, Old Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_mutton_food_14,
            title = "Royal BBQ Mutton Raan",
            price = "750",
            restaurantName = "Royal BBQ",
            rating = "4.8",
            deliveryTime = "50-55 mins",
            distance = "6.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹150",
            address = "BBQ Grill Street"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_mutton_food_15,
            title = "Souper Bowl Mutton Soup",
            price = "280",
            restaurantName = "Souper Bowl",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹28",
            address = "Soup Corner Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_mutton_food_16,
            title = "Paya Special Mutton Paya",
            price = "390",
            restaurantName = "Paya Special",
            rating = "4.5",
            deliveryTime = "40-45 mins",
            distance = "4.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹58",
            address = "Paya Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_mutton_food_17,
            title = "Chop House Mutton Chops",
            price = "410",
            restaurantName = "Chop House",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹82",
            address = "Chop Grill Road"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_mutton_food_18,
            title = "Awadhi Kitchen Rezala",
            price = "480",
            restaurantName = "Awadhi Kitchen",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹72",
            address = "Awadhi Street, Lucknowi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_mutton_food_19,
            title = "Pao Bhaji House Keema Pao",
            price = "340",
            restaurantName = "Pao Bhaji House",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "2.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹34",
            address = "Pao Bhaji Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_mutton_food_20,
            title = "Kebab Mahal Malai Mutton",
            price = "430",
            restaurantName = "Kebab Mahal",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹86",
            address = "Kebab Mahal Road"
        )
    )
    Column {
        muttonRestaurantItems.forEach { restaurantItem ->
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
fun KebabsDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val kebabDietFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                // Kebab types with left icons
                FilterChip(
                    id = "seekh_kebab",
                    text = "Seekh Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_seekh_kebab
                ),
                FilterChip(
                    id = "shami_kebab",
                    text = "Shami Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_shami_kebab
                ),
                FilterChip(
                    id = "chicken_kebab",
                    text = "Chicken Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_kebab
                ),
                FilterChip(
                    id = "vegetable_kebab",
                    text = "Vegetable Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_kebab
                ),
                FilterChip(
                    id = "fish_kebab",
                    text = "Fish Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fish_kebab
                ),
                // Cooking style/text-only filters
                FilterChip(
                    id = "tandoori",
                    text = "Tandoori",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grilled",
                    text = "Grilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fried",
                    text = "Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "skewered",
                    text = "Skewered",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "minced",
                    text = "Minced",
                    type = FilterType.TEXT_ONLY
                ),
                // Spice level
                FilterChip(
                    id = "mild_spice",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "medium_spice",
                    text = "Medium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                // Diet preferences
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_friendly",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                // Price range
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_400",
                    text = "Under ₹400",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "premium",
                    text = "Premium",
                    type = FilterType.TEXT_ONLY
                ),
                // Sort option
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
         FilterButtonFood(
            filterConfig = kebabDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val kebabFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_chicken_seekh_kebab_diet,
                title = "Chicken Seekh Kebab",
                price = "280",
                restaurantName = "Kebab Junction",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20%",
                discountAmount = "up to ₹60",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mutton_galouti_kebab_diet,
                title = "Mutton Galouti Kebab",
                price = "350",
                restaurantName = "Awadhi House",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "25%",
                discountAmount = "up to ₹90",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_chicken_malai_tikka_diet,
                title = "Chicken Malai Tikka",
                price = "320",
                restaurantName = "Tandoori Nation",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "3.8 km",
                discount = "15%",
                discountAmount = "up to ₹48",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_hariyali_chicken_tikka_diet,
                title = "Hariyali Chicken Tikka",
                price = "300",
                restaurantName = "Green Spice Grill",
                rating = "4.5",
                deliveryTime = "22-28 mins",
                distance = "2.5 km",
                discount = "10%",
                discountAmount = "up to ₹30",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mutton_boti_kebab_diet,
                title = "Mutton Boti Kebab",
                price = "360",
                restaurantName = "Royal Mughlai",
                rating = "4.7",
                deliveryTime = "35-40 mins",
                distance = "4.0 km",
                discount = "20%",
                discountAmount = "up to ₹72",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_chicken_tandoori_tikka_diet,
                title = "Chicken Tandoori Tikka",
                price = "330",
                restaurantName = "Tikka Factory",
                rating = "4.6",
                deliveryTime = "25-32 mins",
                distance = "3.0 km",
                discount = "15%",
                discountAmount = "up to ₹55",
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
            foodItems = kebabFoodItems,
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
//
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

    val kebabRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_kebab_food_1,
            title = "Kebab Junction Chicken Seekh Kebabs",
            price = "280",
            restaurantName = "Kebab Junction",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹56",
            address = "Kebab Street Corner"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_kebab_food_2,
            title = "Awadhi House Mutton Galouti Kebab",
            price = "350",
            restaurantName = "Awadhi House",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹88",
            address = "Awadhi Gali, Lucknowi Street"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_kebab_food_3,
            title = "Tandoori Nation Chicken Malai Tikka",
            price = "320",
            restaurantName = "Tandoori Nation",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹48",
            address = "Tandoori Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_kebab_food_4,
            title = "Green Spice Hariyali Chicken Tikka",
            price = "300",
            restaurantName = "Green Spice Grill",
            rating = "4.5",
            deliveryTime = "22-28 mins",
            distance = "2.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹30",
            address = "Green Avenue Road"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_kebab_food_5,
            title = "Royal Mughlai Mutton Boti Kebab",
            price = "360",
            restaurantName = "Royal Mughlai",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹72",
            address = "Mughlai Street, Old Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_kebab_food_6,
            title = "Tikka Factory Chicken Tandoori Tikka",
            price = "330",
            restaurantName = "Tikka Factory",
            rating = "4.6",
            deliveryTime = "25-32 mins",
            distance = "3.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹55",
            address = "Factory Road"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_kebab_food_7,
            title = "Sultan’s Grills Afghani Kebab",
            price = "390",
            restaurantName = "Sultan’s Grills",
            rating = "4.7",
            deliveryTime = "30-35 mins",
            distance = "3.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹78",
            address = "Afghan Grill Street"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_kebab_food_8,
            title = "Mughlai Darbar Shami Kebabs",
            price = "310",
            restaurantName = "Mughlai Darbar",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "2.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹31",
            address = "Darbar Chowk"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_kebab_food_9,
            title = "BBQ Nation Chicken Reshmi Kebab",
            price = "340",
            restaurantName = "BBQ Nation",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹68",
            address = "BBQ Grill Road"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_kebab_food_10,
            title = "Nawabi Kitchen Chicken Tikka Roll",
            price = "260",
            restaurantName = "Nawabi Kitchen",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹26",
            address = "Nawabi Corner"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_kebab_food_11,
            title = "Spice Hub Tandoori Chicken",
            price = "350",
            restaurantName = "Spice Hub",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹53",
            address = "Spice Market Road"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_kebab_food_12,
            title = "Awadhi Special Galouti Kebab Platter",
            price = "420",
            restaurantName = "Awadhi Special",
            rating = "4.8",
            deliveryTime = "30-35 mins",
            distance = "3.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹84",
            address = "Lucknowi Food Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_kebab_food_13,
            title = "Royal BBQ Mutton Sheekh Platter",
            price = "470",
            restaurantName = "Royal BBQ",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.7 km",
            discount = "25% OFF",
            discountAmount = "up to ₹118",
            address = "BBQ Grill Street"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_kebab_food_14,
            title = "Kebab Mahal Malai Chicken",
            price = "310",
            restaurantName = "Kebab Mahal",
            rating = "4.6",
            deliveryTime = "25-30 mins",
            distance = "3.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹62",
            address = "Kebab Mahal Road"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_kebab_food_15,
            title = "Lebanese House Shawarma Chicken",
            price = "260",
            restaurantName = "Lebanese House",
            rating = "4.4",
            deliveryTime = "20-25 mins",
            distance = "2.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹26",
            address = "Lebanese Street"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_kebab_food_16,
            title = "Kolkata Rolls Chicken Kebab Roll",
            price = "230",
            restaurantName = "Kolkata Rolls",
            rating = "4.3",
            deliveryTime = "18-22 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹23",
            address = "Rolls Gali"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_kebab_food_17,
            title = "Biryani Darbar Kebab & Biryani Combo",
            price = "380",
            restaurantName = "Biryani Darbar",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹76",
            address = "Darbar Road"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_kebab_food_18,
            title = "Zaitoon Grill Arabian Kebab Platter",
            price = "520",
            restaurantName = "Zaitoon Grill",
            rating = "4.8",
            deliveryTime = "40-45 mins",
            distance = "5.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹130",
            address = "Arabian Food Street"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_kebab_food_19,
            title = "Fusion Tadka Peri-Peri Chicken Tikka",
            price = "340",
            restaurantName = "Fusion Tadka",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹34",
            address = "Fusion Lane"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_kebab_food_20,
            title = "Muglai Treat Royal Kebabs Mix",
            price = "450",
            restaurantName = "Mughlai Treat",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹90",
            address = "Royal Mughlai Street"
        )
    )

    Column {
        kebabRestaurantItems.forEach { restaurantItem ->
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
fun HealthySnacksPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val healthySnacksFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // Healthy snack types WITH icons
                FilterChip(
                    id = "nuts_seeds",
                    text = "Nuts & Seeds",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_nuts_seeds
                ),
                FilterChip(
                    id = "fruit_chips",
                    text = "Fruit Chips",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruit_chips
                ),
                FilterChip(
                    id = "protein_bars",
                    text = "Protein Bars",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_bar
                ),
                FilterChip(
                    id = "yogurt",
                    text = "Yogurt",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_yogurt
                ),
                FilterChip(
                    id = "granola",
                    text = "Granola",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_granola
                ),
                FilterChip(
                    id = "rice_cakes",
                    text = "Rice Cakes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_rice_cake
                ),

                // Diet preferences - TEXT ONLY
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "organic",
                    text = "Organic",
                    type = FilterType.TEXT_ONLY
                ),

                // Health benefits - TEXT ONLY
                FilterChip(
                    id = "energy_boosting",
                    text = "Energy Boosting",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gut_healthy",
                    text = "Gut Healthy",
                    type = FilterType.TEXT_ONLY
                ),

                // Preparation types - TEXT ONLY
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_preservatives",
                    text = "No Preservatives",
                    type = FilterType.TEXT_ONLY
                ),

                // Price range - TEXT ONLY
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),

                // Sort option
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = healthySnacksFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val healthySnacksItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_nuts_mix_snack,
                title = "Premium Mixed Nuts",
                price = "280",
                restaurantName = "Nature's Basket",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹56",
                address = "Health Hub, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_protein_bar_snack,
                title = "Chocolate Protein Bar",
                price = "120",
                restaurantName = "Protein Power",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.2 km",
                discount = "15%",
                discountAmount = "up to ₹18",
                address = "Fitness Zone, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_fruit_chips_snack,
                title = "Apple Cinnamon Chips",
                price = "180",
                restaurantName = "Fruitful Delights",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.0 km",
                discount = "10%",
                discountAmount = "up to ₹18",
                address = "Organic Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_greek_yogurt_snack,
                title = "Greek Yogurt Bowl",
                price = "220",
                restaurantName = "Yogurt Culture",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "25%",
                discountAmount = "up to ₹55",
                address = "Wellness Center, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_roasted_chickpeas_snack,
                title = "Spicy Roasted Chickpeas",
                price = "160",
                restaurantName = "Crunchy Munchies",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "20%",
                discountAmount = "up to ₹32",
                address = "Healthy Bites, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_granola_bowl_snack,
                title = "Berry Granola Bowl",
                price = "240",
                restaurantName = "Breakfast Club",
                rating = "4.7",
                deliveryTime = "22-28 mins",
                distance = "2.8 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Morning Fresh, Delhi"
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
            foodItems = healthySnacksItems,
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
    val healthySnackItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.snack_image_healthy_snack_1,
            title = "Green Protein Salad Bowl",
            price = "220",
            restaurantName = "Health Hub",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹33",
            address = "Wellness Street"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.snack_image_healthy_snack_2,
            title = "Quinoa & Avocado Power Bowl",
            price = "280",
            restaurantName = "Organic Kitchen",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹56",
            address = "Organic Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.snack_image_healthy_snack_3,
            title = "Greek Yogurt Berry Parfait",
            price = "180",
            restaurantName = "Fit Fuel",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Fitness Corner"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.snack_image_healthy_snack_4,
            title = "Protein Energy Balls (6 pcs)",
            price = "160",
            restaurantName = "Nutri Bites",
            rating = "4.5",
            deliveryTime = "15-20 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Health Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.snack_image_healthy_snack_5,
            title = "Sweet Potato Fries Bowl",
            price = "190",
            restaurantName = "Clean Eats",
            rating = "4.4",
            deliveryTime = "18-22 mins",
            distance = "2.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Clean Food Street"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.snack_image_healthy_snack_6,
            title = "Hummus & Veggie Platter",
            price = "240",
            restaurantName = "Mediterranean Fresh",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹48",
            address = "Mediterranean Road"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.snack_image_healthy_snack_7,
            title = "Chia Seed Pudding Bowl",
            price = "150",
            restaurantName = "Superfood Station",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹23",
            address = "Superfood Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.snack_image_healthy_snack_8,
            title = "Sprout Salad with Lemon Dressing",
            price = "130",
            restaurantName = "Salad Bar",
            rating = "4.3",
            deliveryTime = "12-16 mins",
            distance = "1.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Salad Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.snack_image_healthy_snack_9,
            title = "Almond Butter & Banana Toast",
            price = "170",
            restaurantName = "Toast House",
            rating = "4.5",
            deliveryTime = "14-19 mins",
            distance = "1.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹26",
            address = "Toast Corner"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.snack_image_healthy_snack_10,
            title = "Protein Bar (Assorted Flavors)",
            price = "120",
            restaurantName = "Energy Boost",
            rating = "4.4",
            deliveryTime = "10-14 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Energy Street"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.snack_image_healthy_snack_11,
            title = "Avocado Toast with Microgreens",
            price = "210",
            restaurantName = "Avocado Heaven",
            rating = "4.7",
            deliveryTime = "16-21 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Avocado Road"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.snack_image_healthy_snack_12,
            title = "Veggie Spring Rolls (Baked)",
            price = "190",
            restaurantName = "Light Bites",
            rating = "4.5",
            deliveryTime = "18-23 mins",
            distance = "2.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹29",
            address = "Light Food Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.snack_image_healthy_snack_13,
            title = "Fruit & Nut Trail Mix Bowl",
            price = "140",
            restaurantName = "Trail Mix Co.",
            rating = "4.6",
            deliveryTime = "11-16 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Trail Mix Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.snack_image_healthy_snack_14,
            title = "Cottage Cheese Salad Bowl",
            price = "230",
            restaurantName = "Protein Palace",
            rating = "4.7",
            deliveryTime = "17-22 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹46",
            address = "Protein Street"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.snack_image_healthy_snack_15,
            title = "Detox Green Smoothie",
            price = "160",
            restaurantName = "Smoothie Bar",
            rating = "4.5",
            deliveryTime = "8-12 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Smoothie Corner"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.snack_image_healthy_snack_16,
            title = "Roasted Chickpea Snack Pack",
            price = "110",
            restaurantName = "Crunchy Bites",
            rating = "4.3",
            deliveryTime = "13-17 mins",
            distance = "1.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Crunchy Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.snack_image_healthy_snack_17,
            title = "Mushroom & Spinach Quesadilla",
            price = "200",
            restaurantName = "Veggie Delight",
            rating = "4.6",
            deliveryTime = "19-24 mins",
            distance = "2.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Veggie Lane"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.snack_image_healthy_snack_18,
            title = "Oatmeal with Fresh Fruits",
            price = "170",
            restaurantName = "Breakfast Bowls",
            rating = "4.4",
            deliveryTime = "14-19 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹26",
            address = "Breakfast Street"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.snack_image_healthy_snack_19,
            title = "Zucchini Noodles with Pesto",
            price = "250",
            restaurantName = "Zoodle Zone",
            rating = "4.7",
            deliveryTime = "21-26 mins",
            distance = "2.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Zoodle Avenue"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.snack_image_healthy_snack_20,
            title = "Edamame with Himalayan Salt",
            price = "135",
            restaurantName = "Soy Special",
            rating = "4.5",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Soybean Road"
        )
    )
     Column {
         healthySnackItems.forEach { restaurantItem ->
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
fun LowCaloriePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val lowCalorieFoodFilters = FilterConfig(
            filters = listOf(

                // 1. Filters dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. Low Calorie Categories (WITH LEFT ICON)
                FilterChip(
                    id = "low_cal_snacks",
                    text = "Low Cal Snacks",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_low_calorie_food
                ),
                FilterChip(
                    id = "light_meals",
                    text = "Light Meals",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_light_meal
                ),
                FilterChip(
                    id = "healthy_bars",
                    text = "Healthy Bars",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_healthy_bar
                ),

                // 3. Diet Type (MIX of with-left-icon + text-only)
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. Health Benefits (TEXT ONLY)
                FilterChip(
                    id = "weight_loss",
                    text = "Weight Loss",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "energy_boosting",
                    text = "Energy Boosting",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. Preparation Methods (TEXT ONLY)
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_preservatives",
                    text = "No Preservatives",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. Price Range (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )

        FilterButtonFood(
            filterConfig = lowCalorieFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val lowCalorieFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_lowcal_mixed_fruits_diet,
                title = "Fresh Mixed Fruit Bowl",
                price = "150",
                restaurantName = "Healthy Harvest",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹22",
                address = "Green Valley, Delhi"
            ),

            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_lowcal_greek_yogurt_diet,
                title = "Low-Fat Greek Yogurt",
                price = "120",
                restaurantName = "Yogurt Lab",
                rating = "4.7",
                deliveryTime = "12-18 mins",
                distance = "1.6 km",
                discount = "10%",
                discountAmount = "up to ₹12",
                address = "Fitness Hub, Delhi"
            ),

            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_lowcal_salad_bowl_diet,
                title = "Cucumber Detox Salad",
                price = "180",
                restaurantName = "Fresh Greens",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "2.3 km",
                discount = "20%",
                discountAmount = "up to ₹36",
                address = "Wellness Street, Delhi"
            ),

            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_lowcal_air_fried_chips_diet,
                title = "Air-Fried Veggie Chips",
                price = "130",
                restaurantName = "Crunch Fit",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "10%",
                discountAmount = "up to ₹13",
                address = "Healthy Market, Delhi"
            ),

            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_lowcal_chia_pudding_diet,
                title = "Chia Seed Pudding",
                price = "160",
                restaurantName = "Superfood Café",
                rating = "4.8",
                deliveryTime = "22-28 mins",
                distance = "2.5 km",
                discount = "12%",
                discountAmount = "up to ₹19",
                address = "Organic Square, Delhi"
            ),

            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_lowcal_oats_bowl_diet,
                title = "Honey Oats Bowl",
                price = "140",
                restaurantName = "Morning Fuel",
                rating = "4.5",
                deliveryTime = "16-22 mins",
                distance = "1.8 km",
                discount = "15%",
                discountAmount = "up to ₹21",
                address = "Breakfast Lane, Delhi"
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
            foodItems = lowCalorieFoodItems,
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
    val lowCalorieFoodItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.lowcal_image_food_1,
            title = "Fresh Fruit Detox Bowl",
            price = "150",
            restaurantName = "Nature Fresh",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Green Valley"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.lowcal_image_food_2,
            title = "Low-Calorie Greek Yogurt Parfait",
            price = "180",
            restaurantName = "Yogurt Culture",
            rating = "4.7",
            deliveryTime = "14-20 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Healthy Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.lowcal_image_food_3,
            title = "Cucumber & Mint Salad",
            price = "130",
            restaurantName = "Fresh Greens",
            rating = "4.5",
            deliveryTime = "10-16 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Garden Street"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.lowcal_image_food_4,
            title = "Veggie Air-Fried Chips",
            price = "140",
            restaurantName = "Crunch Fit",
            rating = "4.6",
            deliveryTime = "16-22 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Fitness Road"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.lowcal_image_food_5,
            title = "Chia Seed Pudding (Low Sugar)",
            price = "160",
            restaurantName = "Superfood Station",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Wellness Street"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.lowcal_image_food_6,
            title = "Honey Oats Breakfast Bowl",
            price = "140",
            restaurantName = "Morning Fresh",
            rating = "4.4",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Breakfast Avenue"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.lowcal_image_food_7,
            title = "Sprouts & Veggie Mix Bowl",
            price = "125",
            restaurantName = "Nutri Mix",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Nutri Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.lowcal_image_food_8,
            title = "Baked Multi-Grain Sticks",
            price = "150",
            restaurantName = "BakeLite",
            rating = "4.5",
            deliveryTime = "16-22 mins",
            distance = "2.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹23",
            address = "Baker Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.lowcal_image_food_9,
            title = "Detox Green Smoothie",
            price = "170",
            restaurantName = "Smoothie Bar",
            rating = "4.7",
            deliveryTime = "8-14 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Smoothie Corner"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.lowcal_image_food_10,
            title = "Roasted Lemon Chickpeas",
            price = "120",
            restaurantName = "Crunchy Bites",
            rating = "4.4",
            deliveryTime = "12-16 mins",
            distance = "1.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Crunch Street"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.lowcal_image_food_11,
            title = "Fresh Veggie Spring Rolls (Baked)",
            price = "160",
            restaurantName = "Light Bites",
            rating = "4.5",
            deliveryTime = "18-23 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Light Street"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.lowcal_image_food_12,
            title = "Avocado Toast (Low Cal)",
            price = "190",
            restaurantName = "Avocado Heaven",
            rating = "4.7",
            deliveryTime = "14-20 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹38",
            address = "Avocado Road"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.lowcal_image_food_13,
            title = "Berry Infused Detox Water",
            price = "110",
            restaurantName = "Hydrate+",
            rating = "4.3",
            deliveryTime = "8-12 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Hydration Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.lowcal_image_food_14,
            title = "Zucchini Noodles with Herbs",
            price = "200",
            restaurantName = "Zoodle Zone",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Zoodle Avenue"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.lowcal_image_food_15,
            title = "Protein-Rich Lentil Soup",
            price = "150",
            restaurantName = "Soup Co.",
            rating = "4.5",
            deliveryTime = "15-22 mins",
            distance = "1.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Soup Street"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.lowcal_image_food_16,
            title = "Low Calorie Veggie Wrap",
            price = "180",
            restaurantName = "Wrap Station",
            rating = "4.4",
            deliveryTime = "14-20 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Wrap Lane"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.lowcal_image_food_17,
            title = "Oatmeal with Berries",
            price = "160",
            restaurantName = "Healthy Bowls",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "1.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Bowl Street"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.lowcal_image_food_18,
            title = "Quinoa Veggie Salad Bowl",
            price = "210",
            restaurantName = "Organic Kitchen",
            rating = "4.7",
            deliveryTime = "20-26 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Organic Lane"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.lowcal_image_food_19,
            title = "Steamed Broccoli with Herbs",
            price = "130",
            restaurantName = "Green Plates",
            rating = "4.4",
            deliveryTime = "10-15 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Healthy Corner"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.lowcal_image_food_20,
            title = "Warm Lemon Detox Soup",
            price = "140",
            restaurantName = "Detox Kitchen",
            rating = "4.5",
            deliveryTime = "15-22 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Detox Avenue"
        )
    )

    Column {
        lowCalorieFoodItems.forEach { restaurantItem ->
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
fun VeganPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val veganFoodFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown (Trigger)
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. SPECIALIZED FILTERS (WITH ICONS) - Important dietary categories
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_diet
                ),
                FilterChip(
                    id = "low_cal_snacks",
                    text = "Low Cal Snacks",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_low_calorie_food_vegan
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free_vegan
                ),

                // 3. DIETARY RESTRICTIONS (TEXT ONLY)
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_preservatives",
                    text = "No Preservatives",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. MEAL CATEGORIES (TEXT ONLY)
                FilterChip(
                    id = "light_meals",
                    text = "Light Meals",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy_bars",
                    text = "Healthy Bars",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. HEALTH BENEFITS (TEXT ONLY)
                FilterChip(
                    id = "weight_loss",
                    text = "Weight Loss",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "energy_boosting",
                    text = "Energy Boosting",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. PREPARATION METHODS (TEXT ONLY)
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = veganFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val veganFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_vegan_buddha_bowl,
                title = "Rainbow Buddha Bowl",
                price = "220",
                restaurantName = "Plant Power Kitchen",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Eco Street, Delhi",
            ),

            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_vegan_avocado_toast,
                title = "Smoked Avocado Toast",
                price = "180",
                restaurantName = "Green Leaf Café",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹27",
                address = "Vegan Hub, Delhi",
            ),

            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_vegan_quinoa_salad,
                title = "Lemon Herb Quinoa Salad",
                price = "190",
                restaurantName = "Earth Kitchen",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "10%",
                discountAmount = "up to ₹19",
                address = "Organic Square, Delhi",
            ),

            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_vegan_chickpea_wrap,
                title = "Spiced Chickpea Wrap",
                price = "160",
                restaurantName = "Vegan Delight",
                rating = "4.6",
                deliveryTime = "12-16 mins",
                distance = "0.9 km",
                discount = "25%",
                discountAmount = "up to ₹40",
                address = "Health Lane, Delhi",
            ),

            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_vegan_berry_smoothie,
                title = "Mixed Berry Protein Smoothie",
                price = "150",
                restaurantName = "Smoothie Bar",
                rating = "4.7",
                deliveryTime = "10-15 mins",
                distance = "1.0 km",
                discount = "15%",
                discountAmount = "up to ₹22",
                address = "Fitness Corner, Delhi",
            ),

            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_vegan_mushroom_tacos,
                title = "Portobello Mushroom Tacos",
                price = "210",
                restaurantName = "Mexican Greens",
                rating = "4.9",
                deliveryTime = "22-28 mins",
                distance = "2.2 km",
                discount = "20%",
                discountAmount = "up to ₹42",
                address = "Global Cuisine Street, Delhi",
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
            foodItems = veganFoodItems,
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
    val veganFoodItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.vegan_image_food_1,
            title = "Rainbow Buddha Bowl",
            price = "220",
            restaurantName = "Plant Power Kitchen",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹44",
            address = "Vegan Street"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.vegan_image_food_2,
            title = "Smoked Avocado Toast",
            price = "180",
            restaurantName = "Green Leaf Café",
            rating = "4.7",
            deliveryTime = "14-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Organic Corner"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.vegan_image_food_3,
            title = "Lemon Herb Quinoa Salad",
            price = "190",
            restaurantName = "Earth Kitchen",
            rating = "4.8",
            deliveryTime = "16-22 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Eco Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.vegan_image_food_4,
            title = "Spiced Chickpea Wrap",
            price = "160",
            restaurantName = "Vegan Delight",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "25% OFF",
            discountAmount = "up to ₹40",
            address = "Plant Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.vegan_image_food_5,
            title = "Mixed Berry Protein Smoothie",
            price = "150",
            restaurantName = "Smoothie Bar",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Berry Lane"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.vegan_image_food_6,
            title = "Portobello Mushroom Tacos",
            price = "210",
            restaurantName = "Mexican Greens",
            rating = "4.9",
            deliveryTime = "20-26 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Global Street"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.vegan_image_food_7,
            title = "Avocado Chocolate Pudding",
            price = "140",
            restaurantName = "Sweet Vegan",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹28",
            address = "Dessert Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.vegan_image_food_8,
            title = "Fresh Spring Rolls",
            price = "170",
            restaurantName = "Asian Greens",
            rating = "4.7",
            deliveryTime = "18-24 mins",
            distance = "1.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Asian Avenue"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.vegan_image_food_9,
            title = "Vegan Black Bean Burger",
            price = "230",
            restaurantName = "Burger Plant",
            rating = "4.6",
            deliveryTime = "22-28 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹34",
            address = "Burger Street"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.vegan_image_food_10,
            title = "Coconut Curry Bowl",
            price = "200",
            restaurantName = "Curry Leaves",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹20",
            address = "Spice Lane"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.vegan_image_food_11,
            title = "Hummus & Veggie Platter",
            price = "185",
            restaurantName = "Mediterranean Bites",
            rating = "4.7",
            deliveryTime = "16-22 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Mediterranean Road"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.vegan_image_food_12,
            title = "Tofu Stir Fry Bowl",
            price = "195",
            restaurantName = "Tofu Express",
            rating = "4.5",
            deliveryTime = "18-23 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Stir Fry Lane"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.vegan_image_food_13,
            title = "Vegan Lentil Soup",
            price = "160",
            restaurantName = "Soup & Co",
            rating = "4.6",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Soup Street"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.vegan_image_food_14,
            title = "Sweet Potato Buddha Bowl",
            price = "210",
            restaurantName = "Bowl Co.",
            rating = "4.8",
            deliveryTime = "20-26 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Bowl Avenue"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.vegan_image_food_15,
            title = "Vegan Sushi Rolls",
            price = "240",
            restaurantName = "Green Sushi",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "2.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹36",
            address = "Sushi Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.vegan_image_food_16,
            title = "Chickpea Salad Sandwich",
            price = "175",
            restaurantName = "Sandwich Plant",
            rating = "4.6",
            deliveryTime = "14-19 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Sandwich Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.vegan_image_food_17,
            title = "Vegan Pasta Primavera",
            price = "225",
            restaurantName = "Pasta Greens",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "2.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹34",
            address = "Pasta Avenue"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.vegan_image_food_18,
            title = "Vegan Chocolate Brownie",
            price = "130",
            restaurantName = "Guilt-Free Sweets",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹26",
            address = "Dessert Corner"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.vegan_image_food_19,
            title = "Grilled Veggie Skewers",
            price = "190",
            restaurantName = "Grill Garden",
            rating = "4.6",
            deliveryTime = "18-24 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Grill Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.vegan_image_food_20,
            title = "Vegan Protein Power Bowl",
            price = "235",
            restaurantName = "Protein Plant",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹35",
            address = "Protein Lane"
        )
    )
    Column {
        veganFoodItems.forEach { restaurantItem ->
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
fun ProteinRichPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val proteinRichFoodFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY PROTEIN FILTERS (WITH ICONS)
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_rich
                ),
                FilterChip(
                    id = "vegan_protein",
                    text = "Vegan Protein",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_protein
                ),
                FilterChip(
                    id = "low_carb",
                    text = "Low Carb",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_low_carb
                ),

                // 3. PROTEIN SOURCES (TEXT ONLY)
                FilterChip(
                    id = "whey_protein",
                    text = "Whey Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "plant_protein",
                    text = "Plant Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_based",
                    text = "Egg Based",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. MEAL TYPES (TEXT ONLY)
                FilterChip(
                    id = "post_workout",
                    text = "Post-Workout",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "muscle_building",
                    text = "Muscle Building",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "meal_replacement",
                    text = "Meal Replacement",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. PROTEIN CONTENT (TEXT ONLY)
                FilterChip(
                    id = "20g_plus",
                    text = "20g+ Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "30g_plus",
                    text = "30g+ Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "complete_protein",
                    text = "Complete Protein",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DIETARY (TEXT ONLY)
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_400",
                    text = "Under ₹400",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
         FilterButtonFood(
            filterConfig = proteinRichFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val proteinRichFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_protein_chicken_grill,
                title = "Grilled Chicken Breast Bowl",
                price = "280",
                restaurantName = "Protein Power",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.4 km",
                discount = "15%",
                discountAmount = "up to ₹42",
                address = "Fitness Street, Delhi"
            ),

            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_protein_egg_whites,
                title = "Egg White Omelette Platter",
                price = "190",
                restaurantName = "Eggcelent Protein",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.1 km",
                discount = "10%",
                discountAmount = "up to ₹19",
                address = "Protein Lane, Delhi"
            ),

            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_protein_whey_shake,
                title = "Double Chocolate Whey Shake",
                price = "220",
                restaurantName = "Muscle Fuel",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.8 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Gym Road, Delhi"
            ),

            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_protein_tofu_bowl,
                title = "Tofu & Quinoa Power Bowl",
                price = "240",
                restaurantName = "Plant Protein Co.",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.9 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Vegan Protein Hub, Delhi"
            ),

            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_protein_fish_grill,
                title = "Grilled Salmon with Veggies",
                price = "320",
                restaurantName = "Omega Kitchen",
                rating = "4.8",
                deliveryTime = "22-28 mins",
                distance = "2.3 km",
                discount = "10%",
                discountAmount = "up to ₹32",
                address = "Seafood Avenue, Delhi"
            ),

            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_protein_paneer_tikka,
                title = "Protein Paneer Tikka Bowl",
                price = "260",
                restaurantName = "Desi Protein",
                rating = "4.7",
                deliveryTime = "16-21 mins",
                distance = "1.6 km",
                discount = "12%",
                discountAmount = "up to ₹31",
                address = "Indian Protein Corner, Delhi"
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
            foodItems = proteinRichFoodItems,
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

//    Spacer(modifier = Modifier.height(15.dp))
//    Text(
//        text = "Restaurants delivering to you",
//        style = MaterialTheme.typography.bodySmall.copy(
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color =  MaterialTheme.customColors.black
//        ),
////            textAlign = TextAlign.Center,
//        maxLines = 1,
//        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
//    )
//    Spacer(modifier = Modifier.height(10.dp))
//    Text(
//        text = "Featured restaurants",
//        style = MaterialTheme.typography.bodySmall.copy(
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.customColors.black
//        ),
////            textAlign = TextAlign.Center,
//        maxLines = 1,
//        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
//    )
//    Spacer(modifier = Modifier.height(5.dp))
//
//    // Sample data based on the provided images
//    val veganFoodItems = listOf(
//        RestaurantItemFull(
//            id = 1,
//            imageRes = R.drawable.vegan_image_food_1,
//            title = "Rainbow Buddha Bowl",
//            price = "220",
//            restaurantName = "Plant Power Kitchen",
//            rating = "4.9",
//            deliveryTime = "18-24 mins",
//            distance = "1.5 km",
//            discount = "20% OFF",
//            discountAmount = "up to ₹44",
//            address = "Vegan Street"
//        ),
//        RestaurantItemFull(
//            id = 2,
//            imageRes = R.drawable.vegan_image_food_2,
//            title = "Smoked Avocado Toast",
//            price = "180",
//            restaurantName = "Green Leaf Café",
//            rating = "4.7",
//            deliveryTime = "14-20 mins",
//            distance = "1.2 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹27",
//            address = "Organic Corner"
//        ),
//        RestaurantItemFull(
//            id = 3,
//            imageRes = R.drawable.vegan_image_food_3,
//            title = "Lemon Herb Quinoa Salad",
//            price = "190",
//            restaurantName = "Earth Kitchen",
//            rating = "4.8",
//            deliveryTime = "16-22 mins",
//            distance = "1.8 km",
//            discount = "10% OFF",
//            discountAmount = "up to ₹19",
//            address = "Eco Lane"
//        ),
//        RestaurantItemFull(
//            id = 4,
//            imageRes = R.drawable.vegan_image_food_4,
//            title = "Spiced Chickpea Wrap",
//            price = "160",
//            restaurantName = "Vegan Delight",
//            rating = "4.6",
//            deliveryTime = "12-18 mins",
//            distance = "0.9 km",
//            discount = "25% OFF",
//            discountAmount = "up to ₹40",
//            address = "Plant Avenue"
//        ),
//        RestaurantItemFull(
//            id = 5,
//            imageRes = R.drawable.vegan_image_food_5,
//            title = "Mixed Berry Protein Smoothie",
//            price = "150",
//            restaurantName = "Smoothie Bar",
//            rating = "4.7",
//            deliveryTime = "10-15 mins",
//            distance = "1.0 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹22",
//            address = "Berry Lane"
//        ),
//        RestaurantItemFull(
//            id = 6,
//            imageRes = R.drawable.vegan_image_food_6,
//            title = "Portobello Mushroom Tacos",
//            price = "210",
//            restaurantName = "Mexican Greens",
//            rating = "4.9",
//            deliveryTime = "20-26 mins",
//            distance = "2.2 km",
//            discount = "20% OFF",
//            discountAmount = "up to ₹42",
//            address = "Global Street"
//        ),
//        RestaurantItemFull(
//            id = 7,
//            imageRes = R.drawable.vegan_image_food_7,
//            title = "Avocado Chocolate Pudding",
//            price = "140",
//            restaurantName = "Sweet Vegan",
//            rating = "4.8",
//            deliveryTime = "15-20 mins",
//            distance = "1.3 km",
//            discount = "20% OFF",
//            discountAmount = "up to ₹28",
//            address = "Dessert Lane"
//        ),
//        RestaurantItemFull(
//            id = 8,
//            imageRes = R.drawable.vegan_image_food_8,
//            title = "Fresh Spring Rolls",
//            price = "170",
//            restaurantName = "Asian Greens",
//            rating = "4.7",
//            deliveryTime = "18-24 mins",
//            distance = "1.7 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹25",
//            address = "Asian Avenue"
//        ),
//        RestaurantItemFull(
//            id = 9,
//            imageRes = R.drawable.vegan_image_food_9,
//            title = "Vegan Black Bean Burger",
//            price = "230",
//            restaurantName = "Burger Plant",
//            rating = "4.6",
//            deliveryTime = "22-28 mins",
//            distance = "2.5 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹34",
//            address = "Burger Street"
//        ),
//        RestaurantItemFull(
//            id = 10,
//            imageRes = R.drawable.vegan_image_food_10,
//            title = "Coconut Curry Bowl",
//            price = "200",
//            restaurantName = "Curry Leaves",
//            rating = "4.8",
//            deliveryTime = "20-25 mins",
//            distance = "2.0 km",
//            discount = "10% OFF",
//            discountAmount = "up to ₹20",
//            address = "Spice Lane"
//        ),
//        RestaurantItemFull(
//            id = 11,
//            imageRes = R.drawable.vegan_image_food_11,
//            title = "Hummus & Veggie Platter",
//            price = "185",
//            restaurantName = "Mediterranean Bites",
//            rating = "4.7",
//            deliveryTime = "16-22 mins",
//            distance = "1.6 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹28",
//            address = "Mediterranean Road"
//        ),
//        RestaurantItemFull(
//            id = 12,
//            imageRes = R.drawable.vegan_image_food_12,
//            title = "Tofu Stir Fry Bowl",
//            price = "195",
//            restaurantName = "Tofu Express",
//            rating = "4.5",
//            deliveryTime = "18-23 mins",
//            distance = "1.9 km",
//            discount = "10% OFF",
//            discountAmount = "up to ₹19",
//            address = "Stir Fry Lane"
//        ),
//        RestaurantItemFull(
//            id = 13,
//            imageRes = R.drawable.vegan_image_food_13,
//            title = "Vegan Lentil Soup",
//            price = "160",
//            restaurantName = "Soup & Co",
//            rating = "4.6",
//            deliveryTime = "15-20 mins",
//            distance = "1.4 km",
//            discount = "10% OFF",
//            discountAmount = "up to ₹16",
//            address = "Soup Street"
//        ),
//        RestaurantItemFull(
//            id = 14,
//            imageRes = R.drawable.vegan_image_food_14,
//            title = "Sweet Potato Buddha Bowl",
//            price = "210",
//            restaurantName = "Bowl Co.",
//            rating = "4.8",
//            deliveryTime = "20-26 mins",
//            distance = "2.3 km",
//            discount = "20% OFF",
//            discountAmount = "up to ₹42",
//            address = "Bowl Avenue"
//        ),
//        RestaurantItemFull(
//            id = 15,
//            imageRes = R.drawable.vegan_image_food_15,
//            title = "Vegan Sushi Rolls",
//            price = "240",
//            restaurantName = "Green Sushi",
//            rating = "4.9",
//            deliveryTime = "25-30 mins",
//            distance = "2.8 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹36",
//            address = "Sushi Lane"
//        ),
//        RestaurantItemFull(
//            id = 16,
//            imageRes = R.drawable.vegan_image_food_16,
//            title = "Chickpea Salad Sandwich",
//            price = "175",
//            restaurantName = "Sandwich Plant",
//            rating = "4.6",
//            deliveryTime = "14-19 mins",
//            distance = "1.3 km",
//            discount = "10% OFF",
//            discountAmount = "up to ₹17",
//            address = "Sandwich Street"
//        ),
//        RestaurantItemFull(
//            id = 17,
//            imageRes = R.drawable.vegan_image_food_17,
//            title = "Vegan Pasta Primavera",
//            price = "225",
//            restaurantName = "Pasta Greens",
//            rating = "4.7",
//            deliveryTime = "22-28 mins",
//            distance = "2.4 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹34",
//            address = "Pasta Avenue"
//        ),
//        RestaurantItemFull(
//            id = 18,
//            imageRes = R.drawable.vegan_image_food_18,
//            title = "Vegan Chocolate Brownie",
//            price = "130",
//            restaurantName = "Guilt-Free Sweets",
//            rating = "4.8",
//            deliveryTime = "12-18 mins",
//            distance = "1.1 km",
//            discount = "20% OFF",
//            discountAmount = "up to ₹26",
//            address = "Dessert Corner"
//        ),
//        RestaurantItemFull(
//            id = 19,
//            imageRes = R.drawable.vegan_image_food_19,
//            title = "Grilled Veggie Skewers",
//            price = "190",
//            restaurantName = "Grill Garden",
//            rating = "4.6",
//            deliveryTime = "18-24 mins",
//            distance = "2.0 km",
//            discount = "10% OFF",
//            discountAmount = "up to ₹19",
//            address = "Grill Street"
//        ),
//        RestaurantItemFull(
//            id = 20,
//            imageRes = R.drawable.vegan_image_food_20,
//            title = "Vegan Protein Power Bowl",
//            price = "235",
//            restaurantName = "Protein Plant",
//            rating = "4.9",
//            deliveryTime = "20-25 mins",
//            distance = "2.1 km",
//            discount = "15% OFF",
//            discountAmount = "up to ₹35",
//            address = "Protein Lane"
//        )
//    )
//    Column {
//        veganFoodItems.forEach { restaurantItem ->
//            RestaurantItemListFull(
//                restaurantItem = restaurantItem,
//                onWishlistClick = { },
//                onThreeDotClick = { },
//                onItemClick = { }
//            )
//        }
//    }
}
@Composable
fun SeeAllPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "See All Foods",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

// Usage in MainScreen - Improved with proper indexing
@Composable
fun MainScreenWithDietTabs(navController: NavHostController) {
    var currentPage by remember { mutableIntStateOf(0) }
    Column(modifier = Modifier.fillMaxWidth()) {
        CategoryDietTabsFood(
            onCategorySelected = { dietCategoryPage ->
                // The index is already handled by the selectedTabIndex in CategoryDietTabsFood
                // We just need to update currentPage with the correct index
                currentPage = when (dietCategoryPage) {
                    DietCategoryPage.Chicken -> 0
                    DietCategoryPage.Salad -> 1
                    DietCategoryPage.Mutton -> 2
                    DietCategoryPage.Kebabs -> 3
                    DietCategoryPage.HealthySnacks -> 4
                    DietCategoryPage.LowCalorie -> 5
                    DietCategoryPage.Vegan -> 6
                    DietCategoryPage.ProteinRich -> 7
                    DietCategoryPage.SeeAll -> 8
                }
            }
        )
    }
}

// Alternative: More robust approach using indexOf
@Composable
fun MainScreenWithDietTabsImproved(navController: NavHostController) {
    var currentPage by remember { mutableIntStateOf(0) }

    val dietCategoryPages = listOf(
        DietCategoryPage.Chicken,
        DietCategoryPage.Salad,
        DietCategoryPage.Mutton,
        DietCategoryPage.Kebabs,
        DietCategoryPage.HealthySnacks,
        DietCategoryPage.LowCalorie,
        DietCategoryPage.Vegan,
        DietCategoryPage.ProteinRich,
        DietCategoryPage.SeeAll
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        CategoryDietTabsFood(
            onCategorySelected = { dietCategoryPage ->
                // Find the index of the selected category in the list
                currentPage = dietCategoryPages.indexOfFirst { it == dietCategoryPage }
            }
        )
    }
}