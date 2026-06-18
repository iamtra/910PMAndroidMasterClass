package kh.com.pheaktra.developer.basic.android.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val message: String,
    val data: UserApiResponse
)
