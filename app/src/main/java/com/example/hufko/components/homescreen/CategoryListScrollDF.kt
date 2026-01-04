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

// 🔹 Data model remains the same
data class ProductListDGrid(
    val name: String,
    val price: String,
    val imageRes: Int,
    val backgroundColor: Color? = null // Optional background color
)

// 🔹 Horizontal scrolling version with two rows - CategoryListScrollDF
@Composable
fun CategoryListScrollDF(
    products: List<ProductListDGrid>,
    modifier: Modifier = Modifier,
    itemWidth: Dp? = null, // Width for each item
    itemHeight: Dp? = null, // Height for each item
    showName: Boolean = true,
    showPrice: Boolean = true,
    defaultCardColor: Color = MaterialTheme.colorScheme.surface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    imageHeight: Dp? = null,
    imageAspectRatio: Float = 1f,
    textColor: Color? = null,
    priceBackgroundColor: Color = Color.Black, // Background color for price text
    itemSpacing: Dp = 8.dp,
    rowSpacing: Dp = 2.dp, // Spacing between the two rows
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    onItemClick: (ProductListDGrid) -> Unit = {}
) {
    // Split products into two equal rows (half items in first row, half in second)
    val halfIndex = (products.size + 1) / 2 // Round up for odd number of items
    val row1 = products.take(halfIndex)
    val row2 = products.drop(halfIndex)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .heightIn(min = 200.dp, max = 800.dp)
    ) {
        // First Row (first half of items)
        if (row1.isNotEmpty()) {
            SingleRowScrollDF(
                products = row1,
                itemWidth = itemWidth,
                itemHeight = itemHeight,
                showName = showName,
                showPrice = showPrice,
                defaultCardColor = defaultCardColor,
                backgroundColor = backgroundColor,
                imageHeight = imageHeight,
                imageAspectRatio = imageAspectRatio,
                textColor = textColor,
                priceBackgroundColor = priceBackgroundColor,
                itemSpacing = itemSpacing,
                contentPadding = contentPadding,
                onItemClick = onItemClick
            )
        }

        // Spacing between rows
        if (row2.isNotEmpty()) {
            Spacer(modifier = Modifier.height(rowSpacing))

            // Second Row (second half of items)
            SingleRowScrollDF(
                products = row2,
                itemWidth = itemWidth,
                itemHeight = itemHeight,
                showName = showName,
                showPrice = showPrice,
                defaultCardColor = defaultCardColor,
                backgroundColor = backgroundColor,
                imageHeight = imageHeight,
                imageAspectRatio = imageAspectRatio,
                textColor = textColor,
                priceBackgroundColor = priceBackgroundColor,
                itemSpacing = itemSpacing,
                contentPadding = contentPadding,
                onItemClick = onItemClick
            )
        }
    }
}

// 🔹 Single row horizontal scrolling component (reusable)
@Composable
fun SingleRowScrollDF(
    products: List<ProductListDGrid>,
    modifier: Modifier = Modifier,
    itemWidth: Dp? = null,
    itemHeight: Dp? = null,
    showName: Boolean = true,
    showPrice: Boolean = true,
    defaultCardColor: Color = MaterialTheme.colorScheme.surface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    imageHeight: Dp? = null,
    imageAspectRatio: Float = 1f,
    textColor: Color? = null,
    priceBackgroundColor: Color = Color.Black, // Background color for price text
    itemSpacing: Dp = 8.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    onItemClick: (ProductListDGrid) -> Unit = {}
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp, max = 400.dp),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(products) { product ->
            CategoryScrollItem(
                product = product,
                itemWidth = itemWidth,
                itemHeight = itemHeight,
                showName = showName,
                showPrice = showPrice,
                defaultCardColor = defaultCardColor,
                backgroundColor = backgroundColor,
                imageHeight = imageHeight,
                imageAspectRatio = imageAspectRatio,
                textColor = textColor,
                priceBackgroundColor = priceBackgroundColor,
                onClick = { onItemClick(product) }
            )
        }
    }
}

