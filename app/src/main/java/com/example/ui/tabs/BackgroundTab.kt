package com.example.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.PresetRepository
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun BackgroundTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("background_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Upload Custom Background from Gallery
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E4C3A))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Icon(imageVector = Icons.Default.AddPhotoAlternate, contentDescription = null, tint = Color(0xFFD4AF37))
            Column {
              Text(
                text = "گیلری سے وال پیپر اپلوڈ کریں",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "اپنی تصویر یا ویڈیو کلپ بطور پس منظر منتخب کریں",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }
          }

          Button(
            onClick = { /* simulated file picker feedback */ },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF194435),
              contentColor = Color(0xFFD4AF37)
            )
          ) {
            Text(text = "منتخب کریں", fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }
        }
      }
    }

    // 2. Blur & Dark Overlay Sliders
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "پس منظر اور متن کی شفافیت ایڈجسٹمنٹ:",
            color = Color(0xFFD4AF37),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Dark Overlay Opacity Slider
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "ڈارک اوورلے (متن کے ابھار کے لیے)", color = Color.White, fontSize = 12.sp)
            Text(
              text = "${(uiState.backgroundDarkOverlay * 100).toInt()}%",
              color = Color(0xFF38BDF8),
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Slider(
            value = uiState.backgroundDarkOverlay,
            onValueChange = { viewModel.setBackgroundDarkOverlay(it) },
            valueRange = 0.1f..0.85f,
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFFD4AF37),
              activeTrackColor = Color(0xFF10B981)
            )
          )

          Spacer(modifier = Modifier.height(6.dp))

          // Blur Dp Slider
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "بلور اثر (Soft Background Blur)", color = Color.White, fontSize = 12.sp)
            Text(
              text = "${uiState.backgroundBlurDp.toInt()} dp",
              color = Color(0xFF38BDF8),
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Slider(
            value = uiState.backgroundBlurDp,
            onValueChange = { viewModel.setBackgroundBlur(it) },
            valueRange = 0f..12f,
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFFD4AF37),
              activeTrackColor = Color(0xFF10B981)
            )
          )
        }
      }
    }

    // 3. HD Islamic Backgrounds & Motion Clips List
    item {
      Text(
        text = "1000+ HD اور متحرک ویڈیو وال پیپرز:",
        color = Color(0xFFD4AF37),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
    }

    items(PresetRepository.backgrounds) { bg ->
      val isSelected = uiState.currentBackground.id == bg.id

      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF153F2E) else Color(0xFF0F1E19)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3D2F)
        ),
        modifier = Modifier.clickable { viewModel.selectBackground(bg) }
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
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            // Thumbnail preview block
            Box(
              modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                  Brush.verticalGradient(bg.colors.map { Color(it) })
                )
                .border(1.dp, Color(0x44D4AF37), RoundedCornerShape(10.dp)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Wallpaper,
                contentDescription = null,
                tint = Color(0xFFD4AF37),
                modifier = Modifier.size(24.dp)
              )
            }

            Column {
              Text(
                text = bg.title,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "${bg.urduTitle} • ${bg.category}",
                color = Color(0xFFD4AF37),
                fontSize = 11.sp
              )
              if (bg.isAnimated) {
                Text(
                  text = "⚡ متحرک: ${bg.motionDescription}",
                  color = Color(0xFF38BDF8),
                  fontSize = 10.sp
                )
              }
            }
          }

          if (isSelected) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = "Selected",
              tint = Color(0xFFD4AF37),
              modifier = Modifier.size(22.dp)
            )
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}
