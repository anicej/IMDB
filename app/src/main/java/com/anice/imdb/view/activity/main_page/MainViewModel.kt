package com.anice.imdb.view.activity.main_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anice.imdb.data.model.Detail
import com.anice.imdb.data.model.MainIMDBModel
import com.anice.imdb.data.usecase.IMDBUseCase
import com.anice.imdb.domain.remote.response.ErrorResponse
import com.anice.imdb.liveevent.SingleLiveEvent
import com.anice.imdb.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: IMDBUseCase) : BaseViewModel() {

    val mainlistData = MutableLiveData<MainIMDBModel>()
    val dataFailureEvent = SingleLiveEvent<ErrorResponse>()
    val detailsData = MutableLiveData<Detail>()

    fun getDetailsData(id: String) {
        // mark api request in progress to true
        Log.e("ASSSS", "Dddddddddddd")
//        apiRequestInProgress.value = true

        viewModelScope.launch {
            // get data from network
            Log.e("BBBB", "FFF")
            val response = useCase.getDetails(id)
            Log.e("CCCC", response.toString())
            // check network response
            if (response!!.isSuccessful) {
                // set data value
                detailsData.value = response.data

            } else {
                dataFailureEvent.value = response.errorResponse
            }
            apiRequestInProgress.value = false
        }
    }

    fun getMainlistData() {
        // mark api request in progress to true
        Log.e("ASSSS", "Dddddddddddd")
//        apiRequestInProgress.value = true

        viewModelScope.launch {
            // get data from network
            Log.e("BBBB", "FFF")
            val response = useCase.getMainList()
            Log.e("CCCC", response.toString())
            // check network response
            if (response!!.isSuccessful) {
                // set data value
                mainlistData.value = response.data

            } else {
                dataFailureEvent.value = response.errorResponse
            }
            apiRequestInProgress.value = false
        }
    }
}