// 🔹 Horizontal scroll item with white price text on background
@Composable
fun CategoryScrollItem(
    product: ProductListDGrid,
    itemWidth: Dp? = null,
    itemHeight: Dp? = null,
    showName: Boolean = true,
    showPrice: Boolean = true,
    defaultCardColor: Color = MaterialTheme.colorScheme.surface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    imageHeight: Dp? = null,
    imageAspectRatio: Float = 1f,
    textColor: Color? = null,
    priceBackgroundColor: Color = Color.Black, // Background color for price text
    onClick: () -> Unit
) {
    val cardColor = product.backgroundColor ?: defaultCardColor

    Card(
        modifier = Modifier
            .height(itemHeight ?: 200.dp)
            .width(itemWidth ?: 160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            // Image sizing logic
            val imageModifier = if (imageHeight != null) {
                Modifier
                    .height(imageHeight)
                    .fillMaxWidth()
            } else if (itemHeight != null) {
                // Calculate image height based on item height and whether we show text
                val calculatedHeight = if (showName || showPrice) {
                    itemHeight * 0.7f // 70% for image, 30% for text
                } else {
                    itemHeight
                }
                Modifier
                    .height(calculatedHeight)
                    .fillMaxWidth()
            } else {
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(imageAspectRatio)
            }

            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = imageModifier.clip(RoundedCornerShape(0.dp)),
                contentScale = ContentScale.FillBounds
            )

            if (showName || showPrice) {
                Box(
                    modifier = Modifier
                        .height(6.dp)
                        .fillMaxWidth()
                        .background(backgroundColor) // Change to your desired color
                ) {
                    // Empty content, just for spacing
                }
//                Spacer(modifier = Modifier.height(6.dp))
            }

            // NAME TEXT
            if (showName && product.name.isNotEmpty()) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor ?: MaterialTheme.typography.bodyMedium.color
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(horizontal = 4.dp)
                )
            }

            // PRICE TEXT with white color and background
            if (showPrice && product.price.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = product.price,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // White text color
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        modifier = Modifier
                            .align(Alignment.Center)
//                            .background(
//                                color = priceBackgroundColor,
//                                shape = RoundedCornerShape(4.dp)
//                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            if (showName || showPrice) {
                Box(
                    modifier = Modifier
                        .height(6.dp)
                        .fillMaxWidth()
                        .background(backgroundColor) // Change to your desired color
                ) {
                    // Empty content, just for spacing
                }
//                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

// 🔹 Usage examples for two-row horizontal scrolling version
@Composable
fun ExampleUsageTwoRows() {
    val sampleProducts = listOf(
        ProductListDGrid(
            name = "Product 1",
            price = "$29.99",
            imageRes = R.drawable.popular_chain_1,
            backgroundColor = Color(0xFFFFF8E1)
        ),
        ProductListDGrid(
            name = "Product 2",
            price = "$39.99",
            imageRes = R.drawable.popular_chain_2,
            backgroundColor = Color(0xFFE8F5E8)
        ),
        ProductListDGrid(
            name = "Product 3",
            price = "$49.99",
            imageRes = R.drawable.popular_chain_3,
            backgroundColor = Color(0xFFE3F2FD)
        ),
        ProductListDGrid(
            name = "Product 4",
            price = "$19.99",
            imageRes = R.drawable.popular_chain_4
        ),
        ProductListDGrid(
            name = "Product 5",
            price = "$24.99",
            imageRes = R.drawable.popular_chain_5
        ),
        ProductListDGrid(
            name = "Product 6",
            price = "$34.99",
            imageRes = R.drawable.popular_chain_6
        ),
        ProductListDGrid(
            name = "Product 7",
            price = "$44.99",
            imageRes = R.drawable.popular_chain_1
        ),
        ProductListDGrid(
            name = "Product 8",
            price = "$54.99",
            imageRes = R.drawable.popular_chain_2
        ),
        ProductListDGrid(
            name = "Product 9",
            price = "$64.99",
            imageRes = R.drawable.popular_chain_3
        )
    )

    // Example 1: Two rows with white price on black background
    CategoryListScrollDF(
        products = sampleProducts,
        itemWidth = 160.dp,
        itemHeight = 200.dp,
        showName = true,
        showPrice = true,
        imageHeight = 120.dp,
        itemSpacing = 12.dp,
        rowSpacing = 16.dp,
        priceBackgroundColor = Color.Black,
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Example 2: Different price background color
    CategoryListScrollDF(
        products = sampleProducts.take(6),
        itemWidth = 140.dp,
        itemHeight = 180.dp,
        showName = true,
        showPrice = true,
        imageHeight = 100.dp,
        itemSpacing = 10.dp,
        rowSpacing = 12.dp,
        defaultCardColor = Color(0xFFF5F5F5),
        priceBackgroundColor = Color(0xFF6200EE), // Purple background
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Example 3: Red price background
    CategoryListScrollDF(
        products = sampleProducts,
        itemWidth = 200.dp,
        itemHeight = 150.dp,
        showName = true,
        showPrice = true,
        imageHeight = null,
        imageAspectRatio = 16f / 9f,
        itemSpacing = 16.dp,
        rowSpacing = 20.dp,
        priceBackgroundColor = Color.Red, // Red background
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Example 4: Mixed usage with sections
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray.copy(alpha = 0.1f))
    ) {
        // Featured Section - Two rows
        Text(
            text = "Featured Products",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        CategoryListScrollDF(
            products = sampleProducts,
            itemWidth = 180.dp,
            itemHeight = 220.dp,
            showName = true,
            showPrice = true,
            imageHeight = 140.dp,
            itemSpacing = 16.dp,
            rowSpacing = 16.dp,
            priceBackgroundColor = Color(0xFF4CAF50), // Green background
            contentPadding = PaddingValues(horizontal = 0.dp),
            onItemClick = { product ->
                println("Clicked on ${product.name}")
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // On Sale Section - Two rows with different styling
        Text(
            text = "On Sale",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        CategoryListScrollDF(
            products = sampleProducts.takeLast(8),
            itemWidth = 150.dp,
            itemHeight = 190.dp,
            showName = true,
            showPrice = true,
            imageHeight = null,
            imageAspectRatio = 1f,
            itemSpacing = 12.dp,
            rowSpacing = 16.dp,
            defaultCardColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            priceBackgroundColor = Color(0xFFFF5722), // Orange background
            contentPadding = PaddingValues(horizontal = 0.dp),
            onItemClick = { product ->
                println("Clicked on new arrival: ${product.name}")
            }
        )
    }
}

// 🔹 Test with different product counts
@Composable
fun TestDifferentProductCounts() {
    val products5 = List(5) { index ->
        ProductListDGrid(
            name = "Product ${index + 1}",
            price = "\$${(index + 1) * 9.99}",
            imageRes = R.drawable.popular_chain_1
        )
    }

    val products7 = List(7) { index ->
        ProductListDGrid(
            name = "Product ${index + 1}",
            price = "\$${(index + 1) * 9.99}",
            imageRes = R.drawable.popular_chain_2
        )
    }

    val products10 = List(10) { index ->
        ProductListDGrid(
            name = "Product ${index + 1}",
            price = "\$${(index + 1) * 9.99}",
            imageRes = R.drawable.popular_chain_3
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("5 Products (3 in first row, 2 in second)",
            style = MaterialTheme.typography.titleMedium)
        CategoryListScrollDF(
            products = products5,
            itemWidth = 160.dp,
            itemHeight = 200.dp,
            priceBackgroundColor = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("7 Products (4 in first row, 3 in second)",
            style = MaterialTheme.typography.titleMedium)
        CategoryListScrollDF(
            products = products7,
            itemWidth = 160.dp,
            itemHeight = 200.dp,
            priceBackgroundColor = Color.Blue
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("10 Products (5 in first row, 5 in second)",
            style = MaterialTheme.typography.titleMedium)
        CategoryListScrollDF(
            products = products10,
            itemWidth = 160.dp,
            itemHeight = 200.dp,
            priceBackgroundColor = Color.Green
        )
    }
}