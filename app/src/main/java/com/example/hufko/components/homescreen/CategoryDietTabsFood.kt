package com.example.hufko.components.homescreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hufko.R
import com.example.hufko.ui.theme.customColors
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.DisposableEffect

// Sealed class for different diet category pages
sealed class DietCategoryPage(val title: String, val iconRes: Int) {
    // From the images provided (these use _food_diet suffix)
    object Chicken : DietCategoryPage("Chicken", R.drawable.chicken_food_diet)
    object Salad : DietCategoryPage("Salad", R.drawable.salad_food_diet)
    object Mutton : DietCategoryPage("Mutton", R.drawable.mutton_food_diet)
    object Kebabs : DietCategoryPage("Kebabs", R.drawable.kebabs_food_diet)
    object HealthySnacks : DietCategoryPage("Snacks", R.drawable.healthy_snacks_food_diet)
    object LowCalorie : DietCategoryPage("Low Calorie", R.drawable.low_calorie_food_diet)
    object Vegan : DietCategoryPage("Vegan", R.drawable.vegan_food_diet)
    object ProteinRich : DietCategoryPage("Protein Rich", R.drawable.protein_food_diet)
//    object SeeAll : DietCategoryPage("See All", R.drawable.see_all_food)

    // Categories from your list (these use _food suffix without _diet)
    object Dessert : DietCategoryPage("Dessert", R.drawable.dessert_food)
    object VegMeal : DietCategoryPage("Veg Meal", R.drawable.veg_meal_food)
    object Bowl : DietCategoryPage("Bowl", R.drawable.bowl_food)
    object Sweets : DietCategoryPage("Sweets", R.drawable.sweets_food)
    object Khichdi : DietCategoryPage("Khichdi", R.drawable.khichdi_food)
    object Sundae : DietCategoryPage("Sundae", R.drawable.sundae_food)
    object Juice : DietCategoryPage("Juice", R.drawable.juice_food)
    object Lassi : DietCategoryPage("Lassi", R.drawable.lassi_food)
    object CurdRice : DietCategoryPage("Curd Rice", R.drawable.curd_rice_food)
    object Pudding : DietCategoryPage("Pudding", R.drawable.pudding_food)
    object Custard : DietCategoryPage("Custard", R.drawable.custard_food)
    object Soup : DietCategoryPage("Soup", R.drawable.soup_food)
    object Brownie : DietCategoryPage("Brownie", R.drawable.brownie_food)
    object Waffles : DietCategoryPage("Waffles", R.drawable.waffles_food)
    object ColdCoffee : DietCategoryPage("Cold Coffee", R.drawable.cold_coffee_food)

    // Additional diet-specific items (these use _food_diet suffix as they're diet-specific)
    object GrilledChicken : DietCategoryPage("Grilled Chicken", R.drawable.grilled_chicken)
    object SteamedFish : DietCategoryPage("Steamed Fish", R.drawable.steamed_fish)
    object QuinoaBowl : DietCategoryPage("Quinoa Bowl", R.drawable.quinoa_bowl)
    object AvocadoToast : DietCategoryPage("Avocado Toast", R.drawable.avocado_toast_food)
    object GreenSmoothie : DietCategoryPage("Green Smoothie", R.drawable.green_smoothie_food)
    object Oatmeal : DietCategoryPage("Oatmeal", R.drawable.oatmeal_food)
    object GreekYogurt : DietCategoryPage("Greek Yogurt", R.drawable.greek_yogurt_food_diet)
    object EggWhiteOmelette : DietCategoryPage("Egg White", R.drawable.egg_white_omelette_food_diet)
    object TunaSalad : DietCategoryPage("Tuna Salad", R.drawable.tuna_salad_food_diet)
    object LentilSoup : DietCategoryPage("Lentil Soup", R.drawable.lentil_soup_food_diet)
    object CottageCheese : DietCategoryPage("Cottage Cheese", R.drawable.cottage_cheese_food_diet)
    object SproutsSalad : DietCategoryPage("Sprouts Salad", R.drawable.sprouts_salad_food_diet)
    object BrownRiceBowl : DietCategoryPage("Brown Rice", R.drawable.brown_rice_bowl_food_diet)
    object SteamedVeggies : DietCategoryPage("Steamed Veg", R.drawable.steamed_veggies_food_diet)
    object FruitBowl : DietCategoryPage("Fruit Bowl", R.drawable.fruit_bowl_food_diet)
    object DetoxWater : DietCategoryPage("Detox Water", R.drawable.detox_water_food_diet)
    object HerbalTea : DietCategoryPage("Herbal Tea", R.drawable.herbal_tea_food_diet)
    object ProteinBar : DietCategoryPage("Protein Bar", R.drawable.protein_bar_food_diet)
    object BoiledEggs : DietCategoryPage("Boiled Eggs", R.drawable.boiled_eggs_food_diet)
    object HummusPlate : DietCategoryPage("Hummus Plate", R.drawable.hummus_plate_food_diet)
    object SushiRolls : DietCategoryPage("Sushi Rolls", R.drawable.sushi_rolls_food_diet)
    object TofuStirFry : DietCategoryPage("Tofu Stir Fry", R.drawable.tofu_stir_fry_food_diet)
    object ChiaPudding : DietCategoryPage("Chia Pudding", R.drawable.chia_pudding_food_diet)
    object MilletBowl : DietCategoryPage("Millet Bowl", R.drawable.millet_bowl_food_diet)
    object SeeAll : DietCategoryPage("See All", R.drawable.see_all_food)

}

data class DietFoodItem(
    val id: Int,
    val name: String,
    val description: String? = null,
    val imageRes: Int,
    val calories: Int,
    val protein: Int,
    val isVeg: Boolean = true,
    val category: String = "Chicken"
)

@Composable
fun CategoryDietTabsFood(
    navController: NavHostController? = null,
    currentSelectedIndex: Int,  // This is the external index from parent
    selectedDietTabIndex: Int,   // This is the diet-specific tab index
    onCategorySelected: (DietCategoryPage) -> Unit = {},
    onTabIndexChanged: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Use selectedDietTabIndex as the internal state
    var internalSelectedIndex by rememberSaveable {
        mutableIntStateOf(
            navController?.currentBackStackEntry?.savedStateHandle?.get<Int>("currentDietSelectedIndex")
                ?: selectedDietTabIndex
        )
    }

    // Track recently selected tabs from "See All" page - using Set to avoid duplicates
    var recentlySelectedTabs by rememberSaveable { mutableStateOf(setOf<Int>()) }

    // Save changes back to savedStateHandle
    LaunchedEffect(internalSelectedIndex) {
        navController?.currentBackStackEntry?.savedStateHandle?.set(
            "currentDietSelectedIndex",
            internalSelectedIndex
        )
    }

    // All diet category pages
    val allDietCategoryPages = listOf(
        DietCategoryPage.Chicken,
        DietCategoryPage.Salad,
        DietCategoryPage.Mutton,
        DietCategoryPage.Kebabs,
        DietCategoryPage.HealthySnacks,
        DietCategoryPage.LowCalorie,
        DietCategoryPage.Vegan,
        DietCategoryPage.ProteinRich,

        DietCategoryPage.Dessert,
        DietCategoryPage.VegMeal,
        DietCategoryPage.Bowl,
        DietCategoryPage.Sweets,
        DietCategoryPage.Khichdi,
        DietCategoryPage.Sundae,
        DietCategoryPage.Juice,
        DietCategoryPage.Lassi,
        DietCategoryPage.CurdRice,
        DietCategoryPage.Pudding,
        DietCategoryPage.Custard,
        DietCategoryPage.Soup,
        DietCategoryPage.Brownie,
        DietCategoryPage.Waffles,
        DietCategoryPage.ColdCoffee,
        // Additional diet categories
        DietCategoryPage.GrilledChicken,
        DietCategoryPage.SteamedFish,
        DietCategoryPage.QuinoaBowl,
        DietCategoryPage.AvocadoToast,
        DietCategoryPage.GreenSmoothie,
        DietCategoryPage.Oatmeal,
        DietCategoryPage.GreekYogurt,
        DietCategoryPage.EggWhiteOmelette,
        DietCategoryPage.TunaSalad,
        DietCategoryPage.LentilSoup,
        DietCategoryPage.CottageCheese,
        DietCategoryPage.SproutsSalad,
        DietCategoryPage.BrownRiceBowl,
        DietCategoryPage.SteamedVeggies,
        DietCategoryPage.FruitBowl,
        DietCategoryPage.DetoxWater,
        DietCategoryPage.HerbalTea,
        DietCategoryPage.ProteinBar,
        DietCategoryPage.BoiledEggs,
        DietCategoryPage.HummusPlate,
        DietCategoryPage.SushiRolls,
        DietCategoryPage.TofuStirFry,
        DietCategoryPage.ChiaPudding,
        DietCategoryPage.MilletBowl,
    )

    Log.d("CategoryDietTabsFood", "internalSelectedIndex: $internalSelectedIndex")
    Log.d("CategoryDietTabsFood", "currentSelectedIndex from parent: $currentSelectedIndex")
    Log.d("CategoryDietTabsFood", "recentlySelectedTabs: $recentlySelectedTabs")

    // Update when parent state changes
    LaunchedEffect(selectedDietTabIndex) {
        internalSelectedIndex = selectedDietTabIndex
    }

    // Handle back navigation with selected index from See All page
    DisposableEffect(navController) {
        val savedStateHandle = navController?.currentBackStackEntry?.savedStateHandle
        val liveData = savedStateHandle?.getLiveData<Int>("selectedDietTabFromSeeAll")

        val observer = androidx.lifecycle.Observer<Int> { newIndex ->
            if (newIndex != null) {
                Log.d("CategoryDietTabsFood", "Received new index from See All: $newIndex")

                // Add the selected tab to recently selected tabs if it's beyond initial visible count
                if (newIndex >= 8) {
                    // Update recentlySelectedTabs by adding the new index
                    recentlySelectedTabs = recentlySelectedTabs + newIndex
                    Log.d("CategoryDietTabsFood", "Added to recentlySelectedTabs: $newIndex, now: $recentlySelectedTabs")
                }

                // Update internal selected index
                internalSelectedIndex = newIndex

                // Notify parent about the change
                onTabIndexChanged(newIndex)

                // Get the category page and call onCategorySelected
                allDietCategoryPages.getOrNull(newIndex)?.let { page ->
                    onCategorySelected(page)
                }

                // Clear the value to prevent repeated updates
                savedStateHandle?.remove<Int>("selectedDietTabFromSeeAll")
            }
        }

        liveData?.observeForever(observer)

        onDispose {
            liveData?.removeObserver(observer)
        }
    }

    // Initial visible tabs count
    val initialVisibleCount = 8

    // Build visible tabs: initial 8 + recently selected (sorted) + "See All"
    val visibleTabs = remember(recentlySelectedTabs) {
        buildList {
            // Add first 8 tabs
            addAll(allDietCategoryPages.take(initialVisibleCount))

            // Add recently selected tabs (sorted to maintain consistent order)
            val sortedRecentTabs = recentlySelectedTabs.sorted()
            Log.d("CategoryDietTabsFood", "Building visible tabs with sorted recent tabs: $sortedRecentTabs")

            sortedRecentTabs.forEach { index ->
                if (index >= initialVisibleCount) {
                    allDietCategoryPages.getOrNull(index)?.let { page ->
                        add(page)
                        Log.d("CategoryDietTabsFood", "Added recent tab at index $index: ${page.title}")
                    }
                }
            }

            // Add "See All" at the end
            add(DietCategoryPage.SeeAll)
        }
    }

    Log.d("CategoryDietTabsFood", "visibleTabs count: ${visibleTabs.size}")
    visibleTabs.forEachIndexed { i, page ->
        Log.d("CategoryDietTabsFood", "visibleTabs[$i]: ${page.title}")
    }

    val seeAllIndex = visibleTabs.indexOfLast { it is DietCategoryPage.SeeAll }

    // Create a mapping from allCategories index to visibleTabs index
    val allToVisibleIndexMap = remember(recentlySelectedTabs) {
        val map = mutableMapOf<Int, Int>()

        // Map first 8 tabs
        for (i in 0 until minOf(initialVisibleCount, allDietCategoryPages.size)) {
            map[i] = i
        }

        // Map recently selected tabs
        val sortedRecentTabs = recentlySelectedTabs.sorted()
        sortedRecentTabs.forEachIndexed { recentIndex, allIndex ->
            if (allIndex >= initialVisibleCount) {
                map[allIndex] = initialVisibleCount + recentIndex
            }
        }

        map
    }

    // Find the index in visibleTabs for a given allCategories index
    fun getVisibleTabIndex(allCategoriesIndex: Int): Int {
        return when {
            allCategoriesIndex < initialVisibleCount -> allCategoriesIndex
            allCategoriesIndex in allToVisibleIndexMap -> allToVisibleIndexMap[allCategoriesIndex] ?: seeAllIndex
            else -> seeAllIndex // "See All" tab
        }
    }

    val selectedVisibleIndex = getVisibleTabIndex(internalSelectedIndex)

    Log.d("CategoryDietTabsFood", "selectedVisibleIndex: $selectedVisibleIndex, internalSelectedIndex: $internalSelectedIndex")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 0.dp)
            .background(
                color = MaterialTheme.customColors.orangeLight,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .padding(horizontal = 6.dp, vertical = 0.dp)
    ) {
        // Heading
        Text(
            text = "Atharv, still looking for healthy diet?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.white,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 6.dp)
        )

        // ScrollableTabRow
        ScrollableTabRow(
            selectedTabIndex = selectedVisibleIndex,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.customColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedVisibleIndex]),
                    height = 5.dp,
                    color = MaterialTheme.customColors.header
                )
            }
        ) {
            visibleTabs.forEachIndexed { index, dietCategoryPage ->
                val isSeeAllTab = dietCategoryPage is DietCategoryPage.SeeAll

                // Find which allCategories index this corresponds to
                val allCategoriesIndex = when {
                    index < initialVisibleCount -> index
                    isSeeAllTab -> -1
                    else -> {
                        // This is a recently selected tab - find its original index
                        val sortedRecentTabs = recentlySelectedTabs.sorted()
                        val recentIndex = index - initialVisibleCount
                        if (recentIndex < sortedRecentTabs.size) {
                            sortedRecentTabs[recentIndex]
                        } else -1
                    }
                }

                val isSelected = if (isSeeAllTab) {
                    false // See All tab is never selected as a content tab
                } else {
                    internalSelectedIndex == allCategoriesIndex
                }

                Tab(
                    selected = isSelected,
                    onClick = {
                        if (isSeeAllTab) {
                            // Navigate to the "See All" page
                            navController?.navigate("category_diet_tabs_list/${internalSelectedIndex}") {
                                // Pass all categories and current index as arguments
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "allDietCategories",
                                    allDietCategoryPages
                                )
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "currentDietMainTabIndex",
                                    internalSelectedIndex
                                )
                            }
                        } else {
                            // For regular tabs
                            internalSelectedIndex = allCategoriesIndex
                            onTabIndexChanged(allCategoriesIndex)
                            onCategorySelected(dietCategoryPage)
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 5.dp)
                        .background(Color.Transparent)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .width(80.dp)
                            .background(
                                color = MaterialTheme.customColors.white,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = dietCategoryPage.iconRes),
                            contentDescription = dietCategoryPage.title,
                            modifier = Modifier
                                .width(65.dp)
                                .height(55.dp),
                            contentScale = ContentScale.FillBounds
                        )

                        Text(
                            text = dietCategoryPage.title,
                            fontSize = 15.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) {
                                MaterialTheme.customColors.header
                            } else {
                                MaterialTheme.customColors.black
                            },
                            maxLines = 2,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        // Show a small indicator for recently added tabs
                        if (!isSeeAllTab && allCategoriesIndex >= initialVisibleCount) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.customColors.header.copy(alpha = 0.6f))
                            )
                        }
                    }
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
        // Get the actual category based on internalSelectedIndex
        val actualCategory = allDietCategoryPages.getOrNull(internalSelectedIndex)

        // Show content based on the actual category
        when (actualCategory) {
            DietCategoryPage.Chicken -> ChickenDietPage()
            DietCategoryPage.Salad -> SaladDietPage()
            DietCategoryPage.Mutton -> MuttonDietPage()
            DietCategoryPage.Kebabs -> KebabsDietPage()
            DietCategoryPage.HealthySnacks -> HealthySnacksPage()
            DietCategoryPage.LowCalorie -> LowCaloriePage()
            DietCategoryPage.Vegan -> VeganPage()
            DietCategoryPage.ProteinRich -> ProteinRichPage()

            DietCategoryPage.Dessert -> DessertPage()
            DietCategoryPage.VegMeal -> VegMealPage()
            DietCategoryPage.Bowl -> BowlPage()
            DietCategoryPage.Sweets -> SweetsPage()
            DietCategoryPage.Khichdi -> KhichdiPage()
            DietCategoryPage.Sundae -> SundaePage()
            DietCategoryPage.Juice -> JuicePage()
            DietCategoryPage.Lassi -> LassiPage()
            DietCategoryPage.CurdRice -> CurdRicePage()
            DietCategoryPage.Pudding -> PuddingPage()
            DietCategoryPage.Custard -> CustardPage()
            DietCategoryPage.Soup -> SoupDietPage()
            DietCategoryPage.Brownie -> BrowniePage()
            DietCategoryPage.Waffles -> WafflesPage()
            DietCategoryPage.ColdCoffee -> ColdCoffeePage()

            DietCategoryPage.GrilledChicken -> GrilledChickenDietPage()
            DietCategoryPage.SteamedFish -> SteamedFishDietPage()
            DietCategoryPage.QuinoaBowl -> QuinoaBowlDietPage()
            DietCategoryPage.AvocadoToast -> AvocadoToastDietPage()
            DietCategoryPage.GreenSmoothie -> GreenSmoothieDietPage()
            DietCategoryPage.Oatmeal -> OatmealDietPage()
            DietCategoryPage.GreekYogurt -> GreekYogurtDietPage()
            DietCategoryPage.EggWhiteOmelette -> EggWhiteOmeletteDietPage()
            DietCategoryPage.TunaSalad -> TunaSaladDietPage()
            DietCategoryPage.LentilSoup -> LentilSoupDietPage()
            DietCategoryPage.CottageCheese -> CottageCheeseDietPage()
            DietCategoryPage.SproutsSalad -> SproutsSaladDietPage()
            DietCategoryPage.BrownRiceBowl -> BrownRiceBowlDietPage()
            DietCategoryPage.SteamedVeggies -> SteamedVeggiesDietPage()
            DietCategoryPage.FruitBowl -> FruitBowlDietPage()
            DietCategoryPage.DetoxWater -> DetoxWaterDietPage()
            DietCategoryPage.HerbalTea -> HerbalTeaDietPage()
            DietCategoryPage.ProteinBar -> ProteinBarDietPage()
            DietCategoryPage.BoiledEggs -> BoiledEggsDietPage()
            DietCategoryPage.HummusPlate -> HummusPlateDietPage()
            DietCategoryPage.SushiRolls -> SushiRollsDietPage()
            DietCategoryPage.TofuStirFry -> TofuStirFryDietPage()
            DietCategoryPage.ChiaPudding -> ChiaPuddingDietPage()
            DietCategoryPage.MilletBowl -> MilletBowlDietPage()
            null -> {
                // Show empty state for "See All" tab or invalid index
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Select a diet category",
                        color = MaterialTheme.customColors.gray,
                        fontSize = 16.sp
                    )
                }
            }
            else -> ChickenDietPage()
        }
    }
}


