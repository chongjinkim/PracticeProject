package com.nepplus.practiceproject.viewmodel

import androidx.lifecycle.*
import com.nepplus.practiceproject.model.User
import com.nepplus.practiceproject.model.UserResponse
import com.nepplus.practiceproject.remotedatasource.GithubRepository

class GithubViewModel(private val repository : GithubRepository) : ViewModel() {

    private val _user = MutableLiveData("")


     //livedata 반응형을 받아와야 한다.
    val user : LiveData<UserResponse> = Transformations.switchMap(_user){
        if(!it.isNullOrEmpty()){
            liveData{emit(repository.couroutineGetUsers(it)) }
        } else{
            liveData { emit(UserResponse())}
        }
    }

    fun setUser(query : String){
       _user.value = query
    }
}