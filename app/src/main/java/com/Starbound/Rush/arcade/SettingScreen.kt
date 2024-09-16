package com.Starbound.Rush.arcade


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Starbound.Rush.arcade.ui.theme.nujnoefont

@Preview
@Composable
fun OptionsScreen() {
    // Используем Box для позиционирования элементов на фоне
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background), // Заменить на ваш фон
                contentScale = ContentScale.Crop
            )
    ) {
        // Кнопка назад в верхнем левом углу
        Image(
            painter = painterResource(id = R.drawable.backbutton), // Заменить на ваш ресурс
            contentDescription = "Back Button",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clickable {
                    // Обработка нажатия на кнопку назад
                }
        )

        // Текст "OPTIONS" с кастомным шрифтом по центру
        Text(
            text = "OPTIONS",
            fontFamily = nujnoefont, // Заменить на ваш шрифт
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )

        // Синяя панель настроек
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 320.dp, height = 200.dp)
                .paint(painter = painterResource(id = R.drawable.backgroundsetting), contentScale = ContentScale.Fit)
        ) {
            // Управление состояниями переключателей
            var isMusicEnabled by remember { mutableStateOf(true) }
            var isSoundEnabled by remember { mutableStateOf(true) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Переключатель для музыки
                SettingItem(
                    title = "MUSIC",
                    checked = isMusicEnabled,
                    onCheckedChange = { isMusicEnabled = it } // Обработка изменения состояния
                )

                Spacer(modifier = Modifier.height(16.dp)) // Отступ между элементами

                // Переключатель для звуков
                SettingItem(
                    title = "SOUND",
                    checked = isSoundEnabled,
                    onCheckedChange = { isSoundEnabled = it } // Обработка изменения состояния
                )
            }
        }
    }
}

// Вспомогательная функция для переключателей
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
        // Текст названия опции
        Text(
            text = title,
            fontFamily = nujnoefont, // Заменить на ваш шрифт
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )

        // Переключатель
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
