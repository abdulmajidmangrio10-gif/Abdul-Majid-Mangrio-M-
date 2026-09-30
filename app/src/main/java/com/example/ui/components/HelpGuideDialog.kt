package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

data class GuideItem(
  val questionUrdu: String,
  val answerUrdu: String,
  val path: String,
  val icon: String
)

@Composable
fun HelpGuideDialog(
  onDismiss: () -> Unit,
  onNavigateToTab: (Int) -> Unit
) {
  val guideItems = listOf(
    GuideItem(
      questionUrdu = "آٹو کیپشن اور آیات کی الائنمنٹ کہاں ہے؟",
      answerUrdu = "سب سے پہلے ٹیب 'AI کیپشن' پر جائیں۔ یہاں AI خودکار طور پر آڈیو سن کر سورت اور آیت کی ٹائمنگ سیٹ کرتا ہے۔",
      path = "ایڈیٹر → ٹیب 1 (AI کیپشن)",
      icon = "🎯"
    ),
    GuideItem(
      questionUrdu = "اپنی آواز کو قاری جیسی کیسے بنائیں؟",
      answerUrdu = "'Voice Studio' ٹیب میں جائیں، شیخ عبدالباسط یا امام کعبہ السدیس چنیں اور 'Convert to Qari' دبائیں۔ 10 سیکنڈ نمونہ بھی سن سکتے ہیں۔",
      path = "ایڈیٹر → ٹیب 2 (وائس اسٹوڈیو)",
      icon = "🎙️"
    ),
    GuideItem(
      questionUrdu = "فونٹ کیسے بدلیں اور لائیو ٹیسٹ کریں؟",
      answerUrdu = "'فونٹس و ٹیکسٹ' ٹیب پر جائیں۔ یہاں 'فونٹ ٹیسٹ لیب' میں ہر فونٹ پر کلک کرنے سے بسم اللہ شریف فوری اسی فونٹ میں تبدیل ہو کر لائیو نظر آتی ہے۔",
      path = "ایڈیٹر → ٹیب 3 (فونٹس و متن)",
      icon = "✨"
    ),
    GuideItem(
      questionUrdu = "نیچے کی پٹی (Lower Third) کیسے لگائیں؟",
      answerUrdu = "'کیپشن بار' ٹیب میں جا کر بٹن آن کریں، پٹی کی پوزیشن (نیچے، درمیان، اوپر) اور سائڈ لائن کا رنگ منتخب کریں۔",
      path = "ایڈیٹر → ٹیب 5 (کیپشن بار)",
      icon = "📊"
    ),
    GuideItem(
      questionUrdu = "لیکوڈ کلرز اور گرڈینٹ تھیمز کیسے لگائیں؟",
      answerUrdu = "'کلر تھیم' ٹیب میں جائیں، 'Liquid Gold' یا 'Liquid Ocean' چنیں، اسکرین پر پانی کی طرح بہتا ہوا رنگ شروع ہو جائے گا۔",
      path = "ایڈیٹر → ٹیب 6 (کلر تھیم)",
      icon = "💧"
    ),
    GuideItem(
      questionUrdu = "اینیمیشن (کاراؤکے اور ورڈ بائی ورڈ) کہاں ہے؟",
      answerUrdu = "'اینیمیشن' ٹیب میں ورڈ بائی ورڈ یا کاراؤکے اسٹائل منتخب کریں تاکہ تلاوت کے دوران الفاظ خودکار چمکیں۔",
      path = "ایڈیٹر → ٹیب 4 (اینیمیشن)",
      icon = "⚡"
    ),
    GuideItem(
      questionUrdu = "کعبہ، مدینہ اور قدرتی وال پیپر کیسے لگائیں؟",
      answerUrdu = "'وال پیپر' ٹیب میں 1000+ HD وال پیپرز اور متحرک ویڈیو کلپس موجود ہیں، یا اپنی گیلری سے اپلوڈ کریں۔",
      path = "ایڈیٹر → ٹیب 7 (وال پیپر)",
      icon = "🕋"
    ),
    GuideItem(
      questionUrdu = "یوٹیوب چیپٹرز اور 9:16 ریلز ایکسپورٹ کیسے کریں؟",
      answerUrdu = "'ایکسپورٹ' ٹیب میں 9:16 یا 16:9 منتخب کریں، یوٹیوب چیپٹرز ٹیکسٹ کاپی کریں اور Export بٹن دبائیں۔",
      path = "ایڈیٹر → ٹیب 9 (ایکسپورٹ)",
      icon = "🚀"
    )
  )

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.85f)
        .testTag("help_guide_dialog"),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0A1C16)),
      border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD4AF37))
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
      ) {
        // Header
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
              imageVector = Icons.Default.HelpOutline,
              contentDescription = null,
              tint = Color(0xFFD4AF37)
            )
            Text(
              text = "اسٹوڈیو گائیڈ (کیا کہاں لگانا ہے)",
              color = Color.White,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
          }

          IconButton(onClick = onDismiss) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = Color.White
            )
          }
        }

        Divider(color = Color(0xFF1B493A), modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn(
          verticalArrangement = Arrangement.spacedBy(10.dp),
          modifier = Modifier.weight(1f)
        ) {
          items(guideItems) { item ->
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFF112920),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1D4737))
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                  Text(text = item.icon, fontSize = 18.sp)
                  Text(
                    text = item.questionUrdu,
                    color = Color(0xFFD4AF37),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                  )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                  text = item.answerUrdu,
                  color = Color(0xFFCBD5E1),
                  fontSize = 12.sp,
                  lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = Color(0xFF091C15)
                ) {
                  Text(
                    text = "مقام: ${item.path}",
                    color = Color(0xFF38BDF8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                  )
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
          onClick = onDismiss,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD4AF37),
            contentColor = Color(0xFF071F17)
          )
        ) {
          Text(text = "سمجھ گیا (Got it)", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
