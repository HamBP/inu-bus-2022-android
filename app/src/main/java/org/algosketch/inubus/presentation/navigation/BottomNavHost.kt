package org.algosketch.inubus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavHost(bottomNavController: NavHostController, lifecycleOwner: LifecycleOwner, mainNavController: NavHostController, modifier: Modifier = Modifier, tabNavController: NavHostController) {

    NavHost(navController = bottomNavController, startDestination = "등교", modifier = modifier) {
        composable(route = BottomNavDestination.GOING_TO_SCHOOL.route) {
            TabNavHost(mainNavController = mainNavController, lifecycleOwner = lifecycleOwner, tabNavController = tabNavController)
        }
        composable(route = BottomNavDestination.LEAVING_SCHOOL.route) {
            TabNavHost(mainNavController = mainNavController, lifecycleOwner = lifecycleOwner, tabNavController = tabNavController)
        }
    }
}

class BottomNavDestination {
    companion object {
        val GOING_TO_SCHOOL = object : NavDestination {
            override val route = "등교"
        }
        val LEAVING_SCHOOL = object : NavDestination {
            override val route = "하교"
        }
    }
}