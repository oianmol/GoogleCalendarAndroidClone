package dev.baseio.googlecalendar.navigator

import androidx.navigation.NamedNavArgument

sealed class GoogleCalendarScreen(
  val route: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val name: String = route.appendArguments(navArguments)

  // onboarding
  object GettingStarted : GoogleCalendarScreen("gettingStarted")
  object Dashboard : GoogleCalendarScreen("Dashboard")

}

sealed class GoogleCalendar(val name: String) {
  object OnBoarding : GoogleCalendar("onboardingFeat")
  object Dashboard : GoogleCalendar("DashboardFeat")

}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
  val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
    .orEmpty()
  val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
    .orEmpty()
  return "$this$mandatoryArguments$optionalArguments"
}