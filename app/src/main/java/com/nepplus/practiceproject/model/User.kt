package com.nepplus.practiceproject.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize

//데이터 객체 전달
@Parcelize
data class User(
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val collaborators: Int,
    val company: String,
    val created_at: String,
    val disk_usage: Int,
    val email: String,
    val events_url: String,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: Boolean,
    val html_url: String,
    val id: Int,
    val location: String,
    val login: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val owned_private_repos: Int,
    val private_gists: Int,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val total_private_repos: Int,
    val twitter_username: String,
    val two_factor_authentication: Boolean,
    val type: String,
    val updated_at: String,
    val url: String
) : Parcelable {

    //recyclerview 데이터 갱신을 위해서 이미 DiffUtil을 구현
    companion object{

        val DiffUtil = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem == newItem

            override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem


        }
    }

}

//데이터 객체전달을 받은 클래스 -> LIST 에 담아 두었음
data class UserResponse(

    val items : List<User> = emptyList()
)