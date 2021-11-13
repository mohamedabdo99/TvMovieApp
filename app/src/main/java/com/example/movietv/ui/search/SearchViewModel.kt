package com.example.movietv.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietv.model.search.Show
import com.example.movietv.model.search.TvSearchModel
import com.example.movietv.repo.TvSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject constructor(private val repository: TvSearchRepository) :
    ViewModel() {

    private val _response = MutableLiveData<List<TvSearchModel>>()
    val responseTvShow  : LiveData<List<TvSearchModel>> get() = _response


     fun getAllTvSearch(search : String) = viewModelScope.launch {
        repository.getTvSearch(search).let {
            if (it.isSuccessful){
                _response.postValue(it.body())
                Log.d("success " , "response " + it.body()?.get(0)?.show.toString())
            }
            else{
                Log.d("error response " , "response " + it.message().toString())
            }
        }
    }
}