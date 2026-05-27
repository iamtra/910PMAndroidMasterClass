package kh.com.pheaktra.developer.basic.android.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiResponse(
    @SerialName("user_id") val id: String,
    @SerialName("user_name") val name: String,
    @SerialName("user_email") val email: String,
    val dateTime: String? = null
)

@Serializable
data class GetListUserResponse(
    val message: String,
    val data: List<UserApiResponse>
)

fun UserApiResponse.displayDateTime() : String {
    return dateTime ?: "NMay 27, 2026"
}