package com.example.hufko.components.GroceryHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.components.homescreen.AddressSelection
import com.example.hufko.components.homescreen.BannerFood
import com.example.hufko.components.homescreen.BannerPadding
import com.example.hufko.components.homescreen.CashbackButton
import com.example.hufko.components.homescreen.CategoryItem
import com.example.hufko.components.homescreen.CategoryItemBgImg
import com.example.hufko.components.homescreen.CategoryListBgImg
import com.example.hufko.components.homescreen.CategoryListSimple
import com.example.hufko.components.homescreen.DotPosition
import com.example.hufko.components.homescreen.DynamicSpacing
import com.example.hufko.components.homescreen.Location
import com.example.hufko.components.homescreen.LocationSelectionButton
import com.example.hufko.components.homescreen.OverlayPosition
import com.example.hufko.components.homescreen.VegNonVegButton
import com.example.hufko.components.searchbar.SearchBar
import com.example.hufko.ui.theme.customColors

@Composable
fun GroceryHome(
    navController: NavHostController?,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var showLocationDialog by remember { mutableStateOf(false) }
    var selectedLocation by remember {
        mutableStateOf(
            Location(
                house = "F 109/B",
                street = "Block-F",
                apartment = "5th floor, Gali no 1",
                city = "Noida",
                state = "UP",
                postal = "10001",
                country = "India"
            )
        )
    }

    // Manage selected tab index
    var selectedTabIndex by remember { mutableIntStateOf(0) }
// Track selected category
    var selectedCategory by remember { mutableStateOf<GroceryCategoryPage?>(null) }

    // Listen for updates from CategoryTabsFList via SavedStateHandle
    LaunchedEffect(navController) {
        navController?.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<Int?>("currentSelectedIndex", null)
            ?.collect { newIndex ->
                newIndex?.let { index ->
                    selectedTabIndex = index
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<Int>("currentSelectedIndex")
                }
            }
    }

    val lazyListState = rememberLazyListState()
    var isLocationVisible by remember { mutableStateOf(true) }

    // Track scroll position to hide/show location section
    LaunchedEffect(lazyListState.firstVisibleItemScrollOffset) {
        val scrollThreshold = 50
        isLocationVisible = lazyListState.firstVisibleItemScrollOffset < scrollThreshold
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            // Location Section (hidden when scrolled)
            item {
                if (isLocationVisible) {
                    Surface(
                        color = MaterialTheme.customColors.header,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 0.dp)
                        ) {
                            // Location button - takes available space
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        top = 0.dp,
                                        bottom = 4.dp,
                                        end = 0.dp
                                    )
                                    .shadow(
                                        elevation = 0.dp,
                                        shape = RoundedCornerShape(8.dp),
                                        clip = true
                                    )
                                    .background(
                                        Color(0x56A7A7A7)
                                    ),
                            ) {
                                LocationSelectionButton(
                                    selectedLocation = selectedLocation,
                                    onLocationClick = { showLocationDialog = true }
                                )
                            }

                            // CashbackButton with dynamic width
//                            CashbackButton(
//                                amount = "1500",
//                                modifier = Modifier
//                                    .padding(
//                                        top = 0.dp,
//                                        bottom = 0.dp
//                                    )
//                                    .wrapContentSize()
//                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(0.dp))
                }
            }

            // Sticky Search Bar (always visible)
            stickyHeader {
                Surface(
                    color = MaterialTheme.customColors.lightAccent,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.customColors.header)
                            .padding(horizontal = 12.dp, vertical = 0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.85f)
                                .padding(
                                    top = 4.dp,
                                    bottom = 8.dp,
                                    start = 0.dp,
                                    end = 0.dp
                                )
                        ) {
                            SearchBar(
                                query = searchQuery,
                                onQueryChange = { searchQuery = it },
                                onSearch = { query -> println("Search performed: $query") }
                            )
                        }
