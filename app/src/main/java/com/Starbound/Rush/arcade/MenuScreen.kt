package com.Starbound.Rush.arcade

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(
    onLevel: () -> Unit,
    onSetting: () -> Unit,
    onExit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.settingsbutton),
            contentDescription = "Settings Button",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(50.dp)
                .clickable {
                    onSetting()
                }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.startbutton),
                contentDescription = "Start Button",
                modifier = Modifier
                    .size(width = 400.dp, height = 100.dp)
                    .clickable {
                        onLevel()
                    }
            )

            Spacer(modifier = Modifier.height(52.dp))


            Image(
                painter = painterResource(id = R.drawable.exitbutton),
                contentDescription = "Exit Button",
                modifier = Modifier
                    .size(width = 250.dp, height = 80.dp)
                    .clickable {
                        onExit()
                    }
            )
        }
    }
}
