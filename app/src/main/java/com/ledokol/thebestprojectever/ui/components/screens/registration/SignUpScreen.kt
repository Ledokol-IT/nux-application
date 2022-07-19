package com.ledokol.thebestprojectever.ui.components.screens.registration

import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.ledokol.thebestprojectever.data.local.profile.ProfileEvent
import com.ledokol.thebestprojectever.presentation.ProfileViewModel
import com.ledokol.thebestprojectever.ui.components.screens.SignUpScreenFirst

@Composable
fun SignUpScreen(
    viewModel: ProfileViewModel,
    navController: NavController
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var secondScreen by remember { mutableStateOf(false) }
    var checkPrivacy by remember{ mutableStateOf(false) }

    val buttonNextClickSecond = {
        Log.e("REGISTER","REGISTERED")
        viewModel.onEvent(
            ProfileEvent.SignUp(
                nickname = nickname,
                password = password,
                email = email,
                name = name
            )
        )
    }

    val buttonNextClickFirst = {
        secondScreen = true
    }

    if(!secondScreen){
        fun buttonBackClick(){
            navController.popBackStack()
        }
        SignUpScreenFirst(
            navController = navController,
            email = email,
            setEmail = {
                email = it
            },
            password = password,
            setPassword = {
                password = it
            },
            buttonBackClick = { buttonBackClick() },
            buttonNextClick = buttonNextClickFirst,
        )
    }else{
        fun buttonBackClick(){
            secondScreen = false
        }

        SignUpScreenSecond(
            nickname = nickname,
            setNickname = {
                nickname = it
            },
            name = name,
            setName = {
                name = it
            },
            buttonNextClick = buttonNextClickSecond,
            buttonBackClick = {buttonBackClick() },
            checkPrivacy = checkPrivacy,
            setCheckPrivacy = {
                checkPrivacy = it
            },
        )
    }


}