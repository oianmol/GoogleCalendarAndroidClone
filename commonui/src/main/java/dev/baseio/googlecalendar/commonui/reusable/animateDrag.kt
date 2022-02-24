package dev.baseio.googlecalendar.commonui.reusable

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun Modifier.animateDrag(
  onExpand: () -> Unit,
  onCollapse: () -> Unit
): Modifier =
  composed {
    val sensitivity = 100
    var swipeOffset by remember {
      mutableStateOf(0f)
    }
    var gestureConsumed by remember {
      mutableStateOf(false)
    }
    this.pointerInput(Unit) {
      detectVerticalDragGestures(
        onVerticalDrag = { _, dragAmount ->
          //dragAmount: positive when scrolling down; negative when scrolling up
          swipeOffset += dragAmount
          when {
            swipeOffset > sensitivity -> {
              //offset > 0 when swipe down
              if (!gestureConsumed) {
                onCollapse()
                gestureConsumed = true
              }
            }

            swipeOffset < -sensitivity -> {
              //offset < 0 when swipe up
              if (!gestureConsumed) {
                onExpand()
                gestureConsumed = true
              }
            }
          }
        }, onDragEnd = {
          swipeOffset = 0f
          gestureConsumed = false
        })
    }
  }