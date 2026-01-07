package com.example.hufko.components.homescreen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.components.searchbar.SearchBar
import com.example.hufko.ui.theme.customColors

@Composable
fun HomeScreen(navController: NavHostController?) {
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

    // 🔴 CRITICAL: Manage selected tab index in HomeScreen
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // 🔴 Listen for updates from CategoryTabsFList via SavedStateHandle
    LaunchedEffect(navController) {
        navController?.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<Int?>("currentSelectedIndex", null)
            ?.collect { newIndex ->
                newIndex?.let { index ->
                    // Update the selected tab index
                    selectedTabIndex = index
                    // Clear the saved state
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

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
        ) {
            // 🟢 Location Section (hidden when scrolled)
            item {
                if (isLocationVisible) {
                    Surface(
                        color = MaterialTheme.customColors.header,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // 🔴 FIXED: Using your existing CashbackButton with proper layout
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 0.dp)
//                                .height(48.dp) // Fixed height for consistency
                        ) {
                            // Location button - takes available space
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Takes all available space
                                    .padding(
                                        top = 0.dp,
                                        bottom = 4.dp,
                                        end = 8.dp // Add spacing between location and cashback
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
                            // The width will automatically adjust based on the amount digits
                            CashbackButton(
                                amount = "1500", // You can make this dynamic
                                modifier = Modifier
                                    .padding(
                                        top = 0.dp,
                                        bottom = 0.dp
                                    )
                                    .wrapContentSize() // Let the button determine its own size
                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(0.dp))
                }
            }

            // 🔵 Sticky Search Bar (always visible)
            // 🔵 Sticky Search Bar (always visible)
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
                        Box(
                            modifier = Modifier
                                .weight(0.15f)
                                .padding(
                                    top = 4.dp,
                                    bottom = 8.dp,
                                    start = 4.dp,
                                    end = 0.dp
                                )
                        ) {
                            VegNonVegButton()
                        }
                    }
                }
            }

            item {
                // 🔴 Pass selectedTabIndex and callback
                CategoryTabsFood(
                    navController = navController,
                    selectedTabIndex = selectedTabIndex,
                    onCategorySelected = { categoryPage ->
                        // Update selected index when a category is selected
                        selectedTabIndex = when (categoryPage) {
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
                            CategoryPage.SouthIndian -> 32
                            CategoryPage.AlooTikki -> 33
                            CategoryPage.Pasta -> 34
                            CategoryPage.Pastry -> 35
                            CategoryPage.PavBhaji -> 36
                            CategoryPage.Sandwich -> 37
                            CategoryPage.Shake -> 38
                            CategoryPage.Samosa -> 39
                            CategoryPage.Poori -> 40
                            CategoryPage.SeeAll -> 41
                        }
                    },
                    onTabIndexChanged = { newIndex ->
                        selectedTabIndex = newIndex
                    }
                )
            }
        }
    }

    // 🟢 Location Dialog
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
    // Just wrap your existing CashbackButton
    CashbackButton(
        amount = amount,
        modifier = modifier
    )
}