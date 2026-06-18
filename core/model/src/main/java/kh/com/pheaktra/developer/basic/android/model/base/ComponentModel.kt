package kh.com.pheaktra.developer.basic.android.model.base

data class ComponentModel(
    val id: Int,
    val title: String,
    val description: String,
    val route: Any,
    val iconUrl: String? = null
)
