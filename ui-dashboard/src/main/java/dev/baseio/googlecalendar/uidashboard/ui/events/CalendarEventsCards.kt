package dev.baseio.googlecalendar.uidashboard.ui.events

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarEventsCards() {
  LazyColumn(
    modifier = Modifier
  ) {
    for (item in 0..200) {
      when {
        item % 2 == 0 -> {
          item {
            CalendarCard(isAccepted = true)
          }
        }
        else -> {
          item {
            CalendarCard(isAccepted = false)
          }
        }
      }
    }
  }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarCard(isAccepted: Boolean) {
  Row(
    modifier = Modifier
      .padding(8.dp),
  ) {
    DateHeaderItem()
    EventCardInternal(isAccepted)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RowScope.EventCardInternal(isAccepted: Boolean) {
  Card(
    Modifier.Companion
      .weight(1f),
    shape = RoundedCornerShape(8.dp),
    border = BorderStroke(
      1.dp,
      if (!isAccepted) GoogleCalendarColorProvider.colors.uiBackground else GoogleCalendarColorProvider.colors.buttonColor
    ),
    containerColor = if (isAccepted) GoogleCalendarColorProvider.colors.uiBackground else GoogleCalendarColorProvider.colors.buttonColor
  ) {
    Column(Modifier.padding(8.dp)) {
      EventText(
        text = "Jetpack Compose Discussions, Brainstorming",
        style = textStyle(isAccepted)
      )

      EventText(
        text = "5:00 - 5:15 PM",
        style = textStyle(isAccepted)
      )
    }
  }
}

@Composable
fun DateHeaderItem() {
  Column(Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = "Mon", style = GoogleCalendarTypography.caption.copy(
        GoogleCalendarColorProvider.colors.buttonColor
      )
    )
    Box(
      modifier = Modifier
        .clip(CircleShape)
        .size(32.dp)
        .background(GoogleCalendarColorProvider.colors.buttonColor)
    ) {
      Text(
        text = "28", style = GoogleCalendarTypography.subtitle1.copy(
          GoogleCalendarColorProvider.colors.buttonTextColor
        ), modifier = Modifier.align(Alignment.Center)
      )
    }
  }
}

@Composable
private fun textStyle(isAccepted: Boolean) =
  if (!isAccepted) GoogleCalendarTypography.subtitle2.copy(
    GoogleCalendarColorProvider.colors.textPrimary
  ) else GoogleCalendarTypography.subtitle2.copy(
    GoogleCalendarColorProvider.colors.buttonColor
  )

@Composable
fun EventText(text: String, style: TextStyle) {
  Text(
    text = text,
    style = style
  )
}


