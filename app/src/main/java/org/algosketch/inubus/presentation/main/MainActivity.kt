package org.algosketch.inubus.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.R
import org.algosketch.inubus.presentation.ui.home.HomeViewModel
import org.algosketch.inubus.presentation.ui.inu.InuScreen
import org.algosketch.inubus.presentation.ui.theme.primary

class MainActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        homeViewModel.updateBusList("인천대입구")

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
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    @Composable
    fun BusNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = INU.route,
            modifier = modifier
        ) {
            composable(route = INU.route) {
                InuScreen(
                    owner = this@MainActivity,
                    viewModel = homeViewModel,
                    subwayState = "인천대입구"
                )
            }
            composable(route = BIT.route) {
                InuScreen(
                    owner = this@MainActivity,
                    viewModel = homeViewModel,
                    subwayState = "지식정보단지"
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