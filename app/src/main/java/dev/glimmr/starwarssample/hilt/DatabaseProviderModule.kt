package dev.glimmr.starwarssample.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.glimmr.starwarssample.data.client.AppDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseProviderModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ) = AppDatabase.createDatabase(appContext)

    @Provides
    fun getStarshipDao(db: AppDatabase) = db.starshipDao()
}