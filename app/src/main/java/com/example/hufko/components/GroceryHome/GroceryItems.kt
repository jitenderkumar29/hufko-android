package com.example.hufko.components.GroceryHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R

// -------------------- DATA --------------------

data class GroceryItemTwo(
    val id: Int,
    val discount: String,
    val title: String,
    val weight: String,
    val discountedPrice: String,
    val originalPrice: String,
    val imageRes: Int
)

// -------------------- UI --------------------

@Composable
fun GroceryItemsHorizontal(
    items: List<GroceryItemTwo>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2), // ✅ 2 rows
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp) // important for 2 rows
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            GroceryItemCard(item = item)
        }
    }
}

@Composable
fun GroceryItemCard(
    item: GroceryItemTwo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(110.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {

            // Image + Discount
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                        .width(50.dp),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = item.discount,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
//                        .padding(4.dp)
                        .background(Color(0xFFFF6B6B), RoundedCornerShape(4.dp))
                        .padding(horizontal = 2.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Title
            Text(
                text = item.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Weight
            Text(
                text = item.weight,
                fontSize = 11.sp,
                color = Color.Gray
            )

            // Price Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.discountedPrice,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = item.originalPrice,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough // ✅ strike-through
                )
            }
        }
    }
}
