package com.nepplus.practiceproject.remotedatasource


import com.nepplus.practiceproject.model.UserResponse
import com.nepplus.practiceproject.network.Client
import retrofit2.Call

interface GithubRepository {
    //githubAPI 인터페이스에 query 가 들어가기 때문이다.
    suspend fun couroutineGetUsers(query : String) : UserResponse

}

class GithubRepositoryImpl(val client : Client) : GithubRepository {

    override suspend fun couroutineGetUsers(query: String): UserResponse {

        return client.githubAPI.getUser(query)
    }
}




