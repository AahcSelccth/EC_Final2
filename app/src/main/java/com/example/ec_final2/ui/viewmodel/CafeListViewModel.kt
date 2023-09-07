package com.example.ec_final2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ec_final2.data.CafeServiceResult
import com.example.ec_final2.data.repository.CafeRepository
import com.example.ec_final2.model.Cafe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CafeListViewModel : ViewModel() {
    private val _cafes: MutableLiveData<List<Cafe>> = MutableLiveData<List<Cafe>>()
    val cafes: LiveData<List<Cafe>> = _cafes
    private val repository = CafeRepository()

    fun getCafesFromService() {
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getCafes()
            when(response) {
                is CafeServiceResult.Success -> {
                    _cafes.postValue(response.data!!)
                }
                is CafeServiceResult.Error -> {

                }
            }
        }
    }
}