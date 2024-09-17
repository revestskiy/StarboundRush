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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LevelsScreen(
    onBack: () -> Unit,
    onSetting: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background), // Заменить на ваш фон
                contentScale = ContentScale.Crop
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.backbutton), // Заменить на ваш ресурс
            contentDescription = "Back Button",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clickable {
                    onBack()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.settingsbutton), // Заменить на ваш ресурс
            contentDescription = "Setting Button",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopEnd)
                .padding(16.dp)
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
                    .clickable {
                    }
            )
            // Уровни 1, 2, 3
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LevelButton(levelResId = R.drawable.lvl1)
                LevelButton(levelResId = R.drawable.lvl2)
                LevelButton(levelResId = R.drawable.lvl3)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Уровни 4, 5, 6
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LevelButton(levelResId = R.drawable.lvl4)
                LevelButton(levelResId = R.drawable.lvl5)
                LevelButton(levelResId = R.drawable.lvl6)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Уровни 7, 8, 9
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                LevelButton(levelResId = R.drawable.lvl7)
                LevelButton(levelResId = R.drawable.lvl8)
                LevelButton(levelResId = R.drawable.lvl9)
            }
        }
    }
}

@Composable
fun LevelButton(levelResId: Int) {
    Image(
        painter = painterResource(id = levelResId),
        contentDescription = "Level Button",
        modifier = Modifier
            .size(100.dp)
            .clickable {
                // Обработка нажатия на уровень
            }
    )
}
