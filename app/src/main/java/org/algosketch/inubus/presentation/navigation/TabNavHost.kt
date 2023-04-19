package org.algosketch.inubus.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.presentation.main.BusTabView
import org.algosketch.inubus.presentation.main.ViewModelFactory
import org.algosketch.inubus.presentation.ui.leaveschool.LeaveSchool
import org.algosketch.inubus.presentation.ui.leaveschool.LeaveSchoolViewModel
import org.algosketch.inubus.presentation.ui.toschool.ToSchool

@Composable
fun TabNavHost(
    modifier: Modifier = Modifier,
    destinations: List<NavDestination>,
    isToSchool: Boolean,
    toDetail: (String, String) -> Unit,
) {
    val tabNavController = rememberNavController()
    val currentBackStack by tabNavController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentTab =
        destinations.find { it.route == currentDestination?.route } ?: destinations.first()

    val leaveSchoolViewModelFromGate: LeaveSchoolViewModel =
        ViewModelFactory.create(LeaveSchoolViewModel::class.java)
    val leaveSchoolViewModelFromCOE: LeaveSchoolViewModel =
        ViewModelFactory.create(LeaveSchoolViewModel::class.java)
    val leaveSchoolViewModels = listOf(leaveSchoolViewModelFromGate, leaveSchoolViewModelFromCOE)

    if(!isToSchool) leaveSchoolViewModels.first().updateBusList(currentTab.route)

    Column {
        BusTabView(toSchoolScreens = destinations, onSelected = { MainDestination ->
            tabNavController.navigate(MainDestination.route)
        }, currentScreen = currentTab)
        NavHost(
            navController = tabNavController, startDestination = destinations.first().route, modifier = modifier
        ) {
            destinations.forEachIndexed { index, destination ->
                composable(route = destination.route) {
                    if(isToSchool) {
                        ToSchool(
                            startBusStop = destination.route,
                            toDetail = toDetail,
                        )
                    } else {
                        LeaveSchool(
                            viewModel = leaveSchoolViewModels[index],
                            startBusStop = destination.route,
                            toDetail = toDetail
                        )
                    }
                }
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