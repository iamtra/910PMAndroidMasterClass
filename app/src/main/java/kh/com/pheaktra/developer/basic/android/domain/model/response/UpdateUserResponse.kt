package kh.com.pheaktra.developer.basic.android.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponse(
    val message: String,
    val data: UserApiResponse
)




