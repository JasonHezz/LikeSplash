package com.github.jasonhezz.likesplash.extension

import android.graphics.Color
import java.util.regex.Pattern


/**
 * Created by JavaCoder on 2017/10/23.
 */


val materialDataHex = listOf("#f44336", "#ffebee", "#ffcdd2", "#ef9a9a", "#e57373", "#ef5350",
    "#e53935", "#d32f2f", "#c62828", "#b71c1c", "#ff8a80", "#ff5252", "#ff1744", "#d50000",
    "#e91e63", "#fce4ec", "#f8bbd0", "#f48fb1", "#f06292", "#ec407a", "#d81b60", "#c2185b",
    "#ad1457", "#880e4f", "#ff80ab", "#ff4081", "#f50057", "#c51162", "#9c27b0", "#f3e5f5",
    "#e1bee7", "#ce93d8", "#ba68c8", "#ab47bc", "#8e24aa", "#7b1fa2", "#6a1b9a", "#4a148c",
    "#ea80fc", "#e040fb", "#d500f9", "#aa00ff", "#673ab7", "#ede7f6", "#d1c4e9", "#b39ddb",
    "#9575cd", "#7e57c2", "#5e35b1", "#512da8", "#4527a0", "#311b92", "#b388ff", "#7c4dff",
    "#651fff", "#6200ea", "#3f51b5", "#e8eaf6", "#c5cae9", "#9fa8da", "#7986cb", "#5c6bc0",
    "#3949ab", "#303f9f", "#283593", "#1a237e", "#8c9eff", "#536dfe", "#3d5afe", "#304ffe",
    "#2196f3", "#e3f2fd", "#bbdefb", "#90caf9", "#64b5f6", "#42a5f5", "#1e88e5", "#1976d2",
    "#1565c0", "#0d47a1", "#82b1ff", "#448aff", "#2979ff", "#2962ff", "#03a9f4", "#e1f5fe",
    "#b3e5fc", "#81d4fa", "#4fc3f7", "#29b6f6", "#039be5", "#0288d1", "#0277bd", "#01579b",
    "#80d8ff", "#40c4ff", "#00b0ff", "#0091ea", "#00bcd4", "#e0f7fa", "#b2ebf2", "#80deea",
    "#4dd0e1", "#26c6da", "#00acc1", "#0097a7", "#00838f", "#006064", "#84ffff", "#18ffff",
    "#00e5ff", "#00b8d4", "#009688", "#e0f2f1", "#b2dfdb", "#80cbc4", "#4db6ac", "#26a69a",
    "#00897b", "#00796b", "#00695c", "#004d40", "#a7ffeb", "#64ffda", "#1de9b6", "#00bfa5",
    "#4caf50", "#e8f5e9", "#c8e6c9", "#a5d6a7", "#81c784", "#66bb6a", "#43a047", "#388e3c",
    "#2e7d32", "#1b5e20", "#b9f6ca", "#69f0ae", "#00e676", "#00c853", "#8bc34a", "#f1f8e9",
    "#dcedc8", "#c5e1a5", "#aed581", "#9ccc65", "#7cb342", "#689f38", "#558b2f", "#33691e",
    "#ccff90", "#b2ff59", "#76ff03", "#64dd17", "#cddc39", "#f9fbe7", "#f0f4c3", "#e6ee9c",
    "#dce775", "#d4e157", "#c0ca33", "#afb42b", "#9e9d24", "#827717", "#f4ff81", "#eeff41",
    "#c6ff00", "#aeea00", "#ffeb3b", "#fffde7", "#fff9c4", "#fff59d", "#fff176", "#ffee58",
    "#fdd835", "#fbc02d", "#f9a825", "#f57f17", "#ffff8d", "#ffff00", "#ffea00", "#ffd600",
    "#ffc107", "#fff8e1", "#ffecb3", "#ffe082", "#ffd54f", "#ffca28", "#ffb300", "#ffa000",
    "#ff8f00", "#ff6f00", "#ffe57f", "#ffd740", "#ffc400", "#ffab00", "#ff9800", "#fff3e0",
    "#ffe0b2", "#ffcc80", "#ffb74d", "#ffa726", "#fb8c00", "#f57c00", "#ef6c00", "#e65100",
    "#ffd180", "#ffab40", "#ff9100", "#ff6d00", "#ff5722", "#fbe9e7", "#ffccbc", "#ffab91",
    "#ff8a65", "#ff7043", "#f4511e", "#e64a19", "#d84315", "#bf360c", "#ff9e80", "#ff6e40",
    "#ff3d00", "#dd2c00", "#795548", "#efebe9", "#d7ccc8", "#bcaaa4", "#a1887f", "#8d6e63",
    "#6d4c41", "#5d4037", "#4e342e", "#3e2723", "#9e9e9e", "#fafafa", "#f5f5f5", "#eeeeee",
    "#e0e0e0", "#bdbdbd", "#757575", "#616161", "#424242", "#212121", "#607d8b", "#eceff1",
    "#cfd8dc", "#b0bec5", "#90a4ae", "#78909c", "#546e7a", "#455a64", "#37474f", "#263238",
    "#000000", "#ffffff")

fun String.hexToMaterialHex(): Int {
  if (Pattern.matches("^#([0-9a-fA-F]{6}|[A-F0-9a-f]{8})", this)) {
    val color = Color.parseColor(this)
    val originalRed = Color.red(color)
    val originalBlue = Color.blue(color)
    val originalGreen = Color.green(color)
    return materialDataHex.map {
      Color.parseColor(it)
    }.minBy {
      val red = Color.red(it)
      val green = Color.green(it)
      val blue = Color.blue(it)
      Math.abs(originalRed - red) + Math.abs(
          (originalGreen - green)) + Math.abs(originalBlue - blue)
    } ?: Color.parseColor("#26292c")
  }
  return Color.parseColor("#26292c")
}

