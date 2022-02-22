package dev.baseio.googlecalendar.di

import dev.baseio.googlecalendar.navigator.ComposeNavigator
import dev.baseio.googlecalendar.navigator.composenavigator.GoogleCalendarComposeNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

  @Binds
  @Singleton
  abstract fun provideComposeNavigator(praxisComposeNavigator: GoogleCalendarComposeNavigator): ComposeNavigator
}
