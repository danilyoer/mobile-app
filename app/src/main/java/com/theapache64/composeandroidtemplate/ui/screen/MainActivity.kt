package com.theapache64.composeandroidtemplate.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theapache64.composeandroidtemplate.ui.screen.dashboard.DashboardScreen
import com.theapache64.composeandroidtemplate.ui.screen.splash.SplashScreen
import com.theapache64.composeandroidtemplate.ui.theme.ComposeAndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import com.theapache64.composeandroidtemplate.ui.screen.secondscreen.SecondScreen
import com.theapache64.composeandroidtemplate.ui.screen.createhabit.CreateHabitScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAndroidTemplateTheme {
                Surface(modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                }) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    private fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.Splash.route) {

            // Splash
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        val options = NavOptions.Builder()
                            .setPopUpTo(Screen.Splash.route, inclusive = true)
                            .build()
                        navController.navigate(Screen.Dashboard.route, options)
                    },
                    onGoToSecond = {
                        navController.navigate(Screen.Second.route)
                    },
                    onGoToCreateHabit = {
                        navController.navigate(Screen.CreateHabit.route)
                    }
                )
            }


            // Dashboard
            composable(Screen.Dashboard.route) {
                DashboardScreen()
            }

            // Second Screen
            composable(Screen.Second.route) {
                SecondScreen(
                    onBackToSplash = {
                        navController.navigate(Screen.Splash.route)
                    }
                )
            }
            composable(Screen.CreateHabit.route) {
                CreateHabitScreen(
                    onNavigateBackToMain = {
                        navController.popBackStack() // ← возвращаемся на предыдущий экран (Dashboard)
                    }
                )
            }


        }
    }
}

