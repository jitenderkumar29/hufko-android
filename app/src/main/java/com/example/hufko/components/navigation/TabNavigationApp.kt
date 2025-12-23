package com.example.hufko.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hufko.ui.theme.customColors

@Composable
fun TabNavigationApp(navController: NavHostController? = null) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    val tabItems = listOf(
        TabItem.Home,
        TabItem.Explore,
        TabItem.Profile,
        TabItem.Favorites,
        TabItem.Settings,
    )

    // Find the Pay Bill tab index
    val payBillTabIndex = tabItems.indexOfFirst { it.title == "Pay Bill" }
    val hasPayBillTab = payBillTabIndex != -1

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Scaffold(
            containerColor = MaterialTheme.customColors.footer,
            bottomBar = {
                Column(
                    modifier = Modifier
                        .windowInsetsPadding(
                            WindowInsets.navigationBars.only(WindowInsetsSides.Bottom)
                        )
                ) {
                    // 🔶 Top border bar (active tab indicator)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                    ) {
                        tabItems.forEachIndexed { index, _ ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 4.dp,
                                            bottomEnd = 4.dp
                                        )
                                    )
                                    .background(
                                        if (selectedTabIndex == index)
                                            MaterialTheme.customColors.lightAccent
                                        else
                                            MaterialTheme.customColors.footer
                                    )
                            )
                        }
                    }

                    // Bottom navigation bar
                    NavigationBar(
                        containerColor = MaterialTheme.customColors.footer,
                        modifier = Modifier
                            .height(65.dp)
                            .padding(horizontal = 4.dp)
                            .navigationBarsPadding()
                    ) {
                        tabItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 0.dp),
                                icon = {
                                    // Regular icons for non-PayBill items
                                    if (item.title != "Pay Bill") {
                                        if (item.imageResource != null) {
                                            Image(
                                                painter = painterResource(id = item.imageResource),
                                                contentDescription = item.title,
                                                modifier = Modifier.size(28.dp),
                                                colorFilter = ColorFilter.tint(
                                                    if (selectedTabIndex == index)
                                                        MaterialTheme.customColors.white
                                                    else
                                                        MaterialTheme.customColors.white
                                                )
                                            )
                                        } else {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = item.title,
                                                modifier = Modifier.size(28.dp),
                                                tint = if (selectedTabIndex == index)
                                                    MaterialTheme.customColors.white
                                                else
                                                    MaterialTheme.customColors.white
                                            )
                                        }
                                    } else {
                                        // Empty space for Pay Bill item (the actual icon will be overlaid)
                                        Box(modifier = Modifier.size(28.dp))
                                    }
                                },
                                label = {
                                    if (item.title != "Pay Bill") {
                                        Text(
                                            text = item.title,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            color = if (selectedTabIndex == index)
                                                MaterialTheme.customColors.white
                                            else
                                                MaterialTheme.customColors.white
                                        )
                                    } else {
                                        // Empty text for Pay Bill to maintain layout
                                        Text(
                                            text = "",
                                            fontSize = 11.sp
                                        )
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.customColors.lightAccent,
                                    selectedTextColor = MaterialTheme.customColors.lightAccent,
                                    unselectedIconColor = MaterialTheme.customColors.gray,
                                    unselectedTextColor = MaterialTheme.customColors.gray,
                                    indicatorColor = MaterialTheme.customColors.lightAccent
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.customColors.white)
                    .fillMaxWidth()
                    .fillMaxSize()
            ) {
                // Pass navController to ALL screens
                tabItems[selectedTabIndex].screen(navController)
            }
        }

        // Overlay the Pay Bill image above everything
        if (hasPayBillTab) {
            val payBillItem = tabItems[payBillTabIndex]
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(bottom = 5.dp) // Adjust this value to position above the nav bar
            ) {
                Image(
                    painter = painterResource(id = payBillItem.imageResource!!),
                    contentDescription = payBillItem.title,
                    modifier = Modifier
                        .size(75.dp) // Slightly larger than before
                        .clickable { selectedTabIndex = payBillTabIndex }
                )
            }
        }
    }
}