package kh.com.pheaktra.developer.basic.android.domain.repository

import kh.com.pheaktra.developer.basic.android.data.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.data.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.data.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.data.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.data.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.data.response.UpdateUserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(): Response<GetListUserResponse>

    suspend fun createUser(body: UserApiRequest): Response<CreateUserResponse>

    suspend fun deleteUser(id: String): Response<DeleteUserResponse>

    suspend fun updateUser(id: String, user: UpdateUserRequest): Response<UpdateUserResponse>
}