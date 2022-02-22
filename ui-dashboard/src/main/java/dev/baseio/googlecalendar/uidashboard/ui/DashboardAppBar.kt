package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
      CalendarMonthPicker()
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
fun CalendarMonthPicker() {
  Text("February")
}
