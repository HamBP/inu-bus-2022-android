package org.algosketch.inubus.presentation.main

interface MainDestination {
    val route: String
}

object IncheonNationalUniversity : MainDestination {
    override val route = "인천대입구"
}

object BioInformationTechnology : MainDestination {
    override val route = "지식정보단지"
}

object INU : MainDestination {
    override val route = "인천대입구"
}

object BIT : MainDestination {
    override val route = "지식정보단지"
}

val inuTabRowScreens = listOf(IncheonNationalUniversity, BioInformationTechnology)
