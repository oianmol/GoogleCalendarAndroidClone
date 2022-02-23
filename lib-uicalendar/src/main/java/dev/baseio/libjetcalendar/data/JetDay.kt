package dev.baseio.libjetcalendar.data

import java.time.LocalDate

open class JetCalendarType

data class JetDay(val date: LocalDate, val isPartOfMonth: Boolean) : JetCalendarType()