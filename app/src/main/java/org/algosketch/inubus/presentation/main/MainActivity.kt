package org.algosketch.inubus.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.algosketch.inubus.presentation.ui.inu.InuScreen
import org.algosketch.inubus.presentation.ui.theme.gray
import org.algosketch.inubus.presentation.ui.theme.primary

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                InuBusApp()
            }
        }
    }

    @Composable
    fun InuBusApp() {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        val currentScreen = inuTabRowScreens.find { it.route == currentDestination?.route }
            ?: IncheonNationalUniversity

        Scaffold(
            topBar = {
                BusTab(onSelected = { MainDestination ->
                    navController.navigate(MainDestination.route)
                }, currentScreen = currentScreen)
            }
        ) { innerPadding ->
            BusNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    @Composable
    fun BusTab(onSelected: (MainDestination) -> Unit, currentScreen: MainDestination) {
        Row(
            Modifier
                .selectableGroup()
                .fillMaxWidth()
        ) {
            inuTabRowScreens.forEach { screen ->
                val selected = currentScreen == screen

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(52.dp)
                        .weight(1f, true)
                        .selectable(
                            selected = selected,
                            onClick = { onSelected(screen) },
                        )
                ) {
                    Column {
                        Spacer(modifier = Modifier.weight(1f, true))
                        Text(
                            text = screen.route,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.padding(bottom = 6.dp))
                        Box(
                            modifier = Modifier
                                .background(color = if(selected) primary else gray)
                                .fillMaxWidth()
                                .height(4.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun BusNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = IncheonNationalUniversity.route,
            modifier = modifier
        ) {
            composable(route = IncheonNationalUniversity.route) {
                InuScreen(temp = 2)
            }
            composable(route = BioInformationTechnology.route) {
                InuScreen(temp = 4)
            }
        }
    }

    @Preview(showBackground = true, widthDp = 360, heightDp = 800)
    @Composable
    fun InuBusAppPreview() {
        InuBusApp()
    }
}