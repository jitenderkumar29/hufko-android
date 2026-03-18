

package com.example.hufko.components.GroceryHome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

// Sealed class for different grocery category pages
sealed class GroceryCategoryPage(val title: String, val iconRes: Int) {
    // Main categories from the images
    object TopPicks : GroceryCategoryPage("Top Picks", R.drawable.top_picks)
    object MasalaSugarSpices : GroceryCategoryPage("Masala, sugar & spices", R.drawable.masala_spices)
    object OilGhee : GroceryCategoryPage("Oil & ghee", R.drawable.oil_ghee)
    object NoodlesPasta : GroceryCategoryPage("Noodles & pasta", R.drawable.noodles_pasta)
    object DalPulses : GroceryCategoryPage("Dal & pulses", R.drawable.dal_pulses)
    object AttaRiceGrains : GroceryCategoryPage("Atta, rice & grains", R.drawable.atta_rice)
    object ChipsBiscuits : GroceryCategoryPage("Chips & biscuits", R.drawable.chips_biscuits)
    object JuicesColdDrinks : GroceryCategoryPage("Juices & cold drinks", R.drawable.juices_drinks)
    object DryFruitsNuts : GroceryCategoryPage("Dry fruits & nuts", R.drawable.dry_fruits)
    object OtherCategories : GroceryCategoryPage("Other Categories", R.drawable.other_categories)
}

// Data class for grocery items
data class GroceryItem(
    val id: Int,
    val name: String,
    val description: String? = null,
    val imageRes: Int,
    val price: String,
    val weight: String,
    val isVeg: Boolean = true,
    val category: String = "Top Picks"
)

