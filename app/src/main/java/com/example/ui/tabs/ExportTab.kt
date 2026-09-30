package com.example.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AspectRatioMode
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun ExportTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("export_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Aspect Ratio Selection (Reels 9:16, 1:1, 16:9)
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E4C3A))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "اسپیکٹ ریشو اور ویڈیو پلیٹ فارم:",
            color = Color(0xFFD4AF37),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            AspectRatioMode.values().forEach { mode ->
              val isSelected = uiState.aspectRatio == mode
              Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isSelected) Color(0xFFD4AF37) else Color(0xFF16382B),
                modifier = Modifier
                  .weight(1f)
                  .clickable { viewModel.setAspectRatio(mode) }
              ) {
                Column(
                  modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Text(
                    text = mode.label.substringBefore(" "),
                    color = if (isSelected) Color(0xFF071F17) else Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                  )
                  Text(
                    text = when (mode) {
                      AspectRatioMode.REELS_9_16 -> "ریلز / ٹک ٹاک"
                      AspectRatioMode.SQUARE_1_1 -> "انسٹاگرام"
                      AspectRatioMode.YOUTUBE_16_9 -> "یوٹیوب"
                    },
                    color = if (isSelected) Color(0xFF164E3A) else Color(0xFF94A3B8),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                  )
                }
              }
            }
          }
        }
      }
    }

    // 2. Export Resolution & Quality
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "ویڈیو کوالٹی اور ریزولوشن:",
            color = Color(0xFFD4AF37),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf("1080p Full HD (60fps)", "4K Ultra HD (HDR)").forEach { res ->
              val isSelected = uiState.exportResolution.startsWith(res.take(5))
              Surface(
                shape = RoundedCornerShape(10.dp),
                color = if (isSelected) Color(0xFFD4AF37) else Color(0xFF183B2E),
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  text = res,
                  color = if (isSelected) Color(0xFF071F17) else Color.White,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Center,
                  modifier = Modifier.padding(vertical = 10.dp)
                )
              }
            }
          }
        }
      }
    }

    // 3. YouTube Chapters Generator Box
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1E19)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1F4435))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(imageVector = Icons.Default.FormatListNumbered, contentDescription = null, tint = Color(0xFFD4AF37))
              Text(
                text = "یوٹیوب چیپٹرز (YouTube Chapters Text):",
                color = Color(0xFFD4AF37),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "ہر آیت کا درست ٹائم اسٹیمپ تیار ہے، ویڈیو ڈسکرپشن میں کاپی پیسٹ کریں:",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF07140F)
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Text(
                text = "00:00 - سُورَةُ الفاتحة [آیت 1]\n00:04 - سُورَةُ الفاتحة [آیت 2]\n00:08 - سُورَةُ الفاتحة [آیت 3]\n00:12 - سُورَةُ الفاتحة [آیت 4]",
                color = Color(0xFF38BDF8),
                fontSize = 11.sp,
                lineHeight = 18.sp
              )
            }
          }
        }
      }
    }

    // 4. Main Export Action & Progress
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF143B2C))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          if (uiState.isExporting) {
            Text(
              text = "ویڈیو تیار ہو رہی ہے (${(uiState.exportProgress * 100).toInt()}%)...",
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
              progress = { uiState.exportProgress },
              modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
              color = Color(0xFFD4AF37),
              trackColor = Color(0xFF0A2219)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "بیک گراؤنڈ ایکسپورٹ فعال ہے، آپ ایپ بند بھی کر سکتے ہیں",
              color = Color(0xFFCBD5E1),
              fontSize = 11.sp
            )
          } else {
            Button(
              onClick = { viewModel.exportVideo() },
              modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("export_video_button"),
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD4AF37),
                contentColor = Color(0xFF071F17)
              )
            ) {
              Icon(imageVector = Icons.Default.FileDownload, contentDescription = null)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "ویڈیو ایکسپورٹ کریں (Export High Quality)",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
              )
            }
          }

          if (uiState.exportedVideoUri != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color(0xFF065F46)
            ) {
              Text(
                text = "✓ ویڈیو گیلری میں محفوظ ہو گئی! (Movies/QuranStudio)",
                color = Color(0xFFFDE68A),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // 5. Direct Social Share
    item {
      Text(
        text = "براہ راست شیئر کریں (Direct Social Share):",
        color = Color(0xFFD4AF37),
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(6.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        listOf(
          "WhatsApp" to Color(0xFF25D366),
          "TikTok" to Color(0xFFFE2C55),
          "Instagram" to Color(0xFFE1306C),
          "YouTube" to Color(0xFFFF0000)
        ).forEach { (platform, color) ->
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFF132A21),
            border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
            modifier = Modifier.weight(1f)
          ) {
            Column(
              modifier = Modifier.padding(vertical = 8.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.height(2.dp))
              Text(text = platform, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}
