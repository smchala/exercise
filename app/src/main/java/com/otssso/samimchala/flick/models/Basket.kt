package com.otssso.samimchala.flick.models

/*
could have added functions to calculate discount basket level, but I already done it on item level...
 */
class Basket(val tickets: ArrayList<LineItemSale> = arrayListOf()) : Sale() {

    fun getTotalWithoutDiscount(): Int {
        return tickets
            .filter { it.totalAmountToPurchase >= 0 }
            .map { it.totalAmountToPurchase }
            .sum()
            .times(100)
            .toInt()
    }

    fun getDiscountedTotal(): Int {
        return tickets
            .filter { it.totalDiscountedAmountToPurchase >= 0 }
            .map { it.totalDiscountedAmountToPurchase }
            .sum()
            .times(100)
            .toInt()
    }

    fun getTotalTicketQuantity(): Int {
        return tickets
            .filter { it.totalQuantityToPurchase >= 0 }
            .map { it.totalQuantityToPurchase }
            .sum()
    }

    override fun clear() {
        tickets.clear()
    }
}

