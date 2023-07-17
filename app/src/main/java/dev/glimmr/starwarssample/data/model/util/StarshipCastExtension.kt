package dev.glimmr.starwarssample.data.model.util

import dev.glimmr.starwarssample.data.Constants
import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.model.retrofit.RetroStarship
import dev.glimmr.starwarssample.data.model.room.StarshipEntity


fun Starship.toEntity(): StarshipEntity {
    return StarshipEntity(id, name, model, manufacturer,cost, maxAtmosphereSpeed, shipClass)
}

fun StarshipEntity.toInternalModel(): Starship {
    return Starship(
        id = this.id,
        name = this.name,
        model = model,
        manufacturer = manufacturer,
        cost = cost,
        maxAtmosphereSpeed = maxAtmosphereSpeed,
        shipClass = shipClass,
    )
}

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