package com.Starbound.Rush.arcade

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Starbound.Rush.arcade.ui.theme.nujnoefont

@Composable
fun GameOverScreen(
    isWin: Boolean,
    level: Int,
    score: Int,
    targetScore: Int,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backandrocket),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(1f)
                .background(
                    color = Color(0xFF320564).copy(alpha = 0.8f)
                )
                .padding(24.dp)
        ) {
            Text(
                text = if (isWin) "CONGRATS" else "GAME OVER",
                style = TextStyle(
                    fontSize = 34.sp,
                    fontFamily = nujnoefont,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isWin) "LEVEL $level COMPLETED" else "YOUR RESULT",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = nujnoefont,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.monetka),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "$score/$targetScore",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = nujnoefont
                    )
                )
            }
        }

        // Кнопка Try Again
        Image(
            painter = painterResource(id = R.drawable.tryagain),
            contentDescription = "Try Again Button",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .clickable(
                    onClick = onBack
                )
        )
    }
}
