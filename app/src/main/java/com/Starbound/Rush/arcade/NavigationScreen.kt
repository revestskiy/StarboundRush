package com.Starbound.Rush.arcade

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationScreen(navHostController: NavHostController){
    NavHost(navController = navHostController,
        startDestination = Screens.Loading) {
        composable(Screens.Loading){
            LoadingScreen (
                onNext = {
                    navHostController.navigate(Screens.Menu)
                }
            )
        }

        composable(Screens.Menu){
            MenuScreen(
                onLevel = {
                    navHostController.navigate(Screens.Levels)
                },
                onSetting = {
                    navHostController.navigate(Screens.Settings)
                },
                onExit = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(Screens.Settings){
            OptionsScreen (
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }


        composable(Screens.Levels){
            LevelsScreen(
                onBack = {
                    navHostController.popBackStack()
                },
                onSetting = {
                    navHostController.navigate(Screens.Settings)
                }
            )
        }
    }
}