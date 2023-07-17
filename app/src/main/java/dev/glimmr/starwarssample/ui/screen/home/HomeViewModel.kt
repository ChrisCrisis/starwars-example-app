package dev.glimmr.starwarssample.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.repository.StarshipRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val starshipRepository: StarshipRepository,
): ViewModel() {
    private var uiState: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState.Loading)

    private fun refreshUiData() {
        viewModelScope.launch {
            starshipRepository.getStarships().collectLatest {
                uiState.value = HomeScreenState.Ready(
                    starships = it
                )
            }
        }
    }

    fun getUiState(): State<HomeScreenState>{
        refreshUiData()
        return uiState
    }
}

sealed class HomeScreenState {
    object Loading: HomeScreenState()
    class Ready(
        val starships: List<Starship>
    ): HomeScreenState()
}