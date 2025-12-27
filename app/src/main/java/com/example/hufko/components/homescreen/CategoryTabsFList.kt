package com.example.hufko.components.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors

@Composable
fun CategoryTabsFList(
    onBackClick: () -> Unit = {},
    onTabIndexChanged: (Int) -> Unit = {},
    name: String = "All Food Categories",
    initialSelectedIndex: Int = 0
) {
    var selectedCategoryIndex by remember { mutableStateOf(initialSelectedIndex) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColors.white)
    ) {

        // ---------- HEADER ----------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.customColors.header)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            onTabIndexChanged(selectedCategoryIndex)
                            onBackClick()
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.customColors.white
                )

                Spacer(modifier = Modifier.weight(1f))

                // Search
                IconCircle {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.customColors.black
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Cart
                IconCircle {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Cart",
                        tint = MaterialTheme.customColors.black
                    )
                }
            }
        }

        WelcomeContent(
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = { index ->
                selectedCategoryIndex = index
                onTabIndexChanged(index)
                onBackClick()
            }
        )
    }
}

@Composable
fun IconCircle(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .shadow(2.dp, CircleShape, clip = true)
            .clip(CircleShape)
            .background(Color.White)
            .border(
                1.dp,
                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun WelcomeContent(
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Cuisines and dishes",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.customColors.black
        )

        Spacer(modifier = Modifier.height(16.dp))

        FoodCategoriesGrid(
            selectedCategoryIndex = selectedCategoryIndex,
            onCategorySelected = onCategorySelected
        )
    }
}

