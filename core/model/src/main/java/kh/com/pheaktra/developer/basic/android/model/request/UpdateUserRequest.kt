package kh.com.pheaktra.developer.basic.android.model.request

import kotlinx.serialization.Serializable


@Serializable
data class UpdateUserRequest(
    val id: String,
    val name: String,
    val email: String,
)