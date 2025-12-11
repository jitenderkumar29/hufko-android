package com.example.hufko.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hufko.R
import com.example.hufko.components.homescreen.HomeScreen

sealed class TabItem(
    val title: String,
    val icon: ImageVector,
    val imageResource: Int? = null,
    val screen: @Composable () -> Unit
) {
    data object Home : TabItem(
        title = "Home",
        icon = Icons.Filled.Home,
        imageResource = R.drawable.outline_home_24, // Add this image to your drawable resources
        screen = {
            HomeScreen()
        }
    )

    data object Explore : TabItem(
        title = "Account",
        icon = Icons.Filled.Person,
        imageResource = R.drawable.outline_person_24, // Add this image to your drawable resources
        screen = { ExploreScreen() }
    )


    data object Profile : TabItem(
        title = "Pay Bill",
        icon = Icons.Filled.ShoppingCart,
        imageResource = R.drawable.pay_bill, // Add this image to your drawable resources
        screen = { ProfileScreen() }
    )

    data object Favorites : TabItem(
        title = "Prime",
        icon = Icons.Filled.Star,
        imageResource = R.drawable.outline_card_membership_24, // Add this image to your drawable resources
        screen = { FavoritesScreen() }
    )

    data object Settings : TabItem(
        title = "Cart",
        icon = Icons.Filled.Menu,
        imageResource = R.drawable.outline_shopping_cart_24, // Add this image to your drawable resources
        screen = { SettingsScreen() }
    )

//    data object Notifications : TabItem(
//        title = "Alert",
//        icon = Icons.Filled.Notifications,
//        imageResource = R.drawable.outline_notifications_24, // Add this image to your drawable resources
//        screen = { NotificationsScreen() }
//    )
}