package com.example.ec_final2.data.repository

import android.util.Log
import com.example.ec_final2.data.CafeServiceResult
import com.example.ec_final2.data.db.CafeDao
import com.example.ec_final2.data.db.CafeDataBase
import com.example.ec_final2.data.response.ListCafeResponse
import com.example.ec_final2.data.retrofit.RetrofitHelper
import com.example.ec_final2.model.Cafe


class CafeRepository (db: CafeDataBase? = null) {

    private  val dao: CafeDao? = db?.cafeDao()


    // Retrofit Api
    suspend fun getCafes(): CafeServiceResult<List<Cafe>> {
        return try {
            val response = RetrofitHelper.cafeService.getAllCafes();
            CafeServiceResult.Success(response)
        } catch (e: Exception) {
            Log.e("CafeService", "Error al obtener cafes", e)
            CafeServiceResult.Error(e)
        }
    }

    suspend fun getFavorites(): List<Cafe>{
        dao?.let {
            return dao.getFavorites()
        } ?: kotlin.run {
            return listOf()
        }

    }

    suspend fun addCafeFavorites(cafe: Cafe) {
        dao?.let {
            dao.addCafeToFavorite(cafe)
        }
    }

    suspend fun deleteCafeFavorites(cafe: Cafe) {
        dao?.let {
            dao.deleteCafeFavorite(cafe)
        }
    }

}

