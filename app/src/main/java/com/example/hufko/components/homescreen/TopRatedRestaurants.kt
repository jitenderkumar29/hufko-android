package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

/**
 * Food item data class for Top Rated Restaurants
 */
data class TopRatedRestaurantItem(
    val id: Int? = null,
    val imageRes: Int? = null,
    val title: String? = null,
    val price: String? = null,
    val restaurantName: String? = null,
    val rating: String? = null,
    val deliveryTime: String? = null,
    val distance: String? = null,
    val discount: String? = null,
    val discountAmount: String? = null,
    val address: String? = null,
    val calories: String? = null,
    val protein: String? = null,
    val isHighProtein: Boolean? = null,
    val category: String? = null,
    val isWishlisted: Boolean? = null,
)

/**
 * Individual Top Rated Restaurant card
 */
@Composable
fun TopRatedRestaurantCard(
    restaurantItem: TopRatedRestaurantItem,
    onClick: () -> Unit,
    onWishlistClick: (Int?) -> Unit = {},
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    cardWidth: Dp = 160.dp,
    cardHeight: Dp = 240.dp,
    imageHeight: Dp = 180.dp,
    defaultImageRes: Int = R.drawable.restaurant_1
) {
    var isWishlisted by remember { mutableStateOf(restaurantItem.isWishlisted ?: false) }

    Column(
        modifier = modifier
            .width(cardWidth)
            .height(cardHeight)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(backgroundColor)
    ) {
        // Image section with overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            // Restaurant image
            Image(
                painter = painterResource(id = restaurantItem.imageRes ?: defaultImageRes),
                contentDescription = restaurantItem.title ?: "Restaurant",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )

            // Overlay content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                // Discount badge at top-left
//                restaurantItem.discount?.let { discount ->
//                    if (discount.isNotEmpty()) {
//                        Box(
//                            modifier = Modifier
//                                .align(Alignment.TopStart)
//                                .background(Color(0xB146322B), RoundedCornerShape(6.dp))
//                                .padding(horizontal = 8.dp, vertical = 4.dp)
//                        ) {
//                            Text(
//                                text = "$discount% OFF",
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.White
//                            )
//                        }
//                    }
//                }

                // Wishlist button at top-right
                Box(
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    IconButton(
                        onClick = {
                            isWishlisted = !isWishlisted
                            restaurantItem.id?.let { onWishlistClick(it) }
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (isWishlisted) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Wishlist",
                            tint = if (isWishlisted) Color.Red else Color.White,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp) // Add some padding from edges
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        // Discount Box
                        restaurantItem.discount?.let { discount ->
                            if (discount.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0x7746322B), RoundedCornerShape(6.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "$discount",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        // Add some spacing between discount and price
                        Spacer(modifier = Modifier.height(4.dp))

                        // Price Box
                        restaurantItem.price?.let { price ->
                            if (price.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0x7746322B), RoundedCornerShape(6.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "AT ₹$price",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }

                // Rating badge at bottom-left
//                restaurantItem.rating?.let { rating ->
//                    Box(
//                        modifier = Modifier
//                            .align(Alignment.BottomStart)
//                            .padding(start = 0.dp, bottom = 0.dp)
//                            .background(
//                                color = MaterialTheme.customColors.success,
//                                shape = RoundedCornerShape(20.dp)
//                            )
//                            .padding(horizontal = 8.dp, vertical = 4.dp)
//                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = rating,
//                                color = Color.White,
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Medium,
//                                modifier = Modifier.padding(end = 2.dp)
//                            )
//                            Text(
//                                text = "★",
//                                color = Color.White,
//                                fontSize = 14.sp
//                            )
//                        }
//                    }
//                }
            }
        }

        // Content section below image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            // Restaurant title
            restaurantItem.title?.let { title ->
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

//            Spacer(modifier = Modifier.height(4.dp))

            // Delivery time and distance
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Rating
                restaurantItem.rating?.let { rating ->
                    Image(
                        painter = painterResource(id = R.drawable.ic_star_food),
                        contentDescription = "Restaurant",
                        contentScale = ContentScale.FillBounds, // Changed from FillBounds to Crop for better appearance
                        modifier = Modifier
                            .width(20.dp) // Set width
                            .height(20.dp) // Set height
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = restaurantItem.rating,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                }

                // Delivery time (make it more visible)
                restaurantItem.deliveryTime?.let { deliveryTime ->
                    Text(
                        text = " • ",
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                    Text(
                        text = deliveryTime,
                        color = Color.Black, // Green color for better visibility
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Add a separator dot if both deliveryTime and distance exist
//                if (restaurantItem.deliveryTime != null && restaurantItem.distance != null) {
//                    Text(
//                        text = " • ",
//                        color = Color.Black,
//                        fontSize = 20.sp
//                    )
//                }

                // Distance
//                restaurantItem.distance?.let { distance ->
//                    Text(
//                        text = distance,
//                        color = Color.Black,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Normal
//                    )
//                }
            }

            // Category
            restaurantItem.category?.let { category ->
                Text(
                    text = category,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Horizontal scrolling list of Top Rated Restaurants (SINGLE ROW)
 */
@Composable
fun TopRatedRestaurantsList(
    restaurantItems: List<TopRatedRestaurantItem>,
    onItemClick: (TopRatedRestaurantItem) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    cardWidth: Dp = 160.dp,
    cardHeight: Dp = 240.dp,
    spacing: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp
) {
    if (restaurantItems.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = horizontalPadding)
        ) {
            Text(
                text = "No restaurants available",
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )
        }
        return
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        items(restaurantItems) { restaurantItem ->
            TopRatedRestaurantCard(
                restaurantItem = restaurantItem,
                onClick = { onItemClick(restaurantItem) },
                cardWidth = cardWidth,
                cardHeight = cardHeight
            )
        }
    }
}

/**
 * Top Rated Restaurants with heading
 */
@Composable
fun TopRatedRestaurants(
    heading: String? = null,
    subtitle: String? = null,
    restaurantItems: List<TopRatedRestaurantItem>,
    onItemClick: (TopRatedRestaurantItem) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    cardWidth: Dp = 160.dp,
    cardHeight: Dp = 240.dp,
    spacing: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 16.dp,
    headingBottomPadding: Dp = 12.dp
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        if (heading != null || subtitle != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding)
                    .padding(top = verticalPadding, bottom = headingBottomPadding)
            ) {
                heading?.let {
                    Text(
                        text = heading,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        TopRatedRestaurantsList(
            restaurantItems = restaurantItems,
            onItemClick = onItemClick,
            backgroundColor = backgroundColor,
            cardWidth = cardWidth,
            cardHeight = cardHeight,
            spacing = spacing,
            horizontalPadding = horizontalPadding
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

/**
 * Sample data for Top Rated Restaurants
 */
val sampleTopRatedRestaurants = listOf(
    TopRatedRestaurantItem(
        id = 1,
        imageRes = R.drawable.restaurant_1,
        title = "Shree Jee Restaurant",
        rating = "4.1",
        deliveryTime = "45-50 mins",
        distance = "7.3 km",
        discount = "60",
        discountAmount = "up to ₹120",
        address = "Delhi",
        category = "North Indian"
    ),
    TopRatedRestaurantItem(
        id = 2,
        imageRes = R.drawable.restaurant_1,
        title = "Amiche Pizza",
        rating = "4.3",
        deliveryTime = "60-65 mins",
        category = "Italian"
    ),
    TopRatedRestaurantItem(
        id = 3,
        imageRes = R.drawable.restaurant_1,
        title = "Spice Garden",
        rating = "4.0",
        deliveryTime = "30-35 mins",
        discount = "40",
        category = "Multi-cuisine"
    ),
    TopRatedRestaurantItem(
        id = 4,
        imageRes = R.drawable.restaurant_1,
        title = "South Indian Delight",
        rating = "4.5",
        deliveryTime = "20-25 mins",
        discount = "20",
        category = "South Indian"
    ),
    TopRatedRestaurantItem(
        id = 5,
        imageRes = R.drawable.restaurant_1,
        title = "Biryani House",
        rating = "4.2",
        deliveryTime = "35-40 mins",
        category = "Mughlai"
    ),
    TopRatedRestaurantItem(
        id = 6,
        imageRes = R.drawable.restaurant_1,
        title = "Fast Food Center",
        rating = "4.0",
        deliveryTime = "15-20 mins",
        category = "Fast Food"
    ),
    TopRatedRestaurantItem(
        id = 7,
        imageRes = R.drawable.restaurant_1,
        title = "Chinese Corner",
        rating = "4.4",
        deliveryTime = "25-30 mins",
        discount = "30",
        category = "Chinese"
    )
)

@Preview(showBackground = true)
@Composable
fun TopRatedRestaurantCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preview with all fields
            TopRatedRestaurantCard(
                restaurantItem = sampleTopRatedRestaurants[0],
                onClick = { println("Clicked ${sampleTopRatedRestaurants[0].title}") }
            )

            // Preview with minimal fields
            TopRatedRestaurantCard(
                restaurantItem = sampleTopRatedRestaurants[2],
                onClick = { println("Clicked minimal restaurant") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopRatedRestaurantsPreview() {
    MaterialTheme {
        TopRatedRestaurants(
            heading = "Top Rated Restaurants",
            subtitle = "Based on ratings and reviews",
            restaurantItems = sampleTopRatedRestaurants,
            onItemClick = { restaurant -> println("Clicked ${restaurant.title}") },
            backgroundColor = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopRatedRestaurantsOddNumberPreview() {
    MaterialTheme {
        TopRatedRestaurants(
            heading = "Top Picks",
            restaurantItems = sampleTopRatedRestaurants.take(5),
            onItemClick = { restaurant -> println("Clicked ${restaurant.title}") },
            backgroundColor = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyTopRatedRestaurantsPreview() {
    MaterialTheme {
        TopRatedRestaurants(
            heading = "Top Rated Restaurants",
            restaurantItems = emptyList(),
            onItemClick = { },
            backgroundColor = Color.White
        )
    }
}