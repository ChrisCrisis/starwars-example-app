package dev.glimmr.starwarssample.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.glimmr.starwarssample.data.Constants
import dev.glimmr.starwarssample.data.client.StarWarsApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ViewModelComponent::class)
@Module
class ApiModule {

    @Provides
    fun getRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.STAR_WARS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getStarWarsApiClient(retrofit: Retrofit): StarWarsApiClient = retrofit.create(StarWarsApiClient::class.java)
}