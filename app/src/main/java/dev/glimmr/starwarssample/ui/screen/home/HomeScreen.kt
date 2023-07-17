package dev.glimmr.starwarssample.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.glimmr.starwarssample.R
import dev.glimmr.starwarssample.data.model.Starship
import dev.glimmr.starwarssample.ui.shared.StarWarsAppbar
import dev.glimmr.starwarssample.ui.theme.StarWarsSampleTheme
import dev.glimmr.starwarssample.ui.theme.extension.cardSubTitle
import dev.glimmr.starwarssample.ui.theme.extension.cardTitle

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
    Scaffold (
        topBar = {
            StarWarsAppbar(title = stringResource(id = R.string.home_appbar_label))
        }
    ){ paddingValues ->
        when(uiState) {
            is HomeScreenState.Loading -> Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //TODO
                // not implemented for simplicity
                // replace with proper loading indicator
                Text(text = stringResource(id = R.string.home_content_loading))
            }
            is HomeScreenState.Ready -> StarshipList(
                starships = uiState.starships,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun StarshipList(
    starships: List<Starship>,
    modifier: Modifier = Modifier,
) {
    if(starships.isEmpty()){
        NoShipFoundCard(modifier = modifier)
    } else {
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(all = 24.dp),
            modifier = modifier
        ){
            item {

                Text(
                    text = stringResource(id = R.string.home_label_starship),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
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
            text = stringResource(id = R.string.home_label_list_empty),
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
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = ship.name,
                style = MaterialTheme.typography.cardTitle
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.home_card_model),
                    style = MaterialTheme.typography.cardSubTitle
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = ship.model,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.home_card_price),
                    style = MaterialTheme.typography.cardSubTitle
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = ship.cost_in_credits ?: stringResource(id = R.string.home_card_price_unknown),
                )
            }
        }
    }
}

//region Previews
@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenContentPreviewEmpty() {
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
fun HomeScreenContentPreview() {
    StarWarsSampleTheme {
        HomeScreenContent(
            homeScreenState = mutableStateOf(
                HomeScreenState.Ready(
                    starships = List(size = 5){
                        Starship(
                            "TestShip $it",
                            "TestModel",
                            "",
                            "1337",
                            "",
                            "",
                        )
                    }
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