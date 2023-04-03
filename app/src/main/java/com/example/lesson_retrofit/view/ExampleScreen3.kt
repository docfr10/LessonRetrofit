package com.example.lesson_retrofit.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.lesson_retrofit.retrofit.MainApi
import com.example.lesson_retrofit.retrofit.user.User
import com.example.lesson_retrofit.retrofit.user.UserAuth
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ExampleScreen3(mainApi: MainApi) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val user = remember { mutableStateOf<User?>(null) }
        val photo = remember { mutableStateOf<ImageBitmap?>(null) }

        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        user.value?.let {
            photo.value?.let { it1 ->
                Image(
                    bitmap = it1,
                    contentDescription = "User photo"
                )
            }
            Text(text = it.firstName)
            Text(text = it.lastName)
        }
        OutlinedTextField(value = username.value, onValueChange = { username.value = it })
        OutlinedTextField(value = password.value, onValueChange = { password.value = it })
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                user.value =
                    mainApi.auth(UserAuth(username = username.value, password = password.value))
                photo.value = Picasso.get().load(user.value!!.image).get().asImageBitmap()
            }
        }) { Text(text = "Sign in") }
    }
}