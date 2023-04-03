package com.example.lesson_retrofit.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleScreen2(mainApi: MainApi) {
    val products = remember { mutableStateOf<List<Product>?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            products.value = mainApi.getAllProducts().products
        }

        products.value?.let {
            items(products.value!!) {
                Card() {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = it.title)
                        Text(text = it.brand)
                        Text(text = it.price.toString())
                    }
                }
            }
        }
    }
}