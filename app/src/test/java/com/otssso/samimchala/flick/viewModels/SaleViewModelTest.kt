package com.otssso.samimchala.flick.viewModels

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.otssso.samimchala.flick.models.Day
import com.otssso.samimchala.flick.models.data.TicketsData
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class SaleViewModelTest {

    private lateinit var sut: SaleViewModel

    @Before
    fun setUp() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })

        sut = SaleViewModel()
        sut.tickets = TicketsData.expectedTickets
    }

    @Test
    fun `check getting an offer for a specific day`() {
        val day = Day("Thursday", "21:00", 3)
        assertEquals("OFFER: Three for One Thursdays", sut.addOffer(day))
    }

    @Test
    fun `check that with no discounted day there are no offer added`() {
        val day = Day("Wednesday", "21:00", 2)
        assertEquals("", sut.addOffer(day))
    }

    @Test
    fun `check that addTicket field is empty initially then set`() {
        assertEquals(0, sut.ticketTypeIndex)
        sut.addTicket(1)
        assertEquals(1, sut.ticketTypeIndex)
    }

    @Test
    fun `check the selected day gets converted to the correct index, where sunday is zero,monday is one, etc `() {
        sut.ticketsLiveData.value = TicketsData.expectedTickets
        sut.selectedMovieLiveData.value = 1
        sut.addSelectedDay(1)//index of radio buttons

        assertEquals(3, sut.selectedDay)
    }

    @Test
    fun `check once add to basked get clicked, the basket calculates and format the result`() {
        sut.ticketsLiveData.value = TicketsData.expectedTickets
        sut.tickets = TicketsData.expectedTickets
        sut.selectedMovieLiveData.value = 3
        sut.selectedDay = 3
        sut.quantity.value = 3
        sut.ticketExtraIndex = 1

        sut.populateBasket()

        assertEquals(9, sut.basket.getTotalTicketQuantity())
        assertEquals(2820, sut.basket.getTotalWithoutDiscount())
    }
}