@Composable
fun MainScreenWithDietTabs(navController: NavHostController) {
    var currentPage by remember { mutableIntStateOf(0) }
    var selectedDietTabIndex by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        CategoryDietTabsFood(
            navController = navController,
            currentSelectedIndex = currentPage,  // Pass the current page
            selectedDietTabIndex = selectedDietTabIndex, // Pass the selected diet tab index
            onCategorySelected = { dietCategoryPage ->
                val newIndex = when (dietCategoryPage) {
                    // First 8 main categories (indices 0-7)
                    DietCategoryPage.Chicken -> 0
                    DietCategoryPage.Salad -> 1
                    DietCategoryPage.Mutton -> 2
                    DietCategoryPage.Kebabs -> 3
                    DietCategoryPage.HealthySnacks -> 4
                    DietCategoryPage.LowCalorie -> 5
                    DietCategoryPage.Vegan -> 6
                    DietCategoryPage.ProteinRich -> 7

                    // Categories from your list (indices 8-22)
                    DietCategoryPage.Dessert -> 8
                    DietCategoryPage.VegMeal -> 9
                    DietCategoryPage.Bowl -> 10
                    DietCategoryPage.Sweets -> 11
                    DietCategoryPage.Khichdi -> 12
                    DietCategoryPage.Sundae -> 13
                    DietCategoryPage.Juice -> 14
                    DietCategoryPage.Lassi -> 15
                    DietCategoryPage.CurdRice -> 16
                    DietCategoryPage.Pudding -> 17
                    DietCategoryPage.Custard -> 18
                    DietCategoryPage.Soup -> 19
                    DietCategoryPage.Brownie -> 20
                    DietCategoryPage.Waffles -> 21
                    DietCategoryPage.ColdCoffee -> 22

                    // Additional diet-specific items (indices 23-46)
                    DietCategoryPage.GrilledChicken -> 23
                    DietCategoryPage.SteamedFish -> 24
                    DietCategoryPage.QuinoaBowl -> 25
                    DietCategoryPage.AvocadoToast -> 26
                    DietCategoryPage.GreenSmoothie -> 27
                    DietCategoryPage.Oatmeal -> 28
                    DietCategoryPage.GreekYogurt -> 29
                    DietCategoryPage.EggWhiteOmelette -> 30
                    DietCategoryPage.TunaSalad -> 31
                    DietCategoryPage.LentilSoup -> 32
                    DietCategoryPage.CottageCheese -> 33
                    DietCategoryPage.SproutsSalad -> 34
                    DietCategoryPage.BrownRiceBowl -> 35
                    DietCategoryPage.SteamedVeggies -> 36
                    DietCategoryPage.FruitBowl -> 37
                    DietCategoryPage.DetoxWater -> 38
                    DietCategoryPage.HerbalTea -> 39
                    DietCategoryPage.ProteinBar -> 40
                    DietCategoryPage.BoiledEggs -> 41
                    DietCategoryPage.HummusPlate -> 42
                    DietCategoryPage.SushiRolls -> 43
                    DietCategoryPage.TofuStirFry -> 44
                    DietCategoryPage.ChiaPudding -> 45
                    DietCategoryPage.MilletBowl -> 46

                    DietCategoryPage.SeeAll -> currentPage
                }
                currentPage = newIndex
                selectedDietTabIndex = newIndex
            },
            onTabIndexChanged = { newIndex: Int ->
                currentPage = newIndex
                selectedDietTabIndex = newIndex
            }
        )
    }
}
// Category Page Composables for diet tabs

