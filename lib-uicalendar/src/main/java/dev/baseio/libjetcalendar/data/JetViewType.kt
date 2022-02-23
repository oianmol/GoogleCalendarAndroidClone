package dev.baseio.libjetcalendar.data

enum class JetViewType {
  MONTHLY,
  WEEKLY,
  YEARLY;

  fun next(): JetViewType {
    if (ordinal == values().size.minus(1)) {
      return MONTHLY
    }
    return values()[ordinal + 1]
  }
}