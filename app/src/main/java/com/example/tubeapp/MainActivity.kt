package com.example.tubeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tubeapp.ui.HomeScreen
import com.example.tubeapp.ui.PlayerScreen
import com.example.tubeapp.ui.TubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TubeTheme {
                Surface {
                    val nav = rememberNavController()
                    NavHost(nav, startDestination = "home") {
                        composable("home") {
                            HomeScreen(onVideoClick = { nav.navigate("player/$it") })
                        }
                        composable(
                            "player/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { entry ->
                            val id = entry.arguments?.getInt("id") ?: 0
                            PlayerScreen(
                                videoId = id,
                                onBack = { nav.popBackStack() },
                                onVideoClick = { nav.navigate("player/$it") { popUpTo("home") } }
                            )
                        }
                    }
                }
            }
        }
    }
}
