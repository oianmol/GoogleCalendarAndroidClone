package dev.baseio.googlecalendar.commonui.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarColorProvider
import dev.baseio.googlecalendar.commonui.theme.GoogleCalendarTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SlackListItem(
  icon: ImageVector,
  title: String,
  trailingItem: ImageVector? = null,
  onItemClick: () -> Unit = {}
) {
  Row(
    modifier = Modifier
      .padding(8.dp)
      .clickable {
        onItemClick()
      }, verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = GoogleCalendarColorProvider.colors.textPrimary.copy(alpha = 0.4f),
      modifier = Modifier
        .size(28.dp)
        .padding(4.dp)
    )
    Text(
      text = title,
      style = GoogleCalendarTypography.subtitle1.copy(
        color = GoogleCalendarColorProvider.colors.textPrimary.copy(
          alpha = 0.8f
        )
      ), modifier = Modifier
        .weight(1f)
        .padding(8.dp)
    )
    trailingItem?.let { safeIcon ->
      Icon(
        imageVector = safeIcon,
        contentDescription = null,
        tint = GoogleCalendarColorProvider.colors.textPrimary.copy(alpha = 0.4f),
        modifier = Modifier
          .size(24.dp)
          .padding(4.dp)
      )
    }
  }
}