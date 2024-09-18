package com.Starbound.Rush.arcade

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun LevelsScreen(
    onBack: () -> Unit,
    onSetting: () -> Unit,
    onLevel: (Int) -> Unit
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
            painter = painterResource(id = R.drawable.backbutton),
            contentDescription = "Back Button",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(50.dp)
                .clickable(onClick = onBack)
        )

        Image(
            painter = painterResource(id = R.drawable.settingsbutton), // Заменить на ваш ресурс
            contentDescription = "Setting Button",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(50.dp)
                .clickable {
                    onSetting()
                }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 10.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.levelbutton),
                contentDescription = "Setting Button",
                modifier = Modifier
                    .size(260.dp)
                    .padding(16.dp)
            )
            // Уровни 1, 2, 3
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LevelButton(levelResId = R.drawable.lvl1) {
                    onLevel(1)
                }
                LevelButton(levelResId = R.drawable.lvl2) {
                    onLevel(2)
                }
                LevelButton(levelResId = R.drawable.lvl3) {
                    onLevel(3)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Уровни 4, 5, 6
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LevelButton(levelResId = R.drawable.lvl4) {
                    onLevel(4)
                }
                LevelButton(levelResId = R.drawable.lvl5) {
                    onLevel(5)
                }
                LevelButton(levelResId = R.drawable.lvl6) {
                    onLevel(6)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Уровни 7, 8, 9
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LevelButton(levelResId = R.drawable.lvl7) {
                    onLevel(7)
                }
                LevelButton(levelResId = R.drawable.lvl8) {
                    onLevel(8)
                }
                LevelButton(levelResId = R.drawable.lvl9) {
                    onLevel(9)
                }
            }
        }
    }
}

@Composable
fun LevelButton(levelResId: Int, onClick: () -> Unit = {}) {
    Image(
        painter = painterResource(id = levelResId),
        contentDescription = "Level Button",
        modifier = Modifier
            .size(100.dp)
            .clickable(
                onClick = onClick
            )
    )
}
