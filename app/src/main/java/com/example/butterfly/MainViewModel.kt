package com.example.butterfly

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.butterfly.data.Butterfly
import com.example.butterfly.data.ButterflyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

    private val api: ButterflyApi

) : ViewModel() {

    private val _state = mutableStateOf<ButterflyState>(ButterflyState())
    val state: State<ButterflyState> = _state

    init {
        getRandomButterfly()
    }

    fun getRandomButterfly(){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)

                _state.value = state.value.copy(
                    butterfly = api.getRandomButterfly(),
                    isLoading = false)
            } catch (e:Exception) {
                Log.e("MainViewModel", "get Random Butterfly",e)
                _state.value = state.value.copy(isLoading = false)

            }
        }
    }
    data class ButterflyState(
        val butterfly: Butterfly? = null,
        val isLoading : Boolean = false
    )


}