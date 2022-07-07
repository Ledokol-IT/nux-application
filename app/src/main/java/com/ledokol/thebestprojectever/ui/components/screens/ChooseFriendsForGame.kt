package com.ledokol.thebestprojectever.ui.components.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ledokol.thebestprojectever.R
import com.ledokol.thebestprojectever.data.local.user.User
import com.ledokol.thebestprojectever.data.local.user.UserEvent
import com.ledokol.thebestprojectever.presentation.UserViewModel
import com.ledokol.thebestprojectever.ui.components.atoms.LoadingView
import com.ledokol.thebestprojectever.ui.components.atoms.textfield.Search
import com.ledokol.thebestprojectever.ui.components.molecules.FriendInList
import com.ledokol.thebestprojectever.ui.components.molecules.ScreenTitle
import com.ledokol.thebestprojectever.ui.components.molecules.TitleQuickGame


@Composable
fun ChooseFriendsForGame(
    navController: NavController,
    userViewModel: UserViewModel
){
    val state = userViewModel.state

    fun onClick(
        navController: NavController,
        nickname: String,
    ){
//        userViewModel.onEvent(UserEvent.GetFriendUser(nickname = nickname))
        navController.navigate("friend_screen") {
            popUpTo("friend_screen")
        }
    }

    if(state.isRefreshing){
        Log.e("STATE",state.toString())
        userViewModel.onEvent(UserEvent.Refresh)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.background
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            if (state.isLoading) {
                LoadingView()
            } else {
                LazyColumn(
                    content = {
                        item{
                            TitleQuickGame(
                                step = 2,
                                title = stringResource(id = R.string.title_crew),
                                description = stringResource(id = R.string.description_crew),
                                modifier = Modifier.padding(top = 110.dp)
                            )
                            showSearch(userViewModel = userViewModel)
                        }

                        items(state.users!!.size) { friend ->
                            val user = state.users[friend]
                            var clicked by remember{ mutableStateOf(false)}

                            FriendInList(
                                name = user.nickname,
                                clicked = clicked,
                                onClick = {
                                    clicked = !clicked
                                })
                        }
                    },
                )
//                    }
            }
        }
    }
//    }
}