package com.example.data.repository

import com.example.data.model.BackgroundPreset
import com.example.data.model.ColorThemePreset
import com.example.data.model.FontPreset
import com.example.data.model.QariVoice
import com.example.data.model.StickerItem
import com.example.data.model.VideoFilter

object PresetRepository {

  val qariVoices: List<QariVoice> = listOf(
    QariVoice(
      id = "abdul_basit",
      name = "Qari Abdul Basit Abdul Samad",
      arabicName = "عبد الباسط عبد الصمد",
      title = "Voice of Heaven (صوت مكة)",
      style = "Murattal & Mujawwad Classic",
      icon = "🎙️",
      description = "شہرت یافتہ مصری قاری جن کا لحن اور سانس کا کمال بے مثل ہے۔ بلند و پروقار لہجہ۔",
      sampleText = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
    ),
    QariVoice(
      id = "sudais",
      name = "Sheikh Abdul Rahman Al-Sudais",
      arabicName = "عبد الرحمن السديس",
      title = "Imam of Ka'bah (إمام الحرم المكي)",
      style = "Soulful Makkah Haramain Tone",
      icon = "🕋",
      description = "امام کعبہ کا روح پرور، پرجوش اور خشوع سے لبریز لہجہ جو دلوں کو پگھلا دیتا ہے۔",
      sampleText = "ٱلْحَمْدُ لِلَّهِ رَبِّ ٱلْعَٰلَمِينَ"
    ),
    QariVoice(
      id = "masood",
      name = "Qari Masood",
      arabicName = "قاری مسعود",
      title = "Gentle Soft Melodic",
      style = "Calm Heart-touching Melody",
      icon = "✨",
      description = "نرم، دھیما اور انتہائی پرسکون انداز جو ذہنی سکون اور مراقبے کے لیے بہترین ہے۔",
      sampleText = "ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
    ),
    QariVoice(
      id = "alafasy",
      name = "Mishary Rashid Alafasy",
      arabicName = "مشاري راشد العفاسي",
      title = "Kuwaiti Master",
      style = "Crystal Melodic Resonant",
      icon = "🕌",
      description = "صاف، شیریں اور مترنم تلاوت۔ سوشل میڈیا ریلز اور مختصر ویڈیوز کے لیے سب سے پسندیدہ۔",
      sampleText = "مَٰلِكِ يَوْمِ ٱلدِّينِ"
    ),
    QariVoice(
      id = "al_muaiqly",
      name = "Sheikh Maher Al-Muaiqly",
      arabicName = "ماهر المعيقلي",
      title = "Imam of Masjid Al-Haram",
      style = "Emotional & Soothing",
      icon = "🌙",
      description = "انتہائی دل سوز، دھیمی اور پرتاثیر آواز جس میں خشیت الٰہی کی کیفیت نمایاں ہے۔",
      sampleText = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ"
    ),
    QariVoice(
      id = "al_shuraim",
      name = "Sheikh Saud Al-Shuraim",
      arabicName = "سعود الشريم",
      title = "Ex-Imam of Ka'bah",
      style = "Fast Rhythm Tajweed Flow",
      icon = "📜",
      description = "تیز روان اور قواعد تجوید کے ساتھ پر اعتماد قرأت۔",
      sampleText = "ٱهْدِنَا ٱلصِّرَٰطَ ٱلْمُسْتَقِيمَ"
    )
  )

