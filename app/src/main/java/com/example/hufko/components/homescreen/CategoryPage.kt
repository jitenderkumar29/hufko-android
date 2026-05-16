package com.example.hufko.components.homescreen
import com.example.hufko.api.services.config.NetworkConfig


// Sealed class for different category pages
sealed class CategoryPage(
    val title: String,
    val iconUrl: String,
    val category: String,
    val priority: Int,
    val isActive: Boolean
) {
    // From the images provided
    object All : CategoryPage(
        title = "All",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/all_food.png",
        category = "ALL",
        priority = 1,
        isActive = true
    )

    object Diet : CategoryPage(
        title = "Diet",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/diet_food.png",
        category = "DIET",
        priority = 2,
        isActive = true
    )

    object Pizzas : CategoryPage(
        title = "Pizzas",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pizzas_food.png",
        category = "PIZZAS",
        priority = 3,
        isActive = true
    )

    object Cakes : CategoryPage(
        title = "Cakes",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cakes_food.png",
        category = "CAKES",
        priority = 4,
        isActive = true
    )

    object Momos : CategoryPage(
        title = "Momos",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/momos_food.png",
        category = "MOMOS",
        priority = 5,
        isActive = true
    )

    object Rolls : CategoryPage(
        title = "Rolls",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/rolls_food.png",
        category = "ROLLS",
        priority = 6,
        isActive = true
    )

    object Burgers : CategoryPage(
        title = "Burgers",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/burgers_food.png",
        category = "BURGERS",
        priority = 7,
        isActive = true
    )

    object CholeBhature : CategoryPage(
        title = "Chole Bhature",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chole_bhature_food.png",
        category = "CHOLE_BHATURE",
        priority = 8,
        isActive = true
    )

    object Salad : CategoryPage(
        title = "Salad",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/salad_food.png",
        category = "SALAD",
        priority = 9,
        isActive = true
    )

    object Patty : CategoryPage(
        title = "Patty",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/patty_food.png",
        category = "PATTY",
        priority = 10,
        isActive = true
    )

    object Chinese : CategoryPage(
        title = "Chinese",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chinese_food.png",
        category = "CHINESE",
        priority = 11,
        isActive = true
    )

    object IceCream : CategoryPage(
        title = "Ice Cream",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/ice_cream_food.png",
        category = "ICE_CREAM",
        priority = 12,
        isActive = true
    )

    object Appam : CategoryPage(
        title = "Appam",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/appam_food.png",
        category = "APPAM",
        priority = 13,
        isActive = true
    )

    object Bath : CategoryPage(
        title = "Bath",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bath_food.png",
        category = "BATH",
        priority = 14,
        isActive = true
    )

    object Bonda : CategoryPage(
        title = "Bonda",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bonda_food.png",
        category = "BONDA",
        priority = 15,
        isActive = true
    )

    object Cutlet : CategoryPage(
        title = "Cutlet",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cutlet_food.png",
        category = "CUTLET",
        priority = 16,
        isActive = true
    )

    object Dessert : CategoryPage(
        title = "Dessert",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dessert_food.png",
        category = "DESSERT",
        priority = 17,
        isActive = true
    )

    object Dhokla : CategoryPage(
        title = "Dhokla",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dhokla_food.png",
        category = "DHOKLA",
        priority = 18,
        isActive = true
    )

    object Dosa : CategoryPage(
        title = "Dosa",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dosa_food.png",
        category = "DOSA",
        priority = 19,
        isActive = true
    )

    object Dholda : CategoryPage(
        title = "Dholda",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dholda_food.png",
        category = "DHOLDA",
        priority = 20,
        isActive = true
    )

    object GulabJamun : CategoryPage(
        title = "Gulab Jamun",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/gulab_jamun_food.png",
        category = "GULAB_JAMUN",
        priority = 21,
        isActive = true
    )

    object Idli : CategoryPage(
        title = "Idli",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/idli_food.png",
        category = "IDLI",
        priority = 22,
        isActive = true
    )

    object Biryani : CategoryPage(
        title = "Biryani",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/biryani_food.png",
        category = "BIRYANI",
        priority = 23,
        isActive = true
    )

    object Thali : CategoryPage(
        title = "Thali",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/thali_food.png",
        category = "THALI",
        priority = 24,
        isActive = true
    )

    object Chicken : CategoryPage(
        title = "Chicken",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chicken_food.png",
        category = "CHICKEN",
        priority = 25,
        isActive = true
    )

    object VegMeal : CategoryPage(
        title = "Veg Meal",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/veg_meal_food.png",
        category = "VEG_MEAL",
        priority = 26,
        isActive = true
    )

    object NorthIndian : CategoryPage(
        title = "North Indian",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/north_indian_food.png",
        category = "NORTH_INDIAN",
        priority = 27,
        isActive = true
    )

    object Paneer : CategoryPage(
        title = "Paneer",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/paneer_food.png",
        category = "PANEER",
        priority = 28,
        isActive = true
    )

    object FriedRice : CategoryPage(
        title = "Fried Rice",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/fried_rice_food.png",
        category = "FRIED_RICE",
        priority = 29,
        isActive = true
    )

    object Noodles : CategoryPage(
        title = "Noodles",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/noodles_food.png",
        category = "NOODLES",
        priority = 30,
        isActive = true
    )

    object Paratha : CategoryPage(
        title = "Paratha",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/paratha_food.png",
        category = "PARATHA",
        priority = 31,
        isActive = true
    )

    object Shawarma : CategoryPage(
        title = "Shawarma",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/shawarma_food.png",
        category = "SHAWARMA",
        priority = 32,
        isActive = true
    )

    object SouthIndian : CategoryPage(
        title = "South Indian",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/south_indian_food.png",
        category = "SOUTH_INDIAN",
        priority = 33,
        isActive = true
    )

    object AlooTikki : CategoryPage(
        title = "Aloo Tikki",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/aloo_tikki_food.png",
        category = "ALOO_TIKKI",
        priority = 34,
        isActive = true
    )

    object Pasta : CategoryPage(
        title = "Pasta",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pasta_food.png",
        category = "PASTA",
        priority = 35,
        isActive = true
    )

    object Pastry : CategoryPage(
        title = "Pastry",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pastry_food.png",
        category = "PASTRY",
        priority = 36,
        isActive = true
    )

    object PavBhaji : CategoryPage(
        title = "Pav Bhaji",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pav_bhaji_food.png",
        category = "PAV_BHAJI",
        priority = 37,
        isActive = true
    )

    object Sandwich : CategoryPage(
        title = "Sandwich",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sandwich_food.png",
        category = "SANDWICH",
        priority = 38,
        isActive = true
    )

    object Shake : CategoryPage(
        title = "Shake",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/shake_food.png",
        category = "SHAKE",
        priority = 39,
        isActive = true
    )

    object Samosa : CategoryPage(
        title = "Samosa",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/samosa_food.png",
        category = "SAMOSA",
        priority = 40,
        isActive = true
    )

    object Poori : CategoryPage(
        title = "Poori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/poori_food.png",
        category = "POORI",
        priority = 41,
        isActive = true
    )

    object Bowl : CategoryPage(
        title = "Bowl",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bowl_food.png",
        category = "BOWL",
        priority = 42,
        isActive = true
    )

    object Poha : CategoryPage(
        title = "Poha",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/poha_food.png",
        category = "POHA",
        priority = 43,
        isActive = true
    )

    // New categories from the list
    object Sweets : CategoryPage(
        title = "Sweets",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sweets_food.png",
        category = "SWEETS",
        priority = 44,
        isActive = true
    )

    object CholePoori : CategoryPage(
        title = "Chole Poori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chole_poori_food.png",
        category = "CHOLE_POORI",
        priority = 45,
        isActive = true
    )

    object Khichdi : CategoryPage(
        title = "Khichdi",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/khichdi_food.png",
        category = "KHICHDI",
        priority = 46,
        isActive = true
    )

    object ChilliChicken : CategoryPage(
        title = "Chilli Chicken",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chilli_chicken_food.png",
        category = "CHILLI_CHICKEN",
        priority = 47,
        isActive = true
    )

    object Tea : CategoryPage(
        title = "Tea",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/tea_food.png",
        category = "TEA",
        priority = 48,
        isActive = true
    )

    object VadaPav : CategoryPage(
        title = "Vada Pav",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/vada_pav_food.png",
        category = "VADA_PAV",
        priority = 49,
        isActive = true
    )

    object MasalaMaggi : CategoryPage(
        title = "Masala Maggi",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/masala_maggi_food.png",
        category = "MASALA_MAGGI",
        priority = 50,
        isActive = true
    )

    object Kulche : CategoryPage(
        title = "Kulche",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/kulche_food.png",
        category = "KULCHE",
        priority = 51,
        isActive = true
    )

    object Wings : CategoryPage(
        title = "Wings",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/wings_food.png",
        category = "WINGS",
        priority = 52,
        isActive = true
    )

    object AlooPoori : CategoryPage(
        title = "Aloo Poori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/aloo_poori_food.png",
        category = "ALOO_POORI",
        priority = 53,
        isActive = true
    )

    object Omelette : CategoryPage(
        title = "Omelette",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/omelette_food.png",
        category = "OMELETTE",
        priority = 54,
        isActive = true
    )

    object NonVegMeal : CategoryPage(
        title = "Non Veg Meal",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/non_veg_meal_food.png",
        category = "NON_VEG_MEAL",
        priority = 55,
        isActive = true
    )

    object BreadPakoda : CategoryPage(
        title = "Bread Pakoda",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bread_pakoda_food.png",
        category = "BREAD_PAKODA",
        priority = 56,
        isActive = true
    )

    object Coffee : CategoryPage(
        title = "Coffee",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/coffee_food.png",
        category = "COFFEE",
        priority = 57,
        isActive = true
    )

    object PooriBhaji : CategoryPage(
        title = "Poori Bhaji",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/poori_bhaji_food.png",
        category = "POORI_BHAJI",
        priority = 58,
        isActive = true
    )

    object Pulao : CategoryPage(
        title = "Pulao",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pulao_food.png",
        category = "PULAO",
        priority = 59,
        isActive = true
    )

    object ChurChurNaan : CategoryPage(
        title = "Chur Chur Naan",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chur_chur_naan_food.png",
        category = "CHUR_CHUR_NAAN",
        priority = 60,
        isActive = true
    )

    object Kebabs : CategoryPage(
        title = "Kebabs",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/kebabs_food.png",
        category = "KEBABS",
        priority = 61,
        isActive = true
    )

    object Panipuri : CategoryPage(
        title = "Panipuri",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/panipuri_food.png",
        category = "PANIPURI",
        priority = 62,
        isActive = true
    )

    object Rasmalai : CategoryPage(
        title = "Rasmalai",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/rasmalai_food.png",
        category = "RASMALAI",
        priority = 63,
        isActive = true
    )

    object Mutton : CategoryPage(
        title = "Mutton",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/mutton_food.png",
        category = "MUTTON",
        priority = 64,
        isActive = true
    )

    object Fish : CategoryPage(
        title = "Fish",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/fish_food.png",
        category = "FISH",
        priority = 65,
        isActive = true
    )

    object Pakoda : CategoryPage(
        title = "Pakoda",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pakoda_food.png",
        category = "PAKODA",
        priority = 66,
        isActive = true
    )

    object Halwa : CategoryPage(
        title = "Halwa",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/halwa_food.png",
        category = "HALWA",
        priority = 67,
        isActive = true
    )

    object ChopSuey : CategoryPage(
        title = "Chop Suey",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chop_suey_food.png",
        category = "CHOP_SUEY",
        priority = 68,
        isActive = true
    )

    object Korma : CategoryPage(
        title = "Korma",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/korma_food.png",
        category = "KORMA",
        priority = 69,
        isActive = true
    )

    object Namkeen : CategoryPage(
        title = "Namkeen",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/namkeen_food.png",
        category = "NAMKEEN",
        priority = 70,
        isActive = true
    )

    object Mushrooms : CategoryPage(
        title = "Mushrooms",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/mushrooms_food.png",
        category = "MUSHROOMS",
        priority = 71,
        isActive = true
    )

    object Keema : CategoryPage(
        title = "Keema",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/keema_food.png",
        category = "KEEMA",
        priority = 72,
        isActive = true
    )

    object Sundae : CategoryPage(
        title = "Sundae",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sundae_food.png",
        category = "SUNDAE",
        priority = 73,
        isActive = true
    )

    object Rasgulla : CategoryPage(
        title = "Rasgulla",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/rasgulla_food.png",
        category = "RASGULLA",
        priority = 74,
        isActive = true
    )

    object ButterChicken : CategoryPage(
        title = "Butter Chicken",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/butter_chicken_food.png",
        category = "BUTTER_CHICKEN",
        priority = 75,
        isActive = true
    )

    object RajKachori : CategoryPage(
        title = "Raj Kachori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/raj_kachori_food.png",
        category = "RAJ_KACHORI",
        priority = 76,
        isActive = true
    )

    object Chaat : CategoryPage(
        title = "Chaat",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chaat_food.png",
        category = "CHAAT",
        priority = 77,
        isActive = true
    )

    object Uttapam : CategoryPage(
        title = "Uttapam",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/uttapam_food.png",
        category = "UTTAPAM",
        priority = 78,
        isActive = true
    )

    object Doughnut : CategoryPage(
        title = "Doughnut",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/doughnut_food.png",
        category = "DOUGHNUT",
        priority = 79,
        isActive = true
    )

    object Juice : CategoryPage(
        title = "Juice",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/juice_food.png",
        category = "JUICE",
        priority = 80,
        isActive = true
    )

    object Lassi : CategoryPage(
        title = "Lassi",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/lassi_food.png",
        category = "LASSI",
        priority = 81,
        isActive = true
    )

    object MalaiKofta : CategoryPage(
        title = "Malai Kofta",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/malai_kofta_food.png",
        category = "MALAI_KOFTA",
        priority = 82,
        isActive = true
    )

    object DahiBalle : CategoryPage(
        title = "Dahi Balle",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dahi_balle_food.png",
        category = "DAHI_BALLE",
        priority = 83,
        isActive = true
    )

    object Rajma : CategoryPage(
        title = "Rajma",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/rajma_food.png",
        category = "RAJMA",
        priority = 84,
        isActive = true
    )

    object ChickenHandi : CategoryPage(
        title = "Chicken Handi",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chicken_handi_food.png",
        category = "CHICKEN_HANDI",
        priority = 85,
        isActive = true
    )

    object Cupcake : CategoryPage(
        title = "Cupcake",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cupcake_food.png",
        category = "CUPCAKE",
        priority = 86,
        isActive = true
    )

    object Bhel : CategoryPage(
        title = "Bhel",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bhel_food.png",
        category = "BHEL",
        priority = 87,
        isActive = true
    )

    object Muffin : CategoryPage(
        title = "Muffin",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/muffin_food.png",
        category = "MUFFIN",
        priority = 88,
        isActive = true
    )

    object Cookies : CategoryPage(
        title = "Cookies",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cookies_food.png",
        category = "COOKIES",
        priority = 89,
        isActive = true
    )

    object ChickenCha : CategoryPage(
        title = "Chicken Cha",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chicken_cha_food.png",
        category = "CHICKEN_CHA",
        priority = 90,
        isActive = true
    )

    object PaneerKulche : CategoryPage(
        title = "Paneer Kulche",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/paneer_kulche_food.png",
        category = "PANEER_KULCHE",
        priority = 91,
        isActive = true
    )

    object Chaach : CategoryPage(
        title = "Chaach",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chaach_food.png",
        category = "CHAACH",
        priority = 92,
        isActive = true
    )

    object VegLollipop : CategoryPage(
        title = "Veg Lollipop",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/veg_lollipop_food.png",
        category = "VEG_LOLLIPOP",
        priority = 93,
        isActive = true
    )

    object Sub : CategoryPage(
        title = "Sub",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sub_food.png",
        category = "SUB",
        priority = 94,
        isActive = true
    )

    object Pancake : CategoryPage(
        title = "Pancake",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pancake_food.png",
        category = "PANCAKE",
        priority = 95,
        isActive = true
    )

    object Nihari : CategoryPage(
        title = "Nihari",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/nihari_food.png",
        category = "NIHARI",
        priority = 96,
        isActive = true
    )

    object Tacos : CategoryPage(
        title = "Tacos",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/tacos_food.png",
        category = "TACOS",
        priority = 97,
        isActive = true
    )

    object Thepla : CategoryPage(
        title = "Thepla",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/thepla_food.png",
        category = "THEPLA",
        priority = 98,
        isActive = true
    )

    object Fafda : CategoryPage(
        title = "Fafda",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/fafda_food.png",
        category = "FAFDA",
        priority = 99,
        isActive = true
    )

    object Chocolate : CategoryPage(
        title = "Chocolate",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chocolate_food.png",
        category = "CHOCOLATE",
        priority = 100,
        isActive = true
    )

    object CurdRice : CategoryPage(
        title = "Curd Rice",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/curd_rice_food.png",
        category = "CURD_RICE",
        priority = 101,
        isActive = true
    )

    object Pudding : CategoryPage(
        title = "Pudding",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pudding_food.png",
        category = "PUDDING",
        priority = 102,
        isActive = true
    )

    object Croissant : CategoryPage(
        title = "Croissant",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/croissant_food.png",
        category = "CROISSANT",
        priority = 103,
        isActive = true
    )

    object Khandvi : CategoryPage(
        title = "Khandvi",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/khandvi_food.png",
        category = "KHANDVI",
        priority = 104,
        isActive = true
    )

    object Gajak : CategoryPage(
        title = "Gajak",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/gajak_food.png",
        category = "GAJAK",
        priority = 105,
        isActive = true
    )

    object SambarRice : CategoryPage(
        title = "Sambar Rice",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sambar_rice_food.png",
        category = "SAMBAR_RICE",
        priority = 106,
        isActive = true
    )

    object Tart : CategoryPage(
        title = "Tart",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/tart_food.png",
        category = "TART",
        priority = 107,
        isActive = true
    )

    object Tiramisu : CategoryPage(
        title = "Tiramisu",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/tiramisu_food.png",
        category = "TIRAMISU",
        priority = 108,
        isActive = true
    )

    object Pie : CategoryPage(
        title = "Pie",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pie_food.png",
        category = "PIE",
        priority = 109,
        isActive = true
    )

    object Custard : CategoryPage(
        title = "Custard",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/custard_food.png",
        category = "CUSTARD",
        priority = 110,
        isActive = true
    )

    object SevPoori : CategoryPage(
        title = "Sev Poori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/sev_poori_food.png",
        category = "SEV_POORI",
        priority = 111,
        isActive = true
    )

    object Mousse : CategoryPage(
        title = "Mousse",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/mousse_food.png",
        category = "MOUSSE",
        priority = 112,
        isActive = true
    )

    object DalKachori : CategoryPage(
        title = "Dal Kachori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dal_kachori_food.png",
        category = "DAL_KACHORI",
        priority = 113,
        isActive = true
    )

    object Jalebi : CategoryPage(
        title = "Jalebi",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/jalebi_food.png",
        category = "JALEBI",
        priority = 114,
        isActive = true
    )

    object PyaajKachori : CategoryPage(
        title = "Pyaaj Kachori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/pyaaj_kachori_food.png",
        category = "PYAAJ_KACHORI",
        priority = 115,
        isActive = true
    )

    object RajmaRice : CategoryPage(
        title = "Rajma Rice",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/rajma_rice_food.png",
        category = "RAJMA_RICE",
        priority = 116,
        isActive = true
    )

    object Upma : CategoryPage(
        title = "Upma",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/upma_food.png",
        category = "UPMA",
        priority = 117,
        isActive = true
    )

    object Manchurian : CategoryPage(
        title = "Manchurian",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/manchurian_food.png",
        category = "MANCHURIAN",
        priority = 118,
        isActive = true
    )

    object PaneerPakoda : CategoryPage(
        title = "Paneer Pakoda",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/paneer_pakoda_food.png",
        category = "PANEER_PAKODA",
        priority = 119,
        isActive = true
    )

    object Cheesecake : CategoryPage(
        title = "Cheesecake",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cheesecake_food.png",
        category = "CHEESECAKE",
        priority = 120,
        isActive = true
    )

    object Brownie : CategoryPage(
        title = "Brownie",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/brownie_food.png",
        category = "BROWNIE",
        priority = 121,
        isActive = true
    )

    object Chaap : CategoryPage(
        title = "Chaap",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chaap_food.png",
        category = "CHAAP",
        priority = 122,
        isActive = true
    )

    object Dal : CategoryPage(
        title = "Dal",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/dal_food.png",
        category = "DAL",
        priority = 123,
        isActive = true
    )

    object Waffles : CategoryPage(
        title = "Waffles",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/waffles_food.png",
        category = "WAFFLES",
        priority = 124,
        isActive = true
    )

    object AlooKachori : CategoryPage(
        title = "Aloo Kachori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/aloo_kachori_food.png",
        category = "ALOO_KACHORI",
        priority = 125,
        isActive = true
    )

    object CholeKulche : CategoryPage(
        title = "Chole Kulche",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/chole_kulche_food.png",
        category = "CHOLE_KULCHE",
        priority = 126,
        isActive = true
    )

    object Fries : CategoryPage(
        title = "Fries",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/fries_food.png",
        category = "FRIES",
        priority = 127,
        isActive = true
    )

    object ColdCoffee : CategoryPage(
        title = "Cold Coffee",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/cold_coffee_food.png",
        category = "COLD_COFFEE",
        priority = 128,
        isActive = true
    )

    object Soup : CategoryPage(
        title = "Soup",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/soup_food.png",
        category = "SOUP",
        priority = 129,
        isActive = true
    )

    object Bhurji : CategoryPage(
        title = "Bhurji",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/bhurji_food.png",
        category = "BHURJI",
        priority = 130,
        isActive = true
    )

    object KhastaKachori : CategoryPage(
        title = "Khasta Kachori",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/khasta_kachori_food.png",
        category = "KHASTA_KACHORI",
        priority = 131,
        isActive = true
    )

    object HotDog : CategoryPage(
        title = "Hot Dog",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/hot_dog_food.png",
        category = "HOT_DOG",
        priority = 132,
        isActive = true
    )

    object SeeAll : CategoryPage(
        title = "See All",
        iconUrl = "${NetworkConfig.BASE_URL}/assets/banners/see_all_food.png",
        category = "SEE_ALL",
        priority = 999,
        isActive = true
    )
}