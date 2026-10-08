package org.example.smartmeal.model.recipe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDbResponse(
    val meals: List<MealDto>? = null
)

@Serializable
data class MealDto(
    @SerialName("idMeal") val id: String,
    @SerialName("strMeal") val title: String,
    @SerialName("strCategory") val category: String? = null,
    @SerialName("strArea") val area: String? = null,
    @SerialName("strMealThumb") val thumbnailUrl: String? = null,
)