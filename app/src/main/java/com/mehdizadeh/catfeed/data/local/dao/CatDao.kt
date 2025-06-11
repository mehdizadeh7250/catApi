package com.mehdizadeh.catfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mehdizadeh.catfeed.data.local.entity.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getAll(): Flow<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: CatEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(cat: CatEntity)

    @Query("DELETE FROM cats WHERE favoriteId = :favorite")
    fun deleteByFavoriteId(favorite: Int): Int

    @Query("DELETE FROM cats WHERE catId = :catId")
    fun deleteByCatId(catId: String): Int
}