package com.example.bookstore.ui.MainFragment

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

    private var bookIdMutableLiveData = MutableLiveData<BookShopItem>()
    val bookIdLiveData: LiveData<BookShopItem> get() = bookIdMutableLiveData

    init {
        val service = RemoteBuilder.builderBooks().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun getAllBookFromAPI() = viewModelScope.launch {
        val result = repository.getNovels()
        if (result.body() != null && result.isSuccessful) {
            bookMutableLiveData.postValue(result.body())
        } else
            Log.i(TAG, "getAllBookFromAPI: Error")
    }


    fun getBookId(bookId: Int) = viewModelScope.launch {
            val result = repository.getBookId(bookId)
            if (result.body() != null && result.isSuccessful){
                bookIdMutableLiveData.postValue(result.body())
            }else{
                Log.i(TAG, "getBookId: Error")
            }
        }
}