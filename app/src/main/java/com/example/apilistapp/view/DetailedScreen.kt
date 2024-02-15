package com.example.apilistapp.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.apilistapp.viewmodel.APIViewModel

@Composable
fun DetailedScreen(navController: NavController, apiViewmodel: APIViewModel) {
    apiViewmodel.getNinja()
    val ninja by apiViewmodel.character.observeAsState()
    ninja?.let { Text(text = it.name) }



}

