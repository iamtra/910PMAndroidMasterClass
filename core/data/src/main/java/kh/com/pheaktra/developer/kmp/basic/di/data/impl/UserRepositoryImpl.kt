package kh.com.pheaktra.developer.kmp.basic.di.data.impl

import kh.com.pheaktra.developer.basic.android.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UpdateUserResponse
import javax.inject.Inject
import kh.com.pheaktra.developer.basic.android.network.remote.ApiService
import kh.com.pheaktra.developer.kmp.basic.domain.repository.UserRepository
import retrofit2.Response

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(): Response<GetListUserResponse> {
        return apiService.getUsers()
    }

    override suspend fun createUser(body: UserApiRequest): Response<CreateUserResponse> {
        return apiService.createUser(body)
    }

    override suspend fun deleteUser(id: String): Response<DeleteUserResponse> {
        return apiService.deleteUser(id)
    }

    override suspend fun updateUser(
        id: String,
        user: UpdateUserRequest
    ): Response<UpdateUserResponse> {
        return apiService.updateUser(id, user)
    }
}