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
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.presentation.main.BusBottomNavigation
import org.algosketch.inubus.presentation.navigation.*

@Composable
fun Home(
    toDetail: (String, String) -> Unit,
) {
    val bottomNavController = rememberNavController()
    val currentBottomNavBackStack by bottomNavController.currentBackStackEntryAsState()
    val currentBottomNavDestination = currentBottomNavBackStack?.destination
    val bottomNavScreens = BottomNavDestination.destinations
    val currentBottomNav = bottomNavScreens.find { it.route == currentBottomNavDestination?.route }
        ?: BottomNavDestination.GOING_TO_SCHOOL

    Scaffold(
        bottomBar = {
            BusBottomNavigation(destinations = bottomNavScreens, onSelected = { MainDestination ->
                bottomNavController.navigate(MainDestination.route)
            }, currentBottomNav)
        }
    ) { innerPadding ->
        BottomNavHost(
            bottomNavController = bottomNavController,
            modifier = Modifier.padding(innerPadding),
            toDetail = toDetail,
        )
    }
}