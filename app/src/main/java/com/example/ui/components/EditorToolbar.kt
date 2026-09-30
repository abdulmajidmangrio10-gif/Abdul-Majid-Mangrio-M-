package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class EditorTabItem(
  val titleUrdu: String,
  val titleEn: String,
  val icon: ImageVector
)

val editorTabs = listOf(
  EditorTabItem("AI کیپشن", "Caption", Icons.Default.Subtitles),
  EditorTabItem("وائس اسٹوڈیو", "Voice", Icons.Default.Mic),
  EditorTabItem("فونٹس و متن", "Fonts", Icons.Default.FontDownload),
  EditorTabItem("اینیمیشن", "Anim", Icons.Default.Animation),
  EditorTabItem("کیپشن بار", "Band", Icons.Default.TableRows),
  EditorTabItem("کلر تھیم", "Theme", Icons.Default.Palette),
  EditorTabItem("وال پیپر", "Bg", Icons.Default.Wallpaper),
  EditorTabItem("ایڈیٹنگ ٹولز", "Tools", Icons.Default.AutoFixHigh),
  EditorTabItem("ایکسپورٹ", "Export", Icons.Default.FileDownload)
)

@Composable
fun EditorToolbar(
  selectedTab: Int,
  onTabSelected: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .testTag("editor_toolbar"),
    color = Color(0xFF091712),
    shadowElevation = 4.dp
  ) {
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      itemsIndexed(editorTabs) { index, tab ->
        val isSelected = selectedTab == index

        Surface(
          shape = RoundedCornerShape(12.dp),
          color = if (isSelected) Color(0xFFD4AF37) else Color(0xFF11261D),
          border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E3A2F)),
          modifier = Modifier
            .clickable { onTabSelected(index) }
            .testTag("tab_button_$index")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = tab.icon,
              contentDescription = tab.titleEn,
              tint = if (isSelected) Color(0xFF071F17) else Color(0xFFD4AF37),
              modifier = Modifier.size(16.dp)
            )
            Text(
              text = tab.titleUrdu,
              color = if (isSelected) Color(0xFF071F17) else Color.White,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }
}
