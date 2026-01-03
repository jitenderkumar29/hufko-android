package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

/**
 * Food item data class with all optional fields
 */
data class FoodItemDoubleF(
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
    val isWishlisted: Boolean? = false,
)

/**
 * Individual food item card
 */
@Composable
fun FoodItemCard(
    foodItem: FoodItemDoubleF,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    cardWidth: Dp = 160.dp,
    cardHeight: Dp = 240.dp,
    imageHeight: Dp = 120.dp,
    defaultImageRes: Int = R.drawable.restaurant_1 // Default image if none provided
) {
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
            // Food image
            Image(
                painter = painterResource(id = foodItem.imageRes ?: defaultImageRes),
                contentDescription = foodItem.title ?: "Food item",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )

            // Discount badge at top-left
            foodItem.discount?.let { discount ->
                if (discount.isNotEmpty()) {
                    // Title and price badge at top left of image
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 4.dp, top = 4.dp)
                            .background(Color(0xB146322B), RoundedCornerShape(6.dp))
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${discount} OFF up to ₹100",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                    }
                }
            }

            // Rating overlay at bottom of image (only if rating or delivery time exists)
            if (foodItem.rating != null || foodItem.deliveryTime != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(
                            color = MaterialTheme.customColors.success,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Rating
                        foodItem.rating?.let { rating ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = rating,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                                Text(
                                    text = "★",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Content section below image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            // Title
            foodItem.title?.let { title ->
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Delivery time
            foodItem.deliveryTime?.let { deliveryTime ->
                Text(
                    text = deliveryTime,
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/**
 * Horizontal scrolling food items list with TWO ROWS
 */
@Composable
fun FoodItemsList(
    foodItems: List<FoodItemDoubleF>,
    onItemClick: (FoodItemDoubleF) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    cardWidth: Dp = 160.dp,
    cardHeight: Dp = 240.dp,
    horizontalSpacing: Dp = 12.dp,
    verticalSpacing: Dp = 12.dp,
    horizontalPadding: Dp = 16.dp
) {
    if (foodItems.isEmpty()) {
        // Show empty state or return empty box
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = horizontalPadding)
        ) {
            Text(
                text = "No food items available",
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )
        }
        return
    }

    // Split items into pairs for two rows
    val itemPairs = foodItems.chunked(2)

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing)
    ) {
        items(itemPairs) { pair ->
            Column(
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // First item in the pair
                pair.getOrNull(0)?.let { firstItem ->
                    FoodItemCard(
                        foodItem = firstItem,
                        onClick = { onItemClick(firstItem) },
                        cardWidth = cardWidth,
                        cardHeight = cardHeight
                    )
                }

                // Second item in the pair
                pair.getOrNull(1)?.let { secondItem ->
                    FoodItemCard(
                        foodItem = secondItem,
                        onClick = { onItemClick(secondItem) },
                        cardWidth = cardWidth,
                        cardHeight = cardHeight
                    )
                }
            }
        }
    }
}

/**
 * Food items list with heading and TWO ROWS layout
 */@Composable
fun FoodItemsListWithHeading(
    heading: String? = null,
    subtitle: String? = null,
    foodItems: List<FoodItemDoubleF>,
    onItemClick: (FoodItemDoubleF) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.customColors.white,
    cardWidth: Dp = 160.dp,
    cardHeight: Dp = 240.dp,
    horizontalSpacing: Dp = 12.dp,
    verticalSpacing: Dp = 12.dp,
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

        FoodItemsList(
            foodItems = foodItems,
            onItemClick = onItemClick,
            backgroundColor = backgroundColor,
            cardWidth = cardWidth,
            cardHeight = cardHeight,
            horizontalSpacing = horizontalSpacing,
            verticalSpacing = verticalSpacing,
            horizontalPadding = horizontalPadding
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

/**
 * Sample data and preview with various optional field combinations
 */
val sampleFoodItems = listOf(
    FoodItemDoubleF(
        id = 20,
        imageRes = R.drawable.restaurant_image_all_food_20,
        title = "Paneer Handi",
        price = "180",
        restaurantName = "Shree Jee Restaurant",
        rating = "4.1",
        deliveryTime = "45-50 mins",
        distance = "7.3 km",
        discount = "60%",
        discountAmount = "up to ₹120",
        address = "Delhi"
    ),
    FoodItemDoubleF(
        id = 21,
        imageRes = R.drawable.restaurant_1,
        title = "Butter Chicken",
        price = "220",
        restaurantName = "Amiche Pizza",
        rating = "4.3",
        deliveryTime = "60-65 mins"
    ),
    FoodItemDoubleF(
        id = 22,
        imageRes = R.drawable.restaurant_1,
        title = "Vegetable Biryani",
        price = "150",
        rating = "4.0",
        deliveryTime = "30-35 mins",
        discount = "40%"
    ),
    FoodItemDoubleF(
        id = 23,
        title = "Special Thali",
        restaurantName = "Spice Garden",
        deliveryTime = "25-30 mins"
    ),
    FoodItemDoubleF(
        id = 24,
        imageRes = R.drawable.restaurant_1,
        title = "Chocolate Brownie",
        price = "99",
        discountAmount = "up to ₹50"
    ),
    FoodItemDoubleF(
        id = 25,
        imageRes = R.drawable.restaurant_1,
        title = "Masala Dosa",
        price = "80",
        restaurantName = "South Indian Delight",
        rating = "4.5",
        deliveryTime = "20-25 mins",
        discount = "20%"
    ),
    FoodItemDoubleF(
        id = 26,
        imageRes = R.drawable.restaurant_1,
        title = "Chicken Biryani",
        price = "250",
        restaurantName = "Biryani House",
        rating = "4.2",
        deliveryTime = "35-40 mins"
    )
)

@Preview(showBackground = true)
@Composable
fun FoodItemCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preview with all fields
            FoodItemCard(
                foodItem = sampleFoodItems[0],
                onClick = { println("Clicked ${sampleFoodItems[0].title}") }
            )

            // Preview with minimal fields
            FoodItemCard(
                foodItem = sampleFoodItems[3],
                onClick = { println("Clicked minimal item") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemsListPreview() {
    MaterialTheme {
        FoodItemsListWithHeading(
            heading = "Popular Dishes",
            subtitle = "Scroll to explore more",
            foodItems = sampleFoodItems,
            onItemClick = { foodItem -> println("Clicked ${foodItem.title}") },
            backgroundColor = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemsListOddNumberPreview() {
    MaterialTheme {
        FoodItemsListWithHeading(
            heading = "Today's Specials",
            foodItems = sampleFoodItems.take(5), // Odd number of items
            onItemClick = { foodItem -> println("Clicked ${foodItem.title}") },
            backgroundColor = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyFoodItemsListPreview() {
    MaterialTheme {
        FoodItemsListWithHeading(
            heading = "Popular Dishes",
            foodItems = emptyList(),
            onItemClick = { },
            backgroundColor = Color.White
        )
    }
}