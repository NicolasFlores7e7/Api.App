package com.example.apilistapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilistapp.R
import com.example.apilistapp.models.Characters
import com.example.apilistapp.models.Character
import com.example.apilistapp.navigation.BottomNavScreens
import com.example.apilistapp.navigation.Routes
import com.example.apilistapp.viewmodel.APIViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, apiViewModel: APIViewModel) {
    val bottomNavItems = apiViewModel.bottomNavItems
    Scaffold(
        topBar = { TopBar(apiViewModel) },
        bottomBar = { BottomBar(navController, bottomNavItems) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = "bg",
                    contentScale = ContentScale.FillBounds
                )
                RecyclerView(navController, apiViewModel)
            }
        }
    )
}

@Composable
fun RecyclerView(navController: NavController, apiViewModel: APIViewModel) {
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)
    val characters: Characters by apiViewModel.characters.observeAsState(Characters(emptyList()))
    apiViewModel.getCharacters()
    if (showLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(32.dp),
                color = Color(0xFF916036),
            )
            Text(
                text = "Loading...",
                fontFamily = apiViewModel.font,
                fontSize = 64.sp,
                color = Color(0xFF916036)

            )
        }

    } else {
        LazyColumn(
            modifier = Modifier
        ) {
            items(characters.characters) {
                CharacterItem(character = it, apiViewModel, navController)
            }
        }
    }
    NavButtons(apiViewModel)
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Character, apiViewModel: APIViewModel, navController: NavController) {

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(apiViewModel: APIViewModel) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(32.dp),
                text = "Rick & Morty Character Database",
                fontFamily = apiViewModel.font,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xBEB7DCEF),
            titleContentColor = Color(0xFF916036),
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),

        )
}

@Composable
fun BottomBar(
    navController: NavController,
    bottomNavItems: List<BottomNavScreens>
) {

    BottomNavigation(
        backgroundColor = Color(0xFFC6E2F1),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        tint = Color(0xFF916036)
                    )
                },
                selected = currentRoute == item.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                alwaysShowLabel = false,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}

@Composable
fun NavButtons(apiViewModel: APIViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(0.95f),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xBEB7DCEF),
                        shape = CircleShape
                    )

            ) {
                IconButton(onClick = {
                    if(apiViewModel.page>1){
                    apiViewModel.page--
                    println("pagina: ${apiViewModel.page}")
                    }
                    else {

                    }
                    apiViewModel.getCharacters()
                },
                    enabled = apiViewModel.page!=0
                ) {

                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF916036),
                        modifier = Modifier.size(48.dp)

                    )

                }
            }

            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .background(
                        Color(0xBEB7DCEF),
                        shape = CircleShape
                    )
            ) {
                IconButton(onClick = {

                    apiViewModel.page++
                    apiViewModel.getCharacters()
                    println("pagina: ${apiViewModel.page}")
                },
                ) {

                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Forward",
                        tint = Color(0xFF916036),
                        modifier = Modifier.size(48.dp)
                    )
                }
            }


        }
    }
}
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "babababa",
    dialogText: String = "sadw",

    ) {
    AlertDialog(

        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}




