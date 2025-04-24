package com.theapache64.composeandroidtemplate.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onSplashFinished: () -> Unit,
    onGoToSecond: () -> Unit,
    onGoToCreateHabit: () -> Unit
) {
    val isSplashFinished by viewModel.isSplashFinished.collectAsState(initial = false)

    LaunchedEffect(isSplashFinished) {
        if (isSplashFinished) {
            onSplashFinished()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Приветствие (используем строку из ресурсов)
            Text(
                text = stringResource(id = R.string.label_hello_world), // "Hello World!"
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Кнопка "Click Me"
            Button(
                onClick = onGoToCreateHabit,  // Этот обработчик будет передаваться при переходе
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Создать привычку") // Текст для кнопки
            }


            Button(
                onClick = onGoToSecond,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Мои привычки")
            }
        }

        // Логотип
        Image(
            painter = painterResource(id = R.drawable.ic_compose_logo),
            contentDescription = stringResource(id = R.string.cd_app_logo),
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.TopCenter)
                .padding(top = 32.dp)
        )

        // Версия
        val versionName by viewModel.versionName.collectAsState()
        Text(
            text = versionName,
            modifier = Modifier
        )
    }
}
