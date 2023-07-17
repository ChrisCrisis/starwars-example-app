package dev.glimmr.starwarssample.ui.shared

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarWarsAppbar(
    title: String,
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.shadow(16.dp),
        title = { Text(text = title) },
        navigationIcon = navigationIcon
    )
}