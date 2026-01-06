package com.example.hufko.components.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors
data class Location(
    val house: String = "",
    val street: String = "",
    val apartment: String = "",
    val city: String = "",
    val state: String = "",
    val postal: String = "",
    val country: String = ""
) {
    // Helper function to format the complete address including all fields
    fun getAddress(): String {
        return buildString {
            // House number and street
            if (house.isNotEmpty() && street.isNotEmpty()) {
                append("$house $street")
            } else if (house.isNotEmpty()) {
                append(house)
            } else if (street.isNotEmpty()) {
                append(street)
            }

            // Apartment
            if (apartment.isNotEmpty()) {
                if (isNotEmpty()) append(", ")
                append("Apartment $apartment")
            }

            // City
            if (city.isNotEmpty()) {
                if (isNotEmpty()) append(", ")
                append(city)
            }

            // State
            if (state.isNotEmpty()) {
                if (isNotEmpty()) append(", ")
                append(state)
            }

            // Postal code
            if (postal.isNotEmpty()) {
                if (isNotEmpty()) append(" ")
                append(postal)
            }

            // Country
            if (country.isNotEmpty()) {
                if (isNotEmpty()) append(", ")
                append(country)
            }

            // If all fields are empty, return a default message
            if (isEmpty()) {
                append("No address selected")
            }
        }.trim()
    }

    // Helper function to format the display address (similar to original)
    fun getDisplayAddress(): String {
        return buildString {
            if (house.isNotEmpty()) append("$house ")
            if (street.isNotEmpty()) append("$street ")
            if (apartment.isNotEmpty()) append("Apt $apartment, ")
            if (city.isNotEmpty()) append("$city, ")
            if (state.isNotEmpty()) append("$state ")
            if (postal.isNotEmpty()) append("$postal")
            if (country.isNotEmpty()) append(", $country")
        }.trim().removeSuffix(",").trim()
    }

    // Helper function to get a short address for compact display
    fun getShortAddress(): String {
        return if (city.isNotEmpty() && state.isNotEmpty()) {
            "$city, $state"
        } else if (city.isNotEmpty()) {
            city
        } else if (state.isNotEmpty()) {
            state
        } else {
            "Select location"
        }
    }

    fun isEmpty(): Boolean {
        return house.isEmpty() && street.isEmpty() && apartment.isEmpty() &&
                city.isEmpty() && state.isEmpty() && postal.isEmpty() && country.isEmpty()
    }

    // Additional helper to check if address has minimum required fields
    fun isValidAddress(): Boolean {
        return street.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty() && postal.isNotEmpty()
    }
}

@Composable
fun LocationSelectionButton(
    selectedLocation: Location,
    onLocationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onLocationClick,
        modifier = modifier
            .height(30.dp)
            .background(Color(0x526A2E22)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.map),
//                painter = painterResource(id = R.drawable.outline_home_24),
                contentDescription = "Location",
                tint = Color.Unspecified, // 👈 IMPORTANT
//                tint = MaterialTheme.customColors.success,
                modifier = Modifier.size(30.dp)
            )
//            AsyncImage(
//                model = R.drawable.location, // your GIF in drawable
//                contentDescription = "Location",
//                modifier = Modifier.size(24.dp)
//            )

            Spacer(modifier = Modifier.width(2.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = "HOME-",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.customColors.white,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        text = if (!selectedLocation.isEmpty())
                            selectedLocation.getDisplayAddress()
                        else "Select location",
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = if (!selectedLocation.isEmpty())
                            MaterialTheme.customColors.white
                        else MaterialTheme.customColors.white.copy(alpha = 0.8f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Change location",
                tint = MaterialTheme.customColors.white,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
