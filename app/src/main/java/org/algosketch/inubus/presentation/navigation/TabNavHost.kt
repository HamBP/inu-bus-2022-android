package org.algosketch.inubus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.algosketch.inubus.presentation.main.BIT
import org.algosketch.inubus.presentation.main.INU
import org.algosketch.inubus.presentation.main.ViewModelFactory
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel
import org.algosketch.inubus.presentation.ui.inu.InuScreen

@Composable
fun TabNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    parentNavController: NavHostController,
    lifecycleOwner: LifecycleOwner,
) {
    val toSchoolViewModelFromInu: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)
    val toSchoolViewModelFromBit: ToSchoolViewModel =
        ViewModelFactory.create(ToSchoolViewModel::class.java)

    toSchoolViewModelFromInu.updateBusList("인천대입구")

    NavHost(
        navController = navController, startDestination = INU.route, modifier = modifier
    ) {
        composable(route = INU.route) {
            InuScreen(
                owner = lifecycleOwner,
                viewModel = toSchoolViewModelFromInu,
                subwayState = "인천대입구",
                navController = parentNavController
            )
        }
        composable(route = BIT.route) {
            InuScreen(
                owner = lifecycleOwner,
                viewModel = toSchoolViewModelFromBit,
                subwayState = "지식정보단지",
                navController = parentNavController
            )
        }
    }
}