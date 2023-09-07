package com.example.ec_final2.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://cafeteria-erixcel.fly.dev/erixcel/cafeteria/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cafeService: CafeService = retrofit.create(CafeService::class.java)



}