package com.Starbound.Rush.arcade

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreen() {
    var rocketPosition by remember { mutableStateOf(0.5f) } // Позиция ракеты (0 = левый край, 1 = правый край)
    var timeLeft by remember { mutableStateOf(120000L) } // Время на таймере (2 минуты)
    var gameOver by remember { mutableStateOf(false) } // Флаг окончания игры
    var score by remember { mutableStateOf(0) } // Количество собранных монет
    val planets = listOf(R.drawable.planet1, R.drawable.planet2, R.drawable.planet3)
    val monetka = R.drawable.monetka

    // Таймер
    LaunchedEffect(Unit) {
        object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
            }

            override fun onFinish() {
                gameOver = true
            }
        }.start()
    }

    // Игра
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (gameOver) {
            Text(
                text = "Game Over",
                fontSize = 36.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Отображение монет и планет
            CoinAndPlanetLayer(
                planets = planets,
                monetka = monetka,
                onCoinCollected = {
                    score++
                },
                onGameOver = {
                    gameOver = true
                }
            )

            // Ракета
            Image(
                painter = painterResource(id = R.drawable.rocket),
                contentDescription = "Rocket",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomCenter)
                    .offset(x = (rocketPosition * 200.dp.value).dp)
            )

            // Управление ракетой
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                ArrowButton(
                    onClick = { if (rocketPosition > 0f) rocketPosition -= 0.1f },
                    iconRes = R.drawable.left
                )
                ArrowButton(
                    onClick = { if (rocketPosition < 1f) rocketPosition += 0.1f },
                    iconRes = R.drawable.right
                )
            }

            // Таймер
            Text(
                text = "Time: ${timeLeft / 1000}",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )

            // Счет
            Text(
                text = "Coins: $score",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ArrowButton(onClick: () -> Unit, iconRes: Int) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(Color.Gray, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
fun CoinAndPlanetLayer(
    planets: List<Int>,
    monetka: Int,
    onCoinCollected: () -> Unit,
    onGameOver: () -> Unit
) {
    val planetSpeed = 5 // Скорость движения планет
    var planetPosition by remember { mutableStateOf(Random.nextFloat()) }
    var monetkaPosition by remember { mutableStateOf(Random.nextFloat()) }

    // Логика передвижения монет и планет
    LaunchedEffect(Unit) {
        while (true) {
            planetPosition = Random.nextFloat()
            monetkaPosition = Random.nextFloat()
            delay(1000)
        }
    }

    // Отображение планет
    planets.forEach { planetRes ->
        Image(
            painter = painterResource(id = planetRes),
            contentDescription = "Planet",
            modifier = Modifier
                .size(100.dp)
                .offset(y = (planetPosition * 1000.dp.value).dp)
                .alpha(0.8f)
        )
    }

    // Отображение монет
    Image(
        painter = painterResource(id = monetka),
        contentDescription = "Coin",
        modifier = Modifier
            .size(50.dp)
            .offset(y = (monetkaPosition * 1000.dp.value).dp)
            .alpha(0.8f)
            .clickable {
                onCoinCollected()
            }
    )
}
