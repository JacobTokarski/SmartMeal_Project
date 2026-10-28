package org.example.smartmeal.ui.views.own

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.example.smartmeal.data.repository.cutlery.DietPlanRepository
import org.example.smartmeal.data.repository.home.CustomRecipe
import org.example.smartmeal.data.repository.home.RecipeRepository
import org.example.smartmeal.model.selection.RecipeReference
import org.example.smartmeal.model.selection.RecipeSource
import kotlin.time.Clock

data class OwnRecipeUIState(
    val recipes: List<CustomRecipe> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isFormVisible: Boolean = false,
    val editRecipe: CustomRecipe? = null,
    val recipePendingDelete: CustomRecipe? = null,
)

class OwnViewModel: ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _isFormVisible = MutableStateFlow(false)
    private val _editingRecipe = MutableStateFlow<CustomRecipe?>(null)
    private val _recipePendingDelete = MutableStateFlow<CustomRecipe?>(null)

    val uiState: StateFlow<OwnRecipeUIState> = combine(
        snapshotFlow { RecipeRepository.userRecipes.toList() },
        _searchQuery,
        _isFormVisible,
        _editingRecipe,
        _recipePendingDelete,
    ) { recipes, query, formVisible, editing, pendingDelete ->
        OwnRecipeUIState(
            recipes = recipes,
            searchQuery = query,
            isFormVisible = formVisible,
            editRecipe = editing,
            recipePendingDelete = pendingDelete,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OwnRecipeUIState())

    fun onSearchQuery(newQuery: String) { _searchQuery.value = newQuery }

    fun onAddClick() {
        _editingRecipe.value = null
        _isFormVisible.value = true
    }

    fun onEditClick(recipe: CustomRecipe) {
        _editingRecipe.value = recipe
        _isFormVisible.value = true
    }

    fun onCloseForm() {
        _isFormVisible.value = false
        _editingRecipe.value = null
    }

    fun onSaveRecipe(draft: CustomRecipe) {
        val editing = _editingRecipe.value
        if (editing != null) {
            RecipeRepository.updateRecipe(draft.copy(id = editing.id, isFavorite = editing.isFavorite))
        } else {
            RecipeRepository.addRecipe(
                draft.copy(id = Clock.System.now().toEpochMilliseconds().toString())
            )
        }
        onCloseForm()
    }

    fun onDeleteClick(recipe: CustomRecipe) { _recipePendingDelete.value = recipe }

    fun onDismissDelete() { _recipePendingDelete.value = null }

    fun onConfirmDelete() {
        _recipePendingDelete.value?.let { recipe ->
            RecipeRepository.removeRecipe(recipe.id)
            DietPlanRepository.removeReferencesTo(RecipeReference(recipe.id, RecipeSource.OWN))
        }
        _recipePendingDelete.value = null
    }
}