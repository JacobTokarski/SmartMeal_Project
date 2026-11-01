package org.example.smartmeal.data.remote.cloud

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.example.smartmeal.data.repository.home.CustomRecipe
import org.example.smartmeal.model.health.ActivityLevel
import org.example.smartmeal.model.health.BmiEntry
import org.example.smartmeal.model.health.Gender
import org.example.smartmeal.model.health.TdeeEntry

@Serializable
data class RecipeDoc(
    val id: String = "",
    val title: String = "",
    val category: String = "",
    val type: String = "",
    val imageUrl: String? = null,
    val calories: String = "",
    val time: String = "",
    val isFavorite: Boolean = false
)

fun CustomRecipe.toDoc() = RecipeDoc(
    id = id,
    title = title,
    category = category,
    type = type,
    imageUrl = imageUrl,
    calories = calories,
    time = time,
    isFavorite = isFavorite
)

fun RecipeDoc.toDomain() = CustomRecipe(
    id = id,
    title = title,
    category = category,
    type = type,
    imageUrl = imageUrl,
    calories = calories,
    time = time,
    isFavorite = isFavorite
)

@Serializable
data class FavoritesDoc(val ids: List<String> = emptyList())

@Serializable
data class MealPlanDoc(
    val key: String = "",
    val date: String = "",
    val meal: String = "",
    val recipeId: String = "",
    val source: String = "",
)

@Serializable
data class BmiDoc(
    val id: String = "",
    val date: String = "",
    val weightKg: Double = 0.0,
    val heightCm: Double = 0.0,
    val bmi: Double = 0.0,
    val category: String = "",
)

fun BmiEntry.toDoc() = BmiDoc(
    id = id,
    date = date.toString(),
    weightKg = weightKg,
    heightCm = heightCm,
    bmi = bmi,
    category = category
)

fun BmiDoc.toDomain(): BmiEntry? {
    val parsedDate = runCatching { LocalDate.parse(date) }.getOrNull() ?: return null
    return BmiEntry(id, parsedDate, weightKg, heightCm, bmi, category)
}

@Serializable
data class TdeeDoc(
    val id: String = "",
    val date: String = "",
    val gender: String = "",
    val age: Int = 0,
    val heightCm: Double = 0.0,
    val weightKg: Double = 0.0,
    val activity: String = "",
    val bmr: Double = 0.0,
    val tdee: Double = 0.0,
)

fun TdeeEntry.toDoc() = TdeeDoc(
    id = id,
    date = date.toString(),
    gender = gender.name,
    age = age,
    heightCm = heightCm,
    weightKg = weightKg,
    activity = activity.name,
    bmr = bmr,
    tdee = tdee
)

fun TdeeDoc.toDomain(): TdeeEntry? {
    val parsedDate = runCatching { LocalDate.parse(date) }.getOrNull() ?: return null
    val parsedGender = runCatching { Gender.valueOf(gender) }.getOrNull() ?: return null
    val parsedActivity = runCatching { ActivityLevel.valueOf(activity) }.getOrNull() ?: return null
    return TdeeEntry(id, parsedDate, parsedGender, age, heightCm, weightKg, parsedActivity, bmr, tdee)
}