package org.algosketch.inubus.presentation.navigation

interface NavDestination {
    val route: String
}

object CollegeOfEngineering : NavDestination {
    override val route = "공대"
}

object Gate : NavDestination {
    override val route = "정문"
}
