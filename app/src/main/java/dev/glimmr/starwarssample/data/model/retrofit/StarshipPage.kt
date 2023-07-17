package dev.glimmr.starwarssample.data.model.retrofit

data class StarshipPage(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RetroStarship>,
)
