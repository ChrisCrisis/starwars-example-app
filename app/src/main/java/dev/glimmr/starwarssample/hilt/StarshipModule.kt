package dev.glimmr.starwarssample.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.glimmr.starwarssample.data.repository.StarshipRepository
import dev.glimmr.starwarssample.data.repository.StarshipRepositoryImpl
import dev.glimmr.starwarssample.data.source.StarWarsApiRemoteSource
import dev.glimmr.starwarssample.data.source.StarWarsApiRemoteSourceImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class StarshipModule {
    @Binds
    abstract fun getStarshipRepo(repo: StarshipRepositoryImpl): StarshipRepository

    @Binds
    abstract fun getStarWarsRemoteSource(source: StarWarsApiRemoteSourceImpl): StarWarsApiRemoteSource
}