package kh.com.pheaktra.developer.basic.android.data.request

import kotlinx.serialization.Serializable


@Serializable
data class UserApiRequest(
    val name: String,
    val email: String,
)