@Composable
fun ChickenDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .padding(0.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val chickenDietFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "grilled",
                    text = "Grilled Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_grilled
                ),
                FilterChip(
                    id = "fried",
                    text = "Fried Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_fried
                ),
                FilterChip(
                    id = "curry",
                    text = "Chicken Curry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_curry
                ),
                FilterChip(
                    id = "roast",
                    text = "Roast Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_roast
                ),
                FilterChip(
                    id = "butter_chicken",
                    text = "Butter Chicken",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_butter
                ),
                FilterChip(
                    id = "tandoori",
                    text = "Tandoori",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_tandoori
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "boneless",
                    text = "Boneless Only",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_250",
                    text = "Under ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "protein_rich",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy",
                    text = "Healthy Options",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = chickenDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val chickenDietFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_chicken_grilled_diet,
                title = "Grilled Chicken",
                price = "250",
                restaurantName = "Chicken Kingdom",
                rating = "4.5",
                deliveryTime = "30-35 mins",
                distance = "4.2 km",
                discount = "30%",
                discountAmount = "up to ₹75",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_chicken_fried_diet,
                title = "Crispy Fried Chicken",
                price = "220",
                restaurantName = "Chicken Express",
                rating = "4.3",
                deliveryTime = "25-30 mins",
                distance = "3.5 km",
                discount = "25%",
                discountAmount = "up to ₹55",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_chicken_curry_diet,
                title = "Chicken Curry",
                price = "280",
                restaurantName = "Spice Hub",
                rating = "4.4",
                deliveryTime = "40-45 mins",
                distance = "5.8 km",
                discount = "20%",
                discountAmount = "up to ₹56",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_chicken_roast_diet,
                title = "Roast Chicken",
                price = "320",
                restaurantName = "Royal Chicken",
                rating = "4.6",
                deliveryTime = "50-55 mins",
                distance = "6.3 km",
                discount = "15%",
                discountAmount = "up to ₹48",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_chicken_butter_diet,
                title = "Butter Chicken",
                price = "260",
                restaurantName = "Dhaba Style",
                rating = "4.7",
                deliveryTime = "35-40 mins",
                distance = "4.7 km",
                discount = "40%",
                discountAmount = "up to ₹104",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_chicken_tandoori_diet,
                title = "Tandoori Chicken",
                price = "240",
                restaurantName = "Tandoor Special",
                rating = "4.2",
                deliveryTime = "30-35 mins",
                distance = "3.9 km",
                discount = "35%",
                discountAmount = "up to ₹84",
                address = "Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = chickenDietFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }
//        FilterButtonFood()

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    // Sample data based on the provided images
    val chickenDietRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_chicken_food_1,
            title = "Grilled Chicken Platter",
            price = "325",
            restaurantName = "Chicken Kingdom",
            rating = "4.5",
            deliveryTime = "30-35 mins",
            distance = "4.2 km",
            discount = "30% OFF",
            discountAmount = "up to ₹100",
            address = "Grill Street, Downtown"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_chicken_food_2,
            title = "Crispy Fried Chicken Bucket",
            price = "450",
            restaurantName = "Chicken Express",
            rating = "4.3",
            deliveryTime = "25-30 mins",
            distance = "3.5 km",
            discount = "25% OFF",
            discountAmount = "up to ₹120",
            address = "Fast Food Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_chicken_food_3,
            title = "Chicken Butter Masala",
            price = "280",
            restaurantName = "Spice Hub",
            rating = "4.4",
            deliveryTime = "40-45 mins",
            distance = "5.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹80",
            address = "Curry Corner"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_chicken_food_4,
            title = "Whole Roast Chicken",
            price = "550",
            restaurantName = "Royal Chicken",
            rating = "4.6",
            deliveryTime = "50-55 mins",
            distance = "6.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹85",
            address = "Royal Feast Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_chicken_food_5,
            title = "Butter Chicken with Naan",
            price = "320",
            restaurantName = "Dhaba Style",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.7 km",
            discount = "40% OFF",
            discountAmount = "up to ₹130",
            address = "Highway Dhaba"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_chicken_food_6,
            title = "Tandoori Chicken Platter",
            price = "380",
            restaurantName = "Tandoor Special",
            rating = "4.2",
            deliveryTime = "30-35 mins",
            distance = "3.9 km",
            discount = "35% OFF",
            discountAmount = "up to ₹135",
            address = "Tandoori Street"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_chicken_food_7,
            title = "Chilli Chicken Combo",
            price = "290",
            restaurantName = "Chinese Wok",
            rating = "4.1",
            deliveryTime = "20-25 mins",
            distance = "2.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹90",
            address = "Chinatown Plaza"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_chicken_food_8,
            title = "Hyderabadi Chicken Biryani",
            price = "260",
            restaurantName = "Biryani House",
            rating = "4.8",
            deliveryTime = "35-40 mins",
            distance = "4.5 km",
            discount = "50% OFF",
            discountAmount = "up to ₹130",
            address = "Biryani Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_chicken_food_9,
            title = "Chicken Shawarma Platter",
            price = "220",
            restaurantName = "Arabian Nights",
            rating = "4.3",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹55",
            address = "Middle Eastern Quarter"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_chicken_food_10,
            title = "Chicken 65 Special",
            price = "240",
            restaurantName = "Spicy Corner",
            rating = "4.0",
            deliveryTime = "30-35 mins",
            distance = "4.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Spice Market"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_chicken_food_11,
            title = "Chicken Tikka Masala",
            price = "310",
            restaurantName = "Tikka Palace",
            rating = "4.4",
            deliveryTime = "40-45 mins",
            distance = "5.2 km",
            discount = "30% OFF",
            discountAmount = "up to ₹95",
            address = "Royal Tikka Street"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_chicken_food_12,
            title = "Chicken Korma Deluxe",
            price = "270",
            restaurantName = "Mughlai Darbar",
            rating = "4.6",
            deliveryTime = "45-50 mins",
            distance = "6.1 km",
            discount = "35% OFF",
            discountAmount = "up to ₹95",
            address = "Mughlai Avenue"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_chicken_food_13,
            title = "Chicken Lollipop Basket",
            price = "290",
            restaurantName = "Starters Heaven",
            rating = "4.2",
            deliveryTime = "25-30 mins",
            distance = "3.7 km",
            discount = "40% OFF",
            discountAmount = "up to ₹120",
            address = "Appetizer Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_chicken_food_14,
            title = "Chicken Burger Combo",
            price = "199",
            restaurantName = "Burger King",
            rating = "4.1",
            deliveryTime = "20-25 mins",
            distance = "2.5 km",
            discount = "50% OFF",
            discountAmount = "up to ₹100",
            address = "Fast Food Court"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_chicken_food_15,
            title = "Chicken Wings Platter",
            price = "350",
            restaurantName = "Wings World",
            rating = "4.3",
            deliveryTime = "30-35 mins",
            distance = "4.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹90",
            address = "Wings Street"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_chicken_food_16,
            title = "Chicken Sandwich Meal",
            price = "180",
            restaurantName = "Subway Delights",
            rating = "4.0",
            deliveryTime = "15-20 mins",
            distance = "1.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹55",
            address = "Quick Bite Center"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_chicken_food_17,
            title = "Chicken Teriyaki Bowl",
            price = "320",
            restaurantName = "Tokyo Kitchen",
            rating = "4.5",
            deliveryTime = "35-40 mins",
            distance = "5.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹65",
            address = "Japanese Quarter"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_chicken_food_18,
            title = "Chicken Popcorn Bucket",
            price = "210",
            restaurantName = "Movie Munchies",
            rating = "4.1",
            deliveryTime = "25-30 mins",
            distance = "3.4 km",
            discount = "Buy 1 get 1 free",
            discountAmount = "On large buckets",
            address = "Entertainment District"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_chicken_food_19,
            title = "Chicken Pasta Alfredo",
            price = "280",
            restaurantName = "Italian Bistro",
            rating = "4.4",
            deliveryTime = "30-35 mins",
            distance = "4.6 km",
            discount = "25% OFF",
            discountAmount = "up to ₹70",
            address = "Italian Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_chicken_food_20,
            title = "Chicken Salad Bowl",
            price = "230",
            restaurantName = "Healthy Bites",
            rating = "4.2",
            deliveryTime = "20-25 mins",
            distance = "2.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹70",
            address = "Wellness Center"
        )
    )

    Column {
        chickenDietRestaurantItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun SaladDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val saladFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "greek_salad",
                    text = "Greek Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_greek
                ),
                FilterChip(
                    id = "caesar_salad",
                    text = "Caesar Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_caesar
                ),
                FilterChip(
                    id = "fruit_salad",
                    text = "Fruit Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_fruit
                ),
                FilterChip(
                    id = "chicken_salad",
                    text = "Chicken Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_chicken
                ),
                FilterChip(
                    id = "quinoa_salad",
                    text = "Quinoa Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_quinoa
                ),
                FilterChip(
                    id = "pasta_salad",
                    text = "Pasta Salad",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_pasta
                ),
                FilterChip(
                    id = "vegetarian",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fresh_made",
                    text = "Fresh Made",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = saladFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val saladDietFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_salad_greek_diet,
                title = "Greek Salad",
                price = "180",
                restaurantName = "Mediterranean Delight",
                rating = "4.4",
                deliveryTime = "20-25 mins",
                distance = "3.2 km",
                discount = "25%",
                discountAmount = "up to ₹45",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_salad_caesar_diet,
                title = "Caesar Salad",
                price = "220",
                restaurantName = "Salad Bar",
                rating = "4.3",
                deliveryTime = "15-20 mins",
                distance = "2.8 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_salad_fruit_diet,
                title = "Fruit Salad",
                price = "150",
                restaurantName = "Fruit Paradise",
                rating = "4.6",
                deliveryTime = "10-15 mins",
                distance = "1.5 km",
                discount = "30%",
                discountAmount = "up to ₹45",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_salad_chicken_diet,
                title = "Chicken Salad",
                price = "240",
                restaurantName = "Protein Hub",
                rating = "4.5",
                deliveryTime = "25-30 mins",
                distance = "4.1 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_salad_quinoa_diet,
                title = "Quinoa Salad",
                price = "200",
                restaurantName = "Healthy Bites",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "3.5 km",
                discount = "25%",
                discountAmount = "up to ₹50",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_salad_pasta_diet,
                title = "Pasta Salad",
                price = "190",
                restaurantName = "Italian Greens",
                rating = "4.2",
                deliveryTime = "25-30 mins",
                distance = "3.8 km",
                discount = "20%",
                discountAmount = "up to ₹38",
                address = "Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = saladDietFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }
//
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    // Sample data based on the provided images

    val saladDietRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_salad_food_1,
            title = "Mediterranean Quinoa Bowl",
            price = "285",
            restaurantName = "Green Leaf Cafe",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Health Street, Downtown"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_salad_food_2,
            title = "Protein Power Salad",
            price = "320",
            restaurantName = "FitFuel Kitchen",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹80",
            address = "Fitness Hub Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_salad_food_3,
            title = "Caesar Salad with Grilled Chicken",
            price = "250",
            restaurantName = "Salad Bar Express",
            rating = "4.4",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹75",
            address = "Quick Bite Corner"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_salad_food_4,
            title = "Greek Salad Platter",
            price = "220",
            restaurantName = "Mediterranean Delight",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹35",
            address = "Olive Oil Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_salad_food_5,
            title = "Rainbow Veggie Bowl",
            price = "195",
            restaurantName = "Vegan Vibes",
            rating = "4.8",
            deliveryTime = "30-35 mins",
            distance = "4.1 km",
            discount = "40% OFF",
            discountAmount = "up to ₹80",
            address = "Plant-Based District"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_salad_food_6,
            title = "Avocado & Chickpea Salad",
            price = "240",
            restaurantName = "Superfood Kitchen",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.4 km",
            discount = "35% OFF",
            discountAmount = "up to ₹85",
            address = "Nutrition Street"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_salad_food_7,
            title = "Thai Mango Salad",
            price = "210",
            restaurantName = "Asian Greens",
            rating = "4.3",
            deliveryTime = "30-35 mins",
            distance = "4.3 km",
            discount = "30% OFF",
            discountAmount = "up to ₹65",
            address = "Asian Fusion Plaza"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_salad_food_8,
            title = "Kale & Quinoa Super Bowl",
            price = "275",
            restaurantName = "Clean Eats",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.7 km",
            discount = "50% OFF",
            discountAmount = "up to ₹140",
            address = "Wellness Center"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_salad_food_9,
            title = "Tuna Nicoise Salad",
            price = "290",
            restaurantName = "French Bistro",
            rating = "4.4",
            deliveryTime = "35-40 mins",
            distance = "5.1 km",
            discount = "25% OFF",
            discountAmount = "up to ₹75",
            address = "European Quarter"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_salad_food_10,
            title = "Roasted Veggie & Feta Bowl",
            price = "230",
            restaurantName = "Farm to Table",
            rating = "4.7",
            deliveryTime = "25-30 mins",
            distance = "3.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Organic Market"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_salad_food_11,
            title = "Spinach & Berry Salad",
            price = "205",
            restaurantName = "Berry Fresh",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.9 km",
            discount = "30% OFF",
            discountAmount = "up to ₹65",
            address = "Berry Lane"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_salad_food_12,
            title = "Mexican Fiesta Bowl",
            price = "245",
            restaurantName = "Mexi-Greens",
            rating = "4.3",
            deliveryTime = "30-35 mins",
            distance = "4.2 km",
            discount = "35% OFF",
            discountAmount = "up to ₹90",
            address = "Mexican Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_salad_food_13,
            title = "Pomegranate & Walnut Salad",
            price = "225",
            restaurantName = "Nutrient Hub",
            rating = "4.6",
            deliveryTime = "25-30 mins",
            distance = "3.5 km",
            discount = "40% OFF",
            discountAmount = "up to ₹90",
            address = "Health District"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_salad_food_14,
            title = "Detox Green Salad",
            price = "180",
            restaurantName = "Juice & Salad Bar",
            rating = "4.2",
            deliveryTime = "15-20 mins",
            distance = "1.7 km",
            discount = "50% OFF",
            discountAmount = "up to ₹90",
            address = "Detox Center"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_salad_food_15,
            title = "Warm Lentil & Veggie Bowl",
            price = "195",
            restaurantName = "Comfort Greens",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "3.1 km",
            discount = "25% OFF",
            discountAmount = "up to ₹50",
            address = "Comfort Food Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_salad_food_16,
            title = "Caprese Salad Platter",
            price = "235",
            restaurantName = "Italian Greens",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.6 km",
            discount = "30% OFF",
            discountAmount = "up to ₹70",
            address = "Italian Quarter"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_salad_food_17,
            title = "Asian Crunch Salad",
            price = "215",
            restaurantName = "Tokyo Greens",
            rating = "4.4",
            deliveryTime = "30-35 mins",
            distance = "4.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹45",
            address = "Asian District"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_salad_food_18,
            title = "Beetroot & Goat Cheese Salad",
            price = "255",
            restaurantName = "Gourmet Greens",
            rating = "4.7",
            deliveryTime = "30-35 mins",
            distance = "4.0 km",
            discount = "Buy 1 get 1 free",
            discountAmount = "On all salads",
            address = "Gourmet Street"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_salad_food_19,
            title = "Southwest Chicken Salad",
            price = "265",
            restaurantName = "Grill & Greens",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "3.6 km",
            discount = "25% OFF",
            discountAmount = "up to ₹70",
            address = "Grill Avenue"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_salad_food_20,
            title = "Moroccan Couscous Salad",
            price = "240",
            restaurantName = "Spice & Greens",
            rating = "4.3",
            deliveryTime = "35-40 mins",
            distance = "4.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹75",
            address = "Spice Market Lane"
        )
    )
    Column {
        saladDietRestaurantItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}
