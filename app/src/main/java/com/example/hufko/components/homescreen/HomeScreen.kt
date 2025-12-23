package com.example.hufko.components.homescreen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
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
            ?.getStateFlow<Int?>("updatedTabIndex", null)
            ?.collect { newIndex ->
                newIndex?.let { index ->
                    // Update the selected tab index
                    selectedTabIndex = index
                    // Clear the saved state
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<Int>("updatedTabIndex")
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 0.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.85f)
                                    .padding(
                                        top = 0.dp,
                                        bottom = 4.dp,
                                        start = 0.dp,
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
                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .weight(0.15f)
                                    .padding(
                                        top = 0.dp,
                                        bottom = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp
                                    )
                            ) {
                                CashbackButton(amount = "50")
                            }
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(0.dp))
                }
            }

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
                    onCategorySelected = { category ->
                        // Optional: Handle category selection logic
                    },
                    onTabIndexChanged = { newIndex ->
                        // Update the selectedTabIndex
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