package com.umutcansahin.breakingbadapp.db

import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE charId = :charId ")
    suspend fun deleteFavorite(charId: Int)

    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorite() : List<Favorite>
}