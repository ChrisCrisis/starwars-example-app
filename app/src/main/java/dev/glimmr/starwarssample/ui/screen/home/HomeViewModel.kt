package dev.glimmr.starwarssample.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.glimmr.starwarssample.data.model.Starship
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private var uiState: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState.Loading)

    fun getUiState(): State<HomeScreenState>{
        viewModelScope.launch {
            delay(5000)
            val newState = HomeScreenState.Ready(
                List(size = 100){
                    (
                        Starship(
                            "TestName $it",
                            "",
                            "",
                            "",
                            "",
                            "",
                        )
                    )
                }
            )
            uiState.value = newState
        }

        return uiState
    }
}

sealed class HomeScreenState {
    object Loading: HomeScreenState()
    class Ready(
        val starships: List<Starship>
    ): HomeScreenState()
}