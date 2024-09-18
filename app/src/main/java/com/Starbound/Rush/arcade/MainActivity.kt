package com.Starbound.Rush.arcade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.Cloud.Cruiser.cloudgame.SoundManager
import com.Starbound.Rush.arcade.ui.theme.StarboundRushTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoundManager.init(application)
        Prefs.init(application)
        setContent {
            StarboundRushTheme {
                val navHostController = rememberNavController()
                NavigationScreen(navHostController = navHostController)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        SoundManager.resumeMusic()
    }

    override fun onPause() {
        super.onPause()
        SoundManager.pauseMusic()
    }

    override fun onDestroy() {
        super.onDestroy()
        SoundManager.onDestroy()
    }
}


@Composable
fun LoadingScreen(
    onNext: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onNext()

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.loading),
                contentScale = ContentScale.Crop
            )
    )
}

