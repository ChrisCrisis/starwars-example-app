package dev.glimmr.starwarssample.ui.theme.extension

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle


val Typography.cardTitle: TextStyle
    @Composable get() = this.titleLarge.copy (
        color = MaterialTheme.colorScheme.primary
    )

val Typography.cardSubTitle: TextStyle
    @Composable get() = this.titleMedium.copy(
        color = MaterialTheme.colorScheme.secondary
    )