  val colorThemes: List<ColorThemePreset> = listOf(
    ColorThemePreset(
      id = "liquid_gold",
      name = "Liquid Gold (لیکوڈ گولڈن)",
      urduName = "سنہری بہتا پانی",
      isLiquid = true,
      primaryText = 0xFFFFFFFF,
      highlightGlow = 0xFFFFD700,
      barBackground = 0xEA161C14,
      backgroundBase = 0xFF0A0E0B,
      liquidColors = listOf(0xFFFFDF00, 0xFFFFA500, 0xFFFF6347, 0xFFFFD700)
    ),
    ColorThemePreset(
      id = "liquid_water",
      name = "Liquid Ocean (لیکوڈ واٹر)",
      urduName = "سمندری نیلگوں لہریں",
      isLiquid = true,
      primaryText = 0xFFF0FDF4,
      highlightGlow = 0xFF38BDF8,
      barBackground = 0xEA071926,
      backgroundBase = 0xFF020C14,
      liquidColors = listOf(0xFF06B6D4, 0xFF3B82F6, 0xFF10B981, 0xFF0284C7)
    ),
    ColorThemePreset(
      id = "liquid_fire",
      name = "Liquid Sunset (غروب شفق)",
      urduName = "شام کی گرم رنگت",
      isLiquid = true,
      primaryText = 0xFFFFF1F2,
      highlightGlow = 0xFFFB7185,
      barBackground = 0xEA2A0C14,
      backgroundBase = 0xFF170408,
      liquidColors = listOf(0xFFF43F5E, 0xFFFB923C, 0xFFFBBF24, 0xFFE11D48)
    ),
    ColorThemePreset(
      id = "emerald_islamic",
      name = "Emerald Green (اسلامی زمرد)",
      urduName = "اسلامی سبز",
      isLiquid = false,
      primaryText = 0xFFF0FDF4,
      highlightGlow = 0xFF34D399,
      barBackground = 0xEE09291D,
      backgroundBase = 0xFF051710
    ),
    ColorThemePreset(
      id = "kaaba_gold",
      name = "Kiswah Gold (کعبہ کالی چادر و سونا)",
      urduName = "کسوہ گولڈ",
      isLiquid = false,
      primaryText = 0xFFFFFBEB,
      highlightGlow = 0xFFF59E0B,
      barBackground = 0xFA121212,
      backgroundBase = 0xFF0A0A0A
    ),
    ColorThemePreset(
      id = "navy_royal",
      name = "Royal Navy (شاہی نیلا)",
      urduName = "شاہی بحری نیلا",
      isLiquid = false,
      primaryText = 0xFFF8FAFC,
      highlightGlow = 0xFF60A5FA,
      barBackground = 0xEE0F172A,
      backgroundBase = 0xFF070B14
    ),
    ColorThemePreset(
      id = "purple_dream",
      name = "Purple Dream (جامنی خواب)",
      urduName = "روحانی جامنی",
      isLiquid = false,
      primaryText = 0xFFFAF5FF,
      highlightGlow = 0xFFC084FC,
      barBackground = 0xEE1E112A,
      backgroundBase = 0xFF0F0717
    ),
    ColorThemePreset(
      id = "rose_gold",
      name = "Rose Gold (پنکھ گلابی)",
      urduName = "روز گولڈ",
      isLiquid = false,
      primaryText = 0xFFFFF1F2,
      highlightGlow = 0xFFFDA4AF,
      barBackground = 0xEE2A121A,
      backgroundBase = 0xFF14080D
    ),
    ColorThemePreset(
      id = "minaret_red",
      name = "Minaret Ruby (مینارٹ سرخ)",
      urduName = "سرخ عقیق",
      isLiquid = false,
      primaryText = 0xFFFEF2F2,
      highlightGlow = 0xFFF87171,
      barBackground = 0xEE2E0E0E,
      backgroundBase = 0xFF170606
    ),
    ColorThemePreset(
      id = "pure_pearl",
      name = "Pearl White (سفید موتی)",
      urduName = "چمکدار سفید",
      isLiquid = false,
      primaryText = 0xFF0F172A,
      highlightGlow = 0xFF0D9488,
      barBackground = 0xFAF8FAFC,
      backgroundBase = 0xFFE2E8F0
    ),
    ColorThemePreset(
      id = "pure_black",
      name = "Deep OLED Dark (گہرا سیاہ)",
      urduName = "مکمل بلیک",
      isLiquid = false,
      primaryText = 0xFFFFFFFF,
      highlightGlow = 0xFFE2E8F0,
      barBackground = 0xF5000000,
      backgroundBase = 0xFF000000
    )
  )

