package com.otssso.samimchala.flick.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt

class LineItemSaleTest {

    private lateinit var sut: LineItemSale

    private val ticketsType = TicketsType("", 0)
    private val ticketsExtra = TicketsExtra("", 0)
    private val offers = arrayOf(
        Offer(
            "",
            Offer.OfferTypes.LINE_ITEM_DISCOUNT,
            "",
            2.0,
            0,
            Offer.DiscountTypes.QUANTITY
        )
    )

    @BeforeEach
    fun setUp() {
        sut = LineItemSale(anyInt(), ticketsType, ticketsExtra, offers, anyInt(), anyInt())
    }

    @Test
    fun `check we get the correct discountTotal`() {
        assertEquals (Pair(2, 100.0), sut.getDiscountsTotal(1, 100.0, 2.0, 0, 0))
    }

    @Test
    fun `check we get the correct getTotalWithoutDiscount`() {
        assertEquals(1.02, sut.getTotalWithoutDiscount(1, 100, 2))
    }

    @Test
    fun `check we don't get the discountTotal if not allowed`() {
        assertEquals( Pair(0, 0.0), sut.getDiscountsTotal(1, 100.0, 2.0, 1, 0))
    }
}