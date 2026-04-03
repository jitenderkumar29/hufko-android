package com.example.hufko.components.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.material3.ButtonDefaults
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors
import kotlin.collections.chunked
import kotlin.collections.forEach


@Composable
fun RestaurantItemDetails2(
    item: FoodItemDoubleF,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.customColors.background)
            ) {
                if (item.imageRes != null) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.title ?: "Food item",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.customColors.background),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_photo_camera_24),
                            contentDescription = "No image",
                            tint = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            //  Info Icon
//            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                item.infoIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.customColors.success.copy(alpha = 0.1f),
                        modifier = Modifier.size(width = 65.dp, height = 25.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
                        ) {
                            Text(
                                text = "★",
                                fontSize = 12.sp,
                                color = MaterialTheme.customColors.success,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            Spacer(modifier = Modifier.width(1.dp))
                            Text(
                                text = formatRating(item.rating),
                                fontSize = 12.sp,
                                color = MaterialTheme.customColors.success,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(1.dp))
                            Text(
                                text = "(${getRandomRatings()})",
                                fontSize = 12.sp,
                                color = MaterialTheme.customColors.success,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Title
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.title ?: "Untitled",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Price and Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹${item.price ?: "0"}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.customColors.black
                )

                OutlinedButton(
                    onClick = onAddClick,
                    modifier = Modifier.height(32.dp).width(70.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, color = MaterialTheme.customColors.success),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.customColors.success)
                ) {
                    Text("ADD", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.customColors.success)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewRestaurantItemDetails2() {
    val sampleItems = listOf(
        FoodItemDoubleF(
            id = 1,
            imageRes = R.drawable.restaurant_image_pizzas_food_1,
            title = "Tandoori Soya Chaap Roll",
            price = "189",
            restaurantName = "Tandoori Junction",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "20% OFF",
            discountAmount = "₹20",
            description = "A delicious snack made of soya chaap",
            quantity = "20",
            infoIcon = R.drawable.ic_veg_rest,
            highlyReordered = "icon_name",
            reorderedQuantity = "icon_name",
            address = "Food Court, Mall"
        ),
        FoodItemDoubleF(
            id = 2,
            imageRes = R.drawable.restaurant_image_pizzas_food_2,
            title = "Paneer Tikka Roll",
            price = "220",
            restaurantName = "Tandoori Junction",
            rating = "4.6",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "₹33",
            description = "Delicious paneer tikka roll",
            quantity = "15",
            infoIcon = R.drawable.ic_veg_rest,
            highlyReordered = "icon_name",
            reorderedQuantity = "icon_name",
            address = "Food Court, Mall"
        ),
        FoodItemDoubleF(
            id = 3,
            imageRes = R.drawable.restaurant_image_pizzas_food_1,
            title = "Margherita Pizza",
            price = "450",
            restaurantName = "Pizza House",
            rating = "4.7",
            deliveryTime = "30-35 mins",
            distance = "3.0 km",
            discount = "20% OFF",
            discountAmount = "₹90 OFF",
            address = "Pizza Street",
            calories = "680 kcal",
            protein = "25g",
            isHighProtein = false,
            category = "Pizza",
            isWishlisted = false,
            description = "Classic Margherita pizza with fresh mozzarella and basil",
            quantity = "1",
            infoIcon = R.drawable.ic_veg_rest,
            highlyReordered = "30",
            reorderedQuantity = "300+ orders",
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors.background)
            .padding(12.dp)
    ) {
        // Display items in 2-column grid
        val rows = sampleItems.chunked(2)
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    RestaurantItemDetails2(
                        item = item,
                        modifier = Modifier.weight(1f),
                        onAddClick = { println("Added: ${item.title}") }
                    )
                }
                // Fill empty space if odd number of items
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}