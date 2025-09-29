package presentation

import kotlinx.browser.window

const val SECOND_IN_MILLIS = 1000L

fun isHorizontal(): Boolean = with(window) {
    innerWidth > innerHeight
}

fun isMobile(): Boolean = window.navigator.maxTouchPoints > 0