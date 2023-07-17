package dev.glimmr.starwarssample.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.ui.theme.StarWarsSampleTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreenContent(
        homeScreenState = viewModel.getUiState()
    )
}

@Composable
fun HomeScreenContent(
    homeScreenState: State<HomeScreenState>
) {
    val uiState = homeScreenState.value
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
//                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(uiState) {
                is HomeScreenState.Loading -> Text(text = "loading")
                is HomeScreenState.Ready -> StarshipList(
                    starships = uiState.starships
                )
            }
        }
    }
}

@Composable
fun StarshipList(
    starships: List<Starship>,
    modifier: Modifier = Modifier,
) {
    if(starships.isEmpty()){
        NoShipFoundCard()
    } else {
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(all = 16.dp),
            modifier = modifier
        ){
            items(starships) {starship ->
                StarshipCard(ship = starship)
            }
        }
    }
}

@Composable
fun NoShipFoundCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    )  {
        Text(
            text = "No starship found",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        )
    }
}

@Composable
fun StarshipCard(
    ship: Starship,
    modifier: Modifier =  Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = ship.name)
        Text(text = ship.model)
        Text(text = ship.starshipClass)
        Text(text = ship.manufacturer)
    }
}

//region Previews
@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenContentPreview() {
    StarWarsSampleTheme {
        HomeScreenContent(
            homeScreenState = mutableStateOf(
                HomeScreenState.Ready(
                    starships = emptyList()
                )
            )
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenContentPreviewLoading() {
    StarWarsSampleTheme {
        HomeScreenContent(
            homeScreenState = mutableStateOf(HomeScreenState.Loading)
        )
    }
}
//endregion