package dev.glimmr.starwarssample.data.model.retrofit

import dev.glimmr.starwarssample.data.model.Starship

data class StarshipPage(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Starship>,
)
