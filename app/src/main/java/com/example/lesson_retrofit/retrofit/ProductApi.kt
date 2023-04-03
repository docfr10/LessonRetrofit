package com.example.lesson_retrofit.retrofit

import retrofit2.http.GET

interface ProductApi {
    @GET("products/1")
    fun getProductById(): Product
}