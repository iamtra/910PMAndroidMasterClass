package kh.com.pheaktra.developer.kmp.basic.domain.repository

import kh.com.pheaktra.developer.basic.android.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UpdateUserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(): Response<GetListUserResponse>

    suspend fun createUser(body: UserApiRequest): Response<CreateUserResponse>

    suspend fun deleteUser(id: String): Response<DeleteUserResponse>

    suspend fun updateUser(id: String, user: UpdateUserRequest): Response<UpdateUserResponse>
}