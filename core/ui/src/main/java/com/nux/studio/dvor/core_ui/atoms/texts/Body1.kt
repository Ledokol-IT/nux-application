package com.nux.studio.dvor.core_ui.atoms.texts

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Body1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
    fontWeight: FontWeight? = MaterialTheme.typography.body1.fontWeight,
    textAlign: TextAlign = TextAlign.Left,
) {

    Text(
        text = text,
        color = color,
        modifier = modifier,
        fontWeight = fontWeight,
        textAlign = textAlign,
    )
}