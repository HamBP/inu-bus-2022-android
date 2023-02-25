package org.algosketch.inubus.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.presentation.main.BusTabView
import org.algosketch.inubus.presentation.main.ViewModelFactory
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel
import org.algosketch.inubus.presentation.ui.inu.BusInfoScreen

@Composable
fun TabNavHost(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    destinations: List<NavDestination>,
) {
    val toSchoolViewModelFromInu: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)
    val toSchoolViewModelFromBit: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)

    toSchoolViewModelFromInu.updateBusList("인천대입구")

    val tabNavController = rememberNavController()
    val currentBackStack by tabNavController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentTab =
        destinations.find { it.route == currentDestination?.route } ?: destinations.first()

    Column {
        BusTabView(toSchoolScreens = destinations, onSelected = { MainDestination ->
            tabNavController.navigate(MainDestination.route)
        }, currentScreen = currentTab)
        NavHost(
            navController = tabNavController, startDestination = TabNavDestination.INU.route, modifier = modifier
        ) {
            composable(route = TabNavDestination.INU.route) {
                BusInfoScreen(
                    owner = lifecycleOwner,
                    viewModel = toSchoolViewModelFromInu,
                    subwayState = "인천대입구",
                    navController = mainNavController
                )
            }
            composable(route = TabNavDestination.BIT.route) {
                BusInfoScreen(
                    owner = lifecycleOwner,
                    viewModel = toSchoolViewModelFromBit,
                    subwayState = "지식정보단지",
                    navController = mainNavController
                )
            }
        }
    }
}

class TabNavDestination {
    companion object {
        val INU = object : NavDestination {
            override val route = "인천대입구"
        }
        val BIT = object : NavDestination {
            override val route = "지식정보단지"
        }
        val goingToSchool = listOf(INU, BIT)

        val GATE = object : NavDestination {
            override val route = "정문"
        }
        val COE = object : NavDestination {
            override val route = "공과대"
        }
        val leavingSchool = listOf(GATE, COE)
    }
}