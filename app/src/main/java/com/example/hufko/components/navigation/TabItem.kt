package com.example.hufko.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.components.Account.Account
import com.example.hufko.components.homescreen.HomeScreen

sealed class TabItem(
    val title: String,
    val icon: ImageVector,
    val imageResource: Int? = null,
    val screen: @Composable (NavHostController?) -> Unit,
    val onClick: ((NavHostController?) -> Unit)? = null // Add this
) {
    data object Home : TabItem(
        title = "Home",
        icon = Icons.Filled.Home,
        imageResource = R.drawable.outline_home_24,
        screen = { navController -> HomeScreen(navController) }
    )

//    data object Explore : TabItem(
//        title = "Account",
//        icon = Icons.Filled.Person,
//        imageResource = R.drawable.outline_person_24,
//        screen = { navController ->
//            // Instead of showing Account composable directly,
//            // navigate to the Account screen
//            navController?.navigate(Routes.ACCOUNT) {
//                // Optional navigation options
////                launchSingleTop = true
//            }
//
//            // You can return an empty composable or your previous screen
//            // Or keep showing the current screen while navigation happens
//            Box(modifier = Modifier.fillMaxSize())
//        }
//    )
    data object Explore : TabItem(
        title = "Account",
        icon = Icons.Filled.Person,
        imageResource = R.drawable.outline_person_24,
        screen = { navController ->
            Account(
                navController = navController,
                onBackClick = {
                    // Optional: If you want to navigate to Home tab when back is pressed
                    // This depends on your navigation structure
                }
            )
        }
    )
//    data object Explore : TabItem(
//        title = "Account",
//        icon = Icons.Filled.Person,
//        imageResource = R.drawable.outline_person_24,
//        screen = { navController -> ExploreScreen(navController) }  // Pass navController
//    )

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