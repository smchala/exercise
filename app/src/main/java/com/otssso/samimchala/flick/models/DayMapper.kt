package com.otssso.samimchala.flick.models

object DayMapper{
    enum class DayEnum(val day: Int) {
        Monday(0),
        Tuesday(1),
        Wednesday(2),
        Thursday(3),
        Friday(4),
        Saturday(5),
        Sunday(6)
    }

    fun getDayByName(index: Int) = when(index){
        DayEnum.Monday.day -> DayEnum.Monday.toString()
        DayEnum.Tuesday.day -> DayEnum.Tuesday.toString()
        DayEnum.Wednesday.day -> DayEnum.Wednesday.toString()
        DayEnum.Thursday.day -> DayEnum.Thursday.toString()
        DayEnum.Friday.day -> DayEnum.Friday.toString()
        DayEnum.Saturday.day -> DayEnum.Saturday.toString()
        DayEnum.Sunday.day -> DayEnum.Sunday.toString()
        else -> ""
    }
}
