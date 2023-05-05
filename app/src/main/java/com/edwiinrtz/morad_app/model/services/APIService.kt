package com.edwiinrtz.morad_app.model.services

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface APIService {

    /*HEADERS ARE NEEDED*/
    @POST("fcm/send")
    suspend fun sendNotification(@Body requestBody: RequestBody): Response<ResponseBody>
}