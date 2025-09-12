package presentation

import data.Repo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.*
import models.State
import kotlin.coroutines.CoroutineContext
import kotlin.js.Date
import kotlin.time.toKotlinInstant

class ViewModel: CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main

    private val _state: MutableState<State> = mutableStateOf(State())
    val state: State
        get() = _state.value

    init {
        startLogoSwitching()
        startClock()
    }

    private fun startLogoSwitching() {
        launch {
            while (true) {
                delay(5000)
                RandomWithoutRepeating@ while (true) {
                    val newLogo = Repo.logos.random()
                    if (newLogo != state.logo) {
                        updateState {
                            copy(logo = newLogo)
                        }
                        break@RandomWithoutRepeating
                    }
                }
            }
        }
    }

    private fun startClock() {
        launch(Dispatchers.Default) {
            val localTimeInSeconds = (millisecondDiff(Date()) / SECOND_IN_MILLIS).toInt()
            // TODO: точно ли нельзя сделать через остаток от деления?
            val startHours = localTimeInSeconds / 60 / 60
            val startMinutes = (localTimeInSeconds - (startHours * 60 * 60)) / 60
            val startSeconds = localTimeInSeconds - startMinutes * 60 - startHours * 60 * 60

            withContext(Dispatchers.Main) {
                updateState { copy(hours = startHours, minutes = startMinutes, seconds = startSeconds) }
            }

            while (true) {
                delay(SECOND_IN_MILLIS)
                when {
                    state.seconds < 59 -> updateState { copy(seconds = seconds + 1) }
                    state.minutes < 59 -> updateState { copy(seconds = 0, minutes = minutes + 1) }
                    state.hours < 23 -> updateState { copy(seconds = 0, minutes = 0, hours = hours + 1) }
                    else -> updateState { copy(seconds = 0, minutes = 0, hours = 0) }
                }
            }
        }
    }

    private fun updateState(transform: State.() -> State) {
        _state.value = _state.value.transform()
    }

    private fun millisecondDiff(myDate: Date): Double {
        val midnightDay = myDate.getDate()
        val midnightMonth = myDate.getMonth()
        val midnightYear = myDate.getFullYear()
        val midnightMilliseconds = Date(midnightYear, midnightMonth, midnightDay).getTime()
        return myDate.getTime() - midnightMilliseconds
    }

}