package dev.glimmr.starwarssample.data.repository

import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.source.StarWarsApiRemoteSource
import dev.glimmr.starwarssample.data.source.StarshipLocalSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface StarshipRepository {
    fun getStarships(): Flow<List<Starship>>
    fun getStarshipBy(shipId: String): Flow<Starship>
}

class StarshipRepositoryImpl @Inject constructor(
    private val remoteSource: StarWarsApiRemoteSource,
    private val localSource: StarshipLocalSource,
): StarshipRepository {
    override fun getStarships(): Flow<List<Starship>> {
        CoroutineScope(Dispatchers.IO).launch {
            val data = remoteSource.getAllStarships()
            localSource.storeShips(data)
        }

        return localSource.getAllStarships()
    }

    override fun getStarshipBy(shipId: String): Flow<Starship> {
        CoroutineScope(Dispatchers.IO).launch {
            val data = remoteSource.getStarshipBy(shipId)
            localSource.storeShip(data)
        }
        return localSource.getStarshipBy(shipId)
    }
}