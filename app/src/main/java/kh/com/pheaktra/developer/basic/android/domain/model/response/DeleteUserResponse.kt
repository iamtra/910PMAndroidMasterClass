package kh.com.pheaktra.developer.basic.android.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserResponse(
    val message: String,
    val data: UserApiResponse
)
