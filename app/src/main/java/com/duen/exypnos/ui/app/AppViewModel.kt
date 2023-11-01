package com.duen.exypnos.ui.app

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel(windowSize: WindowSizeClass) : ViewModel() {
    private val _navigation = MutableStateFlow<NavigationCommand>(NavigationCommand.Halt)
    val navigation: StateFlow<NavigationCommand> get() = _navigation
    var windowSize by mutableStateOf(windowSize)

    fun navigate(command: NavigationCommand) {
        _navigation.value = command
    }
}