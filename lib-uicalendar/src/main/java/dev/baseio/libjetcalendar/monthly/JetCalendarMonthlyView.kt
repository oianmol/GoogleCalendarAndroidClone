package dev.baseio.libjetcalendar.monthly

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTypography
import dev.baseio.libjetcalendar.data.*
import dev.baseio.libjetcalendar.weekly.JetCalendarWeekView
import java.time.DayOfWeek

@Composable
fun JetCalendarMonthlyView(
  jetMonth: JetMonth,
  onDateSelected: (JetDay) -> Unit,
  selectedDates: Set<JetDay>,
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(start = 4.dp, end = 4.dp),
    verticalArrangement = Arrangement.SpaceAround,
  ) {
    jetMonth.monthWeeks.forEach { week ->
      JetCalendarWeekView(
        modifier = Modifier.fillMaxWidth(),
        week = week,
        onDateSelected = onDateSelected,
        selectedDates = selectedDates
      )
    }
  }
}


@Composable
fun WeekNames(dayOfWeek: DayOfWeek) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, end = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    dayNames(dayOfWeek = dayOfWeek).forEach {
      Box(
        modifier = Modifier
          .padding(2.dp),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = it, modifier = Modifier.padding(2.dp),
          textAlign = TextAlign.Center,
          style = GoogleCalendarTypography.subtitle2
        )
      }

    }
  }
}