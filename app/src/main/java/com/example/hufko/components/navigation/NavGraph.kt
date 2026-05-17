package com.example.hufko.components.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hufko.components.Account.Account
import com.example.hufko.components.homescreen.CategoryDietTabsFList
import com.example.hufko.components.homescreen.CategoryScreen
import com.example.hufko.components.homescreen.CategoryTabsFList
import com.example.hufko.components.homescreen.CategoryTabsFullListScreen
import com.example.hufko.components.homescreen.RestaurantDetails
import com.example.hufko.components.homescreen.TopRatedRestaurantItem
import com.example.hufko.components.homescreen.completeRestaurantItems

// Define navigation routes in your app
object Routes {
    const val HOME = "home"
    const val ACCOUNT = "account"
    // Add other routes as needed
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    // Shared state for selected tab index
    var sharedSelectedIndex by remember { mutableStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            CategoryScreen(
                navController = navController,
                onOpenFashion = {
                    navController.navigate("fashion_screen")
                },
//                initialSelectedTabIndex = sharedSelectedIndex,
//                onTabIndexChanged = { index ->
//                    sharedSelectedIndex = index
//                }
            )
        }

        composable(
            "category_tabs_f_list/{selectedIndex}",
            arguments = listOf(navArgument("selectedIndex") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) { backStackEntry ->
            val selectedIndex = backStackEntry.arguments?.getInt("selectedIndex") ?: 0

            CategoryTabsFList(
                onBackClick = {
                    // Pop back to home
                    navController.popBackStack()
                },
                onTabIndexChanged = { newIndex ->
                    // 🔴 CRITICAL: Save the updated index to SavedStateHandle
                    // This will be read by HomeScreen when we navigate back
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("updatedTabIndex", newIndex)
                },
                name = "Food Categories",
                initialSelectedIndex = selectedIndex
            )
        }

        // In your NavGraph setup, update the route:
        composable(
            "category_diet_tabs_list/{selectedDietIndex}",
            arguments = listOf(navArgument("selectedDietIndex") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) { backStackEntry ->
            val selectedDietIndex = backStackEntry.arguments?.getInt("selectedDietIndex") ?: 0

            CategoryDietTabsFList(
                navController = navController,
                onBackClick = {
                    navController.popBackStack()
                },
                onTabIndexChanged = { newIndex ->
                    // Save to both current and previous back stack entries
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedDietTabFromSeeAll", newIndex)

                    // Also save to previous entry for redundancy
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("selectedDietTabFromSeeAll", newIndex)

                    Log.d("Navigation", "Saved selected index: $newIndex")
                },
                name = "All Food Categories",
                initialSelectedIndex = selectedDietIndex
            )
        }

//        Account
        composable(Routes.ACCOUNT) {
            Account(
                navController = navController,
                onBackClick = {
                    navController.popBackStack()
                },
            )
//            AccountScreen(
//                navController = navController,
//                onBackClick = { navController.navigateUp() }
//            )
        }

        // Add this to your navigation graph:
// composable("category_tabs_f_list_screen") {
//     CategoryTabsFullListScreen(
//         navController = navController,
//         onCategorySelected = { category, index ->
//             // Handle category selection from "See All" page
//         }
//     )
// }

        // In your NavHost setup:
        composable("category_tabs_f_list_screen") {
            CategoryTabsFullListScreen(
                navController = navController,
                onCategorySelected = { category, index ->
                    // This will be called when a category is selected from "See All" page
                    // The index will be passed back to CategoryTabsFood via savedStateHandle
                    Log.d("Navigation", "Selected category from See All: ${category.title} at index $index")
                }
            )
        }

        composable(
            "restaurant_details/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId")
            // Find the restaurant item by ID from your data source
            val restaurantItem = findRestaurantById(restaurantId)

            RestaurantDetails(
                onBackClick = { navController.popBackStack() },
                navController = navController,
                restaurantItem = restaurantItem
            )
        }

        // Add other destinations as needed
    }
}

// Helper function to find restaurant by ID
fun findRestaurantById(id: Int?): TopRatedRestaurantItem? {
    return completeRestaurantItems.find { it.id == id }
}