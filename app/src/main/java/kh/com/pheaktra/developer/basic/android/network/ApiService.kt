package kh.com.pheaktra.developer.basic.android.network

import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.request.UserUpdateRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UserApiResponse
import kh.com.pheaktra.developer.basic.android.model.response.UserUpdateResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<GetListUserResponse>

    @POST("users")
    suspend fun createUser(@Body user: UserApiRequest): Response<CreateUserResponse>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<DeleteUserResponse>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: UserUpdateRequest): Response<UserUpdateResponse>
}