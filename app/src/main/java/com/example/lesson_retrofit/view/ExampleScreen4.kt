package com.example.lesson_retrofit.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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
fun ExampleScreen4(mainApi: MainApi) {
    val products = remember { mutableStateOf<List<Product>?>(null) }
    val search = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            OutlinedTextField(
                value = search.value,
                onValueChange = { search.value = it },
                label = { Text(text = "Search") })

            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    products.value = mainApi.getProductByName(name = search.value).products
                }
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            products.value?.let {
                items(products.value!!) {
                    Card {
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
}