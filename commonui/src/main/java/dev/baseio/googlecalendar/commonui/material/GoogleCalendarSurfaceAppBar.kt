package dev.baseio.googlecalendar.commonui.material

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarSurface

@Composable
fun GoogleCalendarSurfaceAppBar(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  navigationIcon: @Composable (() -> Unit)? = null,
  actions: @Composable RowScope.() -> Unit = {},
  backgroundColor: Color = MaterialTheme.colors.primarySurface,
  contentColor: Color = contentColorFor(backgroundColor),
  elevation: Dp = AppBarDefaults.TopAppBarElevation,
) {
  GoogleCalendarSurface(
    color = backgroundColor,
    contentColor = contentColor,
    elevation = elevation
  ) {
    TopAppBar(
      title, modifier, navigationIcon, actions, backgroundColor, contentColor, elevation
    )
  }
}

@Composable
fun GoogleCalendarSurfaceAppBar(
  modifier: Modifier = Modifier,
  backgroundColor: Color = MaterialTheme.colors.primarySurface,
  contentColor: Color = contentColorFor(backgroundColor),
  elevation: Dp = AppBarDefaults.TopAppBarElevation,
  contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
  content: @Composable RowScope.() -> Unit
) {
  GoogleCalendarSurface(
    color = backgroundColor,
    contentColor = contentColor,
    elevation = elevation
  ) {
    TopAppBar(
      modifier, backgroundColor, contentColor, elevation, contentPadding, content
    )
  }
}