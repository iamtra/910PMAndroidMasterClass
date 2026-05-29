package kh.com.pheaktra.developer.basic.android.domain.model.request

import kotlinx.serialization.Serializable


@Serializable
data class UserUpdateRequest(
    val name: String,
    val email: String,
)