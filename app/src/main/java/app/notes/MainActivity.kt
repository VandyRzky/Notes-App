package app.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.notes.appinf.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "splash"){
                composable("splash") { SplashScreen(navController) }
                composable("home") { SplashScreen(navController) }
                composable("create") { SplashScreen(navController) }
                composable("delete") { SplashScreen(navController) }

            }
        }
    }
}

