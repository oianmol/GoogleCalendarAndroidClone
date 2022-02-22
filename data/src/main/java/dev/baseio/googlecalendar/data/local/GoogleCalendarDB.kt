package dev.baseio.googlecalendar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.baseio.googlecalendar.data.local.dao.CalendarUserDao
import dev.baseio.googlecalendar.data.local.model.DBCalendarUser


@Database(
    entities = [DBCalendarUser::class],
    version = 1
)
abstract class GoogleCalendarDB : RoomDatabase() {
    abstract fun calendarUserDao(): CalendarUserDao
}