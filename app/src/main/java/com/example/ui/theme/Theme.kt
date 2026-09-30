package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val QuranStudioDarkColorScheme = darkColorScheme(
  primary = GoldPrimary,
  onPrimary = EmeraldDark,
  primaryContainer = GoldContainer,
  onPrimaryContainer = GoldLight,
  secondary = EmeraldLight,
  onSecondary = EmeraldDark,
  secondaryContainer = EmeraldCard,
  onSecondaryContainer = EmeraldAccent,
  tertiary = LiquidCyan,
  onTertiary = Color.Black,
  background = DarkBackground,
  onBackground = TextLightPrimary,
  surface = DarkSurface,
  onSurface = TextLightPrimary,
  surfaceVariant = DarkCard,
  onSurfaceVariant = TextLightSecondary,
  outline = BorderSubtle,
  outlineVariant = Color(0xFF285444)
)

private val QuranStudioLightColorScheme = lightColorScheme(
  primary = Color(0xFF0F5132),
  onPrimary = Color.White,
  primaryContainer = Color(0xFFD1E7DD),
  onPrimaryContainer = Color(0xFF0A3622),
  secondary = Color(0xFFB45309),
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFFEF3C7),
  onSecondaryContainer = Color(0xFF78350F),
  tertiary = Color(0xFF0284C7),
  background = IslamicBeige,
  onBackground = Color(0xFF1E293B),
  surface = Color.White,
  onSurface = Color(0xFF1E293B),
  surfaceVariant = Color(0xFFF1F5F9),
  onSurfaceVariant = Color(0xFF475569),
  outline = Color(0xFFCBD5E1)
)

@Composable
fun QuranStudioTheme(
  darkTheme: Boolean = true, // Default to respectful, atmospheric dark mode
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) QuranStudioDarkColorScheme else QuranStudioLightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
