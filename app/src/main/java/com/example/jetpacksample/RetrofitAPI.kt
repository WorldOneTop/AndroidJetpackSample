package com.example.jetpacksample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpacksample.User
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

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



data class ResponseData(
    @SerializedName("data")
    val user: List<User>,
    @SerializedName("total_pages")
    val totalPages: Int
)
interface Request {
    @Headers("Content-Type: application/json")
    @GET("users/")
    suspend fun getUser(@Query("page") page: Int): ResponseData
}