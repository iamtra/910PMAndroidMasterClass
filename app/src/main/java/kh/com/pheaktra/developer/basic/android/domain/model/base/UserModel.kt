package kh.com.pheaktra.developer.basic.android.domain.model.base

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val age: Int,
    val status: String, //  01: Active, 02: Inactive, 03: deleted
)

fun UserModel.isActive() = status == "01"
fun UserModel.isInactive() = status == "02"

val userList = listOf(
    UserModel(
        id = 1,
        name = "John Doe",
        email = "william.r.king@my-own-personal-domain.com",
        age = 25,
        status = "02" //
    )
)