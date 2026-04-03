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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun RestaurantItemDetails(
    item: FoodItemDoubleF,
    onAddClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 0.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            /** LEFT SIDE **/
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {

                // Veg / Info Icon
                item.infoIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "Veg",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Title
                Text(
                    text = item.title ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2
                )

                // Progress Row
                Row(verticalAlignment = Alignment.CenterVertically) {

                    val progress = (item.highlyReordered?.toFloatOrNull() ?: 0f) / 100f
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.customColors.spacerColor)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(progress)  // 👈 width based on progress
                                .background(MaterialTheme.customColors.success)
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Highly reordered",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }

                // Price
                Text(
                    text = "₹${item.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                // Description
                Text(
                    text = item.description ?: "",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Save & Share
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_bookmark_24),
                            contentDescription = "Save",
                            tint = Color.Gray
                        )
                    }

                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_share_24),
                            contentDescription = "Share",
                            tint = Color.Gray
                        )
                    }
                }
            }

            /** RIGHT SIDE **/
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(140.dp)
                        .height(140.dp)
                ) {
                    // Image
                    Image(
                        painter = painterResource(id = item.imageRes!!),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    // ADD Button (Floating)
                    Button(
                        onClick = onAddClick,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 20.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.customColors.success
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.customColors.success),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp),
                        elevation = ButtonDefaults.buttonElevation(2.dp)
                    ) {
                        Text("ADD +", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Customisable",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRestaurantItemDetails() {
    val foodItems = listOf(
        FoodItemDoubleF(
            id = 1,
            imageRes = R.drawable.restaurant_image_pizzas_food_1,
            title = "Chicken Burger",
            price = "275",
            restaurantName = "Burger Hub",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "₹25 OFF",
            address = "Food Street, City Center",
            calories = "450 kcal",
            protein = "22g",
            isHighProtein = true,
            category = "Fast Food",
            isWishlisted = false,
            description = "Juicy chicken burger with fresh lettuce, tomato and crispy fries",
            quantity = "1",
            infoIcon = R.drawable.ic_veg_rest,
            highlyReordered = "20",
            reorderedQuantity = "150+ orders",
        ),

        FoodItemDoubleF(
            id = 2,
            imageRes = R.drawable.restaurant_image_pizzas_food_2,
            title = "Chilli Garlic Momos",
            price = "363",
            restaurantName = "Momo Express",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.2 km",
            discount = "15% OFF",
            discountAmount = "₹40 OFF",
            address = "Downtown Food Plaza",
            calories = "380 kcal",
            protein = "14g",
            isHighProtein = false,
            category = "Snacks",
            isWishlisted = true,
            description = "Spicy chilli garlic momos tossed with flavorful sauces and herbs",
            quantity = "1",
            infoIcon = R.drawable.ic_veg_rest,
            highlyReordered = "25",
            reorderedQuantity = "200+ orders",
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(vertical = 8.dp)
    ) {
        foodItems.forEach { item ->
            RestaurantItemDetails(
                item = item,
                onAddClick = {
                    println("Added: ${item.title}")
                }
            )
        }
    }
}