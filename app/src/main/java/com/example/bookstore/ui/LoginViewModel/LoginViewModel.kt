package com.example.bookstore.ui.LoginViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.data.RegistrationItem
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.model.remote.RemoteBuilder
import com.example.bookstore.model.remote.Repository.RepositoryImp
import kotlinx.coroutines.launch


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String= "tag"
    private var repository: RepositoryImp

    private var loginUserMutableLiveData = MutableLiveData<RegistrationItem>()
    val loginUserLiveData: LiveData<RegistrationItem> get() = loginUserMutableLiveData

    init {
        val service = RemoteBuilder.builder().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        val result = repository.loginUser(email,password)
        if (result.body() != null && result.isSuccessful){
            loginUserMutableLiveData.postValue(result.body())
        }else{
            Log.i(TAG, "loginUser: Error")
        }
    }

}