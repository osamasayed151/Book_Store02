package com.example.bookstore.ui.MainFragment.SelfDevelopmentFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.model.remote.RemoteBuilder
import com.example.bookstore.model.remote.Repository.RepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelfDevelopmentViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "tag"
    private val repositoryImp: RepositoryImp

    private var selfMutableLiveData = MutableLiveData<List<BookShopItem>>()
    val selfLiveData: LiveData<List<BookShopItem>> get() = selfMutableLiveData

    init {
        val services = RemoteBuilder.builderBooks().create(BookShopAPI::class.java)
        repositoryImp = RepositoryImp(services)
    }

    fun getSelfDevelopmentBooks() {
        viewModelScope.launch {
            val result = repositoryImp.getSelfDevelopmentBooks()

            if (result.body() != null && result.isSuccessful) {
                selfMutableLiveData.postValue(result.body())
            } else {
                Log.i(TAG, "getSelfDevelopmentBooks: ${result.message()}")
            }
        }
    }
}