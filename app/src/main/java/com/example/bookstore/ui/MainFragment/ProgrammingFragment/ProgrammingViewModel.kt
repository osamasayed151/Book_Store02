package com.example.bookstore.ui.MainFragment.ProgrammingFragment

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
import retrofit2.create

class ProgrammingViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = "tag"
    private val repositoryImp: RepositoryImp

    private var programmingMutableLiveData = MutableLiveData<List<BookShopItem>>()
    val programmingLiveData: LiveData<List<BookShopItem>> get() = programmingMutableLiveData

    init {
        val services = RemoteBuilder.builderBooks().create(BookShopAPI::class.java)
        repositoryImp = RepositoryImp(services)
    }
    fun getAllProgrammingBook(){
        viewModelScope.launch {
            val result = repositoryImp.getProgrammingBooks()
            if(result.body() != null && result.isSuccessful){
                programmingMutableLiveData.postValue(result.body())
            }else{
                Log.i(TAG, "getAllProgrammingBook: ${result.message()}")
            }
        }
    }
}