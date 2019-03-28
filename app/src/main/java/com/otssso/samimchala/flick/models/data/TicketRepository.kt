package com.otssso.samimchala.flick.models.data

import com.google.gson.Gson
import com.otssso.samimchala.flick.models.Tickets
import com.otssso.samimchala.flick.utils.Utils
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class TicketRepository(
    var networkTicketsJson: String = Utils.loadJSONFromAsset("networkTickets.json"),
    var localTicketsJson: String = Utils.loadJSONFromAsset("localTickets.json")
) {
   /*
    only interested in the data, regardless to where we get it from,
     */
    fun getTickets(): Flowable<Tickets> {
        return getTicketsFromNetwork()
            .publish { networkData ->
                Flowable.merge(
                    networkData,
                    getTicketsFromLocalStorage().takeUntil(networkData)
                )
            }
    }

    /*
    this is a fake local DB, could be room db, has 1 movie
     */
    internal fun getTicketsFromLocalStorage(): Flowable<Tickets> {
        return Flowable.just(getTicketsObject(localTicketsJson))
            .delay(1000, TimeUnit.MILLISECONDS)
    }

    /*
   has 2 movies
   this function should get data from server, using retrofit, create api interface then once we get response (GsonConverterFactory.create()) update the local DB, room for example
    */
    internal fun getTicketsFromNetwork(): Flowable<Tickets> {
        return Flowable.just(getTicketsObject(networkTicketsJson))
            .delay(2000, TimeUnit.MILLISECONDS)
    }

    internal fun getTicketsObject(ticketsJson: String): Tickets {
        var data:Tickets
        try {
            val gson = Gson()
            data = gson.fromJson<Tickets>(ticketsJson, Tickets::class.java)
        } catch (e: Throwable) {
//            Log.d("sm", "=-0=-0=-0 ERRROR ${e.message}")
            //should have an error rxevent to send up to ui
            data = Tickets()
        }
        return data
    }
}