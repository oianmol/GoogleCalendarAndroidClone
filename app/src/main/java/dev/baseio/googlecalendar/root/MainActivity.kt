package dev.baseio.googlecalendar.root

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.baseio.googlecalendar.navigator.ComposeNavigator
import dev.baseio.googlecalendar.navigator.GoogleCalendar
import dev.baseio.googlecalendar.uionboarding.nav.onboardingNavigation
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var composeNavigator: ComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()

    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      val navController = rememberNavController()

      LaunchedEffect(Unit) {
        composeNavigator.handleNavigationCommands(navController)
      }

      ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        NavHost(
          navController = navController,
          startDestination = GoogleCalendar.OnBoarding.name,
        ) {
          onboardingNavigation(
            composeNavigator = composeNavigator,
          )
        }
      }
    }
  }
}