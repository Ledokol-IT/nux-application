package com.ledokol.thebestprojectever.ui.components.atoms

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HeadlineH3(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    fontWeight: FontWeight? = MaterialTheme.typography.h3.fontWeight,
) {

    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.h3,
        modifier = modifier,
        fontWeight = fontWeight,
    )
}
