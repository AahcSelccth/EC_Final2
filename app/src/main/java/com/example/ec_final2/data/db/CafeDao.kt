package com.example.ec_final2.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ec_final2.model.Cafe

@Dao
interface CafeDao {

    @Insert
    suspend fun addCafeToFavorite(cafe: Cafe)

    @Delete
    suspend fun deleteCafeFavorite(cafe: Cafe)

    @Query("Select * from cafe")
    suspend fun getFavorites() : List<Cafe>

}