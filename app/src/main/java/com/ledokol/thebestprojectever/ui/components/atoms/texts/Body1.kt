package com.ledokol.thebestprojectever.ui.components.atoms

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Body1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    fontWeight: FontWeight? = MaterialTheme.typography.body1.fontWeight,
) {

    Text(
        text = text,
        color = color,
//        style = MaterialTheme.typography.body1,
        modifier = modifier,
        fontWeight = fontWeight,
    )
}
//
//@Composable
//fun getColorText(type: String): Color {
//    val color: Color
//
//    when(type) {
//        "surface" -> color = MaterialTheme.colors.onSurface
//        "primary" -> color = MaterialTheme.colors.onPrimary
//        "primaryInit" -> color = MaterialTheme.colors.primary
//        "secondary" -> color = MaterialTheme.colors.onSecondary
//        "background" -> color = MaterialTheme.colors.onBackground
//        else -> color = MaterialTheme.colors.onError
//    }
//
//    return color
//}