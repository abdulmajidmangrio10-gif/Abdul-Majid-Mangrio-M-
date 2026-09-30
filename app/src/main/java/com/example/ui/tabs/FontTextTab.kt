package com.example.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FontPreset
import com.example.data.repository.PresetRepository
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun FontTextTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  var selectedCategory by remember { mutableStateOf("Arabic") } // "Arabic" or "Urdu/English"

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("font_text_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. ⭐ Live Font Test Interactive Preview Lab
    item {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD4AF37))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(imageVector = Icons.Default.FontDownload, contentDescription = null, tint = Color(0xFFD4AF37))
              Text(
                text = "⭐ فونٹ ٹیسٹ لیب (Live Font Preview)",
                color = Color(0xFFD4AF37),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
            }
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = Color(0xFF194435)
            ) {
              Text(
                text = uiState.currentFont.name,
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Live dynamic test preview of Bismillah & Ayah in currently selected font
          Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF07140F),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1B3D2F))
          ) {
            Column(
              modifier = Modifier.padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ",
                color = Color(uiState.currentTheme.highlightGlow),
                fontSize = (uiState.fontSizeSp * 1.1f).sp,
                fontWeight = if (uiState.isBold) FontWeight.Bold else FontWeight.Normal,
                fontStyle = if (uiState.isItalic) FontStyle.Italic else FontStyle.Normal,
                textAlign = TextAlign.Center
              )
              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = uiState.currentFont.fontStyleDesc,
                color = Color(0xFF94A3B8),
                fontSize = 11.sp,
                textAlign = TextAlign.Center
              )
            }
          }
        }
      }
    }

    // 2. Category Switch (Arabic vs Urdu/English)
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        listOf("Arabic" to "عربی فونٹس (30+ Fonts)", "Urdu/English" to "اردو و انگریزی فونٹس (20+ Fonts)").forEach { (cat, title) ->
          val isSelected = selectedCategory == cat
          Button(
            onClick = { selectedCategory = cat },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = if (isSelected) Color(0xFFD4AF37) else Color(0xFF132A21),
              contentColor = if (isSelected) Color(0xFF071F17) else Color.White
            )
          ) {
            Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }
        }
      }
    }

    // 3. Fonts List
    items(PresetRepository.fonts.filter { it.category == selectedCategory }) { font ->
      val isSelected = font.id == uiState.currentFont.id

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF153F2E) else Color(0xFF0F1E19)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3B2E)
        ),
        modifier = Modifier.clickable { viewModel.selectFont(font) }
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = font.urduName,
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = font.name,
              color = Color(0xFFD4AF37),
              fontSize = 11.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = font.sampleText,
              color = Color(0xFFE2E8F0),
              fontSize = 15.sp
            )
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

    // 4. Font Typography Adjustments (Size, Bold, Outline)
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "ٹائپوگرافی اور سائز ایڈجسٹمنٹس:",
            color = Color(0xFFD4AF37),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Font Size Slider
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "فونٹ سائز (Font Size)", color = Color.White, fontSize = 12.sp)
            Text(
              text = "${uiState.fontSizeSp.toInt()} sp",
              color = Color(0xFF38BDF8),
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Slider(
            value = uiState.fontSizeSp,
            onValueChange = { viewModel.setFontSize(it) },
            valueRange = 18f..42f,
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFFD4AF37),
              activeTrackColor = Color(0xFF10B981)
            )
          )

          Divider(color = Color(0xFF1B3D2F), modifier = Modifier.padding(vertical = 8.dp))

          // Display mode: Both, Arabic Only, Translation Only
          Text(
            text = "ڈسپلے موڈ (عربی + ترجمہ):",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(6.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf("Both", "Arabic Only", "Translation Only").forEach { mode ->
              val isSelected = when (mode) {
                "Both" -> uiState.showArabic && uiState.showTranslation
                "Arabic Only" -> uiState.showArabic && !uiState.showTranslation
                "Translation Only" -> !uiState.showArabic && uiState.showTranslation
                else -> false
              }
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) Color(0xFFD4AF37) else Color(0xFF183B2E),
                modifier = Modifier
                  .weight(1f)
                  .clickable { viewModel.setTranslationDisplayMode(mode) }
              ) {
                Text(
                  text = when (mode) {
                    "Both" -> "عربی + ترجمہ"
                    "Arabic Only" -> "صرف عربی"
                    else -> "صرف ترجمہ"
                  },
                  color = if (isSelected) Color(0xFF0F261E) else Color.White,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  textAlign = TextAlign.Center,
                  modifier = Modifier.padding(vertical = 8.dp)
                )
              }
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
