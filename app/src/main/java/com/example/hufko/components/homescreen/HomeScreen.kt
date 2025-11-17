package com.example.hufko.components.homescreen

import LocationAddress
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.hufko.components.searchbar.SearchBar
import com.example.hufko.ui.theme.customColors
//import com.example.hufko.components.categorytabs.CategoryTabs


@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var showLocationDialog by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf("Noida 201001") }

    val lazyListState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // 🟢 Category Header FIRST (scrolls away with content)
//            item {
//                Surface(
//                    color = MaterialTheme.customColors.lightAccent,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    CategoryHeader(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                    )
////                    CategoryHeader()
////                    CategoryHeader(
////                        modifier = Modifier.padding(vertical = 0.dp)
////                    )
//                }
//            }

            // 🟢 Location Section
            item {
                Surface(
                    color = MaterialTheme.customColors.darkAccent,
//                    color = Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color(0xFF903E3F),
//                                MaterialTheme.customColors.darkAccent
//                            )
//                        )
//                    )
                ) {
                    LocationSelectionButton(
                        selectedLocation = selectedLocation,
                        onLocationClick = { showLocationDialog = false }
//                        onLocationClick = { showLocationDialog = true }
                    )
                }
            }

            // 🔵 Sticky Search Bar (pinned on scroll)
            stickyHeader {
                Surface(
                    color = MaterialTheme.customColors.lightAccent,
//                    color = Color.Transparent,
                    modifier = Modifier.fillMaxWidth()
//                        .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(
//                                    Color(0xFF8B2B2D),
//                                    Color(0xFF923839)
//                                )
//                            )
//                        )
                ) {
//                    Column {
                        // ✅ Status bar spacer
//                        Spacer(
//                            modifier = Modifier
//                                .windowInsetsTopHeight(WindowInsets.statusBars)
//                                .background(MaterialTheme.customColors.lightAccent)
//                        )

                        // 🔍 Sticky Search Bar
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 12.dp, vertical = 0.dp)
//                    ) {
//                        // SearchBar takes 80% width
//                        Box(
//                            modifier = Modifier
//                                .weight(0.85f)
//                                .padding(
//                                    top = 4.dp,     // Top padding
//                                    bottom = 8.dp,  // Bottom padding
//                                    start = 0.dp,  // Left padding
//                                    end = 0.dp      // Right padding (spacing between components)
//                                )
//                        ) {
//                            SearchBar(
//                                query = searchQuery,
//                                onQueryChange = { searchQuery = it },
//                                onSearch = { query -> println("Search performed: $query") }
//                            )
//                        }
//                        // VegNonVegButton takes 20% width
//                        Box(
//                            modifier = Modifier
//                                .weight(0.15f)
//                                .padding(
//                                    top = 4.dp,     // Top padding
//                                    bottom = 8.dp,  // Bottom padding
//                                    start = 4.dp,  // Left padding
//                                    end = 0.dp      // Right padding (spacing between components)
//                                )
//                        ) {
//                            VegNonVegButton()
//                        }
//                    }
                }
            }

//            item {
//                Spacer(modifier = Modifier.height(24.dp)
//                    .background(MaterialTheme.customColors.lightAccent))
//            }

            // 🟢 Category Tabs Items
            item {
//                CategoryTabs()
            }

//            item {
//                MoreCategoryPage()
//            }

            // 🟢 Content Items
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(0.20.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//            item {
//                BannerHome(
//                    onJoinPrimeClick = { println("Prime button clicked!") },
//                    onDealBoxClick = { println("Deal section clicked!") }
//                )
//            }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//
//            item {
//                QrakonPayScreen()
//            }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//
//            item {
//                AdsSponsored(
//                    onAdClick = {
//                        // Handle ad click here, e.g., navigate to product details
//                        println("Ad clicked!")
//                    }
//                )
//            }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//
//            item {
//                CategoryProducts(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()
//                )
//            }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//
//            item { BikeCategoryHeader() }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//
//            item { ElectronicCategory() }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//
//            item { FurnitureCategory() }
//
//            item {
//                Spacer(
//                    modifier = Modifier
//                        .height(2.dp)
//                        .fillMaxWidth()
//                        .background(MaterialTheme.customColors.spacerColor)
//                )
//            }
//            item { ClothsCategory() }
        }
    }

    // 🟢 Location Dialog
    if (showLocationDialog) {
        Dialog(onDismissRequest = { showLocationDialog = false }) {
            LocationAddress(
                onBackClick = { showLocationDialog = false },
                onLocationSelected = { location ->
                    selectedLocation = location
                    showLocationDialog = false
                },
                onUseCurrentLocation = {
                    selectedLocation = "Current Location"
                    showLocationDialog = false
                }
            )
        }
    }
}
