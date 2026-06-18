package kh.com.pheaktra.developer.basic.android.model.request

import kotlinx.serialization.Serializable


@Serializable
data class UserApiRequest(
    val name: String,
    val email: String,
)