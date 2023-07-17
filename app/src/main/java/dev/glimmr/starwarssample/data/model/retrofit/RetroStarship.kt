package dev.glimmr.starwarssample.data.model.retrofit

data class RetroStarship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val max_atmosphering_speed: String,
    val starship_class: String,
    val url: String
)