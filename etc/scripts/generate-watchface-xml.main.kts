#!/usr/bin/env kotlin

import java.io.File
import kotlin.math.cos
import kotlin.math.sin

val template = File("etc/assets/watchface-template.xml").readText()
val diameter = 450
val radius = diameter / 2
val bigFontSize = 56
val smallFontSize = 38
val dialText = buildString {
  append("    <!-- Dial -->\n")
  for (hour in 0..11) {
    val fontSize = when (hour) {
      0, 3, 6, 9 -> bigFontSize
      else -> smallFontSize
    }
    val x = (radius - bigFontSize / 2) * sin(hour * Math.PI / 6)
    val y = -(radius - bigFontSize / 2) * cos(hour * Math.PI / 6)
    val hourStr = if (hour == 0) "12" else hour.toString()
    append(
      """
            |    <PartText
            |      height="450"
            |      tintColor="#FF8000"
            |      width="450"
            |      x="${x.toInt()}"
            |      y="${y.toInt()}">
            |      <Text>
            |        <Font
            |          family="oswald_medium_skewed"
            |          size="$fontSize">$hourStr
            |        </Font>
            |      </Text>
            |    </PartText>
            |
            """.trimMargin()
    )
  }
}

File("watchface/src/main/res/raw/watchface.xml").writeText(
  template.replace(
    "    <!-- Dial -->",
    dialText
  )
)
