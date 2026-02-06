package com.example.week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week2.routes.ROUTE_CALENDAR
import com.example.week2.routes.ROUTE_HOME
import com.example.week2.routes.ROUTE_SETTINGS
import com.example.week2.view.CalendarScreen
import com.example.week2.view.HomeScreen
import com.example.week2.view.SettingsScreen
import com.example.week2.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNav()
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()
    //TaskViewmodel samalla tasolla navhostin kanssa

    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(
                taskViewModel = taskViewModel,
                onGoToCalendar = { navController.navigate(ROUTE_CALENDAR) },
                onGoToSettings = { navController.navigate(ROUTE_SETTINGS) }
            )
        }

        composable(ROUTE_CALENDAR) {
            CalendarScreen(
                taskViewModel = taskViewModel,
                onBackToList = { navController.popBackStack() }
            )
        }

        composable(ROUTE_SETTINGS) {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
