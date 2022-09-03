package com.ledokol.thebestproject.ui.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ledokol.thebestproject.ui.components.atoms.texts.Body1
import com.ledokol.thebestproject.ui.components.atoms.texts.HeadlineH4

@Composable
fun TitleQuickGame(
    step: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
){

    Column(
        modifier = modifier
            .padding(bottom = 10.dp)
        ,
    ){
//        Body1(
//            text = stringResource(id = R.string.step)+" "+step.toString(),
//            color = MaterialTheme.colors.primary,
//            fontWeight = FontWeight.W500,
//        )
        HeadlineH4(
            text = title,
            fontWeight = FontWeight.W500,
        )
        Body1(
            text = description,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}