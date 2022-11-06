package com.umutcansahin.breakingbadapp.ui.detail

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.breakingbadapp.db.Favorite
import com.umutcansahin.breakingbadapp.model.quote.NetworkResponseDetail
import com.umutcansahin.breakingbadapp.repo.BreakingBadRepository
import com.umutcansahin.breakingbadapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: BreakingBadRepository
): ViewModel() {

    val detailResponse = MutableLiveData<NetworkResponseDetail>()
    val isLoading =  MutableLiveData<Boolean>()
    val onError =  MutableLiveData<String>()


    fun addRoomDatabase(
        birthday:String?,
        img: String?,
        name: String?,
        nickName: String?,
        charId: Int?
    ) = viewModelScope.launch {

        val favoriteItem = Favorite(birthday,img,name,nickName,charId,null)
        repository.addFavorite(favoriteItem)

    }

    fun deleteRoomDatabase(charId: Int) = viewModelScope.launch {
        repository.deleteFavorite(charId)
    }


    fun getDetail(name:String) = viewModelScope.launch {
        isLoading.value = true
        val request = repository.getDetail(name)

        when(request) {
            is Resource.Success -> {
                request.data?.let {
                    detailResponse.value = it
                    println("Detail ViewModel Resource.Success")
                    isLoading.value = false
                }
            }
            is Resource.Loading -> {
                isLoading.value = true
            }
            is Resource.Error -> {
                onError.value = request.message
                isLoading.value = false
            }
        }
    }


}
