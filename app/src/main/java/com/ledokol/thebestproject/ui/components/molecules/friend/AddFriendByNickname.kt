package com.ledokol.thebestproject.ui.components.molecules.friend

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nux.studio.dvor.core_ui.atoms.Search
import com.ledokol.thebestproject.ui.components.atoms.buttons.ButtonBorder

@Composable
fun AddFriendByNickname(
    nickname: String,
    onClickButton: () -> Unit,
    onValueChange: (String) -> Unit,
) {

    Search(
        text = nickname,
        placeholder = "Введи ник друга",
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth()
    )

    ButtonBorder(
        text = "Добавить",
        onClick = {
            onClickButton()
        },
        padding = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    )
}