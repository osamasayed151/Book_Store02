package com.example.bookstore.ui.AccountFragment.DetailsAccountFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.data.User
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.model.remote.RemoteBuilder
import com.example.bookstore.model.remote.Repository.RepositoryImp
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String= "tag"
    private var repository: RepositoryImp

    private var profileUserMutableLiveData = MutableLiveData<User>()
    val profileUserLiveData: LiveData<User> get() = profileUserMutableLiveData

    init {
        val service = RemoteBuilder.builderLogin().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun getDataForUser(authorization:String) = viewModelScope.launch {
        val result = repository.getProfile(authorization)
        if (result.isSuccessful){
            if(result.body() != null){
                profileUserMutableLiveData.postValue(result.body())
            }else{
                Log.i(TAG, "getDataForUser: ${result.message()}")
            }
        }
    }
}