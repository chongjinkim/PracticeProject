package com.nepplus.practiceproject.remotedatasource

import com.nepplus.practiceproject.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {

    //api.github.com/search/users/q?=blah

    @GET("search/users")
    suspend fun getUser(
        @Query("q") q : String?,
    ) : UserResponse

}