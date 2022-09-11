package com.ledokol.thebestproject.ui.components.screens.games

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ledokol.thebestproject.presentation.GamesViewModel
import com.ledokol.thebestproject.presentation.UserViewModel
import com.ledokol.thebestproject.ui.components.atoms.buttons.ButtonBorder
import com.ledokol.thebestproject.ui.components.atoms.buttons.ButtonFull
import com.ledokol.thebestproject.ui.components.atoms.texts.HeadlineH4
import com.ledokol.thebestproject.R

@Composable
fun FinishInvitingFriends(
    navController: NavController,
    gamesViewModel: GamesViewModel,
    userViewModel: UserViewModel,
) {
    val context: Context = LocalContext.current

    fun clickFinish(){
        navController.navigate("quick_game"){
            popUpTo("quick_game"){
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun openGame(){
        val launchIntent = context.packageManager.getLaunchIntentForPackage(
            gamesViewModel.state.game!!.android_package_name)
        launchIntent?.let { context.startActivity(it) }
    }

//    LaunchedEffect(key1 = true){
//        delay(3000)
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
            .padding(20.dp)
        ,

        ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            HeadlineH4(text = context.resources.getQuantityString(
                R.plurals.finish_invite_friends,
                userViewModel.state.clickedUsers.size,
                userViewModel.state.clickedUsers.size,
            ),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                ,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W500,
            )
            HeadlineH4(text = "В ${gamesViewModel.state.game!!.name}",
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 50.dp)
                ,
                fontWeight = FontWeight.W700,
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(BottomCenter)
            ,

            verticalArrangement = Arrangement.Bottom,
        ) {
            ButtonFull(
                text = stringResource(id = R.string.open_game),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                ,
                onClick = {
                    openGame()
                    clickFinish()
                },
                colorBackground = MaterialTheme.colors.onBackground,
                colorText = MaterialTheme.colors.secondary
            )

            ButtonBorder(
                text = stringResource(id = R.string.close),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                ,
                onClick = {
                    clickFinish()
                },
                colorBackground = MaterialTheme.colors.secondary,
                colorText = MaterialTheme.colors.onBackground,
                colorBorder = MaterialTheme.colors.onBackground,
                padding = 5.dp
            )
        }
    }
}
