package org.example.smartmeal.model.recipe

// Plik ten zawierać będzie coś co nazywa się heurystycznym tłumaczeniem wszystkich elementów, które nie zawierają się w API, albo  trzeba je bezpośrednio przetłumaczyć

private val AreasTranslations: Map<String, String> = mapOf(
    // Prywatna zmienna tłumacząca angielskie nazwy regionów na polskie, które przypiszemy do naszej karty przepisu

    "Polish" to "Polska",
    "Italian" to "Włoska",
    "British" to "Brytyjska",
    "France" to "Francuska",
    "Chinese" to "Chińska",
    "Mexican" to "Meksykańska",
    "India" to "Indyjska",
    "Thai" to "Tajska",
    "Japanese" to "Japońska",
    "Greek" to "Grecka",
    "Spanish" to "Hiszpańska",
    "United States" to "Amerykańska",
    "Turkish" to "Turecka",
    "Vietnamese" to "Wietnamska",
    "Moroccan" to "Marokańska",
    "Croatian" to "Chorwacka",
    "Dutch" to "Holenderska",
    "Egyptian" to "Egipska",
    "Jamaican" to "Jamajska",
    "Kenyan" to "Kenijska",
    "Malaysian" to "Malezyjska",
    "Russian" to "Rosyjska",
    "Tunisian" to "Tunezyjska",
    "Ukrainian" to "Ukraińska",
    "Uruguayan" to "Urugwajska",
    "Portuguese" to "Portugalska",
    "Canadian" to "Kanadyjska",
    "Filipino" to "Filipińska",
    "Syrian" to "Syryjska",
    "Australian" to "Australijska",
    "Netherlands" to "Holenderska",
    "Irish" to "Irlandzka",
    "Norway" to "Norweska",
    "Slovakia" to "Słowacka",
    "Argentina" to "Argentyńska"
)

private val CategoriesTranslations: Map<String, String> = mapOf(
    "Breakfast" to "Śniadanie",
    "Dessert" to "Kolacja",
    "Starter" to "Obiad",
    "Side" to "Obiad",
    "Vegan" to "Obiad",
    "Vegetarian" to "Obiad"
)

private val CaloriesTranslations: Map<String, Int> =
    mapOf( // Dodanie funkcjonalności wyświetlenie kaloryki
        "Śniadanie" to 350,
        "Obiad" to 600,
        "Kolacja" to 480
    )

fun translationsArea(apiArea: String?): String =
    AreasTranslations[apiArea] ?: apiArea ?: "Międzynarodowa"

fun mapTranslations(apiCategory: String?): String =
    CategoriesTranslations[apiCategory] ?: "Obiad"

fun caloriesTranslations(mealTime: String): String {
    val base = CaloriesTranslations[mealTime] ?: 500
    return "${base + (-50..50).random()} kcal"
}

fun timeTranslations(): String = "${listOf(15,20,25,30,40).random()} minut"


fun MealDto.toRecipe(): Recipe {

    val mealTime = mapTranslations(category)

    return Recipe(
        id = id,
        title = title,
        category = mealTime,
        type = translationsArea(area),
        hasImage = thumbnailUrl != null,
        imageUrl = thumbnailUrl,
        calories = caloriesTranslations(mealTime),
        time = timeTranslations()
    )
}