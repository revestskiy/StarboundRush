package com.Starbound.Rush.arcade

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Cloud.Cruiser.cloudgame.SoundManager

@Composable
fun NavigationScreen(navHostController: NavHostController){
    val activity = LocalContext.current as MainActivity
    NavHost(navController = navHostController,
        startDestination = Screens.Loading) {
        composable(Screens.Loading){
            LoadingScreen (
                onNext = {
                    navHostController.navigate(Screens.Menu) {
                        popUpTo(Screens.Loading)
                    }
                }
            )
        }

        composable(Screens.Menu){
            MenuScreen(
                onLevel = {
                    navHostController.navigate(Screens.Levels)
                    SoundManager.playSound()
                },
                onSetting = {
                    navHostController.navigate(Screens.Settings)
                    SoundManager.playSound()
                },
                onExit = activity::finish
            )
        }

        composable(Screens.Settings){
            OptionsScreen (
                onBack = {
                    navHostController.popBackStack()
                    SoundManager.playSound()
                }
            )
        }


        composable(Screens.Levels){
            LevelsScreen(
                onBack = {
                    navHostController.popBackStack()
                    SoundManager.playSound()
                },
                onSetting = {
                    navHostController.navigate(Screens.Settings)
                    SoundManager.playSound()
                },
                onLevel = {
                    navHostController.navigate(
                        Screens.Game.replace(
                            "{level}",
                            it.toString()
                        )
                    )
                    SoundManager.playSound()
                }
            )
        }

        composable(Screens.Game) {
            val level = it.arguments?.getString("level")?.toInt()!!

            GameScreen(
                level = level,
                onBack = {
                    navHostController.popBackStack()
                    SoundManager.playSound()
                }
            )
        }
    }
}