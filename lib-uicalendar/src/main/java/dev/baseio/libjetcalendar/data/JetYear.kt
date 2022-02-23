package dev.baseio.libjetcalendar.data

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*


class JetYear private constructor(
  val startDate: LocalDate,
  val endDate: LocalDate,
) : JetCalendarType() {
  lateinit var yearMonths: List<JetMonth>

  companion object {
    fun current(
      date: LocalDate = LocalDate.now(),
      firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    ): JetYear {
      val day: LocalDate = date.with(TemporalAdjusters.firstDayOfYear())
      val last: LocalDate = date.with(TemporalAdjusters.lastDayOfYear())
      val year = JetYear(day, last)
      year.yearMonths = year.months(firstDayOfWeek)
      return year
    }
  }

  private fun months(firstDayOfWeek: DayOfWeek): List<JetMonth> {
    val months = mutableListOf<JetMonth>()

    var startDateMonth = this.startDate.withDayOfMonth(1)
    var endDateMonth = this.startDate.withDayOfMonth(this.startDate.lengthOfMonth())

    var currentYear = this.startDate.year
    while (true) {
      months.add(JetMonth.current(startDateMonth, firstDayOfWeek))

      startDateMonth = endDateMonth.plusDays(1)
      endDateMonth = startDateMonth.withDayOfMonth(startDateMonth.lengthOfMonth())
      if (endDateMonth.year > currentYear) {
        break
      }
      currentYear = endDateMonth.year
    }
    return months
  }

  fun year(): String {
    return this.startDate.year.toString()
  }
}
