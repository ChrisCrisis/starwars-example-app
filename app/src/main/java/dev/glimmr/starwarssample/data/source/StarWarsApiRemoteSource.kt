package dev.glimmr.starwarssample.data.source

import dev.glimmr.starwarssample.data.model.Starship
import javax.inject.Inject

interface StarWarsApiRemoteSource {
    suspend fun getAllStarships(): List<Starship>
}

class StarWarsApiRemoteSourceImpl @Inject constructor(): StarWarsApiRemoteSource {
    override suspend fun getAllStarships(): List<Starship> {
        return List(size = 100){
            Starship(
                "TestName $it",
                "",
                "",
                "",
                "",
                "",
            )
        }
    }

}