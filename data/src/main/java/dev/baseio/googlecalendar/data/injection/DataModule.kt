package dev.baseio.googlecalendar.data.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.baseio.googlecalendar.data.local.GoogleCalendarDB
import dev.baseio.googlecalendar.data.local.dao.CalendarUserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): GoogleCalendarDB {
    return Room.inMemoryDatabaseBuilder(
      context,
      GoogleCalendarDB::class.java,
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
  }

  @Provides
  @Singleton
  fun provideUserDao(googleCalendarDB: GoogleCalendarDB): CalendarUserDao = googleCalendarDB.calendarUserDao()
}