package org.algosketch.inubus.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.presentation.ui.detail.DetailScreen
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel
import org.algosketch.inubus.presentation.ui.inu.InuScreen

class MainActivity : AppCompatActivity() {
    private val toSchoolViewModelFromInu: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)
    private val toSchoolViewModelFromBit: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        toSchoolViewModelFromInu.updateBusList("인천대입구")

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
        val currentBackStack by navController.currentBackStackEntryAsState()
        NavHost(navController = navController, startDestination = "HOME") {
            composable(route = "HOME") {
                MainApp(parentNavHostController = navController)
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

    @Composable
    fun MainApp(parentNavHostController: NavHostController) {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        val currentScreen = toSchoolScreens.find { it.route == currentDestination?.route }
            ?: INU

        Scaffold(
            topBar = {
                BusTabView(onSelected = { MainDestination ->
                    navController.navigate(MainDestination.route)
                }, currentScreen = currentScreen)
            },
            bottomBar = {
                BusBottomNavigation()
            }
        ) { innerPadding ->
            BusNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                parentNavController = parentNavHostController,
            )
        }
    }

    @Composable
    fun BusNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        parentNavController: NavHostController,
    ) {
        NavHost(
            navController = navController,
            startDestination = INU.route,
            modifier = modifier
        ) {
            composable(route = INU.route) {
                InuScreen(
                    owner = this@MainActivity,
                    viewModel = toSchoolViewModelFromInu,
                    subwayState = "인천대입구",
                    navController = parentNavController
                )
            }
            composable(route = BIT.route) {
                InuScreen(
                    owner = this@MainActivity,
                    viewModel = toSchoolViewModelFromBit,
                    subwayState = "지식정보단지",
                    navController = parentNavController
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