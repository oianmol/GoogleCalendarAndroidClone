package dev.baseio.googlecalendar.uidashboard.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.googlecalendar.navigator.ComposeNavigator
import dev.baseio.googlecalendar.navigator.GoogleCalendar
import dev.baseio.googlecalendar.navigator.GoogleCalendarScreen
import dev.baseio.googlecalendar.uidashboard.ui.DashboardUI

fun NavGraphBuilder.dashboardNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = GoogleCalendarScreen.Dashboard.name,
    route = GoogleCalendar.Dashboard.name
  ) {
    composable(GoogleCalendarScreen.Dashboard.name) {
      DashboardUI(composeNavigator)
    }
  }

}