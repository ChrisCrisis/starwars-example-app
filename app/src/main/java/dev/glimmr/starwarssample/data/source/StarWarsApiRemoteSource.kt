package dev.glimmr.starwarssample.data.source

import dev.glimmr.starwarssample.data.model.Starship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface StarWarsApiRemoteSource {
    fun getAllStarships(): Flow<List<Starship>>
}

class StarWarsApiRemoteSourceImpl: StarWarsApiRemoteSource {
    override fun getAllStarships(): Flow<List<Starship>> {
        return flowOf(
            listOf(
                Starship( //TODO get from web instead
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                )
            )
        )
    }

}