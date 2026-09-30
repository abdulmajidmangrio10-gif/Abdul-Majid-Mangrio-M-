package com.example.ui.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CaptionAnimationType
import com.example.data.model.TextAnimationType
import com.example.ui.StudioUiState
import com.example.ui.StudioViewModel

@Composable
fun AnimationTab(
  uiState: StudioUiState,
  viewModel: StudioViewModel,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 14.dp, vertical = 8.dp)
      .testTag("animation_tab_content"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Header
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
            Icon(imageVector = Icons.Default.Animation, contentDescription = null, tint = Color(0xFFD4AF37))
            Text(
              text = "اینیمیشن اسٹوڈیو (Text & Caption Animation)",
              color = Color.White,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "تلاوت قرآن کے شایانِ شان پرسکون، نورانی اور رواں اینیمیشنز منتخب کریں۔",
            color = Color(0xFFCBD5E1),
            fontSize = 12.sp
          )
        }
      }
    }

    // 2. ⭐ Caption Sync Animations (Karaoke, Word-by-word, Line-by-line)
    item {
      Text(
        text = "⭐ کیپشن ظہور اینیمیشن (Caption Style):",
        color = Color(0xFFD4AF37),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
    }

    items(CaptionAnimationType.values().toList()) { capAnim ->
      val isSelected = uiState.captionAnimation == capAnim

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF163E2F) else Color(0xFF0E1F18)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3D2F)
        ),
        modifier = Modifier.clickable { viewModel.setCaptionAnimation(capAnim) }
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text(
              text = capAnim.urduLabel,
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = capAnim.label,
              color = Color(0xFFD4AF37),
              fontSize = 12.sp
            )
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

    // 3. ⭐ Text Effect Animations (Fade, Slide, Glow, Liquid)
    item {
      Text(
        text = "⭐ فونٹ اینیمیشن اور نورانی اثرات (Text Animation):",
        color = Color(0xFFD4AF37),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
    }

    items(TextAnimationType.values().toList()) { textAnim ->
      val isSelected = uiState.textAnimation == textAnim

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isSelected) Color(0xFF163E2F) else Color(0xFF0E1F18)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.5.dp,
          if (isSelected) Color(0xFFD4AF37) else Color(0xFF1B3D2F)
        ),
        modifier = Modifier.clickable { viewModel.setTextAnimation(textAnim) }
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text(
              text = textAnim.urduLabel,
              color = Color.White,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = textAnim.label,
              color = Color(0xFF94A3B8),
              fontSize = 11.sp
            )
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
