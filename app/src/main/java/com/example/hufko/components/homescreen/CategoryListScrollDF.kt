package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R

/* -------------------- DATA MODEL -------------------- */

data class ProductListDGrid(
    val name: String,
    val price: String,
    val imageRes: Int,
    val backgroundColor: Color? = null
)

/* -------------------- MAIN GRID -------------------- */

@Composable
fun CategoryListScrollDF(
    products: List<ProductListDGrid>,
    modifier: Modifier = Modifier,
    itemWidth: Dp = 140.dp,
    itemHeight: Dp = 120.dp,
    rowSpacing: Dp = 12.dp,
    itemSpacing: Dp = 12.dp,
    backgroundColor: Color = Color.White,
    defaultCardColor: Color = Color.White,
    onItemClick: (ProductListDGrid) -> Unit = {}
) {
    val pairedProducts = products.chunked(2)

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(pairedProducts) { pair ->
            Column(
                modifier = Modifier.width(itemWidth),
                verticalArrangement = Arrangement.spacedBy(rowSpacing)
            ) {
                pair.forEach { product ->
                    CategoryScrollItem(
                        product = product,
                        itemWidth = itemWidth,
                        itemHeight = itemHeight,
                        containerColor = product.backgroundColor ?: backgroundColor ?: defaultCardColor,
                        onClick = { onItemClick(product) }
                    )
                }
            }
        }
    }
}

/* -------------------- CARD ITEM -------------------- */

@Composable
fun CategoryScrollItem(
    product: ProductListDGrid,
    itemWidth: Dp,
    itemHeight: Dp,
    containerColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(containerColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /* IMAGE */
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(1.dp),
                contentScale = ContentScale.Fit
            )

            /* PRICE STRIP (SAME BACKGROUND) */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(containerColor)
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = product.price,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

/* -------------------- PREVIEW -------------------- */

@Composable
fun CategoryListScrollPreview() {
    val products = listOf(
        ProductListDGrid("Chai Point", "FLAT 10% OFF", R.drawable.popular_chain_1),
        ProductListDGrid("abCoffee", "FLAT 10% OFF", R.drawable.popular_chain_2),
        ProductListDGrid("Good Flippin", "FLAT 10% OFF", R.drawable.popular_chain_3),
        ProductListDGrid("Chelvies", "FLAT 25% OFF", R.drawable.popular_chain_4),
        ProductListDGrid("Cafe Rue", "FLAT 20% OFF", R.drawable.popular_chain_5),
        ProductListDGrid("Barista", "FLAT 30% OFF", R.drawable.popular_chain_6)
    )

    MaterialTheme {
        CategoryListScrollDF(
            products = products,
            backgroundColor = Color(0xFFF2F2F2)
        )
    }
}
