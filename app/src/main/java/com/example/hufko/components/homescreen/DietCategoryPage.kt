package com.example.hufko.components.homescreen

import com.example.hufko.api.services.config.NetworkConfig
//
//data class DietFoodItem(
//    val id: Int,
//    val name: String,
//    val description: String? = null,
//    val imageUrl: String,
//    val calories: Int,
//    val protein: Int,
//    val isVeg: Boolean = true,
//    val category: String = "Chicken"
//)
//
//sealed class DietCategoryPage(
//    val title: String,
//    val iconUrl: String,
//    val category: String,
//    val priority: Int,
//    val isActive: Boolean
//) {
//    // First 8 main categories (indices 0-7)
//    object Chicken : DietCategoryPage(
//        title = "Chicken",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chicken_food_diet.png",
//        category = "CHICKEN_DIET",
//        priority = 1,
//        isActive = true
//    )
//
//    object Salad : DietCategoryPage(
//        title = "Salad",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/salad_food_diet.png",
//        category = "SALAD_DIET",
//        priority = 2,
//        isActive = true
//    )
//
//    object Mutton : DietCategoryPage(
//        title = "Mutton",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/mutton_food_diet.png",
//        category = "MUTTON_DIET",
//        priority = 3,
//        isActive = true
//    )
//
//    object Kebabs : DietCategoryPage(
//        title = "Kebabs",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/kebabs_food_diet.png",
//        category = "KEBABS_DIET",
//        priority = 4,
//        isActive = true
//    )
//
//    object HealthySnacks : DietCategoryPage(
//        title = "Snacks",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/healthy_snacks_food_diet.png",
//        category = "HEALTHY_SNACKS",
//        priority = 5,
//        isActive = true
//    )
//
//    object LowCalorie : DietCategoryPage(
//        title = "Low Calorie",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/low_calorie_food_diet.png",
//        category = "LOW_CALORIE",
//        priority = 6,
//        isActive = true
//    )
//
//    object Vegan : DietCategoryPage(
//        title = "Vegan",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/vegan_food_diet.png",
//        category = "VEGAN_DIET",
//        priority = 7,
//        isActive = true
//    )
//
//    object ProteinRich : DietCategoryPage(
//        title = "Protein Rich",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/protein_food_diet.png",
//        category = "PROTEIN_RICH",
//        priority = 8,
//        isActive = true
//    )
//
//    // Categories from your list (indices 8-22)
//    object Dessert : DietCategoryPage(
//        title = "Dessert",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dessert_food.png",
//        category = "DESSERT_DIET",
//        priority = 9,
//        isActive = true
//    )
//
//    object VegMeal : DietCategoryPage(
//        title = "Veg Meal",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/veg_meal_food.png",
//        category = "VEG_MEAL_DIET",
//        priority = 10,
//        isActive = true
//    )
//
//    object Bowl : DietCategoryPage(
//        title = "Bowl",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bowl_food.png",
//        category = "BOWL_DIET",
//        priority = 11,
//        isActive = true
//    )
//
//    object Sweets : DietCategoryPage(
//        title = "Sweets",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sweets_food.png",
//        category = "SWEETS_DIET",
//        priority = 12,
//        isActive = true
//    )
//
//    object Khichdi : DietCategoryPage(
//        title = "Khichdi",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/khichdi_food.png",
//        category = "KHICHDI_DIET",
//        priority = 13,
//        isActive = true
//    )
//
//    object Sundae : DietCategoryPage(
//        title = "Sundae",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sundae_food.png",
//        category = "SUNDAE_DIET",
//        priority = 14,
//        isActive = true
//    )
//
//    object Juice : DietCategoryPage(
//        title = "Juice",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/juice_food.png",
//        category = "JUICE_DIET",
//        priority = 15,
//        isActive = true
//    )
//
//    object Lassi : DietCategoryPage(
//        title = "Lassi",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/lassi_food.png",
//        category = "LASSI_DIET",
//        priority = 16,
//        isActive = true
//    )
//
//    object CurdRice : DietCategoryPage(
//        title = "Curd Rice",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/curd_rice_food.png",
//        category = "CURD_RICE_DIET",
//        priority = 17,
//        isActive = true
//    )
//
//    object Pudding : DietCategoryPage(
//        title = "Pudding",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pudding_food.png",
//        category = "PUDDING_DIET",
//        priority = 18,
//        isActive = true
//    )
//
//    object Custard : DietCategoryPage(
//        title = "Custard",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/custard_food.png",
//        category = "CUSTARD_DIET",
//        priority = 19,
//        isActive = true
//    )
//
//    object Soup : DietCategoryPage(
//        title = "Soup",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/soup_food.png",
//        category = "SOUP_DIET",
//        priority = 20,
//        isActive = true
//    )
//
//    object Brownie : DietCategoryPage(
//        title = "Brownie",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/brownie_food.png",
//        category = "BROWNIE_DIET",
//        priority = 21,
//        isActive = true
//    )
//
//    object Waffles : DietCategoryPage(
//        title = "Waffles",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/waffles_food.png",
//        category = "WAFFLES_DIET",
//        priority = 22,
//        isActive = true
//    )
//
//    object ColdCoffee : DietCategoryPage(
//        title = "Cold Coffee",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cold_coffee_food.png",
//        category = "COLD_COFFEE_DIET",
//        priority = 23,
//        isActive = true
//    )
//
//    // Additional diet-specific items (indices 23-46)
//    object GrilledChicken : DietCategoryPage(
//        title = "Grilled Chicken",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/grilled_chicken.png",
//        category = "GRILLED_CHICKEN",
//        priority = 24,
//        isActive = true
//    )
//
//    object SteamedFish : DietCategoryPage(
//        title = "Steamed Fish",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/steamed_fish.png",
//        category = "STEAMED_FISH",
//        priority = 25,
//        isActive = true
//    )
//
//    object QuinoaBowl : DietCategoryPage(
//        title = "Quinoa Bowl",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/quinoa_bowl.png",
//        category = "QUINOA_BOWL",
//        priority = 26,
//        isActive = true
//    )
//
//    object AvocadoToast : DietCategoryPage(
//        title = "Avocado Toast",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/avocado_toast_food.png",
//        category = "AVOCADO_TOAST",
//        priority = 27,
//        isActive = true
//    )
//
//    object GreenSmoothie : DietCategoryPage(
//        title = "Green Smoothie",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/green_smoothie_food.png",
//        category = "GREEN_SMOOTHIE",
//        priority = 28,
//        isActive = true
//    )
//
//    object Oatmeal : DietCategoryPage(
//        title = "Oatmeal",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/oatmeal_food.png",
//        category = "OATMEAL",
//        priority = 29,
//        isActive = true
//    )
//
//    object GreekYogurt : DietCategoryPage(
//        title = "Greek Yogurt",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/greek_yogurt_food_diet.png",
//        category = "GREEK_YOGURT",
//        priority = 30,
//        isActive = true
//    )
//
//    object EggWhiteOmelette : DietCategoryPage(
//        title = "Egg White",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/egg_white_omelette_food_diet.png",
//        category = "EGG_WHITE_OMELETTE",
//        priority = 31,
//        isActive = true
//    )
//
//    object TunaSalad : DietCategoryPage(
//        title = "Tuna Salad",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/tuna_salad_food_diet.png",
//        category = "TUNA_SALAD",
//        priority = 32,
//        isActive = true
//    )
//
//    object LentilSoup : DietCategoryPage(
//        title = "Lentil Soup",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/lentil_soup_food_diet.png",
//        category = "LENTIL_SOUP",
//        priority = 33,
//        isActive = true
//    )
//
//    object CottageCheese : DietCategoryPage(
//        title = "Cottage Cheese",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cottage_cheese_food_diet.png",
//        category = "COTTAGE_CHEESE",
//        priority = 34,
//        isActive = true
//    )
//
//    object SproutsSalad : DietCategoryPage(
//        title = "Sprouts Salad",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sprouts_salad_food_diet.png",
//        category = "SPROUTS_SALAD",
//        priority = 35,
//        isActive = true
//    )
//
//    object BrownRiceBowl : DietCategoryPage(
//        title = "Brown Rice",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/brown_rice_bowl_food_diet.png",
//        category = "BROWN_RICE_BOWL",
//        priority = 36,
//        isActive = true
//    )
//
//    object SteamedVeggies : DietCategoryPage(
//        title = "Steamed Veg",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/steamed_veggies_food_diet.png",
//        category = "STEAMED_VEGGIES",
//        priority = 37,
//        isActive = true
//    )
//
//    object FruitBowl : DietCategoryPage(
//        title = "Fruit Bowl",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/fruit_bowl_food_diet.png",
//        category = "FRUIT_BOWL",
//        priority = 38,
//        isActive = true
//    )
//
//    object DetoxWater : DietCategoryPage(
//        title = "Detox Water",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/detox_water_food_diet.png",
//        category = "DETOX_WATER",
//        priority = 39,
//        isActive = true
//    )
//
//    object HerbalTea : DietCategoryPage(
//        title = "Herbal Tea",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/herbal_tea_food_diet.png",
//        category = "HERBAL_TEA",
//        priority = 40,
//        isActive = true
//    )
//
//    object ProteinBar : DietCategoryPage(
//        title = "Protein Bar",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/protein_bar_food_diet.png",
//        category = "PROTEIN_BAR",
//        priority = 41,
//        isActive = true
//    )
//
//    object BoiledEggs : DietCategoryPage(
//        title = "Boiled Eggs",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/boiled_eggs_food_diet.png",
//        category = "BOILED_EGGS",
//        priority = 42,
//        isActive = true
//    )
//
//    object HummusPlate : DietCategoryPage(
//        title = "Hummus Plate",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/hummus_plate_food_diet.png",
//        category = "HUMMUS_PLATE",
//        priority = 43,
//        isActive = true
//    )
//
//    object SushiRolls : DietCategoryPage(
//        title = "Sushi Rolls",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sushi_rolls_food_diet.png",
//        category = "SUSHI_ROLLS",
//        priority = 44,
//        isActive = true
//    )
//
//    object TofuStirFry : DietCategoryPage(
//        title = "Tofu Stir Fry",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/tofu_stir_fry_food_diet.png",
//        category = "TOFU_STIR_FRY",
//        priority = 45,
//        isActive = true
//    )
//
//    object ChiaPudding : DietCategoryPage(
//        title = "Chia Pudding",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chia_pudding_food_diet.png",
//        category = "CHIA_PUDDING",
//        priority = 46,
//        isActive = true
//    )
//
//    object MilletBowl : DietCategoryPage(
//        title = "Millet Bowl",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/millet_bowl_food_diet.png",
//        category = "MILLET_BOWL",
//        priority = 47,
//        isActive = true
//    )
//
//    object SeeAll : DietCategoryPage(
//        title = "See All",
//        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/see_all_food.png",
//        category = "SEE_ALL_DIET",
//        priority = 999,
//        isActive = true
//    )
//}

