//package com.example.hufko.components.Account
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//
//@Composable
//fun AccountScreen(
//    navController: NavHostController,
//    onBackClick: () -> Unit
//) {
//    Account(
//        onBackClick = onBackClick, // This will trigger popBackStack
//        navController = navController,
//        onTabIndexChanged = { index ->
//            // Handle internal tab changes if Account has tabs
//        },
//        selectedCategoryIndex = 0
//    )
//}