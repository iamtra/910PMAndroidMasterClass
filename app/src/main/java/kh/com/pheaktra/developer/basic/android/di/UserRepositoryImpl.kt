package kh.com.pheaktra.developer.basic.android.di

import jakarta.inject.Inject
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository
import kh.com.pheaktra.developer.basic.android.domain.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.domain.model.request.UserUpdateRequest
import kh.com.pheaktra.developer.basic.android.domain.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.domain.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.domain.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.domain.model.response.UserUpdateResponse
import kh.com.pheaktra.developer.basic.android.network.ApiService
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
        user: UserUpdateRequest
    ): Response<UserUpdateResponse> {
        return apiService.updateUser(id, user)
    }
}