package com.example.hufko.components.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.components.searchbar.SearchBar
import com.example.hufko.components.homescreen.RestaurantCard
import com.example.hufko.ui.theme.customColors

@Composable
fun RestaurantDetails(
    onBackClick: () -> Unit,
    navController: NavHostController,
    restaurantItem: TopRatedRestaurantItem? = null
) {
    var searchQuery by remember { mutableStateOf("") }
    var showDropdownMenu by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.customColors.background)
        ) {
            // Sticky Header - Top Bar (Back button and three dots)
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.customColors.header)
                        .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back button
                    IconButton(onClick = onBackClick) {
                        Box(
                            modifier = Modifier.size(42.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back",
                                tint = MaterialTheme.customColors.white,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { /* Handle Join Prime action */ },
                        modifier = Modifier
                            .height(32.dp)
                            .padding(start = 8.dp)
                            .widthIn(min = 90.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.customColors.header,
                            contentColor = MaterialTheme.customColors.black
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.customColors.white
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_people),
                            contentDescription = "Group Order Icon",
                            modifier = Modifier
                                .size(28.dp)
                                .background(MaterialTheme.customColors.header)
                        )

                        Text(
                            text = "Group Order",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.customColors.white,
                            fontSize = 24.sp
                        )
                    }

                    // Three dots (more options) button
                    Box(
                        modifier = Modifier.size(42.dp)
                    ) {
                        IconButton(
                            onClick = { showDropdownMenu = !showDropdownMenu },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                                contentDescription = "More options",
                                tint = MaterialTheme.customColors.white,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showDropdownMenu,
                            onDismissRequest = { showDropdownMenu = false },
                            modifier = Modifier
                                .background(MaterialTheme.customColors.white)
                                .shadow(4.dp)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Share",
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                },
                                onClick = {
                                    showDropdownMenu = false
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_share_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Save to favorites",
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                },
                                onClick = {
                                    showDropdownMenu = false
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_favorite_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Report",
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                onClick = {
                                    showDropdownMenu = false
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_report_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp),
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            )
                        }
                    }
                }
            }

            // Restaurant Content
            item {
                if (restaurantItem != null) {
                    // If you want to show a single restaurant item
                    RestaurantCard(
                        items = listOf(restaurantItem), // Convert single item to list
                        onItemClick = { clickedItem ->
                            println("Clicked: ${clickedItem.restaurantName}")
                        }
                    )
                } else {
                    // Show carousel with multiple restaurants
                    RestaurantCard(
                        items = completeRestaurantItems,
                        onItemClick = { clickedItem ->
                            println("Clicked: ${clickedItem.restaurantName}")
                            // Navigate to restaurant details screen
                            // navController.navigate("restaurant/${clickedItem.id}")
                        }
                    )
                }
            }

            // Sticky Header - Search Bar
            stickyHeader {
                Surface(
                    color = MaterialTheme.customColors.header,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.customColors.header)
                            .padding(horizontal = 15.dp, vertical = 8.dp)
//                            .clip(RoundedCornerShape(
//                                topStart = 0.dp,
//                                topEnd = 0.dp,
//                                bottomStart = 20.dp,  // Adjust value as needed
//                                bottomEnd = 20.dp     // Adjust value as needed
//                            ))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 0.dp,
                                    bottom = 0.dp,
                                    start = 0.dp,
                                    end = 0.dp
                                )
//                                .background(MaterialTheme.customColors.background)
                        ) {
                            SearchBar(
                                query = searchQuery,
                                backgroundColor = MaterialTheme.customColors.background,
//                                backgroundColor = MaterialTheme.customColors.spacerColor,
                                placeholder = "Search for dishes",
                                onQueryChange = { searchQuery = it },
                                onSearch = { query -> println("Search performed: $query") }
                            )
                        }
                    }
                }
            }
        }
    }
}