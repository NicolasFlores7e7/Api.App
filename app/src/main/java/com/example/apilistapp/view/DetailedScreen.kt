package com.example.apilistapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilistapp.viewmodel.APIViewModel
import com.example.apilistapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailedScreen(navController: NavController, apiViewmodel: APIViewModel) {
    apiViewmodel.getCharacter()
    val character by apiViewmodel.character.observeAsState()

    Box(modifier = Modifier
        .fillMaxSize(),
        ){
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(0.9f)
    ) {
        Box(modifier = Modifier
            .padding(8.dp)){
            GlideImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(4.dp, Color(0xBEB7DCEF)),
                        shape = CircleShape
                    ),
                model = character?.image,
                contentDescription = "character image",
                contentScale = ContentScale.FillWidth)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Box(
                modifier = Modifier
                    .background(Color(0xBEB7DCEF))
            ){
                character?.let { Text(
                    text = "  Name: " + it.name+"  ",
                    fontFamily = apiViewmodel.font,
                    fontSize = 24.sp,
                    color = Color(0xFF916036)
                    ) }


            }
            Box(
                modifier = Modifier
                    .background(Color(0xBEB7DCEF))
            ){
                character?.let { Text(
                    text = "  Gender: "+it.gender+"  ",
                    fontFamily = apiViewmodel.font,
                    fontSize = 24.sp,
                    color = Color(0xFF916036)
                    ) }
            }
            Box(
                modifier = Modifier
                    .background(Color(0xBEB7DCEF))
            ){
                character?.let { Text(
                    text = "  Status: "+it.status+"  ",
                    fontFamily = apiViewmodel.font,
                    fontSize = 24.sp,
                    color = Color(0xFF916036)
                ) }
            }


        }
    }




    



}

