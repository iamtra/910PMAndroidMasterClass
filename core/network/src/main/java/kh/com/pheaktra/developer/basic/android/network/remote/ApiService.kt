package kh.com.pheaktra.developer.basic.android.network.remote

import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UpdateUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<GetListUserResponse>

    @POST("users")
    suspend fun createUser(@Body user: UserApiRequest): Response<CreateUserResponse>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<DeleteUserResponse>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: UpdateUserRequest): Response<UpdateUserResponse>
}