@Composable
fun MuttonDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val muttonDietFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                FilterChip(
                    id = "mutton_curry",
                    text = "Mutton Curry",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_curry
                ),
                FilterChip(
                    id = "mutton_biryani",
                    text = "Mutton Biryani",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_biryani
                ),
                FilterChip(
                    id = "mutton_kebabs",
                    text = "Mutton Kebabs",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_kebab
                ),
                FilterChip(
                    id = "mutton_rogan_josh",
                    text = "Rogan Josh",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_rogan_josh
                ),
                FilterChip(
                    id = "mutton_keema",
                    text = "Mutton Keema",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mutton_keema
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mild",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bone_in",
                    text = "Bone-In",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "boneless",
                    text = "Boneless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_friendly",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_500",
                    text = "Under ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "traditional",
                    text = "Traditional",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "premium",
                    text = "Premium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = muttonDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val muttonFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_mutton_biryani_diet,
                title = "Mutton Biryani",
                price = "450",
                restaurantName = "Royal Mughlai",
                rating = "4.8",
                deliveryTime = "35-40 mins",
                distance = "4.2 km",
                discount = "20%",
                discountAmount = "up to ₹90",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mutton_korma_diet,
                title = "Mutton Korma",
                price = "420",
                restaurantName = "Darbar Kitchen",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "3.5 km",
                discount = "15%",
                discountAmount = "up to ₹63",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_mutton_rogan_josh_diet,
                title = "Mutton Rogan Josh",
                price = "480",
                restaurantName = "Kashmiri Delight",
                rating = "4.7",
                deliveryTime = "40-45 mins",
                distance = "5.1 km",
                discount = "25%",
                discountAmount = "up to ₹120",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_mutton_curry_diet,
                title = "Mutton Curry",
                price = "380",
                restaurantName = "Spice Nation",
                rating = "4.4",
                deliveryTime = "25-30 mins",
                distance = "2.8 km",
                discount = "10%",
                discountAmount = "up to ₹38",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mutton_kebabs_diet,
                title = "Mutton Seekh Kebabs",
                price = "350",
                restaurantName = "Kebab Junction",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "2.3 km",
                discount = "20%",
                discountAmount = "up to ₹70",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_mutton_handi_diet,
                title = "Mutton Handi",
                price = "550",
                restaurantName = "Handi House",
                rating = "4.8",
                deliveryTime = "35-40 mins",
                distance = "4.5 km",
                discount = "15%",
                discountAmount = "up to ₹82",
                address = "Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = muttonFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }
//
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    // Sample data based on the provided images

    val muttonRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_mutton_food_1,
            title = "Royal Mughlai Mutton Biryani",
            price = "450",
            restaurantName = "Royal Mughlai",
            rating = "4.8",
            deliveryTime = "35-40 mins",
            distance = "4.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹90",
            address = "Mughlai Street, Old Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_mutton_food_2,
            title = "Darbar Kitchen Mutton Korma",
            price = "420",
            restaurantName = "Darbar Kitchen",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹63",
            address = "Royal Palace Road"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_mutton_food_3,
            title = "Kashmiri Rogan Josh",
            price = "480",
            restaurantName = "Kashmiri Delight",
            rating = "4.7",
            deliveryTime = "40-45 mins",
            distance = "5.1 km",
            discount = "25% OFF",
            discountAmount = "up to ₹120",
            address = "Kashmiri Valley Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_mutton_food_4,
            title = "Spice Nation Mutton Curry",
            price = "380",
            restaurantName = "Spice Nation",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "2.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹38",
            address = "Spice Market Area"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_mutton_food_5,
            title = "Kebab Junction Seekh Kebabs",
            price = "350",
            restaurantName = "Kebab Junction",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹70",
            address = "Kebab Street Corner"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_mutton_food_6,
            title = "Handi House Mutton Handi",
            price = "550",
            restaurantName = "Handi House",
            rating = "4.8",
            deliveryTime = "35-40 mins",
            distance = "4.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹82",
            address = "Handi Special Area"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_mutton_food_7,
            title = "Keema Special Mutton Keema",
            price = "320",
            restaurantName = "Keema Special",
            rating = "4.3",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "Keema Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_mutton_food_8,
            title = "Kadai King Special Mutton",
            price = "440",
            restaurantName = "Kadai King",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹88",
            address = "Kadai Special Road"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_mutton_food_9,
            title = "Pulao Paradise Mutton Pulao",
            price = "400",
            restaurantName = "Pulao Paradise",
            rating = "4.4",
            deliveryTime = "30-35 mins",
            distance = "4.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹60",
            address = "Pulao Street"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_mutton_food_10,
            title = "Punjabi Dhaba Mutton Saag",
            price = "370",
            restaurantName = "Punjabi Dhaba",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹37",
            address = "Highway Dhaba Road"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_mutton_food_11,
            title = "Kolhapuri Mutton Special",
            price = "460",
            restaurantName = "Maharashtrian Tadka",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹92",
            address = "Maharashtrian Food Street"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_mutton_food_12,
            title = "Afghani Mutton Delicacy",
            price = "520",
            restaurantName = "Afghan Kitchen",
            rating = "4.8",
            deliveryTime = "40-45 mins",
            distance = "5.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹130",
            address = "Afghan Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_mutton_food_13,
            title = "Nihari House Special Mutton",
            price = "490",
            restaurantName = "Nihari House",
            rating = "4.9",
            deliveryTime = "45-50 mins",
            distance = "5.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹73",
            address = "Nihari Street, Old Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_mutton_food_14,
            title = "Royal BBQ Mutton Raan",
            price = "750",
            restaurantName = "Royal BBQ",
            rating = "4.8",
            deliveryTime = "50-55 mins",
            distance = "6.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹150",
            address = "BBQ Grill Street"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_mutton_food_15,
            title = "Souper Bowl Mutton Soup",
            price = "280",
            restaurantName = "Souper Bowl",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹28",
            address = "Soup Corner Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_mutton_food_16,
            title = "Paya Special Mutton Paya",
            price = "390",
            restaurantName = "Paya Special",
            rating = "4.5",
            deliveryTime = "40-45 mins",
            distance = "4.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹58",
            address = "Paya Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_mutton_food_17,
            title = "Chop House Mutton Chops",
            price = "410",
            restaurantName = "Chop House",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹82",
            address = "Chop Grill Road"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_mutton_food_18,
            title = "Awadhi Kitchen Rezala",
            price = "480",
            restaurantName = "Awadhi Kitchen",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹72",
            address = "Awadhi Street, Lucknowi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_mutton_food_19,
            title = "Pao Bhaji House Keema Pao",
            price = "340",
            restaurantName = "Pao Bhaji House",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "2.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹34",
            address = "Pao Bhaji Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_mutton_food_20,
            title = "Kebab Mahal Malai Mutton",
            price = "430",
            restaurantName = "Kebab Mahal",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹86",
            address = "Kebab Mahal Road"
        )
    )
    Column {
        muttonRestaurantItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun KebabsDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val kebabDietFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),
                // Kebab types with left icons
                FilterChip(
                    id = "seekh_kebab",
                    text = "Seekh Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_seekh_kebab
                ),
                FilterChip(
                    id = "shami_kebab",
                    text = "Shami Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_shami_kebab
                ),
                FilterChip(
                    id = "chicken_kebab",
                    text = "Chicken Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_kebab
                ),
                FilterChip(
                    id = "vegetable_kebab",
                    text = "Vegetable Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_veg_kebab
                ),
                FilterChip(
                    id = "fish_kebab",
                    text = "Fish Kebab",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fish_kebab
                ),
                // Cooking style/text-only filters
                FilterChip(
                    id = "tandoori",
                    text = "Tandoori",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grilled",
                    text = "Grilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fried",
                    text = "Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "skewered",
                    text = "Skewered",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "minced",
                    text = "Minced",
                    type = FilterType.TEXT_ONLY
                ),
                // Spice level
                FilterChip(
                    id = "mild_spice",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "medium_spice",
                    text = "Medium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                // Diet preferences
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_friendly",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                // Price range
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_400",
                    text = "Under ₹400",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "premium",
                    text = "Premium",
                    type = FilterType.TEXT_ONLY
                ),
                // Sort option
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = kebabDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val kebabFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_chicken_seekh_kebab_diet,
                title = "Chicken Seekh Kebab",
                price = "280",
                restaurantName = "Kebab Junction",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "20%",
                discountAmount = "up to ₹60",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mutton_galouti_kebab_diet,
                title = "Mutton Galouti Kebab",
                price = "350",
                restaurantName = "Awadhi House",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "3.2 km",
                discount = "25%",
                discountAmount = "up to ₹90",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_chicken_malai_tikka_diet,
                title = "Chicken Malai Tikka",
                price = "320",
                restaurantName = "Tandoori Nation",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "3.8 km",
                discount = "15%",
                discountAmount = "up to ₹48",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_hariyali_chicken_tikka_diet,
                title = "Hariyali Chicken Tikka",
                price = "300",
                restaurantName = "Green Spice Grill",
                rating = "4.5",
                deliveryTime = "22-28 mins",
                distance = "2.5 km",
                discount = "10%",
                discountAmount = "up to ₹30",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mutton_boti_kebab_diet,
                title = "Mutton Boti Kebab",
                price = "360",
                restaurantName = "Royal Mughlai",
                rating = "4.7",
                deliveryTime = "35-40 mins",
                distance = "4.0 km",
                discount = "20%",
                discountAmount = "up to ₹72",
                address = "Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_chicken_tandoori_tikka_diet,
                title = "Chicken Tandoori Tikka",
                price = "330",
                restaurantName = "Tikka Factory",
                rating = "4.6",
                deliveryTime = "25-32 mins",
                distance = "3.0 km",
                discount = "15%",
                discountAmount = "up to ₹55",
                address = "Delhi"
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = kebabFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }
//
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    // Sample data based on the provided images

    val kebabRestaurantItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.restaurant_image_kebab_food_1,
            title = "Kebab Junction Chicken Seekh Kebabs",
            price = "280",
            restaurantName = "Kebab Junction",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹56",
            address = "Kebab Street Corner"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.restaurant_image_kebab_food_2,
            title = "Awadhi House Mutton Galouti Kebab",
            price = "350",
            restaurantName = "Awadhi House",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "3.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹88",
            address = "Awadhi Gali, Lucknowi Street"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.restaurant_image_kebab_food_3,
            title = "Tandoori Nation Chicken Malai Tikka",
            price = "320",
            restaurantName = "Tandoori Nation",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹48",
            address = "Tandoori Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.restaurant_image_kebab_food_4,
            title = "Green Spice Hariyali Chicken Tikka",
            price = "300",
            restaurantName = "Green Spice Grill",
            rating = "4.5",
            deliveryTime = "22-28 mins",
            distance = "2.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹30",
            address = "Green Avenue Road"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.restaurant_image_kebab_food_5,
            title = "Royal Mughlai Mutton Boti Kebab",
            price = "360",
            restaurantName = "Royal Mughlai",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹72",
            address = "Mughlai Street, Old Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.restaurant_image_kebab_food_6,
            title = "Tikka Factory Chicken Tandoori Tikka",
            price = "330",
            restaurantName = "Tikka Factory",
            rating = "4.6",
            deliveryTime = "25-32 mins",
            distance = "3.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹55",
            address = "Factory Road"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.restaurant_image_kebab_food_7,
            title = "Sultan’s Grills Afghani Kebab",
            price = "390",
            restaurantName = "Sultan’s Grills",
            rating = "4.7",
            deliveryTime = "30-35 mins",
            distance = "3.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹78",
            address = "Afghan Grill Street"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.restaurant_image_kebab_food_8,
            title = "Mughlai Darbar Shami Kebabs",
            price = "310",
            restaurantName = "Mughlai Darbar",
            rating = "4.4",
            deliveryTime = "25-30 mins",
            distance = "2.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹31",
            address = "Darbar Chowk"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.restaurant_image_kebab_food_9,
            title = "BBQ Nation Chicken Reshmi Kebab",
            price = "340",
            restaurantName = "BBQ Nation",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹68",
            address = "BBQ Grill Road"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.restaurant_image_kebab_food_10,
            title = "Nawabi Kitchen Chicken Tikka Roll",
            price = "260",
            restaurantName = "Nawabi Kitchen",
            rating = "4.3",
            deliveryTime = "20-25 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹26",
            address = "Nawabi Corner"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.restaurant_image_kebab_food_11,
            title = "Spice Hub Tandoori Chicken",
            price = "350",
            restaurantName = "Spice Hub",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹53",
            address = "Spice Market Road"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.restaurant_image_kebab_food_12,
            title = "Awadhi Special Galouti Kebab Platter",
            price = "420",
            restaurantName = "Awadhi Special",
            rating = "4.8",
            deliveryTime = "30-35 mins",
            distance = "3.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹84",
            address = "Lucknowi Food Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.restaurant_image_kebab_food_13,
            title = "Royal BBQ Mutton Sheekh Platter",
            price = "470",
            restaurantName = "Royal BBQ",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.7 km",
            discount = "25% OFF",
            discountAmount = "up to ₹118",
            address = "BBQ Grill Street"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.restaurant_image_kebab_food_14,
            title = "Kebab Mahal Malai Chicken",
            price = "310",
            restaurantName = "Kebab Mahal",
            rating = "4.6",
            deliveryTime = "25-30 mins",
            distance = "3.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹62",
            address = "Kebab Mahal Road"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.restaurant_image_kebab_food_15,
            title = "Lebanese House Shawarma Chicken",
            price = "260",
            restaurantName = "Lebanese House",
            rating = "4.4",
            deliveryTime = "20-25 mins",
            distance = "2.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹26",
            address = "Lebanese Street"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.restaurant_image_kebab_food_16,
            title = "Kolkata Rolls Chicken Kebab Roll",
            price = "230",
            restaurantName = "Kolkata Rolls",
            rating = "4.3",
            deliveryTime = "18-22 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹23",
            address = "Rolls Gali"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.restaurant_image_kebab_food_17,
            title = "Biryani Darbar Kebab & Biryani Combo",
            price = "380",
            restaurantName = "Biryani Darbar",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "3.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹76",
            address = "Darbar Road"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.restaurant_image_kebab_food_18,
            title = "Zaitoon Grill Arabian Kebab Platter",
            price = "520",
            restaurantName = "Zaitoon Grill",
            rating = "4.8",
            deliveryTime = "40-45 mins",
            distance = "5.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹130",
            address = "Arabian Food Street"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.restaurant_image_kebab_food_19,
            title = "Fusion Tadka Peri-Peri Chicken Tikka",
            price = "340",
            restaurantName = "Fusion Tadka",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹34",
            address = "Fusion Lane"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.restaurant_image_kebab_food_20,
            title = "Muglai Treat Royal Kebabs Mix",
            price = "450",
            restaurantName = "Mughlai Treat",
            rating = "4.7",
            deliveryTime = "35-40 mins",
            distance = "4.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹90",
            address = "Royal Mughlai Street"
        )
    )

    Column {
        kebabRestaurantItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun HealthySnacksPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val healthySnacksFilters = FilterConfig(
            filters = listOf(
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // Healthy snack types WITH icons
                FilterChip(
                    id = "nuts_seeds",
                    text = "Nuts & Seeds",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_nuts_seeds
                ),
                FilterChip(
                    id = "fruit_chips",
                    text = "Fruit Chips",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruit_chips
                ),
                FilterChip(
                    id = "protein_bars",
                    text = "Protein Bars",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_bar
                ),
                FilterChip(
                    id = "yogurt",
                    text = "Yogurt",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_yogurt
                ),
                FilterChip(
                    id = "granola",
                    text = "Granola",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_granola
                ),
                FilterChip(
                    id = "rice_cakes",
                    text = "Rice Cakes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_rice_cake
                ),

                // Diet preferences - TEXT ONLY
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "organic",
                    text = "Organic",
                    type = FilterType.TEXT_ONLY
                ),

                // Health benefits - TEXT ONLY
                FilterChip(
                    id = "energy_boosting",
                    text = "Energy Boosting",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gut_healthy",
                    text = "Gut Healthy",
                    type = FilterType.TEXT_ONLY
                ),

                // Preparation types - TEXT ONLY
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_preservatives",
                    text = "No Preservatives",
                    type = FilterType.TEXT_ONLY
                ),

                // Price range - TEXT ONLY
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),

                // Sort option
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = healthySnacksFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val healthySnacksItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_nuts_mix_snack,
                title = "Premium Mixed Nuts",
                price = "280",
                restaurantName = "Nature's Basket",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹56",
                address = "Health Hub, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_protein_bar_snack,
                title = "Chocolate Protein Bar",
                price = "120",
                restaurantName = "Protein Power",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.2 km",
                discount = "15%",
                discountAmount = "up to ₹18",
                address = "Fitness Zone, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_fruit_chips_snack,
                title = "Apple Cinnamon Chips",
                price = "180",
                restaurantName = "Fruitful Delights",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "3.0 km",
                discount = "10%",
                discountAmount = "up to ₹18",
                address = "Organic Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_greek_yogurt_snack,
                title = "Greek Yogurt Bowl",
                price = "220",
                restaurantName = "Yogurt Culture",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "25%",
                discountAmount = "up to ₹55",
                address = "Wellness Center, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_roasted_chickpeas_snack,
                title = "Spicy Roasted Chickpeas",
                price = "160",
                restaurantName = "Crunchy Munchies",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "2.5 km",
                discount = "20%",
                discountAmount = "up to ₹32",
                address = "Healthy Bites, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_granola_bowl_snack,
                title = "Berry Granola Bowl",
                price = "240",
                restaurantName = "Breakfast Club",
                rating = "4.7",
                deliveryTime = "22-28 mins",
                distance = "2.8 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Morning Fresh, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = healthySnacksItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val healthySnackItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.snack_image_healthy_snack_1,
            title = "Green Protein Salad Bowl",
            price = "220",
            restaurantName = "Health Hub",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹33",
            address = "Wellness Street"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.snack_image_healthy_snack_2,
            title = "Quinoa & Avocado Power Bowl",
            price = "280",
            restaurantName = "Organic Kitchen",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹56",
            address = "Organic Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.snack_image_healthy_snack_3,
            title = "Greek Yogurt Berry Parfait",
            price = "180",
            restaurantName = "Fit Fuel",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Fitness Corner"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.snack_image_healthy_snack_4,
            title = "Protein Energy Balls (6 pcs)",
            price = "160",
            restaurantName = "Nutri Bites",
            rating = "4.5",
            deliveryTime = "15-20 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Health Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.snack_image_healthy_snack_5,
            title = "Sweet Potato Fries Bowl",
            price = "190",
            restaurantName = "Clean Eats",
            rating = "4.4",
            deliveryTime = "18-22 mins",
            distance = "2.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Clean Food Street"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.snack_image_healthy_snack_6,
            title = "Hummus & Veggie Platter",
            price = "240",
            restaurantName = "Mediterranean Fresh",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "2.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹48",
            address = "Mediterranean Road"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.snack_image_healthy_snack_7,
            title = "Chia Seed Pudding Bowl",
            price = "150",
            restaurantName = "Superfood Station",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹23",
            address = "Superfood Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.snack_image_healthy_snack_8,
            title = "Sprout Salad with Lemon Dressing",
            price = "130",
            restaurantName = "Salad Bar",
            rating = "4.3",
            deliveryTime = "12-16 mins",
            distance = "1.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Salad Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.snack_image_healthy_snack_9,
            title = "Almond Butter & Banana Toast",
            price = "170",
            restaurantName = "Toast House",
            rating = "4.5",
            deliveryTime = "14-19 mins",
            distance = "1.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹26",
            address = "Toast Corner"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.snack_image_healthy_snack_10,
            title = "Protein Bar (Assorted Flavors)",
            price = "120",
            restaurantName = "Energy Boost",
            rating = "4.4",
            deliveryTime = "10-14 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Energy Street"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.snack_image_healthy_snack_11,
            title = "Avocado Toast with Microgreens",
            price = "210",
            restaurantName = "Avocado Heaven",
            rating = "4.7",
            deliveryTime = "16-21 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Avocado Road"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.snack_image_healthy_snack_12,
            title = "Veggie Spring Rolls (Baked)",
            price = "190",
            restaurantName = "Light Bites",
            rating = "4.5",
            deliveryTime = "18-23 mins",
            distance = "2.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹29",
            address = "Light Food Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.snack_image_healthy_snack_13,
            title = "Fruit & Nut Trail Mix Bowl",
            price = "140",
            restaurantName = "Trail Mix Co.",
            rating = "4.6",
            deliveryTime = "11-16 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Trail Mix Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.snack_image_healthy_snack_14,
            title = "Cottage Cheese Salad Bowl",
            price = "230",
            restaurantName = "Protein Palace",
            rating = "4.7",
            deliveryTime = "17-22 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹46",
            address = "Protein Street"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.snack_image_healthy_snack_15,
            title = "Detox Green Smoothie",
            price = "160",
            restaurantName = "Smoothie Bar",
            rating = "4.5",
            deliveryTime = "8-12 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Smoothie Corner"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.snack_image_healthy_snack_16,
            title = "Roasted Chickpea Snack Pack",
            price = "110",
            restaurantName = "Crunchy Bites",
            rating = "4.3",
            deliveryTime = "13-17 mins",
            distance = "1.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Crunchy Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.snack_image_healthy_snack_17,
            title = "Mushroom & Spinach Quesadilla",
            price = "200",
            restaurantName = "Veggie Delight",
            rating = "4.6",
            deliveryTime = "19-24 mins",
            distance = "2.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Veggie Lane"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.snack_image_healthy_snack_18,
            title = "Oatmeal with Fresh Fruits",
            price = "170",
            restaurantName = "Breakfast Bowls",
            rating = "4.4",
            deliveryTime = "14-19 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹26",
            address = "Breakfast Street"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.snack_image_healthy_snack_19,
            title = "Zucchini Noodles with Pesto",
            price = "250",
            restaurantName = "Zoodle Zone",
            rating = "4.7",
            deliveryTime = "21-26 mins",
            distance = "2.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Zoodle Avenue"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.snack_image_healthy_snack_20,
            title = "Edamame with Himalayan Salt",
            price = "135",
            restaurantName = "Soy Special",
            rating = "4.5",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Soybean Road"
        )
    )
    Column {
        healthySnackItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun LowCaloriePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val lowCalorieFoodFilters = FilterConfig(
            filters = listOf(

                // 1. Filters dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. Low Calorie Categories (WITH LEFT ICON)
                FilterChip(
                    id = "low_cal_snacks",
                    text = "Low Cal Snacks",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_low_calorie_food
                ),
                FilterChip(
                    id = "light_meals",
                    text = "Light Meals",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_light_meal
                ),
                FilterChip(
                    id = "healthy_bars",
                    text = "Healthy Bars",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_healthy_bar
                ),

                // 3. Diet Type (MIX of with-left-icon + text-only)
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. Health Benefits (TEXT ONLY)
                FilterChip(
                    id = "weight_loss",
                    text = "Weight Loss",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "energy_boosting",
                    text = "Energy Boosting",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. Preparation Methods (TEXT ONLY)
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_preservatives",
                    text = "No Preservatives",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. Price Range (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )

        FilterButtonFood(
            filterConfig = lowCalorieFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val lowCalorieFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_lowcal_mixed_fruits_diet,
                title = "Fresh Mixed Fruit Bowl",
                price = "150",
                restaurantName = "Healthy Harvest",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹22",
                address = "Green Valley, Delhi"
            ),

            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_lowcal_greek_yogurt_diet,
                title = "Low-Fat Greek Yogurt",
                price = "120",
                restaurantName = "Yogurt Lab",
                rating = "4.7",
                deliveryTime = "12-18 mins",
                distance = "1.6 km",
                discount = "10%",
                discountAmount = "up to ₹12",
                address = "Fitness Hub, Delhi"
            ),

            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_lowcal_salad_bowl_diet,
                title = "Cucumber Detox Salad",
                price = "180",
                restaurantName = "Fresh Greens",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "2.3 km",
                discount = "20%",
                discountAmount = "up to ₹36",
                address = "Wellness Street, Delhi"
            ),

            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_lowcal_air_fried_chips_diet,
                title = "Air-Fried Veggie Chips",
                price = "130",
                restaurantName = "Crunch Fit",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "2.0 km",
                discount = "10%",
                discountAmount = "up to ₹13",
                address = "Healthy Market, Delhi"
            ),

            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_lowcal_chia_pudding_diet,
                title = "Chia Seed Pudding",
                price = "160",
                restaurantName = "Superfood Café",
                rating = "4.8",
                deliveryTime = "22-28 mins",
                distance = "2.5 km",
                discount = "12%",
                discountAmount = "up to ₹19",
                address = "Organic Square, Delhi"
            ),

            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_lowcal_oats_bowl_diet,
                title = "Honey Oats Bowl",
                price = "140",
                restaurantName = "Morning Fuel",
                rating = "4.5",
                deliveryTime = "16-22 mins",
                distance = "1.8 km",
                discount = "15%",
                discountAmount = "up to ₹21",
                address = "Breakfast Lane, Delhi"
            ),
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
//            heading = "Popular Dishes",
//            subtitle = "Scroll to see more delicious options",
            foodItems = lowCalorieFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val lowCalorieFoodItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.lowcal_image_food_1,
            title = "Fresh Fruit Detox Bowl",
            price = "150",
            restaurantName = "Nature Fresh",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Green Valley"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.lowcal_image_food_2,
            title = "Low-Calorie Greek Yogurt Parfait",
            price = "180",
            restaurantName = "Yogurt Culture",
            rating = "4.7",
            deliveryTime = "14-20 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Healthy Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.lowcal_image_food_3,
            title = "Cucumber & Mint Salad",
            price = "130",
            restaurantName = "Fresh Greens",
            rating = "4.5",
            deliveryTime = "10-16 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Garden Street"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.lowcal_image_food_4,
            title = "Veggie Air-Fried Chips",
            price = "140",
            restaurantName = "Crunch Fit",
            rating = "4.6",
            deliveryTime = "16-22 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Fitness Road"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.lowcal_image_food_5,
            title = "Chia Seed Pudding (Low Sugar)",
            price = "160",
            restaurantName = "Superfood Station",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Wellness Street"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.lowcal_image_food_6,
            title = "Honey Oats Breakfast Bowl",
            price = "140",
            restaurantName = "Morning Fresh",
            rating = "4.4",
            deliveryTime = "15-20 mins",
            distance = "1.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Breakfast Avenue"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.lowcal_image_food_7,
            title = "Sprouts & Veggie Mix Bowl",
            price = "125",
            restaurantName = "Nutri Mix",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Nutri Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.lowcal_image_food_8,
            title = "Baked Multi-Grain Sticks",
            price = "150",
            restaurantName = "BakeLite",
            rating = "4.5",
            deliveryTime = "16-22 mins",
            distance = "2.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹23",
            address = "Baker Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.lowcal_image_food_9,
            title = "Detox Green Smoothie",
            price = "170",
            restaurantName = "Smoothie Bar",
            rating = "4.7",
            deliveryTime = "8-14 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Smoothie Corner"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.lowcal_image_food_10,
            title = "Roasted Lemon Chickpeas",
            price = "120",
            restaurantName = "Crunchy Bites",
            rating = "4.4",
            deliveryTime = "12-16 mins",
            distance = "1.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Crunch Street"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.lowcal_image_food_11,
            title = "Fresh Veggie Spring Rolls (Baked)",
            price = "160",
            restaurantName = "Light Bites",
            rating = "4.5",
            deliveryTime = "18-23 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Light Street"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.lowcal_image_food_12,
            title = "Avocado Toast (Low Cal)",
            price = "190",
            restaurantName = "Avocado Heaven",
            rating = "4.7",
            deliveryTime = "14-20 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹38",
            address = "Avocado Road"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.lowcal_image_food_13,
            title = "Berry Infused Detox Water",
            price = "110",
            restaurantName = "Hydrate+",
            rating = "4.3",
            deliveryTime = "8-12 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Hydration Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.lowcal_image_food_14,
            title = "Zucchini Noodles with Herbs",
            price = "200",
            restaurantName = "Zoodle Zone",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "2.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Zoodle Avenue"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.lowcal_image_food_15,
            title = "Protein-Rich Lentil Soup",
            price = "150",
            restaurantName = "Soup Co.",
            rating = "4.5",
            deliveryTime = "15-22 mins",
            distance = "1.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Soup Street"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.lowcal_image_food_16,
            title = "Low Calorie Veggie Wrap",
            price = "180",
            restaurantName = "Wrap Station",
            rating = "4.4",
            deliveryTime = "14-20 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Wrap Lane"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.lowcal_image_food_17,
            title = "Oatmeal with Berries",
            price = "160",
            restaurantName = "Healthy Bowls",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "1.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Bowl Street"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.lowcal_image_food_18,
            title = "Quinoa Veggie Salad Bowl",
            price = "210",
            restaurantName = "Organic Kitchen",
            rating = "4.7",
            deliveryTime = "20-26 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Organic Lane"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.lowcal_image_food_19,
            title = "Steamed Broccoli with Herbs",
            price = "130",
            restaurantName = "Green Plates",
            rating = "4.4",
            deliveryTime = "10-15 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Healthy Corner"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.lowcal_image_food_20,
            title = "Warm Lemon Detox Soup",
            price = "140",
            restaurantName = "Detox Kitchen",
            rating = "4.5",
            deliveryTime = "15-22 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Detox Avenue"
        )
    )

    Column {
        lowCalorieFoodItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun VeganPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val veganFoodFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown (Trigger)
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. SPECIALIZED FILTERS (WITH ICONS) - Important dietary categories
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_diet
                ),
                FilterChip(
                    id = "low_cal_snacks",
                    text = "Low Cal Snacks",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_low_calorie_food_vegan
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free_vegan
                ),

                // 3. DIETARY RESTRICTIONS (TEXT ONLY)
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_preservatives",
                    text = "No Preservatives",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. MEAL CATEGORIES (TEXT ONLY)
                FilterChip(
                    id = "light_meals",
                    text = "Light Meals",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy_bars",
                    text = "Healthy Bars",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. HEALTH BENEFITS (TEXT ONLY)
                FilterChip(
                    id = "weight_loss",
                    text = "Weight Loss",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "energy_boosting",
                    text = "Energy Boosting",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. PREPARATION METHODS (TEXT ONLY)
                FilterChip(
                    id = "air_fried",
                    text = "Air Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = veganFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val veganFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_vegan_buddha_bowl,
                title = "Rainbow Buddha Bowl",
                price = "220",
                restaurantName = "Plant Power Kitchen",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Eco Street, Delhi",
            ),

            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_vegan_avocado_toast,
                title = "Smoked Avocado Toast",
                price = "180",
                restaurantName = "Green Leaf Café",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹27",
                address = "Vegan Hub, Delhi",
            ),

            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_vegan_quinoa_salad,
                title = "Lemon Herb Quinoa Salad",
                price = "190",
                restaurantName = "Earth Kitchen",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.8 km",
                discount = "10%",
                discountAmount = "up to ₹19",
                address = "Organic Square, Delhi",
            ),

            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_vegan_chickpea_wrap,
                title = "Spiced Chickpea Wrap",
                price = "160",
                restaurantName = "Vegan Delight",
                rating = "4.6",
                deliveryTime = "12-16 mins",
                distance = "0.9 km",
                discount = "25%",
                discountAmount = "up to ₹40",
                address = "Health Lane, Delhi",
            ),

            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_vegan_berry_smoothie,
                title = "Mixed Berry Protein Smoothie",
                price = "150",
                restaurantName = "Smoothie Bar",
                rating = "4.7",
                deliveryTime = "10-15 mins",
                distance = "1.0 km",
                discount = "15%",
                discountAmount = "up to ₹22",
                address = "Fitness Corner, Delhi",
            ),

            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_vegan_mushroom_tacos,
                title = "Portobello Mushroom Tacos",
                price = "210",
                restaurantName = "Mexican Greens",
                rating = "4.9",
                deliveryTime = "22-28 mins",
                distance = "2.2 km",
                discount = "20%",
                discountAmount = "up to ₹42",
                address = "Global Cuisine Street, Delhi",
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
            foodItems = veganFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )

    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val veganFoodItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.vegan_image_food_1,
            title = "Rainbow Buddha Bowl",
            price = "220",
            restaurantName = "Plant Power Kitchen",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹44",
            address = "Vegan Street"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.vegan_image_food_2,
            title = "Smoked Avocado Toast",
            price = "180",
            restaurantName = "Green Leaf Café",
            rating = "4.7",
            deliveryTime = "14-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Organic Corner"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.vegan_image_food_3,
            title = "Lemon Herb Quinoa Salad",
            price = "190",
            restaurantName = "Earth Kitchen",
            rating = "4.8",
            deliveryTime = "16-22 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Eco Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.vegan_image_food_4,
            title = "Spiced Chickpea Wrap",
            price = "160",
            restaurantName = "Vegan Delight",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "25% OFF",
            discountAmount = "up to ₹40",
            address = "Plant Avenue"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.vegan_image_food_5,
            title = "Mixed Berry Protein Smoothie",
            price = "150",
            restaurantName = "Smoothie Bar",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Berry Lane"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.vegan_image_food_6,
            title = "Portobello Mushroom Tacos",
            price = "210",
            restaurantName = "Mexican Greens",
            rating = "4.9",
            deliveryTime = "20-26 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Global Street"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.vegan_image_food_7,
            title = "Avocado Chocolate Pudding",
            price = "140",
            restaurantName = "Sweet Vegan",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹28",
            address = "Dessert Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.vegan_image_food_8,
            title = "Fresh Spring Rolls",
            price = "170",
            restaurantName = "Asian Greens",
            rating = "4.7",
            deliveryTime = "18-24 mins",
            distance = "1.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Asian Avenue"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.vegan_image_food_9,
            title = "Vegan Black Bean Burger",
            price = "230",
            restaurantName = "Burger Plant",
            rating = "4.6",
            deliveryTime = "22-28 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹34",
            address = "Burger Street"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.vegan_image_food_10,
            title = "Coconut Curry Bowl",
            price = "200",
            restaurantName = "Curry Leaves",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹20",
            address = "Spice Lane"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.vegan_image_food_11,
            title = "Hummus & Veggie Platter",
            price = "185",
            restaurantName = "Mediterranean Bites",
            rating = "4.7",
            deliveryTime = "16-22 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Mediterranean Road"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.vegan_image_food_12,
            title = "Tofu Stir Fry Bowl",
            price = "195",
            restaurantName = "Tofu Express",
            rating = "4.5",
            deliveryTime = "18-23 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Stir Fry Lane"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.vegan_image_food_13,
            title = "Vegan Lentil Soup",
            price = "160",
            restaurantName = "Soup & Co",
            rating = "4.6",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Soup Street"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.vegan_image_food_14,
            title = "Sweet Potato Buddha Bowl",
            price = "210",
            restaurantName = "Bowl Co.",
            rating = "4.8",
            deliveryTime = "20-26 mins",
            distance = "2.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Bowl Avenue"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.vegan_image_food_15,
            title = "Vegan Sushi Rolls",
            price = "240",
            restaurantName = "Green Sushi",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "2.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹36",
            address = "Sushi Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.vegan_image_food_16,
            title = "Chickpea Salad Sandwich",
            price = "175",
            restaurantName = "Sandwich Plant",
            rating = "4.6",
            deliveryTime = "14-19 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Sandwich Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.vegan_image_food_17,
            title = "Vegan Pasta Primavera",
            price = "225",
            restaurantName = "Pasta Greens",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "2.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹34",
            address = "Pasta Avenue"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.vegan_image_food_18,
            title = "Vegan Chocolate Brownie",
            price = "130",
            restaurantName = "Guilt-Free Sweets",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹26",
            address = "Dessert Corner"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.vegan_image_food_19,
            title = "Grilled Veggie Skewers",
            price = "190",
            restaurantName = "Grill Garden",
            rating = "4.6",
            deliveryTime = "18-24 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Grill Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.vegan_image_food_20,
            title = "Vegan Protein Power Bowl",
            price = "235",
            restaurantName = "Protein Plant",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹35",
            address = "Protein Lane"
        )
    )
    Column {
        veganFoodItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun ProteinRichPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val proteinRichFoodFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY PROTEIN FILTERS (WITH ICONS)
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_rich
                ),
                FilterChip(
                    id = "vegan_protein",
                    text = "Vegan Protein",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_protein
                ),
                FilterChip(
                    id = "low_carb",
                    text = "Low Carb",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_low_carb
                ),

                // 3. PROTEIN SOURCES (TEXT ONLY)
                FilterChip(
                    id = "whey_protein",
                    text = "Whey Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "plant_protein",
                    text = "Plant Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_based",
                    text = "Egg Based",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. MEAL TYPES (TEXT ONLY)
                FilterChip(
                    id = "post_workout",
                    text = "Post-Workout",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "muscle_building",
                    text = "Muscle Building",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "meal_replacement",
                    text = "Meal Replacement",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. PROTEIN CONTENT (TEXT ONLY)
                FilterChip(
                    id = "20g_plus",
                    text = "20g+ Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "30g_plus",
                    text = "30g+ Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "complete_protein",
                    text = "Complete Protein",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DIETARY (TEXT ONLY)
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "under_400",
                    text = "Under ₹400",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = proteinRichFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val proteinRichFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_protein_chicken_grill,
                title = "Grilled Chicken Breast Bowl",
                price = "280",
                restaurantName = "Protein Power",
                rating = "4.8",
                deliveryTime = "18-22 mins",
                distance = "1.4 km",
                discount = "15%",
                discountAmount = "up to ₹42",
                address = "Fitness Street, Delhi"
            ),

            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_protein_egg_whites,
                title = "Egg White Omelette Platter",
                price = "190",
                restaurantName = "Eggcelent Protein",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.1 km",
                discount = "10%",
                discountAmount = "up to ₹19",
                address = "Protein Lane, Delhi"
            ),

            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_protein_whey_shake,
                title = "Double Chocolate Whey Shake",
                price = "220",
                restaurantName = "Muscle Fuel",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.8 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Gym Road, Delhi"
            ),

            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_protein_tofu_bowl,
                title = "Tofu & Quinoa Power Bowl",
                price = "240",
                restaurantName = "Plant Protein Co.",
                rating = "4.6",
                deliveryTime = "20-25 mins",
                distance = "1.9 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Vegan Protein Hub, Delhi"
            ),

            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_protein_fish_grill,
                title = "Grilled Salmon with Veggies",
                price = "320",
                restaurantName = "Omega Kitchen",
                rating = "4.8",
                deliveryTime = "22-28 mins",
                distance = "2.3 km",
                discount = "10%",
                discountAmount = "up to ₹32",
                address = "Seafood Avenue, Delhi"
            ),

            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_protein_paneer_tikka,
                title = "Protein Paneer Tikka Bowl",
                price = "260",
                restaurantName = "Desi Protein",
                rating = "4.7",
                deliveryTime = "16-21 mins",
                distance = "1.6 km",
                discount = "12%",
                discountAmount = "up to ₹31",
                address = "Indian Protein Corner, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
            foodItems = proteinRichFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val proteinRichFoodItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.protein_image_food_1,
            title = "Grilled Chicken Breast",
            price = "280",
            restaurantName = "Protein Palace",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹56",
            address = "Muscle Street"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.protein_image_food_2,
            title = "Salmon Fillet with Quinoa",
            price = "350",
            restaurantName = "Omega-3 Kitchen",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹52",
            address = "Fish Avenue"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.protein_image_food_3,
            title = "Lean Chicken Steak",
            price = "420",
            restaurantName = "Carnivore Corner",
            rating = "4.7",
            deliveryTime = "20-30 mins",
            distance = "2.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹42",
            address = "Steak Lane"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.protein_image_food_4,
            title = "Egg White Omelette",
            price = "180",
            restaurantName = "Breakfast Protein Bar",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "25% OFF",
            discountAmount = "up to ₹45",
            address = "Egg Street"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.protein_image_food_5,
            title = "Greek Yogurt Parfait",
            price = "160",
            restaurantName = "Dairy Delight",
            rating = "4.5",
            deliveryTime = "10-16 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Yogurt Lane"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.protein_image_food_6,
            title = "Tuna Salad Bowl",
            price = "220",
            restaurantName = "Sea Protein",
            rating = "4.7",
            deliveryTime = "16-22 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹44",
            address = "Ocean Road"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.protein_image_food_7,
            title = "Protein Pancakes",
            price = "190",
            restaurantName = "Gainz Café",
            rating = "4.8",
            deliveryTime = "14-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Gym Street"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.protein_image_food_8,
            title = "Chicken & Brown Rice Bowl",
            price = "240",
            restaurantName = "Fit Food Kitchen",
            rating = "4.7",
            deliveryTime = "18-24 mins",
            distance = "1.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹24",
            address = "Fitness Avenue"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.protein_image_food_9,
            title = "Whey Protein Shake",
            price = "150",
            restaurantName = "Supplement Station",
            rating = "4.6",
            deliveryTime = "8-15 mins",
            distance = "0.8 km",
            discount = "30% OFF",
            discountAmount = "up to ₹45",
            address = "Supplement Lane"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.protein_image_food_10,
            title = "Turkey Wrap",
            price = "210",
            restaurantName = "Lean Meat Deli",
            rating = "4.5",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Deli Corner"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.protein_image_food_11,
            title = "Cottage Cheese Bowl",
            price = "170",
            restaurantName = "Paneer Palace",
            rating = "4.7",
            deliveryTime = "13-19 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Dairy Avenue"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.protein_image_food_12,
            title = "Protein Burger",
            price = "290",
            restaurantName = "Burger Gains",
            rating = "4.8",
            deliveryTime = "20-28 mins",
            distance = "2.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Burger Road"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.protein_image_food_13,
            title = "Shrimp Stir Fry",
            price = "320",
            restaurantName = "Seafood Protein",
            rating = "4.9",
            deliveryTime = "19-26 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "Shrimp Lane"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.protein_image_food_14,
            title = "Lentil & Chicken Soup",
            price = "190",
            restaurantName = "Protein Soup Co.",
            rating = "4.6",
            deliveryTime = "16-22 mins",
            distance = "1.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹38",
            address = "Soup Street"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.protein_image_food_15,
            title = "Chicken Protein Bowl",
            price = "380",
            restaurantName = "Carnivore Kitchen",
            rating = "4.8",
            deliveryTime = "22-30 mins",
            distance = "2.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹57",
            address = "Chicken Avenue"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.protein_image_food_16,
            title = "Protein Energy Bars (Pack of 3)",
            price = "120",
            restaurantName = "Snack Protein",
            rating = "4.5",
            deliveryTime = "10-18 mins",
            distance = "1.2 km",
            discount = "25% OFF",
            discountAmount = "up to ₹30",
            address = "Snack Lane"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.protein_image_food_17,
            title = "Chicken Tikka Skewers",
            price = "260",
            restaurantName = "Spice Protein",
            rating = "4.7",
            deliveryTime = "18-25 mins",
            distance = "1.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹52",
            address = "Tikka Corner"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.protein_image_food_18,
            title = "Casein Protein Pudding",
            price = "140",
            restaurantName = "Slow Release Protein",
            rating = "4.6",
            deliveryTime = "12-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Dessert Protein"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.protein_image_food_19,
            title = "Fish & Sweet Potato",
            price = "310",
            restaurantName = "Balanced Protein",
            rating = "4.8",
            deliveryTime = "20-28 mins",
            distance = "2.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹31",
            address = "Balance Road"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.protein_image_food_20,
            title = "Protein Power Platter",
            price = "450",
            restaurantName = "Ultimate Gains",
            rating = "4.9",
            deliveryTime = "25-35 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹67",
            address = "Ultimate Street"
        )
    )
    Column {
        proteinRichFoodItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

// Categories from your list (Dessert through Cold Coffee)
@Composable
fun DessertPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val dessertFoodFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY DESSERT FILTERS (WITH ICONS) - 5 Icon-based filters
                FilterChip(
                    id = "chocolate",
                    text = "Chocolate",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chocolate_dessert_1
                ),
                FilterChip(
                    id = "ice_cream",
                    text = "Ice Cream",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_ice_cream_dessert_1
                ),
                FilterChip(
                    id = "cakes",
                    text = "Cakes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cake_dessert_1
                ),
                FilterChip(
                    id = "fruit_desserts",
                    text = "Fruit Desserts",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruits_dessert
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free_dessert
                ),

                // 3. ADDITIONAL DESSERT TYPES (TEXT ONLY)
                FilterChip(
                    id = "pastries",
                    text = "Pastries",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookies",
                    text = "Cookies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brownies",
                    text = "Brownies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "puddings",
                    text = "Puddings",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "donuts",
                    text = "Donuts",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. FLAVORS (TEXT ONLY)
                FilterChip(
                    id = "vanilla",
                    text = "Vanilla",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "strawberry",
                    text = "Strawberry",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mango",
                    text = "Mango",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "butterscotch",
                    text = "Butterscotch",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caramel",
                    text = "Caramel",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. DIETARY (TEXT ONLY)
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto",
                    text = "Keto",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. OCCASIONS (TEXT ONLY)
                FilterChip(
                    id = "birthday",
                    text = "Birthday",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "anniversary",
                    text = "Anniversary",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "party",
                    text = "Party",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "200_400",
                    text = "₹200 - ₹400",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "400_600",
                    text = "₹400 - ₹600",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_600",
                    text = "Above ₹600",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )

        FilterButtonFood(
            filterConfig = dessertFoodFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val dessertFoodItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_dessert_chocolate_cake,
                title = "Belgian Chocolate Cake",
                price = "320",
                restaurantName = "Sweet Cravings",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "1.8 km",
                discount = "15%",
                discountAmount = "up to ₹48",
                address = "Dessert Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_dessert_ice_cream,
                title = "Premium Ice Cream Sundae",
                price = "180",
                restaurantName = "Frozen Delights",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "10%",
                discountAmount = "up to ₹18",
                address = "Sweet Avenue, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_dessert_brownie,
                title = "Hot Chocolate Brownie",
                price = "220",
                restaurantName = "Brownie House",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "1.4 km",
                discount = "20%",
                discountAmount = "up to ₹44",
                address = "Chocolate Lane, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_dessert_gulab_jamun,
                title = "Gulab Jamun with Rabri",
                price = "160",
                restaurantName = "Mithai Wala",
                rating = "4.6",
                deliveryTime = "18-22 mins",
                distance = "1.5 km",
                discount = "12%",
                discountAmount = "up to ₹19",
                address = "Old Sweet Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_dessert_pastry,
                title = "Fresh Cream Pastry Pack",
                price = "240",
                restaurantName = "The Bakery Shop",
                rating = "4.7",
                deliveryTime = "22-28 mins",
                distance = "2.1 km",
                discount = "15%",
                discountAmount = "up to ₹36",
                address = "Bakery Road, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_dessert_fruit_bowl,
                title = "Fresh Fruit Bowl with Honey",
                price = "200",
                restaurantName = "Healthy Sweets",
                rating = "4.5",
                deliveryTime = "15-20 mins",
                distance = "1.3 km",
                discount = "10%",
                discountAmount = "up to ₹20",
                address = "Fresh Market, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
            foodItems = dessertFoodItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val dessertFoodItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.dessert_image_1,
            title = "Belgian Dark Chocolate Cake",
            price = "320",
            restaurantName = "The Chocolate Room",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "1.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹64",
            address = "Chocolate Avenue"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.dessert_image_2,
            title = "Premium Ice Cream Sundae",
            price = "180",
            restaurantName = "Frozen Delights",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Ice Cream Lane"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.dessert_image_3,
            title = "Hot Chocolate Brownie with Ice Cream",
            price = "220",
            restaurantName = "Brownie Heaven",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹22",
            address = "Brownie Street"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.dessert_image_4,
            title = "Gulab Jamun with Rabri",
            price = "160",
            restaurantName = "Mithai Wala",
            rating = "4.6",
            deliveryTime = "18-22 mins",
            distance = "1.5 km",
            discount = "25% OFF",
            discountAmount = "up to ₹40",
            address = "Sweet Market"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.dessert_image_5,
            title = "Fresh Fruit Pastry",
            price = "240",
            restaurantName = "The Bakery Shop",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹36",
            address = "Bakery Road"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.dessert_image_6,
            title = "Fresh Fruit Bowl with Honey",
            price = "200",
            restaurantName = "Healthy Sweets",
            rating = "4.5",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Fresh Market"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.dessert_image_7,
            title = "Rasmalai (2 pieces)",
            price = "190",
            restaurantName = "Bengal Sweets",
            rating = "4.8",
            deliveryTime = "16-22 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Bengal Lane"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.dessert_image_8,
            title = "Tiramisu Cup",
            price = "280",
            restaurantName = "Italian Desserts",
            rating = "4.7",
            deliveryTime = "24-30 mins",
            distance = "2.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹28",
            address = "Italian Street"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.dessert_image_9,
            title = "Jalebi with Rabri",
            price = "150",
            restaurantName = "Old Delhi Sweets",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "30% OFF",
            discountAmount = "up to ₹45",
            address = "Chandni Chowk"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.dessert_image_10,
            title = "Red Velvet Pastry",
            price = "210",
            restaurantName = "Cake Factory",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "1.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Cake Avenue"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.dessert_image_11,
            title = "Kulfi Falooda",
            price = "170",
            restaurantName = "Royal Kulfi",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Kulfi Lane"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.dessert_image_12,
            title = "Chocolate Mousse Jar",
            price = "190",
            restaurantName = "Dessert Jar",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Dessert Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.dessert_image_13,
            title = "Gajar Ka Halwa",
            price = "200",
            restaurantName = "Desi Mithai",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹20",
            address = "Winter Special Road"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.dessert_image_14,
            title = "Waffle with Nutella",
            price = "230",
            restaurantName = "Waffle House",
            rating = "4.6",
            deliveryTime = "16-22 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹46",
            address = "Waffle Corner"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.dessert_image_15,
            title = "Creme Brulee",
            price = "280",
            restaurantName = "French Patisserie",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "2.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹42",
            address = "French Quarter"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.dessert_image_16,
            title = "Assorted Macarons (6 pcs)",
            price = "350",
            restaurantName = "Macaron Paradise",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹87",
            address = "Paris Lane"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.dessert_image_17,
            title = "Motichoor Ladoo (4 pcs)",
            price = "140",
            restaurantName = "Brijwasi Sweets",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹28",
            address = "Sweet Circle"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.dessert_image_18,
            title = "Cheesecake Slice",
            price = "260",
            restaurantName = "Cheesecake & Co.",
            rating = "4.6",
            deliveryTime = "20-26 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹39",
            address = "Cheese Road"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.dessert_image_19,
            title = "Kheer Rice Pudding",
            price = "130",
            restaurantName = "Traditional Desserts",
            rating = "4.8",
            deliveryTime = "10-16 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Tradition Street"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.dessert_image_20,
            title = "Assorted Dessert Platter",
            price = "450",
            restaurantName = "Dessert Kingdom",
            rating = "4.9",
            deliveryTime = "25-35 mins",
            distance = "2.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹67",
            address = "Royal Dessert Road"
        )
    )
    Column {
        dessertFoodItems .forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun VegMealPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val vegMealDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY VEG MEAL FILTERS (WITH ICONS) - 5 Icon-based filters
                FilterChip(
                    id = "north_indian",
                    text = "North Indian",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_north_indian_thali
                ),
                FilterChip(
                    id = "south_indian",
                    text = "South Indian",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_south_indian_thali
                ),
                FilterChip(
                    id = "thali",
                    text = "Thali",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_thali_meal
                ),
                FilterChip(
                    id = "rice_bowl",
                    text = "Rice Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_rice_bowl
                ),
                FilterChip(
                    id = "salad_bowl",
                    text = "Salad Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salad_bowl
                ),

                // 3. ADDITIONAL MEAL TYPES (TEXT ONLY)
                FilterChip(
                    id = "curry",
                    text = "Curry",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dosa",
                    text = "Dosa",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "idli",
                    text = "Idli",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pav_bhaji",
                    text = "Pav Bhaji",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pulao",
                    text = "Pulao",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "paratha",
                    text = "Paratha",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chaat",
                    text = "Chaat",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. PROTEIN SOURCES (TEXT ONLY)
                FilterChip(
                    id = "paneer",
                    text = "Paneer",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tofu",
                    text = "Tofu",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lentils",
                    text = "Lentils (Dal)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chickpeas",
                    text = "Chickpeas",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "soy",
                    text = "Soy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mushroom",
                    text = "Mushroom",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. CUISINE STYLES (TEXT ONLY)
                FilterChip(
                    id = "punjabi",
                    text = "Punjabi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gujarati",
                    text = "Gujarati",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bengali",
                    text = "Bengali",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rajasthani",
                    text = "Rajasthani",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "maharashtrian",
                    text = "Maharashtrian",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_fiber",
                    text = "High Fiber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "balanced_meal",
                    text = "Balanced Meal",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. MEAL PREPARATION (TEXT ONLY)
                FilterChip(
                    id = "oil_free",
                    text = "Oil Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "steamed",
                    text = "Steamed",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grilled",
                    text = "Grilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fried",
                    text = "Fried",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. SPICE LEVEL (TEXT ONLY)
                FilterChip(
                    id = "mild",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "medium",
                    text = "Medium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "very_spicy",
                    text = "Very Spicy",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "150_300",
                    text = "₹150 - ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "300_500",
                    text = "₹300 - ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_500",
                    text = "Above ₹500",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )

        FilterButtonFood(
            filterConfig = vegMealDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val vegMealDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_veg_north_indian_thali,
                title = "North Indian Veg Thali",
                price = "299",
                restaurantName = "Punjabi Dhaba",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "2.3 km",
                discount = "20%",
                discountAmount = "up to ₹60",
                address = "Main Bazaar, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_veg_south_indian_meals,
                title = "South Indian Meals",
                price = "249",
                restaurantName = "Madras Cafe",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "1.8 km",
                discount = "15%",
                discountAmount = "up to ₹37",
                address = "Anna Nagar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_veg_paneer_biryani,
                title = "Paneer Biryani with Raita",
                price = "279",
                restaurantName = "Biryani House",
                rating = "4.6",
                deliveryTime = "30-35 mins",
                distance = "2.1 km",
                discount = "10%",
                discountAmount = "up to ₹28",
                address = "Spice Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_veg_healthy_bowl,
                title = "Quinoa Veg Power Bowl",
                price = "329",
                restaurantName = "Healthy Bites",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "15%",
                discountAmount = "up to ₹49",
                address = "Fitness Plaza, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_veg_rajasthani_thali,
                title = "Rajasthani Thali Special",
                price = "349",
                restaurantName = "Royal Rasoi",
                rating = "4.9",
                deliveryTime = "35-40 mins",
                distance = "3.2 km",
                discount = "25%",
                discountAmount = "up to ₹87",
                address = "Jaipur Road, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_veg_pav_bhaji,
                title = "Butter Pav Bhaji",
                price = "189",
                restaurantName = "Mumbai Street Food",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "10%",
                discountAmount = "up to ₹19",
                address = "Food Street, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
            foodItems = vegMealDietItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val vegMealDietItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.veg_meal_1,
            title = "North Indian Veg Thali",
            price = "299",
            restaurantName = "Punjabi Dhaba",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "1.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Main Bazaar, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.veg_meal_2,
            title = "South Indian Meals",
            price = "249",
            restaurantName = "Madras Cafe",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹37",
            address = "Anna Nagar, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.veg_meal_3,
            title = "Paneer Butter Masala + Naan",
            price = "329",
            restaurantName = "Curry Palace",
            rating = "4.7",
            deliveryTime = "25-30 mins",
            distance = "2.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹33",
            address = "Restaurant Road"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.veg_meal_4,
            title = "Veg Biryani with Raita",
            price = "259",
            restaurantName = "Biryani House",
            rating = "4.6",
            deliveryTime = "30-35 mins",
            distance = "2.0 km",
            discount = "25% OFF",
            discountAmount = "up to ₹65",
            address = "Spice Market"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.veg_meal_5,
            title = "Chole Bhature Combo",
            price = "179",
            restaurantName = "Chole Wala",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Food Street"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.veg_meal_6,
            title = "Dal Makhani + Rice + Salad",
            price = "219",
            restaurantName = "Dal Special",
            rating = "4.5",
            deliveryTime = "20-25 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹44",
            address = "Comfort Food Lane"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.veg_meal_7,
            title = "Masala Dosa with Sambhar",
            price = "149",
            restaurantName = "Dosa Corner",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "South Avenue"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.veg_meal_8,
            title = "Rajasthani Thali",
            price = "349",
            restaurantName = "Royal Rasoi",
            rating = "4.9",
            deliveryTime = "30-35 mins",
            distance = "2.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹35",
            address = "Jaipur Road"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.veg_meal_9,
            title = "Pav Bhaji (Butter)",
            price = "189",
            restaurantName = "Mumbai Street Food",
            rating = "4.6",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "30% OFF",
            discountAmount = "up to ₹57",
            address = "Food Truck Park"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.veg_meal_10,
            title = "Quinoa Veg Power Bowl",
            price = "329",
            restaurantName = "Healthy Bites",
            rating = "4.5",
            deliveryTime = "18-24 mins",
            distance = "1.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹66",
            address = "Fitness Plaza"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.veg_meal_11,
            title = "Gujarati Thali",
            price = "279",
            restaurantName = "Gujarat Bhavan",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "1.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹42",
            address = "Gujarat Colony"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.veg_meal_12,
            title = "Aloo Paratha + Curd",
            price = "159",
            restaurantName = "Paratha Point",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Breakfast Street"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.veg_meal_13,
            title = "Idli-Vada Combo",
            price = "139",
            restaurantName = "South Indian Cafe",
            rating = "4.9",
            deliveryTime = "10-15 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Morning Market"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.veg_meal_14,
            title = "Veg Kolhapuri + Bhakri",
            price = "239",
            restaurantName = "Maharashtra Mishra",
            rating = "4.6",
            deliveryTime = "20-26 mins",
            distance = "1.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹48",
            address = "Kolhapur Road"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.veg_meal_15,
            title = "Mushroom Curry + Rice",
            price = "259",
            restaurantName = "Mushroom House",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹39",
            address = "Mushroom Lane"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.veg_meal_16,
            title = "Bengali Veg Thali",
            price = "289",
            restaurantName = "Bengal Kitchen",
            rating = "4.5",
            deliveryTime = "25-30 mins",
            distance = "2.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹72",
            address = "Bengal Street"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.veg_meal_17,
            title = "Chana Masala + Bhatura",
            price = "169",
            restaurantName = "Chana Bhatura House",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹34",
            address = "Old Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.veg_meal_18,
            title = "Mediterranean Veg Wrap",
            price = "219",
            restaurantName = "Wrap & Roll",
            rating = "4.6",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹33",
            address = "Food Court"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.veg_meal_19,
            title = "Kadhai Paneer + Tandoori Roti",
            price = "309",
            restaurantName = "Kadhai King",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹31",
            address = "Tandoori Lane"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.veg_meal_20,
            title = "Grand Veg Festival Thali",
            price = "399",
            restaurantName = "Grand Bazaar",
            rating = "4.9",
            deliveryTime = "30-35 mins",
            distance = "2.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹60",
            address = "Food Festival Ground"
        )
    )
    Column {
        vegMealDietItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun BowlPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val bowlDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY BOWL DIET FILTERS (WITH ICONS) - Most important categories with visuals
                FilterChip(
                    id = "protein_bowl",
                    text = "Protein Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_bowl
                ),
                FilterChip(
                    id = "green_bowl",
                    text = "Green Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_green_bowl
                ),
                FilterChip(
                    id = "grain_bowl",
                    text = "Grain Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_grain_bowl
                ),
                FilterChip(
                    id = "quinoa_bowl",
                    text = "Quinoa Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_quinoa_bowl
                ),
                FilterChip(
                    id = "acai_bowl",
                    text = "Acai Bowl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_acai_bowl
                ),

                // 3. BOWL BASE (TEXT ONLY)
                FilterChip(
                    id = "rice_base",
                    text = "Rice Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quinoa_base",
                    text = "Quinoa Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kale_base",
                    text = "Kale Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mixed_greens",
                    text = "Mixed Greens",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cauliflower_rice",
                    text = "Cauliflower Rice",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. PROTEIN ADDITIONS (TEXT ONLY)
                FilterChip(
                    id = "grilled_chicken",
                    text = "Grilled Chicken",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "salmon",
                    text = "Salmon",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tofu_protein",
                    text = "Tofu",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggs",
                    text = "Eggs",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "beans",
                    text = "Beans",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lentils_protein",
                    text = "Lentils",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. TOPPINGS & ADD-ONS (TEXT ONLY)
                FilterChip(
                    id = "avocado",
                    text = "Avocado",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "nuts",
                    text = "Nuts",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "seeds",
                    text = "Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fresh_fruits",
                    text = "Fresh Fruits",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "berries",
                    text = "Berries",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "granola",
                    text = "Granola",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DRESSINGS & SAUCES (TEXT ONLY)
                FilterChip(
                    id = "sesame_dressing",
                    text = "Sesame Dressing",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "peanut_sauce",
                    text = "Peanut Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "yogurt_dressing",
                    text = "Yogurt Dressing",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lemon_vinaigrette",
                    text = "Lemon Vinaigrette",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sriracha",
                    text = "Sriracha",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "high_protein_bowl",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb_bowl",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan_bowl",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_bowl",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_bowl",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "paleo_bowl",
                    text = "Paleo",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. CALORIE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_400_cal",
                    text = "Under 400 cal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "400_600_cal",
                    text = "400-600 cal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "600_800_cal",
                    text = "600-800 cal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_800_cal",
                    text = "Above 800 cal",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. CUISINE INSPIRATION (TEXT ONLY)
                FilterChip(
                    id = "asian_bowl",
                    text = "Asian Inspired",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mediterranean_bowl",
                    text = "Mediterranean",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mexican_bowl",
                    text = "Mexican Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hawaiian_bowl",
                    text = "Hawaiian",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )

        FilterButtonFood(
            filterConfig = bowlDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val bowlDietItems = listOf(
            // 1. Protein Power Bowl
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_protein_power_bowl,
                title = "Protein Power Bowl",
                price = "399",
                restaurantName = "Bowl Culture",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "1.8 km",
                discount = "15%",
                discountAmount = "up to ₹60",
                address = "Fitness District, Delhi"
            ),
            // 2. Acai Berry Bowl
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_acai_berry_bowl,
                title = "Acai Berry Bowl",
                price = "449",
                restaurantName = "Healthy Bowl Co",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "2.1 km",
                discount = "10%",
                discountAmount = "up to ₹45",
                address = "Wellness Hub, Delhi"
            ),
            // 3. Quinoa Garden Bowl
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_quinoa_garden_bowl,
                title = "Quinoa Garden Bowl",
                price = "359",
                restaurantName = "Green Theory",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹72",
                address = "Eco Square, Delhi"
            ),
            // 4. Mediterranean Bowl
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_mediterranean_bowl,
                title = "Mediterranean Bowl",
                price = "429",
                restaurantName = "Olive Kitchen",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "2.4 km",
                discount = "15%",
                discountAmount = "up to ₹64",
                address = "Mediterranean Plaza, Delhi"
            ),
            // 5. Hawaiian Poke Bowl
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_hawaiian_poke_bowl,
                title = "Hawaiian Poke Bowl",
                price = "499",
                restaurantName = "Island Bowls",
                rating = "4.9",
                deliveryTime = "30-35 mins",
                distance = "2.7 km",
                discount = "10%",
                discountAmount = "up to ₹50",
                address = "Beachside Ave, Delhi"
            ),
            // 6. Buddha's Delight Bowl
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_buddha_bowl,
                title = "Buddha's Delight Bowl",
                price = "379",
                restaurantName = "Zen Kitchen",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "1.9 km",
                discount = "15%",
                discountAmount = "up to ₹57",
                address = "Peace Complex, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
            foodItems = bowlDietItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Restaurants delivering to you",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =  MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Featured restaurants",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.black
        ),
//            textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth().padding(start=12.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))

    // Sample data based on the provided images
    val bowlDietItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.bowl_diet_1,
            title = "Classic Protein Power Bowl",
            price = "349",
            restaurantName = "Bowl Culture",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹70",
            address = "Fitness District, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.bowl_diet_2,
            title = "Acai Berry Bliss Bowl",
            price = "429",
            restaurantName = "Healthy Bowl Co",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹64",
            address = "Wellness Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.bowl_diet_3,
            title = "Quinoa Garden Harvest Bowl",
            price = "359",
            restaurantName = "Green Theory",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "1.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹72",
            address = "Eco Square, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.bowl_diet_4,
            title = "Mediterranean Sunshine Bowl",
            price = "399",
            restaurantName = "Olive Kitchen",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "2.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹60",
            address = "Mediterranean Plaza, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.bowl_diet_5,
            title = "Hawaiian Poke Bowl",
            price = "479",
            restaurantName = "Island Bowls",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "2.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹48",
            address = "Beachside Ave, Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.bowl_diet_6,
            title = "Buddha's Delight Bowl",
            price = "369",
            restaurantName = "Zen Kitchen",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "1.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹55",
            address = "Peace Complex, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.bowl_diet_7,
            title = "Berry Blast Smoothie Bowl",
            price = "319",
            restaurantName = "Morning Bowls",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "Breakfast Street, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.bowl_diet_8,
            title = "Thai Green Curry Bowl",
            price = "409",
            restaurantName = "Thai Spice",
            rating = "4.7",
            deliveryTime = "28-34 mins",
            distance = "2.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹61",
            address = "Asian Food Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.bowl_diet_9,
            title = "Mexican Fiesta Bowl",
            price = "379",
            restaurantName = "Salsa Bowls",
            rating = "4.6",
            deliveryTime = "22-28 mins",
            distance = "1.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹76",
            address = "Food Square, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.bowl_diet_10,
            title = "Tofu Teriyaki Bowl",
            price = "349",
            restaurantName = "Asian Wok",
            rating = "4.7",
            deliveryTime = "20-26 mins",
            distance = "1.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹35",
            address = "Teriyaki Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.bowl_diet_11,
            title = "Falafel Hummus Bowl",
            price = "329",
            restaurantName = "Middle East Eats",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹49",
            address = "Mediterranean Market, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.bowl_diet_12,
            title = "Mango Coconut Chia Bowl",
            price = "289",
            restaurantName = "Tropical Bowls",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹29",
            address = "Fruit Bazaar, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.bowl_diet_13,
            title = "Korean Bibimbap Bowl",
            price = "419",
            restaurantName = "Seoul Kitchen",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "2.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹63",
            address = "Korea Town, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.bowl_diet_14,
            title = "Moroccan Spice Bowl",
            price = "389",
            restaurantName = "Marrakesh Flavors",
            rating = "4.7",
            deliveryTime = "24-30 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹78",
            address = "Spice Souk, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.bowl_diet_15,
            title = "Greek Goddess Bowl",
            price = "359",
            restaurantName = "Athens Bowl Co",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹54",
            address = "Greek Plaza, Delhi"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.bowl_diet_16,
            title = "Rainbow Veggie Bowl",
            price = "299",
            restaurantName = "Colorful Eats",
            rating = "4.6",
            deliveryTime = "18-23 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Green Market, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.bowl_diet_17,
            title = "Japanese Zen Bowl",
            price = "439",
            restaurantName = "Tokyo Bowl House",
            rating = "4.9",
            deliveryTime = "26-32 mins",
            distance = "2.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹44",
            address = "Japantown, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.bowl_diet_18,
            title = "California Sunrise Bowl",
            price = "399",
            restaurantName = "West Coast Bowls",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹60",
            address = "Beach Boulevard, Delhi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.bowl_diet_19,
            title = "Indian Masala Bowl",
            price = "309",
            restaurantName = "Spice Route",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹62",
            address = "Masala Street, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.bowl_diet_20,
            title = "Grand Superfood Bowl",
            price = "499",
            restaurantName = "Superfood Kitchen",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "2.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹75",
            address = "Wellness Avenue, Delhi"
        )
    )
    Column {
        bowlDietItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

@Composable
fun SweetsPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val sweetsDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY SWEETS DIET FILTERS (WITH ICONS) - Most important sweet categories with visuals
                FilterChip(
                    id = "indian_sweets",
                    text = "Indian Sweets",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_indian_sweets
                ),
                FilterChip(
                    id = "chocolate_desserts",
                    text = "Chocolate Desserts",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chocolate_dessert_2
                ),
                FilterChip(
                    id = "ice_cream",
                    text = "Ice Cream",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_ice_cream
                ),
                FilterChip(
                    id = "cakes_pastries",
                    text = "Cakes & Pastries",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cake
                ),
                FilterChip(
                    id = "traditional_desserts",
                    text = "Traditional Desserts",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_traditional_dessert
                ),

                // 3. INDIAN SWEETS (MITHAS) - TEXT ONLY
                FilterChip(
                    id = "gulab_jamun",
                    text = "Gulab Jamun",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rasgulla",
                    text = "Rasgulla",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "jalebi",
                    text = "Jalebi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "barfi",
                    text = "Barfi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "laddu",
                    text = "Laddu",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "halwa",
                    text = "Halwa",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kheer",
                    text = "Kheer / Payasam",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "peda",
                    text = "Peda",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. INTERNATIONAL DESSERTS - TEXT ONLY
                FilterChip(
                    id = "tiramisu",
                    text = "Tiramisu",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cheesecake",
                    text = "Cheesecake",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brownie",
                    text = "Brownie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookie",
                    text = "Cookies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "donut",
                    text = "Donuts",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "waffle",
                    text = "Waffles",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pancake",
                    text = "Pancakes",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "crepe",
                    text = "Crepes",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "macaron",
                    text = "Macarons",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mousse",
                    text = "Mousse",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. ICE CREAM & FROZEN TREATS - TEXT ONLY
                FilterChip(
                    id = "vanilla_icecream",
                    text = "Vanilla",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_icecream",
                    text = "Chocolate",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "strawberry_icecream",
                    text = "Strawberry",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mango_icecream",
                    text = "Mango",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pista_icecream",
                    text = "Pistachio",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kulfi",
                    text = "Kulfi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sorbet",
                    text = "Sorbet",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gelato",
                    text = "Gelato",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "frozen_yogurt",
                    text = "Frozen Yogurt",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. CAKE FLAVORS & TYPES - TEXT ONLY
                FilterChip(
                    id = "chocolate_cake",
                    text = "Chocolate Cake",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "red_velvet",
                    text = "Red Velvet",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "black_forest",
                    text = "Black Forest",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pineapple_cake",
                    text = "Pineapple",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "butterscotch",
                    text = "Butterscotch",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fruit_cake",
                    text = "Fruit Cake",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cupcakes",
                    text = "Cupcakes",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. FESTIVAL SPECIAL SWEETS - TEXT ONLY
                FilterChip(
                    id = "diwali_sweets",
                    text = "Diwali Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "holi_sweets",
                    text = "Holi Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eid_sweets",
                    text = "Eid Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "christmas_desserts",
                    text = "Christmas Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "birthday_cakes",
                    text = "Birthday Cakes",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "anniversary_desserts",
                    text = "Anniversary Special",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan_desserts",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_desserts",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_desserts",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_250",
                    text = "₹100 - ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "250_500",
                    text = "₹250 - ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_500",
                    text = "Above ₹500",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. Sort By Dropdown
                FilterChip(
                    id = "sort_by",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )

        FilterButtonFood(
            filterConfig = sweetsDietFilters,
            onFilterClick = { filter ->
                println("Filter clicked: ${filter.text}")
                // Handle filter logic
            },
            onSortClick = {
                println("Sort clicked")
                // Handle sort logic
            }
        )
        // Sample data with all fields
        val sweetsDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_chocolate_cake,
                title = "Chocolate Cake",
                price = "299",
                restaurantName = "Sweet Tooth Bakery",
                rating = "4.8",
                deliveryTime = "30-35 mins",
                distance = "2.3 km",
                discount = "10%",
                discountAmount = "up to ₹30",
                address = "Dessert Avenue, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_gulab_jamun_1,
                title = "Gulab Jamun (4 pcs)",
                price = "149",
                restaurantName = "Mithai Wala",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "5%",
                discountAmount = "up to ₹7",
                address = "Old Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_ice_cream_sundae,
                title = "Ice Cream Sundae",
                price = "199",
                restaurantName = "Frozen Treats",
                rating = "4.6",
                deliveryTime = "25-30 mins",
                distance = "1.9 km",
                discount = "15%",
                discountAmount = "up to ₹30",
                address = "Cool Plaza, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_rasmalai_1,
                title = "Rasmalai",
                price = "249",
                restaurantName = "Bengal Sweets",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "2.1 km",
                discount = "10%",
                discountAmount = "up to ₹25",
                address = "Eastern Avenue, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_brownie,
                title = "Chocolate Brownie",
                price = "179",
                restaurantName = "Bake House",
                rating = "4.5",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "20%",
                discountAmount = "up to ₹36",
                address = "Baker Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_jalebi_1,
                title = "Crispy Jalebi",
                price = "129",
                restaurantName = "Street Sweets",
                rating = "4.4",
                deliveryTime = "15-20 mins",
                distance = "1.1 km",
                discount = "0%",
                discountAmount = "up to ₹0",
                address = "Food Court, Delhi"
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Recommended for you",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.customColors.black
            ),
//            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().padding(start=12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        FoodItemsListWithHeading(
            heading = null,
            subtitle = null,
            foodItems = sweetsDietItems,
            onItemClick = { foodItem ->
                println("Food item clicked: ${foodItem.title}")
            },
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            cardWidth = 150.dp,
            cardHeight = 170.dp,
            horizontalSpacing = 8.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 0.dp,
            headingBottomPadding = 0.dp
        )
    }


}

@Composable
fun KhichdiPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Khichdi Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your khichdi items here
    }
}

@Composable
fun SundaePage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Sundae Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your sundae items here
    }
}

@Composable
fun JuicePage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Juice Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your juice items here
    }
}

@Composable
fun LassiPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Lassi Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your lassi items here
    }
}

