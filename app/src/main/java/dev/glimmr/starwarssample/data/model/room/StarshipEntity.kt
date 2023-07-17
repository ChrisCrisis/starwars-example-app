package dev.glimmr.starwarssample.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = StarshipEntity.TABLE_NAME)
class StarshipEntity (
    @PrimaryKey val id: String,
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost: String,
    val maxAtmosphereSpeed: String?,
    val shipClass: String,
){
    companion object{
        const val TABLE_NAME = "starship"
    }
}