  val fonts: List<FontPreset> = listOf(
    FontPreset(
      id = "uthmani_hafs",
      name = "King Fahd Uthmani",
      urduName = "عثمانی رسم الخط (مجمع الملک فہد)",
      category = "Arabic",
      fontStyleDesc = "The certified Quranic script with complete Medina Mushaf diacritics."
    ),
    FontPreset(
      id = "nastaliq_noori",
      name = "Jameel Noori Nastaliq",
      urduName = "جمیل نوری نستعلیق",
      category = "Urdu/English",
      fontStyleDesc = "Classic elegant Urdu script for translations and subtitles."
    ),
    FontPreset(
      id = "thuluth_gold",
      name = "Royal Thuluth (ثلث)",
      urduName = "خط ثلث شاہی",
      category = "Arabic",
      fontStyleDesc = "Monumental Arabic calligraphy style used in mosques and titles."
    ),
    FontPreset(
      id = "kufi_modern",
      name = "Geometric Kufic (کوفی)",
      urduName = "خط کوفی جدید",
      category = "Arabic",
      fontStyleDesc = "Bold geometric early Islamic script perfect for modern video reels."
    ),
    FontPreset(
      id = "diwani_classic",
      name = "Diwani Calligraphy (دیوانی)",
      urduName = "خط دیوانی",
      category = "Arabic",
      fontStyleDesc = "Flowing Ottoman cursive script with high aesthetic elegance."
    ),
    FontPreset(
      id = "naskh_clarity",
      name = "High-Legibility Naskh (نسخ)",
      urduName = "خط نسخ واضح",
      category = "Arabic",
      fontStyleDesc = "Crystal-clear reading font optimal for fast video scanning."
    ),
    FontPreset(
      id = "cinzel_serif",
      name = "Cinzel Classical Serif",
      urduName = "سنیماٹک انگریزی خط",
      category = "Urdu/English",
      fontStyleDesc = "Prestigious serif font for English translations and channel branding."
    ),
    FontPreset(
      id = "inter_modern",
      name = "Inter Sans Clean",
      urduName = "صاف ماڈرن سانز",
      category = "Urdu/English",
      fontStyleDesc = "Modern minimalist typeface for crisp subtitle viewing."
    )
  )

  val backgrounds: List<BackgroundPreset> = listOf(
    BackgroundPreset(
      id = "makkah_kaaba",
      title = "Holy Ka'bah Tawaf",
      urduTitle = "مطاف و کعبہ شریف",
      category = "Holy Sanctuaries",
      colors = listOf(0xFF0F172A, 0xFF1E293B, 0xFF020617),
      isAnimated = true,
      motionDescription = "Golden light ambient glow & moving pilgrims"
    ),
    BackgroundPreset(
      id = "madinah_green_dome",
      title = "Madinah Nabawi Dome",
      urduTitle = "گنبد خضراء و مسجد نبوی",
      category = "Holy Sanctuaries",
      colors = listOf(0xFF064E3B, 0xFF022C22, 0xFF0B1F19),
      isAnimated = true,
      motionDescription = "Peaceful evening mist and illuminated minarets"
    ),
    BackgroundPreset(
      id = "serene_rain",
      title = "Gentle Rain on Mosque Window",
      urduTitle = "بارش کے قطرے اور پرسکون فضا",
      category = "Rain",
      colors = listOf(0xFF1E3A8A, 0xFF0F172A, 0xFF020617),
      isAnimated = true,
      motionDescription = "Slow motion falling raindrops with soft ripple"
    ),
    BackgroundPreset(
      id = "starry_desert",
      title = "Starlit Desert Sky",
      urduTitle = "صحرا کی پرسکون رات اور ستارے",
      category = "Night",
      colors = listOf(0xFF1E1B4B, 0xFF0F0D24, 0xFF05030A),
      isAnimated = true,
      motionDescription = "Milky Way dust and gentle drifting twinkling stars"
    ),
    BackgroundPreset(
      id = "mountain_clouds",
      title = "Mountain Clouds & Fog",
      urduTitle = "پہاڑوں کی دھند اور بادل",
      category = "Nature",
      colors = listOf(0xFF134E4A, 0xFF042F2E, 0xFF021716),
      isAnimated = true,
      motionDescription = "Time-lapse rolling mountain clouds"
    ),
    BackgroundPreset(
      id = "ocean_calm",
      title = "Calm Ocean Waves",
      urduTitle = "سمندر کی پرسکون لہریں",
      category = "Nature",
      colors = listOf(0xFF0C4A6E, 0xFF082F49, 0xFF021019),
      isAnimated = true,
      motionDescription = "Peaceful rhythmic ocean surge"
    ),
    BackgroundPreset(
      id = "cinematic_particles",
      title = "Golden Divine Particles",
      urduTitle = "سنہری ذرات کا پرنور بہاؤ",
      category = "Cinematic",
      colors = listOf(0xFF291B00, 0xFF140D00, 0xFF000000),
      isAnimated = true,
      motionDescription = "Soft floating golden bokeh particles"
    ),
    BackgroundPreset(
      id = "emerald_liquid_bg",
      title = "Islamic Liquid Aurora",
      urduTitle = "اسلامی لیکوڈ لہریں",
      category = "Liquid Gradient",
      colors = listOf(0xFF065F46, 0xFF047857, 0xFF022C22),
      isAnimated = true,
      motionDescription = "Smooth liquid gradient continuous drift"
    )
  )

