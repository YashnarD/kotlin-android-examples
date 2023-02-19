package com.example.retrofitreqresin.retrofit

import com.example.retrofitreqresin.models.create.ReqCreatorUser
import com.example.retrofitreqresin.models.create.ResCreatorUser
import com.example.retrofitreqresin.models.list_users.UserData
import com.example.retrofitreqresin.models.register.ReqRegister
import com.example.retrofitreqresin.models.register.ResRegister
import com.example.retrofitreqresin.models.resource.ResourceData
import com.example.retrofitreqresin.models.single_user.SingleUser
import com.example.retrofitreqresin.models.update.ResUpdateUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/users")
    fun getUserList(@Query("page") page: Int): Call<UserData>

    @GET("api/users")
    fun getUserListDelay(@Query("delay") delay: Int): Call<UserData>

    @GET("api/users/{id}")
    fun getSingleUser(@Path("id") id: Int): Call<SingleUser>

    @GET("api/unknown")
    fun getResources(): Call<ResourceData>

//    @GET("api/unknown/{id}")
//    fun getSingleResource(@Path("id") id: Int):Call<>

    @POST("api/users")
    fun createUser(@Body reqCreatorUser: ReqCreatorUser): Call<ResCreatorUser>

    @PATCH("api/users/{id}")
    fun updateUser(@Path("id") id: Int, @Body reqCreatorUser: ReqCreatorUser): Call<ResUpdateUser>

    @DELETE("api/users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<ResponseBody>

    @POST("api/register")
    fun register(@Body reqRegister: ReqRegister): Call<ResRegister>
}