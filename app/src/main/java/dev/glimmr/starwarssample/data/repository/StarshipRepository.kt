package dev.glimmr.starwarssample.data.repository

import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.source.StarWarsApiRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface StarshipRepository {
    fun getStarships(): Flow<List<Starship>>
}

class StarshipRepositoryImpl @Inject constructor(
    private val remoteSource: StarWarsApiRemoteSource
): StarshipRepository {

    override fun getStarships(): Flow<List<Starship>> {
        return flow {
            emit(emptyList())
            emit(remoteSource.getAllStarships())
        }
    }
}