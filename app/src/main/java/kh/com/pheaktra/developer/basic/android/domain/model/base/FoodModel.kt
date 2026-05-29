package kh.com.pheaktra.developer.basic.android.domain.model.base

import androidx.annotation.DrawableRes
import kh.com.pheaktra.developer.basic.android.R

data class FoodModel(
    val id: Int,
    @DrawableRes val image: Int,
    val label: String
)

val images = listOf(
    R.drawable.img_food_1,
    R.drawable.img_food_2,
    R.drawable.img_food_3
)

val foodList = List(30) { index ->
    FoodModel(
        id = index + 1,
        image = images[index % images.size], // rotate images
        label = "Food ${index + 1}"
    )
}

