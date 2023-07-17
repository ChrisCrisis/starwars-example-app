package dev.glimmr.starwarssample.data.client

import dev.glimmr.starwarssample.data.model.retrofit.StarshipPage
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApiClient {

    @GET("starships/")
    fun getAllShips(): Call<StarshipPage>
}