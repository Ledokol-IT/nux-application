package com.ledokol.thebestprojectever.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ledokol.thebestprojectever.data.local.notifications.NotificationsState
import com.ledokol.thebestprojectever.data.repository.NotificationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: NotificationsRepository
) : ViewModel() {

    val state = mutableStateOf(NotificationsState())


    fun onEvent(){

    }

    private fun clearNotifications(){
        viewModelScope.launch {
            repository.clearNotifications()
        }
    }

    private fun getNotifications(){
        viewModelScope.launch {
            repository.getNotifications()
        }
    }

}