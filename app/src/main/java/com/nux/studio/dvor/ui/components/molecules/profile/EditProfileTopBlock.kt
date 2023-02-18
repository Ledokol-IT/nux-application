package com.nux.studio.dvor.ui.components.molecules.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import com.nux.dvor.ui.components.molecules.UploadAvatar
import com.nux.studio.dvor.core_ui.atoms.texts.Body1
import com.nux.studio.dvor.core_ui.atoms.texts.HeadlineH3
import com.nux.studio.dvor.presentation.ProfileViewModel
import com.nux.studio.dvor.presentation.UserViewModel

@Composable
fun EditProfileTopBlock(
    profileViewModel: ProfileViewModel,
    userViewModel: UserViewModel,
) {

    val state = profileViewModel.state.profile

    state?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            UploadAvatar(
                profile_pic = state.profile_pic.toString(),
                profileViewModel = profileViewModel,
                userViewModel = userViewModel,
                modifier = Modifier.size(130.dp, 130.dp)
            )

            HeadlineH3(
                text = state.nickname,
                color = MaterialTheme.colors.onBackground,
                fontWeight = W700
            )
            Body1(
                text = state.phone!!,
                fontWeight = W700,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

}