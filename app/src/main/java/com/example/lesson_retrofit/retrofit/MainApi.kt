package com.example.lesson_retrofit.retrofit

import com.example.lesson_retrofit.retrofit.product.Product
import com.example.lesson_retrofit.retrofit.product.Products
import com.example.lesson_retrofit.retrofit.user.User
import com.example.lesson_retrofit.retrofit.user.UserAuth
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MainApi {
    @GET("products/1")
    suspend fun getProductById(): Product

    @GET("products")
    suspend fun getAllProducts(): Products

    @POST("auth/login")
    suspend fun auth(@Body userAuth: UserAuth): User

    @GET("products/search")
    suspend fun getProductByName(@Query("q") name: String): Products
}