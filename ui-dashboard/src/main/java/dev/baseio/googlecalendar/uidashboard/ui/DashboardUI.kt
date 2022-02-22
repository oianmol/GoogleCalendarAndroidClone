package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTheme
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTypography
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
        Box(
          modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .statusBarsPadding()
            .padding(top = 16.dp),
        ) {
          Column {
            DrawerHeader()
          }
        }
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

@Composable
fun DrawerHeader() {
  Row(Modifier.fillMaxWidth().padding(12.dp)) {
    Image(
      painterResource(id = dev.baseio.googlecalendar.commonui.R.drawable.google),
      contentDescription = null,
    )
    Spacer(modifier = Modifier.width(4.dp))
    Text(
      text = stringResource(id = dev.baseio.googlecalendar.common.R.string.calendar),
      style = GoogleCalendarTypography.h6.copy(GoogleCalendarColorProvider.colors.textPrimary)
    )
  }
}

@Composable
private fun DashboardAppBar(toggleDrawer: () -> Unit) {
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
