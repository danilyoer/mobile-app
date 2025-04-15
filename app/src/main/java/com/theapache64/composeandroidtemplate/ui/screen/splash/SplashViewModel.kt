package com.theapache64.composeandroidtemplate.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theapache64.composeandroidtemplate.BuildConfig
import com.theapache64.composeandroidtemplate.util.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val SPLASH_DURATION_IN_MILLIS = 1500L
    }

    // Храним информацию о версии приложения
    private val _versionName = MutableStateFlow("v${BuildConfig.VERSION_NAME}")
    val versionName: StateFlow<String> = _versionName

    // Флаг, показывающий завершение экрана splash
    private val _isSplashFinished = mutableEventFlow<Boolean>()
    val isSplashFinished: SharedFlow<Boolean> = _isSplashFinished

    // Флаг для контроля, нужно ли автоматически переходить на другой экран
    private val _isAutoNavigate = MutableStateFlow(false)
    val isAutoNavigate: StateFlow<Boolean> = _isAutoNavigate

    init {
        // Пауза перед автоматическим переходом
        viewModelScope.launch {
            delay(SPLASH_DURATION_IN_MILLIS)
            _isAutoNavigate.emit(true)  // Переход по таймеру
        }
    }

    // Метод для ручного управления переходом
    fun onNavigateManually() {
        viewModelScope.launch {
            _isAutoNavigate.emit(true)
        }
    }
}
