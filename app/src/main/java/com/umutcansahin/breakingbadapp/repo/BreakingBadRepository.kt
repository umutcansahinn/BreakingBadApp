package com.umutcansahin.breakingbadapp.repo

import com.umutcansahin.breakingbadapp.db.Favorite
import com.umutcansahin.breakingbadapp.db.FavoriteDao
import com.umutcansahin.breakingbadapp.model.character.NetworkResponse
import com.umutcansahin.breakingbadapp.model.quote.NetworkResponseDetail
import com.umutcansahin.breakingbadapp.service.BreakingBadAPI
import com.umutcansahin.breakingbadapp.util.Resource
import javax.inject.Inject

class BreakingBadRepository @Inject constructor(
    private val api: BreakingBadAPI,
    private val dao: FavoriteDao
) {

    suspend fun getBreakingBadList(): Resource<NetworkResponse> {
        val response = try {
            api.getBreakingBadList()
        }catch (e: Exception) {
            return Resource.Error("in response Error: ${e}")
        }
        return Resource.Success(response)
    }

    suspend fun getDetail(name: String): Resource<NetworkResponseDetail> {
        val  detailResponse = try {
            api.getDetail(name)
        }catch (e: Exception) {
            return Resource.Error("in response Error: ${e}")
        }
        return Resource.Success(detailResponse)
    }

    suspend fun addFavorite(favorite: Favorite) {
        dao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(charId: Int) {
        dao.deleteFavorite(charId)
    }

    suspend fun getAllFavorite(): List<Favorite> {
        return dao.getAllFavorite()
    }

}