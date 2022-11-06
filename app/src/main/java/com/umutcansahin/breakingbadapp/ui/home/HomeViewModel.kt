package com.umutcansahin.breakingbadapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.breakingbadapp.model.character.NetworkResponse
import com.umutcansahin.breakingbadapp.repo.BreakingBadRepository
import com.umutcansahin.breakingbadapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BreakingBadRepository
) : ViewModel() {

    val breakingBadResponse = MutableLiveData<NetworkResponse>()
    val isLoading =  MutableLiveData<Boolean>()
    val onError =  MutableLiveData<String>()

    fun getData() = viewModelScope.launch {
        isLoading.value = true
        val request = repository.getBreakingBadList()
        when(request) {
            is Resource.Success -> {
                request.data?.let {
                    breakingBadResponse.value = it
                    isLoading.value = false
                }
            }
            is Resource.Error -> {
                onError.value = request.message
                isLoading.value = false
            }
            is Resource.Loading -> {
                isLoading.value = true
            }
        }
    }
}