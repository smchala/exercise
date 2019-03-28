package com.otssso.samimchala.flick.models.data

import com.otssso.samimchala.flick.models.*

object TicketsData{
    var localTicketsJson: String = "{\n" +
            "  \"cinemaId\": \"123456\",\n" +
            "  \"cinemaName\": \"Flick Cinema\",\n" +
            "  \"timeStamp\": \"1521815700\",\n" +
            "  \"currency\": \"GBP\",\n" +
            "  \"version\": 1,\n" +
            "  \"ticketsType\": [\n" +
            "    {\n" +
            "      \"name\": \"Stansdard\",\n" +
            "      \"price\": 790\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Concession\",\n" +
            "      \"price\": 540\n" +
            "    }\n" +
            "  ],\n" +
            "  \"ticketsExtra\": [\n" +
            "    {\n" +
            "      \"name\": \"Real3D\",\n" +
            "      \"price\": 90\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"IMAX\",\n" +
            "      \"price\": 150\n" +
            "    }\n" +
            "  ],\n" +
            "  \"offers\": [\n" +
            "    {\n" +
            "      \"offerType\": \"LINE_ITEM_DISCOUNT\",\n" +
            "      \"discountType\": \"QUANTITY\",\n" +
            "      \"name\": \"Three for One Thursdays\",\n" +
            "      \"discountDays\": 3,\n" +
            "      \"discount\": 3,\n" +
            "      \"description\": \"buy one get two free for tickets purchased on Thursdays\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"movies\": [\n" +
            "    {\n" +
            "      \"name\": \"movie 1\",\n" +
            "      \"days\": [\n" +
            "        {\n" +
            "          \"day\": \"Monday\",\n" +
            "          \"id\": 0,\n" +
            "          \"time\": \"18:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Tuesday\",\n" +
            "          \"id\": 1,\n" +
            "          \"time\": \"18:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Thursday\",\n" +
            "          \"id\": 3,\n" +
            "          \"time\": \"18:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Saturday\",\n" +
            "          \"id\": 5,\n" +
            "          \"time\": \"18:00\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"pg\": \"all\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    var networkTicketsJson: String = "{\n" +
            "  \"cinemaId\": \"123456\",\n" +
            "  \"cinemaName\": \"Flick Cinema\",\n" +
            "  \"timestamp\": \"1521815700\",\n" +
            "  \"currency\": \"GBP\",\n" +
            "  \"version\": 1,\n" +
            "  \"ticketsType\": [\n" +
            "    {\n" +
            "      \"name\": \"Stansdard\",\n" +
            "      \"price\": 790\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Concession\",\n" +
            "      \"price\": 540\n" +
            "    }\n" +
            "  ],\n" +
            "  \"ticketsExtra\": [\n" +
            "    {\n" +
            "      \"name\": \"Real3D\",\n" +
            "      \"price\": 90\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"IMAX\",\n" +
            "      \"price\": 150\n" +
            "    }\n" +
            "  ],\n" +
            "  \"offers\": [\n" +
            "    {\n" +
            "      \"offerType\": \"LINE_ITEM_DISCOUNT\",\n" +
            "      \"discountType\": \"QUANTITY\",\n" +
            "      \"name\": \"OFFER: Three for One Thursdays\",\n" +
            "      \"discountDay\": 3,\n" +
            "      \"discount\": 3,\n" +
            "      \"description\": \"buy one get two free for tickets purchased on Thursdays\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"movies\": [\n" +
            "    {\n" +
            "      \"name\": \"movie 1\",\n" +
            "      \"days\": [\n" +
            "        {\n" +
            "          \"day\": \"Monday\",\n" +
            "          \"id\": 0,\n" +
            "          \"time\": \"18:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Tuesday\",\n" +
            "          \"id\": 1,\n" +
            "          \"time\": \"18:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Thursday\",\n" +
            "          \"id\": 3,\n" +
            "          \"time\": \"18:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Saturday\",\n" +
            "          \"id\": 5,\n" +
            "          \"time\": \"18:00\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"pg\": \"all\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"movie 2\",\n" +
            "      \"days\": [\n" +
            "        {\n" +
            "          \"day\": \"Tuesday\",\n" +
            "          \"id\": 1,\n" +
            "          \"time\": \"21:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Thursday\",\n" +
            "          \"id\": 3,\n" +
            "          \"time\": \"21:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Saturday\",\n" +
            "          \"id\": 5,\n" +
            "          \"time\": \"21:00\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"pg\": \"15\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"movie 3\",\n" +
            "      \"days\": [\n" +
            "        {\n" +
            "          \"day\": \"Tuesday\",\n" +
            "          \"id\": 1,\n" +
            "          \"time\": \"21:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Thursday\",\n" +
            "          \"id\": 3,\n" +
            "          \"time\": \"21:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Saturday\",\n" +
            "          \"id\": 5,\n" +
            "          \"time\": \"21:00\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"pg\": \"all\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"movie 4\",\n" +
            "      \"days\": [\n" +
            "        {\n" +
            "          \"day\": \"Tuesday\",\n" +
            "          \"id\": 1,\n" +
            "          \"time\": \"21:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Thursday\",\n" +
            "          \"id\": 3,\n" +
            "          \"time\": \"21:00\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"day\": \"Saturday\",\n" +
            "          \"id\": 5,\n" +
            "          \"time\": \"21:00\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"pg\": \"15\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    val expectedTickets = Tickets(
        cinemaId = "123456",
        cinemaName = "Flick Cinema",
        timeStamp = "1521815700",
        currency = "GBP",
        version = 1,
        ticketsType = arrayOf(
            TicketsType(name = "Stansdard", price = 790),
            TicketsType(name = "Concession", price = 540)
        ),
        ticketsExtra = arrayOf(TicketsExtra(name = "Real3D", price = 90), TicketsExtra(name = "IMAX", price = 150)),
        offers = arrayOf(
            Offer(
                name = "OFFER: Three for One Thursdays",
                offerType = Offer.OfferTypes.LINE_ITEM_DISCOUNT,
                description = "buy one get two free for tickets purchased on Thursdays",
                discount = 3.0,
                discountDay = 3,
                discountType = Offer.DiscountTypes.QUANTITY
            )
        ),
        movies = arrayOf(
            Movie(
                name = "movie 1", pg = "all",
                days = arrayOf(
                    Day(day = "Monday", time = "18:00", id = 0),
                    Day(day = "Tuesday", time = "18:00", id = 1),
                    Day(day = "Thursday", time = "18:00", id = 3),
                    Day(day = "Saturday", time = "18:00", id = 5)
                )
            ),
            Movie(
                name = "movie 2",
                pg = "15",
                days = arrayOf(
                    Day(day = "Tuesday", time = "21:00", id = 1),
                    Day(day = "Thursday", time = "21:00", id = 3),
                    Day(day = "Saturday", time = "21:00", id = 5)
                )
            ),
            Movie(
                name = "movie 3",
                pg = "all",
                days = arrayOf(
                    Day(day = "Tuesday", time = "21:00", id = 1),
                    Day(day = "Thursday", time = "21:00", id = 3),
                    Day(day = "Saturday", time = "21:00", id = 5)
                )
            ),
            Movie(
                name = "movie 4",
                pg = "15",
                days = arrayOf(
                    Day(day = "Tuesday", time = "21:00", id = 1),
                    Day(day = "Thursday", time = "21:00", id = 3),
                    Day(day = "Saturday", time = "21:00", id = 5)
                )
            )
        )
    )
}