@Composable
fun GroceryTabs(
    navController: NavHostController? = null,
    selectedTabIndex: Int,
    onCategorySelected: (GroceryCategoryPage) -> Unit = {},
    onTabIndexChanged: (Int) -> Unit = {}
) {
    // Get current value
    var currentSelectedIndex by rememberSaveable {
        mutableIntStateOf(
            navController?.currentBackStackEntry?.savedStateHandle?.get<Int>("currentSelectedIndex")
                ?: selectedTabIndex
        )
    }

    // Save changes back to savedStateHandle
    LaunchedEffect(currentSelectedIndex) {
        navController?.currentBackStackEntry?.savedStateHandle?.set(
            "currentSelectedIndex",
            currentSelectedIndex
        )
    }

    Log.d("GroceryTabs", "currentSelectedIndex in GroceryTabs: $currentSelectedIndex")

    val savedGroceryTabIndex = remember(navController?.currentBackStackEntry) {
        navController?.currentBackStackEntry?.savedStateHandle?.get<Int>("groceryTabIndex")
            ?: 0
    }

    // Update when parent state changes
    LaunchedEffect(selectedTabIndex) {
        currentSelectedIndex = selectedTabIndex
    }

    // All grocery category pages from the images (all tabs displayed)
    val allCategoryPages = listOf(
        GroceryCategoryPage.TopPicks,
        GroceryCategoryPage.MasalaSugarSpices,
        GroceryCategoryPage.OilGhee,
        GroceryCategoryPage.NoodlesPasta,
        GroceryCategoryPage.DalPulses,
        GroceryCategoryPage.AttaRiceGrains,
        GroceryCategoryPage.ChipsBiscuits,
        GroceryCategoryPage.JuicesColdDrinks,
        GroceryCategoryPage.DryFruitsNuts,
        GroceryCategoryPage.OtherCategories
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .background(MaterialTheme.customColors.skyBlue)
    ) {
        ScrollableTabRow(
            selectedTabIndex = currentSelectedIndex,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[currentSelectedIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.header
                )
            }
        ) {
            allCategoryPages.forEachIndexed { index, categoryPage ->
                val isSelected = currentSelectedIndex == index

                Tab(
                    selected = isSelected,
                    onClick = {
                        currentSelectedIndex = index
                        onTabIndexChanged(index)
                        onCategorySelected(categoryPage)
                    },
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .background(Color.Transparent)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 0.dp)
                    ) {
                        Image(
                            painter = painterResource(id = categoryPage.iconRes),
                            contentDescription = categoryPage.title,
                            modifier = Modifier
                                .width(75.dp)
                                .height(85.dp),
                            contentScale = ContentScale.FillBounds
                        )

//                        Text(
//                            text = categoryPage.title,
//                            fontSize = 13.sp,
//                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
//                            color = if (isSelected) {
//                                MaterialTheme.customColors.header
//                            } else {
//                                MaterialTheme.customColors.black
//                            },
//                            maxLines = 2,
//                            textAlign = TextAlign.Center,
//                            overflow = TextOverflow.Ellipsis,
//                            modifier = Modifier.padding(top = 4.dp)
//                        )
                    }
                }
            }
        }

        // Show content for each tab
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFEDF6FF),
                            Color(0xFFFDFEFF)
                        )
                    )
                )
        ) {
            // Get the actual category based on currentSelectedIndex
            val actualCategory = allCategoryPages.getOrNull(currentSelectedIndex)

            // Show content based on the actual category
            when (actualCategory) {
                GroceryCategoryPage.TopPicks -> TopPicksCategoryPage()
                GroceryCategoryPage.MasalaSugarSpices -> MasalaSugarSpicesCategoryPage()
                GroceryCategoryPage.OilGhee -> OilGheeCategoryPage()
                GroceryCategoryPage.NoodlesPasta -> NoodlesPastaCategoryPage()
                GroceryCategoryPage.DalPulses -> DalPulsesCategoryPage()
                GroceryCategoryPage.AttaRiceGrains -> AttaRiceGrainsCategoryPage()
                GroceryCategoryPage.ChipsBiscuits -> ChipsBiscuitsCategoryPage()
                GroceryCategoryPage.JuicesColdDrinks -> JuicesColdDrinksCategoryPage()
                GroceryCategoryPage.DryFruitsNuts -> DryFruitsNutsCategoryPage()
                GroceryCategoryPage.OtherCategories -> OtherCategoriesPage()
                null -> {
                    // Show empty state for invalid index
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Select a category",
                            color = MaterialTheme.customColors.gray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

// Sample content for each category page
@Composable
fun TopPicksCategoryPage() {
    val sampleItems = listOf(
        GroceryItemTwo(
            id = 1,
            discount = "12% OFF",
            title = "Fortune Sunlite Sunflower Oil",
            weight = "800 g",
            discountedPrice = "₹163",
            originalPrice = "₹185",
            imageRes = R.drawable.fortune_sunlite_sunflower_oil
        ),
        GroceryItemTwo(
            id = 2,
            discount = "20% OFF",
            title = "Fortune Premium Kachi Ghani Pure Mustard Oil",
            weight = "1 L",
            discountedPrice = "₹168",
            originalPrice = "₹210",
            imageRes = R.drawable.fortune_premium_kachi_ghani_mustard_oil
        ),
        GroceryItemTwo(
            id = 3,
            discount = "18% OFF",
            title = "Fortune Premium Kachi Ghani Pure Mustard Oil",
            weight = "1 L",
            discountedPrice = "₹176",
            originalPrice = "₹215",
            imageRes = R.drawable.fortune_premium_kachi_ghani_mustard_oil_1
        ),
        GroceryItemTwo(
            id = 4,
            discount = "22% OFF",
            title = "Exo Anti-Bacterial Diswash Bar",
            weight = "Pack of 4, 120 g",
            discountedPrice = "₹30",
            originalPrice = "",
            imageRes = R.drawable.exo_antibacterial_dishwash_bar
        ),
        GroceryItemTwo(
            id = 5,
            discount = "",
            title = "Parle Platina Hide & Seek Cookies",
            weight = "200 g",
            discountedPrice = "₹42",
            originalPrice = "₹54",
            imageRes = R.drawable.parle_platina_hide_seek_cookies
        ),
        GroceryItemTwo(
            id = 6,
            discount = "12% OFF",
            title = "Fortune Sunlite Sunflower Oil Jar",
            weight = "4.35 kg",
            discountedPrice = "₹929",
            originalPrice = "₹1,055",
            imageRes = R.drawable.fortune_sunlite_sunflower_oil_jar_1
        ),
        GroceryItemTwo(
            id = 7,
            discount = "18% OFF",
            title = "Maggi 2-Minute Masala Instant Noodles",
            weight = "Pack of 12, 70 g",
            discountedPrice = "₹148",
            originalPrice = "₹180",
            imageRes = R.drawable.maggi_masala_instant_noodles_pack
        ),
        GroceryItemTwo(
            id = 8,
            discount = "34% OFF",
            title = "Tata Sampann Toor Dal/Arhar Dal",
            weight = "1 kg",
            discountedPrice = "₹162",
            originalPrice = "₹244",
            imageRes = R.drawable.tata_sampann_toor_dal_1
        ),
        GroceryItemTwo(
            id = 9,
            discount = "27% OFF",
            title = "Vedaka Raw Peanuts",
            weight = "1 kg",
            discountedPrice = "₹219",
            originalPrice = "₹300",
            imageRes = R.drawable.vedaka_raw_peanuts
        ),
        GroceryItemTwo(
            id = 10,
            discount = "18% OFF",
            title = "Saffola Active Multi-Source Oil",
            weight = "850 g",
            discountedPrice = "₹153",
            originalPrice = "₹187",
            imageRes = R.drawable.saffola_active_multisource_oil
        ),
//        GroceryItemTwo(
//            id = 11,
//            discount = "10% OFF",
//            title = "Fortune Soya Health Soyabean Oil",
//            weight = "870 g",
//            discountedPrice = "₹195",
//            originalPrice = "",
//            imageRes = R.drawable.fortune_soya_health_soyabean_oil
//        ),
//        GroceryItemTwo(
//            id = 12,
//            discount = "",
//            title = "Comfort Morning Fresh Fabric Conditioner",
//            weight = "2 L",
//            discountedPrice = "₹390",
//            originalPrice = "₹435",
//            imageRes = R.drawable.comfort_morning_fresh_fabric_conditioner
//        ),
//        GroceryItemTwo(
//            id = 13,
//            discount = "23% OFF",
//            title = "Fortune Sugar",
//            weight = "1 kg",
//            discountedPrice = "₹58",
//            originalPrice = "₹75",
//            imageRes = R.drawable.fortune_sugar
//        ),
//        GroceryItemTwo(
//            id = 14,
//            discount = "",
//            title = "Vim Dishwash Bar",
//            weight = "Pack of 1, (4 Pcs)",
//            discountedPrice = "₹93",
//            originalPrice = "",
//            imageRes = R.drawable.vim_dishwash_bar
//        ),
//        GroceryItemTwo(
//            id = 15,
//            discount = "",
//            title = "Aashirvaad Shuddh Chakki Atta",
//            weight = "5 kg",
//            discountedPrice = "₹254",
//            originalPrice = "₹262",
//            imageRes = R.drawable.aashirvaad_shuddh_chakki_atta
//        ),
//        GroceryItemTwo(
//            id = 16,
//            discount = "35% OFF",
//            title = "Britannia Nutri Choice Digestive Biscuits",
//            weight = "1 kg",
//            discountedPrice = "₹129",
//            originalPrice = "₹190",
//            imageRes = R.drawable.britannia_nutri_choice_digestive
//        ),
//        GroceryItemTwo(
//            id = 17,
//            discount = "7% OFF",
//            title = "Maggi 2-Minute Masala Instant Noodles",
//            weight = "70 g",
//            discountedPrice = "₹14",
//            originalPrice = "₹16",
//            imageRes = R.drawable.maggi_masala_instant_noodles
//        ),
//        GroceryItemTwo(
//            id = 18,
//            discount = "10% OFF",
//            title = "Emami Healthy & Tasty Rice Bran Oil",
//            weight = "825 g",
//            discountedPrice = "₹135",
//            originalPrice = "₹150",
//            imageRes = R.drawable.emami_healthy_tasty_rice_bran_oil
//        ),
//        GroceryItemTwo(
//            id = 19,
//            discount = "23% OFF",
//            title = "Fortune Rice Bran Health Oil Pouch",
//            weight = "870 g",
//            discountedPrice = "₹153",
//            originalPrice = "₹199",
//            imageRes = R.drawable.fortune_rice_bran_health_oil_pouch
//        ),
//        GroceryItemTwo(
//            id = 20,
//            discount = "28% OFF",
//            title = "Tata Sampann Yellow Moong Dal",
//            weight = "1 kg",
//            discountedPrice = "₹154",
//            originalPrice = "₹214",
//            imageRes = R.drawable.tata_sampann_yellow_moong_dal
//        ),
//        GroceryItemTwo(
//            id = 21,
//            discount = "21% OFF",
//            title = "Tata Sampann Green Moong Dal",
//            weight = "500 g",
//            discountedPrice = "₹74",
//            originalPrice = "₹94",
//            imageRes = R.drawable.tata_sampann_green_moong_dal
//        ),
//        GroceryItemTwo(
//            id = 22,
//            discount = "62% OFF",
//            title = "Vedaka Cumini (White Jeera)",
//            weight = "100 g",
//            discountedPrice = "₹59",
//            originalPrice = "₹155",
//            imageRes = R.drawable.vedaka_cumini_white_jeera
//        ),
//        GroceryItemTwo(
//            id = 23,
//            discount = "37% OFF",
//            title = "Britannia Classic Little Hearts",
//            weight = "70 g",
//            discountedPrice = "₹19",
//            originalPrice = "₹30",
//            imageRes = R.drawable.britannia_classic_little_hearts
//        ),
//        GroceryItemTwo(
//            id = 24,
//            discount = "17% OFF",
//            title = "Uttam Sugar Sulphurfree Buxar",
//            weight = "1 kg",
//            discountedPrice = "₹54",
//            originalPrice = "₹65",
//            imageRes = R.drawable.uttam_sugar_sulphurfree
//        ),
//        GroceryItemTwo(
//            id = 25,
//            discount = "70% OFF",
//            title = "Vedaka Cumini (Jeera)",
//            weight = "200 g",
//            discountedPrice = "₹91",
//            originalPrice = "₹360",
//            imageRes = R.drawable.vedaka_cumini_jeera
//        ),
//        GroceryItemTwo(
//            id = 26,
//            discount = "30% OFF",
//            title = "Aashirvaad Iodized Salt",
//            weight = "1 kg",
//            discountedPrice = "₹21",
//            originalPrice = "₹36",
//            imageRes = R.drawable.aashirvaad_iodized_salt
//        ),
//        GroceryItemTwo(
//            id = 27,
//            discount = "16% OFF",
//            title = "Aashirvaad Select Sharbati Atta",
//            weight = "5 kg",
//            discountedPrice = "₹273",
//            originalPrice = "₹325",
//            imageRes = R.drawable.aashirvaad_select_sharbati_atta
//        ),
//        GroceryItemTwo(
//            id = 28,
//            discount = "",
//            title = "Surf Excel Detergent Bar",
//            weight = "Pack Of 4, 200 g",
//            discountedPrice = "₹128",
//            originalPrice = "",
//            imageRes = R.drawable.surf_excel_detergent_bar
//        ),
//        GroceryItemTwo(
//            id = 29,
//            discount = "21% OFF",
//            title = "Dettol Skincare Liquid Handwash",
//            weight = "1.35 L",
//            discountedPrice = "₹174",
//            originalPrice = "₹219",
//            imageRes = R.drawable.dettol_skincare_liquid_handwash
//        )
    )

    MaterialTheme {
        GroceryItemsHorizontal(items = sampleItems)
    }
}

@Composable
fun MasalaSugarSpicesCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Masala Sugar Spices Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun OilGheeCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Oil Ghee Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun NoodlesPastaCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = " Noodles Pasta Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun DalPulsesCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = " Dal Pulses Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun AttaRiceGrainsCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Atta Rice Grains Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun ChipsBiscuitsCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Chips Biscuits Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun JuicesColdDrinksCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Juices Cold Drinks Category",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun DryFruitsNutsCategoryPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Dry Fruits Nuts",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}

@Composable
fun OtherCategoriesPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Other Categories",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp)
        )
    }
}
