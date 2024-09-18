package com.Starbound.Rush.arcade

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameScreen(
    level: Int = 1,              // Current level
    targetScore: Int = when (level) {
        1 -> 10
        2 -> 15
        3 -> 20
        4 -> 35
        5 -> 40
        6 -> 55
        7 -> 60
        8 -> 65
        9 -> 70
        10 -> 100
        else -> 0
    },     // Target score to win the level
    onBack: () -> Unit = {}     // Callback to go back to the main menu
) {
    var score by remember { mutableIntStateOf(0) }
    var timeLeft by remember { mutableIntStateOf(
        when (level) {
            1 -> 30000
            2 -> 50000
            3 -> 70000
            4 -> 110000
            5 -> 130000
            6 -> 150000
            7 -> 170000
            8 -> 220000
            9 -> 250000
            10 -> 180000
            else -> 0
        }
    ) }
    var rocketPosition by remember { mutableFloatStateOf(0f) } // Horizontal position of the rocket
    val rocketSpeed = 5f // Reduced speed for smoother movement
    var items by remember { mutableStateOf(listOf<Item>()) } // Generate coins based on level
    var gameOver by remember { mutableStateOf(false) }
    var levelCompleted by remember { mutableStateOf(false) } // Track if the level is completed
    var isMovingLeft by remember { mutableStateOf(false) }
    var isMovingRight by remember { mutableStateOf(false) }
    var isSettings by remember { mutableStateOf(false) }
    // Screen width to enforce boundaries

    // Rocket movement logic with boundary checks


    // Countdown timer
    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            if (!isSettings) {
                timeLeft -= 1000
            }
        }
        else {
            gameOver = true
        }
    }
    if (isSettings) {
        BackHandler {
            isSettings = false
        }
        OptionsScreen {
            isSettings = false
        }
    }
    else if (!gameOver && !levelCompleted) {
        // Game UI
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds
                ), // Pinkish background
            contentAlignment = Alignment.Center
        ) {
            val maxHeight = constraints.maxHeight
            val screenWidth = constraints.maxWidth * 0.3f
            LaunchedEffect(isMovingLeft, isMovingRight) {
                while (isMovingLeft || isMovingRight) {
                    delay(16L) // Frame delay for roughly 60 FPS

                    if (isMovingLeft) {
                        // Left boundary limit
                        val leftLimit = -screenWidth + 80f
                        if (rocketPosition > leftLimit) {
                            rocketPosition -= rocketSpeed
                        } else {
                            rocketPosition = leftLimit // Set position exactly to the left limit
                            isMovingLeft = false // Stop further movement to the left
                        }
                    }

                    if (isMovingRight) {
                        // Right boundary limit
                        val rightLimit = screenWidth - 80f
                        if (rocketPosition < rightLimit) {
                            rocketPosition += rocketSpeed
                        } else {
                            rocketPosition = rightLimit // Set position exactly to the right limit
                            isMovingRight = false // Stop further movement to the right
                        }
                    }
                }
            }


            // Display coins
            items.forEach { coin ->
                CoinItem(
                    coin.x,
                    coin.y,
                    coin.drawableRes
                )
            }
// Display score
            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.monetka),
                    contentDescription = "Coin",
                    modifier = Modifier
                        .size(50.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "$score",
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(16.dp),
                    color = Color.White
                )
            }

            // Display time left
            Text(
                text = timeLeft.millisFormatted,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                color = Color.White
            )
            // Display rocket
            Box(
                modifier = Modifier
                    .offset(
                        x = rocketPosition.dp,
                        y = (maxHeight * 0.1f).dp
                    ) // 15% from the top of the screen
                    .size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rocket), // Replace with your rocket drawable
                    contentDescription = "Rocket",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .pointerInteropFilter {
                        when (it.action) {
                            android.view.MotionEvent.ACTION_DOWN -> {
                                isMovingLeft = true
                            }

                            android.view.MotionEvent.ACTION_UP,
                            android.view.MotionEvent.ACTION_CANCEL -> {
                                isMovingLeft = false
                            }
                        }
                        true
                    }
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .pointerInteropFilter {
                        when (it.action) {
                            android.view.MotionEvent.ACTION_DOWN -> {
                                isMovingRight = true
                            }

                            android.view.MotionEvent.ACTION_UP,
                            android.view.MotionEvent.ACTION_CANCEL -> {
                                isMovingRight = false
                            }
                        }
                        true
                    })
            }
            // Movement buttons (left and right)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 120.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { rocketPosition -= rocketSpeed },
                    modifier = Modifier
                        .pointerInteropFilter {
                            when (it.action) {
                                android.view.MotionEvent.ACTION_DOWN -> {
                                    isMovingLeft = true
                                }

                                android.view.MotionEvent.ACTION_UP,
                                android.view.MotionEvent.ACTION_CANCEL -> {
                                    isMovingLeft = false
                                }
                            }
                            true
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.left),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { rocketPosition += rocketSpeed },
                    modifier = Modifier
                        .pointerInteropFilter {
                            when (it.action) {
                                android.view.MotionEvent.ACTION_DOWN -> {
                                    isMovingRight = true
                                }

                                android.view.MotionEvent.ACTION_UP,
                                android.view.MotionEvent.ACTION_CANCEL -> {
                                    isMovingRight = false
                                }
                            }
                            true
                        }) {
                    Icon(
                        painter = painterResource(id = R.drawable.right),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.backbutton), // Replace with your cloud drawable
                contentDescription = "Exit",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(60.dp)
                    .clickable(onClick = onBack),
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(id = R.drawable.settingsbutton), // Replace with your cloud drawable
                contentDescription = "Settings",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(60.dp)
                    .clickable(onClick = {
                        isSettings = true
                    }),
                contentScale = ContentScale.Fit
            )
            LaunchedEffect(Unit) {
                items += generateCoins(level, screenWidth, maxHeight)
                while (timeLeft > 0) {
                    delay(10000L)
                    items += generateCoins(level, screenWidth, maxHeight)
                }
            }
            LaunchedEffect(Unit) {
                while (timeLeft > 0) {
                    delay(16L) // Smooth animation with high FPS (60 frames per second)

                    // Move coins down smoothly
                    items = items.map { coin ->
                        val newCoin =
                            coin.copy(y = coin.y + 4) // Move by smaller steps for smoother animation
                        if (newCoin.y > maxHeight) {
                            // Regenerate coin once it falls off the screen, spawn it above the screen
                            Item(
                                (-screenWidth.roundToInt()..screenWidth.roundToInt()).random()
                                    .toFloat(),
                                Random.nextFloat() * -maxHeight - 50f // Spawn coins slightly higher (adjust this value as needed)
                            )
                        }
                        else {
                            newCoin
                        }
                    }

                    // Collision detection and filtering out collected coins
                    items = items.filter { item ->
                        val itemCollected =
                            item.y > (maxHeight * 0.1f) && item.y < (maxHeight * 0.1f + 80) &&
                                    abs(item.x - rocketPosition) < 50
                        val planetCollected = itemCollected && item.drawableRes != R.drawable.monetka
                        if (planetCollected) {
                            gameOver = true
                            levelCompleted = false
                        }
                        else if (itemCollected) {
                            score += 1 // Increment score if the item is collected
                        }
                        !itemCollected // Only keep coins that are not collected
                    }

                    // Check if the player won the level
                    if (score >= targetScore) {
                        levelCompleted = true
                    }
                }
            }
        }
    }
    else {
        GameOverScreen(
            isWin = levelCompleted,
            level = level,
            score = score,
            targetScore = targetScore
        ) {
            onBack()
        }
    }
}

// Coin data class to store position
data class Item(val x: Float, val y: Float, val drawableRes: Int = R.drawable.monetka)

// Coin composable for drawing a coin
@Composable
fun CoinItem(x: Float, y: Float, drawableRes: Int = R.drawable.monetka) {
    Image(
        modifier = Modifier
            .offset(x = x.dp, y = y.dp)
            .size(40.dp),
        painter = painterResource(id = drawableRes),
        contentDescription = "Coin",
        contentScale = ContentScale.Fit
    )
}

// Function to generate initial set of coins based on the level
fun generateCoins(level: Int, screenWidth: Float, height: Int): List<Item> {
    val numberOfCoins = 3 + level // Increase the number of coins per level
    val listDrawables = listOf(
        R.drawable.monetka,
        R.drawable.planet1,
        R.drawable.planet2,
        R.drawable.planet3
    )
    return List(numberOfCoins) {
        Item(
            (-screenWidth.roundToInt()..screenWidth.roundToInt()).random().toFloat(),
            Random.nextFloat() * -height - 50f,
            listDrawables.random()
        ) // Coins spawn off-screen above the visible area
    }
}

val Number.millisFormatted
    get() = SimpleDateFormat("mm:ss", Locale.getDefault()).format(this)
