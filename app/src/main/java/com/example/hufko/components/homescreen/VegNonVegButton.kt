package com.example.hufko.components.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VegNonVegButton(
    modifier: Modifier = Modifier,
    onFilterChanged: (Int) -> Unit = {} // Changed back to Int for filter options
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }
    var isActive by remember { mutableStateOf(false) }

    // Toggle button that opens dropdown when clicked
    Column(
        modifier = modifier
            .width(80.dp)
            .height(45.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
            .background(
                color = if (isActive) Color(0xFFE8F5E8) else Color.White
            )
            .clickable { expanded = true },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Top Text
        Text(
            text = "VEG",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isActive) Color(0xFF2E7D32) else Color(0xFF555555)
        )

//        Spacer(modifier = Modifier.height(4.dp))

        // Toggle Switch (Custom) - Shows current state
        Box(
            modifier = Modifier
                .width(45.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isActive) Color(0xFFD7F3DB) else Color(0xFFE5E5E5)
                )
                .clickable { expanded = true }
        ) {
            // Circle inside toggle - shows current selection
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .offset(
                        x = if (selectedOption == 1) 23.dp else 3.dp // 1 = Veg, 0 = All
                    )
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (selectedOption == 1) Color(0xFF2E7D32) else Color.White
                    )
                    .border(
                        2.dp,
                        if (selectedOption == 1) Color(0xFF2E7D32) else Color(0xFFCCCCCC),
                        RoundedCornerShape(50)
                    )
            )
        }
    }

    // Dropdown menu (popup) - Same as your original design
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Color.White)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
    ) {
        // Header
        DropdownMenuItem(
            text = {
                Text(
                    text = "See veg dishes from",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            },
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )

        // Option 1: All restaurants (full form)
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = selectedOption == 0,
                        onClick = {
                            selectedOption = 0
                            isActive = true
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "All restaurants",
                        fontSize = 16.sp,
                        color = Color(0xFF333333),
                        fontWeight = if (selectedOption == 0) FontWeight.Medium else FontWeight.Normal
                    )
                }
            },
            onClick = {
                selectedOption = 0
                isActive = true
            }
        )

        // Option 2: Pure Veg restaurants only (full form)
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = selectedOption == 1,
                        onClick = {
                            selectedOption = 1
                            isActive = true
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Pure Veg restaurants only",
                        fontSize = 16.sp,
                        color = Color(0xFF333333),
                        fontWeight = if (selectedOption == 1) FontWeight.Medium else FontWeight.Normal
                    )
                }
            },
            onClick = {
                selectedOption = 1
                isActive = true
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )

        // Apply button
        DropdownMenuItem(
            text = {
                Text(
                    text = "Apply",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onClick = {
                expanded = false
                isActive = true
                onFilterChanged(selectedOption)
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color(0xFFE0E0E0)
        )

        // More settings
        DropdownMenuItem(
            text = {
                Text(
                    text = "More settings",
                    fontSize = 16.sp,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onClick = {
                expanded = false
                // Navigate to more settings
            }
        )

        // Clear filter option (only shown when active)
        if (isActive) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                thickness = 1.dp,
                color = Color(0xFFE0E0E0)
            )

            DropdownMenuItem(
                text = {
                    Text(
                        text = "Clear filter",
                        fontSize = 16.sp,
                        color = Color(0xFFF44336),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                onClick = {
                    expanded = false
                    isActive = false
                    selectedOption = 0
                    onFilterChanged(-1) // -1 indicates filter cleared
                }
            )
        }
    }
}

// Alternative version with text label (if you prefer text instead of just "VEG")
@Composable
fun VegNonVegButtonWithLabel(
    modifier: Modifier = Modifier,
    onFilterChanged: (Int) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }
    var isActive by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable { expanded = true }
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = if (isActive) Color(0xFFE8F5E8) else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                BorderStroke(
                    width = if (isActive) 2.dp else 1.dp,
                    color = if (isActive) Color(0xFF4CAF50) else Color(0xFFE0E0E0)
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = when (selectedOption) {
                    0 -> "All"
                    1 -> "Veg Only"
                    else -> "All"
                },
                fontSize = 16.sp,
                color = if (isActive) Color(0xFF2E7D32) else Color(0xFF333333),
                fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown",
                tint = if (isActive) Color(0xFF4CAF50) else Color(0xFF666666),
                modifier = Modifier.size(20.dp)
            )
        }
    }

    // Same dropdown menu as above
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Color.White)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
    ) {
        // ... (same dropdown content as above)
    }
}

// Usage example in your main screen with SearchBar
@Composable
fun RestaurantFilterSection() {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // SearchBar takes 80% width
        Box(
            modifier = Modifier
                .weight(0.8f)
                .padding(end = 8.dp)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { query -> println("Search performed: $query") }
            )
        }

        // VegNonVegButton takes 20% width
        Box(
            modifier = Modifier
                .weight(0.2f)
        ) {
            VegNonVegButton(
                onFilterChanged = { filterType ->
                    when (filterType) {
                        0 -> println("Filter: All restaurants")
                        1 -> println("Filter: Pure Veg only")
                        -1 -> println("Filter cleared")
                    }
                }
            )
        }
    }
}

// Placeholder SearchBar component
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF5F5F5))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = if (query.isEmpty()) "Search..." else query,
            fontSize = 16.sp,
            color = if (query.isEmpty()) Color(0xFF888888) else Color(0xFF333333)
        )
    }
}