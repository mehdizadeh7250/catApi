package com.mehdizadeh.catfeed.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehdizadeh.catfeed.data.local.dao.CatDao
import com.mehdizadeh.catfeed.data.local.entity.CatEntity

@Database(entities = [CatEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}