package com.example.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CaptionBarPosition
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun CaptionBarTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  val bar = uiState.captionBar

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("caption_bar_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Header & Master Enable Switch
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E4C3A))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(imageVector = Icons.Default.TableRows, contentDescription = null, tint = Color(0xFFD4AF37))
              Column {
                Text(
                  text = "⭐ نیچے کی پٹی (Caption Band / Lower Third)",
                  color = Color.White,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
                )
                Text(
                  text = "پروفیشنل ٹی وی اور اسلامک چینلز جیسا براڈکاسٹ بینر",
                  color = Color(0xFF94A3B8),
                  fontSize = 11.sp
                )
              }
            }

            Switch(
              checked = bar.isEnabled,
              onCheckedChange = { viewModel.updateCaptionBar(bar.copy(isEnabled = it)) },
              colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFD4AF37),
                checkedTrackColor = Color(0xFF10B981)
              )
            )
          }
        }
      }
    }

    if (bar.isEnabled) {
      // 2. Position Selector (Bottom, Center, Top)
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text(
              text = "پٹی کی پوزیشن (Bar Position):",
              color = Color(0xFFD4AF37),
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              listOf(
                CaptionBarPosition.BOTTOM to "نیچے (Bottom)",
                CaptionBarPosition.CENTER to "درمیان (Center)",
                CaptionBarPosition.TOP to "اوپر (Top)"
              ).forEach { (pos, label) ->
                val isSelected = bar.position == pos
                Surface(
                  shape = RoundedCornerShape(10.dp),
                  color = if (isSelected) Color(0xFFD4AF37) else Color(0xFF193B2F),
                  modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.updateCaptionBar(bar.copy(position = pos)) }
                ) {
                  Text(
                    text = label,
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

      // 3. Opacity & Rounded Corners Sliders
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            // Opacity slider
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(text = "پٹی کی شفافیت (Opacity)", color = Color.White, fontSize = 12.sp)
              Text(text = "${(bar.opacity * 100).toInt()}%", color = Color(0xFF38BDF8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Slider(
              value = bar.opacity,
              onValueChange = { viewModel.updateCaptionBar(bar.copy(opacity = it)) },
              valueRange = 0.2f..1.0f,
              colors = SliderDefaults.colors(
                thumbColor = Color(0xFFD4AF37),
                activeTrackColor = Color(0xFF10B981)
              )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Corner Radius slider
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(text = "گول کونے (Corner Radius)", color = Color.White, fontSize = 12.sp)
              Text(text = "${bar.cornerRadiusDp} dp", color = Color(0xFF38BDF8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Slider(
              value = bar.cornerRadiusDp.toFloat(),
              onValueChange = { viewModel.updateCaptionBar(bar.copy(cornerRadiusDp = it.toInt())) },
              valueRange = 0f..28f,
              colors = SliderDefaults.colors(
                thumbColor = Color(0xFFD4AF37),
                activeTrackColor = Color(0xFF10B981)
              )
            )
          }
        }
      }

      // 4. Sidebar Accent & Badges
      item {
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            // Sidebar Accent Line switch
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(text = "سائڈ بار لائن (Vertical Accent Line)", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Text(text = "براڈکاسٹ چینلز کی طرح پٹی کے بائیں جانب نمایاں لکیر", color = Color(0xFF94A3B8), fontSize = 11.sp)
              }
              Switch(
                checked = bar.hasSidebarAccent,
                onCheckedChange = { viewModel.updateCaptionBar(bar.copy(hasSidebarAccent = it)) }
              )
            }

            Divider(color = Color(0xFF1B3D2F), modifier = Modifier.padding(vertical = 10.dp))

            // Surah Badge switch
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "سورت اور آیت کا ٹیگ دکھائیں", color = Color.White, fontSize = 13.sp)
              Switch(
                checked = bar.showSurahBadge,
                onCheckedChange = { viewModel.updateCaptionBar(bar.copy(showSurahBadge = it)) }
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Qari Badge switch
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "قاری صاحب کا نام دکھائیں", color = Color.White, fontSize = 13.sp)
              Switch(
                checked = bar.showQariBadge,
                onCheckedChange = { viewModel.updateCaptionBar(bar.copy(showQariBadge = it)) }
              )
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
