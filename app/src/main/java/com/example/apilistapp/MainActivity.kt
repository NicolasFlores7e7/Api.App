package com.example.apilistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apilistapp.navigation.Routes
import com.example.apilistapp.ui.theme.ApiListAppTheme
import com.example.apilistapp.view.DetailedScreen
import com.example.apilistapp.view.RecyclerView
import com.example.apilistapp.viewmodel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiViewModel by viewModels<APIViewModel>()
        setContent {
            ApiListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.List.route){
                        composable(Routes.List.route){ RecyclerView(navigationController, apiViewModel) }
                        composable(Routes.Detailed.route){ DetailedScreen(navigationController, apiViewModel) }

                    }
                }
            }
        }
    }
}


