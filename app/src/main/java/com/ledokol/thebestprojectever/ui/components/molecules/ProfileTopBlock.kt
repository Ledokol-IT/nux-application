package com.ledokol.thebestprojectever.ui.components.molecules

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ledokol.thebestprojectever.R
import com.ledokol.thebestprojectever.presentation.ProfileViewModel
import com.ledokol.thebestprojectever.presentation.StatusViewModel
import com.ledokol.thebestprojectever.ui.components.atoms.buttons.ButtonPrimary

@Composable
fun ProfileTopBlock(
    profileViewModel: ProfileViewModel,
    statusViewModel: StatusViewModel,
    navController: NavController,
){

    val state = profileViewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.background
            )
//            .verticalScroll(rememberScrollState())
    ) {
        if(state.profile != null){
            UserInformationProfile(
                name = state.profile.nickname,
                profile = true,
            )
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            

            ButtonPrimary(
                text = stringResource(id = R.string.logout),
                onClick = {
                    Log.e("ACCESS",state.profile!!.access_token)
                    statusViewModel.leaveStatus(accessToken = state.profile.access_token)
                    profileViewModel.logOut()
                },
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }

    }

}