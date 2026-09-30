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
import com.example.data.model.QariVoice
import com.example.data.repository.PresetRepository
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun VoiceStudioTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  var isPreviewPlaying by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("voice_studio_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Header Banner
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0D241C)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E4C3A))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.GraphicEq,
              contentDescription = null,
              tint = Color(0xFFD4AF37)
            )
            Text(
              text = "AI آواز کنورژن (اپنی آواز → قاری کی آواز)",
              color = Color.White,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "اپنی تلاوت کی ریکارڈنگ اپلوڈ کریں اور AI کے ذریعے دنیا کے نامور قاریوں کے لہجے اور پختہ تجوید میں تبدیل کریں۔",
            color = Color(0xFFCBD5E1),
            fontSize = 12.sp,
            lineHeight = 18.sp
          )
        }
      }
    }

    // 2. Qari Selection List
    item {
      Text(
        text = "پسندیدہ قاری منتخب کریں:",
        color = Color(0xFFD4AF37),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
    }

    items(PresetRepository.qariVoices) { qari ->
      val isSelected = qari.id == uiState.voiceState.selectedQari.id

      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF143B2C) else Color(0xFF0F1E19)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1E3A2E)
        ),
        modifier = Modifier.clickable { viewModel.selectQariVoice(qari) }
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Qari Avatar / Icon
          Box(
            modifier = Modifier
              .size(46.dp)
              .clip(CircleShape)
              .background(Color(0xFF0A1F17))
              .border(1.dp, Color(0xFFD4AF37), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Text(text = qari.icon, fontSize = 22.sp)
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = qari.name,
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "${qari.arabicName} • ${qari.title}",
              color = Color(0xFFD4AF37),
              fontSize = 11.sp
            )
            Text(
              text = qari.description,
              color = Color(0xFF94A3B8),
              fontSize = 11.sp,
              maxLines = 2,
              lineHeight = 15.sp,
              modifier = Modifier.padding(top = 2.dp)
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

    // 3. 10-Second Voice Preview & Convert Action
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF11261D))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          // Preview button row
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "10 سیکنڈ نمونہ سنیں (Voice Preview)",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "${uiState.voiceState.selectedQari.name} کا صوتی نمونہ",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }

            IconButton(
              onClick = { isPreviewPlaying = !isPreviewPlaying },
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFD4AF37))
            ) {
              Icon(
                imageVector = if (isPreviewPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = "Preview",
                tint = Color(0xFF0B281F)
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Main Convert Button
          Button(
            onClick = { viewModel.convertRecitationVoice() },
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("convert_voice_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFD4AF37),
              contentColor = Color(0xFF071F17)
            ),
            enabled = !uiState.voiceState.isConverting
          ) {
            if (uiState.voiceState.isConverting) {
              CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color(0xFF071F17),
                strokeWidth = 2.dp
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "AI آواز تبدیل کر رہا ہے (${(uiState.voiceState.conversionProgress * 100).toInt()}%)...",
                fontWeight = FontWeight.Bold
              )
            } else {
              Icon(imageVector = Icons.Default.Transform, contentDescription = null)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = if (uiState.voiceState.isConverted) "دوبارہ تبدیل کریں (Re-Convert)" else "آواز تبدیل کریں (Convert to Qari)",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
              )
            }
          }
        }
      }
    }

    // 4. Voice Controls (Speed, Pitch, Mix & Cleanup)
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1E19))
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text(
            text = "آواز ایڈجسٹمنٹس اور مکسنگ:",
            color = Color(0xFFD4AF37),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Mix Ratio Slider (Original Voice vs Converted Voice)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "اصل آواز اور قاری آواز کا مکس", color = Color.White, fontSize = 12.sp)
            Text(
              text = "${(uiState.voiceState.mixRatio * 100).toInt()}% قاری",
              color = Color(0xFF34D399),
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Slider(
            value = uiState.voiceState.mixRatio,
            onValueChange = { frac ->
              viewModel.updateVoiceSettings(
                speed = uiState.voiceState.speed,
                pitch = uiState.voiceState.pitch,
                mix = frac,
                noiseReduction = uiState.voiceState.noiseReduction,
                reverb = uiState.voiceState.haramainReverb
              )
            },
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFFD4AF37),
              activeTrackColor = Color(0xFF10B981)
            )
          )

          // Speed Slider
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "تلاوت کی رفتار (Speed)", color = Color.White, fontSize = 12.sp)
            Text(
              text = "${String.format("%.2f", uiState.voiceState.speed)}x",
              color = Color(0xFF38BDF8),
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Slider(
            value = uiState.voiceState.speed,
            onValueChange = { spd ->
              viewModel.updateVoiceSettings(
                speed = spd,
                pitch = uiState.voiceState.pitch,
                mix = uiState.voiceState.mixRatio,
                noiseReduction = uiState.voiceState.noiseReduction,
                reverb = uiState.voiceState.haramainReverb
              )
            },
            valueRange = 0.75f..1.5f,
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFF38BDF8),
              activeTrackColor = Color(0xFF0284C7)
            )
          )

          Divider(color = Color(0xFF1B3D2F), modifier = Modifier.padding(vertical = 10.dp))

          // Audio Cleanup Switches (Noise reduction, Haramain Echo, Watermark remove)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(text = "شور ختم کرنا (Noise Reduction)", color = Color.White, fontSize = 13.sp)
              Text(text = "پس منظر کی کھڑکھڑاہٹ اور ہوا کی آواز ختم کریں", color = Color(0xFF94A3B8), fontSize = 10.sp)
            }
            Switch(
              checked = uiState.voiceState.noiseReduction,
              onCheckedChange = {
                viewModel.updateVoiceSettings(
                  speed = uiState.voiceState.speed,
                  pitch = uiState.voiceState.pitch,
                  mix = uiState.voiceState.mixRatio,
                  noiseReduction = it,
                  reverb = uiState.voiceState.haramainReverb
                )
              }
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(text = "حرمین شریفین ایکو (Haramain Reverb)", color = Color.White, fontSize = 13.sp)
              Text(text = "مسجد الحرام کے وسیع ہال جیسا قدرتی گونج اثر", color = Color(0xFF94A3B8), fontSize = 10.sp)
            }
            Switch(
              checked = uiState.voiceState.haramainReverb,
              onCheckedChange = {
                viewModel.updateVoiceSettings(
                  speed = uiState.voiceState.speed,
                  pitch = uiState.voiceState.pitch,
                  mix = uiState.voiceState.mixRatio,
                  noiseReduction = uiState.voiceState.noiseReduction,
                  reverb = it
                )
              }
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
