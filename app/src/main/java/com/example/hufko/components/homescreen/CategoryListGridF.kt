package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R

// 🔹 Data model with optional background color
data class ProductListGrid(
    val name: String,
    val price: String,
    val imageRes: Int,
    val backgroundColor: Color? = null // Optional background color
)

// 🔹 Safe & reusable CategoryListGrid with dynamic text color
@Composable
fun CategoryListGridF(
    products: List<ProductListGrid>,
    columns: Int = 3,
    modifier: Modifier = Modifier,
    gridHeight: Dp? = null,
    showName: Boolean = true,
    showPrice: Boolean = true,
    defaultCardColor: Color = MaterialTheme.colorScheme.surface,
    imageHeight: Dp? = null,
    imageAspectRatio: Float = 1f,
    textColor: Color? = null, // <<< Dynamic text color
    onItemClick: (ProductListGrid) -> Unit = {}
) {
    val safeModifier = if (gridHeight != null) {
        modifier
            .fillMaxWidth()
            .height(gridHeight)
    } else {
        modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 1800.dp)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = safeModifier
            .padding(0.dp)
            .background(defaultCardColor),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            CategoryGridItem(
                product = product,
                showName = showName,
                showPrice = showPrice,
                defaultCardColor = defaultCardColor,
                imageHeight = imageHeight,
                imageAspectRatio = imageAspectRatio,
                textColor = textColor, // <<< Pass color
                onClick = { onItemClick(product) }
            )
        }
    }
}

// 🔹 Item card with dynamic text color
@Composable
fun CategoryGridItem(
    product: ProductListGrid,
    showName: Boolean = true,
    showPrice: Boolean = true,
    defaultCardColor: Color = MaterialTheme.colorScheme.surface,
    imageHeight: Dp? = null,
    imageAspectRatio: Float = 1f,
    textColor: Color? = null, // <<< Dynamic text color
    onClick: () -> Unit
) {
    val cardColor = product.backgroundColor ?: defaultCardColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(0.dp)
        ) {
            val imageModifier = if (imageHeight != null) {
                Modifier
                    .height(imageHeight)
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
                Spacer(modifier = Modifier.height(6.dp))
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
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // PRICE TEXT
            if (showPrice && product.price.isNotEmpty()) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor ?: MaterialTheme.typography.bodySmall.color
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (showName || showPrice) {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}


// Usage examples:
@Composable
fun ExampleUsage() {
    val sampleProducts = listOf(
        ProductListGrid(
            name = "Product 1",
            price = "$29.99",
            imageRes = R.drawable.popular_chain_1,
//            backgroundColor = Color(0xFFFFF8E1) // Light amber
        ),
        ProductListGrid(
            name = "Product 2",
            price = "$39.99",
            imageRes = R.drawable.popular_chain_2,
//            backgroundColor = Color(0xFFE8F5E8) // Light green
        ),
        ProductListGrid(
            name = "Product 3",
            price = "$49.99",
            imageRes = R.drawable.popular_chain_3,
//            backgroundColor = Color(0xFFE3F2FD) // Light blue
        ),
        ProductListGrid(
            name = "Product 4",
            price = "$19.99",
            imageRes = R.drawable.popular_chain_4
            // No background color - will use default
        ),
        ProductListGrid(
            name = "Product 4",
            price = "$19.99",
            imageRes = R.drawable.popular_chain_5
            // No background color - will use default
        ),
        ProductListGrid(
            name = "Product 4",
            price = "$19.99",
            imageRes = R.drawable.popular_chain_6
            // No background color - will use default
        )
    )

    // Example 1: Fixed image height (original behavior)
    CategoryListGridF(
        products = sampleProducts,
        columns = 3,
        gridHeight = 945.dp,
        showName = false,
        showPrice = true,
        imageHeight = 190.dp, // Fixed height
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    // Example 2: Dynamic height with 1:1 aspect ratio (square images)
    CategoryListGridF(
        products = sampleProducts,
        columns = 3,
        showName = true,
        showPrice = true,
        imageHeight = null, // Dynamic based on aspect ratio
        imageAspectRatio = 1f, // Square images
        defaultCardColor = Color(0xFFF5F5F5),
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    // Example 3: Dynamic height with 4:3 aspect ratio (wider images)
    CategoryListGridF(
        products = sampleProducts,
        columns = 3,
        showName = true,
        showPrice = true,
        imageHeight = null, // Dynamic based on aspect ratio
        imageAspectRatio = 4f / 3f, // 4:3 aspect ratio
        defaultCardColor = MaterialTheme.colorScheme.background,
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    // Example 4: Dynamic height with 3:4 aspect ratio (portrait images)
    CategoryListGridF(
        products = sampleProducts,
        columns = 2, // Fewer columns for portrait images
        showName = true,
        showPrice = true,
        imageHeight = null, // Dynamic based on aspect ratio
        imageAspectRatio = 3f / 4f, // Portrait orientation
        onItemClick = { product ->
            println("Clicked on ${product.name}")
        }
    )

    // Example 5: Mixed usage - some with fixed height, some with dynamic
    Column {
        // Fixed height section
        CategoryListGridF(
            products = sampleProducts.take(2),
            columns = 2,
            showName = true,
            showPrice = true,
            imageHeight = 120.dp, // Compact fixed height
            onItemClick = { product ->
                println("Clicked on ${product.name}")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dynamic height section
        CategoryListGridF(
            products = sampleProducts.takeLast(2),
            columns = 2,
            showName = true,
            showPrice = true,
            imageHeight = null, // Dynamic
            imageAspectRatio = 16f / 9f, // Wide banner-like images
            onItemClick = { product ->
                println("Clicked on ${product.name}")
            }
        )
    }
}