package com.example.bookstore.ui.MainViewModel.NovelFragment

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
import kotlinx.coroutines.launch

class NovelViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG: String = "tag"
    private var repository: RepositoryImp

    private var novelMutableLiveData = MutableLiveData<List<BookShopItem>>()
    val novelLiveData: LiveData<List<BookShopItem>> get() = novelMutableLiveData

    init {
        val service = RemoteBuilder.builderBooks().create(BookShopAPI::class.java)
        repository = RepositoryImp(service)
    }

    fun getNovelFromAPI() = viewModelScope.launch {
        val result = repository.getNovels()

        if (result.body() != null && result.isSuccessful) {
            novelMutableLiveData.postValue(result.body())
        } else
            Log.i(TAG, "getAllBookFromAPI: Error")
    }
}