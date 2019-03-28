package com.otssso.samimchala.flick.models

/*
having the  val discountDays:Array<Int>, allow to apply a discount to a specific day of the week
 its a collection of ints where monday is 0 ....to Sunday index 6,
 in this instance the json have only one index 3, thursday as per requirement
 */
class LineItemSale(
    private val quantity: Int,
    ticketsType: TicketsType,
    ticketsExtra: TicketsExtra,
    private val offers: Array<Offer>,
    selectedDay: Int,
    selectedMovieDay: Int
) : Sale() {

    var totalQuantityToPurchase: Int = 0
    var totalAmountToPurchase: Double = 0.0
    var totalDiscountedAmountToPurchase: Double = 0.0

    init {
        totalAmountToPurchase = getTotalWithoutDiscount(quantity, ticketsType.price, ticketsExtra.price)

        //only pass the appropriate offer, each day 1 offer
        if (isDiscountAllowed(offers, selectedDay, selectedMovieDay)) {
            offers.map { offer ->
                val discountsTotal = getDiscountsTotal(
                    quantity,
                    totalAmountToPurchase,
                    offer.discount,
                    selectedDay,
                    offer.discountDay//assuming for now we only have one day -> 1 discount
                )
                totalQuantityToPurchase += discountsTotal.first

                totalDiscountedAmountToPurchase += discountsTotal.second
            }
        }else{
            totalQuantityToPurchase =quantity
        }
    }

    /*
    get Total Per Line Item Without Discount
     */
    internal fun getTotalWithoutDiscount(quantity: Int, ticketsTypePrice: Int, ticketsExtraPrice: Int): Double =
        (quantity.times(ticketsTypePrice + ticketsExtraPrice)).toDouble() / 100

    /*
    get Total Per Line Item With Discount

    assumption: we only cater for one discount day for now
     */
    internal fun getDiscountsTotal(
        quantity: Int,
        totalWithoutDiscount: Double,
        discount: Double,
        selectedDay: Int,
        discountDay: Int
    ): Pair<Int, Double> {
        var newQuantity = 0
        var newAmount = 0.0
        // apply all discounts to the line item
        offers.map { offer ->
            //only have 2 types of discounts for now, quantity and amount
            when (offer.discountType) {
                Offer.DiscountTypes.QUANTITY -> {
                    //only apply discount for the selected day
                    if (selectedDay == discountDay) {
                        newQuantity += offer.discount.toInt() * quantity
                        newAmount += (totalWithoutDiscount * newQuantity)-totalWithoutDiscount
                    }
                }
                else -> {
                    //might need more work, but not used for now!
                    //only apply discount for the selected day
                    if (selectedDay == discountDay) {
                        newAmount += totalWithoutDiscount * 100 / discount
                        newQuantity += quantity
                    }
                }
            }
        }
        return Pair(newQuantity, newAmount)
    }

    /*
    * once purchase is done clear*/
    override fun clear() {
        totalQuantityToPurchase = 0
        totalAmountToPurchase = 0.0
        totalDiscountedAmountToPurchase = 0.0
    }
}