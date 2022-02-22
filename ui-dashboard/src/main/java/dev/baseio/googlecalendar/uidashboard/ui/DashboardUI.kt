package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTheme
import dev.baseio.googlecalendar.navigator.ComposeNavigator

@Composable
fun DashboardUI(composeNavigator: ComposeNavigator) {
  GoogleCalendarTheme {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
      backgroundColor = GoogleCalendarColorProvider.colors.uiBackground,
      contentColor = GoogleCalendarColorProvider.colors.textPrimary,
      modifier = Modifier.statusBarsPadding(), scaffoldState = scaffoldState, snackbarHost = {
        scaffoldState.snackbarHostState
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {
        GoogleCalendarSurface(
          modifier = Modifier
            .padding(12.dp)
        ) {

        }
      }
    }
  }
}