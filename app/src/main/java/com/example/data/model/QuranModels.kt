package com.example.data.model

data class SurahInfo(
  val id: Int,
  val nameArabic: String,
  val nameEnglish: String,
  val nameUrdu: String,
  val versesCount: Int,
  val revelationType: String, // "Makkah" or "Madinah"
  val englishMeaning: String
)

data class AyahData(
  val surahId: Int,
  val ayahNumber: Int,
  val arabicUthmani: String,
  val urduMaududi: String,
  val urduJalandhari: String,
  val englishSahih: String,
  val turkish: String = "",
  val words: List<WordData> = emptyList()
)

data class WordData(
  val index: Int,
  val text: String,
  val startMs: Long,
  val endMs: Long,
  val confidence: Float = 0.98f
)

data class CaptionItem(
  val id: String,
  val surahNumber: Int,
  val ayahNumber: Int,
  var startMs: Long,
  var endMs: Long,
  var arabicText: String,
  var translationText: String,
  val words: MutableList<WordTiming> = mutableListOf(),
  var confidence: Float = 0.95f,
  var isPartial: Boolean = false,
  var isSelected: Boolean = false
)

data class WordTiming(
  val word: String,
  var startMs: Long,
  var endMs: Long,
  val confidence: Float = 0.95f
)

data class QariVoice(
  val id: String,
  val name: String,
  val arabicName: String,
  val title: String,
  val style: String,
  val icon: String,
  val description: String,
  val sampleText: String
)

enum class CaptionBarPosition {
  BOTTOM,
  CENTER,
  TOP
}

data class CaptionBarConfig(
  val isEnabled: Boolean = true,
  val position: CaptionBarPosition = CaptionBarPosition.BOTTOM,
  val heightDp: Int = 110,
  val cornerRadiusDp: Int = 16,
  val opacity: Float = 0.85f,
  val isGradient: Boolean = true,
  val solidColor: Long = 0xEE0B281F,
  val gradientEndColor: Long = 0xDD061712,
  val hasSidebarAccent: Boolean = true,
  val sidebarColor: Long = 0xFFD4AF37,
  val showQariBadge: Boolean = true,
  val showSurahBadge: Boolean = true,
  val channelTitle: String = "@QuranStudio"
)

data class ColorThemePreset(
  val id: String,
  val name: String,
  val urduName: String,
  val isLiquid: Boolean = false,
  val primaryText: Long = 0xFFFFFFFF,
  val highlightGlow: Long = 0xFFD4AF37,
  val barBackground: Long = 0xDD0D261E,
  val backgroundBase: Long = 0xFF07140F,
  val liquidColors: List<Long> = emptyList()
)

data class FontPreset(
  val id: String,
  val name: String,
  val urduName: String,
  val category: String, // "Arabic" or "Urdu/English"
  val sampleText: String = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
  val sampleTranslation: String = "شروع اللہ کے نام سے جو بڑا مہربان نہایت رحم والا ہے",
  val fontStyleDesc: String
)

data class BackgroundPreset(
  val id: String,
  val title: String,
  val urduTitle: String,
  val category: String, // "Holy Sanctuaries", "Nature", "Rain", "Night", "Cinematic", "Liquid Gradient"
  val colors: List<Long>,
  val isAnimated: Boolean = false,
  val motionDescription: String = ""
)

data class VideoFilter(
  val id: String,
  val name: String,
  val urduName: String,
  val tintColor: Long = 0x00000000,
  val brightnessMultiplier: Float = 1.0f,
  val contrastMultiplier: Float = 1.0f
)

data class StickerItem(
  val id: String,
  val symbol: String,
  val text: String,
  val category: String,
  var posX: Float = 0.5f,
  var posY: Float = 0.2f,
  var scale: Float = 1.0f,
  var animation: String = "Pulse" // "None", "Fade", "Bounce", "Pulse", "Float"
)

enum class AspectRatioMode(val label: String, val ratio: Float, val iconName: String) {
  REELS_9_16("9:16 (Reels/TikTok/Shorts)", 9f / 16f, "crop_portrait"),
  SQUARE_1_1("1:1 (Instagram Post)", 1f, "crop_square"),
  YOUTUBE_16_9("16:9 (YouTube Video)", 16f / 9f, "crop_16_9")
}

enum class TextAnimationType(val label: String, val urduLabel: String) {
  FADE_IN("Fade In / Out", "مدھم ہو کر آنا"),
  SLIDE_UP("Slide Up", "نیچے سے اوپر آنا"),
  POP_IN("Pop In / Zoom", "اچانک بڑا ہونا"),
  TYPEWRITER("Typewriter", "حرف بہ حرف ٹائپنگ"),
  GLOW_PULSE("Glow Pulse", "نورانی چمک دھڑکنا"),
  WAVE("Wave", "لہرانا"),
  BOUNCE("Bounce", "اچھل کر آنا"),
  NEON_GLOW("Neon Glow", "روشن نین گلو"),
  LIQUID_TEXT("Liquid Flow", "پانی کی طرح بہاؤ")
}

enum class CaptionAnimationType(val label: String, val urduLabel: String) {
  WORD_BY_WORD("Word-by-Word", "لفظ بہ لفظ ظہور"),
  LINE_BY_LINE("Line-by-Line", "سطر بہ سطر"),
  KARAOKE_HIGHLIGHT("Karaoke Glow", "کاراؤکے انداز ہائی لائٹ"),
  SMOOTH_SCROLL("Smooth Scroll", "ہموار اسکرول")
}
