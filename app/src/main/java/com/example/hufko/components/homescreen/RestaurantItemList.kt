package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

// Data class for restaurant information
data class Restaurant(
    val id: Int,
    val imageRes: Int,
    val restaurantName: String,
    val bestIn: String,
    val rating: Double,
    val totalRating: String,
    val deliverTime: String,
    val categories: String,
    val address: String,
    val distance: String,
    val isWishlisted: Boolean = false,
    val hasFlatDeal: Boolean = false,
    val flatDealText: String = "",
    val itemsPrice: String = ""
)

@Composable
fun RestaurantItemList(
    restaurant: Restaurant,
    onItemClick: (Restaurant) -> Unit = {},
    onWishlistClick: (Restaurant) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(restaurant) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Restaurant Image with Wishlist Icon
        Box(
            modifier = Modifier
                .width(170.dp)     // set width
                .height(200.dp)
//                .size(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.customColors.gray.copy(alpha = 0.2f))
        ) {
            // Restaurant Image (placeholder - replace with actual image loading)
            Image(
                painter = painterResource(id = restaurant.imageRes),
                contentDescription = "${restaurant.restaurantName} image",
                modifier = Modifier
                    .width(170.dp)     // set width
                    .height(200.dp)     // set height
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds
            )


            // Wishlist Icon at top right corner
            Icon(
                painter = painterResource(
                    id = if (restaurant.isWishlisted)
                        R.drawable.ic_wishlist_outline
                    else
                        R.drawable.ic_wishlist_outline
                ),
                contentDescription = if (restaurant.isWishlisted) "Remove from wishlist" else "Add to wishlist",
                tint = if (restaurant.isWishlisted) Color.Red else Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopEnd)
                    .padding(9.dp)
                    .clickable { onWishlistClick(restaurant) }
            )
        }

        // Restaurant Details
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically), // Center vertically in the row
            verticalArrangement = Arrangement.Center, // Center content vertically within column,
//            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Restaurant Name and Best In

            Text(
                text = restaurant.bestIn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.customColors.gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = restaurant.restaurantName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Rating and Delivery Time
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rating
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${restaurant.rating}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.customColors.black
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.outline_star_24),
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = "(${restaurant.totalRating})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.customColors.black,
//                        color = MaterialTheme.customColors.gray
                    )
                }

                // Dot separator
                Text(
                    text = "•",
                    fontSize = 14.sp,
                    color = MaterialTheme.customColors.darkGray
                )

                // Delivery Time
                Text(
                    text = restaurant.deliverTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.customColors.black,
//                    color = MaterialTheme.customColors.gray
                )
            }

            // Categories
            Text(
                text = restaurant.categories,
                fontSize = 14.sp,
                color = MaterialTheme.customColors.darkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Address and Distance
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = restaurant.address,
                    fontSize = 14.sp,
                    color = MaterialTheme.customColors.darkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Dot separator
                Text(
                    text = "•",
                    fontSize = 14.sp,
                    color = MaterialTheme.customColors.darkGray
                )

                Text(
                    text = restaurant.distance,
                    fontSize = 14.sp,
                    color = MaterialTheme.customColors.darkGray
                )
            }
        }
    }
}

// Divider between restaurant items
@Composable
fun RestaurantDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.customColors.gray.copy(alpha = 0.3f))
            .padding(horizontal = 16.dp)
    )
}

// Complete restaurant list component
@Composable
fun RestaurantList(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit = {},
    onWishlistToggle: (Restaurant) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors.white)
    ) {
        restaurants.forEachIndexed { index, restaurant ->
            RestaurantItemList(
                restaurant = restaurant,
                onItemClick = onRestaurantClick,
                onWishlistClick = onWishlistToggle
            )

            // Add divider between items (except after last item)
            if (index < restaurants.size - 1) {
                RestaurantDivider()
            }
        }
    }
}

// Sample data for preview
val sampleRestaurants = listOf(
    Restaurant(
        id = 1,
        imageRes = R.drawable.restaurant_1, // Replace with your image resource
        restaurantName = "Burger King",
        bestIn = "Best In Burger",
        rating = 4.3,
        totalRating = "26K+",
        deliverTime = "30–35 mins",
        categories = "Burgers, American",
        address = "Sector 35",
        distance = "5.0 km",
        isWishlisted = false
    ),
    Restaurant(
        id = 2,
        imageRes = R.drawable.restaurant_2, // Replace with your image resource
        restaurantName = "Burger Singh(Big Punjabi...)",
        bestIn = "",
        rating = 4.2,
        totalRating = "868",
        deliverTime = "25–30 mins",
        categories = "Burgers, Snacks, Desserts, Beverages",
        address = "DMRC Metro Station",
        distance = "21 km",
        isWishlisted = true
    ),
    Restaurant(
        id = 3,
        imageRes = R.drawable.restaurant_3, // Replace with your image resource
        restaurantName = "McDonald's",
        bestIn = "Best In Burger",
        rating = 4.4,
        totalRating = "10K+",
        deliverTime = "35–40 mins",
        categories = "American",
        address = "Sector 35",
        distance = "5.0 km",
        isWishlisted = false
    )
)