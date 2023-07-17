package dev.glimmr.starwarssample.ui.screen.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.data.repository.StarshipRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: StarshipRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val shipId: String = savedStateHandle["shipId"] ?: throw IllegalStateException(
        "Cannot instantiate viewModel without an id"
    )

    private var uiState: MutableState<ShipDetailState> = mutableStateOf(ShipDetailState.Loading)

    private fun refreshUiData() {
        viewModelScope.launch {
            repository.getStarshipBy(shipId).collectLatest {
                uiState.value = ShipDetailState.Ready(it)
            }
        }
    }

    fun getUiState(): State<ShipDetailState> {
        refreshUiData()
        return uiState
    }
}

sealed class ShipDetailState {
    object Loading: ShipDetailState()
    class Ready(
        val starship: Starship
    ): ShipDetailState()
}