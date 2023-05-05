package com.edwiinrtz.morad_app.model.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface retrofitService {

    companion object {

        const val API_URL = "https://fcm.googleapis.com/"

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val call = getRetrofit().create(APIService::class.java)
    }


}