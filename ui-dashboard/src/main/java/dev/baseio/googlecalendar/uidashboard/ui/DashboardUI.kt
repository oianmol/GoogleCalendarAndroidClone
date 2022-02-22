package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTheme
import dev.baseio.googlecalendar.navigator.ComposeNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardUI(composeNavigator: ComposeNavigator) {
  GoogleCalendarTheme {
    val sysUiController = rememberSystemUiController()
    val colors = GoogleCalendarColorProvider.colors

    SideEffect {
      sysUiController.setSystemBarsColor(color = colors.appBarColor)
      sysUiController.setNavigationBarColor(color = colors.uiBackground)
    }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawer(
      drawerContent = {
        DashboardDrawer()
      },
      drawerState = drawerState,
      drawerContainerColor = GoogleCalendarColorProvider.colors.appBarColor,
      modifier = Modifier.padding()
    ) {
      Scaffold(
        containerColor = GoogleCalendarColorProvider.colors.uiBackground,
        contentColor = GoogleCalendarColorProvider.colors.textPrimary,
        modifier = Modifier
          .statusBarsPadding()
          .navigationBarsPadding(),
        topBar = {
          DashboardAppBar {
            scope.launch {
              if (drawerState.isClosed) {
                drawerState.open()
              } else {
                drawerState.close()
              }
            }
          }
        },
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
}




