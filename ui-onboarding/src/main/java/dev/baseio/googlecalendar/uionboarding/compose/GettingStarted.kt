package dev.baseio.googlecalendar.uionboarding.compose

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.googlecalendar.commonui.theme.*
import dev.baseio.googlecalendar.navigator.ComposeNavigator
import dev.baseio.googlecalendar.uionboarding.R

@Composable
fun GettingStartedUI(composeNavigator: ComposeNavigator) {
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
            .padding(28.dp)
        ) {
          Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .fillMaxHeight()
              .fillMaxWidth()
          ) {
            Spacer(Modifier.padding(8.dp))
            CenterImage()
            Spacer(Modifier.padding(8.dp))
            GoogleCalendarOnboardingText()
            Spacer(Modifier.padding(8.dp))
          }
        }
      }
    }
  }
}

@Composable
private fun GoogleCalendarOnboardingText() {
  var expanded by remember { mutableStateOf(false) }
  LaunchedEffect(Unit) {
    expanded = !expanded
  }
  AnimatedVisibility(
    expanded, enter = fadeIn(
      // Fade in with the initial alpha of 0.3f.
      initialAlpha = 0.3f, animationSpec = tween(durationMillis = 2000)
    )
  ) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = stringResource(id = R.string.google_calendar),
        style = GoogleCalendarTypography.h5.copy(color = GoogleCalendarColorProvider.colors.textPrimary)
      )
      Spacer(modifier = Modifier.height(24.dp))
      Text(
        text = stringResource(id = R.string.make_most_everyday),
        style = GoogleCalendarTypography.subtitle1.copy(color = GoogleCalendarColorProvider.colors.textSecondary)
      )
    }
  }

}

@Composable
private fun CenterImage() {
  Image(
    painter = painterResource(id = R.drawable.gettingstarted),
    contentDescription = "Logo",
    Modifier
  )
}

