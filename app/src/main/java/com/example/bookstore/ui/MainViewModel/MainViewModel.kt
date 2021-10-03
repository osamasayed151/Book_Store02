package com.example.bookstore.ui.MainViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.model.remote.RemoteBuilder
import com.example.bookstore.model.remote.Repository.RepositoryImp
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "tag"
    private var repository: RepositoryImp

    private var bookMutableLiveData = MutableLiveData<List<BookShopItem>>()
    val bookLiveData: LiveData<List<BookShopItem>> get() = bookMutableLiveData

    init {
        val service = RemoteBuilder.builder().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun getAllBookFromAPI() = viewModelScope.launch {
        val result = repository.getAllBooks()
        if (result.body() != null && result.isSuccessful) {
            bookMutableLiveData.postValue(result.body())
        } else
            Log.i(TAG, "getAllBookFromAPI: Error")
    }


}