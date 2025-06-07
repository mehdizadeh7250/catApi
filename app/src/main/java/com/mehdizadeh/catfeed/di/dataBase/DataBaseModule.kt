package com.mehdizadeh.catfeed.di.dataBase

import android.content.Context
import androidx.room.Room
import com.mehdizadeh.catfeed.data.local.dao.CatDao
import com.mehdizadeh.catfeed.data.local.dataBase.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "cats.db").build()

    @Provides
    fun provideNoteDao(db: AppDatabase): CatDao = db.catDao()
}