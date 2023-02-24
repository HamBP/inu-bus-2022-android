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
import org.algosketch.inubus.presentation.navigation.INU
import org.algosketch.inubus.presentation.navigation.TabNavHost

@Composable
fun Home(lifecycleOwner: LifecycleOwner, parentNavHostController: NavHostController) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val toSchoolScreens = listOf(INU, BIT)

    val currentScreen = toSchoolScreens.find { it.route == currentDestination?.route }
        ?: INU

    Scaffold(
        topBar = {
            BusTabView(toSchoolScreens = toSchoolScreens, onSelected = { MainDestination ->
                navController.navigate(MainDestination.route)
            }, currentScreen = currentScreen)
        },
        bottomBar = {
            BusBottomNavigation()
        }
    ) { innerPadding ->
        TabNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            parentNavController = parentNavHostController,
            lifecycleOwner = lifecycleOwner,
        )
    }
}