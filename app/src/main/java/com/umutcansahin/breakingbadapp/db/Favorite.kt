package com.umutcansahin.breakingbadapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    val birthday: String?,
    val img: String?,
    val name: String?,
    val nickName: String?,
    val charId: Int?,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)
