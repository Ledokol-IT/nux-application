package com.ledokol.thebestprojectever.ui.components.atoms.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ledokol.thebestprojectever.ui.components.atoms.texts.HeadlineH6

@Composable
fun ButtonPrimary(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    padding: Dp = 10.dp,
){

    Button(
        onClick = onClick,
        modifier = Modifier
            .then(modifier)
            .border(2.dp, MaterialTheme.colors.primary)
            .clip(RoundedCornerShape(0.dp))
    ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        )
    ) {
        HeadlineH6(
            text = text,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(padding)
        )
    }
}