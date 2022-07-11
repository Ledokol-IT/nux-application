package com.ledokol.thebestprojectever.ui.components.atoms.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ledokol.thebestprojectever.ui.components.atoms.HeadlineH6

@Composable
fun ButtonPrimaryFull(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){

    Button(
        onClick = onClick,
        modifier = Modifier
            .then(modifier)
            .background(MaterialTheme.colors.primary)
    ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
        )
    ) {
        HeadlineH6(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(5.dp)
        )
    }
}