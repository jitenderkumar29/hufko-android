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
import androidx.compose.ui.graphics.Brush
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

                // Price and discount
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹${item.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Image(
                        painter = painterResource(R.drawable.discount_badge),
                        contentDescription = "",
                        modifier = Modifier
                            .size(12.dp),
//                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${item.discount}",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "USE HUFKO COUPON",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

//              Rating
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Transparent,
                    modifier = Modifier.wrapContentSize()  // Dynamic width & height
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.customColors.success.copy(alpha = 0.2f),
                                        MaterialTheme.customColors.success.copy(alpha = 0.1f)
                                    )
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(
                                start = 6.dp,
                                top = 0.dp,
                                end = 6.dp,
                                bottom = 0.dp
                            )
                        ) {
                            Text(
                                text = "★",
                                fontSize = 12.sp,
                                color = MaterialTheme.customColors.success,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = formatRating(item.rating),
                                fontSize = 12.sp,
                                color = MaterialTheme.customColors.success,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "(${getRandomRatings()})",
                                fontSize = 12.sp,
                                color = MaterialTheme.customColors.success,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                //            Protein
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.protein ?: "Untitled",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.customColors.orangeVivid, // Primary text color
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = "Protein",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.customColors.orangeVivid, // Secondary/Label color
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(
                        text = "•",
                        fontSize = 20.sp,
                        color = MaterialTheme.customColors.orangeLight // Separator color
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = item.calories ?: "Untitled",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.customColors.orangeVivid, // Primary text color
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
//                    modifier = Modifier.weight(1f)
                    )
                }

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
                    .padding(end = 0.dp),  // Removed extra space - changed from 1.dp to 0.dp
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()  // Changed from fixed width to fill remaining space
//                        .width(140.dp)
                        .height(170.dp)
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


// Helper functions (add these if not already defined elsewhere)
