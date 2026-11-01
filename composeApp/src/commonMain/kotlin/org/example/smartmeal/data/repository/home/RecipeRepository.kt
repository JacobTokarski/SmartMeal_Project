package org.example.smartmeal.data.repository.home

import androidx.compose.runtime.mutableStateListOf
import org.example.smartmeal.data.remote.cloud.CloudScope
import org.example.smartmeal.data.remote.cloud.RecipeDoc
import org.example.smartmeal.data.remote.cloud.UserCloud
import org.example.smartmeal.data.remote.cloud.toDoc
import org.example.smartmeal.data.remote.cloud.toDomain

data class CustomRecipe(
    val id: String,
    val title: String,
    val category: String,
    val type: String,
    val imageUrl: String? = null,
    val calories: String,
    val time: String,
    val isFavorite: Boolean
) {
    val hasImage: Boolean get() = imageUrl != null
}

object RecipeRepository {
    val userRecipes = mutableStateListOf<CustomRecipe>()

    fun addRecipe(recipe: CustomRecipe) {
        userRecipes.add(recipe)
        save(recipe)
    }

    fun updateRecipe(updated: CustomRecipe) {
        val index = userRecipes.indexOfFirst { it.id == updated.id }
        if (index >= 0) {
            userRecipes[index] = updated
            save(updated)
        }
    }

    fun removeRecipe(id: String) {
        userRecipes.removeAll { it.id == id }
        val ref = UserCloud.document("recipes", id)
        CloudScope.write { ref?.delete() }
    }

    private fun save(recipe: CustomRecipe) {
        val ref = UserCloud.document("recipes", recipe.id)
        CloudScope.write {
            ref?.set(RecipeDoc.serializer(), recipe.toDoc()) { encodeDefaults = true }
        }
    }

    suspend fun loadFromCloud() {
        val docs = UserCloud.collection("recipes")?.get()?.documents ?: return
        val loaded = docs.map { it.data(RecipeDoc.serializer()).toDomain() }
        userRecipes.clear()
        userRecipes.addAll(loaded)
    }

    fun clear() = userRecipes.clear()
}