package com.ledokol.thebestprojectever.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ledokol.thebestprojectever.data.local.user.User
import com.ledokol.thebestprojectever.data.local.user.UserEvent
import com.ledokol.thebestprojectever.data.local.user.UserState
import com.ledokol.thebestprojectever.data.repository.UsersRepository
import com.ledokol.thebestprojectever.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class
UserViewModel @Inject constructor(
    private val repository: UsersRepository,
): ViewModel() {

    var accessToken: String = ""
    var state by mutableStateOf(UserState())

    private var searchUser: Job? = null

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.Refresh -> {
//                state = state.copy(isRefreshing = true)
                getUsers(fetchRemote = true)
                state = state.copy(isRefreshing = false)
            }
            is UserEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchUser?.cancel()
                searchUser = viewModelScope.launch {
                    delay(500L)
                    getUsers()
                }
            }
            is UserEvent.GetFriendUser -> {
                getUser(nickname = event.nickname)
            }
            is UserEvent.SelectUser -> {
                viewModelScope.launch {
                    val shoulRemove = checkSelectedUser(event.user)
                    if (shoulRemove) {
                        removeSelectedUser(event.user)
                    } else {
                        insertSelectedUser(event.user)
                    }
                }
            }
        }

    }


    fun insertUser(user: User){
        viewModelScope.launch{
            repository.insertUser(user)
            getUsers()
        }
    }



    fun getUsers(
        query: String = state.searchQuery.lowercase(),
        fetchRemote: Boolean = false
    ) {

        viewModelScope.launch {
            repository.getUsers(fetchFromRemote = fetchRemote, accessToken = accessToken,query = query)
                .collect{ result ->
                    when(result){
                        is Resource.Success -> {
                            result.data.let { users ->
                                state = state.copy(
                                    users = users
                                )
                            }
                            Log.e("USER VIEW MODEL  GET USERS",state.toString())
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state =state.copy(
                                isLoading = result.isLoading
                            )
                        }

                    }
                }
        }

    }

    private fun getUser(
        nickname: String
    ){
        viewModelScope.launch {
            repository.getUser(nickname = nickname)
                .collect{ result ->
                    when(result){
                        is Resource.Success -> {
                            result.data.let {
                                user ->
                                state = state.copy(
                                    friendUser = user
                                )
                            }
                            Log.e("User View Model Friend",state.toString())
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                }
        }
    }

    val TAG = "SELECTEDUSER"

    fun removeSelectedUser(selectedUser: User){
        Log.d(TAG, "REMOVE")
        state = state.copy(
            clickedUsers = state.clickedUsers.toMutableList().filter { user -> user.userId!=selectedUser.userId }.toList(),
//            users = state.users!!.toMutableList().filter { user -> user.userId!=selectedUser.userId }.toList()
        )

        Log.d(TAG, "REMOVE "+state.users!!.size.toString())
    }

    fun insertSelectedUser(selectedUser: User){
        state = state.copy(
            clickedUsers = state.clickedUsers.toMutableList().apply { add(selectedUser) }.toList(),
//            users = state.users!!.toMutableList().apply { add(selectedUser) }.toList()
        )
        Log.d(TAG, "INSERT "+state.users!!.size.toString())
    }

    fun checkSelectedUser(selectedUser: User): Boolean{
        Log.d(TAG, "CHECK")
        var inList: Boolean = false
        for(user in state.clickedUsers){
            if (user.userId == selectedUser.userId){
                inList = true
                break
            }
        }

        return inList
    }

}