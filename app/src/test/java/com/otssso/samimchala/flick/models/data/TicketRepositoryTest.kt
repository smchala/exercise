package com.otssso.samimchala.flick.models.data

import com.otssso.samimchala.flick.models.Tickets
import com.otssso.samimchala.flick.models.data.TicketsData.expectedTickets
import io.reactivex.subscribers.TestSubscriber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations

class TicketRepositoryTest {

    private lateinit var sut: TicketRepository
    private var observer = TestSubscriber<Tickets>()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = TicketRepository(TicketsData.networkTicketsJson, TicketsData.localTicketsJson)
    }

    @Test
    fun `check we are getting tickets information when we parse a json string`() {
        val expected = sut.getTicketsObject(TicketsData.networkTicketsJson)
        assertEquals(expectedTickets.cinemaName, expected.cinemaName)
        //... could check all fields to make sure it adhere's to
    }

    @Test
    fun `check we are getting an empty ticket object when we parse an invalid string`() {
        val expected = sut.getTicketsObject("-09-09")
        assertEquals(null, expected.cinemaName)
        assertEquals( "GBP", expected.currency)
        assertEquals(0, expected.offers.size)
        //... could check all fields to make sure it adhere's to
    }

    @Test
    fun `check we are getting an empty ticket object when we parse an empty string to a Tickets object`() {
        val expected = sut.getTicketsObject("")
        assertEquals(null, expected.cinemaName)
        //... could check all fields to make sure it adhere's to
    }

    @Test
    fun `check we are getting tickets information when we invoke network data`() {
        val observer = sut.getTicketsFromNetwork().test()
        observer.awaitTerminalEvent()
        assertEquals( expectedTickets.cinemaName, observer.values()[0].cinemaName)
        observer.assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun `check we are getting tickets information when we invoke local data`() {
        val observer = sut.getTicketsFromLocalStorage().test()
        observer.awaitTerminalEvent()
        assertEquals( expectedTickets.cinemaName, observer.values()[0].cinemaName)
        observer.assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun `check we are getting tickets observable when we call getTickets`() {
        observer = sut.getTicketsFromNetwork().test()
        observer.awaitTerminalEvent()
        assertEquals(expectedTickets.cinemaName, observer.values()[0].cinemaName)
        observer.assertComplete()
            .assertNoErrors()
            .dispose()
    }

    @Test
    fun `check we are getting the latest tickets from network and not stale local data`() {
        observer = sut.getTicketsFromNetwork().test()
        observer.awaitTerminalEvent()
        assertEquals(4, observer.values()[0].movies.size)//network data has 4 movies, local only 1
        observer.assertComplete()
            .assertNoErrors()
            .dispose()
    }
}
