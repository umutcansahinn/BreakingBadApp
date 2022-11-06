package com.umutcansahin.breakingbadapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.umutcansahin.breakingbadapp.db.FavoriteDao
import com.umutcansahin.breakingbadapp.db.FavoriteDatabase
import com.umutcansahin.breakingbadapp.repo.BreakingBadRepository
import com.umutcansahin.breakingbadapp.service.BreakingBadAPI
import com.umutcansahin.breakingbadapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Singleton
    @Provides
    fun provideRetrofitInstance(): BreakingBadAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BreakingBadAPI::class.java)

    }

    @Singleton
    @Provides
    fun breakingBadRepository(api: BreakingBadAPI,dao: FavoriteDao) = BreakingBadRepository(api,dao)

    @Singleton
    @Provides
    fun roomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,
        "favoriteDatabase"
    ).build()

    @Singleton
    @Provides
    fun favoriteDao(database: FavoriteDatabase) = database.favoriteDao()

}