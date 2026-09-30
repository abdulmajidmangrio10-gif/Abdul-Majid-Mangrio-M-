package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AspectRatioMode
import com.example.data.model.CaptionBarPosition
import com.example.data.model.CaptionItem
import com.example.data.model.TextAnimationType
import com.example.ui.StudioUiState
import kotlin.math.sin

@Composable
fun VideoCanvasPreview(
  uiState: StudioUiState,
  onTogglePlay: () -> Unit,
  onSeek: (Long) -> Unit,
  onToggleAspect: () -> Unit,
  modifier: Modifier = Modifier
) {
  // Infinite transition for liquid gradients and particle glows
  val infiniteTransition = rememberInfiniteTransition(label = "canvas_anim")
  val liquidAnimPhase by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 6.28f,
    animationSpec = infiniteRepeatable(
      animation = tween(4000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "liquid_phase"
  )
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.96f,
    targetValue = 1.04f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_scale"
  )

  // Current active caption based on timeline
  val currentCaption = remember(uiState.captions, uiState.currentPositionMs) {
    uiState.captions.find { uiState.currentPositionMs in it.startMs..it.endMs }
      ?: uiState.captions.firstOrNull()
  }

  // Active word based on timeline
  val currentWord = remember(currentCaption, uiState.currentPositionMs) {
    currentCaption?.words?.find { uiState.currentPositionMs in it.startMs..it.endMs }
  }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 12.dp, vertical = 6.dp)
      .testTag("video_canvas_card"),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = Color.Black),
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Canvas Toolbar: Aspect Ratio badge & Qari indicator
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF0F1B16))
          .padding(horizontal = 14.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = Color(0xFF163228),
          modifier = Modifier.clickable { onToggleAspect() }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Crop,
              contentDescription = "Aspect Ratio",
              tint = Color(0xFFD4AF37),
              modifier = Modifier.size(16.dp)
            )
            Text(
              text = uiState.aspectRatio.label.substringBefore(" "),
              color = Color.White,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        // Qari and Surah Badge
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = uiState.selectedSurah.nameArabic,
            color = Color(0xFFD4AF37),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "• ${uiState.voiceState.selectedQari.title}",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
          )
        }
      }

      // Dynamic Aspect Ratio Canvas Container
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(uiState.aspectRatio.ratio.coerceIn(0.6f, 1.8f))
          .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
          .background(Color.Black),
        contentAlignment = Alignment.Center
      ) {
        // 1. Dynamic Background / Motion Wallpaper Layer
        Canvas(modifier = Modifier.fillMaxSize()) {
          val width = size.width
          val height = size.height

          if (uiState.currentTheme.isLiquid && uiState.currentTheme.liquidColors.isNotEmpty()) {
            // Liquid flowing gradient
            val offsetShift = sin(liquidAnimPhase) * (width * 0.3f)
            val brush = Brush.linearGradient(
              colors = uiState.currentTheme.liquidColors.map { Color(it) },
              start = Offset(offsetShift, 0f),
              end = Offset(width - offsetShift, height)
            )
            drawRect(brush = brush)
          } else {
            // Preset background gradient
            val bgColors = uiState.currentBackground.colors.map { Color(it) }
            val brush = Brush.verticalGradient(
              colors = if (bgColors.size >= 2) bgColors else listOf(Color(0xFF071B14), Color(0xFF020906))
            )
            drawRect(brush = brush)
          }

          // Optional Starry / Divine particle simulation
          if (uiState.currentBackground.id == "starry_desert" || uiState.currentBackground.id == "cinematic_particles") {
            for (i in 0..18) {
              val x = (width * ((i * 47) % 100) / 100f + sin(liquidAnimPhase + i) * 12f)
              val y = (height * ((i * 31) % 100) / 100f)
              val radius = ((i % 4) + 1.5f).dp.toPx()
              drawCircle(
                color = Color(0x88FFD54F),
                radius = radius,
                center = Offset(x, y)
              )
            }
          }
        }

        // 2. Video Filter / Dark Overlay Layer for Text Clarity
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = uiState.backgroundDarkOverlay))
        )
        if (uiState.currentFilter.tintColor != 0x00000000L) {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(Color(uiState.currentFilter.tintColor))
          )
        }

        // 3. Audio Wave Visualizer Bars at top or bottom of canvas
        if (uiState.isPlaying) {
          Row(
            modifier = Modifier
              .align(Alignment.TopCenter)
              .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            for (i in 0..14) {
              val barHeight = (12 + (sin(liquidAnimPhase * 2 + i) * 10).coerceAtLeast(0f) * 1.6f).dp
              Box(
                modifier = Modifier
                  .width(3.dp)
                  .height(barHeight)
                  .clip(RoundedCornerShape(2.dp))
                  .background(Color(0xFFD4AF37).copy(alpha = 0.85f))
              )
            }
          }
        }

        // 4. Islamic Stickers on Canvas
        uiState.stickersOnCanvas.forEach { sticker ->
          Box(
            modifier = Modifier
              .fillMaxSize()
              .wrapContentSize(Alignment.TopStart)
              .offset(
                x = (sticker.posX * 260).dp,
                y = (sticker.posY * 380).dp
              )
          ) {
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color.Black.copy(alpha = 0.35f),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x44D4AF37))
            ) {
              Text(
                text = "${sticker.symbol} ${sticker.text}",
                color = Color(0xFFFDE68A),
                fontSize = (13 * sticker.scale).sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }

        // 5. Main Recitation Text & Lower Third Caption Bar
        val alignment = when (uiState.captionBar.position) {
          CaptionBarPosition.TOP -> Alignment.TopCenter
          CaptionBarPosition.CENTER -> Alignment.Center
          CaptionBarPosition.BOTTOM -> Alignment.BottomCenter
        }

        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(
              start = 14.dp,
              end = 14.dp,
              bottom = if (uiState.captionBar.position == CaptionBarPosition.BOTTOM) 22.dp else 12.dp,
              top = if (uiState.captionBar.position == CaptionBarPosition.TOP) 44.dp else 12.dp
            ),
          contentAlignment = alignment
        ) {
          if (uiState.captionBar.isEnabled) {
            // Lower-Third / Broadcast Caption Bar
            Surface(
              modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(
                  elevation = 8.dp,
                  shape = RoundedCornerShape(uiState.captionBar.cornerRadiusDp.dp)
                ),
              shape = RoundedCornerShape(uiState.captionBar.cornerRadiusDp.dp),
              color = Color(uiState.captionBar.solidColor).copy(alpha = uiState.captionBar.opacity),
              border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                brush = Brush.horizontalGradient(
                  listOf(
                    Color(uiState.captionBar.sidebarColor).copy(alpha = 0.8f),
                    Color.Transparent
                  )
                )
              )
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                // Vertical accent line
                if (uiState.captionBar.hasSidebarAccent) {
                  Box(
                    modifier = Modifier
                      .width(4.dp)
                      .height(48.dp)
                      .clip(RoundedCornerShape(2.dp))
                      .background(Color(uiState.captionBar.sidebarColor))
                  )
                  Spacer(modifier = Modifier.width(10.dp))
                }

                // Main Text Column
                Column(
                  modifier = Modifier.weight(1f),
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  // Metadata Badges Row (Surah, Ayah, Qari)
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    if (uiState.captionBar.showSurahBadge) {
                      Text(
                        text = "سُورَةُ ${uiState.selectedSurah.nameArabic} : ${currentCaption?.ayahNumber ?: 1}",
                        color = Color(0xFFD4AF37),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                      )
                    }
                    if (uiState.captionBar.showQariBadge) {
                      Text(
                        text = uiState.voiceState.selectedQari.name.take(18) + "...",
                        color = Color(0xFF94A3B8),
                        fontSize = 10.sp
                      )
                    }
                  }

                  Spacer(modifier = Modifier.height(4.dp))

                  // Render Arabic with Word-by-Word / Letter glow highlighting
                  if (uiState.showArabic) {
                    RenderArabicCaption(
                      caption = currentCaption,
                      activeWord = currentWord,
                      uiState = uiState,
                      pulseScale = if (uiState.textAnimation == TextAnimationType.GLOW_PULSE) pulseScale else 1.0f
                    )
                  }

                  // Render Translation (Urdu / English)
                  if (uiState.showTranslation && currentCaption != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = currentCaption.translationText,
                      color = Color(0xFFE2E8F0),
                      fontSize = (uiState.fontSizeSp * 0.58f).sp,
                      fontFamily = FontFamily.Default,
                      textAlign = TextAlign.Center,
                      lineHeight = ((uiState.fontSizeSp * 0.58f) * 1.35f).sp
                    )
                  }
                }
              }
            }
          } else {
            // Clean Floating Style (without lower-third bar)
            Column(
              modifier = Modifier.fillMaxWidth(),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              if (uiState.showArabic) {
                RenderArabicCaption(
                  caption = currentCaption,
                  activeWord = currentWord,
                  uiState = uiState,
                  pulseScale = if (uiState.textAnimation == TextAnimationType.GLOW_PULSE) pulseScale else 1.0f
                )
              }
              if (uiState.showTranslation && currentCaption != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = currentCaption.translationText,
                  color = Color(0xFFF1F5F9),
                  fontSize = (uiState.fontSizeSp * 0.65f).sp,
                  textAlign = TextAlign.Center,
                  style = TextStyle(
                    shadow = Shadow(color = Color.Black, blurRadius = 8f)
                  )
                )
              }
            }
          }
        }

        // 6. Watermark Badge (Toggleable)
        if (uiState.watermarkEnabled) {
          Text(
            text = "Quran AI Studio",
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
              .align(Alignment.BottomEnd)
              .padding(8.dp)
          )
        }

        // 7. Interactive Play/Pause button overlay in center when paused
        if (!uiState.isPlaying) {
          IconButton(
            onClick = onTogglePlay,
            modifier = Modifier
              .size(56.dp)
              .clip(CircleShape)
              .background(Color(0xCC0B281F))
              .border(1.5.dp, Color(0xFFD4AF37), CircleShape)
              .testTag("canvas_play_overlay_button")
          ) {
            Icon(
              imageVector = Icons.Default.PlayArrow,
              contentDescription = "Play Video",
              tint = Color(0xFFD4AF37),
              modifier = Modifier.size(32.dp)
            )
          }
        }
      }

      // Bottom Playback Controls & Timeline Scrubber
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF0A1511))
          .padding(horizontal = 14.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = onTogglePlay,
          modifier = Modifier.size(36.dp)
        ) {
          Icon(
            imageVector = if (uiState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
            contentDescription = if (uiState.isPlaying) "Pause" else "Play",
            tint = Color(0xFFD4AF37)
          )
        }

        val progress = if (uiState.totalDurationMs > 0) {
          (uiState.currentPositionMs.toFloat() / uiState.totalDurationMs.toFloat()).coerceIn(0f, 1f)
        } else 0f

        Slider(
          value = progress,
          onValueChange = { frac ->
            val target = (frac * uiState.totalDurationMs).toLong()
            onSeek(target)
          },
          modifier = Modifier
            .weight(1f)
            .padding(horizontal = 6.dp),
          colors = SliderDefaults.colors(
            thumbColor = Color(0xFFD4AF37),
            activeTrackColor = Color(0xFF10B981),
            inactiveTrackColor = Color(0xFF1E3A2E)
          )
        )

        // Time display
        val currentSec = uiState.currentPositionMs / 1000
        val totalSec = uiState.totalDurationMs / 1000
        Text(
          text = String.format("%02d:%02d / %02d:%02d", currentSec / 60, currentSec % 60, totalSec / 60, totalSec % 60),
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          fontWeight = FontWeight.Medium
        )
      }
    }
  }
}

