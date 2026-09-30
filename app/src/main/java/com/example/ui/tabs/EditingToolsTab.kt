package com.example.ui.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.PresetRepository
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun EditingToolsTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("editing_tools_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Islamic Calligraphy & Stickers
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E4C3A))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "⭐ اسلامی اسٹیکرز اور خوبصورت خطاطی (500+ Calligraphy & Stickers):",
            color = Color(0xFFD4AF37),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(10.dp))

          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(PresetRepository.stickers) { sticker ->
              Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFF163C2E),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x44D4AF37)),
                modifier = Modifier.clickable { viewModel.addSticker(sticker) }
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Text(text = sticker.symbol, fontSize = 16.sp)
                  Text(text = sticker.text, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        }
      }
    }

    // 2. Video Filters Section
    item {
      Text(
        text = "30+ سنیماٹک ویڈیو فلٹرز (Video Filters):",
        color = Color(0xFFD4AF37),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
    }

    items(PresetRepository.videoFilters) { filter ->
      val isSelected = uiState.currentFilter.id == filter.id

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF163E2F) else Color(0xFF0F1E19)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3D2F)
        ),
        modifier = Modifier.clickable { viewModel.selectVideoFilter(filter) }
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Icon(imageVector = Icons.Default.AutoFixHigh, contentDescription = null, tint = Color(0xFFD4AF37))
            Column {
              Text(text = filter.name, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
              Text(text = filter.urduName, color = Color(0xFF94A3B8), fontSize = 11.sp)
            }
          }

          if (isSelected) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Selected", tint = Color(0xFFD4AF37), modifier = Modifier.size(20.dp))
          }
        }
      }
    }

    // 3. Audio Mixing Sliders (Recitation vs Ambient Halal Nasheed)
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(imageVector = Icons.Default.VolumeUp, contentDescription = null, tint = Color(0xFFD4AF37))
            Text(text = "صوتی توازن (Audio Studio Controls):", color = Color(0xFFD4AF37), fontSize = 13.sp, fontWeight = FontWeight.Bold)
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Recitation Volume Slider
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "تلاوت قرآن والیوم", color = Color.White, fontSize = 12.sp)
            Text(text = "${(uiState.recitationVolume * 100).toInt()}%", color = Color(0xFF38BDF8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
          }
          Slider(
            value = uiState.recitationVolume,
            onValueChange = { /* state update */ },
            colors = SliderDefaults.colors(thumbColor = Color(0xFFD4AF37), activeTrackColor = Color(0xFF10B981))
          )

          Spacer(modifier = Modifier.height(6.dp))

          // Ambient Background Nasheed Slider
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "پس منظر میں ہلکی نشید / سکون بخش ہوا", color = Color.White, fontSize = 12.sp)
            Text(text = "${(uiState.backgroundNasheedVolume * 100).toInt()}%", color = Color(0xFF38BDF8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
          }
          Slider(
            value = uiState.backgroundNasheedVolume,
            onValueChange = { /* state update */ },
            colors = SliderDefaults.colors(thumbColor = Color(0xFF38BDF8), activeTrackColor = Color(0xFF0284C7))
          )
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}
