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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.presentation.ui.home.Home
import org.algosketch.inubus.presentation.ui.detail.DetailScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                InuBusApp()
            }
        }
    }

    @Composable
    fun InuBusApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "HOME") {
            composable(route = "HOME") {
                Home(lifecycleOwner = this@MainActivity, parentNavHostController = navController)
            }
            composable(route = "DETAIL") {
                DetailScreen(
                    busNumber = "8",
                    busStops = listOf("인천대입구", "인천대입구", "송도컨벤시아", "인천대입구"),
                    lastStopName = "송도컨벤시아"
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