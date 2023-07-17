package dev.glimmr.starwarssample.data.client

import dev.glimmr.starwarssample.data.model.retrofit.RetroStarship
import dev.glimmr.starwarssample.data.model.retrofit.StarshipPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiClient {

    @GET("starships/")
    fun getAllShips(): Call<StarshipPage>

    @GET("starships/{id}")
    fun getShipBy(@Path("id") shipId: String): Call<RetroStarship>
}