package com.example.hufko.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hufko.components.homescreen.CategoryScreen
import com.example.hufko.components.homescreen.CategoryTabsFList
import com.example.hufko.components.homescreen.CategoryTabsFood
import com.example.hufko.components.homescreen.MainScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // ✅ Home category screen
//        composable("category_screen") {
//            MainScreen(navController)
//        }

        composable("home") {
            CategoryScreen(
                navController,
                onOpenFashion = {
                    navController.navigate("fashion_screen")
                }
            )
        }

        composable("category_tabs_f_list") {
            CategoryTabsFList()
        }

    }
}
