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
import com.example.hufko.components.homescreen.CashbackButton
import com.example.hufko.components.homescreen.CategoryItem
import com.example.hufko.components.homescreen.CategoryListSimple
import com.example.hufko.components.homescreen.Location
import com.example.hufko.components.homescreen.LocationSelectionButton
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


                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Recommended for you",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.customColors.black
                        ),
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

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
