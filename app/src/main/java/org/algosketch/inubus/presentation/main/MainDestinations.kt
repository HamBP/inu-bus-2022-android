package org.algosketch.inubus.presentation.main

interface MainDestination {
    val route: String
}

object INU : MainDestination {
    override val route = "인천대입구"
}

object BIT : MainDestination {
    override val route = "지식정보단지"
}

object CollegeOfEngineering : MainDestination {
    override val route = "공대"
}

object Gate : MainDestination {
    override val route = "정문"
}

val toSchoolScreens = listOf(INU, BIT)
val toHomeScreens = listOf(CollegeOfEngineering, Gate)
