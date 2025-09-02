import kotlin.math.pow

class NumberToSymbolsConverter {

    operator fun invoke(number: Int): CharArray {
        if (number == 0) return charArrayOf('○', '○', '○', '○', '○', '○')
        var reminder = number.toFloat()
        val result = CharArray(MAX_PERIOD)
        repeat(MAX_PERIOD + 1) { index ->
            val period = MAX_PERIOD - index
            val current2InPeriod = 2f.pow(period)
            if (reminder < current2InPeriod) {
                result[index - 1] = '○'
            } else {
                reminder %= current2InPeriod
                result[index - 1] = '●'
            }
        }
        return result
    }

    companion object {

        private const val MAX_PERIOD = 6

    }

}