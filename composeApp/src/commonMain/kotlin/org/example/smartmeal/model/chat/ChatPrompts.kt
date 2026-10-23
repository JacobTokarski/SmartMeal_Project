package org.example.smartmeal.model.chat

data class ChatSuggestion(val label: String, val prompt: String)

object ChatPrompts {
    const val SYSTEM = """
        Jesteś asystentem kulinarnym aplikacji SmartMeal. Odpowiadaj po polsku, zwięźle (do około 120 słów).
        Na podstawie składników lub wymagań użytkownika zaproponuj jedno danie: nazwa, krótka lista składników,
        3-5 kroków przygotowania, szacunkowa kaloryczność i czas. Ograniczenia użytkownika (limit kalorii,
        dieta, wykluczone składniki) są obowiązkowe. Kaloryczność podawaj jako szacunek.
        Nie używaj markdownu (żadnych gwiazdek, nagłówków ani tabel), pisz zwykłym tekstem.
        Odpowiadaj wyłącznie na tematy kulinarne i żywieniowe, w innych sprawach uprzejmie odmów.
        Nie udzielaj porad medycznych ani diet leczniczych, w takich sprawach zasugeruj konsultację ze specjalistą.
    """

    val suggestions = listOf(
        ChatSuggestion("Do 500 kcal", "Zaproponuj danie do 500 kcal"),
        ChatSuggestion("Coś na słodko", "Zaproponuj coś słodkiego"),
        ChatSuggestion("Zostały mi resztki", "Mam w lodówce resztki")
    )
}