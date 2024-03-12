package com.andreylindo.cataaschallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.andreylindo.cataaschallenge.navigation.MainNavGraph
import com.andreylindo.cataaschallenge.ui.screens.home.HomeViewModel
import com.andreylindo.cataaschallenge.ui.theme.CataasChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            val mainNavController = rememberNavController()

            CataasChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavGraph(
                        navController = mainNavController,
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }
}