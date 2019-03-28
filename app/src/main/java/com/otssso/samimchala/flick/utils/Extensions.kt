package com.otssso.samimchala.flick.utils

import java.util.*

fun Int.formatPrice(currencyCode: String): String {
    val parsed = try {
        this.toDouble() / 100
    } catch (e: Throwable) {
        0.00
    }

    val currency = Currency.getInstance(currencyCode)
    val formatter = java.text.NumberFormat.getCurrencyInstance()
    formatter.currency = currency
    formatter.maximumFractionDigits = 2
    return formatter.format(parsed)
}

