package dev.glimmr.starwarssample.data.client

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.glimmr.starwarssample.data.model.room.StarshipDao
import dev.glimmr.starwarssample.data.model.room.StarshipEntity

@Database(entities = [StarshipEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun starshipDao(): StarshipDao

    companion object {
        fun createDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "starwars-db"
            ).build()
        }
    }
}
