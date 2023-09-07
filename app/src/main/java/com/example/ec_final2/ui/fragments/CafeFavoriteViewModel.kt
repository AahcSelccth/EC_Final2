package com.example.ec_final2.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ec_final2.data.db.CafeDataBase
import com.example.ec_final2.data.repository.CafeRepository
import com.example.ec_final2.model.Cafe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CafeFavoriteViewModel (application: Application): AndroidViewModel(application) {
    private val repository: CafeRepository
    private  var _favorites: MutableLiveData<List<Cafe>> = MutableLiveData()
    var favorites: LiveData<List<Cafe>> = _favorites


    init {
        val db = CafeDataBase.getDatabase(application)
        repository = CafeRepository(db)
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO){
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }
}