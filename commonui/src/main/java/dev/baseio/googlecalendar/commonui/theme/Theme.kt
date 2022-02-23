package dev.baseio.googlecalendar.commonui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = GoogleCalendarColorPalette(
  uiBackground = Color.White,
  textPrimary = Color(0xff212121),
  textSecondary = Color(0xff424242),
  isDark = false,
  buttonColor = ButtonColor,
  buttonTextColor = Color.White,
  darkBackground = DarkBackground,
  lineColor = LineColorLight,
  bottomNavSelectedColor = Color.Black,
  bottomNavUnSelectedColor = Color.LightGray,
  appBarIconColor = Color(0xff424242),
  appBarTextTitleColor = Color(0xff212121),
  appBarTextSubTitleColor =  Color(0xff424242),
  sendButtonDisabled = Color.LightGray,
  sendButtonEnabled = Color.Black,
  appBarColor = DashboardAppBarColor
)

private val DarkColorPalette = GoogleCalendarColorPalette(
  uiBackground = DarkBackground,
  textPrimary = Color.White,
  textSecondary = Color.White,
  isDark = true,
  buttonColor = ButtonColor,
  buttonTextColor = Color.Black,
  darkBackground = DarkBackground,
  lineColor = LineColorDark,
  bottomNavSelectedColor = Color.White,
  bottomNavUnSelectedColor = Color.Gray,
  appBarIconColor = Color.White,
  appBarTextTitleColor = Color.White,
  appBarTextSubTitleColor = Color.LightGray,
  sendButtonDisabled = Color.White.copy(alpha = 0.4f),
  sendButtonEnabled = Color.White,
  appBarColor = DarkBackground
)

@Composable
fun GoogleCalendarTheme(
  isDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (isDarkTheme) DarkColorPalette else LightColorPalette
  val view = LocalView.current

  SideEffect {
    (view.context as Activity).window.statusBarColor = colors.uiBackground.toArgb()
    (view.context as Activity).window.navigationBarColor = colors.uiBackground.toArgb()
    ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !isDarkTheme
  }

  ProvideGoogleCalendarColors(colors) {
    MaterialTheme(
      colors = debugColors(isDarkTheme),
      typography = GoogleCalendarTypography,
      shapes = GoogleCalendarShapes,
      content = content
    )
  }
}

object GoogleCalendarColorProvider {
  val colors: GoogleCalendarColorPalette
    @Composable
    get() = LocalGoogleCalendarColor.current
}

/**
 * GoogleCalendar custom Color Palette
 */
@Stable
class GoogleCalendarColorPalette(
  uiBackground: Color,
  textPrimary: Color,
  textSecondary: Color,
  isDark: Boolean,
  buttonColor: Color,
  buttonTextColor: Color,
  darkBackground: Color,
  lineColor: Color,
  bottomNavSelectedColor: Color,
  bottomNavUnSelectedColor: Color,
  appBarIconColor: Color,
  appBarTextTitleColor: Color,
  appBarTextSubTitleColor: Color,
  sendButtonDisabled:Color,
  sendButtonEnabled:Color,
  appBarColor:Color
) {
  var uiBackground by mutableStateOf(uiBackground)
    private set
  var appBarColor by mutableStateOf(appBarColor)
    private set
  var textPrimary by mutableStateOf(textPrimary)
    private set
  var textSecondary by mutableStateOf(textSecondary)
    private set
  var isDark by mutableStateOf(isDark)
    private set
  var buttonColor by mutableStateOf(buttonColor)
    private set
  var buttonTextColor by mutableStateOf(buttonTextColor)
    private set
  var darkBackground by mutableStateOf(darkBackground)
    private set
  var lineColor by mutableStateOf(lineColor)
    private set

  var bottomNavSelectedColor by mutableStateOf(bottomNavSelectedColor)
    private set
  var bottomNavUnSelectedColor by mutableStateOf(bottomNavUnSelectedColor)
    private set
  var appBarIconColor by mutableStateOf(appBarIconColor)
    private set

  var appBarTextTitleColor by mutableStateOf(appBarTextTitleColor)
    private set
  var appBarTextSubTitleColor by mutableStateOf(appBarTextSubTitleColor)
    private set
  var sendButtonDisabled by mutableStateOf(sendButtonDisabled)
    private set

  var sendButtonEnabled by mutableStateOf(sendButtonEnabled)
    private set


  fun update(other: GoogleCalendarColorPalette) {
    uiBackground = other.uiBackground
    textPrimary = other.textPrimary
    textSecondary = other.textSecondary
    isDark = other.isDark
    buttonColor = other.buttonColor
    buttonTextColor = other.buttonTextColor
    darkBackground = other.darkBackground
    lineColor = other.lineColor
    bottomNavSelectedColor = other.bottomNavSelectedColor
    bottomNavUnSelectedColor = other.bottomNavUnSelectedColor
    appBarIconColor = other.appBarIconColor
    appBarTextTitleColor = other.appBarTextTitleColor
    appBarTextSubTitleColor = other.appBarTextSubTitleColor
    sendButtonEnabled = other.sendButtonEnabled
    sendButtonDisabled = other.sendButtonDisabled
    appBarColor = other.appBarColor
  }
}

@Composable
fun ProvideGoogleCalendarColors(
  colors: GoogleCalendarColorPalette,
  content: @Composable () -> Unit
) {
  val colorPalette = remember { colors }
  colorPalette.update(colors)
  CompositionLocalProvider(LocalGoogleCalendarColor provides colorPalette, content = content)
}

private val LocalGoogleCalendarColor = staticCompositionLocalOf<GoogleCalendarColorPalette> {
  error("No GoogleCalendarColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [GoogleCalendarColorProvider.colors].
 */
fun debugColors(
  darkTheme: Boolean,
  debugColor: Color = Color.Red
) = Colors(
  primary = debugColor,
  primaryVariant = debugColor,
  secondary = debugColor,
  secondaryVariant = debugColor,
  background = debugColor,
  surface = debugColor,
  error = debugColor,
  onPrimary = debugColor,
  onSecondary = debugColor,
  onBackground = debugColor,
  onSurface = debugColor,
  onError = debugColor,
  isLight = !darkTheme
)