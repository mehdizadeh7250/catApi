package com.mehdizadeh.catfeed.presentation.activity

import androidx.lifecycle.viewModelScope
import com.mehdizadeh.catfeed.core.BaseViewAction
import com.mehdizadeh.catfeed.core.BaseViewEvent
import com.mehdizadeh.catfeed.core.BaseViewModel
import com.mehdizadeh.catfeed.core.BaseViewState
import com.mehdizadeh.catfeed.domain.repository.ThemeMode
import com.mehdizadeh.catfeed.domain.useCase.GetThemeModeUseCase
import com.mehdizadeh.catfeed.domain.useCase.SaveThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    initialState: MainActivityViewState,
    private val saveThemeUseCase: SaveThemeModeUseCase,
    private val getThemeUseCase: GetThemeModeUseCase
) : BaseViewModel<MainActivityViewActions, MainActivityViewState, MainActivityViewEvents>(
    initialState
) {

    init {
        loadTheme()
    }

    private fun loadTheme() {

        viewModelScope.launch {
            getThemeUseCase().collect { theme ->
                setState {
                    it.copy(
                        themeMode = theme
                    )
                }
            }
        }
    }

    override fun handle(action: MainActivityViewActions) {

    }
}

data class MainActivityViewState(
    val themeMode: ThemeMode = ThemeMode.LIGHT
) : BaseViewState

sealed interface MainActivityViewEvents : BaseViewEvent

sealed interface MainActivityViewActions : BaseViewAction