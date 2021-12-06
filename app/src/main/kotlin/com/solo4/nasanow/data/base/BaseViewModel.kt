package com.solo4.nasanow.data.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    protected val _requestState = MutableSharedFlow<RequestState>()
    val requestState = _requestState.asSharedFlow()


    protected fun launchOnLifecycle(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) { block.invoke() }
    }


}