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
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Custom SetSaver for rememberSaveable
//val SetSaver = Saver<Set<Int>, Set<Int>>(
//    save = { it },
//    restore = { it }
//)

// Sealed class for different diet category pages
sealed class DietCategoryPage(val title: String, val iconRes: Int) {
    // First 8 main categories (indices 0-7)
    object Chicken : DietCategoryPage("Chicken", R.drawable.chicken_food_diet)
    object Salad : DietCategoryPage("Salad", R.drawable.salad_food_diet)
    object Mutton : DietCategoryPage("Mutton", R.drawable.mutton_food_diet)
    object Kebabs : DietCategoryPage("Kebabs", R.drawable.kebabs_food_diet)
    object HealthySnacks : DietCategoryPage("Snacks", R.drawable.healthy_snacks_food_diet)
    object LowCalorie : DietCategoryPage("Low Calorie", R.drawable.low_calorie_food_diet)
    object Vegan : DietCategoryPage("Vegan", R.drawable.vegan_food_diet)
    object ProteinRich : DietCategoryPage("Protein Rich", R.drawable.protein_food_diet)

    // Categories from your list (indices 8-22)
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

    // Additional diet-specific items (indices 23-46)
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
// SetSaver for saving Set<Int> in rememberSaveable
val SetSaver = Saver<Set<Int>, List<Int>>(
    save = { it.toList() },
    restore = { it.toSet() }
)

@Composable
fun CategoryDietTabsFood(
    navController: NavHostController? = null,
    currentSelectedIndex: Int,
    selectedDietTabIndex: Int,
    onCategorySelected: (DietCategoryPage) -> Unit = {},
    onTabIndexChanged: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val TAG = "CategoryDietTabsFood"
    val initialVisibleCount = 8

    // All diet category pages
    val allDietCategoryPages = remember {
        listOf(
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
            DietCategoryPage.Custard,  // This is index 18
            DietCategoryPage.Soup,
            DietCategoryPage.Brownie,
            DietCategoryPage.Waffles,
            DietCategoryPage.ColdCoffee,
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
    }

    // Internal state
    var internalSelectedIndex by rememberSaveable {
        mutableIntStateOf(
            navController?.currentBackStackEntry?.savedStateHandle?.get<Int>("currentDietSelectedIndex")
                ?: selectedDietTabIndex
        )
    }

    var recentlySelectedTabs by rememberSaveable(stateSaver = SetSaver) {
        mutableStateOf(
            navController?.currentBackStackEntry?.savedStateHandle?.get<Set<Int>>("recentlySelectedDietTabs")
                ?: emptySet()
        )
    }

    // Save state
    LaunchedEffect(internalSelectedIndex) {
        navController?.currentBackStackEntry?.savedStateHandle?.set(
            "currentDietSelectedIndex",
            internalSelectedIndex
        )
    }

    LaunchedEffect(recentlySelectedTabs) {
        navController?.currentBackStackEntry?.savedStateHandle?.set(
            "recentlySelectedDietTabs",
            recentlySelectedTabs
        )
    }

    // Update when parent changes
    LaunchedEffect(selectedDietTabIndex) {
        if (selectedDietTabIndex != internalSelectedIndex) {
            internalSelectedIndex = selectedDietTabIndex
            // When index changes from parent, add to recently selected if beyond initial count
            if (selectedDietTabIndex >= initialVisibleCount) {
                recentlySelectedTabs = recentlySelectedTabs + selectedDietTabIndex
                Log.d(TAG, "Added to recentlySelectedTabs from parent: $selectedDietTabIndex")
            }
        }
    }

    // Handle selection from See All page
    DisposableEffect(navController) {
        val savedStateHandle = navController?.currentBackStackEntry?.savedStateHandle
        val liveData = savedStateHandle?.getLiveData<Int>("selectedDietTabFromSeeAll")

        val observer = androidx.lifecycle.Observer<Int> { newIndex ->
            if (newIndex != null) {
                Log.d(TAG, "Received new index from See All: $newIndex")

                // First update recently selected tabs
                if (newIndex >= initialVisibleCount) {
                    recentlySelectedTabs = recentlySelectedTabs + newIndex
                    Log.d(TAG, "Added to recentlySelectedTabs: $newIndex, now: $recentlySelectedTabs")
                }

                // Small delay to ensure recomposition with updated recentlySelectedTabs
                CoroutineScope(Dispatchers.Main).launch {
                    delay(10)
                    // Then update internal selected index
                    internalSelectedIndex = newIndex

                    // Notify parent
                    onTabIndexChanged(newIndex)

                    // Get the category page and call onCategorySelected
                    allDietCategoryPages.getOrNull(newIndex)?.let { page ->
                        onCategorySelected(page)
                    }
                }

                // Clear the value
                savedStateHandle?.remove<Int>("selectedDietTabFromSeeAll")
            }
        }

        liveData?.observeForever(observer)

        onDispose {
            liveData?.removeObserver(observer)
        }
    }

    // Build visible tabs - recomputes whenever recentlySelectedTabs changes
    val visibleTabs = remember(recentlySelectedTabs) {
        buildList {
            // Add first 8 tabs
            addAll(allDietCategoryPages.take(initialVisibleCount))

            // Add recently selected tabs beyond first 8, preserving order
            val sortedRecentTabs = recentlySelectedTabs
                .filter { it >= initialVisibleCount }
                .sorted()

            Log.d(TAG, "Building visibleTabs with recent tabs: $sortedRecentTabs")

            sortedRecentTabs.forEach { index ->
                allDietCategoryPages.getOrNull(index)?.let {
                    add(it)
                    Log.d(TAG, "Added recent tab at index $index: ${it.title}")
                }
            }

            // Add See All tab at the end
            add(DietCategoryPage.SeeAll)
        }
    }

    // Create mapping from all indices to visible indices
    val allToVisibleMap = remember(recentlySelectedTabs) {
        val map = mutableMapOf<Int, Int>()

        // Map first 8 tabs
        for (i in 0 until minOf(initialVisibleCount, allDietCategoryPages.size)) {
            map[i] = i
        }

        // Map recent tabs
        val sortedRecentTabs = recentlySelectedTabs
            .filter { it >= initialVisibleCount }
            .sorted()

        sortedRecentTabs.forEachIndexed { index, allIndex ->
            map[allIndex] = initialVisibleCount + index
        }

        map
    }

    // Create reverse mapping from visible index to original index
    val visibleToAllMap = remember(recentlySelectedTabs) {
        val map = mutableMapOf<Int, Int>()

        // Map first 8 tabs
        for (i in 0 until minOf(initialVisibleCount, allDietCategoryPages.size)) {
            map[i] = i
        }

        // Map recent tabs
        val sortedRecentTabs = recentlySelectedTabs
            .filter { it >= initialVisibleCount }
            .sorted()

        sortedRecentTabs.forEachIndexed { index, allIndex ->
            map[initialVisibleCount + index] = allIndex
        }

        map
    }

    // Get visible index for current selection
    val selectedVisibleIndex = remember(internalSelectedIndex, recentlySelectedTabs, visibleTabs) {
        val visibleIndex = when {
            // Case 1: Selected index is within first 8 tabs
            internalSelectedIndex < initialVisibleCount -> internalSelectedIndex

            // Case 2: Selected index is a recently selected tab (beyond first 8)
            internalSelectedIndex in allToVisibleMap -> {
                val index = allToVisibleMap[internalSelectedIndex]
                Log.d(TAG, "Mapping internal index $internalSelectedIndex to visible index $index")
                index ?: (visibleTabs.size - 1)
            }

            // Case 3: Selected index is not in visible tabs - add it to recently selected
            else -> {
                Log.d(TAG, "Selected index $internalSelectedIndex not found in visible tabs, adding to recently selected")
                // Add to recently selected tabs
                if (internalSelectedIndex >= initialVisibleCount) {
                    // Use a coroutine to update recentlySelectedTabs
                    CoroutineScope(Dispatchers.Main).launch {
                        recentlySelectedTabs = recentlySelectedTabs + internalSelectedIndex
                        Log.d(TAG, "Added missing tab to recentlySelectedTabs: $internalSelectedIndex")
                    }
                }
                visibleTabs.size - 1 // Default to See All for now
            }
        }

        // Ensure the visible index is within bounds
        if (visibleIndex in visibleTabs.indices) {
            visibleIndex
        } else {
            Log.d(TAG, "Visible index $visibleIndex out of bounds, defaulting to See All")
            visibleTabs.size - 1
        }
    }

    // Debug logging
    LaunchedEffect(internalSelectedIndex, recentlySelectedTabs) {
        Log.d(TAG, "=== State Update ===")
        Log.d(TAG, "internalSelectedIndex: $internalSelectedIndex")
        Log.d(TAG, "selectedVisibleIndex: $selectedVisibleIndex")
        Log.d(TAG, "recentlySelectedTabs: $recentlySelectedTabs")
        Log.d(TAG, "visibleTabs size: ${visibleTabs.size}")
        visibleTabs.forEachIndexed { index, page ->
            val originalIndex = visibleToAllMap[index]
            Log.d(TAG, "visibleTabs[$index]: ${page.title} (original index: $originalIndex)")
        }
        Log.d(TAG, "Selected tab title: ${visibleTabs.getOrNull(selectedVisibleIndex)?.title}")
        Log.d(TAG, "==================")
    }

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

                // Find original index for this visible tab
                val originalIndex = when {
                    index < initialVisibleCount -> index
                    isSeeAllTab -> -1
                    else -> visibleToAllMap[index] ?: -1
                }

                val isSelected = !isSeeAllTab && internalSelectedIndex == originalIndex

                Tab(
                    selected = isSelected,
                    onClick = {
                        if (isSeeAllTab) {
                            // Navigate to See All page
                            navController?.navigate("category_diet_tabs_list/${internalSelectedIndex}") {
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
                            // Regular tab click
                            if (originalIndex >= 0) {
                                internalSelectedIndex = originalIndex
                                onTabIndexChanged(originalIndex)
                                onCategorySelected(dietCategoryPage)

                                // IMPORTANT: Always add to recently selected if beyond initial count
                                if (originalIndex >= initialVisibleCount) {
                                    // Add to recently selected even if already there (to ensure it appears)
                                    recentlySelectedTabs = recentlySelectedTabs + originalIndex
                                    Log.d(TAG, "Added to recentlySelectedTabs on click: $originalIndex")
                                }
                            }
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

                        // Show indicator for recently added tabs
                        if (!isSeeAllTab && originalIndex >= initialVisibleCount) {
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

    // Content section
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
        val actualCategory = allDietCategoryPages.getOrNull(internalSelectedIndex)

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
            DietCategoryPage.Custard -> CustardPage()  // This should now be selectable
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
            currentSelectedIndex = currentPage,
            selectedDietTabIndex = selectedDietTabIndex,
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

    val sweetsDietItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.sweets_diet_1,
            title = "Date & Nut Energy Balls",
            price = "199",
            restaurantName = "Sweet Greens",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Health Street, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.sweets_diet_2,
            title = "Dark Chocolate Berry Cups",
            price = "249",
            restaurantName = "Pure Indulgence",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹37",
            address = "Sweet Corner, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.sweets_diet_3,
            title = "Chia Seed Pudding",
            price = "179",
            restaurantName = "Healthy Treats",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Wellness Ave, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.sweets_diet_4,
            title = "Almond Butter Brownie",
            price = "219",
            restaurantName = "Guilt-Free Bakes",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹44",
            address = "Bakery Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.sweets_diet_5,
            title = "Frozen Yogurt Berry Bowl",
            price = "189",
            restaurantName = "Yogurt World",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Dessert Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.sweets_diet_6,
            title = "Raw Vegan Cheesecake",
            price = "299",
            restaurantName = "Raw Earth",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "1.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Organic Market, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.sweets_diet_7,
            title = "Coconut Ladoo (Sugar-Free)",
            price = "149",
            restaurantName = "Indian Roots",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Old Delhi Street",
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.sweets_diet_8,
            title = "Dark Chocolate Almond Bar",
            price = "169",
            restaurantName = "Choco Fit",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Chocolate Square, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.sweets_diet_9,
            title = "Fruit Sorbet Cup",
            price = "139",
            restaurantName = "Frozen Bliss",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "0.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹28",
            address = "Market Street, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.sweets_diet_10,
            title = "Peanut Butter Protein Cups",
            price = "229",
            restaurantName = "Protein Lab",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹34",
            address = "Fitness Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.sweets_diet_11,
            title = "Mango Sticky Rice (Light)",
            price = "259",
            restaurantName = "Thai Delight",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹26",
            address = "Asian Plaza, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.sweets_diet_12,
            title = "Gluten-Free Banana Bread",
            price = "189",
            restaurantName = "Healthy Bakes",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹38",
            address = "Bakery Street, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.sweets_diet_13,
            title = "Matcha Green Tea Mousse",
            price = "279",
            restaurantName = "Zen Desserts",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹42",
            address = "Tea District, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.sweets_diet_14,
            title = "Roasted Chickpea Sweets",
            price = "129",
            restaurantName = "Munch Healthy",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Snack Corner, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.sweets_diet_15,
            title = "Berry Compote with Granola",
            price = "209",
            restaurantName = "Morning Fresh",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Breakfast Blvd, Delhi"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.sweets_diet_16,
            title = "Kheer (Low-Fat Rice Pudding)",
            price = "169",
            restaurantName = "Desi Delights",
            rating = "4.7",
            deliveryTime = "18-24 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Spice Market, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.sweets_diet_17,
            title = "Chocolate Avocado Mousse",
            price = "239",
            restaurantName = "Pure Indulgence",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹24",
            address = "Healthy Ave, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.sweets_diet_18,
            title = "Fig & Honey Energy Bar",
            price = "159",
            restaurantName = "Nature's Basket",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹32",
            address = "Organic Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.sweets_diet_19,
            title = "Watermelon Granita",
            price = "119",
            restaurantName = "Summer Cool",
            rating = "4.8",
            deliveryTime = "8-12 mins",
            distance = "0.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Beach Road, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.sweets_diet_20,
            title = "Assorted Diet Dessert Box",
            price = "449",
            restaurantName = "Dessert Studio",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹90",
            address = "Gourmet Street, Delhi"
        )
    )
    Column {
        sweetsDietItems.forEach { restaurantItem ->
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
fun KhichdiPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val khichdiDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY KHICHDI DIET FILTERS (WITH ICONS) - Main khichdi categories
                FilterChip(
                    id = "traditional_khichdi",
                    text = "Traditional Khichdi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_traditional_food
                ),
                FilterChip(
                    id = "protein_rich",
                    text = "Protein Rich",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_calorie
                ),
                FilterChip(
                    id = "one_pot_meals",
                    text = "One Pot Meals",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_one_pot
                ),
                FilterChip(
                    id = "comfort_food",
                    text = "Comfort Food",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_comfort_food
                ),
                // 5. REGIONAL VARIETIES (TEXT ONLY)
                FilterChip(
                    id = "gujarati_khichdi",
                    text = "Gujarati Khichdi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bengali_khichdi",
                    text = "Bengali Khichuri",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sindhi_khichdi",
                    text = "Sindhi Khichdi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "south_indian",
                    text = "South Indian Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "punjabi_khichdi",
                    text = "Punjabi Khichdi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bihari_khichdi",
                    text = "Bihari Khichdi",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. VEGETABLE ADDITIONS (TEXT ONLY)
                FilterChip(
                    id = "mixed_vegetable",
                    text = "Mixed Vegetables",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "palak_khichdi",
                    text = "Palak (Spinach)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lauki_khichdi",
                    text = "Lauki (Bottle Gourd)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pumpkin_khichdi",
                    text = "Pumpkin",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "carrot_beans",
                    text = "Carrot & Beans",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "methi_khichdi",
                    text = "Methi (Fenugreek)",
                    type = FilterType.TEXT_ONLY
                ),
            FilterChip(
                id = "soyachunks_khichdi",
                text = "Soya Chunks Khichdi",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "mushroom_khichdi",
                text = "Mushroom Khichdi",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "peas_khichdi",
                text = "Green Peas Khichdi",
                type = FilterType.TEXT_ONLY
            ),

            // 8. DIETARY & HEALTH (TEXT ONLY)
            FilterChip(
                id = "sattvic_khichdi",
                text = "Sattvic (No Onion/Garlic)",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "ayurvedic_khichdi",
                text = "Ayurvedic Khichdi",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "detox_khichdi",
                text = "Detox Khichdi",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "high_protein",
                text = "High Protein",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "low_fat",
                text = "Low Fat",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "diabetic_friendly",
                text = "Diabetic Friendly",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "gluten_free_khichdi",
                text = "Gluten Free",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "vegan_khichdi",
                text = "Vegan",
                type = FilterType.TEXT_ONLY
            ),

            // 9. CONVENIENCE (TEXT ONLY)
            FilterChip(
                id = "ready_to_eat",
                text = "Ready to Eat",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "meal_combo",
                text = "Meal Combos",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "family_pack",
                text = "Family Pack",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "with_side_dishes",
                text = "With Side Dishes",
                type = FilterType.TEXT_ONLY
            ),

            // 10. SIDE DISHES (TEXT ONLY)
            FilterChip(
                id = "with_kadhi",
                text = "With Kadhi",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "with_papad",
                text = "With Papad",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "with_pickle",
                text = "With Pickle",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "with_raita",
                text = "With Raita",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "with_ghee",
                text = "With Extra Ghee",
                type = FilterType.TEXT_ONLY
            ),

            // 11. PRICE RANGE (TEXT ONLY)
            FilterChip(
                id = "under_100",
                text = "Under ₹100",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "100_200",
                text = "₹100 - ₹200",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "200_300",
                text = "₹200 - ₹300",
                type = FilterType.TEXT_ONLY
            ),
            FilterChip(
                id = "above_300",
                text = "Above ₹300",
                type = FilterType.TEXT_ONLY
            ),

            // 12. SORT BY DROPDOWN
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
            filterConfig = khichdiDietFilters,
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
        val khichdiDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_moong_dal_khichdi,
                title = "Classic Moong Dal Khichdi",
                price = "179",
                restaurantName = "Healthy Bites",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹27",
                address = "Wellness Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_vegetable_khichdi,
                title = "Mixed Vegetable Khichdi",
                price = "199",
                restaurantName = "Green Bowl Kitchen",
                rating = "4.8",
                deliveryTime = "25-30 mins",
                distance = "1.6 km",
                discount = "10%",
                discountAmount = "up to ₹20",
                address = "Organic Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_tawa_khichdi,
                title = "Special Tawa Khichdi",
                price = "229",
                restaurantName = "Punjabi Tadka",
                rating = "4.7",
                deliveryTime = "25-30 mins",
                distance = "1.9 km",
                discount = "20%",
                discountAmount = "up to ₹46",
                address = "Food Square, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_quinoa_khichdi,
                title = "Quinoa Protein Khichdi",
                price = "279",
                restaurantName = "Fit Food Cafe",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.4 km",
                discount = "10%",
                discountAmount = "up to ₹28",
                address = "Fitness Hub, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_oats_khichdi,
                title = "Oats & Moong Dal Khichdi",
                price = "189",
                restaurantName = "Diet Kitchen",
                rating = "4.6",
                deliveryTime = "18-22 mins",
                distance = "1.1 km",
                discount = "15%",
                discountAmount = "up to ₹28",
                address = "Health Avenue, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_masala_khichdi_1,
                title = "Masala Khichdi with Ghee",
                price = "209",
                restaurantName = "Spice Route",
                rating = "4.8",
                deliveryTime = "22-27 mins",
                distance = "1.5 km",
                discount = "20%",
                discountAmount = "up to ₹42",
                address = "Spice Market, Delhi"
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
            foodItems = khichdiDietItems ,
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

    val khichdiDietItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.khichdi_diet_1,
            title = "Moong Dal Khichdi (Plain)",
            price = "149",
            restaurantName = "Healthy Bites",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹30",
            address = "Food Street, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.khichdi_diet_2,
            title = "Vegetable Khichdi",
            price = "179",
            restaurantName = "Desi Roots",
            rating = "4.7",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Old Market, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.khichdi_diet_3,
            title = "Brown Rice Khichdi",
            price = "189",
            restaurantName = "Nutri Meal",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Health Plaza, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.khichdi_diet_4,
            title = "Quinoa Khichdi",
            price = "229",
            restaurantName = "Fusion Foods",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹46",
            address = "Gourmet Avenue, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.khichdi_diet_5,
            title = "Masala Khichdi",
            price = "169",
            restaurantName = "Spice Kitchen",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Spice Market, Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.khichdi_diet_6,
            title = "Palak Khichdi (Spinach)",
            price = "159",
            restaurantName = "Green Bowl",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Green Street, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.khichdi_diet_7,
            title = "Bajra Khichdi",
            price = "199",
            restaurantName = "Rajasthani Thali",
            rating = "4.6",
            deliveryTime = "20-25 mins",
            distance = "1.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Heritage Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.khichdi_diet_8,
            title = "Jowar Khichdi",
            price = "189",
            restaurantName = "Millet Magic",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Organic Market, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.khichdi_diet_9,
            title = "Kadhi Khichdi Combo",
            price = "249",
            restaurantName = "Gujarati House",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹50",
            address = "Sector 18, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.khichdi_diet_10,
            title = "Papdi Khichdi",
            price = "179",
            restaurantName = "Street Foodies",
            rating = "4.5",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Food Court, Delhi"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.khichdi_diet_11,
            title = "Methi Khichdi (Fenugreek)",
            price = "159",
            restaurantName = "Healthy Palate",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Wellness Road, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.khichdi_diet_12,
            title = "Surti Khichdi",
            price = "209",
            restaurantName = "Gujarati Rasoi",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "South Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.khichdi_diet_13,
            title = "Lauki Khichdi (Bottle Gourd)",
            price = "169",
            restaurantName = "Diet Delight",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Health Street, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.khichdi_diet_14,
            title = "Tawa Khichdi",
            price = "189",
            restaurantName = "Punjabi Dhaba",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Highway Inn, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.khichdi_diet_15,
            title = "Soybean Khichdi",
            price = "199",
            restaurantName = "Protein Plus",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Fitness Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.khichdi_diet_16,
            title = "Paneer Khichdi",
            price = "239",
            restaurantName = "Cheese Cottage",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹36",
            address = "Dairy Circle, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.khichdi_diet_17,
            title = "Garlic Khichdi",
            price = "169",
            restaurantName = "Flavors of India",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Spice Bazaar, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.khichdi_diet_18,
            title = "Daliya Khichdi (Broken Wheat)",
            price = "159",
            restaurantName = "Whole Foods",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹32",
            address = "Organic Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.khichdi_diet_19,
            title = "Sambhar Khichdi",
            price = "219",
            restaurantName = "South Indian Cafe",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹33",
            address = "Chennai Express, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.khichdi_diet_20,
            title = "Grand Khichdi Platter",
            price = "349",
            restaurantName = "Royal Feast",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "2.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹70",
            address = "Palace Road, Delhi"
        )
    )
    Column {
        khichdiDietItems.forEach { restaurantItem ->
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
fun SundaePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val sundaeDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY SUNDAE DIET CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "classic_sundaes",
                    text = "Classic Sundaes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_classic_sundae
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_calorie_sundae
                ),
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sugar_free_sundae
                ),
                FilterChip(
                    id = "protein_sundaes",
                    text = "Protein Sundaes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_sundae
                ),
                FilterChip(
                    id = "vegan_sundaes",
                    text = "Vegan",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegan_sundae
                ),

                // 3. BASE FLAVORS (TEXT ONLY)
                FilterChip(
                    id = "vanilla_base",
                    text = "Vanilla Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_base",
                    text = "Chocolate Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "strawberry_base",
                    text = "Strawberry Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coffee_base",
                    text = "Coffee Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mango_base",
                    text = "Mango Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pistachio_base",
                    text = "Pistachio Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coconut_base",
                    text = "Coconut Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "banana_base",
                    text = "Banana Base",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. TOPPINGS (TEXT ONLY)
                FilterChip(
                    id = "chocolate_sauce",
                    text = "Chocolate Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caramel_sauce",
                    text = "Caramel Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "strawberry_sauce",
                    text = "Strawberry Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "nuts_toppings",
                    text = "Nuts",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sprinkles",
                    text = "Sprinkles",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cherry_on_top",
                    text = "Cherry on Top",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brownie_bits",
                    text = "Brownie Bits",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookie_crumbs",
                    text = "Cookie Crumbs",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fresh_fruits",
                    text = "Fresh Fruits",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "whipped_cream",
                    text = "Whipped Cream",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "waffle_pieces",
                    text = "Waffle Pieces",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. OCCASION SUNDAES (TEXT ONLY)
                FilterChip(
                    id = "birthday_special",
                    text = "Birthday Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "party_pack",
                    text = "Party Pack",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "date_night",
                    text = "Date Night Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kids_favorite",
                    text = "Kids Favorite",
                    type = FilterType.TEXT_ONLY
                ),
                // 8. SIZE OPTIONS (TEXT ONLY)
                FilterChip(
                    id = "small_sundae",
                    text = "Small (150ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_sundae",
                    text = "Regular (250ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_sundae",
                    text = "Large (400ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sharing_sundae",
                    text = "Sharing (600ml)",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_200",
                    text = "₹100 - ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "200_300",
                    text = "₹200 - ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_300",
                    text = "Above ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                // 12. SORT BY DROPDOWN
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
            filterConfig = sundaeDietFilters,
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
        val sundaeDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_berry_protein_sundae,
                title = "Berry Protein Sundae",
                price = "249",
                restaurantName = "Protein Lab",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹37",
                address = "Fitness Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_greek_yogurt_sundae,
                title = "Greek Yogurt Berry Bowl",
                price = "219",
                restaurantName = "Yogurt World",
                rating = "4.8",
                deliveryTime = "12-18 mins",
                distance = "0.9 km",
                discount = "10%",
                discountAmount = "up to ₹22",
                address = "Healthy Hub, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_dark_chocolate_sundae,
                title = "Dark Chocolate Protein Sundae",
                price = "279",
                restaurantName = "Choco Fit",
                rating = "4.9",
                deliveryTime = "18-24 mins",
                distance = "1.4 km",
                discount = "20%",
                discountAmount = "up to ₹56",
                address = "Chocolate Square, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_mango_coconut_sundae,
                title = "Mango Coconut Delight",
                price = "229",
                restaurantName = "Tropical Bliss",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "1.1 km",
                discount = "15%",
                discountAmount = "up to ₹34",
                address = "Fruit Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_banana_walnut_sundae,
                title = "Banana Walnut Sundae",
                price = "199",
                restaurantName = "Nutty Scoops",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.7 km",
                discount = "10%",
                discountAmount = "up to ₹20",
                address = "Nut Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_mixed_berry_sundae,
                title = "Mixed Berry Antioxidant Sundae",
                price = "259",
                restaurantName = "Berry Farms",
                rating = "4.9",
                deliveryTime = "16-22 mins",
                distance = "1.3 km",
                discount = "20%",
                discountAmount = "up to ₹52",
                address = "Berry Gardens, Delhi"
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
            foodItems = sundaeDietItems,
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

    val sundaeDietItems = listOf(
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.sundae_diet_1,
            title = "Berry Acai Protein Sundae",
            price = "279",
            restaurantName = "Protein Lab",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹42",
            address = "Fitness Street, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.sundae_diet_2,
            title = "Greek Yogurt Honey Walnut",
            price = "229",
            restaurantName = "Yogurt World",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹23",
            address = "Healthy Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.sundae_diet_3,
            title = "Dark Chocolate Almond Sundae",
            price = "299",
            restaurantName = "Choco Fit",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Chocolate Square, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.sundae_diet_4,
            title = "Mango Coconut Light Sundae",
            price = "239",
            restaurantName = "Tropical Bliss",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹36",
            address = "Fruit Market, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.sundae_diet_5,
            title = "Banana Walnut Protein Sundae",
            price = "219",
            restaurantName = "Nutty Scoops",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹22",
            address = "Nut Street, Delhi"
        ),
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.sundae_diet_6,
            title = "Mixed Berry Antioxidant Sundae",
            price = "269",
            restaurantName = "Berry Farms",
            rating = "4.9",
            deliveryTime = "16-22 mins",
            distance = "1.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹54",
            address = "Berry Gardens, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.sundae_diet_7,
            title = "Strawberry Chia Seed Sundae",
            price = "209",
            restaurantName = "Healthy Treats",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹31",
            address = "Wellness Ave, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.sundae_diet_8,
            title = "Peanut Butter Cup Sundae",
            price = "289",
            restaurantName = "Protein Plus",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Fitness Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.sundae_diet_9,
            title = "Matcha Green Tea Sundae",
            price = "259",
            restaurantName = "Zen Desserts",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹26",
            address = "Tea District, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.sundae_diet_10,
            title = "Pistachio Rose Delight",
            price = "279",
            restaurantName = "Royal Desserts",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.7 km",
            discount = "20% OFF",
            discountAmount = "up to ₹56",
            address = "Palace Road, Delhi"
        ),
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.sundae_diet_11,
            title = "Keto-Friendly Berry Sundae",
            price = "319",
            restaurantName = "Keto Kitchen",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹48",
            address = "Diet Street, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.sundae_diet_12,
            title = "Vegan Coconut Mango Sundae",
            price = "249",
            restaurantName = "Plant Power",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹25",
            address = "Organic Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.sundae_diet_13,
            title = "Coffee Walnut Protein Sundae",
            price = "269",
            restaurantName = "Brew & Scoop",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹54",
            address = "Coffee Square, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.sundae_diet_14,
            title = "Fig & Honey Greek Sundae",
            price = "289",
            restaurantName = "Mediterranean Delights",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Food Street, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.sundae_diet_15,
            title = "Raspberry Dark Chocolate",
            price = "299",
            restaurantName = "Choco Berry",
            rating = "4.8",
            deliveryTime = "16-22 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹30",
            address = "Berry Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.sundae_diet_16,
            title = "Pineapple Coconut Light",
            price = "219",
            restaurantName = "Island Flavors",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹44",
            address = "Market Street, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.sundae_diet_17,
            title = "Almond Butter Cacao Sundae",
            price = "309",
            restaurantName = "Nutty Butters",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹46",
            address = "Nut Avenue, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.sundae_diet_18,
            title = "Watermelon Mint Sorbet Sundae",
            price = "189",
            restaurantName = "Summer Cool",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "0.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Beach Road, Delhi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.sundae_diet_19,
            title = "Triple Berry Protein Sundae",
            price = "289",
            restaurantName = "Berry Bliss",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹58",
            address = "Berry Gardens, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.sundae_diet_20,
            title = "Grand Diet Sundae Platter",
            price = "449",
            restaurantName = "Dessert Studio",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹90",
            address = "Gourmet Street, Delhi"
        )
    )
    Column {
        sundaeDietItems.forEach { restaurantItem ->
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
fun JuicePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val juiceDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY JUICE DIET CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "detox_juices",
                    text = "Detox Juices",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_detox_juice  // Leaf/detox icon
                ),
                FilterChip(
                    id = "green_juices",
                    text = "Green Juices",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_green_juice  // Green veggie icon
                ),
                FilterChip(
                    id = "fruit_juices",
                    text = "Fruit Juices",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruit_juice  // Fruit icon
                ),
                FilterChip(
                    id = "vegetable_juices",
                    text = "Vegetable Juices",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegetable_juice  // Carrot/celery icon
                ),
                FilterChip(
                    id = "protein_shakes",
                    text = "Protein Shakes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_protein_shake  // Protein/gym icon
                ),
                // 3. BASE FRUITS/VEGETABLES (TEXT ONLY)
                FilterChip(
                    id = "apple_base",
                    text = "Apple",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "orange_base",
                    text = "Orange",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "carrot_base",
                    text = "Carrot",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spinach_base",
                    text = "Spinach",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kale_base",
                    text = "Kale",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "beetroot_base",
                    text = "Beetroot",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ginger_base",
                    text = "Ginger",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "turmeric_base",
                    text = "Turmeric",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cucumber_base",
                    text = "Cucumber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pineapple_base",
                    text = "Pineapple",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "watermelon_base",
                    text = "Watermelon",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "celery_base",
                    text = "Celery",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. ADDED BOOSTERS (TEXT ONLY)
                FilterChip(
                    id = "chia_seeds",
                    text = "Chia Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "flax_seeds",
                    text = "Flax Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "protein_powder",
                    text = "Protein Powder",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spirulina",
                    text = "Spirulina",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "wheatgrass",
                    text = "Wheatgrass",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coconut_water",
                    text = "Coconut Water",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "aloe_vera",
                    text = "Aloe Vera",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "matcha",
                    text = "Matcha",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "vegan_juice",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_friendly",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "paleo",
                    text = "Paleo",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "raw",
                    text = "Raw",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "organic",
                    text = "Organic",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. OCCASION/PURPOSE (TEXT ONLY)
                FilterChip(
                    id = "morning_detox",
                    text = "Morning Detox",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "post_workout",
                    text = "Post Workout",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "meal_replacement",
                    text = "Meal Replacement",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cleansing",
                    text = "Cleansing",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "energy_boost",
                    text = "Energy Boost",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. SIZE OPTIONS (TEXT ONLY)
                FilterChip(
                    id = "small_juice",
                    text = "Small (250ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_juice",
                    text = "Regular (350ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_juice",
                    text = "Large (500ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_pack",
                    text = "Family Pack (1L)",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_100",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_200",
                    text = "₹100 - ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "200_300",
                    text = "₹200 - ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_300",
                    text = "Above ₹300",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. SORT BY DROPDOWN
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
            filterConfig = juiceDietFilters,
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
        val juiceDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_green_detox_juice,
                title = "Green Detox Juice",
                price = "149",
                restaurantName = "Fresh Press",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.8 km",
                discount = "20%",
                discountAmount = "up to ₹30",
                address = "Health Square, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_tropical_morning_juice,
                title = "Tropical Morning Bliss",
                price = "179",
                restaurantName = "Juice Junction",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "1.1 km",
                discount = "15%",
                discountAmount = "up to ₹27",
                address = "Fruit Bazaar, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_protein_berry_smoothie,
                title = "Protein Berry Smoothie",
                price = "199",
                restaurantName = "Smoothie Bar",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "1.3 km",
                discount = "10%",
                discountAmount = "up to ₹20",
                address = "Fitness Hub, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_immunity_booster,
                title = "Immunity Booster Shot",
                price = "129",
                restaurantName = "Wellness Cafe",
                rating = "4.7",
                deliveryTime = "8-12 mins",
                distance = "0.6 km",
                discount = "25%",
                discountAmount = "up to ₹32",
                address = "Wellness Street, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_beetroot_carrot_blast,
                title = "Beetroot Carrot Blast",
                price = "159",
                restaurantName = "Roots Juicery",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.9 km",
                discount = "15%",
                discountAmount = "up to ₹24",
                address = "Vegetable Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_coconut_watermelon_cooler,
                title = "Coconut Watermelon Cooler",
                price = "189",
                restaurantName = "Tropical Sips",
                rating = "4.9",
                deliveryTime = "14-20 mins",
                distance = "1.2 km",
                discount = "20%",
                discountAmount = "up to ₹38",
                address = "Beach Road, Delhi"
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
            foodItems = juiceDietItems,
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

    val juiceDietItems = listOf(
        // 1-5: DETOX & GREEN JUICES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.juice_diet_1,
            title = "Classic Green Detox Juice",
            price = "149",
            restaurantName = "Fresh Press",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹30",
            address = "Health Square, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.juice_diet_2,
            title = "Cucumber Mint Cooler",
            price = "129",
            restaurantName = "Cool Juices",
            rating = "4.7",
            deliveryTime = "8-12 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Market Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.juice_diet_3,
            title = "Wheatgrass Immunity Shot",
            price = "99",
            restaurantName = "Wellness Cafe",
            rating = "4.8",
            deliveryTime = "5-10 mins",
            distance = "0.3 km",
            discount = "25% OFF",
            discountAmount = "up to ₹25",
            address = "Fitness Street, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.juice_diet_4,
            title = "Spinach Kale Green Blast",
            price = "169",
            restaurantName = "Green Goddess",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Organic Market, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.juice_diet_5,
            title = "Lemon Ginger Detox",
            price = "119",
            restaurantName = "Detox Bar",
            rating = "4.7",
            deliveryTime = "8-12 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Wellness Ave, Delhi"
        ),

        // 6-10: FRUIT JUICES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.juice_diet_6,
            title = "Tropical Orange Punch",
            price = "139",
            restaurantName = "Citrus Bliss",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.9 km",
            discount = "20% OFF",
            discountAmount = "up to ₹28",
            address = "Fruit Market, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.juice_diet_7,
            title = "Watermelon Mint Fresher",
            price = "119",
            restaurantName = "Summer Cool",
            rating = "4.9",
            deliveryTime = "8-12 mins",
            distance = "0.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Beach Road, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.juice_diet_8,
            title = "Pineapple Coconut Breeze",
            price = "159",
            restaurantName = "Island Flavors",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹32",
            address = "Tropical Square, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.juice_diet_9,
            title = "Mixed Berry Antioxidant",
            price = "189",
            restaurantName = "Berry Farms",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Berry Gardens, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.juice_diet_10,
            title = "Apple Carrot Ginger",
            price = "149",
            restaurantName = "Roots Juicery",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Vegetable Market, Delhi"
        ),

        // 11-15: VEGETABLE JUICES
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.juice_diet_11,
            title = "Beetroot Apple Blast",
            price = "159",
            restaurantName = "Red Roots",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹32",
            address = "Health Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.juice_diet_12,
            title = "Carrot Orange Sunshine",
            price = "139",
            restaurantName = "Vitamin Sea",
            rating = "4.8",
            deliveryTime = "8-14 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Wellness Street, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.juice_diet_13,
            title = "Tomato Celery Spice",
            price = "129",
            restaurantName = "Veggie Delight",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Green Market, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.juice_diet_14,
            title = "Cucumber Celery Cooler",
            price = "119",
            restaurantName = "Fresh Veggies",
            rating = "4.7",
            deliveryTime = "8-12 mins",
            distance = "0.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹24",
            address = "Farm Road, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.juice_diet_15,
            title = "Parsley Spinach Greenie",
            price = "149",
            restaurantName = "Green Machine",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Organic Valley, Delhi"
        ),

        // 16-18: PROTEIN SMOOTHIES
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.juice_diet_16,
            title = "Protein Berry Smoothie",
            price = "199",
            restaurantName = "Smoothie Bar",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "20% OFF",
            discountAmount = "up to ₹40",
            address = "Fitness Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.juice_diet_17,
            title = "Peanut Butter Banana Shake",
            price = "189",
            restaurantName = "Protein Plus",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Gym Street, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.juice_diet_18,
            title = "Mango Protein Power",
            price = "209",
            restaurantName = "Tropical Protein",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹42",
            address = "Fruit Bazaar, Delhi"
        ),

        // 19-20: SPECIALTY JUICES
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.juice_diet_19,
            title = "Turmeric Ginger Immunity",
            price = "149",
            restaurantName = "Ayurveda Juices",
            rating = "4.9",
            deliveryTime = "10-15 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Herbal Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.juice_diet_20,
            title = "Grand Juice Diet Platter",
            price = "399",
            restaurantName = "Juice Studio",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹80",
            address = "Gourmet Street, Delhi"
        )
    )
    Column {
        juiceDietItems.forEach { restaurantItem ->
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
fun LassiPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val lassiDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY LASSI DIET CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "sweet_lassi",
                    text = "Sweet Lassi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sweet_lassi  // Sweet/cup icon with sugar crystal
                ),
                FilterChip(
                    id = "salted_lassi",
                    text = "Salted Lassi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salted_lassi  // Salt shaker/cup icon
                ),
                FilterChip(
                    id = "mango_lassi",
                    text = "Mango Lassi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_mango_lassi  // Mango/cup icon
                ),
                FilterChip(
                    id = "rose_lassi",
                    text = "Rose Lassi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_rose_lassi  // Rose flower/cup icon
                ),
                FilterChip(
                    id = "dry_fruit_lassi",
                    text = "Dry Fruit Lassi",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_dry_fruit_lassi  // Nuts/dry fruits icon
                ),

                // 3. BASE INGREDIENTS (TEXT ONLY)
                FilterChip(
                    id = "yogurt_base",
                    text = "Fresh Yogurt",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "greek_yogurt",
                    text = "Greek Yogurt",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat_yogurt",
                    text = "Low-Fat Yogurt",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "full_cream_yogurt",
                    text = "Full Cream Yogurt",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "buffalo_milk",
                    text = "Buffalo Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cow_milk",
                    text = "Cow Milk",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. FLAVOR ADDITIONS (TEXT ONLY)
                FilterChip(
                    id = "mango_pulp",
                    text = "Mango Pulp",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rose_syrup",
                    text = "Rose Syrup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "strawberry",
                    text = "Strawberry",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "saffron",
                    text = "Kesar (Saffron)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cardamom",
                    text = "Cardamom",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rose_water",
                    text = "Rose Water",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. DRY FRUITS & TOPPINGS (TEXT ONLY)
                FilterChip(
                    id = "almonds",
                    text = "Badam (Almonds)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pistachios",
                    text = "Pista (Pistachios)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cashews",
                    text = "Kaju (Cashews)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "raisins",
                    text = "Kishmish (Raisins)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "charoli",
                    text = "Charoli/Chironji",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar-Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "probiotic",
                    text = "Probiotic Rich",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. OCCASION/PURPOSE (TEXT ONLY)
                FilterChip(
                    id = "summer_special",
                    text = "Summer Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "after_meal",
                    text = "After Meal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pre_workout",
                    text = "Pre-Workout",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "festival_special",
                    text = "Festival Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "party_lassi",
                    text = "Party Lassi",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. SIZE OPTIONS (TEXT ONLY)
                FilterChip(
                    id = "small_lassi",
                    text = "Small (200ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_lassi",
                    text = "Regular (300ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_lassi",
                    text = "Large (500ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_lassi",
                    text = "Family (1L)",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_50",
                    text = "Under ₹50",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "50_100",
                    text = "₹50 - ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_150",
                    text = "₹100 - ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_150",
                    text = "Above ₹150",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. REGIONAL VARIETIES (TEXT ONLY)
                FilterChip(
                    id = "punjabi_lassi",
                    text = "Punjabi Lassi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sindhi_lassi",
                    text = "Sindhi Lassi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "haryanvi_lassi",
                    text = "Haryanvi Lassi",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bihari_lassi",
                    text = "Bihari Lassi",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. SORT BY DROPDOWN
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
            filterConfig = lassiDietFilters,
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
        val lassiDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_sweet_lassi_2,  // Creamy white lassi in glass with malai topping
                title = "Classic Sweet Lassi",
                price = "89",
                restaurantName = "Punjabi Dhaba",
                rating = "4.9",
                deliveryTime = "10-15 mins",
                distance = "0.5 km",
                discount = "15%",
                discountAmount = "up to ₹13",
                address = "Old Market Road, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mango_lassi_1,  // Yellow-orange mango lassi with mango pieces
                title = "Royal Mango Lassi",
                price = "129",
                restaurantName = "Mango Grove",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.8 km",
                discount = "20%",
                discountAmount = "up to ₹26",
                address = "Fruit Market, Delhi"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_salted_lassi_1,  // White lassi with cumin and black salt
                title = "Masala Salted Lassi",
                price = "99",
                restaurantName = "Haldiram's Corner",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.6 km",
                discount = "10%",
                discountAmount = "up to ₹10",
                address = "Chowk Bazaar, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_dry_fruit_lassi_1,  // Thick lassi loaded with dry fruits
                title = "Kaju Pista Delight",
                price = "159",
                restaurantName = "Royal Sweets",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "12%",
                discountAmount = "up to ₹19",
                address = "Sweets Gallery, Delhi"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_rose_lassi_1,  // Pink lassi with rose petals
                title = "Gulabi Rose Lassi",
                price = "119",
                restaurantName = "Flavors of Awadh",
                rating = "4.8",
                deliveryTime = "12-17 mins",
                distance = "0.7 km",
                discount = "15%",
                discountAmount = "up to ₹18",
                address = "Old City, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_bhang_lassi,  // Greenish lassi with special herbs
                title = "Special Bhang Lassi",
                price = "199",
                restaurantName = "Blue Lassi Cafe",
                rating = "4.7",
                deliveryTime = "20-25 mins",
                distance = "1.5 km",
                discount = "5%",
                discountAmount = "up to ₹10",
                address = "River Side, Delhi",
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
            foodItems = lassiDietItems,
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
    val lassiDietItems = listOf(
        // 1-5: CLASSIC & SWEET LASSI VARIETIES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.lassi_diet_1,
            title = "Punjabi Sweet Lassi",
            price = "89",
            restaurantName = "Amritsari Dhaba",
            rating = "4.9",
            deliveryTime = "10-15 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹13",
            address = "Old Market Road, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.lassi_diet_2,
            title = "Malai Makkhan Lassi",
            price = "119",
            restaurantName = "Desi Tadka",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Village Square, Delhi"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.lassi_diet_3,
            title = "Kesar Elaichi Lassi",
            price = "149",
            restaurantName = "Royal Lassi House",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹30",
            address = "Palace Road, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.lassi_diet_4,
            title = "Rose Gulabi Lassi",
            price = "129",
            restaurantName = "Flavors of Awadh",
            rating = "4.8",
            deliveryTime = "12-17 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Old City, Delhi"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.lassi_diet_5,
            title = "Honey Almond Lassi",
            price = "159",
            restaurantName = "Pure Desi Ghee",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Sweet Market, Delhi"
        ),

        // 6-10: FRUIT LASSI VARIETIES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.lassi_diet_6,
            title = "Royal Mango Lassi",
            price = "139",
            restaurantName = "Mango Grove",
            rating = "4.9",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "20% OFF",
            discountAmount = "up to ₹28",
            address = "Fruit Market, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.lassi_diet_7,
            title = "Strawberry Cream Lassi",
            price = "149",
            restaurantName = "Berry Blast",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Berry Gardens, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.lassi_diet_8,
            title = "Banana Protein Lassi",
            price = "129",
            restaurantName = "Health Hub",
            rating = "4.8",
            deliveryTime = "8-12 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Fitness Street, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.lassi_diet_9,
            title = "Lychee Rose Lassi",
            price = "159",
            restaurantName = "Exotic Flavors",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹32",
            address = "Gourmet Lane, Delhi"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.lassi_diet_10,
            title = "Sitaphal (Custard Apple) Lassi",
            price = "169",
            restaurantName = "Seasonal Specials",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Fruit Bazaar, Delhi"
        ),

        // 11-15: SALTED & MASALA LASSI
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.lassi_diet_11,
            title = "Masala Salted Lassi",
            price = "99",
            restaurantName = "Haldiram's Corner",
            rating = "4.8",
            deliveryTime = "8-12 mins",
            distance = "0.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹10",
            address = "Chowk Bazaar, Delhi"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.lassi_diet_12,
            title = "Jeera Pudina Lassi",
            price = "109",
            restaurantName = "Fresh & Cool",
            rating = "4.7",
            deliveryTime = "8-12 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹16",
            address = "Herb Garden, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.lassi_diet_13,
            title = "Black Salt Cumin Lassi",
            price = "99",
            restaurantName = "Street Food King",
            rating = "4.8",
            deliveryTime = "5-10 mins",
            distance = "0.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹20",
            address = "Food Street, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.lassi_diet_14,
            title = "Green Chilli Ginger Lassi",
            price = "119",
            restaurantName = "Spicy Desi",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Spice Market, Delhi"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.lassi_diet_15,
            title = "Roasted Cumin Lassi",
            price = "109",
            restaurantName = "Traditional Tastes",
            rating = "4.7",
            deliveryTime = "8-12 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹16",
            address = "Old Delhi, Delhi"
        ),

        // 16-18: DRY FRUIT & RICH LASSI
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.lassi_diet_16,
            title = "Kaju Pista Badam Lassi",
            price = "189",
            restaurantName = "Royal Sweets",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Sweets Gallery, Delhi"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.lassi_diet_17,
            title = "Charoli Chironji Lassi",
            price = "179",
            restaurantName = "Heritage Foods",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹36",
            address = "Traditional Market, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.lassi_diet_18,
            title = "Dates & Walnut Lassi",
            price = "169",
            restaurantName = "Healthy Indulgence",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Wellness Street, Delhi"
        ),

        // 19-20: SPECIALTY & FUSION LASSI
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.lassi_diet_19,
            title = "Saffron Pista Lassi",
            price = "199",
            restaurantName = "Lucknowi Zaika",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹20",
            address = "Nawab Street, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.lassi_diet_20,
            title = "Grand Lassi Platter",
            price = "349",
            restaurantName = "Lassi Mahal",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.8 km",
            discount = "20% OFF",
            discountAmount = "up to ₹70",
            address = "Gourmet Street, Delhi",
        )
    )
    Column {
        lassiDietItems.forEach { restaurantItem ->
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
fun CurdRicePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val curdRiceDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY CURD RICE CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "south_indian_curd_rice",
                    text = "South Indian Curd Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_south_indian_curd_rice  // Traditional south indian bowl with curry leaves
                ),
                FilterChip(
                    id = "north_indian_dahi_chawal",
                    text = "North Indian Dahi Chawal",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_dahi_chawal  // North indian style with raita consistency
                ),
                FilterChip(
                    id = "tempered_curd_rice",
                    text = "Tempered Curd Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_tempered_curd_rice  // Tadka/seasoning icon with mustard seeds
                ),
                FilterChip(
                    id = "fruit_curd_rice",
                    text = "Fruit Curd Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruit_curd_rice  // Mixed fruits with yogurt
                ),
                FilterChip(
                    id = "vegetable_curd_rice",
                    text = "Vegetable Curd Rice",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegetable_curd_rice  // Mixed veggies with yogurt
                ),

                // 3. RICE VARIETIES (TEXT ONLY)
                FilterChip(
                    id = "basmati_rice",
                    text = "Basmati Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sona_masoori",
                    text = "Sona Masoori",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ponni_rice",
                    text = "Ponni Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brown_rice",
                    text = "Brown Rice (Healthy)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "parboiled_rice",
                    text = "Parboiled Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "leftover_rice",
                    text = "Leftover Rice Style",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. CURD/YOGURT TYPES (TEXT ONLY)
                FilterChip(
                    id = "fresh_curd",
                    text = "Fresh Homemade Curd",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thick_greek_yogurt",
                    text = "Thick Greek Yogurt",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sour_curd",
                    text = "Sour Curd (Traditional)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat_curd",
                    text = "Low-Fat Curd",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "buffalo_milk_curd",
                    text = "Buffalo Milk Curd",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cow_milk_curd",
                    text = "Cow Milk Curd",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. TEMPERING/TAKA INGREDIENTS (TEXT ONLY)
                FilterChip(
                    id = "mustard_seeds",
                    text = "Rai (Mustard Seeds)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "curry_leaves",
                    text = "Curry Leaves",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "urad_dal",
                    text = "Urad Dal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chana_dal",
                    text = "Chana Dal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hing",
                    text = "Hing (Asafoetida)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dry_red_chilli",
                    text = "Dry Red Chilli",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ginger",
                    text = "Ginger",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "green_chilli",
                    text = "Green Chilli",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. ADD-INS & MIX-INS (TEXT ONLY)
                FilterChip(
                    id = "pomegranate",
                    text = "Anar (Pomegranate)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grapes",
                    text = "Angoor (Grapes)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mango_pieces",
                    text = "Raw Mango Pieces",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cucumber",
                    text = "Cucumber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "carrot_grated",
                    text = "Grated Carrot",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "onion",
                    text = "Onion",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coriander",
                    text = "Fresh Coriander",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mint",
                    text = "Mint Leaves",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. NUTS & DRY FRUITS (TEXT ONLY)
                FilterChip(
                    id = "cashews_curd_rice",
                    text = "Kaju (Cashews)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "raisins_curd_rice",
                    text = "Kishmish (Raisins)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "almonds_curd_rice",
                    text = "Badam (Almonds)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pista_curd_rice",
                    text = "Pista (Pistachios)",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "vegan_curd_rice",
                    text = "Vegan (Coconut Curd)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_curd_rice",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat_curd_rice",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein_curd_rice",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "probiotic_rich",
                    text = "Probiotic Rich",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_onion_garlic",
                    text = "No Onion-Garlic",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "satvik",
                    text = "Satvik (Pure)",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. OCCASION/PURPOSE (TEXT ONLY)
                FilterChip(
                    id = "comfort_food",
                    text = "Comfort Food",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "after_meal_curd_rice",
                    text = "After Meal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "summer_cooler",
                    text = "Summer Cooler",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sick_day_food",
                    text = "Sick Day Food",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "festive_special",
                    text = "Festive Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brunch_special",
                    text = "Brunch Special",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. REGIONAL VARIETIES (TEXT ONLY)
                FilterChip(
                    id = "tamil_nadu_curd_rice",
                    text = "Tamil Nadu Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "karnataka_mosranna",
                    text = "Karnataka Mosranna",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "andhra_curd_rice",
                    text = "Andhra Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kerala_curd_rice",
                    text = "Kerala Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "telangana_perugu_annam",
                    text = "Telangana Perugu Annam",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. SIDE ACCOMPANIMENTS (TEXT ONLY)
                FilterChip(
                    id = "mango_pickle",
                    text = "Mango Pickle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lime_pickle",
                    text = "Lime Pickle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "papad",
                    text = "Papad",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vadam",
                    text = "Vadam (Fried Crisps)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thoran",
                    text = "Vegetable Thoran",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. SIZE OPTIONS (TEXT ONLY)
                FilterChip(
                    id = "small_curd_rice",
                    text = "Small Bowl (200g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_curd_rice",
                    text = "Regular Bowl (350g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_curd_rice",
                    text = "Large Bowl (500g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_curd_rice",
                    text = "Family Pack (1kg)",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_60",
                    text = "Under ₹60",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "60_100",
                    text = "₹60 - ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_150_curd",
                    text = "₹100 - ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_150_curd",
                    text = "Above ₹150",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SORT BY DROPDOWN
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
            filterConfig = curdRiceDietFilters,
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
        val curdRiceDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_south_indian_curd_rice_1,  // White curd rice in banana leaf with curry leaves tempering
                title = "South Indian Curd Rice",
                price = "99",
                restaurantName = "Madras Cafe",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "0.6 km",
                discount = "15%",
                discountAmount = "up to ₹15",
                address = "Triplicane, Chennai"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_tempered_curd_rice_1,  // Curd rice with mustard seeds, curry leaves, red chilli tempering on top
                title = "Tempered Curd Rice",
                price = "109",
                restaurantName = "Saravana Bhavan",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "10%",
                discountAmount = "up to ₹11",
                address = "T. Nagar, Chennai"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_fruit_curd_rice_1,  // Curd rice with pomegranate, grapes, apple pieces
                title = "Fruit Curd Rice",
                price = "129",
                restaurantName = "Fruitful Bowl",
                rating = "4.7",
                deliveryTime = "12-18 mins",
                distance = "0.9 km",
                discount = "15%",
                discountAmount = "up to ₹19",
                address = "Fruit Market, Chennai"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_vegetable_curd_rice_1,  // Curd rice with carrot, cucumber, grated vegetables
                title = "Vegetable Curd Rice",
                price = "119",
                restaurantName = "Green Kitchen",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.7 km",
                discount = "12%",
                discountAmount = "up to ₹14",
                address = "Vegetable Market, Chennai"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_dahi_chawal_1,  // North Indian style dahi chawal with raita consistency and boondi
                title = "North Indian Dahi Chawal",
                price = "109",
                restaurantName = "Haldiram's",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "10%",
                discountAmount = "up to ₹11",
                address = "Chandni Chowk, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_andhra_curd_rice_1,  // Spicier version with green chillies, ginger, and curry leaves
                title = "Andhra Perugu Annam",
                price = "119",
                restaurantName = "Andhra Mess",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "15%",
                discountAmount = "up to ₹18",
                address = "Andhra Colony, Chennai",
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
            foodItems = curdRiceDietItems,
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
    val curdRiceDietItems = listOf(
        // 1-5: CLASSIC CURD RICE VARIETIES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.curd_rice_diet_1,
            title = "Traditional South Indian Curd Rice",
            price = "99",
            restaurantName = "Madras Cafe",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹15",
            address = "Triplicane High Road, Chennai"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.curd_rice_diet_2,
            title = "Kalyana Veetu Curd Rice",
            price = "129",
            restaurantName = "Kalyana Bhavan",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Marriage Road, Madurai"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.curd_rice_diet_3,
            title = "Temple Style Curd Rice",
            price = "89",
            restaurantName = "Annapoorna Mess",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹18",
            address = "Temple Street, Trichy"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.curd_rice_diet_4,
            title = "Grandmother's Recipe Curd Rice",
            price = "119",
            restaurantName = "Paati's Kitchen",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Residential Colony, Coimbatore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.curd_rice_diet_5,
            title = "Simple Homestyle Curd Rice",
            price = "79",
            restaurantName = "Home Food Express",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹8",
            address = "Gandhi Nagar, Bangalore"
        ),

        // 6-10: FLAVORED & TEMPERED CURD RICE
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.curd_rice_diet_6,
            title = "Pomegranate Curd Rice",
            price = "149",
            restaurantName = "Fusion Foods",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹30",
            address = "Fruit Market, Hyderabad"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.curd_rice_diet_7,
            title = "Mango Curd Rice",
            price = "159",
            restaurantName = "Seasonal Delights",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Mango Garden, Bangalore"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.curd_rice_diet_8,
            title = "Jeera Tadka Curd Rice",
            price = "109",
            restaurantName = "Spice Junction",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹16",
            address = "Spice Market, Chennai"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.curd_rice_diet_9,
            title = "Mustard & Curry Leaf Curd Rice",
            price = "119",
            restaurantName = "South Indian Tiffins",
            rating = "4.8",
            deliveryTime = "12-17 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Mylapore, Chennai"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.curd_rice_diet_10,
            title = "Ginger & Green Chilli Curd Rice",
            price = "129",
            restaurantName = "Spicy Treat",
            rating = "4.6",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹26",
            address = "Jayanagar, Bangalore"
        ),

        // 11-15: HEALTHY & DIET CURD RICE
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.curd_rice_diet_11,
            title = "Brown Rice Curd Rice",
            price = "149",
            restaurantName = "Healthy Bites",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Wellness Colony, Pune"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.curd_rice_diet_12,
            title = "Low Fat Greek Yogurt Rice",
            price = "169",
            restaurantName = "Fit Food Kitchen",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹34",
            address = "Fitness Street, Mumbai"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.curd_rice_diet_13,
            title = "Quinoa Curd Rice",
            price = "189",
            restaurantName = "Modern Healthy",
            rating = "4.7",
            deliveryTime = "18-22 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Health Hub, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.curd_rice_diet_14,
            title = "Millet Curd Rice",
            price = "159",
            restaurantName = "Organic Valley",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Organic Market, Mysore"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.curd_rice_diet_15,
            title = "Protein Rich Curd Rice with Nuts",
            price = "199",
            restaurantName = "Power Bowls",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹30",
            address = "Sports Complex, Bangalore"
        ),

        // 16-18: DRY FRUIT & RICH CURD RICE
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.curd_rice_diet_16,
            title = "Cashew & Raisin Curd Rice",
            price = "179",
            restaurantName = "Royal South Indian",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.curd_rice_diet_17,
            title = "Badam Pista Curd Rice",
            price = "189",
            restaurantName = "Heritage Taste",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "1.4 km",
            discount = "20% OFF",
            discountAmount = "up to ₹38",
            address = "Old City, Hyderabad"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.curd_rice_diet_18,
            title = "Dates & Walnut Curd Rice",
            price = "199",
            restaurantName = "Nutri Bowl",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹30",
            address = "Green Park, Delhi"
        ),

        // 19-20: SPECIALTY & COMBO CURD RICE
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.curd_rice_diet_19,
            title = "Curd Rice with Papad & Pickle",
            price = "149",
            restaurantName = "Complete Meal",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Food Court, Chennai"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.curd_rice_diet_20,
            title = "Grand Curd Rice Thali",
            price = "299",
            restaurantName = "Bharatiya Rasoi",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "2.0 km",
            discount = "20% OFF",
            discountAmount = "up to ₹60",
            address = "Thali Village, Bangalore"
        )
    )
    Column {
        curdRiceDietItems.forEach { restaurantItem ->
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
fun PuddingPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val puddingDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY PUDDING CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "rice_pudding_kheer",
                    text = "Rice Pudding (Kheer)",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_rice_pudding_diet  // Traditional kheer bowl with rice grains
                ),
                FilterChip(
                    id = "bread_pudding",
                    text = "Bread Pudding",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_bread_pudding_diet  // Bread cubes in custard
                ),
                FilterChip(
                    id = "chocolate_pudding",
                    text = "Chocolate Pudding",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chocolate_pudding_diet  // Chocolate dessert cup
                ),
                FilterChip(
                    id = "caramel_pudding",
                    text = "Caramel Pudding (Flan)",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_caramel_pudding_diet  // Caramel custard with sauce
                ),
                FilterChip(
                    id = "tapioca_pudding",
                    text = "Tapioca Pudding (Sabudana Kheer)",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_tapioca_pudding_diet  // Sabudana pearls in milk
                ),
                // 4. BASE INGREDIENTS (TEXT ONLY)
                FilterChip(
                    id = "milk_pudding",
                    text = "Milk-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cream_pudding",
                    text = "Cream-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "custard_pudding",
                    text = "Custard-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_pudding",
                    text = "Egg-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cornflour_pudding",
                    text = "Cornflour-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gelatin_pudding",
                    text = "Gelatin Set",
                    type = FilterType.TEXT_ONLY
                ),
                // 6. INTERNATIONAL PUDDINGS (TEXT ONLY)
                FilterChip(
                    id = "english_pudding",
                    text = "English Pudding",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "american_pudding",
                    text = "American Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "french_pudding",
                    text = "French Custard",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spanish_flan",
                    text = "Spanish Flan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "japanese_pudding",
                    text = "Japanese Purin",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. FLAVOR PROFILES (TEXT ONLY)
                FilterChip(
                    id = "vanilla_pudding",
                    text = "Vanilla",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_pudding_flavor",
                    text = "Chocolate",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cardamom_pudding",
                    text = "Cardamom (Elaichi)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "saffron_pudding",
                    text = "Saffron (Kesar)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rose_pudding",
                    text = "Rose (Gulab)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coffee_pudding",
                    text = "Coffee",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "butterscotch",
                    text = "Butterscotch",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. NUTS & DRY FRUITS (TEXT ONLY)
                FilterChip(
                    id = "almond_pudding",
                    text = "Almonds (Badam)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pistachio_pudding",
                    text = "Pistachios (Pista)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cashew_pudding",
                    text = "Cashews (Kaju)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "walnut_pudding",
                    text = "Walnuts (Akhrot)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "raisin_pudding",
                    text = "Raisins (Kishmish)",
                    type = FilterType.TEXT_ONLY
                ),
                // 10. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "vegan_pudding",
                    text = "Vegan (Plant-Based)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_pudding",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_sugar_pudding",
                    text = "Low Sugar",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free_pudding",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless_pudding",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat_pudding",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. OCCASION/PURPOSE (TEXT ONLY)
                FilterChip(
                    id = "festival_sweet",
                    text = "Festival Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "party_dessert",
                    text = "Party Dessert",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "comfort_pudding",
                    text = "Comfort Food",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kids_special",
                    text = "Kids Favourite",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "date_night_dessert",
                    text = "Date Night Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "after_dinner_pudding",
                    text = "After Dinner",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. TEXTURE PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "creamy_pudding",
                    text = "Creamy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "silky_pudding",
                    text = "Silky Smooth",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chunky_pudding",
                    text = "Chunky (With Fruits/Nuts)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "layered_pudding",
                    text = "Layered",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "baked_pudding",
                    text = "Baked",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "steamed_pudding",
                    text = "Steamed",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chilled_pudding",
                    text = "Chilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "warm_pudding",
                    text = "Warm",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. TEMPERATURE (TEXT ONLY)
                FilterChip(
                    id = "hot_pudding",
                    text = "Hot/Served Warm",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cold_pudding",
                    text = "Cold/Chilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "room_temp_pudding",
                    text = "Room Temperature",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SERVING SIZE (TEXT ONLY)
                FilterChip(
                    id = "individual_pudding",
                    text = "Individual Cup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "small_pudding",
                    text = "Small (100-150g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_pudding",
                    text = "Regular (200-250g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_pudding",
                    text = "Family Size (500g+)",
                    type = FilterType.TEXT_ONLY
                ),

                // 15. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_80_pudding",
                    text = "Under ₹80",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "80_150_pudding",
                    text = "₹80 - ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "150_250_pudding",
                    text = "₹150 - ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_250_pudding",
                    text = "Above ₹250",
                    type = FilterType.TEXT_ONLY
                ),

                // 16. SORT BY DROPDOWN
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
            filterConfig = puddingDietFilters,
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
        val puddingDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_rice_kheer_1,  // Traditional rice kheer in clay pot with nuts and saffron
                title = "Traditional Rice Kheer",
                price = "89",
                restaurantName = "Mishram Sweets",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "0.7 km",
                discount = "10%",
                discountAmount = "up to ₹9",
                address = "Chandni Chowk, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_bread_pudding_1,  // Shahi tukda style bread pudding with nuts and rabri
                title = "Shahi Bread Pudding",
                price = "149",
                restaurantName = "Royal Desserts",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹22",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_caramel_custard_1,  // Caramel flan with golden caramel sauce on top
                title = "Caramel Custard",
                price = "129",
                restaurantName = "Flan House",
                rating = "4.7",
                deliveryTime = "10-15 mins",
                distance = "0.8 km",
                discount = "12%",
                discountAmount = "up to ₹15",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_seviyan_kheer_1,  // Vermicelli kheer with nuts and cardamom
                title = "Seviyan Kheer",
                price = "99",
                restaurantName = "Lucknawi Zaika",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.6 km",
                discount = "10%",
                discountAmount = "up to ₹10",
                address = "Old Lucknow, Lucknow"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mango_pudding_1,  // Mango pudding with fresh mango pieces
                title = "Mango Pudding",
                price = "139",
                restaurantName = "Mango Mania",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.9 km",
                discount = "15%",
                discountAmount = "up to ₹21",
                address = "Fruit Market, Mumbai"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_chocolate_pudding_1,  // Rich chocolate pudding with chocolate shavings
                title = "Belgian Chocolate Pudding",
                price = "159",
                restaurantName = "Chocolate Room",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.1 km",
                discount = "10%",
                discountAmount = "up to ₹16",
                address = "Bandra West, Mumbai"
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
            foodItems = puddingDietItems,
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
    val puddingDietItems = listOf(
        // 1-5: CLASSIC INDIAN PUDDINGS (KHEER & PAYASAM)
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.pudding_diet_1,
            title = "Traditional Rice Kheer",
            price = "99",
            restaurantName = "Mishram Sweets",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹10",
            address = "Chandni Chowk, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.pudding_diet_2,
            title = "Semiya Payasam (Vermicelli Pudding)",
            price = "109",
            restaurantName = "Kerala Kitchen",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹16",
            address = "Fort Kochi, Kerala"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.pudding_diet_3,
            title = "Phirni (Clay Pot Rice Pudding)",
            price = "119",
            restaurantName = "Mughal Darbar",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹14",
            address = "Old Delhi, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.pudding_diet_4,
            title = "Paradha Payasam (South Indian Kheer)",
            price = "129",
            restaurantName = "Temple Bhavan",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Guruvayur, Kerala"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.pudding_diet_5,
            title = "Doodh Pak (Slow-Cooked Rice Pudding)",
            price = "139",
            restaurantName = "Royal Thali",
            rating = "4.9",
            deliveryTime = "18-22 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Palace Road, Jaipur"
        ),

        // 6-10: BREAD & MILK PUDDINGS
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.pudding_diet_6,
            title = "Shahi Tukda (Bread Pudding)",
            price = "149",
            restaurantName = "Lucknawi Zaika",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Hazratganj, Lucknow"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.pudding_diet_7,
            title = "Double Ka Meetha (Hyderabadi Bread Pudding)",
            price = "159",
            restaurantName = "Nizam's Kitchen",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "1.3 km",
            discount = "20% OFF",
            discountAmount = "up to ₹32",
            address = "Old City, Hyderabad"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.pudding_diet_8,
            title = "Caramel Custard Pudding",
            price = "129",
            restaurantName = "Flan House",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.pudding_diet_9,
            title = "Bread & Butter Pudding",
            price = "139",
            restaurantName = "Anglo Indian Cafe",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Park Street, Kolkata"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.pudding_diet_10,
            title = "Malai Rabri Pudding",
            price = "169",
            restaurantName = "Sweets Corner",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹20",
            address = "Juhu, Mumbai"
        ),

        // 11-15: FRUIT & FLAVORED PUDDINGS
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.pudding_diet_11,
            title = "Mango Phirni",
            price = "159",
            restaurantName = "Mango Mania",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Fruit Market, Mumbai"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.pudding_diet_12,
            title = "Strawberry Cream Pudding",
            price = "169",
            restaurantName = "Berry Bliss",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Berry Gardens, Bangalore"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.pudding_diet_13,
            title = "Chikoo (Sapota) Pudding",
            price = "149",
            restaurantName = "Seasonal Delights",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Maharashtra Market, Pune"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.pudding_diet_14,
            title = "Sitaphal (Custard Apple) Kheer",
            price = "179",
            restaurantName = "Exotic Flavors",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹36",
            address = "Fruit Bazaar, Chennai"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.pudding_diet_15,
            title = "Jackfruit Payasam",
            price = "169",
            restaurantName = "Coastal Kitchen",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "1.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Mangalore, Karnataka"
        ),

        // 16-18: NUTS & DRY FRUIT PUDDINGS
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.pudding_diet_16,
            title = "Badam (Almond) Kheer",
            price = "189",
            restaurantName = "Nut House",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.pudding_diet_17,
            title = "Pista (Pistachio) Phirni",
            price = "199",
            restaurantName = "Royal Sweets",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.pudding_diet_18,
            title = "Kaju (Cashew) & Kesari Pudding",
            price = "209",
            restaurantName = "Heritage Taste",
            rating = "4.9",
            deliveryTime = "18-22 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹31",
            address = "Old City, Hyderabad"
        ),

        // 19-20: SPECIALTY & DIET PUDDINGS
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.pudding_diet_19,
            title = "Sabudana (Tapioca) Kheer",
            price = "129",
            restaurantName = "Healthy Indulgence",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹13",
            address = "Wellness Street, Pune"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.pudding_diet_20,
            title = "Sugar-Free Kesar Pudding",
            price = "179",
            restaurantName = "Diet Desserts",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Fitness Street, Mumbai"
        )
    )
    Column {
        puddingDietItems.forEach { restaurantItem ->
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
fun CustardPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val custardDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY CUSTARD CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "classic_custard",
                    text = "Classic Custard",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_classic_custard_diet  // Traditional vanilla custard bowl
                ),
                FilterChip(
                    id = "fruit_custard",
                    text = "Fruit Custard",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_fruit_custard_diet  // Mixed fruits in creamy custard
                ),
                FilterChip(
                    id = "baked_custard",
                    text = "Baked Custard",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_baked_custard_diet  // Baked custard with caramelized top
                ),
                FilterChip(
                    id = "steamed_custard",
                    text = "Steamed Custard",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_steamed_custard_diet  // Steamed custard in ramekin
                ),
                FilterChip(
                    id = "caramel_custard",
                    text = "Caramel Custard",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_caramel_custard_diet  // Flan with caramel sauce
                ),
                // 4. BASE TYPES (TEXT ONLY)
                FilterChip(
                    id = "milk_custard",
                    text = "Milk-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cream_custard",
                    text = "Cream-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_custard",
                    text = "Egg-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cornflour_custard",
                    text = "Cornflour-Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "custard_powder",
                    text = "Custard Powder",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. FLAVOR PROFILES (TEXT ONLY)
                FilterChip(
                    id = "vanilla_custard",
                    text = "Vanilla",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_custard",
                    text = "Chocolate",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "butterscotch_custard",
                    text = "Butterscotch",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "strawberry_custard_flavor",
                    text = "Strawberry",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mango_custard_flavor",
                    text = "Mango",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pineapple_custard",
                    text = "Pineapple",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pista_custard",
                    text = "Pistachio (Pista)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "badam_custard",
                    text = "Almond (Badam)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kesar_custard",
                    text = "Saffron (Kesar)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "rose_custard",
                    text = "Rose (Gulab)",
                    type = FilterType.TEXT_ONLY
                ),
              // 8. CONSISTENCY & TEXTURE (TEXT ONLY)
                FilterChip(
                    id = "thin_custard",
                    text = "Thin/Pouring",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thick_custard",
                    text = "Thick/Set",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "creamy_custard",
                    text = "Creamy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "smooth_custard",
                    text = "Smooth",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chunky_custard",
                    text = "Chunky (with Fruits)",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. TEMPERATURE (TEXT ONLY)
                FilterChip(
                    id = "hot_custard",
                    text = "Hot/Warm Custard",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cold_custard",
                    text = "Cold/Chilled Custard",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "room_temp_custard",
                    text = "Room Temperature",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "vegan_custard",
                    text = "Vegan (Plant-Based)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_custard",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_sugar_custard",
                    text = "Low Sugar",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free_custard",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless_custard_diet",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat_custard",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein_custard",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. OCCASION/PURPOSE (TEXT ONLY)
                FilterChip(
                    id = "party_custard",
                    text = "Party Dessert",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kids_custard",
                    text = "Kids Favorite",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "comfort_custard",
                    text = "Comfort Food",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "after_meal_custard",
                    text = "After Meal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "festival_custard",
                    text = "Festival Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "summer_custard",
                    text = "Summer Cooler",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. REGIONAL VARIETIES (TEXT ONLY)
                FilterChip(
                    id = "british_custard",
                    text = "British Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "french_custard",
                    text = "French Crème",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spanish_custard",
                    text = "Spanish Flan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "asian_custard",
                    text = "Asian Style",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. SERVING STYLE (TEXT ONLY)
                FilterChip(
                    id = "individual_cup_custard",
                    text = "Individual Cups",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_bowl_custard",
                    text = "Family Bowl",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "layered_custard",
                    text = "Layered Dessert",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "trifle_custard",
                    text = "Custard Trifle",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SPECIAL ADDITIONS (TEXT ONLY)
                FilterChip(
                    id = "jelly_custard",
                    text = "With Jelly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cake_custard",
                    text = "With Cake Pieces",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookie_custard",
                    text = "With Cookies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ice_cream_custard",
                    text = "With Ice Cream",
                    type = FilterType.TEXT_ONLY
                ),

                // 15. SERVING SIZE (TEXT ONLY)
                FilterChip(
                    id = "small_custard",
                    text = "Small (100ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_custard",
                    text = "Regular (200ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_custard",
                    text = "Large (350ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_custard",
                    text = "Family (500ml+)",
                    type = FilterType.TEXT_ONLY
                ),

                // 16. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_70_custard",
                    text = "Under ₹70",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "70_120_custard",
                    text = "₹70 - ₹120",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "120_180_custard",
                    text = "₹120 - ₹180",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_180_custard",
                    text = "Above ₹180",
                    type = FilterType.TEXT_ONLY
                ),

                // 17. SORT BY DROPDOWN
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
            filterConfig = custardDietFilters,
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
        val custardDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_classic_custard_1,  // Classic vanilla custard in a dessert bowl with nutmeg sprinkle
                title = "Classic Vanilla Custard",
                price = "79",
                restaurantName = "Dessert House",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.6 km",
                discount = "10%",
                discountAmount = "up to ₹8",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mixed_fruit_custard_1,  // Mixed fruit custard with apple, banana, pomegranate and grapes
                title = "Mixed Fruit Custard",
                price = "119",
                restaurantName = "Fruit Bowl",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.8 km",
                discount = "12%",
                discountAmount = "up to ₹14",
                address = "Fruit Market, Mumbai"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_caramel_custard_2,  // Caramel custard flan with golden caramel sauce and cream
                title = "Caramel Custard Flan",
                price = "129",
                restaurantName = "Flan House",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.9 km",
                discount = "15%",
                discountAmount = "up to ₹19",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_mango_custard_1,  // Mango custard with fresh mango slices and mint garnish
                title = "Alphonso Mango Custard",
                price = "139",
                restaurantName = "Mango Mania",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "1.0 km",
                discount = "10%",
                discountAmount = "up to ₹14",
                address = "Bandra West, Mumbai"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_baked_custard_1,  // Baked custard with caramelized top and berry compote
                title = "Baked Egg Custard",
                price = "109",
                restaurantName = "Bake House",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.7 km",
                discount = "12%",
                discountAmount = "up to ₹13",
                address = "Park Street, Kolkata"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_strawberry_custard_1,  // Strawberry custard with fresh strawberry pieces
                title = "Strawberry Cream Custard",
                price = "129",
                restaurantName = "Berry Bliss",
                rating = "4.8",
                deliveryTime = "12-18 mins",
                distance = "1.1 km",
                discount = "15%",
                discountAmount = "up to ₹19",
                address = "Berry Gardens, Bangalore"
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
            foodItems = custardDietItems,
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
    val custardDietItems = listOf(
        // 1-5: CLASSIC CUSTARD VARIETIES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.custard_diet_1,
            title = "Classic Vanilla Custard",
            price = "79",
            restaurantName = "Dessert House",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹8",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.custard_diet_2,
            title = "Eggless Vanilla Custard",
            price = "89",
            restaurantName = "Pure Veg Sweets",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹9",
            address = "Marine Lines, Mumbai"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.custard_diet_3,
            title = "Caramel Custard Flan",
            price = "129",
            restaurantName = "Flan House",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.custard_diet_4,
            title = "Baked Egg Custard",
            price = "109",
            restaurantName = "Bake House",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "0.7 km",
            discount = "12% OFF",
            discountAmount = "up to ₹13",
            address = "Park Street, Kolkata"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.custard_diet_5,
            title = "Steamed Custard Pudding",
            price = "99",
            restaurantName = "Steam Kitchen",
            rating = "4.6",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹10",
            address = "Andheri East, Mumbai"
        ),

        // 6-10: FRUIT CUSTARDS
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.custard_diet_6,
            title = "Mixed Fruit Custard",
            price = "119",
            restaurantName = "Fruit Bowl",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹14",
            address = "Fruit Market, Mumbai"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.custard_diet_7,
            title = "Alphonso Mango Custard",
            price = "139",
            restaurantName = "Mango Mania",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Bandra West, Mumbai"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.custard_diet_8,
            title = "Strawberry Cream Custard",
            price = "129",
            restaurantName = "Berry Bliss",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Berry Gardens, Bangalore"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.custard_diet_9,
            title = "Banana Walnut Custard",
            price = "109",
            restaurantName = "Healthy Treats",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹13",
            address = "Wellness Street, Pune"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.custard_diet_10,
            title = "Baked Apple Custard",
            price = "119",
            restaurantName = "Apple Orchard",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Shimla Hills, Delhi"
        ),

        // 11-15: FLAVORED CUSTARDS
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.custard_diet_11,
            title = "Rich Chocolate Custard",
            price = "139",
            restaurantName = "Chocolate Room",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Bandra West, Mumbai"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.custard_diet_12,
            title = "Butterscotch Crunch Custard",
            price = "129",
            restaurantName = "Sweet Tooth",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "12% OFF",
            discountAmount = "up to ₹15",
            address = "Jayanagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.custard_diet_13,
            title = "Pista (Pistachio) Custard",
            price = "149",
            restaurantName = "Royal Sweets",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.custard_diet_14,
            title = "Badam (Almond) Custard",
            price = "149",
            restaurantName = "Nut House",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "12% OFF",
            discountAmount = "up to ₹18",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.custard_diet_15,
            title = "Kesar (Saffron) Custard",
            price = "159",
            restaurantName = "Royal Thali",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹16",
            address = "Palace Road, Jaipur"
        ),

        // 16-18: PREMIUM & SPECIALTY CUSTARDS
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.custard_diet_16,
            title = "Tiramisu Custard",
            price = "169",
            restaurantName = "Italian Delights",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Colaba, Mumbai"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.custard_diet_17,
            title = "Coconut Cream Custard",
            price = "139",
            restaurantName = "Coastal Flavors",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "12% OFF",
            discountAmount = "up to ₹17",
            address = "Marine Drive, Kochi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.custard_diet_18,
            title = "Coffee Walnut Custard",
            price = "149",
            restaurantName = "Cafe Dessert",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Brigade Road, Bangalore"
        ),

        // 19-20: LAYERED & TRIFLE CUSTARDS
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.custard_diet_19,
            title = "Fruit Custard Trifle",
            price = "159",
            restaurantName = "Trifle House",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.4 km",
            discount = "12% OFF",
            discountAmount = "up to ₹19",
            address = "Park Street, Kolkata"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.custard_diet_20,
            title = "Layered Berry Custard",
            price = "169",
            restaurantName = "Layered Desserts",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Jubilee Hills, Hyderabad"
        )
    )
    Column {
        custardDietItems.forEach { restaurantItem ->
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
fun SoupDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val soupDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY SOUP CATEGORIES (WITH ICONS)
                FilterChip(
                    id = "chicken_soup",
                    text = "Chicken Soup",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_soup_diet  // Chicken broth with pieces
                ),
                FilterChip(
                    id = "vegetable_soup",
                    text = "Vegetable Soup",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vegetable_soup_diet  // Mixed vegetables in broth
                ),
                FilterChip(
                    id = "tomato_soup",
                    text = "Tomato Soup",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_tomato_soup_diet  // Creamy tomato soup
                ),
                FilterChip(
                    id = "lentil_soup",
                    text = "Lentil Soup",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_lentil_soup_diet  // Dal/bean based soup
                ),
                FilterChip(
                    id = "cream_soup",
                    text = "Cream Soup",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cream_soup_diet  // Creamy bisque style
                ),
                // 3. BROTH BASE (TEXT ONLY)
                FilterChip(
                    id = "clear_broth",
                    text = "Clear Broth",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thick_soup",
                    text = "Thick Soup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "creamy_base",
                    text = "Creamy Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tomato_base",
                    text = "Tomato Base",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coconut_base",
                    text = "Coconut Base",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. PROTEIN TYPES (TEXT ONLY)
                FilterChip(
                    id = "chicken_protein",
                    text = "Chicken",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mutton_protein",
                    text = "Mutton/Lamb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_protein",
                    text = "Fish/Seafood",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_protein",
                    text = "Egg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tofu_protein",
                    text = "Tofu/Paneer",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lentil_protein",
                    text = "Lentils/Beans",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. CUISINE STYLES (TEXT ONLY)
                FilterChip(
                    id = "indian_soup",
                    text = "Indian Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chinese_soup",
                    text = "Chinese Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thai_soup",
                    text = "Thai Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "italian_soup",
                    text = "Italian Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mexican_soup",
                    text = "Mexican Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "american_soup",
                    text = "American Style",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "low_calorie_soup",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein_soup",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb_soup",
                    text = "Low Carb/Keto",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan_soup",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_soup",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dairy_free_soup",
                    text = "Dairy Free",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. HEALTH FOCUS (TEXT ONLY)
                FilterChip(
                    id = "detox_soup",
                    text = "Detox/Cleanse",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "immunity_soup",
                    text = "Immunity Booster",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cold_fighting",
                    text = "Cold/Flu Fighter",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gut_health",
                    text = "Gut Health",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. TEMPERATURE (TEXT ONLY)
                FilterChip(
                    id = "hot_soup",
                    text = "Hot Soup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cold_soup",
                    text = "Cold Soup",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. OCCASION/SEASON (TEXT ONLY)
                FilterChip(
                    id = "winter_soup",
                    text = "Winter Warmer",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "monsoon_soup",
                    text = "Monsoon Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "summer_soup",
                    text = "Summer Cooler",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sick_day",
                    text = "Sick Day Comfort",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "appetizer",
                    text = "Starter/Appetizer",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "light_meal",
                    text = "Light Meal",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. TEXTURE/CONSISTENCY (TEXT ONLY)
                FilterChip(
                    id = "chunky_soup",
                    text = "Chunky",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "smooth_soup",
                    text = "Smooth/Puréed",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brothy_soup",
                    text = "Brothy/Light",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. SPECIAL ADDITIONS (TEXT ONLY)
                FilterChip(
                    id = "with_croutons",
                    text = "With Croutons",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_noodles",
                    text = "With Noodles",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_rice",
                    text = "With Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "with_dumplings",
                    text = "With Dumplings",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. SERVING SIZE (TEXT ONLY)
                FilterChip(
                    id = "small_soup",
                    text = "Small (200ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_soup",
                    text = "Regular (350ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_soup",
                    text = "Large (500ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_soup",
                    text = "Family (1L+)",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_80_soup",
                    text = "Under ₹80",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "80_150_soup",
                    text = "₹80 - ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "150_250_soup",
                    text = "₹150 - ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_250_soup",
                    text = "Above ₹250",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SORT BY DROPDOWN
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
            filterConfig = soupDietFilters,
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
        val soupDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_classic_tomato_soup_1,  // Creamy tomato soup in a bowl with basil leaf and cream swirl
                title = "Classic Tomato Soup",
                price = "89",
                restaurantName = "Soup Symphony",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.5 km",
                discount = "15%",
                discountAmount = "up to ₹13",
                address = "MG Road, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_chicken_noodle_soup_1,  // Chicken noodle soup with vegetables in clear broth
                title = "Hearty Chicken Noodle",
                price = "149",
                restaurantName = "Brother's Broth",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "12%",
                discountAmount = "up to ₹18",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_cream_of_mushroom_1,  // Cream of mushroom soup with mushroom slices and herbs
                title = "Cream of Mushroom",
                price = "119",
                restaurantName = "The Soup Spoon",
                rating = "4.7",
                deliveryTime = "12-18 mins",
                distance = "0.7 km",
                discount = "10%",
                discountAmount = "up to ₹12",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_thai_coconut_soup_1,  // Thai coconut soup (Tom Kha) with lemongrass and mushrooms
                title = "Thai Coconut Soup",
                price = "169",
                restaurantName = "Thai Basil",
                rating = "4.9",
                deliveryTime = "18-25 mins",
                distance = "1.2 km",
                discount = "15%",
                discountAmount = "up to ₹25",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mixed_vegetable_soup_1,  // Mixed vegetable soup with carrots, beans, corn in clear broth
                title = "Garden Vegetable Soup",
                price = "99",
                restaurantName = "Green Bowl",
                rating = "4.8",
                deliveryTime = "10-15 mins",
                distance = "0.6 km",
                discount = "20%",
                discountAmount = "up to ₹20",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_lentil_soup_1,  // Hearty lentil soup (dal shorba) with Indian spices
                title = "Masala Lentil Shorba",
                price = "109",
                restaurantName = "Spice Route",
                rating = "4.8",
                deliveryTime = "12-18 mins",
                distance = "0.9 km",
                discount = "10%",
                discountAmount = "up to ₹11",
                address = "Baner, Pune"
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
            foodItems = soupDietItems,
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
    val soupDietItems = listOf(
        // 1-5: CLASSIC & COMFORT SOUPS
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.soup_diet_1,
            title = "Classic Tomato Soup",
            price = "89",
            restaurantName = "Soup Symphony",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹13",
            address = "MG Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.soup_diet_2,
            title = "Cream of Mushroom",
            price = "119",
            restaurantName = "The Soup Spoon",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹12",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.soup_diet_3,
            title = "Sweet Corn Vegetable Soup",
            price = "99",
            restaurantName = "Wok & Roll",
            rating = "4.6",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "12% OFF",
            discountAmount = "up to ₹12",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.soup_diet_4,
            title = "Hot & Sour Soup",
            price = "109",
            restaurantName = "Dragon House",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹16",
            address = "Chinatown, Kolkata"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.soup_diet_5,
            title = "Manchow Soup",
            price = "99",
            restaurantName = "Asian Wok",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹10",
            address = "Andheri East, Mumbai"
        ),

        // 6-10: PROTEIN-RICH SOUPS
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.soup_diet_6,
            title = "Hearty Chicken Noodle",
            price = "149",
            restaurantName = "Brother's Broth",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹18",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.soup_diet_7,
            title = "Lemon Coriander Chicken",
            price = "139",
            restaurantName = "Spice Kitchen",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹21",
            address = "Bandra West, Mumbai"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.soup_diet_8,
            title = "Mutton Bone Broth",
            price = "179",
            restaurantName = "Heritage Taste",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Old City, Hyderabad"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.soup_diet_9,
            title = "Egg Drop Soup",
            price = "89",
            restaurantName = "Quick Bites",
            rating = "4.6",
            deliveryTime = "8-12 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹13",
            address = "Food Street, Pune"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.soup_diet_10,
            title = "Chicken Clear Soup",
            price = "119",
            restaurantName = "Healthy Bowl",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹14",
            address = "Salt Lake, Kolkata"
        ),

        // 11-15: INTERNATIONAL & EXOTIC SOUPS
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.soup_diet_11,
            title = "Thai Coconut Soup (Tom Kha)",
            price = "169",
            restaurantName = "Thai Basil",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.soup_diet_12,
            title = "Tom Yum Soup",
            price = "159",
            restaurantName = "Bangkok Street",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "12% OFF",
            discountAmount = "up to ₹19",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.soup_diet_13,
            title = "French Onion Soup",
            price = "149",
            restaurantName = "Café de Paris",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Colaba, Mumbai"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.soup_diet_14,
            title = "Minestrone Soup",
            price = "129",
            restaurantName = "Italian Garden",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹19",
            address = "Jayanagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.soup_diet_15,
            title = "Broccoli Cheddar Soup",
            price = "139",
            restaurantName = "American Diner",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.2 km",
            discount = "12% OFF",
            discountAmount = "up to ₹17",
            address = "Sector 29, Gurgaon"
        ),

        // 16-18: INDIAN STYLE & LENTIL SOUPS
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.soup_diet_16,
            title = "Masala Lentil Shorba",
            price = "109",
            restaurantName = "Spice Route",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.soup_diet_17,
            title = "Tomato Dhaniya Shorba",
            price = "99",
            restaurantName = "Punjab Grill",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹15",
            address = "CP, Delhi"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.soup_diet_18,
            title = "Palak Corn Soup",
            price = "119",
            restaurantName = "Green Kitchen",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹14",
            address = "Civil Lines, Jaipur"
        ),

        // 19-20: HEALTH & DIET SOUPS
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.soup_diet_19,
            title = "Keto Chicken Soup",
            price = "169",
            restaurantName = "Fit Food Co.",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Koramangala, Bangalore"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.soup_diet_20,
            title = "Detox Green Soup",
            price = "129",
            restaurantName = "Healthy Soul",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "20% OFF",
            discountAmount = "up to ₹26",
            address = "Jubilee Hills, Hyderabad"
        )
    )
     Column {
         soupDietItems.forEach { restaurantItem ->
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
fun BrowniePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val brownieDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY BROWNIE CATEGORIES (WITH ICONS - MAIN FLAVOR PROFILES)
                FilterChip(
                    id = "classic_chocolate",
                    text = "Classic Chocolate",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_classic_chocolate  // Rich chocolate brownie
                ),
                FilterChip(
                    id = "walnut_brownie",
                    text = "Walnut",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_walnut_brownie  // Brownie with walnut pieces
                ),
                FilterChip(
                    id = "blondie",
                    text = "Blondie",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_blondie_brownie  // Vanilla/butterscotch based
                ),
                FilterChip(
                    id = "cheesecake_brownie",
                    text = "Cheesecake Swirl",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cheesecake_brownie  // Brownie with cheesecake marbling
                ),
                FilterChip(
                    id = "gluten_free_brownie",
                    text = "Gluten Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_gluten_free_brownie  // GF symbol with brownie
                ),
                // 3. CHOCOLATE TYPES (TEXT ONLY)
                FilterChip(
                    id = "dark_chocolate",
                    text = "Dark Chocolate (70%+)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "milk_chocolate",
                    text = "Milk Chocolate",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "white_chocolate",
                    text = "White Chocolate",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "triple_chocolate",
                    text = "Triple Chocolate",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free_chocolate",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. ADD-INS & MIX-INS (TEXT ONLY)
                FilterChip(
                    id = "nuts_brownie",
                    text = "With Nuts",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_chips",
                    text = "Extra Chocolate Chips",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caramel_swirl",
                    text = "Caramel Swirl",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "peanut_butter",
                    text = "Peanut Butter Swirl",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "marshmallow",
                    text = "Marshmallow",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookie_dough",
                    text = "Cookie Dough",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fruit_infused",
                    text = "Fruit Infused",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sea_salt",
                    text = "Sea Salt",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. TEXTURE PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "fudgy_brownie",
                    text = "Fudgy (Dense & Moist)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cakey_brownie",
                    text = "Cakey (Light & Fluffy)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chewy_brownie",
                    text = "Chewy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "crusty_top",
                    text = "Crusty Top",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gooey_center",
                    text = "Gooey Center",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. SIZE/FORMAT (TEXT ONLY)
                FilterChip(
                    id = "individual_brownie",
                    text = "Individual (1 pc)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mini_brownie",
                    text = "Mini Bites (4-6 pcs)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "shareable_tray",
                    text = "Shareable Tray (6-9 pcs)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_tray",
                    text = "Family Tray (12+ pcs)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brownie_cake",
                    text = "Brownie Cake (Round)",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. TOPPINGS (TEXT ONLY)
                FilterChip(
                    id = "frosted_brownie",
                    text = "Frosted",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "glazed_brownie",
                    text = "Glazed",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "drizzled_brownie",
                    text = "Chocolate Drizzled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sprinkles_brownie",
                    text = "With Sprinkles",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "powdered_sugar",
                    text = "Dusted with Powdered Sugar",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ice_cream_topping",
                    text = "Served with Ice Cream",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. DIETARY & HEALTH (TEXT ONLY)
                FilterChip(
                    id = "keto_brownie",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb_brownie",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein_brownie",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless_brownie",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dairy_free_brownie",
                    text = "Dairy Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "nut_free_brownie",
                    text = "Nut Free",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. OCCASION (TEXT ONLY)
                FilterChip(
                    id = "birthday_brownie",
                    text = "Birthday Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gift_box",
                    text = "Gift Boxed",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "party_pack",
                    text = "Party Pack",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dessert_platter",
                    text = "Dessert Platter",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "holiday_special",
                    text = "Holiday Special",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_50_brownie",
                    text = "Under ₹50",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "50_100_brownie",
                    text = "₹50 - ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_200_brownie",
                    text = "₹100 - ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "200_350_brownie",
                    text = "₹200 - ₹350",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_350_brownie",
                    text = "Above ₹350",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. BRAND/BAKERY TYPE (TEXT ONLY)
                FilterChip(
                    id = "artisan_brownie",
                    text = "Artisan Bakery",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "homemade_brownie",
                    text = "Homemade Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "premium_brownie",
                    text = "Premium/Gourmet",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "local_bakery",
                    text = "Local Bakery",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. FRESHNESS (TEXT ONLY)
                FilterChip(
                    id = "fresh_baked",
                    text = "Freshly Baked",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "frozen_brownie",
                    text = "Frozen/Heat at Home",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "shelf_stable",
                    text = "Shelf Stable",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. SORT BY DROPDOWN
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
            filterConfig = brownieDietFilters,
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
        val brownieDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_classic_chocolate_brownie_1,  // Rich, fudgy chocolate brownie with cracked top, dusted with powdered sugar
                title = "Classic Chocolate Fudge",
                price = "79",
                restaurantName = "Brownie House",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "0.4 km",
                discount = "10%",
                discountAmount = "up to ₹8",
                address = "Church Street, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_walnut_brownie_1,  // Dark chocolate brownie loaded with crunchy walnut pieces, golden brown crust
                title = "Crunchy Walnut Brownie",
                price = "99",
                restaurantName = "Nutty Baker",
                rating = "4.8",
                deliveryTime = "18-25 mins",
                distance = "0.9 km",
                discount = "15%",
                discountAmount = "up to ₹15",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_blondie_brownie_1,  // Golden vanilla blondie with white chocolate chips and a buttery sheen
                title = "Caramel Blondie",
                price = "89",
                restaurantName = "Blondie's Cafe",
                rating = "4.7",
                deliveryTime = "12-18 mins",
                distance = "0.7 km",
                discount = "10%",
                discountAmount = "up to ₹9",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_cheesecake_brownie_1,  // Marbled brownie with creamy cheesecake swirl, creating beautiful patterns
                title = "Cheesecake Swirl Brownie",
                price = "129",
                restaurantName = "The Dessert Story",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.1 km",
                discount = "12%",
                discountAmount = "up to ₹15",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_salted_caramel_brownie_1,  // Rich chocolate brownie drizzled with salted caramel sauce and sea salt flakes
                title = "Salted Caramel Brownie",
                price = "119",
                restaurantName = "Caramel Heaven",
                rating = "4.9",
                deliveryTime = "15-20 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹18",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_gluten_free_brownie_1,  // Decadent gluten-free brownie with almond flour texture, rich chocolate chunks visible
                title = "Gluten-Free Chocolate Brownie",
                price = "139",
                restaurantName = "Healthy Bites",
                rating = "4.8",
                deliveryTime = "18-25 mins",
                distance = "1.2 km",
                discount = "10%",
                discountAmount = "up to ₹14",
                address = "Baner, Pune"
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
            foodItems = brownieDietItems,
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
    val brownieDietItems = listOf(
        // 1-5: CLASSIC & FUDGY BROWNIES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.brownie_diet_1,
            title = "Classic Chocolate Fudge Brownie",
            price = "79",
            restaurantName = "Brownie House",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹8",
            address = "Church Street, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.brownie_diet_2,
            title = "Triple Chocolate Brownie",
            price = "119",
            restaurantName = "Chocolate Factory",
            rating = "4.8",
            deliveryTime = "18-25 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.brownie_diet_3,
            title = "Dark Chocolate Sea Salt",
            price = "99",
            restaurantName = "Artisan Desserts",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.6 km",
            discount = "12% OFF",
            discountAmount = "up to ₹12",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.brownie_diet_4,
            title = "Belgian Chocolate Brownie",
            price = "139",
            restaurantName = "Belgian Waffle Co.",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.brownie_diet_5,
            title = "Molten Lava Brownie",
            price = "149",
            restaurantName = "Lava Cafe",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Bandra West, Mumbai"
        ),

        // 6-10: NUTTY & CRUNCHY BROWNIES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.brownie_diet_6,
            title = "Crunchy Walnut Brownie",
            price = "99",
            restaurantName = "Nutty Baker",
            rating = "4.8",
            deliveryTime = "18-25 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹15",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.brownie_diet_7,
            title = "Pecan Caramel Brownie",
            price = "129",
            restaurantName = "Caramel Heaven",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹15",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.brownie_diet_8,
            title = "Almond Chocolate Brownie",
            price = "109",
            restaurantName = "Healthy Bites",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.brownie_diet_9,
            title = "Hazelnut Crunch Brownie",
            price = "119",
            restaurantName = "Nutella House",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Jayanagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.brownie_diet_10,
            title = "Macadamia White Chocolate",
            price = "149",
            restaurantName = "Gourmet Treats",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "12% OFF",
            discountAmount = "up to ₹18",
            address = "Colaba, Mumbai"
        ),

        // 11-15: SPECIALTY & SWIRL BROWNIES
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.brownie_diet_11,
            title = "Cheesecake Swirl Brownie",
            price = "129",
            restaurantName = "The Dessert Story",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.1 km",
            discount = "12% OFF",
            discountAmount = "up to ₹15",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.brownie_diet_12,
            title = "Salted Caramel Brownie",
            price = "119",
            restaurantName = "Caramel Heaven",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.brownie_diet_13,
            title = "Peanut Butter Swirl",
            price = "129",
            restaurantName = "Peanut Factory",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹15",
            address = "Andheri East, Mumbai"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.brownie_diet_14,
            title = "Mint Chocolate Brownie",
            price = "109",
            restaurantName = "Mint Leaf Cafe",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹11",
            address = "Salt Lake, Kolkata"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.brownie_diet_15,
            title = "Coffee Walnut Brownie",
            price = "99",
            restaurantName = "Coffee House",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹15",
            address = "MG Road, Bangalore"
        ),

        // 16-18: DIETARY & HEALTH-CONSCIOUS BROWNIES
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.brownie_diet_16,
            title = "Gluten-Free Chocolate Brownie",
            price = "139",
            restaurantName = "Healthy Bites",
            rating = "4.8",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.brownie_diet_17,
            title = "Vegan Walnut Brownie",
            price = "129",
            restaurantName = "Green Soul",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹15",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.brownie_diet_18,
            title = "Keto Almond Brownie",
            price = "159",
            restaurantName = "Keto Kitchen",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹24",
            address = "Koramangala, Bangalore"
        ),

        // 19-20: SEASONAL & PREMIUM BROWNIES
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.brownie_diet_19,
            title = "Red Velvet Brownie",
            price = "119",
            restaurantName = "Velvet Bakery",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹14",
            address = "Sector 29, Gurgaon"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.brownie_diet_20,
            title = "Tiramisu Brownie",
            price = "149",
            restaurantName = "Italian Delights",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹22",
            address = "Colaba, Mumbai"
        )
    )
    Column {
        brownieDietItems.forEach { restaurantItem ->
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
fun WafflesPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val waffleDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY WAFFLE CATEGORIES (WITH ICONS - MAIN FLAVOR PROFILES)
                FilterChip(
                    id = "classic_waffle",
                    text = "Classic Waffle",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_classic_waffle  // Traditional belgian waffle
                ),
                FilterChip(
                    id = "liege_waffle",
                    text = "Liège Waffle",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_liege_waffle_diet  // Caramelized pearl sugar waffle
                ),
                FilterChip(
                    id = "chocolate_waffle",
                    text = "Chocolate Waffle",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chocolate_waffle  // Chocolate batter waffle
                ),
                FilterChip(
                    id = "red_velvet_waffle",
                    text = "Red Velvet",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_red_velvet_waffle  // Red velvet flavored
                ),
                FilterChip(
                    id = "gluten_free_waffle",
                    text = "Gluten Free",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_gluten_free_waffle  // GF symbol with waffle
                ),

                // 3. BATTER BASES (TEXT ONLY)
                FilterChip(
                    id = "buttermilk_waffle",
                    text = "Buttermilk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "whole_wheat_waffle",
                    text = "Whole Wheat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "oatmeal_waffle",
                    text = "Oatmeal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "protein_waffle",
                    text = "Protein Enriched",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pumpkin_waffle",
                    text = "Pumpkin Spice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "banana_waffle",
                    text = "Banana",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. TOPPINGS & SPREADS (TEXT ONLY)
                FilterChip(
                    id = "nutella_waffle",
                    text = "Nutella Spread",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "peanut_butter_waffle",
                    text = "Peanut Butter",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "maple_syrup",
                    text = "Maple Syrup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_syrup",
                    text = "Chocolate Syrup",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caramel_sauce",
                    text = "Caramel Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "berry_compote",
                    text = "Berry Compote",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "whipped_cream",
                    text = "Whipped Cream",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ice_cream_waffle",
                    text = "Ice Cream Scoop",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. FRUIT ADD-ONS (TEXT ONLY)
                FilterChip(
                    id = "fresh_berries",
                    text = "Fresh Berries",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "banana_slices",
                    text = "Banana Slices",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "apple_cinnamon",
                    text = "Apple Cinnamon",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grilled_peach",
                    text = "Grilled Peaches",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. SAVORY WAFFLES (TEXT ONLY)
                FilterChip(
                    id = "chicken_waffle",
                    text = "Chicken & Waffle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bacon_waffle",
                    text = "Bacon Waffle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "egg_cheese_waffle",
                    text = "Egg & Cheese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cheesy_waffle",
                    text = "Cheese Waffle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "herb_waffle",
                    text = "Herbs & Spices",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. TEXTURE & STYLE (TEXT ONLY)
                FilterChip(
                    id = "crispy_waffle",
                    text = "Extra Crispy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fluffy_waffle",
                    text = "Light & Fluffy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thick_waffle",
                    text = "Thick Cut",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thin_waffle",
                    text = "Thin & Crisp",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "stuffed_waffle",
                    text = "Stuffed (Fillings)",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. DIETARY & HEALTH (TEXT ONLY)
                FilterChip(
                    id = "vegan_waffle",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_waffle",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_carb_waffle",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sugar_free_waffle",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dairy_free_waffle",
                    text = "Dairy Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "eggless_waffle",
                    text = "Eggless",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. SIZE/FORMAT (TEXT ONLY)
                FilterChip(
                    id = "single_waffle",
                    text = "Single Waffle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "double_waffle",
                    text = "Double Stack",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mini_waffle_bites",
                    text = "Mini Waffle Bites (4-6 pcs)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "waffle_sandwich",
                    text = "Waffle Sandwich",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "waffle_fries",
                    text = "Waffle Fries/Slices",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. OCCASION (TEXT ONLY)
                FilterChip(
                    id = "breakfast_waffle",
                    text = "Breakfast Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brunch_waffle",
                    text = "Brunch Favorite",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dessert_waffle",
                    text = "Dessert Waffle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "holiday_waffle",
                    text = "Holiday Special",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_100_waffle",
                    text = "Under ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_200_waffle",
                    text = "₹100 - ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "200_350_waffle",
                    text = "₹200 - ₹350",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "350_500_waffle",
                    text = "₹350 - ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_500_waffle",
                    text = "Above ₹500",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. ESTABLISHMENT TYPE (TEXT ONLY)
                FilterChip(
                    id = "waffle_specialist",
                    text = "Waffle Specialist",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cafe_waffle",
                    text = "Café",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dessert_parlor",
                    text = "Dessert Parlor",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "food_truck_waffle",
                    text = "Food Truck",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. FRESHNESS (TEXT ONLY)
                FilterChip(
                    id = "fresh_made",
                    text = "Made to Order",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "premade_waffle",
                    text = "Ready to Eat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "frozen_waffle",
                    text = "Frozen/Heat at Home",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SORT BY DROPDOWN
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
            filterConfig = waffleDietFilters,
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
        val waffleDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_classic_waffle_1,  // Golden brown Belgian waffle with perfect grid pattern, dusted with powdered sugar, served with maple syrup drizzle
                title = "Classic Belgian Waffle",
                price = "149",
                restaurantName = "Waffle House",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.5 km",
                discount = "10%",
                discountAmount = "up to ₹15",
                address = "Brigade Road, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_liege_waffle_1,  // Caramelized Liège waffle with pearl sugar chunks, irregular golden-brown crust, slightly glossy appearance
                title = "Authentic Liège Waffle",
                price = "179",
                restaurantName = "Belgian Waffle Co.",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.7 km",
                discount = "15%",
                discountAmount = "up to ₹27",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_chocolate_waffle_1,  // Rich chocolate waffle with chocolate chips, drizzled with melted chocolate, topped with chocolate shavings
                title = "Double Chocolate Waffle",
                price = "199",
                restaurantName = "Chocolate Factory",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "0.9 km",
                discount = "12%",
                discountAmount = "up to ₹24",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_red_velvet_waffle_1,  // Vibrant red velvet waffle with cream cheese glaze drizzle, white chocolate chips, elegant presentation
                title = "Red Velvet Cream Cheese Waffle",
                price = "219",
                restaurantName = "The Dessert Story",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "10%",
                discountAmount = "up to ₹22",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_strawberry_waffle_1,  // Crispy waffle topped with fresh strawberry slices, strawberry sauce drizzle, whipped cream dollop, mint garnish
                title = "Strawberry Delight Waffle",
                price = "189",
                restaurantName = "Berry Bliss",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.6 km",
                discount = "15%",
                discountAmount = "up to ₹28",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_savory_waffle_1,  // Golden savory waffle topped with fried chicken strip, honey butter drizzle, sprinkling of herbs
                title = "Chicken & Waffle",
                price = "249",
                restaurantName = "Savory Bites",
                rating = "4.8",
                deliveryTime = "18-25 mins",
                distance = "1.1 km",
                discount = "10%",
                discountAmount = "up to ₹25",
                address = "Baner, Pune"
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
            foodItems = waffleDietItems,
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
    val waffleDietItems = listOf(
        // 1-5: CLASSIC & BELGIAN WAFFLES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.waffle_diet_1,
            title = "Classic Belgian Waffle",
            price = "149",
            restaurantName = "Waffle House",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.5 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Brigade Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.waffle_diet_2,
            title = "Authentic Liège Waffle",
            price = "179",
            restaurantName = "Belgian Waffle Co.",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹27",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.waffle_diet_3,
            title = "Brussels Waffle",
            price = "159",
            restaurantName = "European Delights",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹19",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.waffle_diet_4,
            title = "Buttermilk Waffle",
            price = "139",
            restaurantName = "Morning Cafe",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.4 km",
            discount = "10% OFF",
            discountAmount = "up to ₹14",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.waffle_diet_5,
            title = "Yeasted Waffle",
            price = "169",
            restaurantName = "Artisan Bakery",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Bandra West, Mumbai"
        ),

        // 6-10: CHOCOLATE & SWEET WAFFLES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.waffle_diet_6,
            title = "Double Chocolate Waffle",
            price = "199",
            restaurantName = "Chocolate Factory",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.waffle_diet_7,
            title = "Nutella Banana Waffle",
            price = "209",
            restaurantName = "Nutella House",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹31",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.waffle_diet_8,
            title = "Salted Caramel Waffle",
            price = "189",
            restaurantName = "Caramel Heaven",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹19",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.waffle_diet_9,
            title = "White Chocolate Berry",
            price = "199",
            restaurantName = "Berry Bliss",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Jayanagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.waffle_diet_10,
            title = "Red Velvet Waffle",
            price = "219",
            restaurantName = "Velvet Bakery",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹33",
            address = "Colaba, Mumbai"
        ),

        // 11-15: FRUIT-TOPPED WAFFLES
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.waffle_diet_11,
            title = "Mixed Berry Waffle",
            price = "189",
            restaurantName = "Berry Waffles",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "1.1 km",
            discount = "12% OFF",
            discountAmount = "up to ₹23",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.waffle_diet_12,
            title = "Strawberry Delight",
            price = "179",
            restaurantName = "Strawberry Fields",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.waffle_diet_13,
            title = "Banana Caramel Waffle",
            price = "169",
            restaurantName = "Caramel Heaven",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.9 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Andheri East, Mumbai"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.waffle_diet_14,
            title = "Apple Cinnamon Waffle",
            price = "159",
            restaurantName = "Cinnamon Cafe",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.6 km",
            discount = "12% OFF",
            discountAmount = "up to ₹19",
            address = "Salt Lake, Kolkata"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.waffle_diet_15,
            title = "Peach Melba Waffle",
            price = "199",
            restaurantName = "Fruit Paradise",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹30",
            address = "MG Road, Bangalore"
        ),

        // 16-20: SAVORY & SPECIALTY WAFFLES
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.waffle_diet_16,
            title = "Chicken & Waffle",
            price = "249",
            restaurantName = "Savory Bites",
            rating = "4.9",
            deliveryTime = "18-25 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹25",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.waffle_diet_17,
            title = "Bacon Maple Waffle",
            price = "229",
            restaurantName = "Breakfast Club",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹27",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.waffle_diet_18,
            title = "Cheese & Herb Waffle",
            price = "179",
            restaurantName = "Cheesy Bites",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Koramangala, Bangalore"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.waffle_diet_19,
            title = "Mediterranean Waffle",
            price = "209",
            restaurantName = "Olive Kitchen",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹31",
            address = "Sector 29, Gurgaon"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.waffle_diet_20,
            title = "Waffle Ice Cream Sundae",
            price = "239",
            restaurantName = "Dessert Parlor",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.7 km",
            discount = "12% OFF",
            discountAmount = "up to ₹29",
            address = "Colaba, Mumbai"
        )
    )
    Column {
        waffleDietItems.forEach { restaurantItem ->
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
fun ColdCoffeePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val coldCoffeeDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY COLD COFFEE CATEGORIES (WITH ICONS - MAIN BEVERAGE TYPES)
                FilterChip(
                    id = "classic_iced_coffee",
                    text = "Classic Iced Coffee",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_classic_iced_coffee  // Simple iced coffee with ice cubes
                ),
                FilterChip(
                    id = "cold_brew",
                    text = "Cold Brew",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cold_brew_coffee  // Smooth cold brew in glass
                ),
                FilterChip(
                    id = "frappe",
                    text = "Frappé",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_frappe_coffee  // Blended frozen coffee drink
                ),
                FilterChip(
                    id = "nitro_cold_brew",
                    text = "Nitro Cold Brew",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_nitro_cold_brew  // Cascading nitro pour
                ),
                FilterChip(
                    id = "vietnamese_iced",
                    text = "Vietnamese Style",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_vietnamese_coffee  // Phin filter with condensed milk
                ),

                // 3. COFFEE BASES (TEXT ONLY)
                FilterChip(
                    id = "espresso_based",
                    text = "Espresso Based",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "drip_coffee",
                    text = "Drip Coffee",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "french_press",
                    text = "French Press",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "instant_coffee",
                    text = "Instant Coffee",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "decaf_base",
                    text = "Decaf",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. MILK TYPES & ALTERNATIVES (TEXT ONLY)
                FilterChip(
                    id = "dairy_milk",
                    text = "Whole Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "skim_milk",
                    text = "Skim Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "oat_milk",
                    text = "Oat Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "almond_milk",
                    text = "Almond Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "soy_milk",
                    text = "Soy Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coconut_milk",
                    text = "Coconut Milk",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "condensed_milk",
                    text = "Sweetened Condensed",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. FLAVOR SYRUPS (TEXT ONLY)
                FilterChip(
                    id = "vanilla_syrup",
                    text = "Vanilla",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caramel_syrup",
                    text = "Caramel",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hazelnut_syrup",
                    text = "Hazelnut",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_syrup",
                    text = "Chocolate/Mocha",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cinnamon_syrup",
                    text = "Cinnamon",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "peppermint_syrup",
                    text = "Peppermint",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coconut_syrup",
                    text = "Coconut",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brown_sugar",
                    text = "Brown Sugar",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. TOPPINGS & ADD-ONS (TEXT ONLY)
                FilterChip(
                    id = "whipped_cream",
                    text = "Whipped Cream",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_drizzle",
                    text = "Chocolate Drizzle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caramel_drizzle",
                    text = "Caramel Drizzle",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cocoa_powder",
                    text = "Dusted Cocoa",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cinnamon_dust",
                    text = "Cinnamon Dust",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chocolate_shavings",
                    text = "Chocolate Shavings",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cookie_crumbs",
                    text = "Cookie Crumbs",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. ICE & TEMPERATURE (TEXT ONLY)
                FilterChip(
                    id = "regular_ice",
                    text = "Regular Ice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "crushed_ice",
                    text = "Crushed Ice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coffee_ice_cubes",
                    text = "Coffee Ice Cubes",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "light_ice",
                    text = "Light Ice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_ice",
                    text = "No Ice (Room Temp)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "blended_frozen",
                    text = "Blended/Frozen",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. SWEETNESS LEVEL (TEXT ONLY)
                FilterChip(
                    id = "unsweetened",
                    text = "Unsweetened",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lightly_sweetened",
                    text = "Lightly Sweetened",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_sweet",
                    text = "Regular Sweet",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "extra_sweet",
                    text = "Extra Sweet",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. DIETARY & HEALTH (TEXT ONLY)
                FilterChip(
                    id = "sugar_free",
                    text = "Sugar Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_calorie",
                    text = "Low Calorie",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_friendly",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan_coffee",
                    text = "Vegan (Plant Milk)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lactose_free",
                    text = "Lactose Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. SIZE/FORMAT (TEXT ONLY)
                FilterChip(
                    id = "small_coffee",
                    text = "Small (200ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "medium_coffee",
                    text = "Medium (300ml)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_coffee",
                    text = "Large (400ml+)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bottle_coffee",
                    text = "Bottled/Canned",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_pack",
                    text = "Family Pack (1L+)",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. OCCASION/TIME (TEXT ONLY)
                FilterChip(
                    id = "morning_brew",
                    text = "Morning Boost",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "afternoon_pickup",
                    text = "Afternoon Pick-me-up",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "evening_treat",
                    text = "Evening Treat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dessert_coffee",
                    text = "Dessert Coffee",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_50_coffee",
                    text = "Under ₹50",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "50_100_coffee",
                    text = "₹50 - ₹100",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "100_150_coffee",
                    text = "₹100 - ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "150_250_coffee",
                    text = "₹150 - ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_250_coffee",
                    text = "Above ₹250",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. CAFE TYPE (TEXT ONLY)
                FilterChip(
                    id = "coffee_chain",
                    text = "Coffee Chain",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "specialty_roaster",
                    text = "Specialty Roaster",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "local_cafe",
                    text = "Local Café",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "drive_thru",
                    text = "Drive-Thru",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. CAFFEINE LEVEL (TEXT ONLY)
                FilterChip(
                    id = "regular_caffeine",
                    text = "Regular Caffeine",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "extra_shot",
                    text = "Extra Shot",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "half_caff",
                    text = "Half Caff",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "decaf",
                    text = "Decaf",
                    type = FilterType.TEXT_ONLY
                ),

                // 15. SORT BY DROPDOWN
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
            filterConfig = coldCoffeeDietFilters,
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
        val coldCoffeeDietItems = listOf(
            // 1-6: COLD COFFEE VARIETIES
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_classic_iced_coffee_1,  // Tall glass of classic iced coffee with ice cubes, light brown color, topped with a splash of milk, served with a striped straw
                title = "Classic Iced Coffee",
                price = "99",
                restaurantName = "Brew House",
                rating = "4.7",
                deliveryTime = "10-15 mins",
                distance = "0.4 km",
                discount = "10%",
                discountAmount = "up to ₹10",
                address = "Church Street, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_cold_brew_1,  // Dark, smooth cold brew served in a glass with large ice cube, garnished with coffee bean, rich dark brown color
                title = "Smooth Cold Brew",
                price = "129",
                restaurantName = "Cold Brew Co.",
                rating = "4.9",
                deliveryTime = "12-18 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹19",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_frappe_1,  // Blended frozen coffee frappé with whipped cream topping, chocolate drizzle, caramel sauce streaks on glass
                title = "Caramel Frappé",
                price = "159",
                restaurantName = "Frosty Brew",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.7 km",
                discount = "12%",
                discountAmount = "up to ₹19",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_vietnamese_coffee_1,  // Vietnamese iced coffee with traditional phin filter on top, condensed milk layer at bottom, stirred caramel color
                title = "Vietnamese Iced Coffee",
                price = "139",
                restaurantName = "Saigon Cafe",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "1.0 km",
                discount = "10%",
                discountAmount = "up to ₹14",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_mocha_ice_1,  // Iced mocha with chocolate sauce swirls on glass, whipped cream topping, chocolate shavings, dark and light layers
                title = "Chilled Mocha",
                price = "149",
                restaurantName = "Chocolate Cafe",
                rating = "4.7",
                deliveryTime = "12-18 mins",
                distance = "0.6 km",
                discount = "15%",
                discountAmount = "up to ₹22",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_nitro_cold_brew_1,  // Nitro cold brew cascading in glass with creamy foam head, served from tap, dark stout-like appearance
                title = "Nitro Cold Brew",
                price = "179",
                restaurantName = "Nitro Lab",
                rating = "4.9",
                deliveryTime = "10-15 mins",
                distance = "0.9 km",
                discount = "10%",
                discountAmount = "up to ₹18",
                address = "Baner, Pune"
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
            foodItems = coldCoffeeDietItems,
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
    val coldCoffeeDietItems = listOf(
        // 1-5: CLASSIC COLD COFFEE VARIETIES
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.cold_coffee_diet_1,
            title = "Classic Iced Coffee",
            price = "119",
            restaurantName = "Coffee House",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹18",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.cold_coffee_diet_2,
            title = "Cold Brew Black",
            price = "149",
            restaurantName = "Brew Bros",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹15",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.cold_coffee_diet_3,
            title = "Vanilla Iced Latte",
            price = "159",
            restaurantName = "Latte Lounge",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "12% OFF",
            discountAmount = "up to ₹19",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.cold_coffee_diet_4,
            title = "Caramel Iced Coffee",
            price = "169",
            restaurantName = "Caramel Cafe",
            rating = "4.9",
            deliveryTime = "12-18 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹25",
            address = "Brigade Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.cold_coffee_diet_5,
            title = "Hazelnut Cold Brew",
            price = "179",
            restaurantName = "Nutty Brew",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Bandra West, Mumbai"
        ),

        // 6-10: SPECIALTY COLD COFFEE
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.cold_coffee_diet_6,
            title = "Nitro Cold Brew",
            price = "199",
            restaurantName = "Nitro Lab",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.cold_coffee_diet_7,
            title = "Vietnamese Iced Coffee",
            price = "189",
            restaurantName = "Saigon Cafe",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Koramangala, Bangalore"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.cold_coffee_diet_8,
            title = "Thai Iced Coffee",
            price = "179",
            restaurantName = "Bangkok Street",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Andheri East, Mumbai"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.cold_coffee_diet_9,
            title = "Irish Cold Coffee",
            price = "209",
            restaurantName = "Pub & Brew",
            rating = "4.8",
            deliveryTime = "18-25 mins",
            distance = "1.3 km",
            discount = "12% OFF",
            discountAmount = "up to ₹25",
            address = "Sector 29, Gurgaon"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.cold_coffee_diet_10,
            title = "Mexican Spiced Cold Brew",
            price = "189",
            restaurantName = "Spice Route",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Jayanagar, Bangalore"
        ),

        // 11-15: FROZEN & BLENDED COFFEE
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.cold_coffee_diet_11,
            title = "Frappé Coffee",
            price = "169",
            restaurantName = "Frost Cafe",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹17",
            address = "Salt Lake, Kolkata"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.cold_coffee_diet_12,
            title = "Mocha Frappuccino",
            price = "199",
            restaurantName = "Chocolate House",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.7 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Colaba, Mumbai"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.cold_coffee_diet_13,
            title = "Caramel Frappé",
            price = "189",
            restaurantName = "Caramel Heaven",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.cold_coffee_diet_14,
            title = "Java Chip Frappé",
            price = "209",
            restaurantName = "Coffee Club",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹21",
            address = "MG Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.cold_coffee_diet_15,
            title = "Cookies & Cream Frappé",
            price = "199",
            restaurantName = "Dessert Cafe",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Indiranagar, Bangalore"
        ),

        // 16-20: MILK-BASED & DESSERT COFFEE
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.cold_coffee_diet_16,
            title = "Iced Latte (Oat Milk)",
            price = "189",
            restaurantName = "Plant Brew",
            rating = "4.7",
            deliveryTime = "10-15 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹28",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.cold_coffee_diet_17,
            title = "Iced Almond Milk Latte",
            price = "179",
            restaurantName = "Nut Milk Cafe",
            rating = "4.8",
            deliveryTime = "10-15 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹18",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.cold_coffee_diet_18,
            title = "Coconut Cold Brew",
            price = "199",
            restaurantName = "Tropical Brews",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹24",
            address = "Bandra West, Mumbai"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.cold_coffee_diet_19,
            title = "Cold Coffee with Ice Cream",
            price = "229",
            restaurantName = "Scoops & Brews",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹34",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.cold_coffee_diet_20,
            title = "Protein Cold Coffee",
            price = "209",
            restaurantName = "Fit Fuel",
            rating = "4.7",
            deliveryTime = "12-18 mins",
            distance = "1.1 km",
            discount = "10% OFF",
            discountAmount = "up to ₹21",
            address = "Koramangala, Bangalore"
        )
    )
    Column {
        coldCoffeeDietItems.forEach { restaurantItem ->
            RestaurantItemListFull(
                restaurantItem = restaurantItem,
                onWishlistClick = { },
                onThreeDotClick = { },
                onItemClick = { }
            )
        }
    }
}

// Additional diet-specific item pages
@Composable
fun GrilledChickenDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val grilledChickenDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY GRILLED CHICKEN CATEGORIES (WITH ICONS - MAIN PREPARATION STYLES)
                FilterChip(
                    id = "grilled_breast",
                    text = "Grilled Breast",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_grilled_chicken_breast  // Juicy grilled chicken breast with grill marks
                ),
                FilterChip(
                    id = "grilled_thighs",
                    text = "Grilled Thighs",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_grilled_chicken_thigh  // Tender chicken thigh piece
                ),
                FilterChip(
                    id = "chicken_tenders",
                    text = "Chicken Tenders",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_tenders  // Crispy grilled chicken strips
                ),
                FilterChip(
                    id = "chicken_skewers",
                    text = "Chicken Skewers",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_chicken_skewers  // Chicken pieces on skewer with grill marks
                ),
                FilterChip(
                    id = "spatchcock_chicken",
                    text = "Spatchcock",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_spatchcock_chicken  // Butterflied whole chicken
                ),
                // 3. MARINADES & SEASONINGS (TEXT ONLY)
                FilterChip(
                    id = "lemon_pepper",
                    text = "Lemon Pepper",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "garlic_herb",
                    text = "Garlic Herb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cajun_spice",
                    text = "Cajun Spice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "teriyaki_glaze",
                    text = "Teriyaki Glaze",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bbq_rub",
                    text = "BBQ Rub",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "peri_peri",
                    text = "Peri Peri",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mediterranean",
                    text = "Mediterranean",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tandoori_masala",
                    text = "Tandoori Masala",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "buffalo_sauce",
                    text = "Buffalo Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "honey_mustard",
                    text = "Honey Mustard",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. SIDE DISHES (TEXT ONLY)
                FilterChip(
                    id = "steamed_veggies",
                    text = "Steamed Veggies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grilled_veggies",
                    text = "Grilled Veggies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "garden_salad",
                    text = "Garden Salad",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "caesar_salad",
                    text = "Caesar Salad",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brown_rice",
                    text = "Brown Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quinoa",
                    text = "Quinoa",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sweet_potato",
                    text = "Sweet Potato",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mashed_potato",
                    text = "Mashed Potato",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. SAUCES & DRESSINGS (TEXT ONLY)
                FilterChip(
                    id = "ranch_dressing",
                    text = "Ranch",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "blue_cheese",
                    text = "Blue Cheese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "honey_mustard_sauce",
                    text = "Honey Mustard",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bbq_sauce",
                    text = "BBQ Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "buffalo_dip",
                    text = "Buffalo Dip",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tzatziki",
                    text = "Tzatziki",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "garlic_aioli",
                    text = "Garlic Aioli",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DIETARY & HEALTH (TEXT ONLY)
                FilterChip(
                    id = "low_carb",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein_diet",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_diet",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "paleo_diet",
                    text = "Paleo",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dairy_free",
                    text = "Dairy Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "no_antibiotics",
                    text = "No Antibiotics",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "free_range",
                    text = "Free Range",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. SPICE LEVEL (TEXT ONLY)
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
                    id = "spicy_chicken",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "extra_spicy",
                    text = "Extra Spicy",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. COOKING STYLE (TEXT ONLY)
                FilterChip(
                    id = "char_grilled",
                    text = "Char-Grilled",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "wood_fired",
                    text = "Wood Fired",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pan_seared",
                    text = "Pan Seared",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "open_flame",
                    text = "Open Flame",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "under_150",
                    text = "Under ₹150",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "150_250",
                    text = "₹150 - ₹250",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "250_350",
                    text = "₹250 - ₹350",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "350_500",
                    text = "₹350 - ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "above_500",
                    text = "Above ₹500",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. MEAL TYPE (TEXT ONLY)
                FilterChip(
                    id = "lunch_special",
                    text = "Lunch Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dinner_meal",
                    text = "Dinner",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "post_workout",
                    text = "Post-Workout",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "meal_prep",
                    text = "Meal Prep",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_meal",
                    text = "Family Meal",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. PORTION SIZE (TEXT ONLY)
                FilterChip(
                    id = "single_serving",
                    text = "Single (150-200g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "double_serving",
                    text = "Double (300-400g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_pack_chicken",
                    text = "Family Pack (500g+)",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. RESTAURANT TYPE (TEXT ONLY)
                FilterChip(
                    id = "fast_casual",
                    text = "Fast Casual",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy_eatery",
                    text = "Healthy Eatery",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sports_bar",
                    text = "Sports Bar",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fine_dining",
                    text = "Fine Dining",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. ACCOMPANIMENTS (TEXT ONLY)
                FilterChip(
                    id = "with_bone",
                    text = "With Bone",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "boneless",
                    text = "Boneless",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "skin_on",
                    text = "Skin On",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "skinless",
                    text = "Skinless",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SORT BY DROPDOWN
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
            filterConfig = grilledChickenDietFilters,
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
        val grilledChickenDietItems = listOf(
            // 1-6: GRILLED CHICKEN VARIETIES
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_grilled_chicken_breast_1,  // Juicy grilled chicken breast with perfect grill marks, seasoned with herbs and black pepper, served with a lemon wedge on the side, steam rising from the meat
                title = "Herb Grilled Chicken Breast",
                price = "199",
                restaurantName = "Grill Master",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.5 km",
                discount = "15%",
                discountAmount = "up to ₹30",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_grilled_chicken_thighs_1,  // Tender grilled chicken thighs with caramelized edges, glazed with BBQ sauce, garnished with fresh parsley, slightly charred for smoky flavor
                title = "BBQ Glazed Chicken Thighs",
                price = "219",
                restaurantName = "Smoky Grill",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "0.9 km",
                discount = "10%",
                discountAmount = "up to ₹22",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_chicken_skewers_1,  // Colorful chicken skewers with bell peppers, red onions, and cherry tomatoes, perfectly charred on the grill, served on a wooden platter with mint chutney
                title = "Mediterranean Chicken Skewers",
                price = "189",
                restaurantName = "Kebab Studio",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.7 km",
                discount = "12%",
                discountAmount = "up to ₹23",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_grilled_chicken_wings_1,  // Crispy grilled chicken wings with light char marks, tossed in peri-peri sauce, served with celery sticks and ranch dip on the side
                title = "Peri-Peri Grilled Wings",
                price = "179",
                restaurantName = "Wingstop",
                rating = "4.8",
                deliveryTime = "12-18 mins",
                distance = "0.6 km",
                discount = "10%",
                discountAmount = "up to ₹18",
                address = "Church Street, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_lemon_herb_chicken_1,  // Lemon herb grilled chicken with rosemary sprigs, garlic cloves, and lemon slices, golden brown crispy skin, served on a cast iron skillet
                title = "Lemon Rosemary Grilled Chicken",
                price = "229",
                restaurantName = "Heritage Grill",
                rating = "4.9",
                deliveryTime = "18-25 mins",
                distance = "1.1 km",
                discount = "15%",
                discountAmount = "up to ₹34",
                address = "Connaught Place, Delhi"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_tandoori_chicken_1,  // Traditional tandoori chicken with vibrant red color, charred edges, served with onion rings, mint chutney, and lemon wedge on a sizzling platter
                title = "Tandoori Grilled Chicken",
                price = "249",
                restaurantName = "Punjabi Grill",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.2 km",
                discount = "10%",
                discountAmount = "up to ₹25",
                address = "Koramangala, Bangalore"
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
            foodItems = grilledChickenDietItems,
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
    val grilledChickenDietItems = listOf(
        // 1-5: CLASSIC GRILLED CHICKEN
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.grilled_chicken_diet_1,
            title = "Classic Grilled Chicken Breast",
            price = "249",
            restaurantName = "Protein House",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹37",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.grilled_chicken_diet_2,
            title = "Lemon Herb Grilled Chicken",
            price = "269",
            restaurantName = "Healthy Bites",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹27",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.grilled_chicken_diet_3,
            title = "Peri Peri Grilled Chicken",
            price = "279",
            restaurantName = "Spice Grill",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "0.7 km",
            discount = "12% OFF",
            discountAmount = "up to ₹33",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.grilled_chicken_diet_4,
            title = "Garlic Rosemary Chicken",
            price = "259",
            restaurantName = "Herb Kitchen",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "0.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹39",
            address = "Brigade Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.grilled_chicken_diet_5,
            title = "Honey Mustard Grilled Chicken",
            price = "289",
            restaurantName = "Sweet & Savory",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹29",
            address = "Bandra West, Mumbai"
        ),

        // 6-10: GRILLED CHICKEN WITH VEGETABLES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.grilled_chicken_diet_6,
            title = "Grilled Chicken with Steamed Veggies",
            price = "299",
            restaurantName = "Fresh & Fit",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹36",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.grilled_chicken_diet_7,
            title = "Grilled Chicken Salad Bowl",
            price = "279",
            restaurantName = "Salad Story",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹42",
            address = "Koramangala, Bangalore"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.grilled_chicken_diet_8,
            title = "Mediterranean Grilled Chicken",
            price = "309",
            restaurantName = "Mediterraneo",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹31",
            address = "Andheri East, Mumbai"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.grilled_chicken_diet_9,
            title = "Grilled Chicken with Quinoa",
            price = "319",
            restaurantName = "Superfood Cafe",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹38",
            address = "Sector 29, Gurgaon"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.grilled_chicken_diet_10,
            title = "Grilled Chicken with Brown Rice",
            price = "289",
            restaurantName = "Wholesome Kitchen",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Jayanagar, Bangalore"
        ),

        // 11-15: INTERNATIONAL GRILLED CHICKEN STYLES
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.grilled_chicken_diet_11,
            title = "Tandoori Grilled Chicken",
            price = "269",
            restaurantName = "Tandoori Nights",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹27",
            address = "Salt Lake, Kolkata"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.grilled_chicken_diet_12,
            title = "Thai Basil Grilled Chicken",
            price = "299",
            restaurantName = "Thai Spice",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "1.1 km",
            discount = "12% OFF",
            discountAmount = "up to ₹36",
            address = "Colaba, Mumbai"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.grilled_chicken_diet_13,
            title = "Moroccan Spiced Chicken",
            price = "309",
            restaurantName = "Marrakesh Cafe",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "1.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹46",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.grilled_chicken_diet_14,
            title = "Teriyaki Grilled Chicken",
            price = "289",
            restaurantName = "Tokyo Grill",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹29",
            address = "MG Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.grilled_chicken_diet_15,
            title = "Cajun Spiced Grilled Chicken",
            price = "279",
            restaurantName = "Louisiana Kitchen",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹33",
            address = "Indiranagar, Bangalore"
        ),

        // 16-20: DIET-SPECIAL GRILLED CHICKEN
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.grilled_chicken_diet_16,
            title = "Keto Grilled Chicken Bowl",
            price = "329",
            restaurantName = "Keto Kitchen",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹49",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.grilled_chicken_diet_17,
            title = "Paleo Grilled Chicken",
            price = "319",
            restaurantName = "Paleo Plate",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.grilled_chicken_diet_18,
            title = "High Protein Grilled Chicken",
            price = "299",
            restaurantName = "Protein Lab",
            rating = "4.9",
            deliveryTime = "18-22 mins",
            distance = "0.5 km",
            discount = "12% OFF",
            discountAmount = "up to ₹36",
            address = "Bandra West, Mumbai"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.grilled_chicken_diet_19,
            title = "Low Carb Grilled Chicken",
            price = "289",
            restaurantName = "Low Carb Life",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.grilled_chicken_diet_20,
            title = "Grilled Chicken with Avocado",
            price = "349",
            restaurantName = "Avocado Factory",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹35",
            address = "Koramangala, Bangalore"
        )
    )
    Column {
        grilledChickenDietItems.forEach { restaurantItem ->
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
fun SteamedFishDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val steamedFishDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY FISH TYPES (WITH ICONS - MAIN CATEGORIES ONLY)
                FilterChip(
                    id = "sea_bass",
                    text = "Sea Bass",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_sea_bass  // Whole sea bass fish illustration
                ),
                FilterChip(
                    id = "salmon_fish",
                    text = "Salmon",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_salmon  // Salmon fish with characteristic pink color
                ),
                FilterChip(
                    id = "tilapia",
                    text = "Tilapia",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_tilapia  // Tilapia fish shape
                ),
                FilterChip(
                    id = "pomfret",
                    text = "Pomfret",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_pomfret  // Silver pomfret fish
                ),
                FilterChip(
                    id = "cod_fish",
                    text = "Cod",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_cod  // Cod fish illustration
                ),
                // 3. FISH CUTS & PREPARATION (TEXT ONLY)
                FilterChip(
                    id = "whole_fish",
                    text = "Whole Fish",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_fillet",
                    text = "Fillet",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_steak",
                    text = "Steak Cut",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "butterflied",
                    text = "Butterflied",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. STEAMING STYLES (TEXT ONLY)
                FilterChip(
                    id = "cantonese_style",
                    text = "Cantonese Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thai_style",
                    text = "Thai Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "malay_style",
                    text = "Malay Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "indian_style",
                    text = "Indian Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "banana_leaf",
                    text = "Banana Leaf Wrapped",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "parchment_baked",
                    text = "En Papillote",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. MARINADES & SEASONINGS (TEXT ONLY)
                FilterChip(
                    id = "ginger_spring_onion",
                    text = "Ginger & Spring Onion",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lemon_dill",
                    text = "Lemon Dill",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "garlic_butter",
                    text = "Garlic Butter",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "soy_ginger",
                    text = "Soy Ginger",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chili_lime",
                    text = "Chili Lime",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "miso_glaze",
                    text = "Miso Glaze",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "herb_crust",
                    text = "Herb Crust",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tamarind_glaze",
                    text = "Tamarind Glaze",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "coconut_cream",
                    text = "Coconut Cream",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. ACCOMPANIMENTS (TEXT ONLY)
                FilterChip(
                    id = "steamed_veggies_fish",
                    text = "Steamed Veggies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bok_choy",
                    text = "Bok Choy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "asian_greens",
                    text = "Asian Greens",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "steamed_rice",
                    text = "Steamed Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "brown_rice_fish",
                    text = "Brown Rice",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quinoa_fish",
                    text = "Quinoa",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "steamed_potatoes",
                    text = "Steamed Potatoes",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. SAUCES & CONDIMENTS (TEXT ONLY)
                FilterChip(
                    id = "soy_sauce",
                    text = "Light Soy Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "oyster_sauce",
                    text = "Oyster Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chili_oil",
                    text = "Chili Oil",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ginger_scallion_oil",
                    text = "Ginger Scallion Oil",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lemon_butter_sauce",
                    text = "Lemon Butter Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tartar_sauce",
                    text = "Tartar Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sweet_chili",
                    text = "Sweet Chili Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "ponzu",
                    text = "Ponzu",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. DIETARY & HEALTH (TEXT ONLY)
                FilterChip(
                    id = "low_carb_fish",
                    text = "Low Carb",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "high_protein_fish",
                    text = "High Protein",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_fat_fish",
                    text = "Low Fat",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "keto_fish",
                    text = "Keto Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "paleo_fish",
                    text = "Paleo",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_fish",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dairy_free_fish",
                    text = "Dairy Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "omega_3_rich",
                    text = "Omega-3 Rich",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "wild_caught",
                    text = "Wild Caught",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "farm_raised",
                    text = "Farm Raised",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sustainable",
                    text = "Sustainable",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. SPICE LEVEL (TEXT ONLY)
                FilterChip(
                    id = "mild_fish",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "medium_fish",
                    text = "Medium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy_fish",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thai_hot",
                    text = "Thai Hot",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "fish_under_200",
                    text = "Under ₹200",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_200_350",
                    text = "₹200 - ₹350",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_350_500",
                    text = "₹350 - ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_500_750",
                    text = "₹500 - ₹750",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fish_above_750",
                    text = "Above ₹750",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. REGIONAL CUISINES (TEXT ONLY)
                FilterChip(
                    id = "chinese_fish",
                    text = "Chinese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "thai_fish",
                    text = "Thai",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vietnamese_fish",
                    text = "Vietnamese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "japanese_fish",
                    text = "Japanese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kerala_fish",
                    text = "Kerala Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bengali_fish",
                    text = "Bengali Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "goan_fish",
                    text = "Goan Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mediterranean_fish",
                    text = "Mediterranean",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. PORTION SIZE (TEXT ONLY)
                FilterChip(
                    id = "single_fish",
                    text = "Single (150-200g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "double_fish",
                    text = "Double (300-400g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_fish",
                    text = "Family (600g+)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "whole_fish_portion",
                    text = "Whole Fish",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. BONE OPTIONS (TEXT ONLY)
                FilterChip(
                    id = "with_bones",
                    text = "With Bones",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "boneless_fish",
                    text = "Boneless",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. SKIN OPTIONS (TEXT ONLY)
                FilterChip(
                    id = "skin_on_fish",
                    text = "Skin On",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "skinless_fish",
                    text = "Skinless",
                    type = FilterType.TEXT_ONLY
                ),

                // 15. MEAL TYPE (TEXT ONLY)
                FilterChip(
                    id = "light_meal",
                    text = "Light Meal",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "main_course",
                    text = "Main Course",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dinner_special",
                    text = "Dinner Special",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "healthy_lunch",
                    text = "Healthy Lunch",
                    type = FilterType.TEXT_ONLY
                ),

                // 16. RESTAURANT TYPE (TEXT ONLY)
                FilterChip(
                    id = "seafood_specialist",
                    text = "Seafood Specialist",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "asian_eatery",
                    text = "Asian Eatery",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fine_dining_fish",
                    text = "Fine Dining",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "casual_dining",
                    text = "Casual Dining",
                    type = FilterType.TEXT_ONLY
                ),

                // 17. SORT BY DROPDOWN
                FilterChip(
                    id = "sort_by_fish",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = steamedFishDietFilters,
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
        val steamedFishDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_steamed_sea_bass_1,
                title = "Cantonese Steamed Sea Bass",
                price = "349",
                restaurantName = "Dragon Wok",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "0.8 km",
                discount = "15%",
                discountAmount = "up to ₹52",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_steamed_salmon_1,
                title = "Lemon Dill Steamed Salmon",
                price = "399",
                restaurantName = "Sea Catch",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "1.1 km",
                discount = "10%",
                discountAmount = "up to ₹40",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_thai_steamed_fish_1,
                title = "Thai Style Steamed Pomfret",
                price = "329",
                restaurantName = "Thai Spice",
                rating = "4.8",
                deliveryTime = "22-28 mins",
                distance = "0.9 km",
                discount = "12%",
                discountAmount = "up to ₹39",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_steamed_tilapia_1,
                title = "Steamed Tilapia with Ginger",
                price = "289",
                restaurantName = "Healthy Bowl",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.5 km",
                discount = "15%",
                discountAmount = "up to ₹43",
                address = "Brigade Road, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_banana_leaf_fish_1,
                title = "Banana Leaf Steamed Fish",
                price = "359",
                restaurantName = "Malay Kitchen",
                rating = "4.9",
                deliveryTime = "25-30 mins",
                distance = "1.3 km",
                discount = "10%",
                discountAmount = "up to ₹36",
                address = "Bandra West, Mumbai"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_steamed_cod_1,
                title = "Miso Glazed Steamed Cod",
                price = "449",
                restaurantName = "Umami Japan",
                rating = "4.9",
                deliveryTime = "20-25 mins",
                distance = "1.0 km",
                discount = "12%",
                discountAmount = "up to ₹54",
                address = "Connaught Place, Delhi"
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
            foodItems = steamedFishDietItems,
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
    val steamedFishDietItems = listOf(
        // 1-5: CLASSIC STEAMED FISH
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.steamed_fish_diet_1,
            title = "Cantonese Steamed Sea Bass",
            price = "349",
            restaurantName = "Dragon Wok",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹52",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.steamed_fish_diet_2,
            title = "Lemon Dill Steamed Salmon",
            price = "399",
            restaurantName = "Sea Catch",
            rating = "4.9",
            deliveryTime = "18-22 mins",
            distance = "0.9 km",
            discount = "10% OFF",
            discountAmount = "up to ₹40",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.steamed_fish_diet_3,
            title = "Thai Style Steamed Pomfret",
            price = "329",
            restaurantName = "Thai Spice",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "0.7 km",
            discount = "12% OFF",
            discountAmount = "up to ₹39",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.steamed_fish_diet_4,
            title = "Steamed Tilapia with Ginger",
            price = "289",
            restaurantName = "Healthy Bowl",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "0.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Brigade Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.steamed_fish_diet_5,
            title = "Banana Leaf Steamed Fish",
            price = "359",
            restaurantName = "Malay Kitchen",
            rating = "4.9",
            deliveryTime = "25-30 mins",
            distance = "1.3 km",
            discount = "10% OFF",
            discountAmount = "up to ₹36",
            address = "Bandra West, Mumbai"
        ),

        // 6-10: STEAMED FISH WITH ASIAN STYLES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.steamed_fish_diet_6,
            title = "Miso Glazed Steamed Cod",
            price = "449",
            restaurantName = "Umami Japan",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹54",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.steamed_fish_diet_7,
            title = "Soy Ginger Steamed Snapper",
            price = "379",
            restaurantName = "Asian Wok",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "1.1 km",
            discount = "15% OFF",
            discountAmount = "up to ₹57",
            address = "Koramangala, Bangalore"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.steamed_fish_diet_8,
            title = "Sichuan Style Steamed Fish",
            price = "339",
            restaurantName = "Red Chilli",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹34",
            address = "Andheri East, Mumbai"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.steamed_fish_diet_9,
            title = "Steamed Fish with Black Bean Sauce",
            price = "359",
            restaurantName = "Canton House",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "1.2 km",
            discount = "12% OFF",
            discountAmount = "up to ₹43",
            address = "Sector 29, Gurgaon"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.steamed_fish_diet_10,
            title = "Vietnamese Steamed Fish",
            price = "369",
            restaurantName = "Saigon Delight",
            rating = "4.8",
            deliveryTime = "25-30 mins",
            distance = "1.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹55",
            address = "Jayanagar, Bangalore"
        ),

        // 11-15: STEAMED FISH WITH HERBS & VEGETABLES
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.steamed_fish_diet_11,
            title = "Steamed Fish with Asparagus",
            price = "389",
            restaurantName = "Green Leaf",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹39",
            address = "Salt Lake, Kolkata"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.steamed_fish_diet_12,
            title = "Mediterranean Steamed Fish",
            price = "409",
            restaurantName = "Olive Grove",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹49",
            address = "Colaba, Mumbai"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.steamed_fish_diet_13,
            title = "Steamed Fish with Bok Choy",
            price = "329",
            restaurantName = "Wok & Roll",
            rating = "4.7",
            deliveryTime = "18-22 mins",
            distance = "0.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹49",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.steamed_fish_diet_14,
            title = "Lemon Grass Steamed Fish",
            price = "349",
            restaurantName = "Thai Orchid",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹35",
            address = "MG Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.steamed_fish_diet_15,
            title = "Steamed Fish with Fennel",
            price = "379",
            restaurantName = "Herb Garden",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹45",
            address = "Indiranagar, Bangalore"
        ),

        // 16-20: DIET-SPECIAL STEAMED FISH
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.steamed_fish_diet_16,
            title = "Keto Steamed Fish with Butter",
            price = "419",
            restaurantName = "Keto Kitchen",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "0.6 km",
            discount = "15% OFF",
            discountAmount = "up to ₹63",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.steamed_fish_diet_17,
            title = "Paleo Steamed Fish",
            price = "399",
            restaurantName = "Paleo Plate",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "1.0 km",
            discount = "10% OFF",
            discountAmount = "up to ₹40",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.steamed_fish_diet_18,
            title = "High Protein Steamed Fish",
            price = "379",
            restaurantName = "Protein Lab",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "0.5 km",
            discount = "12% OFF",
            discountAmount = "up to ₹45",
            address = "Bandra West, Mumbai"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.steamed_fish_diet_19,
            title = "Low Calorie Steamed Fish",
            price = "339",
            restaurantName = "Low Cal Life",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "15% OFF",
            discountAmount = "up to ₹51",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.steamed_fish_diet_20,
            title = "Omega-3 Rich Steamed Salmon",
            price = "459",
            restaurantName = "Healthy Catch",
            rating = "4.9",
            deliveryTime = "22-28 mins",
            distance = "1.2 km",
            discount = "10% OFF",
            discountAmount = "up to ₹46",
            address = "Koramangala, Bangalore"
        )
    )
    Column {
        steamedFishDietItems.forEach { restaurantItem ->
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
fun QuinoaBowlDietPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val quinoaBowlDietFilters = FilterConfig(
            filters = listOf(
                // 1. Main Filters Dropdown
                FilterChip(
                    id = "filters",
                    text = "Filters",
                    type = FilterType.FILTER_DROPDOWN,
                    icon = R.drawable.ic_filter,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                ),

                // 2. KEY QUINOA TYPES (WITH ICONS - MAIN CATEGORIES ONLY)
                FilterChip(
                    id = "white_quinoa",
                    text = "White Quinoa",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_white_quinoa  // White quinoa grains illustration
                ),
                FilterChip(
                    id = "red_quinoa",
                    text = "Red Quinoa",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_red_quinoa  // Red quinoa grains with reddish hue
                ),
                FilterChip(
                    id = "black_quinoa",
                    text = "Black Quinoa",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_black_quinoa  // Black quinoa grains illustration
                ),
                FilterChip(
                    id = "tricolor_quinoa",
                    text = "Tricolor Quinoa",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_tricolor_quinoa  // Mix of white, red, black quinoa
                ),
                FilterChip(
                    id = "quinoa_flakes",
                    text = "Quinoa Flakes",
                    type = FilterType.WITH_LEFT_ICON,
                    icon = R.drawable.ic_quinoa_flakes  // Flattened quinoa flakes
                ),

                // 3. BOWL BASE (TEXT ONLY)
                FilterChip(
                    id = "quinoa_only",
                    text = "Quinoa Only",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quinoa_rice_mix",
                    text = "Quinoa & Rice Mix",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quinoa_greens_mix",
                    text = "Quinoa & Greens",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "quinoa_spring_mix",
                    text = "Quinoa Spring Mix",
                    type = FilterType.TEXT_ONLY
                ),

                // 4. PROTEIN SOURCES (TEXT ONLY)
                FilterChip(
                    id = "grilled_chicken",
                    text = "Grilled Chicken",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "salmon_bowl",
                    text = "Salmon",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tuna_bowl",
                    text = "Tuna",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tofu_bowl",
                    text = "Tofu",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tempeh_bowl",
                    text = "Tempeh",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "edamame_bowl",
                    text = "Edamame",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chickpeas_bowl",
                    text = "Chickpeas",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "black_beans_bowl",
                    text = "Black Beans",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "lentils_bowl",
                    text = "Lentils",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hard_boiled_egg",
                    text = "Hard Boiled Egg",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "poached_egg",
                    text = "Poached Egg",
                    type = FilterType.TEXT_ONLY
                ),

                // 5. FRESH VEGETABLES (TEXT ONLY)
                FilterChip(
                    id = "roasted_veggies",
                    text = "Roasted Veggies",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "steamed_broccoli",
                    text = "Steamed Broccoli",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "roasted_cauliflower",
                    text = "Roasted Cauliflower",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bell_peppers_bowl",
                    text = "Bell Peppers",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cherry_tomatoes",
                    text = "Cherry Tomatoes",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cucumber_bowl",
                    text = "Cucumber",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spinach_bowl",
                    text = "Fresh Spinach",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "kale_bowl",
                    text = "Kale",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "arugula_bowl",
                    text = "Arugula",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "roasted_sweet_potato",
                    text = "Roasted Sweet Potato",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "avocado_bowl",
                    text = "Avocado",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "roasted_mushrooms",
                    text = "Roasted Mushrooms",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "carrots_bowl",
                    text = "Shredded Carrots",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "corn_bowl",
                    text = "Sweet Corn",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "red_onion_bowl",
                    text = "Red Onion",
                    type = FilterType.TEXT_ONLY
                ),

                // 6. DRESSINGS & SAUCES (TEXT ONLY)
                FilterChip(
                    id = "lemon_herb_vinaigrette",
                    text = "Lemon Herb Vinaigrette",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "balsamic_glaze",
                    text = "Balsamic Glaze",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "tahini_dressing",
                    text = "Tahini Dressing",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "greek_yogurt_dressing",
                    text = "Greek Yogurt Dressing",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cilantro_lime",
                    text = "Cilantro Lime",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "avocado_crema",
                    text = "Avocado Crema",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "miso_ginger",
                    text = "Miso Ginger",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sesame_ginger",
                    text = "Sesame Ginger",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy_peanut",
                    text = "Spicy Peanut Sauce",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sriracha_mayo",
                    text = "Sriracha Mayo",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pesto_bowl",
                    text = "Pesto",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "olive_oil_lemon",
                    text = "Olive Oil & Lemon",
                    type = FilterType.TEXT_ONLY
                ),

                // 7. TOPPINGS & ADD-ONS (TEXT ONLY)
                FilterChip(
                    id = "roasted_nuts",
                    text = "Roasted Nuts",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "pumpkin_seeds",
                    text = "Pumpkin Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sunflower_seeds",
                    text = "Sunflower Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "sesame_seeds",
                    text = "Sesame Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "chia_seeds",
                    text = "Chia Seeds",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hemp_hearts",
                    text = "Hemp Hearts",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "crumbled_feta",
                    text = "Crumbled Feta",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "goat_cheese",
                    text = "Goat Cheese",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "parmesan_bowl",
                    text = "Parmesan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "nutritional_yeast",
                    text = "Nutritional Yeast",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "fresh_herbs_bowl",
                    text = "Fresh Herbs",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "microgreens_bowl",
                    text = "Microgreens",
                    type = FilterType.TEXT_ONLY
                ),

                // 8. DIETARY PREFERENCES (TEXT ONLY)
                FilterChip(
                    id = "vegan_bowl",
                    text = "Vegan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegetarian_bowl",
                    text = "Vegetarian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "gluten_free_bowl",
                    text = "Gluten Free",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dairy_free_bowl",
                    text = "Dairy Free",
                    type = FilterType.TEXT_ONLY
                ),
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
                    id = "low_fat_bowl",
                    text = "Low Fat",
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
                FilterChip(
                    id = "whole30_bowl",
                    text = "Whole30",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "low_sodium_bowl",
                    text = "Low Sodium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "organic_bowl",
                    text = "Organic",
                    type = FilterType.TEXT_ONLY
                ),

                // 9. SPICE LEVEL (TEXT ONLY)
                FilterChip(
                    id = "mild_bowl",
                    text = "Mild",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "medium_bowl",
                    text = "Medium",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "spicy_bowl",
                    text = "Spicy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "extra_spicy_bowl",
                    text = "Extra Spicy",
                    type = FilterType.TEXT_ONLY
                ),

                // 10. PRICE RANGE (TEXT ONLY)
                FilterChip(
                    id = "bowl_under_300",
                    text = "Under ₹300",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bowl_300_500",
                    text = "₹300 - ₹500",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bowl_500_700",
                    text = "₹500 - ₹700",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bowl_700_1000",
                    text = "₹700 - ₹1000",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bowl_above_1000",
                    text = "Above ₹1000",
                    type = FilterType.TEXT_ONLY
                ),

                // 11. CUISINE STYLES (TEXT ONLY)
                FilterChip(
                    id = "mediterranean_bowl",
                    text = "Mediterranean",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "mexican_bowl",
                    text = "Mexican",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "asian_bowl",
                    text = "Asian",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "greek_bowl",
                    text = "Greek",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "moroccan_bowl",
                    text = "Moroccan",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "indian_bowl",
                    text = "Indian Fusion",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "california_bowl",
                    text = "California Style",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "hawaiian_bowl",
                    text = "Hawaiian (Poke)",
                    type = FilterType.TEXT_ONLY
                ),

                // 12. BOWL SIZE (TEXT ONLY)
                FilterChip(
                    id = "small_bowl",
                    text = "Small (250-300g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "regular_bowl",
                    text = "Regular (400-450g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "large_bowl",
                    text = "Large (550-600g)",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "family_bowl",
                    text = "Family (800g+)",
                    type = FilterType.TEXT_ONLY
                ),

                // 13. MEAL TYPE (TEXT ONLY)
                FilterChip(
                    id = "light_lunch_bowl",
                    text = "Light Lunch",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "post_workout_bowl",
                    text = "Post-Workout",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "meal_prep_bowl",
                    text = "Meal Prep Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "dinner_bowl",
                    text = "Dinner Bowl",
                    type = FilterType.TEXT_ONLY
                ),

                // 14. PREPARATION STYLE (TEXT ONLY)
                FilterChip(
                    id = "raw_bowl",
                    text = "Raw / Fresh",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "warm_bowl",
                    text = "Warm Bowl",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "cold_bowl",
                    text = "Cold Bowl",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "grilled_bowl",
                    text = "Grilled Bowl",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "roasted_bowl",
                    text = "Roasted Bowl",
                    type = FilterType.TEXT_ONLY
                ),

                // 15. SPECIAL DIETS (TEXT ONLY)
                FilterChip(
                    id = "diabetic_friendly",
                    text = "Diabetic Friendly",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "heart_healthy_bowl",
                    text = "Heart Healthy",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "weight_loss_bowl",
                    text = "Weight Loss",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "muscle_gain_bowl",
                    text = "Muscle Gain",
                    type = FilterType.TEXT_ONLY
                ),

                // 16. RESTAURANT TYPE (TEXT ONLY)
                FilterChip(
                    id = "health_cafe",
                    text = "Health Cafe",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "bowl_specialist",
                    text = "Bowl Specialist",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "vegan_restaurant",
                    text = "Vegan Restaurant",
                    type = FilterType.TEXT_ONLY
                ),
                FilterChip(
                    id = "organic_cafe",
                    text = "Organic Cafe",
                    type = FilterType.TEXT_ONLY
                ),

                // 17. SORT BY DROPDOWN
                FilterChip(
                    id = "sort_by_bowl",
                    text = "Sort By",
                    type = FilterType.SORT_DROPDOWN,
                    rightIcon = R.drawable.outline_keyboard_arrow_down_24
                )
            ),
            rows = 2
        )
        FilterButtonFood(
            filterConfig = quinoaBowlDietFilters,
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
        val quinoaBowlDietItems = listOf(
            FoodItemDoubleF(
                id = 1,
                imageRes = R.drawable.ic_mediterranean_quinoa_bowl_1,
                title = "Mediterranean Quinoa Bowl",
                price = "299",
                restaurantName = "The Green Bowl",
                rating = "4.8",
                deliveryTime = "15-20 mins",
                distance = "0.6 km",
                discount = "15%",
                discountAmount = "up to ₹45",
                address = "Koramangala, Bangalore"
            ),
            FoodItemDoubleF(
                id = 2,
                imageRes = R.drawable.ic_mexican_quinoa_bowl_1,
                title = "Mexican Spicy Quinoa Bowl",
                price = "329",
                restaurantName = "Fiesta Fresh",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "1.2 km",
                discount = "12%",
                discountAmount = "up to ₹39",
                address = "Koregaon Park, Pune"
            ),
            FoodItemDoubleF(
                id = 3,
                imageRes = R.drawable.ic_thai_quinoa_bowl_1,
                title = "Thai Peanut Quinoa Bowl",
                price = "319",
                restaurantName = "Thai Alive",
                rating = "4.8",
                deliveryTime = "20-25 mins",
                distance = "0.9 km",
                discount = "10%",
                discountAmount = "up to ₹32",
                address = "Jubilee Hills, Hyderabad"
            ),
            FoodItemDoubleF(
                id = 4,
                imageRes = R.drawable.ic_teriyaki_quinoa_bowl_1,
                title = "Teriyaki Tofu Quinoa Bowl",
                price = "289",
                restaurantName = "Healthy Asian",
                rating = "4.7",
                deliveryTime = "15-20 mins",
                distance = "0.7 km",
                discount = "15%",
                discountAmount = "up to ₹43",
                address = "Indiranagar, Bangalore"
            ),
            FoodItemDoubleF(
                id = 5,
                imageRes = R.drawable.ic_greek_quinoa_bowl_1,
                title = "Greek Quinoa with Feta",
                price = "339",
                restaurantName = "Olympus Bowls",
                rating = "4.9",
                deliveryTime = "18-22 mins",
                distance = "1.1 km",
                discount = "12%",
                discountAmount = "up to ₹41",
                address = "Bandra West, Mumbai"
            ),
            FoodItemDoubleF(
                id = 6,
                imageRes = R.drawable.ic_southwest_quinoa_bowl_1,
                title = "Southwest Grilled Chicken Quinoa",
                price = "359",
                restaurantName = "Desi Roots",
                rating = "4.8",
                deliveryTime = "22-28 mins",
                distance = "1.4 km",
                discount = "10%",
                discountAmount = "up to ₹36",
                address = "Connaught Place, Delhi"
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
            foodItems = quinoaBowlDietItems,
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
    val quinoaBowlDietItems = listOf(
        // 1-5: CLASSIC QUINOA BOWLS
        RestaurantItemFull(
            id = 1,
            imageRes = R.drawable.quinoa_bowl_diet_1,
            title = "Mediterranean Quinoa Bowl",
            price = "299",
            restaurantName = "The Green Bowl",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.5 km",
            discount = "15% OFF",
            discountAmount = "up to ₹45",
            address = "Koramangala, Bangalore"
        ),
        RestaurantItemFull(
            id = 2,
            imageRes = R.drawable.quinoa_bowl_diet_2,
            title = "Mexican Spicy Quinoa Bowl",
            price = "329",
            restaurantName = "Fiesta Fresh",
            rating = "4.9",
            deliveryTime = "18-22 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹39",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 3,
            imageRes = R.drawable.quinoa_bowl_diet_3,
            title = "Thai Peanut Quinoa Bowl",
            price = "319",
            restaurantName = "Thai Alive",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 4,
            imageRes = R.drawable.quinoa_bowl_diet_4,
            title = "Teriyaki Tofu Quinoa Bowl",
            price = "289",
            restaurantName = "Healthy Asian",
            rating = "4.7",
            deliveryTime = "15-20 mins",
            distance = "0.4 km",
            discount = "15% OFF",
            discountAmount = "up to ₹43",
            address = "Indiranagar, Bangalore"
        ),
        RestaurantItemFull(
            id = 5,
            imageRes = R.drawable.quinoa_bowl_diet_5,
            title = "Greek Quinoa with Feta",
            price = "339",
            restaurantName = "Olympus Bowls",
            rating = "4.9",
            deliveryTime = "18-22 mins",
            distance = "1.3 km",
            discount = "12% OFF",
            discountAmount = "up to ₹41",
            address = "Bandra West, Mumbai"
        ),

        // 6-10: FUSION & INTERNATIONAL STYLES
        RestaurantItemFull(
            id = 6,
            imageRes = R.drawable.quinoa_bowl_diet_6,
            title = "Hawaiian Poke Quinoa Bowl",
            price = "379",
            restaurantName = "Island Bowls",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹57",
            address = "Connaught Place, Delhi"
        ),
        RestaurantItemFull(
            id = 7,
            imageRes = R.drawable.quinoa_bowl_diet_7,
            title = "Moroccan Spiced Quinoa Bowl",
            price = "309",
            restaurantName = "Marrakesh Cafe",
            rating = "4.8",
            deliveryTime = "22-28 mins",
            distance = "1.1 km",
            discount = "12% OFF",
            discountAmount = "up to ₹37",
            address = "Banjara Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 8,
            imageRes = R.drawable.quinoa_bowl_diet_8,
            title = "Japanese Miso Quinoa Bowl",
            price = "349",
            restaurantName = "Umami Bowl",
            rating = "4.8",
            deliveryTime = "20-25 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹35",
            address = "Andheri East, Mumbai"
        ),
        RestaurantItemFull(
            id = 9,
            imageRes = R.drawable.quinoa_bowl_diet_9,
            title = "Southwest Chipotle Quinoa",
            price = "359",
            restaurantName = "Desi Roots",
            rating = "4.7",
            deliveryTime = "22-28 mins",
            distance = "1.2 km",
            discount = "15% OFF",
            discountAmount = "up to ₹54",
            address = "Sector 29, Gurgaon"
        ),
        RestaurantItemFull(
            id = 10,
            imageRes = R.drawable.quinoa_bowl_diet_10,
            title = "Indian Masala Quinoa Bowl",
            price = "299",
            restaurantName = "Spice Roots",
            rating = "4.8",
            deliveryTime = "18-24 mins",
            distance = "0.6 km",
            discount = "12% OFF",
            discountAmount = "up to ₹36",
            address = "Jayanagar, Bangalore"
        ),

        // 11-15: PROTEIN-RICH BOWLS
        RestaurantItemFull(
            id = 11,
            imageRes = R.drawable.quinoa_bowl_diet_11,
            title = "Grilled Chicken Quinoa Bowl",
            price = "359",
            restaurantName = "Protein House",
            rating = "4.9",
            deliveryTime = "18-24 mins",
            distance = "0.6 km",
            discount = "10% OFF",
            discountAmount = "up to ₹36",
            address = "Salt Lake, Kolkata"
        ),
        RestaurantItemFull(
            id = 12,
            imageRes = R.drawable.quinoa_bowl_diet_12,
            title = "Salmon Avocado Quinoa Bowl",
            price = "429",
            restaurantName = "Sea Green",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "0.9 km",
            discount = "12% OFF",
            discountAmount = "up to ₹51",
            address = "Colaba, Mumbai"
        ),
        RestaurantItemFull(
            id = 13,
            imageRes = R.drawable.quinoa_bowl_diet_13,
            title = "Chickpea & Spinach Quinoa",
            price = "279",
            restaurantName = "Plant Power",
            rating = "4.8",
            deliveryTime = "15-20 mins",
            distance = "0.7 km",
            discount = "15% OFF",
            discountAmount = "up to ₹42",
            address = "Baner, Pune"
        ),
        RestaurantItemFull(
            id = 14,
            imageRes = R.drawable.quinoa_bowl_diet_14,
            title = "Tandoori Paneer Quinoa Bowl",
            price = "319",
            restaurantName = "Punjabi Angithi",
            rating = "4.8",
            deliveryTime = "18-22 mins",
            distance = "0.8 km",
            discount = "10% OFF",
            discountAmount = "up to ₹32",
            address = "MG Road, Bangalore"
        ),
        RestaurantItemFull(
            id = 15,
            imageRes = R.drawable.quinoa_bowl_diet_15,
            title = "Edamame & Tofu Quinoa Bowl",
            price = "309",
            restaurantName = "Vegan Valley",
            rating = "4.7",
            deliveryTime = "20-25 mins",
            distance = "1.0 km",
            discount = "12% OFF",
            discountAmount = "up to ₹37",
            address = "Indiranagar, Bangalore"
        ),

        // 16-20: BREAKFAST & SPECIALTY BOWLS
        RestaurantItemFull(
            id = 16,
            imageRes = R.drawable.quinoa_bowl_diet_16,
            title = "Breakfast Power Quinoa Bowl",
            price = "249",
            restaurantName = "Morning Fresh",
            rating = "4.8",
            deliveryTime = "12-18 mins",
            distance = "0.3 km",
            discount = "15% OFF",
            discountAmount = "up to ₹37",
            address = "Whitefield, Bangalore"
        ),
        RestaurantItemFull(
            id = 17,
            imageRes = R.drawable.quinoa_bowl_diet_17,
            title = "Rainbow Veggie Quinoa Bowl",
            price = "269",
            restaurantName = "Pure Earth",
            rating = "4.9",
            deliveryTime = "15-20 mins",
            distance = "0.5 km",
            discount = "20% OFF",
            discountAmount = "up to ₹54",
            address = "Koregaon Park, Pune"
        ),
        RestaurantItemFull(
            id = 18,
            imageRes = R.drawable.quinoa_bowl_diet_18,
            title = "Buddha's Quinoa Bliss Bowl",
            price = "349",
            restaurantName = "Zen Bowl",
            rating = "4.9",
            deliveryTime = "20-25 mins",
            distance = "1.0 km",
            discount = "15% OFF",
            discountAmount = "up to ₹52",
            address = "Saket, Delhi"
        ),
        RestaurantItemFull(
            id = 19,
            imageRes = R.drawable.quinoa_bowl_diet_19,
            title = "Detox Green Quinoa Bowl",
            price = "289",
            restaurantName = "Juice & Bowl",
            rating = "4.7",
            deliveryTime = "18-22 mins",
            distance = "0.8 km",
            discount = "12% OFF",
            discountAmount = "up to ₹35",
            address = "Jubilee Hills, Hyderabad"
        ),
        RestaurantItemFull(
            id = 20,
            imageRes = R.drawable.quinoa_bowl_diet_20,
            title = "Superfood Berry Quinoa Bowl",
            price = "329",
            restaurantName = "Berry Fresh",
            rating = "4.8",
            deliveryTime = "16-20 mins",
            distance = "0.7 km",
            discount = "10% OFF",
            discountAmount = "up to ₹33",
            address = "Bandra West, Mumbai"
        )
    )
    Column {
        quinoaBowlDietItems.forEach { restaurantItem ->
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
