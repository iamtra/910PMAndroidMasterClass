package kh.com.pheaktra.developer.basic.android.feature.profile

import kh.com.pheaktra.developer.basic.android.model.base.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {
//    private val userList = listOf(
////        UserModel(1, "Pheaktra", "pheaktra@gmail.com", 25),
////        UserModel(2, "Dara", "dara@gmail.com", 22),
////        UserModel(3, "Sokha", "sokha@gmail.com", 28),
////        UserModel(4, "Vannak", "vannak@gmail.com", 30)
//    )
    private val userList = emptyList<UserModel>()

    /**
     * Filter user by id
     */
    suspend fun getUserDetail(id: Int): Flow<UserModel?> {
        delay(1000)
        return flow {
            val user = userList.filter { it.id == id }
            if (user.isNotEmpty()) {
                emit(user[0])
            } else {
                emit(null)
            }
        }
    }
}