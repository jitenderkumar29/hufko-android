package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R

data class RestaurantItemFull(
    val id: Int,
    val imageRes: Int, // Changed from imageUrl: String to imageRes: Int
    val title: String,
    val price: String,
    val restaurantName: String,
    val rating: String,
    val deliveryTime: String,
    val distance: String,
    val address: String,
    val discount: String,
    val discountAmount: String,
    val isWishlisted: Boolean = false
)

@Composable
fun RestaurantItemListFull(
    restaurantItem: RestaurantItemFull, // Fixed parameter name
    modifier: Modifier = Modifier,
    onWishlistClick: (Int) -> Unit = {},
    onThreeDotClick: (Int) -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    var isWishlisted by remember { mutableStateOf(restaurantItem.isWishlisted) }

    Card(
        onClick = { onItemClick(restaurantItem.id) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Image section with overlay buttons
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                // Restaurant image using local drawable
                androidx.compose.foundation.Image(
                    painter = painterResource(id = restaurantItem.imageRes),
                    contentDescription = restaurantItem.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.FillBounds
                )

                // Top right corner buttons
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Wishlist button
                    IconButton(
                        onClick = {
                            isWishlisted = !isWishlisted
                            onWishlistClick(restaurantItem.id)
                        },
                        modifier = Modifier
                            .size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isWishlisted) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Wishlist",
                            tint = if (isWishlisted) Color.Red else Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Three dot menu button
                    IconButton(
                        onClick = { onThreeDotClick(restaurantItem.id) },
                        modifier = Modifier
                            .size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                // Title and price badge at top left of image
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 12.dp, top = 12.dp)
                        .background(Color(0xB146322B), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = restaurantItem.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "₹${restaurantItem.price}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }

            // Content section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                // Restaurant name and rating row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = restaurantItem.restaurantName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Row(
                        modifier = Modifier
                            .background(Color(0xFF007631), RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = restaurantItem.rating,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "★",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Delivery time and distance
                Text(
                    text = "${restaurantItem.deliveryTime},  ${restaurantItem.distance}, ${restaurantItem.address}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                // Discount badge at bottom left
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
//                        .padding(horizontal = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_percent),
                        contentDescription = restaurantItem.title,
                        modifier = Modifier
                            .size(22.dp)          // Icon size
                            .clip(RoundedCornerShape(4.dp)), // Optional small rounding
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "${restaurantItem.discount} OFF up to ${restaurantItem.discountAmount}",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}

// Updated extension function for local images
@Composable
fun RestaurantItemListFull(
    imageRes: Int,
    title: String,
    price: String,
    restaurantName: String,
    rating: String,
    deliveryTime: String,
    distance: String,
    address: String,
    discount: String,
    discountAmount: String,
    modifier: Modifier = Modifier,
    isWishlisted: Boolean = false,
    onWishlistClick: () -> Unit = {},
    onThreeDotClick: () -> Unit = {},
    onItemClick: () -> Unit = {}
) {
    val restaurantItem = RestaurantItemFull(
        id = 0,
        imageRes = imageRes,
        title = title,
        price = price,
        restaurantName = restaurantName,
        rating = rating,
        deliveryTime = deliveryTime,
        distance = distance,
        address = address,
        discount = discount,
        discountAmount = discountAmount,
        isWishlisted = isWishlisted
    )

    RestaurantItemListFull(
        restaurantItem = restaurantItem,
        modifier = modifier,
        onWishlistClick = { onWishlistClick() },
        onThreeDotClick = { onThreeDotClick() },
        onItemClick = { onItemClick() }
    )
}

// Sample data with local drawable
val sampleRestaurantItem = RestaurantItemFull(
    id = 1,
    imageRes = R.drawable.restaurant_image, // Use your actual drawable resource
    title = "Paneer Delight Momos",
    price = "159",
    restaurantName = "Goblins",
    rating = "4.0",
    deliveryTime = "30-35 mins",
    distance = "5.8 km",
    address = "Givindpuram",
    discount = "50%",
    discountAmount = "100",
    isWishlisted = false
)


@Preview(showBackground = true)
@Composable
fun RestaurantItemListFullPreview() {
    RestaurantItemListFull(
        restaurantItem = sampleRestaurantItem, // Fixed parameter name
        onWishlistClick = { },
        onThreeDotClick = { },
        onItemClick = { }
    )
}