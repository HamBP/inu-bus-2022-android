package org.algosketch.inubus.presentation.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.presentation.main.BusBottomNavigation
import org.algosketch.inubus.presentation.main.BusTabView
import org.algosketch.inubus.presentation.navigation.BIT
import org.algosketch.inubus.presentation.navigation.BottomNavDestination
import org.algosketch.inubus.presentation.navigation.BottomNavHost
import org.algosketch.inubus.presentation.navigation.INU

@Composable
fun Home(lifecycleOwner: LifecycleOwner, mainNavController: NavHostController) {
    val tabNavController = rememberNavController()
    val currentBackStack by tabNavController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val toSchoolScreens = listOf(INU, BIT)
    val currentTab = toSchoolScreens.find { it.route == currentDestination?.route } ?: INU

    val bottomNavController = rememberNavController()
    val currentBottomNavBackStack by bottomNavController.currentBackStackEntryAsState()
    val currentBottomNavDestination = currentBottomNavBackStack?.destination
    val bottomNavScreens = BottomNavDestination.destinations
    val currentBottomNav = bottomNavScreens.find { it.route == currentBottomNavDestination?.route } ?: BottomNavDestination.GOING_TO_SCHOOL

    Scaffold(
        topBar = {
            BusTabView(toSchoolScreens = toSchoolScreens, onSelected = { MainDestination ->
                tabNavController.navigate(MainDestination.route)
            }, currentScreen = currentTab)
        },
        bottomBar = {
            BusBottomNavigation(destinations = bottomNavScreens, onSelected = { MainDestination ->
                bottomNavController.navigate(MainDestination.route)
            }, currentBottomNav)
        }
    ) { innerPadding ->
        BottomNavHost(
            bottomNavController = bottomNavController,
            lifecycleOwner = lifecycleOwner,
            mainNavController = mainNavController,
            modifier = Modifier.padding(innerPadding),
            tabNavController = tabNavController,
        )
    }
}