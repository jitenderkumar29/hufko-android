package com.example.hufko.components.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.hufko.components.searchbar.SearchBar
import com.example.hufko.ui.theme.customColors


@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var showLocationDialog by remember { mutableStateOf(false) }
    var selectedLocation by remember {
        mutableStateOf(
            Location(
                house = "123",
                street = "Main",
                apartment = "4B",
                city = "Noida",
                state = "UP",
                postal = "10001",
                country = "India"
            )
        )
    }

    val lazyListState = rememberLazyListState()
    var isLocationVisible by remember { mutableStateOf(true) }

    // Track scroll position to hide/show location section
    LaunchedEffect(lazyListState.firstVisibleItemScrollOffset) {
        val scrollThreshold = 50 // Adjust this value as needed
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
//                        color = MaterialTheme.customColors.darkAccent,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.customColors.header)
//                            .background(
//                                brush = Brush.verticalGradient(
//                                    colors = listOf(
//                                        Color(0xFFC2E1FE),
//                                        Color(0xFFEDF6FF)
//                                    )
//                                )
//                            )
                                .padding(horizontal = 12.dp, vertical = 0.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.80f)
                                    .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 0.dp
                                    )
                            ) {
                                LocationSelectionButton(
                                    selectedLocation = selectedLocation,
                                    onLocationClick = { showLocationDialog = true }
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(0.20f)
                                    .padding( top = 8.dp, bottom = 8.dp, start = 0.dp, end = 0.dp
                                    )
                            ) {
                                CashbackButton(amount = "50")
                            }
                        }
                    }
                } else {
                    // Empty space when location is hidden to maintain layout
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
//                            .background(
//                                brush = Brush.verticalGradient(
//                                    colors = listOf(
//                                        Color(0xFFC2E1FE),
//                                        Color(0xFFEDF6FF)
//                                    )
//                                )
//                            )
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
                CategoryTabsFood()
            }

            // Add some sample content to enable scrolling
//            items(20) { index ->
//                Surface(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    color = MaterialTheme.colorScheme.surface
//                ) {
//                    Text(
//                        text = "Content item $index",
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//            }
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