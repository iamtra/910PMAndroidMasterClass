package kh.com.pheaktra.developer.basic.android.network

import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UserApiResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserApiResponse>>

    @POST("users")
    suspend fun createUser(@Body user: UserApiRequest): Response<CreateUserResponse>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<DeleteUserResponse>
}