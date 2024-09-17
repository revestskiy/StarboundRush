package com.Starbound.Rush.arcade

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.Starbound.Rush.arcade.ui.theme.nujnoefont

@Preview
@Composable
fun GameOverScreen(

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
                text = "GAME OVER",
                style = TextStyle(
                    fontSize = 34.sp,
                    fontFamily = nujnoefont,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "YOUR RESULT",
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
                    text = "16/30",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
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
                .clickable {

                }
        )
    }
}
