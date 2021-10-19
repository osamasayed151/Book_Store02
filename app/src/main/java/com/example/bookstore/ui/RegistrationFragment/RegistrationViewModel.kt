package com.example.bookstore.ui.RegistrationFragment

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

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "tag"
    private var repositoryImp: RepositoryImp
    private var postMutableLiveData = MutableLiveData<User?>()
    val postLiveData: LiveData<User?> get() = postMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(BookShopAPI::class.java)
        repositoryImp = RepositoryImp(service)
    }

    fun postUser(data: Data) = viewModelScope.launch {
        val result = repositoryImp.postRegister(data)
        if (result.isSuccessful) {
            if (result.body() != null) {
                postMutableLiveData.postValue(result.body())
            }
        } else {
            Log.i(TAG, "postUser: ${result.message()}")
        }
    }
}
