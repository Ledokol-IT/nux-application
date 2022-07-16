package com.ledokol.thebestprojectever.data.remote

import com.ledokol.thebestprojectever.data.local.game.Game
import com.ledokol.thebestprojectever.data.local.profile.Profile
import com.ledokol.thebestprojectever.data.local.user.Apps
import com.ledokol.thebestprojectever.data.local.user.CurrentApp
import com.ledokol.thebestprojectever.data.local.user.User
import com.ledokol.thebestprojectever.domain.GameJSON
import com.ledokol.thebestprojectever.domain.ProfileJSON
import com.ledokol.thebestprojectever.domain.StatusJSON
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RetrofitServicesCloud {

    @FormUrlEncoded
    @POST("token")
    fun login(
        @Field("username") nickname: String,
        @Field("password") password: String )
    : Call<Profile>

//    @PUT("{bucket}/{key}")
//    fun uploadAvatar(
//        @Path("bucket") bucket: String,
//        @Path("key") key: String,
//
//        @Body()
//    )

    @Headers("Content-Type: application/json")
    @POST("register")
    fun createProfile(@Body profile: ProfileJSON)
            : Call<Profile>

    @GET("friends")
    fun getFriends(
        @Header("Authorization") authHeader: String
    )
            : Call<List<User>>

    @Headers("Content-Type: application/json")
    @GET("user/{id}")
    fun getUser(
        @Path("id") id: String
    )
            : Call<User>

    @PUT("status/set/android")
    fun setStatus(
        @Header("Authorization") authHeader: String,
        @Body status: StatusJSON
    )
            : Call<StatusJSON>

    @PUT("status/leave")
    fun leaveStatus(

    )
            : Call<StatusJSON>

    @PUT("sync_installed_apps/android")
    fun shareGames(
        @Body games: List<StatusJSON>
    )
            : Call<List<GameJSON>>

    @PUT("current_user/firebase_messaging_token")
    fun setCurrentFirebaseToken(
        @Body firebase_messaging_token: String
    )
            :Call<String>

    @GET("user/{id}/apps")
    fun getUserGames(
        @Path("id") id: String
    )
            :Call<Apps>


    @POST("friends/invite")
    fun friendsInvite(
        @Header("Authorization") authHeader: String,
        @Field("friends_ids") friends_ids: List<String>,
        @Field("app_id") app_id: String,
    )
}