package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
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
    val selectedDates = rememberSaveable(stateSaver = JetDaySaver) {
      mutableStateOf(JetDay(LocalDate.now(), true))
    }

    Column(Modifier.fillMaxWidth()) {
      WeekNames(DayOfWeek.SUNDAY)
      JetCalendarMonthlyView(
        jetMonth = JetMonth.current(),
        onDateSelected = {
          selectedDates.value = it
        },
        selectedDate = selectedDates.value
      )
    }
  }
}

val JetDaySaver =  run {
  val dateKey = "Date"
  val isPartKey = "IsPart"
  mapSaver(
    save = { mapOf(dateKey to it.date, isPartKey to it.isPartOfMonth) },
    restore = { JetDay(it[dateKey] as LocalDate, it[isPartKey] as Boolean)}
  )
}
