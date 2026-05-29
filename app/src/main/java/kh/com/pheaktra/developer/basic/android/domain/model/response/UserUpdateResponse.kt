package kh.com.pheaktra.developer.basic.android.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateResponse(
    val id: Int,
    val name: String,
    val email: String,
)




