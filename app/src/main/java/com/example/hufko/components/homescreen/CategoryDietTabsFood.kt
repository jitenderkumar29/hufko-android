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
    onCategorySelected: (DietCategoryPage) -> Unit = {}
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
            .background(MaterialTheme.customColors.skyBlue)
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
                            .background(
                                color = MaterialTheme.customColors.white,
                                shape = RoundedCornerShape(10.dp)  // ← Rounded corners applied here
                            )
                            .padding(horizontal = 5.dp)
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
            .padding(16.dp)
    ) {
        Text(
            text = "Mutton Diet Options",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun KebabsDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Healthy Kebabs",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun HealthySnacksPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Healthy Snacks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun LowCaloriePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Low Calorie Options",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun VeganPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Vegan Options",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
}

@Composable
fun ProteinRichPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Protein Rich Foods",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        )
    }
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