package kh.com.pheaktra.developer.basic.android.data.response

import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserResponse(
    val message: String,
    val data: UserApiResponse
)
