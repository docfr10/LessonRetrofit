package com.example.lesson_retrofit.retrofit

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ProductCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != Product::class.java) {
            return null
        }
        return object : CallAdapter<Product, Product> {
            override fun responseType(): Type = Product::class.java
            override fun adapt(call: retrofit2.Call<Product>): Product = call.execute().body()!!
        }
    }
}