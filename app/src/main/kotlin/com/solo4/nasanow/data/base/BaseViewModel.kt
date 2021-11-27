package com.solo4.nasanow.data.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    protected fun launchOnLifecycle(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) { block.invoke() }
    }
}