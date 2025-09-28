package dev.oOooeuvre.providers

import data.Repo
import dev.oOooeuvre.models.Photo

private const val OoOO_SYMBOL_ZERO = "○"
private const val OoOO_SYMBOL_ONE = "●"
private const val OoOO_SPACER = "\u200B"

fun getPhotos(): List<Photo> {
    val localPhotos = Repo.photos
    return localPhotos.map { localPhoto ->
        Photo(
            url = "${Repo.IMAGES_HOST}${localPhoto.imageName}",
            text = localPhoto.text?.getBitsString()?.convertBinaryToSymbols()
        )
    }
}

private fun String.getBitsString(): String = toByteArray()
    .joinToString(separator = "\u200B") { byte ->
        byte
            .toBits()
            .map { if (it) '1' else '0' }
            .joinToString(separator = "")
    }

private fun String.convertBinaryToSymbols(): String =
    map { symbol ->
        when (symbol) {
            '0' -> OoOO_SYMBOL_ZERO
            '1' -> OoOO_SYMBOL_ONE
            else -> OoOO_SPACER
        }
    }
        .joinToString(separator = "")

fun Byte.toBits(): List<Boolean> {
    val bits = mutableListOf<Boolean>()
    // Iterate through each bit of the byte, from most significant to least significant
    for (i in 7 downTo 0) {
        // Check if the i-th bit is set (1) or not (0)
        val isBitSet = (this.toInt() shr i) and 1 == 1
        bits.add(isBitSet)
    }
    return bits
}