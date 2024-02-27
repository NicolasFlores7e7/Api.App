package com.example.apilistapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilistapp.models.Characters
import com.example.apilistapp.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navController: NavController, apiViewModel: APIViewModel) {
    val bottomNavItems = apiViewModel.bottomNavItems
    Scaffold(
        topBar = { TopBar(apiViewModel) },
        bottomBar = { BottomBar(navController, bottomNavItems, apiViewModel) },
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
                FavsRecyclerView(apiViewModel, navController)
            }
        }
    )
}

@Composable
fun FavsRecyclerView(apiViewModel: APIViewModel, navController: NavController) {
    val favorites: MutableList<Character> by apiViewModel.favorites.observeAsState(mutableListOf())
    apiViewModel.getFavorites()
    if (favorites.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(favorites) {
                FavItem(character = it, apiViewModel = apiViewModel, navController = navController)
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavItem(character: Character, apiViewModel: APIViewModel, navController: NavController) {
    Card(
        border = BorderStroke(
            2.dp,
            Color(0xBEB7DCEF)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(Routes.Detailed.route)
                apiViewModel.set_Id(character.id)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    GlideImage(
                        model = character.image,
                        contentDescription = "Character image",

                        contentScale = ContentScale.Crop,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xBEB7DCEF)),

                        ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize(),
                            text = character.name,
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = apiViewModel.font,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = Color(0xFF916036)
                        )
                    }
                }

            }
        }
    }
}


