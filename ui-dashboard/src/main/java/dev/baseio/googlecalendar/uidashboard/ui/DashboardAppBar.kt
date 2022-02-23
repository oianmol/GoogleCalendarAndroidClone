package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider

@Composable
fun DashboardAppBar(toggleDrawer: () -> Unit) {
  SmallTopAppBar(
    colors = TopAppBarDefaults.smallTopAppBarColors(
      containerColor = GoogleCalendarColorProvider.colors.appBarColor,
      navigationIconContentColor = GoogleCalendarColorProvider.colors.appBarIconColor,
      titleContentColor = GoogleCalendarColorProvider.colors.appBarTextTitleColor,
      actionIconContentColor = GoogleCalendarColorProvider.colors.appBarIconColor
    ), title = {
      CalendarMonthPicker {}
    }, actions = {
      IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.Search, contentDescription = null)
      }
      IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.Settings, contentDescription = null)
      }
      IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.Person, contentDescription = null)
      }
    }, navigationIcon = {
      IconButton(onClick = {
        toggleDrawer.invoke()
      }) {
        Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = "null"
        )
      }
    }
  )
}


@Composable
fun CalendarMonthPicker(onToggle: () -> Unit) {
  Row(Modifier.clickable {
    onToggle.invoke()
  }, verticalAlignment = Alignment.CenterVertically) {
    Text("February")
    Icon(
      Icons.Filled.ArrowDropDown,
      contentDescription = null,
      modifier = Modifier.padding(start = 4.dp)
    )
  }
}
