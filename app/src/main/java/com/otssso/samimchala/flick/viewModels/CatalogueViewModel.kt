package com.otssso.samimchala.flick.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otssso.samimchala.flick.models.Tickets
import com.otssso.samimchala.flick.models.data.TicketRepository
import com.otssso.samimchala.flick.viewModels.SharedViewModel.Companion.ticketsLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CatalogueViewModel(var ticketsRepo: TicketRepository = TicketRepository()) : ViewModel() {
    val navigateToSaleCatalogueEvent = MutableLiveData<Int>()
    private var compositeDisposable: Disposable?

    internal val tickets get() = ticketsLiveData

    init {

        compositeDisposable = ticketsRepo.getTickets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tickets.value = it
            }
    }

    fun clickedMovie(position: Int) {
        navigateToSaleCatalogueEvent.value = position
    }

    fun getMovieName(position: Int): MutableLiveData<String> {
        val result: MutableLiveData<String> = MutableLiveData()
        result.value = ticketsLiveData.value?.movies?.get(position)?.name
        return result
    }

    fun getCinemaTickets(): Tickets {
        var tickets = Tickets()
        ticketsLiveData.value?.let {
            tickets = it
        }
        return tickets
    }

    fun cleanUpObservables() {
        compositeDisposable?.dispose()
    }
}