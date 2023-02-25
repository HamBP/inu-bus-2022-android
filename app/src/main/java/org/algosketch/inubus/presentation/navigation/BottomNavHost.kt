package org.algosketch.inubus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.algosketch.inubus.R
import org.algosketch.inubus.presentation.main.ViewModelFactory
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel

@Composable
fun BottomNavHost(
    bottomNavController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    mainNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    val toSchoolViewModelFromInu: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)
    val toSchoolViewModelFromBit: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)
    val toSchoolViewModels = listOf(toSchoolViewModelFromInu, toSchoolViewModelFromBit)

    NavHost(navController = bottomNavController, startDestination = "등교", modifier = modifier) {
        composable(route = BottomNavDestination.GOING_TO_SCHOOL.route) {
            TabNavHost(
                mainNavController = mainNavController,
                lifecycleOwner = lifecycleOwner,
                destinations = TabNavDestination.goingToSchool,
                viewModels = toSchoolViewModels
            )
        }
        composable(route = BottomNavDestination.LEAVING_SCHOOL.route) {
            TabNavHost(
                mainNavController = mainNavController,
                lifecycleOwner = lifecycleOwner,
                destinations = TabNavDestination.leavingSchool,
                viewModels = toSchoolViewModels
            )
        }
    }
}

class BottomNavDestination {
    companion object {
        val GOING_TO_SCHOOL = object : NavDestination {
            override val route = "등교"
            override val icon: Int
                get() = R.drawable.school
        }
        val LEAVING_SCHOOL = object : NavDestination {
            override val route = "하교"
            override val icon: Int
                get() = R.drawable.home
        }
        val destinations = listOf(GOING_TO_SCHOOL, LEAVING_SCHOOL)
    }
}