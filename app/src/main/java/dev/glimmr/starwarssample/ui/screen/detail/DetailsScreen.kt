package dev.glimmr.starwarssample.ui.screen.detail

import StarWarsLoadingIndicator
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun ShipDetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackButtonClicked: () -> Unit
) {
    ShipDetailsScreenContent(
        state = viewModel.getUiState(),
        onBackButtonClicked = onBackButtonClicked,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShipDetailsScreenContent(
    state: State<ShipDetailState>,
    onBackButtonClicked: () -> Unit = {}
) {
    when (val uiState = state.value){
        is ShipDetailState.Loading -> StarWarsLoadingIndicator()
        is ShipDetailState.Ready -> Scaffold(
        topBar = {
            StarWarsAppbar(title = uiState.starship.name){
                IconButton(onClick = onBackButtonClicked) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
        ) {
            ShipInformationLayout(
                starship = uiState.starship,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
fun ShipInformationLayout(
    starship: Starship,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 24.dp,
                horizontal = 24.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShipStatInfo(
            label = stringResource(R.string.detail_model_label),
            value = starship.model
        )
        ShipStatInfo(
            label = stringResource(R.string.detail_class_label),
            value = starship.shipClass
        )
        ShipStatInfo(
            label = stringResource(R.string.detail_manufacturer_label),
            value = starship.manufacturer
        )
        ShipStatInfo(
            label = stringResource(R.string.detail_atmospeed_label),
            value = starship.maxAtmosphereSpeed ?: "unknown"
        )
        ShipStatInfo(
            label = stringResource(R.string.detail_cost_label),
            value = starship.cost
        )
    }
}

@Composable
fun ShipStatInfo(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//region Previews

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShipDetailsScreenPreview() {
    StarWarsSampleTheme {
        ShipDetailsScreenContent(
            state = mutableStateOf(
                ShipDetailState.Ready(
                    Starship(
                        "testID",
                        "Test Name",
                        "Test Model",
                        "Test Company",
                        "13370815",
                        "1337",
                        "Test Class",
                    )
                )
            ),
        )
    }
}

//endregion