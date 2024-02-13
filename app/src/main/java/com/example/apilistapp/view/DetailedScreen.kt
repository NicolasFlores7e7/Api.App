package com.example.apilistapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.apilistapp.viewmodel.APIViewModel

@Composable
fun DetailedScreen(navController: NavController, apiViewmodel:APIViewModel){
    Column {
        var card = apiViewmodel.card
        apiViewmodel.getCard(apiViewmodel.name)
        Text(text = apiViewmodel.name.toString())
    }
}