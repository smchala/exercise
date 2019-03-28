package com.otssso.samimchala.flick.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otssso.samimchala.flick.models.Tickets

/*
it will do for now, as I wanted to use android navigation to pass data from one fragment to another
this shared vm was inspired from the following https://medium.com/mindorks/how-to-communicate-between-fragments-and-activity-using-viewmodel-ca733233a51c
until android navigation support passing objects easily, or could go back to parcelables
 */
class SharedViewModel : ViewModel() {

    companion object {
        var ticketsLiveData: MutableLiveData<Tickets> = MutableLiveData()
        var selectedMovie: MutableLiveData<Int> = MutableLiveData<Int>().apply { -1 }
    }
}