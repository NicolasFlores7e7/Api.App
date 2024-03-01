package com.example.apilistapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilistapp.viewmodel.APIViewModel
import com.example.apilistapp.R
import java.nio.file.WatchEvent


@Composable
fun DetailedScreen(navController: NavController, apiViewModel: APIViewModel) {
    val bottomNavItems = apiViewModel.bottomNavItems
    apiViewModel.searchIconActivator(false)
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
                CharacterData(apiViewModel)
            }
        }


    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterData(apiViewmodel: APIViewModel) {
    apiViewmodel.getCharacter()
    val character by apiViewmodel.character.observeAsState()
    character?.let { apiViewmodel.isFavorite(it) }

    Column(
        modifier = Modifier
            .fillMaxSize(0.9f)
            .padding(start = 32.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
        ) {
            GlideImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(4.dp, Color(0xBEB7DCEF)),
                        shape = CircleShape
                    ),
                model = character?.image,
                contentDescription = "character image",
                contentScale = ContentScale.FillWidth,
            )
            Box(
                modifier = Modifier
                    .padding(start = 192.dp, top = 32.dp)
                    .clickable {
                    }
            ) {
                FavButton(apiViewmodel)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 320.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .background(Color(0xBEB7DCEF))
                ) {
                    character?.let {
                        Text(
                            text = "  Name: " + it.name + "  ",
                            fontFamily = apiViewmodel.font,
                            fontSize = 24.sp,
                            color = Color(0xFF916036)
                        )
                    }


                }
                Box(
                    modifier = Modifier
                        .background(Color(0xBEB7DCEF))
                ) {
                    character?.let {
                        Text(
                            text = "  Gender: " + it.gender + "  ",
                            fontFamily = apiViewmodel.font,
                            fontSize = 24.sp,
                            color = Color(0xFF916036)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .background(Color(0xBEB7DCEF))
                ) {
                    character?.let {
                        Text(
                            text = "  Status: " + it.status + "  ",
                            fontFamily = apiViewmodel.font,
                            fontSize = 24.sp,
                            color = Color(0xFF916036)
                        )
                    }
                }

            }
        }


    }
}


@Composable
fun FavButton(apiViewModel: APIViewModel) {
    val isFavorite by apiViewModel.isFavorite.observeAsState(false)
    val character by apiViewModel.character.observeAsState()

    Icon(
        tint = Color(0xFFE46F92),
        modifier = Modifier
            .graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            }
            .clickable {
                apiViewModel.favController(character, isFavorite)
            },
        imageVector = if (isFavorite == true) {
            Icons.Filled.Favorite
        } else {
            Icons.Default.FavoriteBorder
        },
        contentDescription = null
    )
}





