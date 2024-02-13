package com.example.apilistapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilistapp.R
import com.example.apilistapp.models.Cards
import com.example.apilistapp.models.CardsItem
import com.example.apilistapp.navigation.Routes
import com.example.apilistapp.viewmodel.APIViewModel
import okhttp3.Route

@Composable
fun RecyclerView(navController: NavController, apiViewModel: APIViewModel) {
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)
    val cards: ArrayList<CardsItem> by apiViewModel.cards.observeAsState(ArrayList<CardsItem>())
    apiViewModel.getCards()
    if (showLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    } else {
        LazyColumn(modifier = Modifier
          ) {
            items(cards) {
                CardItem(card = it, apiViewModel, navController)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItem(card: CardsItem, apiViewModel:APIViewModel, navController: NavController) {
    Card(
        border = BorderStroke(
            2.dp,
            Color.Gray
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(Routes.Detailed.route)
                apiViewModel.set_Name(card.name)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.background
                ),
                contentDescription = "bg",
                modifier = Modifier
                    .fillMaxWidth()
                ,
                contentScale = ContentScale.Crop
            )


            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = card.img ?: "default"
                if (image != "default") {
                    GlideImage(
                        model = card.img,
                        contentDescription = "Card Image",
                        contentScale = ContentScale.Inside,
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.empty),
                        contentDescription = "empty"
                    )
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(47, 18, 12, 188)),

                    ){
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = card.name,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        color = Color(255,255,255)
                    )
                }


            }
        }
    }
}