package com.example.hufko.components.Account

import android.provider.CalendarContract
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import  com.example.hufko.R
import com.example.hufko.ui.theme.customColors

data class AccountOption(
    val text: String,
    val iconResId: Int,
    val isCheckbox: Boolean = false,
    val isChecked: Boolean = false
)
@Composable
fun Account(
    onBackClick: () -> Unit,
    navController: NavHostController?,
//    onTabIndexChanged: (Int) -> Unit,
//    selectedCategoryIndex: Int = 0
) {
    // Set the value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // Header - sticky item
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEEE6))
//                    .background(MaterialTheme.customColors.header)
//                    .padding(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_back),
//                        contentDescription = "Back",
//                        modifier = Modifier
//                            .size(28.dp)
//                            .clickable {
//                                onBackClick()
//                            }
//                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "My Account",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Spacer(modifier = Modifier.width(12.dp))

                    // Cart
//                    IconCircle {
                        Image(
                            painter = painterResource(id = R.drawable.ic_help),  // Use actual resource ID
                            contentDescription = "Help icon",
                            modifier = Modifier.size(65.dp),
                            contentScale = ContentScale.Fit
                        )
//                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // More options
                    IconCircle {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_more_vert_24),
                            contentDescription = "More options",
                        )
                    }
                }
            }
        }

        // User Profile Section
        item {
            UserProfileSection()
        }

        // Membership Section
        item {
            MembershipSection()
        }

        // Account Info with items
        item {
            val accountItems = listOf(
                AccountItem("Saved\nAddress", R.drawable.ic_address),
                AccountItem("Payment\nModes", R.drawable.ic_payment),
                AccountItem("My\nRefunds", R.drawable.ic_refunds),
                AccountItem("Hufko\nMoney", R.drawable.ic_money)
            )

            AccountInfo(
                items = accountItems,
                config = AccountInfoConfig(
                    itemsPerRow = 4,
                    showSingleItemCentered = false,
                    itemSpacing = 8.dp,
                    padding = 8.dp,
                    imageSize = 40.dp,
                    textSpacing = 8.dp,
                    fontSize = 12
                ),
                onItemClick = { item ->
                    println("Clicked: ${item.title}")
                }
            )
        }

        // Account Options Section
        item {
            AccountOptionsSection()
        }

        // Past Orders Section
        item {
            PastOrdersSection()
        }
    }
}

@Composable
private fun UserProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFEEE6), // Add your color here
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 25.dp,
                    bottomStart = 25.dp
                )
        )
            .padding(16.dp)
    ) {
        Text(
            text = "Jitender Kumar",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "+91 - 7042341856",
            fontSize = 14.sp,
            color = Color.Black
        )

//        Spacer(modifier = Modifier.height(16.dp))

//        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Composable
private fun MembershipSection() {
    var isMembershipExpanded by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(12.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .border(
                width = 1.dp,
                color = Color.Gray, // Or any color you want
                shape = RoundedCornerShape(20.dp) // Optional rounded corners
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Header row with toggle button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // "One" badge
            Box(
                modifier = Modifier
//                    .background(Color.Black)
//                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_one),
                    contentDescription = "One", // Keep descriptive for accessibility
                    modifier = Modifier
                        .size(70.dp) // Adjust size to match your Text size
                        .align(Alignment.Center), // Center the image in the Box
//                    colorFilter = ColorFilter.tint(Color.White) // Optional: make icon white
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            // Join Now
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)) // Adjust corner radius as needed
                    .background(Color.Red)
                    .padding(horizontal = 12.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "JOIN NOW",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Toggle button (up/down arrow)
            Icon(
                imageVector = if (isMembershipExpanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = if (isMembershipExpanded)
                    "Collapse membership options"
                else
                    "Expand membership options",
                modifier = Modifier
                    .size(34.dp)
                    .clickable { isMembershipExpanded = !isMembershipExpanded },
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

//        // GENERAL label
//        Text(
//            text = "GENERAL",
//            fontSize = 10.sp,
//            color = Color.Gray,
//            fontWeight = FontWeight.Medium
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))

        // Title and description
        Text(
            text = "Unlimited free deliveries, extra discounts & more!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Join now to unlock exclusive benefits",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Membership options (expandable/collapsible)
        AnimatedVisibility(
            visible = isMembershipExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .clickable { /* Handle click */ }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_loading),
                        contentDescription = "loading",
                        modifier = Modifier.size(30.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Join Hufko One",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f) // ← This pushes the icon to the right
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
                        contentDescription = "Keyboard Arrow Forward",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                }

//                Redeem Membership Coupon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .clickable { /* Handle click */ }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_loading_redeem),
                        contentDescription = "Redeem",
                        modifier = Modifier.size(30.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Redeem Membership Coupon",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f) // ← This pushes the icon to the right
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
                        contentDescription = "Keyboard Arrow Forward",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                }

            }
        }
    }
}

@Composable
private fun AccountOptionsSection() {
    val accountOptions = listOf(
        AccountOption("Hufko HDFC Bank Credit Card", R.drawable.ic_credit_card_1, false),
        AccountOption("My Vouchers", R.drawable.ic_local_offer, false),
        AccountOption("Account Statements", R.drawable.ic_description, false),
        AccountOption("Order Food on Train", R.drawable.ic_train, false),
        AccountOption("Corporate Rewards", R.drawable.ic_business, false),
        AccountOption("Student Rewards", R.drawable.ic_school, false),
        AccountOption("My Instamart Wishlist", R.drawable.ic_favorite, false),
        AccountOption("Favourites", R.drawable.ic_star, false),
        AccountOption("Partner Rewards", R.drawable.ic_card_giftcard, false),
        AccountOption("Allow restaurants to contact you", R.drawable.ic_notifications, false)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray, // Or any color you want
                shape = RoundedCornerShape(20.dp) // Optional rounded corners
            )
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        accountOptions.forEach { option ->
            AccountItem(
                text = option.text,
                iconResId = option.iconResId,
                isCheckbox = option.isCheckbox,
                isChecked = option.isChecked
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun PastOrdersSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "PAST ORDERS",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // If you have an image resource, use this:
                 Image(
                     painter = painterResource(id = R.drawable.hufko_empty_state),
                     contentDescription = "No orders",
                     modifier = Modifier.size(120.dp)
                 )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "You haven't Hufko'd yet",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "From food to groceries and dining\n" +
                            "experiences - all in one place!",
                    fontSize = 14.sp,
                    color = Color.Black,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { /* Handle explore action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.customColors.orangeButton, // Hufko orange color
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(48.dp)
                        .width(200.dp)
                ) {
                    Text(
                        text = "Explore Hufko",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "App version A990 (1883)",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun AccountItem(
    text: String,
    iconResId: Int,
    isCheckbox: Boolean = false,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable {
                if (isCheckbox) {
                    onCheckedChange(!isChecked)
                } else {
                    // Handle regular item click
                }
            }
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))


        if (isCheckbox) {
            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.outline_arrow_forward_ios_24),
                contentDescription = "Arrow",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
//    Divider(color = Color.LightGray, thickness = 1.dp)
}


@Composable
private fun IconCircle(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

// Optional: Custom colors if needed
object CustomColors {
    val header = Color(0xFFFC8019) // Hufko orange color
    val white = Color.White
    val black = Color.Black
}

// For MaterialTheme extension
val CalendarContract.Colors.header: Color
    get() = CustomColors.header