@Composable
fun CurdRicePage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Curd Rice Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your curd rice items here
    }
}

@Composable
fun PuddingPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Pudding Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your pudding items here
    }
}

@Composable
fun CustardPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Custard Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your custard items here
    }
}

@Composable
fun SoupDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Soup Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your soup items here
    }
}

@Composable
fun BrowniePage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Brownie Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your brownie items here
    }
}

@Composable
fun WafflesPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Waffles Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your waffles items here
    }
}

@Composable
fun ColdCoffeePage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Cold Coffee Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your cold coffee items here
    }
}

// Additional diet-specific item pages
@Composable
fun GrilledChickenDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Grilled Chicken Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your grilled chicken items here
    }
}

@Composable
fun SteamedFishDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Steamed Fish Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your steamed fish items here
    }
}

@Composable
fun QuinoaBowlDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Quinoa Bowl Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your quinoa bowl items here
    }
}

@Composable
fun AvocadoToastDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Avocado Toast Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your avocado toast items here
    }
}

@Composable
fun GreenSmoothieDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Green Smoothie Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your green smoothie items here
    }
}

@Composable
fun OatmealDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Oatmeal Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your oatmeal items here
    }
}

@Composable
fun GreekYogurtDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Greek Yogurt Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your greek yogurt items here
    }
}

@Composable
fun EggWhiteOmeletteDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Egg White Omelette Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your egg white omelette items here
    }
}

