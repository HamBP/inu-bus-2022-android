package org.algosketch.inubus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavHost() {
    val bottomNavController = rememberNavController()

    NavHost(navController = bottomNavController, startDestination = "등교", modifier = Modifier) {
        composable(route = BottomNavDestination.GOING_TO_SCHOOL.route) {

        }
        composable(route = BottomNavDestination.LEAVING_SCHOOL.route) {

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