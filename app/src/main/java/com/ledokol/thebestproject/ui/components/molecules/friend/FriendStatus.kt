package com.ledokol.thebestproject.ui.components.molecules.friend

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ledokol.thebestproject.data.local.user.User
import com.nux.studio.dvor.core_ui.atoms.Status
import com.ledokol.thebestproject.ui.components.atoms.texts.Body1

@Composable
fun FriendStatus(
    user: User
) {
    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Body1(
            text = if (!user.status.online) "Offline" else "Online"
        )

        Status(
            status = if (!user.status.online) "offline" else "online",
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically)
        )
    }
}