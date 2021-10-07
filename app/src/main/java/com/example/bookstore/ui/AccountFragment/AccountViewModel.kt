package com.example.bookstore.ui.AccountFragment

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

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String= "tag"
    private var repository: RepositoryImp

    private var logoutUserMutableLiveData = MutableLiveData<User>()
    val logoutUserLiveData: LiveData<User> get() = logoutUserMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun logoutUser(authorization: String) = viewModelScope.launch {
        val result = repository.postLogout(authorization)

        if (result.body() != null && result.isSuccessful){
            logoutUserMutableLiveData.postValue(result.body())
        }else{
            Log.i(TAG, "logoutUser: ${result.message()}")
        }
    }
}