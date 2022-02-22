package dev.baseio.googlecalendar.uionboarding.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.baseio.googlecalendar.navigator.ComposeNavigator
import dev.baseio.googlecalendar.navigator.GoogleCalendar
import dev.baseio.googlecalendar.navigator.GoogleCalendarScreen
import dev.baseio.googlecalendar.uionboarding.compose.GettingStartedUI

fun NavGraphBuilder.onboardingNavigation(
  composeNavigator: ComposeNavigator,
) {
  navigation(
    startDestination = GoogleCalendarScreen.GettingStarted.name,
    route = GoogleCalendar.OnBoarding.name
  ) {
    composable(GoogleCalendarScreen.GettingStarted.name) {
      GettingStartedUI(composeNavigator)
    }
  }

}