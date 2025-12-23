package com.example.hufko.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.components.homescreen.HomeScreen

sealed class TabItem(
    val title: String,
    val icon: ImageVector,
    val imageResource: Int? = null,
    val screen: @Composable (NavHostController?) -> Unit
) {
    data object Home : TabItem(
        title = "Home",
        icon = Icons.Filled.Home,
        imageResource = R.drawable.outline_home_24,
        screen = { navController -> HomeScreen(navController) }
    )

    data object Explore : TabItem(
        title = "Account",
        icon = Icons.Filled.Person,
        imageResource = R.drawable.outline_person_24,
        screen = { navController -> ExploreScreen(navController) }  // Pass navController
    )

    data object Profile : TabItem(
        title = "Pay Bill",
        icon = Icons.Filled.ShoppingCart,
        imageResource = R.drawable.pay_bill,
        screen = { navController -> ProfileScreen(navController) }  // Pass navController
    )

    data object Favorites : TabItem(
        title = "Prime",
        icon = Icons.Filled.Star,
        imageResource = R.drawable.outline_card_membership_24,
        screen = { navController -> FavoritesScreen(navController) }  // Pass navController
    )

    data object Settings : TabItem(
        title = "Cart",
        icon = Icons.Filled.Menu,
        imageResource = R.drawable.outline_shopping_cart_24,
        screen = { navController -> SettingsScreen(navController) }  // Pass navController
    )
}