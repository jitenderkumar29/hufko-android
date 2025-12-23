package com.example.hufko.components.navigation

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
import com.example.hufko.components.homescreen.CategoryScreen
import com.example.hufko.components.homescreen.CategoryTabsFList
import com.example.hufko.components.homescreen.CategoryTabsFood

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

        // Add other destinations as needed
    }
}