package com.example.jetpacksample.retrofit

import com.example.jetpacksample.room.User
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


object RetrofitAPI {
    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val request: Request by lazy {
        retrofit.create(Request::class.java)
    }
}

interface Request {
    @Headers("Content-Type: application/json")
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<ResponseData> // Call 은 흐름처리 기능을 제공해줌
}

data class ResponseData(
    @SerializedName("data")
    val user: User?,
)