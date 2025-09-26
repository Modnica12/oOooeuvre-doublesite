package data

import dev.oOooeuvre.data.models.PhotoLocal

object Repo {

    val photos = listOf(
        PhotoLocal(
            imageName = "photo_2025-07-16%2016.58.23.jpeg",
            text = "Может ли творчество ",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2017.00.33.jpeg",
            text = "быть идеальным/чистым?\n",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2016.57.49.jpeg",
            text = "Наш глаз цепляется за грязь,",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2017.00.54.jpeg",
            text = " за нечто живое.\n",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2016.58.55%20(1).jpeg",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2017.01.38.jpeg",
            text = "Это моя грязь.",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2017.01.48.jpeg",
            text = " Купайтесь в ней своими глазами.\n\n",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2016.58.38.jpeg",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2017.00.09.jpeg",
        ),
        PhotoLocal(
            imageName = "photo_2025-07-16%2016.59.22.jpeg",
            text = "Грязное творчество."
        ),
    )

    const val IMAGES_HOST = "https://storage.yandexcloud.net/ooooeuvre-polutions-images/"

}