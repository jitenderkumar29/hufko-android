package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun FilterButtonFood(
    modifier: Modifier = Modifier,
    onFilterClick: (String) -> Unit = {},
    onSortClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // First Row - Horizontal Scroll
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Filter button with both icons
            FilterChipWithBothIcons(
                leftIcon = R.drawable.ic_filter,
                text = "Filters",
                rightIcon = R.drawable.outline_keyboard_arrow_down_24,
                onClick = { onFilterClick("Filters") }
            )

            // Sort button
            FilterChipWithRightIcon(
                text = "Sort by",
                rightIcon = R.drawable.outline_keyboard_arrow_down_24,
                onClick = onSortClick
            )

            // Pizza type filters with left icons
            FilterChipWithLeftIcon(
                icon = R.drawable.ic_pizza_cheese_burst,
                text = "Cheese Burst",
                onClick = { onFilterClick("Cheese Burst") }
            )

            FilterChipWithLeftIcon(
                icon = R.drawable.ic_pizza_farmhouse,
                text = "Farmhouse",
                onClick = { onFilterClick("Farmhouse") }
            )

            FilterChipWithLeftIcon(
                icon = R.drawable.ic_pizza_margherita,
                text = "Margherita",
                onClick = { onFilterClick("Margherita") }
            )

            FilterChipWithLeftIcon(
                icon = R.drawable.ic_pizza_multigrain,
                text = "Multigrain",
                onClick = { onFilterClick("Multigrain") }
            )

            FilterChipWithLeftIcon(
                icon = R.drawable.ic_pizza_pan,
                text = "Pan",
                onClick = { onFilterClick("Pan") }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Second Row - Horizontal Scroll
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Text-only filters
            FilterChipTextOnly(
                text = "Under ₹150",
                onClick = { onFilterClick("Under ₹150") }
            )

            FilterChipTextOnly(
                text = "Under 30 mins",
                onClick = { onFilterClick("Under 30 mins") }
            )

            FilterChipTextOnly(
                text = "Rating 4.0+",
                onClick = { onFilterClick("Rating 4.0+") }
            )

            FilterChipTextOnly(
                text = "Pure Veg",
                onClick = { onFilterClick("Pure Veg") }
            )

            // Schedule with dropdown
            FilterChipWithRightIcon(
                text = "Schedule",
                rightIcon = R.drawable.outline_keyboard_arrow_down_24,
                onClick = { onFilterClick("Schedule") }
            )

            // Topping filters with left icons
            FilterChipWithLeftIcon(
                icon = R.drawable.ic_veg_paneer,
                text = "Paneer",
                onClick = { onFilterClick("Paneer") }
            )

            FilterChipWithLeftIcon(
                icon = R.drawable.ic_non_veg_pepperoni,
                text = "Pepperoni",
                onClick = { onFilterClick("Pepperoni") }
            )
        }
    }
}

// Reusable component for chips with left icon only
@Composable
fun FilterChipWithLeftIcon(
    icon: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.customColors.white)
            .border(
                width = 1.dp,
                color = MaterialTheme.customColors.gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.customColors.black
        )
    }
}

// Reusable component for chips with right icon only
@Composable
fun FilterChipWithRightIcon(
    text: String,
    rightIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.customColors.white)
            .border(
                width = 1.dp,
                color = MaterialTheme.customColors.gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.customColors.black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = rightIcon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

// Reusable component for chips with both left and right icons
@Composable
fun FilterChipWithBothIcons(
    leftIcon: Int,
    text: String,
    rightIcon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.customColors.white)
            .border(
                width = 1.dp,
                color = MaterialTheme.customColors.gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = leftIcon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.customColors.black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = rightIcon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

// Reusable component for text-only chips
@Composable
fun FilterChipTextOnly(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.customColors.white)
            .border(
                width = 1.dp,
                color = MaterialTheme.customColors.gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.customColors.black
        )
    }
}

// Helper functions implementation
fun showFilterDialog() {
    // Implement your filter dialog logic here
    // This could open a BottomSheetDialog or Dialog
    println("Show filter dialog")
}

fun filterByCrustType(crustType: String) {
    println("Filter by crust type: $crustType")
}

fun filterByPizzaType(pizzaType: String) {
    println("Filter by pizza type: $pizzaType")
}

fun filterByPrice(maxPrice: Int) {
    println("Filter by max price: $maxPrice")
}

fun filterByRating(minRating: Double) {
    println("Filter by min rating: $minRating")
}

fun filterByVeg(isVeg: Boolean) {
    println("Filter by veg: $isVeg")
}

fun showScheduleDialog() {
    println("Show schedule dialog")
}

fun filterByTopping(topping: String) {
    println("Filter by topping: $topping")
}

fun showSortOptionsDialog() {
    println("Show sort options dialog")
}

fun applyFilters(filters: Set<String>) {
    println("Applied filters: $filters")
}

fun applySorting(sortOption: String) {
    println("Applied sorting: $sortOption")
}