package com.example.lesson_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lesson_retrofit.retrofit.ProductApi
import com.example.lesson_retrofit.retrofit.ProductCallAdapterFactory
import com.example.lesson_retrofit.ui.theme.LessonRetrofitTheme
import com.example.lesson_retrofit.view.ExampleScreen1
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ProductCallAdapterFactory())
        .build()
    private val productApi = retrofit.create(ProductApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LessonRetrofitTheme {
                ExampleScreen1(productApi = productApi)
            }
        }
    }
}