package org.algosketch.inubus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.algosketch.inubus.presentation.main.ViewModelFactory
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel
import org.algosketch.inubus.presentation.ui.inu.InuScreen

@Composable
fun TabNavHost(
    modifier: Modifier = Modifier,
    tabNavController: NavHostController,
    mainNavController: NavHostController,
    lifecycleOwner: LifecycleOwner,
) {
    val toSchoolViewModelFromInu: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)
    val toSchoolViewModelFromBit: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)

    toSchoolViewModelFromInu.updateBusList("인천대입구")

    NavHost(
        navController = tabNavController, startDestination = INU.route, modifier = modifier
    ) {
        composable(route = INU.route) {
            InuScreen(
                owner = lifecycleOwner,
                viewModel = toSchoolViewModelFromInu,
                subwayState = "인천대입구",
                navController = mainNavController
            )
        }
        composable(route = BIT.route) {
            InuScreen(
                owner = lifecycleOwner,
                viewModel = toSchoolViewModelFromBit,
                subwayState = "지식정보단지",
                navController = mainNavController
            )
        }
    }
}

object INU : NavDestination {
    override val route = "인천대입구"
}

object BIT : NavDestination {
    override val route = "지식정보단지"
}