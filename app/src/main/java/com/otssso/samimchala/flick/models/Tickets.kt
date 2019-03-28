package com.otssso.samimchala.flick.models

/*
*	networkTickets.json:
*
*	represents the response from restful api call for a particular cinema.
*	Ideally we would have the movies catalogue and offers on a separate call, as ticket type and extras rarely changes
*
*	Assumption:
*	in this case I am applying concessions for children younger than 15 years old, the rest will pay standard rate
*	having ticketsType, ticketsExtra, offers as collection we may have multiple new types of pricing/offers that may be added in the future
*/
data class Tickets(
    val cinemaId: String? = null,
    val cinemaName: String? = null,
    val timeStamp: String? = null,
    val currency: String = "GBP",
    val version: Int? = null,
    val ticketsType: Array<TicketsType> = arrayOf(),
    val ticketsExtra: Array<TicketsExtra> = arrayOf(),
    val offers: Array<Offer> = arrayOf(),
    val movies: Array<Movie> = arrayOf()
)