// Helper function to get all diet category pages
//fun getAllDietCategoryPages(): List<DietCategoryPage> {
//    return listOf(
//        DietCategoryPage.Chicken,
//        DietCategoryPage.Salad,
//        DietCategoryPage.Mutton,
//        DietCategoryPage.Kebabs,
//        DietCategoryPage.HealthySnacks,
//        DietCategoryPage.LowCalorie,
//        DietCategoryPage.Vegan,
//        DietCategoryPage.ProteinRich,
//        DietCategoryPage.Dessert,
//        DietCategoryPage.VegMeal,
//        DietCategoryPage.Bowl,
//        DietCategoryPage.Sweets,
//        DietCategoryPage.Khichdi,
//        DietCategoryPage.Sundae,
//        DietCategoryPage.Juice,
//        DietCategoryPage.Lassi,
//        DietCategoryPage.CurdRice,
//        DietCategoryPage.Pudding,
//        DietCategoryPage.Custard,
//        DietCategoryPage.Soup,
//        DietCategoryPage.Brownie,
//        DietCategoryPage.Waffles,
//        DietCategoryPage.ColdCoffee,
//        DietCategoryPage.GrilledChicken,
//        DietCategoryPage.SteamedFish,
//        DietCategoryPage.QuinoaBowl,
//        DietCategoryPage.AvocadoToast,
//        DietCategoryPage.GreenSmoothie,
//        DietCategoryPage.Oatmeal,
//        DietCategoryPage.GreekYogurt,
//        DietCategoryPage.EggWhiteOmelette,
//        DietCategoryPage.TunaSalad,
//        DietCategoryPage.LentilSoup,
//        DietCategoryPage.CottageCheese,
//        DietCategoryPage.SproutsSalad,
//        DietCategoryPage.BrownRiceBowl,
//        DietCategoryPage.SteamedVeggies,
//        DietCategoryPage.FruitBowl,
//        DietCategoryPage.DetoxWater,
//        DietCategoryPage.HerbalTea,
//        DietCategoryPage.ProteinBar,
//        DietCategoryPage.BoiledEggs,
//        DietCategoryPage.HummusPlate,
//        DietCategoryPage.SushiRolls,
//        DietCategoryPage.TofuStirFry,
//        DietCategoryPage.ChiaPudding,
//        DietCategoryPage.MilletBowl
//    ).filter { it.isActive }
//}