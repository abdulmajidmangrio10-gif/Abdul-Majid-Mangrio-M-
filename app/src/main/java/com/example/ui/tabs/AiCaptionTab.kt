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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CaptionItem
import com.example.data.model.SurahInfo
import com.example.data.repository.QuranRepository
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun AiCaptionTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  var searchQuery by remember { mutableStateOf("") }
  var showSurahPicker by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("ai_caption_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. AI Auto-Alignment Status Banner
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F261E)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1B493A))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = Color(0xFFD4AF37)
              )
              Text(
                text = "AI آٹو الائنمنٹ (Quran Whisper AI)",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
            }

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = if (uiState.isAligningWithAi) Color(0xFF92400E) else Color(0xFF065F46)
            ) {
              Text(
                text = if (uiState.isAligningWithAi) "AI الائن کر رہا ہے..." else "98% درستگی (Active)",
                color = Color(0xFFFDE68A),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "AI نے تلاوت کی سورت اور آیات کا خودکار تعین کر لیا ہے۔ ہر لفظ کا وقت قرآن کے مصحف مدینہ سے ہم آہنگ ہے۔",
            color = Color(0xFFCBD5E1),
            fontSize = 12.sp,
            lineHeight = 18.sp
          )
        }
      }
    }

    // 2. Surah & Ayah Selector Section
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // Surah button
        OutlinedButton(
          onClick = { showSurahPicker = !showSurahPicker },
          modifier = Modifier.weight(1f),
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFFD4AF37)
          ),
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD4AF37).copy(alpha = 0.5f))
        ) {
          Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "${uiState.selectedSurah.id}. ${uiState.selectedSurah.nameArabic}",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
        }

        // Quick Ayah Range Selector
        Row(
          modifier = Modifier
            .background(Color(0xFF132A21), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "آیات: ${uiState.ayahStart} - ${uiState.ayahEnd}",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
          )
        }
      }
    }

    // Surah Dropdown/List when opened
    if (showSurahPicker) {
      item {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF10211A))
        ) {
          Column(modifier = Modifier.padding(10.dp)) {
            Text(
              text = "سورت منتخب کریں:",
              color = Color(0xFFD4AF37),
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              items(QuranRepository.surahs) { surah ->
                val isSelected = surah.id == uiState.selectedSurah.id
                Surface(
                  shape = RoundedCornerShape(10.dp),
                  color = if (isSelected) Color(0xFFD4AF37) else Color(0xFF183B2E),
                  modifier = Modifier.clickable {
                    viewModel.selectSurah(surah)
                    showSurahPicker = false
                  }
                ) {
                  Text(
                    text = "${surah.id}. ${surah.nameArabic}",
                    color = if (isSelected) Color(0xFF0F261E) else Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    // 3. Highlighting Mode Switches (Word-by-word & Letter-by-letter)
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1E19))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          // Word-by-word switch
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "ورڈ بائی ورڈ ہائی لائٹنگ (Word Glow)",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "جیسے قاری لفظ پڑھے وہ سنہری / سبز رنگ میں چمکے",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }
            Switch(
              checked = uiState.highlightWordsWordByWord,
              onCheckedChange = { viewModel.toggleWordHighlight(it) },
              colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFD4AF37),
                checkedTrackColor = Color(0xFF10B981)
              )
            )
          }

          Divider(color = Color(0xFF1E3A2E), modifier = Modifier.padding(vertical = 10.dp))

          // Letter-by-letter switch
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "لیٹر بائی لیٹر ہائی لائٹنگ (Letter Glow)",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "حرف بہ حرف نورانی گلو اینیمیشن",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }
            Switch(
              checked = uiState.highlightLettersGlow,
              onCheckedChange = { viewModel.toggleLetterGlow(it) },
              colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFD4AF37),
                checkedTrackColor = Color(0xFF10B981)
              )
            )
          }
        }
      }
    }

    // 4. Captions List with Confidence & Manual Nudge Buttons
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "کیپشن ٹائم اسٹیمپس اور خودکار درستگی:",
          color = Color(0xFFD4AF37),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "${uiState.captions.size} آیات الائنڈ",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp
        )
      }
    }

    items(uiState.captions) { caption ->
      CaptionItemCard(
        caption = caption,
        onNudgeStart = { delta -> viewModel.updateCaptionTiming(caption.id, delta, 0L) },
        onNudgeEnd = { delta -> viewModel.updateCaptionTiming(caption.id, 0L, delta) },
        onDelete = { viewModel.deleteCaption(caption.id) }
      )
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun CaptionItemCard(
  caption: CaptionItem,
  onNudgeStart: (Long) -> Unit,
  onNudgeEnd: (Long) -> Unit,
  onDelete: () -> Unit
) {
  val isLowConfidence = caption.confidence < 0.88f

  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isLowConfidence) Color(0xFF291F0A) else Color(0xFF11261D)
    ),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      if (isLowConfidence) Color(0xFFEAB308) else Color(0xFF1B493A)
    )
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Ayah badge
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = Color(0xFF163C2E)
        ) {
          Text(
            text = "آیت ${caption.ayahNumber}",
            color = Color(0xFFD4AF37),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }

        // AI Confidence score badge
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = if (isLowConfidence) Color(0xFFCA8A04) else Color(0xFF059669)
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = if (isLowConfidence) Icons.Default.Warning else Icons.Default.CheckCircle,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = if (isLowConfidence) "AI Confidence ${(caption.confidence * 100).toInt()}% (ریویو کریں)"
              else "AI Confidence ${(caption.confidence * 100).toInt()}%",
              color = Color.White,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        IconButton(
          onClick = onDelete,
          modifier = Modifier.size(24.dp)
        ) {
          Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = "Delete",
            tint = Color(0xFFEF4444),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Arabic text
      Text(
        text = caption.arabicText,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
      )

      // Translation text
      Text(
        text = caption.translationText,
        color = Color(0xFF94A3B8),
        fontSize = 12.sp,
        modifier = Modifier.padding(top = 2.dp)
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Timing and manual adjustment row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        val startSec = caption.startMs / 1000.0
        val endSec = caption.endMs / 1000.0

        Text(
          text = String.format("%.1fs → %.1fs", startSec, endSec),
          color = Color(0xFF38BDF8),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )

        // Nudge adjustments
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFF1B3D2F),
            modifier = Modifier.clickable { onNudgeStart(-200L) }
          ) {
            Text(
              text = "-0.2s",
              color = Color(0xFFCBD5E1),
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
            )
          }
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color(0xFF1B3D2F),
            modifier = Modifier.clickable { onNudgeEnd(200L) }
          ) {
            Text(
              text = "+0.2s",
              color = Color(0xFFCBD5E1),
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
            )
          }
        }
      }
    }
  }
}
