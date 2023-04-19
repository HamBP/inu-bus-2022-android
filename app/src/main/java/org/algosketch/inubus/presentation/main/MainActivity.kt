package org.algosketch.inubus.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import org.algosketch.inubus.presentation.ui.detail.DetailScreen
import org.algosketch.inubus.presentation.ui.home.HomeScreen
import org.algosketch.inubus.presentation.ui.theme.BasicsCodelabTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            BasicsCodelabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InuBusApp()
                }
            }
        }
    }

    @Composable
    fun InuBusApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "HOME") {
            composable(route = "HOME") {
                HomeScreen(
                    toDetail = { busNumber: String, busStop: String -> navController.navigate("DETAIL/$busNumber/$busStop") }
                )
            }
            composable(
                route = "DETAIL/{busNumber}/{busStop}",
                arguments = listOf(
                    navArgument("busNumber") { type = NavType.StringType },
                    navArgument("busStop") { type = NavType.StringType },
                )
            ) { navBackStackEntry ->
                val busNumber = navBackStackEntry.arguments?.getString("busNumber")
                val busStop = navBackStackEntry.arguments?.getString("busStop")
                DetailScreen(
                    busNumber = busNumber ?: "??",
                    navController = navController,
                    busStop = busStop ?: "",
                )
            }
        }
    }

    @Preview(showBackground = true, widthDp = 360, heightDp = 800)
    @Composable
    fun InuBusAppPreview() {
        InuBusApp()
    }
}