@Composable
private fun RenderArabicCaption(
  caption: CaptionItem?,
  activeWord: com.example.data.model.WordTiming?,
  uiState: StudioUiState,
  pulseScale: Float
) {
  if (caption == null) return

  // If word-by-word highlight is enabled, render words with active highlight
  if (uiState.highlightWordsWordByWord && caption.words.isNotEmpty()) {
    // Flow/Wrapping words row
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      caption.words.forEach { wordTiming ->
        val isWordActive = activeWord?.word == wordTiming.word
        val highlightColor = Color(uiState.currentTheme.highlightGlow)
        val normalColor = Color(uiState.currentTheme.primaryText)

        val textColor = if (isWordActive) highlightColor else normalColor
        val scale = if (isWordActive) pulseScale else 1.0f

        Box(
          modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
          Text(
            text = wordTiming.word,
            color = textColor,
            fontSize = (uiState.fontSizeSp * scale).sp,
            fontWeight = if (uiState.isBold || isWordActive) FontWeight.Bold else FontWeight.Normal,
            fontStyle = if (uiState.isItalic) FontStyle.Italic else FontStyle.Normal,
            textAlign = TextAlign.Center,
            style = TextStyle(
              shadow = if (isWordActive) {
                Shadow(color = highlightColor, blurRadius = 14f)
              } else if (uiState.textShadow) {
                Shadow(color = Color.Black, blurRadius = 6f)
              } else null
            )
          )
        }
      }
    }
  } else {
    // Full Ayah block render
    Text(
      text = caption.arabicText,
      color = Color(uiState.currentTheme.primaryText),
      fontSize = (uiState.fontSizeSp * pulseScale).sp,
      fontWeight = if (uiState.isBold) FontWeight.Bold else FontWeight.Normal,
      fontStyle = if (uiState.isItalic) FontStyle.Italic else FontStyle.Normal,
      textAlign = TextAlign.Center,
      lineHeight = (uiState.fontSizeSp * 1.45f).sp,
      style = TextStyle(
        shadow = if (uiState.textShadow) Shadow(color = Color.Black, blurRadius = 8f) else null
      )
    )
  }
}
