package org.algosketch.inubus.presentation.navigation

interface NavDestination {
    val route: String
    val icon: Int?
        get() = null
}
