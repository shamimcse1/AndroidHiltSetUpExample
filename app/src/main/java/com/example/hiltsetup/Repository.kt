package com.example.hiltsetup

import com.example.hiltsetup.di.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    fun getRemoteData() = apiService.getRandomDog()
}