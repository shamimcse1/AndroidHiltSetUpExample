package com.example.hiltsetup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiltsetup.model.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val dogResponse = MutableLiveData<ApiResponse>()
    val error = MutableLiveData<String>()
    private lateinit var disposable: Disposable

    fun getRandomDogs() {
        disposable = repository.getRemoteData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dogResponse.postValue(it)
                }, {
                    error.postValue(it.toString())
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        if (this::disposable.isInitialized) {
            disposable.dispose()
        }
    }
}