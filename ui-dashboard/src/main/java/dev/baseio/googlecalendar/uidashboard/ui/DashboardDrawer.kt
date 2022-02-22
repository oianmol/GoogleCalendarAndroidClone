package dev.baseio.googlecalendar.uidashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import dev.baseio.googlecalendar.commonui.R
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTypography

@Composable
fun DashboardDrawer() {
  Box(
    modifier = Modifier
      .statusBarsPadding()
      .padding(top = 16.dp),
  ) {
    Column {
      DrawerHeader()
    }
  }
}

@Composable
private fun DrawerHeader() {
  Row(
    Modifier
      .fillMaxWidth()
      .padding(12.dp)) {
    Image(
      painterResource(id = R.drawable.google),
      contentDescription = null,
    )
    Spacer(modifier = Modifier.width(4.dp))
    Text(
      text = stringResource(id = dev.baseio.googlecalendar.common.R.string.calendar),
      style = GoogleCalendarTypography.h6.copy(GoogleCalendarColorProvider.colors.textPrimary)
    )
  }
}