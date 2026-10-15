package org.example.smartmeal.model.preset

import org.jetbrains.compose.resources.DrawableResource
import smartmeal_project.composeapp.generated.resources.Res
import smartmeal_project.composeapp.generated.resources.preset_bruschetta
import smartmeal_project.composeapp.generated.resources.preset_fruits
import smartmeal_project.composeapp.generated.resources.preset_hamburger
import smartmeal_project.composeapp.generated.resources.preset_meat
import smartmeal_project.composeapp.generated.resources.preset_pancakes
import smartmeal_project.composeapp.generated.resources.preset_pizza
import smartmeal_project.composeapp.generated.resources.preset_salad
import smartmeal_project.composeapp.generated.resources.preset_smoothie
import smartmeal_project.composeapp.generated.resources.preset_soup
import smartmeal_project.composeapp.generated.resources.preset_steak
import smartmeal_project.composeapp.generated.resources.preset_toast
import smartmeal_project.composeapp.generated.resources.preset_vege

object PresetImages {

    private const val PREFIX = "preset:"

    data class Preset(val key: String, val label: String, val resource: DrawableResource) {
        val url: String get() = PREFIX + key
    }

    val all: List<Preset> = listOf(
        Preset("zupa", "Zupa", Res.drawable.preset_soup),
        Preset("stek", "Dania mięsne", Res.drawable.preset_steak),
        Preset("bruschetta", "Przystawka", Res.drawable.preset_bruschetta),
        Preset("owoce", "Deser owocowy", Res.drawable.preset_fruits),
        Preset("sałatka", "Sałatka", Res.drawable.preset_salad),
        Preset("pizza", "Pizza", Res.drawable.preset_pizza),
        Preset("hamburger", "Burger / Fast food", Res.drawable.preset_hamburger),
        Preset("tost", "Tosty / kanapki", Res.drawable.preset_toast),
        Preset("smoothie", "Koktajl", Res.drawable.preset_smoothie),
        Preset("mięsne ciasto", "Zapiekanka / wytrawne", Res.drawable.preset_meat),
        Preset("warzywa", "Danie wege", Res.drawable.preset_vege),
        Preset("pancakes", "Naleśniki / Pancakes", Res.drawable.preset_pancakes)
    )

    fun fromUrl(url: String?): Preset? =
        if (url != null && url.startsWith(PREFIX)) all.find { it.key == url.removePrefix(PREFIX) } else null
}