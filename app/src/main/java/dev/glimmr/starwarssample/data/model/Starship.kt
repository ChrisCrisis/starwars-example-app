package dev.glimmr.starwarssample.data.model

data class Starship(
    val id: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost: String,
    val maxAtmosphereSpeed: String?,
    val shipClass: String,
)


