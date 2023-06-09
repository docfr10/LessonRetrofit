package com.example.lesson_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.lesson_retrofit.retrofit.MainApi
import com.example.lesson_retrofit.ui.theme.LessonRetrofitTheme
import com.example.lesson_retrofit.view.ExampleScreen4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val mainApi = retrofit.create(MainApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            LessonRetrofitTheme {
                // ExampleScreen1(mainApi = mainApi)
                // ExampleScreen2(mainApi = mainApi)
                // ExampleScreen3(mainApi = mainApi)
                ExampleScreen4(context = context, mainApi = mainApi)
            }
        }
    }
}