package dev.glimmr.starwarssample.data.model.retrofit

import dev.glimmr.starwarssample.data.Constants
import dev.glimmr.starwarssample.data.model.Starship

data class RetroStarship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val max_atmosphering_speed: String,
    val starship_class: String,
    val url: String
)

fun RetroStarship.toInternalModel(): Starship {
    val id = this.url
        .removePrefix("${Constants.STAR_WARS_API_BASE_URL}starships/")
        .removeSuffix("/")
    return Starship(
        id = id,
        name = name,
        model = model,
        manufacturer = manufacturer,
        cost = cost_in_credits,
        maxAtmosphereSpeed = max_atmosphering_speed,
        shipClass = starship_class,
    )
}