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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Cloud.Cruiser.cloudgame.SoundManager
import com.Starbound.Rush.arcade.ui.theme.nujnoefont

@Composable
fun OptionsScreen(
    onBack: () -> Unit
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
                .clickable {
                    onBack()
                }
        )


        Text(
            text = "OPTIONS",
            fontFamily = nujnoefont,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )


        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 320.dp, height = 200.dp)
                .paint(painter = painterResource(id = R.drawable.backgroundsetting), contentScale = ContentScale.Fit)
        ) {

            var isMusicEnabled by remember { mutableStateOf(Prefs.musicVolume != 0f) }
            var isSoundEnabled by remember { mutableStateOf(Prefs.soundVolume != 0f) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SettingItem(
                    title = "MUSIC",
                    checked = isMusicEnabled,
                    onCheckedChange = {
                        isMusicEnabled = it
                        Prefs.musicVolume = if (isMusicEnabled) 0.5f else 0f
                        SoundManager.setMusicVolume()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))


                SettingItem(
                    title = "SOUND",
                    checked = isSoundEnabled,
                    onCheckedChange = { isSoundEnabled = it
                        Prefs.soundVolume = if (isSoundEnabled) 0.5f else 0f
                        SoundManager.setSoundVolume()
                    }
                )
            }
        }
    }
}


@Composable
fun SettingItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Text(
            text = title,
            fontFamily = nujnoefont, // Заменить на ваш шрифт
            fontSize = 24.sp,
            modifier = Modifier.weight(1f),
            color = Color(0xFF0A5286)
        )


        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Red, // Цвет переключателя
                uncheckedThumbColor = Color.Gray
            )
        )
    }
}
