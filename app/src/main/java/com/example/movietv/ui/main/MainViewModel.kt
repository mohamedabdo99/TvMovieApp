package com.example.movietv.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietv.model.TvShowModel
import com.example.movietv.repo.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: TvShowRepository) : ViewModel() {

    private val _response = MutableLiveData<List<TvShowModel>>()
    val responseTvShow  : LiveData<List<TvShowModel>> get() = _response

    init {
        getAllTvShows()
    }

    private fun getAllTvShows() = viewModelScope.launch {
        repository.getTvShows().let { response->
            if (response.isSuccessful){
                _response.postValue(response.body())
                Log.d("success " , "response " + response.body().toString())
            }
            else{
                Log.d("error response " , "response " + response.message().toString())
            }
        }
    }
}