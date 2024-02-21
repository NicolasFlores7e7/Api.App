package com.example.apilistapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.apilistapp.R
import com.example.apilistapp.models.Character
import com.example.apilistapp.viewmodel.APIViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.apilistapp.models.Characters


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navController: NavController, apiViewModel: APIViewModel){
    val bottomNavItems = apiViewModel.bottomNavItems
    Scaffold(
        topBar = { TopBar(apiViewModel) },
        bottomBar = { BottomBar(navController, bottomNavItems) },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = "bg",
                    contentScale = ContentScale.FillBounds
                )
            }
        }


    )
}

@Composable
fun Favs(apiViewModel: APIViewModel){
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)

}

