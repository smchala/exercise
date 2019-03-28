package com.otssso.samimchala.flick.models

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations

class BasketTest {

    private lateinit var sut: Basket
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
    private var lineItemSale = LineItemSale(0, ticketsType, ticketsExtra, offers, 0, 0)
    private var lineItemSales = arrayListOf<LineItemSale>()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lineItemSale.totalQuantityToPurchase = 1
        lineItemSale.totalAmountToPurchase = 1.0
        lineItemSale.totalDiscountedAmountToPurchase = 1.0
        lineItemSales.add(lineItemSale)
        lineItemSales.add(lineItemSale)

        sut = Basket(lineItemSales)
    }

    @AfterEach
    fun tearDown() {
        lineItemSales.clear()
    }

    @Test
    fun `checking we can calculate total amount in the basket`() {
        assertEquals( 200, sut.getTotalWithoutDiscount())
    }

    @Test
    fun `checking we can calculate total discount in the basket`() {
        assertEquals(200, sut.getDiscountedTotal())
    }

    @Test
    fun `checking we can calculate total quantity in the basket`() {
        assertEquals(2, sut.getTotalTicketQuantity())
    }

    @Test
    fun `check if there are no line item sales in the basket we get zero`() {
        sut = Basket(arrayListOf())
        assertEquals(0, sut.getTotalTicketQuantity())
        assertEquals(0, sut.getDiscountedTotal())
        assertEquals(0, sut.getTotalTicketQuantity())
    }

    @Test
    fun `check if there are invalid values in the totals in line item sales in the basket we get zero`() {
        lineItemSale.totalQuantityToPurchase = -11
        lineItemSale.totalAmountToPurchase = -11.0
        lineItemSale.totalDiscountedAmountToPurchase = -11.0
        lineItemSales.clear()
        lineItemSales.add(lineItemSale)
        sut = Basket(lineItemSales)
        assertEquals(0, sut.getTotalTicketQuantity())
        assertEquals(0, sut.getDiscountedTotal())
        assertEquals(0, sut.getTotalTicketQuantity())
    }
}