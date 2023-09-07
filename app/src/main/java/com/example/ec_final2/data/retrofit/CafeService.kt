package com.example.ec_final2.data.retrofit

import com.example.ec_final2.data.response.ListCafeResponse
import com.example.ec_final2.model.Cafe
import retrofit2.http.GET

interface CafeService {
    @GET("cafes")
    suspend fun getAllCafes(): List<Cafe>
}