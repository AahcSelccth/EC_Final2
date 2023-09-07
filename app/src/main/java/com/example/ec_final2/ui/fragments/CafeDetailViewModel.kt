package com.example.ec_final2.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ec_final2.data.db.CafeDataBase
import com.example.ec_final2.data.repository.CafeRepository
import com.example.ec_final2.model.Cafe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CafeDetailViewModel (application: Application): AndroidViewModel(application) {
    private val repository: CafeRepository

    init{
        val db = CafeDataBase.getDatabase(application)
        repository = CafeRepository(db)
    }


    fun addFavorites(cafe: Cafe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCafeFavorites(cafe)
        }
    }

    fun removeFavorites(cafe: Cafe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCafeFavorites(cafe)
        }
    }
}
