package kh.com.pheaktra.developer.basic.android.domain.model.request

import kh.com.pheaktra.developer.basic.android.domain.model.response.UpdateUserResponse
import kotlinx.serialization.Serializable


@Serializable
data class UpdateUserRequest(
    val id: String,
    val name: String,
    val email: String,
)