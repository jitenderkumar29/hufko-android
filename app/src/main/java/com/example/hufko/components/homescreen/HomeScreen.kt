package com.example.hufko.components.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()

//                .background(MaterialTheme.colorScheme.background)
        ) {
            // 🟢 Location Section
            item {
                Surface(
                    color = MaterialTheme.customColors.darkAccent,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LocationSelectionButton(
                        selectedLocation = selectedLocation,
                        onLocationClick = { showLocationDialog = false }
                    )
                }
            }

            // 🔵 Sticky Search Bar (pinned on scroll)
            stickyHeader {
                Surface(
                    color = MaterialTheme.customColors.lightAccent,
                    modifier = Modifier.fillMaxWidth()
//                        .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(
//                                    Color(0xFF9BCDFE),
//                                    Color(0xFFC2E1FE)
//                                )
//                            )
//                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFC2E1FE),
                                        Color(0xFFEDF6FF)
                                    )
                                )
                            )
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
        }
    }

    // 🟢 Location Dialog
    if (showLocationDialog) {
        Dialog(onDismissRequest = { showLocationDialog = false }) {
            AddressSelection(
                onDismissRequest = { showLocationDialog = false },
                onAddressSelected = { location ->
                    // Update the selected location with the chosen address
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
                    // Handle add new address
                    showLocationDialog = false
                }
            )
        }
    }
}