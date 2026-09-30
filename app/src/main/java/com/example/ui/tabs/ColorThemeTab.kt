package com.example.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.WaterDrop
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
fun ColorThemeTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("color_theme_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Header Banner
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E4C3A))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(imageVector = Icons.Default.Palette, contentDescription = null, tint = Color(0xFFD4AF37))
            Text(
              text = "کلر سکیمز اور لیکوڈ کلرز (Liquid Themes)",
              color = Color.White,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "پانی کی طرح رواں لیکوڈ گرڈینٹس اور کعبہ و مدینہ سے متاثر سنہری و زمردی تھیمز۔ خودکار کنٹراسٹ پروٹیکشن آن ہے۔",
            color = Color(0xFFCBD5E1),
            fontSize = 12.sp
          )
        }
      }
    }

    // 2. ⭐ Liquid Color Themes Section
    item {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(imageVector = Icons.Default.WaterDrop, contentDescription = null, tint = Color(0xFF38BDF8), modifier = Modifier.size(18.dp))
        Text(
          text = "⭐ لیکوڈ کلر اینیمیٹڈ تھیمز (Flowing Liquid Gradients):",
          color = Color(0xFF38BDF8),
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    items(PresetRepository.colorThemes.filter { it.isLiquid }) { theme ->
      val isSelected = uiState.currentTheme.id == theme.id

      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF153B2C) else Color(0xFF0F1E19)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3D2F)
        ),
        modifier = Modifier.clickable { viewModel.selectTheme(theme) }
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
            // Liquid color preview orb
            Box(
              modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(
                  Brush.linearGradient(theme.liquidColors.map { Color(it) })
                )
                .border(1.5.dp, Color.White.copy(alpha = 0.4f), CircleShape)
            )

            Column {
              Text(
                text = theme.name,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "${theme.urduName} • متحرک گرڈینٹ",
                color = Color(0xFFD4AF37),
                fontSize = 11.sp
              )
            }
          }

          if (isSelected) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = "Selected",
              tint = Color(0xFFD4AF37),
              modifier = Modifier.size(24.dp)
            )
          }
        }
      }
    }

    // 3. Classic Islamic Themes Section
    item {
      Text(
        text = "اسلامک اور کلاسک تھیمز (20+ Preset Themes):",
        color = Color(0xFFD4AF37),
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp)
      )
    }

    items(PresetRepository.colorThemes.filterNot { it.isLiquid }) { theme ->
      val isSelected = uiState.currentTheme.id == theme.id

      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF153B2C) else Color(0xFF0F1E19)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3D2F)
        ),
        modifier = Modifier.clickable { viewModel.selectTheme(theme) }
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
            // Color swatch
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(theme.highlightGlow))
                .border(2.dp, Color(theme.barBackground), CircleShape)
            )

            Column {
              Text(
                text = theme.name,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = theme.urduName,
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
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
