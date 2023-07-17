package dev.glimmr.starwarssample.data.source

import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.model.room.StarshipDao
import dev.glimmr.starwarssample.data.model.util.toEntity
import dev.glimmr.starwarssample.data.model.util.toInternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface StarshipLocalSource {
    fun getAllStarships(): Flow<List<Starship>>

    fun getStarshipBy(shipId: String): Flow<Starship>

    fun storeShips(ships: List<Starship>)
    fun storeShip(ship: Starship) = storeShips(listOf(ship))
}

class StarshipLocalSourceImpl @Inject constructor(
    private val starshipDao: StarshipDao
): StarshipLocalSource {

    override fun getAllStarships(): Flow<List<Starship>> {
        return starshipDao.getAllStarships().map {starships ->
            starships.map { it.toInternalModel() }
        }
    }

    override fun getStarshipBy(shipId: String): Flow<Starship> {
        return  starshipDao.getStarshipBy(shipId).map { it.toInternalModel() }
    }

    override fun storeShips(ships: List<Starship>) {
        val entities = ships.map { it.toEntity() }
        starshipDao.insertAll(*entities.toTypedArray())
    }

}