//                        Box(
//                            modifier = Modifier
//                                .weight(0.15f)
//                                .padding(
//                                    top = 4.dp,
//                                    bottom = 8.dp,
//                                    start = 4.dp,
//                                    end = 0.dp
//                                )
//                        ) {
//                            VegNonVegButton()
//                        }
                    }
                }
            }


            item {
                // Use
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
//                        .padding(12.dp)
                ) {
//                    Spacer(modifier = Modifier.height(20.dp))
                    // gudi Sale
                    val gudiCategoriesSimple = listOf(
                        CategoryItem(0, "", R.drawable.ic_gudi_sale_1, "View products"),
                        CategoryItem(1, "", R.drawable.ic_gudi_sale_2, "View products"),
                        CategoryItem(2, "", R.drawable.ic_gudi_sale_3, "View products"),
                        CategoryItem(3, "", R.drawable.ic_gudi_sale_4, "View products"),
                        CategoryItem(4, "", R.drawable.ic_gudi_sale_5, "View products"),
                    )

//        Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_gudi),
                        contentDescription = "Banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                200.dp
                            ),
                        contentScale = ContentScale.FillBounds
                    )
                    CategoryListSimple(
                        items = gudiCategoriesSimple,
                        onItemClick = { item -> println("Selected: ${item.name}") },
                        itemWidth = 110.dp,
                        itemHeight = 140.dp,
                        horizontalSpacing = 12.dp,
//                        verticalPadding = 8.dp,
                        horizontalPadding = 12.dp,
                        backgroundColor = Color(0xFFE2FCE3)
                    )

                    val items = listOf(
                        CategoryItemBgImg(
                            id = 1,
                            name = "",
                            imageRes = R.drawable.ic_fest_1
                        ),
                        CategoryItemBgImg(
                            id = 2,
                            name = "",
                            imageRes = R.drawable.ic_fest_2
                        ),
                        CategoryItemBgImg(
                            id = 3,
                            name = "",
                            imageRes = R.drawable.ic_fest_3
                        ),
                        CategoryItemBgImg(
                            id = 4,
                            name = "",
                            imageRes = R.drawable.ic_fest_4
                        ),
                        CategoryItemBgImg(
                            id = 5,
                            name = "",
                            imageRes = R.drawable.ic_fest_5
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CategoryListBgImg(
                        backgroundImageRes = R.drawable.ic_fest_bg,
                        items = items,
                        onItemClick = { },
                        backgroundImageHeight = 300.dp,
                        listItemWidth = 120.dp,
                        listItemHeight = 230.dp,
                        overlayItemSize = 90.dp,
                        overlayTextSize = 12.sp,
                        backgroundOverlay = Color.Black.copy(alpha = 0.3f),
                        overlayBottomPosition = OverlayPosition.pixels(250.dp),
                        title = null,
                        showHorizontalList = false,
                        overlayItemsSpacing = DynamicSpacing.Fixed(15.dp), // Fixed 28dp between overlay items
                        listItemsSpacing = DynamicSpacing.Fixed(12.dp) // Fixed 12dp between list items
                    )

                    // Banner Section

                    BannerFood(
                        images = listOf(
                            painterResource(id = R.drawable.all_grocery_banner1),
                            painterResource(id = R.drawable.all_grocery_banner2),
                            painterResource(id = R.drawable.all_grocery_banner3),
                            painterResource(id = R.drawable.all_grocery_banner4),
                            painterResource(id = R.drawable.all_grocery_banner5),
                        ),
                        onImageClick = { page ->
                            when (page) {
                                0 -> onBanner1Click()
                                1 -> onBanner2Click()
                                2 -> onBanner3Click()
                            }
                        },
                        autoScrollDelay = 2000,
                        height = 225.dp,
                        roundedCornerShape = 0.dp,
                        contentScale = ContentScale.FillBounds,
                        dotSize = 8.dp,
                        dotPadding = 4.dp,
                        dotPosition = DotPosition.BELOW_IMAGE,
                        overlayGradient = true, // Adds gradient for better visibility
                        selectedDotColor = Color.White,
                        padding = BannerPadding.all(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Feature this week
                    val featureThisWeekCategoriesGrocery = listOf(
                        CategoryItem(0, "", R.drawable.ic_grocery_newly_launched, "View products"),
                        CategoryItem(1, "", R.drawable.ic_grocery_oral_health, "View products"),
                        CategoryItem(2, "", R.drawable.ic_grocery_ugadi, "View products"),
                        CategoryItem(3, "", R.drawable.ic_grocery_exam, "View products"),
                        CategoryItem(4, "", R.drawable.ic_grocery_decathlon, "View products"),
                        CategoryItem(5, "", R.drawable.ic_grocery_date_delight, "View products"),
                        CategoryItem(6, "", R.drawable.ic_grocery_glow, "View products"),
                        CategoryItem(7, "", R.drawable.ic_grocery_metro, "View products"),
                        CategoryItem(8, "", R.drawable.ic_grocery_lifestyle, "View products"),
                        CategoryItem(9, "", R.drawable.ic_grocery_summer, "View products"),
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Feature This Week",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.customColors.black
                        ),
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    CategoryListSimple(
                        items = featureThisWeekCategoriesGrocery,
                        onItemClick = { item -> println("Selected: ${item.name}") },
                        itemWidth = 100.dp,
                        itemHeight = 120.dp,
                        horizontalSpacing = 12.dp,
//                        verticalPadding = 8.dp,
                        horizontalPadding = 12.dp,
                        backgroundColor = Color(0xFFFFFFFF)
                    )

                    val defaultGroceryItems = listOf(
                        GroceryItemCategory(
                            id = 1,
                            title = "Fresh vegetables",
                            iconRes = R.drawable.ic_vegetables
                        ),
                        GroceryItemCategory(
                            id = 2,
                            title = "Fresh fruits",
                            iconRes = R.drawable.ic_fruits
                        ),
                        GroceryItemCategory(
                            id = 3,
                            title = "Atta, rice & grains",
                            iconRes = R.drawable.ic_grains
                        ),
                        GroceryItemCategory(
                            id = 4,
                            title = "Dal & pulses",
                            iconRes = R.drawable.ic_dal
                        ),
                        GroceryItemCategory(
                            id = 5,
                            title = "Oil & ghee",
                            iconRes = R.drawable.ic_oil
                        ),
                        GroceryItemCategory(
                            id = 6,
                            title = "Masala, sugar & spices",
                            iconRes = R.drawable.ic_spices_grocery
                        ),
                        GroceryItemCategory(
                            id = 7,
                            title = "Milk & dairy",
                            iconRes = R.drawable.ic_dairy
                        ),
                        GroceryItemCategory(
                            id = 8,
                            title = "Breads & bakery",
                            iconRes = R.drawable.ic_bakery
                        ),
                        GroceryItemCategory(
                            id = 9,
                            title = "Cereals & breakfast",
                            iconRes = R.drawable.ic_cereals
                        ),
                        GroceryItemCategory(
                            id = 10,
                            title = "Tea, coffee & drink mixes",
                            iconRes = R.drawable.ic_tea_coffee
                        ),
                        GroceryItemCategory(
                            id = 11,
                            title = "Juices & cold drinks",
                            iconRes = R.drawable.ic_juices
                        ),
                        GroceryItemCategory(
                            id = 12,
                            title = "Sauces & spreads",
                            iconRes = R.drawable.ic_sauces
                        ),
                        GroceryItemCategory(
                            id = 13,
                            title = "Dry fruits & seeds",
                            iconRes = R.drawable.ic_dry_fruits
                        ),
                        GroceryItemCategory(
                            id = 14,
                            title = "Noodles & pasta",
                            iconRes = R.drawable.ic_noodles
                        ),
                        GroceryItemCategory(
                            id = 15,
                            title = "Chips & biscuits",
                            iconRes = R.drawable.ic_chips
                        ),
                        GroceryItemCategory(
                            id = 16,
                            title = "Chocolates & ice cream",
                            iconRes = R.drawable.ic_chocolates
                        )
                    )
                    // In your screen composable
                    Spacer(modifier = Modifier.height(10.dp))
                    ListItemsGrid(
                        browseText = "Groceries & Food",
                        items = defaultGroceryItems,
                        onItemClick = { item ->
                            println("Clicked: ${item.title}")
                        },
                        columns = 4,
                        horizontalSpacing = 8.dp,
                        verticalSpacing = 1.dp,
                        itemHeight = 120.dp,
                        imageSize = 90.dp,
                        backgroundColor = MaterialTheme.customColors.background,
                        onBackClick = {}
                    )

                    val defaultBeautyCareItems = listOf(
                        GroceryItemCategory(
                            id = 1,
                            title = "Bath & body",
                            iconRes = R.drawable.ic_bath_body
                        ),
                        GroceryItemCategory(
                            id = 2,
                            title = "Hair care",
                            iconRes = R.drawable.ic_hair_care
                        ),
                        GroceryItemCategory(
                            id = 3,
                            title = "Skin & face",
                            iconRes = R.drawable.ic_skin_face
                        ),
                        GroceryItemCategory(
                            id = 4,
                            title = "Deos & perfumes",
                            iconRes = R.drawable.ic_deos_perfumes
                        ),
                        GroceryItemCategory(
                            id = 5,
                            title = "Feminine hygiene",
                            iconRes = R.drawable.ic_feminine_hygiene
                        ),
                        GroceryItemCategory(
                            id = 6,
                            title = "Men's grooming",
                            iconRes = R.drawable.ic_mens_grooming
                        ),
                        GroceryItemCategory(
                            id = 7,
                            title = "Oral care",
                            iconRes = R.drawable.ic_oral_care
                        ),
                        GroceryItemCategory(
                            id = 8,
                            title = "Baby care",
                            iconRes = R.drawable.ic_baby_care
                        ),
                        GroceryItemCategory(
                            id = 9,
                            title = "Makeup & cosmetics",
                            iconRes = R.drawable.ic_makeup_cosmetics
                        ),
                        GroceryItemCategory(
                            id = 10,
                            title = "Pharma & wellness",
                            iconRes = R.drawable.ic_pharma_wellness
                        ),
                        GroceryItemCategory(
                            id = 11,
                            title = "Diet & nutrition",
                            iconRes = R.drawable.ic_diet_nutrition
                        ),
                        GroceryItemCategory(
                            id = 12,
                            title = "Pet care",
                            iconRes = R.drawable.ic_pet_care
                        )
                    )
                    // In your screen composable
                    Spacer(modifier = Modifier.height(10.dp))
                    ListItemsGrid(
                        browseText = "Beauty & Personal Care",
                        items = defaultBeautyCareItems,
                        onItemClick = { item ->
                            println("Clicked: ${item.title}")
                        },
                        columns = 4,
                        horizontalSpacing = 8.dp,
                        verticalSpacing = 1.dp,
                        itemHeight = 120.dp,
                        imageSize = 90.dp,
                        backgroundColor = MaterialTheme.customColors.background,
                        onBackClick = {}
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Recommended for you",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.customColors.black
                        ),
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    GroceryTabs(
                        navController = navController,
                        selectedTabIndex = selectedTabIndex,
                        onCategorySelected = { category ->
                            selectedCategory = category
                            println("Selected category: ${category.title}")
                        },
                        onTabIndexChanged = { index ->
                            selectedTabIndex = index
                            println("Tab changed to index: $index")
                        }
                    )

                }
            }
            // Additional content items would go here
            // Add your product listings, categories, etc.
        }
    }

    // Location Dialog
    if (showLocationDialog) {
        Dialog(onDismissRequest = { showLocationDialog = false }) {
            AddressSelection(
                onDismissRequest = { showLocationDialog = false },
                onAddressSelected = { location ->
                    selectedLocation = location
                    showLocationDialog = false
                },
                onUseCurrentLocation = {
                    selectedLocation = Location(
                        house = "",
                        street = "Current Location",
                        apartment = "",
                        city = "",
                        state = "",
                        postal = "",
                        country = ""
                    )
                    showLocationDialog = false
                },
                onSearchByArea = {
                    selectedLocation = Location(
                        house = "",
                        street = "Searched Area",
                        apartment = "",
                        city = "Noida",
                        state = "UP",
                        postal = "",
                        country = "India"
                    )
                    showLocationDialog = false
                },
                onAddNewAddress = {
                    showLocationDialog = false
                }
            )
        }
    }
}

// Helper function if you need to use different amounts
@Composable
fun CashbackButtonWrapper(
    amount: String = "0",
    modifier: Modifier = Modifier
) {
    CashbackButton(
        amount = amount,
        modifier = modifier
    )
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