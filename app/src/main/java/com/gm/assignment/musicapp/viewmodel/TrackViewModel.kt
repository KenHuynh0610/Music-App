package com.gm.assignment.musicapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gm.assignment.musicapp.TrackListResult
import com.gm.assignment.musicapp.network.GetApi
import com.gm.assignment.musicapp.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class TrackViewModel : ViewModel() {
    var trackListLiveData: MutableLiveData<TrackListResult> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()


    fun getTrackListObserver(): MutableLiveData<TrackListResult> {
        return trackListLiveData
    }

    fun makeApiCall(artistNameInput: String) {
        Log.d("viewModel", "makeApiCall: $artistNameInput")
        val retrofitInstance = RetrofitService.getRetrofitInstance().create(GetApi::class.java)
        retrofitInstance.getTracks(artistNameInput).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(getTrackListResultWithRxJava())
    }

    private fun getTrackListResultWithRxJava(): io.reactivex.Observer<TrackListResult> {
        return object : io.reactivex.Observer<TrackListResult> {
            override fun onSubscribe(d: Disposable) {
                //start the progress bar
               isLoading.value = true
            }

            override fun onNext(t: TrackListResult) {
                trackListLiveData.postValue(t)
                Log.d("getTrackListResult", "onNext: $t")
            }

            override fun onError(e: Throwable) {
                trackListLiveData.postValue(null)
                Log.d("getTrackListResult", "onError: ")
            }

            override fun onComplete() {
                //hide progress bar
                isLoading.value = false
            }

        }
    }
}