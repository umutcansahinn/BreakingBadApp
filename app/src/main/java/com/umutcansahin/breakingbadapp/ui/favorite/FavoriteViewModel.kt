package com.umutcansahin.breakingbadapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.breakingbadapp.db.Favorite
import com.umutcansahin.breakingbadapp.repo.BreakingBadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: BreakingBadRepository
): ViewModel() {

    val favoriteData = MutableLiveData<List<Favorite>>()

    fun getAllDataFromRoom() = viewModelScope.launch {
        favoriteData.value = repository.getAllFavorite()
    }
}