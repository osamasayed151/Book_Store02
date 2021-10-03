package com.example.bookstore.ui.RegistrationViewModel

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

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "tag"
    private var repositoryImp: RepositoryImp
    private var postMutableLiveData = MutableLiveData<RegistrationItem>()
    val postLiveData: LiveData<RegistrationItem> get() = postMutableLiveData

    init {
        val service = RemoteBuilder.builder().create(BookShopAPI::class.java)
        repositoryImp = RepositoryImp(service)
    }

    fun postUser(registration: RegistrationItem) = viewModelScope.launch {
        val result = repositoryImp.registrationUser(registration)
        if (result.body() != null && result.isSuccessful) {
            postMutableLiveData.postValue(result.body())
        }else{
            Log.i(TAG, "postUser: Error")
        }
    }
}