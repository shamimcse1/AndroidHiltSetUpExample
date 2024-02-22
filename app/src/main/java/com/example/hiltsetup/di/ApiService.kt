package com.example.hiltsetup.di

import com.example.hiltsetup.Constants.END_POINT
import com.example.hiltsetup.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    fun getRandomDog(): Single<ApiResponse>
}