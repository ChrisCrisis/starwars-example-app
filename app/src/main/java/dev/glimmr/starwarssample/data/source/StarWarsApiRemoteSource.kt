package dev.glimmr.starwarssample.data.source

import dev.glimmr.starwarssample.data.client.StarWarsApiClient
import dev.glimmr.starwarssample.data.model.Starship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface StarWarsApiRemoteSource {
    suspend fun getAllStarships(): List<Starship>
}

class StarWarsApiRemoteSourceImpl @Inject constructor(
    private val starWarsApiClient: StarWarsApiClient
): StarWarsApiRemoteSource {
    override suspend fun getAllStarships(): List<Starship> = withContext(Dispatchers.IO) {
        val response = starWarsApiClient.getAllShips().execute()
        return@withContext response.body()?.results ?: emptyList()
    }

}