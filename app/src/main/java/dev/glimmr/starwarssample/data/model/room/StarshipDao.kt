package dev.glimmr.starwarssample.data.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StarshipDao {

    @Query("SELECT * FROM ${StarshipEntity.TABLE_NAME} ORDER BY id ASC")
    fun getAllStarships(): Flow<List<StarshipEntity>>

    @Query("SELECT * FROM ${StarshipEntity.TABLE_NAME} WHERE id IN (:shipId)")
    fun getStarshipBy(shipId: String): Flow<StarshipEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg ships: StarshipEntity)
}