@Composable
fun FoodCategoriesGrid(
    selectedCategoryIndex: Int = 0,
    onCategorySelected: (Int) -> Unit = {}
) {

    val categories = listOf(
        FoodCategory("Diet", R.drawable.diet_food),
        FoodCategory("Pizzas", R.drawable.pizzas_food),
        FoodCategory("Cakes", R.drawable.cakes_food),
        FoodCategory("Momos", R.drawable.momos_food),
        FoodCategory("Rolls", R.drawable.rolls_food),
        FoodCategory("Burgers", R.drawable.burgers_food),
        FoodCategory("Chole Bhature", R.drawable.chole_bhature_food),
        FoodCategory("Salad", R.drawable.salad_food),
        FoodCategory("Patty", R.drawable.patty_food),
        FoodCategory("Chinese", R.drawable.chinese_food),
        FoodCategory("Ice Cream", R.drawable.ice_cream_food),
        FoodCategory("Appam", R.drawable.appam_food),
        FoodCategory("Bath", R.drawable.bath_food),
        FoodCategory("Bonda", R.drawable.bonda_food),
        FoodCategory("Cutlet", R.drawable.cutlet_food),
        FoodCategory("Dessert", R.drawable.dessert_food),
        FoodCategory("Dhokla", R.drawable.dhokla_food),
        FoodCategory("Dosa", R.drawable.dosa_food),
        FoodCategory("Dholda", R.drawable.dholda_food),
        FoodCategory("Gulab Jamun", R.drawable.gulab_jamun_food),
        FoodCategory("Idli", R.drawable.idli_food),
        FoodCategory("Biryani", R.drawable.biryani_food),
        FoodCategory("Thali", R.drawable.thali_food),
        FoodCategory("Chicken", R.drawable.chicken_food),
        FoodCategory("Veg Meal", R.drawable.veg_meal_food),
        FoodCategory("North Indian", R.drawable.north_indian_food),
        FoodCategory("Paneer", R.drawable.paneer_food),
        FoodCategory("Fried Rice", R.drawable.fried_rice_food),
        FoodCategory("Noodles", R.drawable.noodles_food),
        FoodCategory("Paratha", R.drawable.paratha_food),
        FoodCategory("Shawarma", R.drawable.shawarma_food),
        FoodCategory("South Indian", R.drawable.south_indian_food),
        FoodCategory("Aloo Tikki", R.drawable.aloo_tikki_food),
        FoodCategory("Pasta", R.drawable.pasta_food),
        FoodCategory("Pastry", R.drawable.pastry_food),
        FoodCategory("Pav Bhaji", R.drawable.pav_bhaji_food),
        FoodCategory("Sandwich", R.drawable.sandwich_food),
        FoodCategory("Shake", R.drawable.shake_food),
        FoodCategory("Samosa", R.drawable.samosa_food),
        FoodCategory("Poori", R.drawable.poori_food),
        FoodCategory("Bowl", R.drawable.bowl_food),
        FoodCategory("Poha", R.drawable.poha_food),
        FoodCategory("Sweets", R.drawable.sweets_food),
        FoodCategory("Chole Poori", R.drawable.chole_poori_food),
        FoodCategory("Khichdi", R.drawable.khichdi_food),
        FoodCategory("Chilli Chicken", R.drawable.chilli_chicken_food),
        FoodCategory("Tea", R.drawable.tea_food),
        FoodCategory("Vada Pav", R.drawable.vada_pav_food),
        FoodCategory("Masala Maggi", R.drawable.masala_maggi_food),
        FoodCategory("Kulche", R.drawable.kulche_food),
        FoodCategory("Wings", R.drawable.wings_food),
        FoodCategory("Aloo Poori", R.drawable.aloo_poori_food),
        FoodCategory("Omelette", R.drawable.omelette_food),
        FoodCategory("Non Veg Meal", R.drawable.non_veg_meal_food),
        FoodCategory("Bread Pakoda", R.drawable.bread_pakoda_food),
        FoodCategory("Coffee", R.drawable.coffee_food),
        FoodCategory("Poori Bhaji", R.drawable.poori_bhaji_food),
        FoodCategory("Pulao", R.drawable.pulao_food),
        FoodCategory("Chur Chur Naan", R.drawable.chur_chur_naan_food),
        FoodCategory("Kebabs", R.drawable.kebabs_food),
        FoodCategory("Panipuri", R.drawable.panipuri_food),
        FoodCategory("Rasmalai", R.drawable.rasmalai_food),
        FoodCategory("Mutton", R.drawable.mutton_food),
        FoodCategory("Fish", R.drawable.fish_food),
        FoodCategory("Pakoda", R.drawable.pakoda_food),
        FoodCategory("Halwa", R.drawable.halwa_food),
        FoodCategory("Chop Suey", R.drawable.chop_suey_food),
        FoodCategory("Korma", R.drawable.korma_food),
        FoodCategory("Namkeen", R.drawable.namkeen_food),
        FoodCategory("Mushrooms", R.drawable.mushrooms_food),
        FoodCategory("Keema", R.drawable.keema_food),
        FoodCategory("Sundae", R.drawable.sundae_food),
        FoodCategory("Rasgulla", R.drawable.rasgulla_food),
        FoodCategory("Butter Chicken", R.drawable.butter_chicken_food),
        FoodCategory("Raj Kachori", R.drawable.raj_kachori_food),
        FoodCategory("Chaat", R.drawable.chaat_food),
        FoodCategory("Uttapam", R.drawable.uttapam_food),
        FoodCategory("Doughnut", R.drawable.doughnut_food),
        FoodCategory("Juice", R.drawable.juice_food),
        FoodCategory("Lassi", R.drawable.lassi_food),
        FoodCategory("Malai Kofta", R.drawable.malai_kofta_food),
        FoodCategory("Dahi Balle", R.drawable.dahi_balle_food),
        FoodCategory("Rajma", R.drawable.rajma_food),
        FoodCategory("Chicken Handi", R.drawable.chicken_handi_food),
        FoodCategory("Cupcake", R.drawable.cupcake_food),
        FoodCategory("Bhel", R.drawable.bhel_food),
        FoodCategory("Muffin", R.drawable.muffin_food),
        FoodCategory("Cookies", R.drawable.cookies_food),
        FoodCategory("Chicken Cha", R.drawable.chicken_cha_food),
        FoodCategory("Paneer Kulche", R.drawable.paneer_kulche_food),
        FoodCategory("Chaach", R.drawable.chaach_food),
        FoodCategory("Veg Lollipop", R.drawable.veg_lollipop_food),
        FoodCategory("Sub", R.drawable.sub_food),
        FoodCategory("Pancake", R.drawable.pancake_food),
        FoodCategory("Nihari", R.drawable.nihari_food),
        FoodCategory("Tacos", R.drawable.tacos_food),
        FoodCategory("Thepla", R.drawable.thepla_food),
        FoodCategory("Fafda", R.drawable.fafda_food),
        FoodCategory("Chocolate", R.drawable.chocolate_food),
        FoodCategory("Curd Rice", R.drawable.curd_rice_food),
        FoodCategory("Pudding", R.drawable.pudding_food),
        FoodCategory("Croissant", R.drawable.croissant_food),
        FoodCategory("Khandvi", R.drawable.khandvi_food),
        FoodCategory("Gajak", R.drawable.gajak_food),
        FoodCategory("Sambar Rice", R.drawable.sambar_rice_food),
        FoodCategory("Tart", R.drawable.tart_food),
        FoodCategory("Tiramisu", R.drawable.tiramisu_food),
        FoodCategory("Pie", R.drawable.pie_food),
        FoodCategory("Custard", R.drawable.custard_food),
        FoodCategory("Sev Poori", R.drawable.sev_poori_food),
        FoodCategory("Mousse", R.drawable.mousse_food),
        FoodCategory("Dal Kachori", R.drawable.dal_kachori_food),
        FoodCategory("Jalebi", R.drawable.jalebi_food),
        FoodCategory("Pyaaj Kachori", R.drawable.pyaaj_kachori_food),
        FoodCategory("Rajma Rice", R.drawable.rajma_rice_food),
        FoodCategory("Upma", R.drawable.upma_food),
        FoodCategory("Manchurian", R.drawable.manchurian_food),
        FoodCategory("Paneer Pakoda", R.drawable.paneer_pakoda_food),
        FoodCategory("Cheesecake", R.drawable.cheesecake_food),
        FoodCategory("Brownie", R.drawable.brownie_food),
        FoodCategory("Chaap", R.drawable.chaap_food),
        FoodCategory("Dal", R.drawable.dal_food),
        FoodCategory("Waffles", R.drawable.waffles_food),
        FoodCategory("Aloo Kachori", R.drawable.aloo_kachori_food),
        FoodCategory("Chole Kulche", R.drawable.chole_kulche_food),
        FoodCategory("Fries", R.drawable.fries_food),
        FoodCategory("Cold Coffee", R.drawable.cold_coffee_food),
        FoodCategory("Soup", R.drawable.soup_food),
        FoodCategory("Bhurji", R.drawable.bhurji_food),
        FoodCategory("Khasta Kachori", R.drawable.khasta_kachori_food),
        FoodCategory("Hot Dog", R.drawable.hot_dog_food)
    )

    // Find Shake index dynamically
    val shakeIndex = categories.indexOfFirst { it.name == "Shake" }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        itemsIndexed(categories) { index, category ->

            val isClickable = index <= shakeIndex

            FoodCategoryItem(
                category = category,
                isSelected = index == selectedCategoryIndex,
                isClickable = isClickable,
                onClick = {
                    if (isClickable) {
                        onCategorySelected(index + 1)
                    }
                }
            )
        }
    }
}

data class FoodCategory(
    val name: String,
    val iconRes: Int
)

@Composable
fun FoodCategoryItem(
    category: FoodCategory,
    isSelected: Boolean = false,
    isClickable: Boolean = true,
    onClick: () -> Unit = {}
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
//            .alpha(if (isClickable) 1f else 0.4f)
            .then(
                if (isClickable) Modifier.clickable { onClick() }
                else Modifier
            )
    ) {

        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) MaterialTheme.customColors.primary
            else MaterialTheme.customColors.black,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(70.dp)
        )
    }
}
