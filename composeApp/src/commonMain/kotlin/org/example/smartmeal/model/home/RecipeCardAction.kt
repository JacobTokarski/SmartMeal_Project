package org.example.smartmeal.model.home

// Plik, który zawiera klasę odpowiadającą za przydzielanie komponentowi odpowiedniego stanu

sealed class RecipeCardAction {

    data class EditDelete( // Karta jest w stanie edycji/usunięcia (ekran Własny)
        val onEditClick: () -> Unit,
        val onDeleteClick: () -> Unit,
    ) : RecipeCardAction()

    data class Selection( // Karta jest w stanie dodawania do przepisu (Cutlery)
        val isSelected: Boolean,
    ) : RecipeCardAction()

    data class Favorite( // Karta jest dodawana do ekranu ulubionych (Favorite)
        val isFavorite: Boolean,
        val onToggle: () -> Unit
    ) : RecipeCardAction()
}