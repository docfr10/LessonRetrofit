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
import com.example.lesson_retrofit.retrofit.Product
import com.example.lesson_retrofit.retrofit.ProductApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ExampleScreen1(productApi: ProductApi) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val product = remember { mutableStateOf<Product?>(null) }

        product.value?.brand?.let { Text(text = it) }
        product.value?.title?.let { Text(text = it) }
        product.value?.price?.let { Text(text = it.toString()) }

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                product.value = productApi.getProductById()
            }
        }) { Text(text = "Get product") }
    }
}