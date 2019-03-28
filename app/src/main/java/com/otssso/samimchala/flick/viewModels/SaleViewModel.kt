package com.otssso.samimchala.flick.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.otssso.samimchala.flick.models.*
import com.otssso.samimchala.flick.utils.formatPrice

class SaleViewModel(
    var selectedMovieLiveData: MutableLiveData<Int> = SharedViewModel.selectedMovie,
    var ticketsLiveData: MutableLiveData<Tickets> = SharedViewModel.ticketsLiveData
) : ViewModel() {

    internal var tickets: Tickets = Tickets()
    internal var counter = 0
    internal val basket: Basket = Basket()
    internal var ticketTypeIndex: Int = 0 // standard ticket is selected by default
    internal var ticketExtraIndex: Int = -1
    internal var selectedDay: Int = -1
    internal val all = "all"
    internal val pg15 = "15"
    internal val concession = "Concession"
    val makePaymentEvent = MutableLiveData<String>()
    val errorEvent = MutableLiveData<Int>()
    val cinema = MutableLiveData<String>()
    val movie = MutableLiveData<String>()
    val pg = MutableLiveData<String>()
    val quantity = MutableLiveData<Int>()
    val totalAmount = MutableLiveData<String>()
    val totalSaved = MutableLiveData<String>()
    var ticketsCount = MutableLiveData<Int>().apply { 0 }
    val canIncreaseQuantity: LiveData<Boolean> = map(quantity) { if (it != null) it < 99 else false }
    val canDecreaseQuantity: LiveData<Boolean> = map(quantity) { if (it != null) it > 0 else false }

    init {
        quantity.value = counter
        ticketsLiveData.value?.let {
            tickets = it
            cinema.value = "Location: ${it.cinemaName}"
            selectedMovieLiveData.value?.let { selectedMovie ->
                movie.value = "Movie: ${it.movies[selectedMovie].name}"
                pg.value = "PG: ${it.movies[selectedMovie].pg}"
            }
        }
        ticketsCount.value = 0
    }

    internal fun addOffer(day: Day): String {
        tickets.offers.map {
            if (it.discountDay == day.id) {
                return it.name
            }
        }
        return ""
    }

    internal fun addTicket(checkedId: Int) {
        ticketTypeIndex = checkedId
    }

    internal fun addExtra(checkedId: Int) {
        ticketExtraIndex = checkedId
    }

    internal fun addSelectedDay(checkedId: Int) {
        selectedDay = extractDaysIndexFromRadioButtonIndex(checkedId)
    }

    private fun extractDaysIndexFromRadioButtonIndex(checkedId: Int): Int {
        var day: Int = -1
        selectedMovieLiveData.value?.let {
            day = tickets.movies[it].days[checkedId].id
        }
        return day
    }

    fun addToBasket(view: View) {
        populateBasket()
    }

    var oldQuantity:Int? = 0
    internal fun populateBasket() {
        if (null != quantity.value && selectedDay > -1) {
            // ticketExtras are optional, if none selected we still have to handle that
            val ticketsExtra = when {
                ticketExtraIndex >= 0 -> tickets.ticketsExtra[ticketExtraIndex]
                else -> TicketsExtra()
            }

            quantity.value?.let {


                val lineItemSale = LineItemSale(
                    it,
                    tickets.ticketsType[ticketTypeIndex],
                    ticketsExtra,
                    tickets.offers,
                    selectedDay,
                    extractDayId()
                )
                basket.tickets.add(lineItemSale)

                //this formatting should be in the ui, maybe a binding adapter function?
                // want to be able to move the text where it belongs :)
                totalAmount.value = "Total: ${basket.getTotalWithoutDiscount()
                    .formatPrice(tickets.currency)}"

                //should calculate the actual saving
                totalSaved.value = "Savings: ${basket.getDiscountedTotal()
                    .formatPrice(tickets.currency)}"

                //it's late! :) should be done in the basket
                quantity.value?.let {
                    var totalTickets = when (basket.getTotalTicketQuantity()) {
                        0 -> it
                        else -> basket.getTotalTicketQuantity()
                    }
                    ticketsCount.value = totalTickets
                }

            }
        } else {
            //should send appropriate errors event
            errorEvent.value = 3333
        }
    }

    private fun extractDayId(): Int {
        val day = DayMapper.getDayByName(selectedDay)
        selectedMovieLiveData.value?.let {
            tickets.movies[it].days.map {
                if (it.day == day) {
                    return it.id
                }
            }
        }
        return -1
    }

    fun increaseQuantity(view: View) {
        quantity.value = ++counter
    }

    fun decreaseQuantity(view: View) {
        quantity.value = --counter
    }

    fun isAllowed(ticketType: String): Boolean {
        var isAllowed = false
        selectedMovieLiveData.value?.let {
            isAllowed = tickets.movies[it].pg == all ||
                    !(tickets.movies[it].pg == pg15 && ticketType == concession)
        }
        return isAllowed
    }

    fun makePayment(view: View) {
        basket.clear()
        totalAmount.value = null
        totalSaved.value = null
        ticketsCount.value = null
        makePaymentEvent.value = "done! :)"
    }
}