package dev.baseio.libjetcalendar.yearly

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.libjetcalendar.data.*
import dev.baseio.libjetcalendar.monthly.JetCalendarMonthlyView
import dev.baseio.libjetcalendar.monthly.WeekNames
import java.time.DayOfWeek

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JetCalendarYearlyView(
  jetYear: JetYear,
  onDateSelected: (JetDay) -> Unit,
  selectedDates: Set<JetDay>,
  isGridView: Boolean = true,
  dayOfWeek: DayOfWeek,
  startIndex: Int = 0,
  needsYearNavigator: Boolean = false,
  onPreviousYear: () -> Unit = {},
  onNextYear: () -> Unit = {}
) {
  // affected by https://stackoverflow.com/questions/69739108/how-to-save-paging-state-of-lazycolumn-during-navigation-in-jetpack-compose
  val listState = rememberLazyListState(startIndex)

  YearViewInternal(
    listState,
    jetYear,
    onDateSelected,
    selectedDates,
    isGridView,
    dayOfWeek = dayOfWeek, needsYearNavigator, onPreviousYear, onNextYear
  )
}


@ExperimentalFoundationApi
@Composable
private fun YearViewInternal(
  listState: LazyListState,
  jetYear: JetYear,
  onDateSelected: (JetDay) -> Unit,
  selectedDates: Set<JetDay>,
  isGridView: Boolean,
  dayOfWeek: DayOfWeek,
  needsYearNavigator: Boolean,
  onPreviousYear: () -> Unit,
  onNextYear: () -> Unit,
) {
  when (jetYear.yearMonths.size) {
    0 -> CircularProgressIndicator(color = Color.Black, modifier = Modifier.padding(8.dp))
    else -> {
      if (isGridView) {
        GridViewYearly(
          listState,
          jetYear.yearMonths,
          onDateSelected,
          selectedDates,
          isGridView,
          needsYearNavigator,
          onPreviousYear,
          onNextYear
        )
      } else {
        Column {
          YearNavigatorHeader(jetYear.year(), onPreviousYear, onNextYear)
          WeekNames(dayOfWeek = dayOfWeek)
          ListViewYearly(
            listState, jetYear.yearMonths, onDateSelected, selectedDates, isGridView,
            needsYearNavigator,
            onPreviousYear,
            onNextYear
          )
        }
      }
    }
  }

}

@ExperimentalFoundationApi
@Composable
private fun ListViewYearly(
  listState: LazyListState,
  pagedMonths: List<JetMonth>,
  onDateSelected: (JetDay) -> Unit,
  selectedDates: Set<JetDay>,
  isGridView: Boolean,
  needsYearNavigator: Boolean,
  onPreviousYear: () -> Unit,
  onNextYear: () -> Unit
) {
  LazyColumn(
    state = listState,
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
  ) {
    for (index in pagedMonths.indices) {
      item {
        CalendarMonthlyBox(
          pagedMonths,
          index,
          onDateSelected,
          selectedDates,
          isGridView
        )
      }
    }
  }
}

@ExperimentalFoundationApi
@Composable
private fun GridViewYearly(
  listState: LazyListState,
  pagedMonths: List<JetMonth>,
  onDateSelected: (JetDay) -> Unit,
  selectedDates: Set<JetDay>,
  isGridView: Boolean,
  needsYearNavigator: Boolean,
  onPreviousYear: () -> Unit,
  onNextYear: () -> Unit,
) {
  LazyVerticalGrid(
    cells = GridCells.Fixed(3),
    state = listState,
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
  ) {
    for (index in pagedMonths.indices) {
      when {
        index % 12 == 0 -> {
          item(span = { GridItemSpan(3) }) {
            if (needsYearNavigator) {
              YearNavigatorHeader(pagedMonths[index].year(), onPreviousYear, onNextYear)
            } else {
              YearHeader(pagedMonths[index].year())
            }
          }
          item {
            CalendarMonthlyBox(
              pagedMonths,
              index,
              onDateSelected,
              selectedDates,
              isGridView
            )
          }
        }
        else -> {
          item {
            CalendarMonthlyBox(
              pagedMonths,
              index,
              onDateSelected,
              selectedDates,
              isGridView
            )
          }
        }
      }
    }
  }
}

@Composable
fun YearNavigatorHeader(year: String, onPreviousYear: () -> Unit, onNextYear: () -> Unit) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(onClick = {
      onPreviousYear()
    }) {
      Icon(
        Icons.Filled.ArrowBack, "Previous Year",
        tint = Color.Red
      )
    }
    YearHeader(year)
    IconButton(onClick = {
      onNextYear()
    }) {
      Icon(
        Icons.Filled.ArrowForward, "Next Year",
        tint = Color.Red
      )
    }
  }
}

@Composable
private fun YearHeader(
  year: String
) {
  Text(
    text = year,
    modifier = Modifier.padding(8.dp),
    style = TextStyle(
      color = Color.Red,
      fontSize = 24.sp,
      fontWeight = FontWeight.SemiBold
    )
  )
}

@Composable
private fun CalendarMonthlyBox(
  pagedMonths: List<JetMonth>,
  index: Int,
  onDateSelected: (JetDay) -> Unit,
  selectedDates: Set<JetDay>,
  isGridView: Boolean,
) {
  JetCalendarMonthlyView(
    pagedMonths[index],
    {
      onDateSelected(it)
    },
    selectedDates
  )
}
