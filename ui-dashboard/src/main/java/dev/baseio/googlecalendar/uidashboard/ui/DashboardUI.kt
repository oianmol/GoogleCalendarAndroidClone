package dev.baseio.googlecalendar.uidashboard.ui

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.googlecalendar.commonui.reusable.animateDrag
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTheme
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTypography
import dev.baseio.googlecalendar.navigator.ComposeNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class CalendarExpansion { Collapsed, Expanded }

@OptIn(
  ExperimentalMaterial3Api::class, androidx.compose.material.ExperimentalMaterialApi::class,
  androidx.compose.animation.ExperimentalAnimationApi::class
)
@Composable
fun DashboardUI(composeNavigator: ComposeNavigator) {
  GoogleCalendarTheme {
    val colors = GoogleCalendarColorProvider.colors

    val view = LocalView.current

    SideEffect {
      (view.context as Activity).window.statusBarColor = colors.appBarColor.toArgb()
      (view.context as Activity).window.navigationBarColor = colors.uiBackground.toArgb()
      ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !colors.isDark
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val monthExpanded = remember {
      mutableStateOf(CalendarExpansion.Collapsed)
    }

    NavigationDrawer(
      drawerContent = {
        DashboardDrawer()
      },
      drawerState = drawerState,
      drawerContainerColor = GoogleCalendarColorProvider.colors.appBarColor,
      modifier = Modifier.padding(),
    ) {
      Scaffold(
        containerColor = GoogleCalendarColorProvider.colors.uiBackground,
        contentColor = GoogleCalendarColorProvider.colors.textPrimary,
        modifier = Modifier
          .statusBarsPadding()
          .navigationBarsPadding(),
        topBar = {
          DashboardAppBar({
            switchDrawer(scope, drawerState)
          }, {
            monthExpanded.value = monthExpanded.value.toggle()
          })
        },
      ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
          GoogleCalendarSurface {
            Column(
              modifier = Modifier
                .fillMaxSize()
            ) {
              AnimatedVisibility(monthExpanded.value.isExpanded()) {
                DashboardMonthView(Modifier.fillMaxWidth())
              }
              Box {
                CalendarEventsCards()
                if (monthExpanded.value == CalendarExpansion.Expanded){
                  DragWhenCalendarExpanded { expansion ->
                    monthExpanded.value = expansion
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun DragWhenCalendarExpanded(expanded: (CalendarExpansion) -> Unit) {
  Box(
    Modifier
      .fillMaxSize()
      .animateDrag({
        expanded(CalendarExpansion.Collapsed)
      }, {
        expanded(CalendarExpansion.Expanded)
      })
  )
}

private fun CalendarExpansion.isExpanded(): Boolean {
  return this == CalendarExpansion.Expanded
}

private fun CalendarExpansion.toggle(): CalendarExpansion {
  if (this == CalendarExpansion.Expanded) {
    return CalendarExpansion.Collapsed
  }
  return CalendarExpansion.Expanded
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarEventsCards() {
  LazyColumn(
    modifier = Modifier
  ) {
    items(5) {
      Card(
        modifier = Modifier
          .height(250.dp)
          .fillMaxWidth()
          .padding(16.dp),
        containerColor = GoogleCalendarColorProvider.colors.buttonColor
      ) {
        Text(
          text = "Event",
          style = GoogleCalendarTypography.subtitle1.copy(GoogleCalendarColorProvider.colors.buttonTextColor)
        )
      }
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
private fun switchDrawer(
  scope: CoroutineScope,
  drawerState: DrawerState
) {
  scope.launch {
    if (drawerState.isClosed) {
      drawerState.open()
    } else {
      drawerState.close()
    }
  }
}




