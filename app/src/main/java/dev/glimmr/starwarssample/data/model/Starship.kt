package dev.glimmr.starwarssample.data.model

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String?, //TODO change to Double o. int
    val maxAtmosphericSpeed: String,
    val starship_class: String?,
)
