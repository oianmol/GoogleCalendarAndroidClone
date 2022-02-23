package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface
import dev.baseio.libjetcalendar.data.JetDay
import dev.baseio.libjetcalendar.data.JetMonth
import dev.baseio.libjetcalendar.monthly.JetCalendarMonthlyView
import dev.baseio.libjetcalendar.monthly.WeekNames
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun DashboardMonthView() {
  GoogleCalendarSurface(
    color = GoogleCalendarColorProvider.colors.appBarColor
  ) {
    Column {
      WeekNames(DayOfWeek.SUNDAY)
      JetCalendarMonthlyView(
        jetMonth = JetMonth.current(),
        onDateSelected = {},
        selectedDates = hashSetOf(JetDay(LocalDate.now(), true))
      )
    }
  }

}