package com.otssso.samimchala.flick.models

data class Offer(
    val name: String,
    val offerType: OfferTypes,
    val description: String,
    val discount: Double,
    val discountDay: Int,
    val discountType: DiscountTypes
) {
    //Can discount at line item or basket level, in networkTickets.json I've set it to basket level
    //Also discount can be applied to price or quantity set in discountType of networkTickets.json
    enum class OfferTypes {
        BASKET_DISCOUNT,
        LINE_ITEM_DISCOUNT
    }

    //can discount on quantity like the one in requirements or by price reduction on the amount, assumption its a %
    enum class DiscountTypes {
        QUANTITY,
        AMOUNT
    }
}