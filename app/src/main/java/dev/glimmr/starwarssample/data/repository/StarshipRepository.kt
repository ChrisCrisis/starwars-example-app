package dev.glimmr.starwarssample.data.repository

import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.source.StarWarsApiRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface StarshipRepository {
    fun getStarship(): Flow<List<Starship>>
}

class StarshipRepositoryImpl @Inject constructor(
    private val remoteSource: StarWarsApiRemoteSource
): StarshipRepository {

    override fun getStarship(): Flow<List<Starship>> {
        return remoteSource.getAllStarships()
    }
}