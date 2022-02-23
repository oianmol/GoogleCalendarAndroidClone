package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface
import dev.baseio.libjetcalendar.data.JetDay
import dev.baseio.libjetcalendar.data.JetMonth
import dev.baseio.libjetcalendar.monthly.JetCalendarMonthlyView
import dev.baseio.libjetcalendar.monthly.WeekNames
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun DashboardMonthView(modifier: Modifier) {
  GoogleCalendarSurface(
    color = GoogleCalendarColorProvider.colors.appBarColor, modifier = modifier
  ) {
    Column(Modifier.fillMaxWidth()) {
      WeekNames(DayOfWeek.SUNDAY)
      JetCalendarMonthlyView(
        jetMonth = JetMonth.current(),
        onDateSelected = {},
        selectedDates = hashSetOf(JetDay(LocalDate.now(), true))
      )
    }
  }
}