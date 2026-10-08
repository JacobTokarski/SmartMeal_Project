package org.example.smartmeal.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.smartmeal.model.recipe.MealDbResponse

class MealDbApiService(
    private val httpClient: HttpClient
) {
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1"

    suspend fun searchByFirstLetter(letter: Char): MealDbResponse {
        return httpClient.get("$baseUrl/search.php") {
            url { parameters.append("f", letter.toString()) }
        }.body()
    }
}