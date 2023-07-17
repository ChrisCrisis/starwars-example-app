package dev.glimmr.starwarssample.data.source

import dev.glimmr.starwarssample.data.client.StarWarsApiClient
import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.model.retrofit.toInternalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface StarWarsApiRemoteSource {
    suspend fun getAllStarships(): List<Starship>
    suspend fun getStarshipBy(shipId: String): Starship
}

class StarWarsApiRemoteSourceImpl @Inject constructor(
    private val starWarsApiClient: StarWarsApiClient
): StarWarsApiRemoteSource {
    override suspend fun getAllStarships(): List<Starship> = withContext(Dispatchers.IO) {
        val response = starWarsApiClient.getAllShips().execute()
        return@withContext response.body()?.results?.map {
            it.toInternalModel()
        } ?: emptyList()
    }

    override suspend fun getStarshipBy(shipId: String): Starship = withContext(Dispatchers.IO){
        val response = starWarsApiClient.getShipBy(shipId).execute()
        return@withContext response.body()?.toInternalModel() ?: throw IllegalArgumentException(
            "No ship for id=$shipId found"
        )
    }
}
