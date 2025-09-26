package presentation

import data.Repo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import data.PhotosRepository
import data.TimeRepository
import kotlinx.coroutines.*
import models.State
import kotlin.coroutines.CoroutineContext
import kotlin.js.Date

class ViewModel: CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main

    private val _state: MutableState<State> = mutableStateOf(State())
    val state: State
        get() = _state.value

    private val photosRepository = PhotosRepository()
    private val timeRepository = TimeRepository()

    init {
        startLogoSwitching()
        startClock()
        launch(Dispatchers.Default) {
            photosRepository.getPhotos()
                .onSuccess { photos -> updateState { copy(photos = photos, isLoading = false) } }
//                .onFailure { it.printStackTrace() }
        }
        requestTime()
    }

    fun visibilityChanged(newVisibilityState: String?) {
        if (newVisibilityState != null && newVisibilityState == VISIBILITY_STATE_VISIBLE) {
            requestTime()
        }
    }

    private fun requestTime() {
        launch(Dispatchers.Default) {
            timeRepository.getEkbTimeMillis()
                .onSuccess { time ->
                    updateState { copy(hours = time.hours, minutes = time.minutes, seconds = time.seconds) }
                }
        }
    }

    private fun startLogoSwitching() {
        launch {
            while (true) {
                delay(5000)
                setRandomLogoWithoutRepeating()
            }
        }
    }

    private fun setRandomLogoWithoutRepeating() {
        while (true) {
            val newLogo = Repo.logos.random()
            if (newLogo != state.logo) {
                updateState { copy(logo = newLogo) }
                break
            }
        }
    }

    private fun startClock() {
        launch(Dispatchers.Default) {
            while (true) {
                getTimeFromLocal()
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

    private suspend fun getTimeFromLocal() {
        val localTimeInSeconds = (millisecondDiff(Date()) / SECOND_IN_MILLIS).toInt()
        // TODO: точно ли нельзя сделать через остаток от деления?
        val startHours = localTimeInSeconds / 60 / 60
        val startMinutes = (localTimeInSeconds - (startHours * 60 * 60)) / 60
        val startSeconds = localTimeInSeconds - startMinutes * 60 - startHours * 60 * 60

        withContext(Dispatchers.Main) {
            updateState { copy(hours = startHours, minutes = startMinutes, seconds = startSeconds) }
        }
    }

    private fun millisecondDiff(date: Date): Double {
        val ekbUTCOffset = 5 * 60 * 60 * 1000
        val currentTimeZoneOffset = date.getTimezoneOffset() * 60 * 1000
        val ekbDate = Date(date.getTime() + ekbUTCOffset)
        val ekbDay = ekbDate.getUTCDate()
        val ekbMonth = ekbDate.getUTCMonth()
        val ekbYear = ekbDate.getUTCFullYear()
        val ekbMidnightMilliseconds = Date(ekbYear, ekbMonth, ekbDay).getTime()
        return (ekbDate.getTime() - ekbMidnightMilliseconds + currentTimeZoneOffset)
    }

    private fun updateState(transform: State.() -> State) {
        _state.value = _state.value.transform()
    }

    companion object {

        private const val VISIBILITY_STATE_VISIBLE = "visible"

    }

}