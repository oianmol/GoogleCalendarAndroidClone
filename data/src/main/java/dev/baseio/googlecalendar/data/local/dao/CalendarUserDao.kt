package dev.baseio.googlecalendar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.baseio.googlecalendar.data.local.model.DBCalendarUser

@Dao
interface CalendarUserDao {
  @Query("SELECT * FROM slackuser")
  fun getAll(): List<DBCalendarUser>

  @Query("SELECT * FROM slackuser WHERE uuid IN (:slackuserIds)")
  fun loadAllByIds(slackuserIds: IntArray): List<DBCalendarUser>

  @Query(
    "SELECT * FROM slackuser WHERE first_name LIKE :first AND " +
        "last_name LIKE :last LIMIT 1"
  )
  fun findByName(first: String, last: String): DBCalendarUser

  @Insert
  fun insertAll(calendarUsers: List<DBCalendarUser>)

  @Delete
  fun delete(calendarUser: DBCalendarUser)
}