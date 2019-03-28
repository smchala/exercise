package com.otssso.samimchala.flick.models

open abstract class Sale {

    /* checking 2 things:
       1. check if its the same type of discount, in this case line item discount
       2. check if it is on the day(s) that the discount apply
    */
    fun isDiscountAllowed(
        offers: Array<Offer>,
        selectedDay: Int,
        selectedMovieDay: Int
    ): Boolean {
        //maybe worth considering changing structure so we have 2 nested arrays so Array.flatten can be used!
        offers.forEach {
            return when (it.offerType) {
                Offer.OfferTypes.LINE_ITEM_DISCOUNT -> {
                    (selectedDay == it.discountDay) && (selectedMovieDay == it.discountDay)
                }
                else -> false
            }
        }
        return false
    }

    abstract fun clear()
}