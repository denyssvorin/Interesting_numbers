package com.example.interestingfactsaboutnumbers.model.api

import com.example.interestingfactsaboutnumbers.model.data.NumberData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberApi {
    companion object {
        const val BASE_URL = "http://numbersapi.com/"
    }

    @GET("{selectedNumber}?json")
    fun getNumberData(@Path("selectedNumber") selectedNumber: Int) : Call<NumberData>

    @GET("random/math?json")
    fun getRandomNumberData() : Call<NumberData>
}