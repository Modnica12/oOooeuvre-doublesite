package presentation

import kotlin.math.pow

class NumberToSymbolsConverter {

    operator fun invoke(number: Int): CharArray {
        if (number == 0) return CharArray(BIT_COUNT) { '○' }
        var reminder = number.toFloat()
        val result = CharArray(BIT_COUNT)
        repeat(BIT_COUNT) { index ->
            // Max period should be less than count by 1
            val period = BIT_COUNT - 1 - index
            val current2InPeriod = 2f.pow(period)
            if (reminder < current2InPeriod) {
                result[index] = '○'
            } else {
                reminder %= current2InPeriod
                result[index] = '●'
            }
        }
        return result
    }

    companion object {

        private const val BIT_COUNT = 6

    }

}