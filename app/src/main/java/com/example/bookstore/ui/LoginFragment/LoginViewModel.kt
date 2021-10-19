package com.example.bookstore.ui.LoginFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.data.Data
import com.example.bookstore.model.data.User
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.model.remote.RemoteBuilder
import com.example.bookstore.model.remote.Repository.RepositoryImp
import kotlinx.coroutines.launch


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String= "tag"
    private var repository: RepositoryImp

    private var loginUserMutableLiveData = MutableLiveData<User>()
    val loginUserLiveData: LiveData<User> get() = loginUserMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun loginUser(data: Data) = viewModelScope.launch {
        val result = repository.postLogin(data)
        if (result.body() != null && result.isSuccessful){
            loginUserMutableLiveData.postValue(result.body())
        }else{
            Log.i(TAG, "loginUser: ${result.message()}")
        }
    }

}