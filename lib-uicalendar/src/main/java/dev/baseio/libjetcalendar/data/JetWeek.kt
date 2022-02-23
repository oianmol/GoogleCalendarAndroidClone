package dev.baseio.libjetcalendar.data

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.*


fun dayNames(dayOfWeek: DayOfWeek): List<String> {
  val days = mutableListOf<DayOfWeek>()
  days.add(dayOfWeek)
  while (days.size != 7) {
    days.add(days.last().plus(1))
  }
  return days.map {
    it.getDisplayName(
      TextStyle.NARROW,
      Locale.getDefault()
    )
  }
}

class JetWeek private constructor(
  val startDate: LocalDate,
  val endDate: LocalDate,
  val monthOfWeek: Int,
  val dayOfWeek: DayOfWeek,
) : JetCalendarType() {
  lateinit var days: List<JetDay>

  companion object {
    fun current(
      date: LocalDate = LocalDate.now(),
      dayOfWeek: DayOfWeek
    ): JetWeek {
      val startOfCurrentWeek: LocalDate =
        date.with(TemporalAdjusters.previousOrSame(dayOfWeek))
      val lastDayOfWeek = dayOfWeek.plus(6) // or minus(1)
      val endOfWeek: LocalDate = date.with(TemporalAdjusters.nextOrSame(lastDayOfWeek))
      val week = JetWeek(startOfCurrentWeek, endOfWeek, date.monthValue, dayOfWeek)
      week.days = week.dates()
      return week
    }
  }

  fun dates(): List<JetDay> {
    val days = mutableListOf<JetDay>()
    val isPart = startDate.monthValue == this.monthOfWeek
    days.add(startDate.toJetDay(isPart))
    while (days.size != 7) {
      days.add(days.last().nextDay(this))
    }
    return days
  }

}


fun LocalDate.toJetDay(isPart: Boolean): JetDay {
  return JetDay(this, isPart)
}

private fun JetDay.nextDay(jetWeek: JetWeek): JetDay {
  val date = this.date.plusDays(1)
  val isPartOfMonth = this.date.plusDays(1).monthValue == jetWeek.monthOfWeek
  return JetDay(date, isPartOfMonth)
}

fun JetWeek.nextWeek(): JetWeek {
  val firstDay = this.endDate.plusDays(1)
  val week = JetWeek.current(firstDay, dayOfWeek = dayOfWeek)
  week.days = week.dates()
  return week
}

