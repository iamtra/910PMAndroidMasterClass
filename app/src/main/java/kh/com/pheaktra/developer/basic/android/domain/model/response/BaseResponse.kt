package kh.com.pheaktra.developer.basic.android.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    val message: String,
)
