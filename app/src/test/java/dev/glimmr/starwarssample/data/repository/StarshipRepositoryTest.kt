package dev.glimmr.starwarssample.data.repository

import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.source.StarWarsApiRemoteSource
import dev.glimmr.starwarssample.data.source.StarshipLocalSource
import dev.glimmr.starwarssample.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class StarshipRepositoryTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val mockRemoteSource: StarWarsApiRemoteSource = mock()
    private val mockLocalSource: StarshipLocalSource = mock()

    private lateinit var repo: StarshipRepository

    @Before
    fun setup() {
        repo = StarshipRepositoryImpl(
            mockRemoteSource,
            mockLocalSource,
        )
    }

    @Test
    fun allShips_accesses_client_success() = coroutineRule.runTest {
        val expected = List(size = 3) {
            Starship(
                "$it",
                "name $it",
                "model $it",
                "company $it",
                "1337$it",
                "speed $it",
                "class $it",
            )
        }
        whenever(mockRemoteSource.getAllStarships()).thenReturn(expected)
        whenever(mockLocalSource.getAllStarships()).thenReturn(emptyFlow())

        repo.getStarships()

        verify(mockLocalSource).storeShips(expected)
    }
}