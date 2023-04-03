package com.example.lesson_retrofit.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lesson_retrofit.retrofit.MainApi
import com.example.lesson_retrofit.retrofit.product.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ExampleScreen1(mainApi: MainApi) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val product = remember { mutableStateOf<Product?>(null) }

        product.value?.let {
            Text(text = it.brand)
            Text(text = it.title)
            Text(text = it.price.toString())
            Text(text = it.discountPercentage.toString())
            Text(text = it.rating.toString())
            Text(text = it.stock.toString())
            Text(text = it.brand)
            Text(text = it.category)
            Text(text = it.thumbnail)
        }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                product.value = mainApi.getProductById()
            }
        }) { Text(text = "Get product") }
    }
}