@Composable
fun TunaSaladDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Tuna Salad Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your tuna salad items here
    }
}

@Composable
fun LentilSoupDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Lentil Soup Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your lentil soup items here
    }
}

@Composable
fun CottageCheeseDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Cottage Cheese Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your cottage cheese items here
    }
}

@Composable
fun SproutsSaladDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Sprouts Salad Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your sprouts salad items here
    }
}

@Composable
fun BrownRiceBowlDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Brown Rice Bowl Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your brown rice bowl items here
    }
}

@Composable
fun SteamedVeggiesDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Steamed Veggies Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your steamed veggies items here
    }
}

@Composable
fun FruitBowlDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Fruit Bowl Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your fruit bowl items here
    }
}

@Composable
fun DetoxWaterDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Detox Water Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your detox water items here
    }
}

@Composable
fun HerbalTeaDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Herbal Tea Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your herbal tea items here
    }
}

@Composable
fun ProteinBarDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Protein Bar Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your protein bar items here
    }
}

@Composable
fun BoiledEggsDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Boiled Eggs Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your boiled eggs items here
    }
}

@Composable
fun HummusPlateDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Hummus Plate Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your hummus plate items here
    }
}

@Composable
fun SushiRollsDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Sushi Rolls Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your sushi rolls items here
    }
}

@Composable
fun TofuStirFryDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Tofu Stir Fry Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your tofu stir fry items here
    }
}

@Composable
fun ChiaPuddingDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Chia Pudding Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your chia pudding items here
    }
}

@Composable
fun MilletBowlDietPage() {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Millet Bowl Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.customColors.header
        )
        // Add your millet bowl items here
    }
}

@Composable
fun SeeAllPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tap 'See All' tab above to browse all food categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.customColors.black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "This will open the full category selection screen",
            fontSize = 14.sp,
            color = MaterialTheme.customColors.black.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )
    }
}
