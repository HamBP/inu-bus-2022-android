package org.algosketch.inubus.presentation.main

interface NavDestination {
    val route: String
}

object INU : NavDestination {
    override val route = "인천대입구"
}

object BIT : NavDestination {
    override val route = "지식정보단지"
}

object CollegeOfEngineering : NavDestination {
    override val route = "공대"
}

object Gate : NavDestination {
    override val route = "정문"
}

val toSchoolScreens = listOf(INU, BIT)
val toHomeScreens = listOf(CollegeOfEngineering, Gate)