  val stickers: List<StickerItem> = listOf(
    StickerItem("s1", "﷽", "بِسْمِ اللَّهِ", "Calligraphy", 0.5f, 0.15f, 1.2f, "Pulse"),
    StickerItem("s2", "🕌", "مسجد", "Icons", 0.5f, 0.82f, 1.0f, "Float"),
    StickerItem("s3", "🕋", "کعبہ", "Icons", 0.85f, 0.12f, 0.9f, "None"),
    StickerItem("s4", "🌙", "ہلال", "Icons", 0.15f, 0.12f, 0.9f, "Float"),
    StickerItem("s5", "⭐", "نجم", "Icons", 0.85f, 0.22f, 0.8f, "Pulse"),
    StickerItem("s6", "سُبْحَانَ ٱللَّهِ", "سبحان اللہ", "Calligraphy", 0.5f, 0.78f, 1.0f, "Fade"),
    StickerItem("s7", "ٱلْحَمْدُ لِلَّهِ", "الحمد للہ", "Calligraphy", 0.5f, 0.78f, 1.0f, "Fade"),
    StickerItem("s8", "اللَّهُ أَكْبَرُ", "اللہ اکبر", "Calligraphy", 0.5f, 0.78f, 1.0f, "Bounce"),
    StickerItem("s9", "مَا شَاءَ ٱللَّهُ", "ما شاء اللہ", "Calligraphy", 0.5f, 0.78f, 1.0f, "Pulse"),
    StickerItem("s10", "🎙️", "قاری کا مائک", "Studio", 0.15f, 0.88f, 0.9f, "None"),
    StickerItem("s11", "🎧", "ہیڈ فون", "Studio", 0.85f, 0.88f, 0.9f, "None"),
    StickerItem("s12", "🌿", "زیتون شاخ", "Decorative", 0.15f, 0.2f, 0.8f, "Float")
  )

  val videoFilters: List<VideoFilter> = listOf(
    VideoFilter("normal", "Original (بغیر فلٹر)", "اصل"),
    VideoFilter("warm_gold", "Golden Hour (سنہری شفق)", "سنہری", 0x33FFA500, 1.05f, 1.1f),
    VideoFilter("emerald_serene", "Emerald Serene (پرسکون زمرد)", "زمرد", 0x2210B981, 1.0f, 1.15f),
    VideoFilter("cool_night", "Night Mood (رات کا سکون)", "ٹھنڈا نیلا", 0x251E40AF, 0.92f, 1.1f),
    VideoFilter("vintage_film", "Vintage Makkah (قدیم یادیں)", "وینٹیج", 0x22D97706, 0.98f, 0.95f),
    VideoFilter("cinematic_contrast", "Cinematic Contrast (سنیماٹک)", "سنیما", 0x00000000, 1.0f, 1.3f)
  )
}
