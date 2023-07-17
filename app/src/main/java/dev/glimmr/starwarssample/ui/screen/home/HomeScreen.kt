package dev.glimmr.starwarssample.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.glimmr.starwarssample.ui.theme.StarWarsSampleTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    Scaffold {
        Box(modifier = Modifier.padding(it)){
            Text(text = "Test")
        }
    }
}

//region Previews
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenContentPreview() {
    StarWarsSampleTheme {
        HomeScreenContent()
    }
}
//endregion