package com.example.data.repository

import com.example.data.model.AyahData
import com.example.data.model.SurahInfo
import com.example.data.model.WordData

object QuranRepository {

  val surahs: List<SurahInfo> = listOf(
    SurahInfo(1, "الفاتحة", "Al-Fatihah", "سورۃ الفاتحہ", 7, "Makkah", "The Opening"),
    SurahInfo(2, "البقرة", "Al-Baqarah", "سورۃ البقرہ", 286, "Madinah", "The Cow"),
    SurahInfo(3, "آل عمران", "Ali 'Imran", "سورۃ آل عمران", 200, "Madinah", "Family of Imran"),
    SurahInfo(4, "النساء", "An-Nisa", "سورۃ النساء", 176, "Madinah", "The Women"),
    SurahInfo(5, "المائدة", "Al-Ma'idah", "سورۃ المائدہ", 120, "Madinah", "The Table Spread"),
    SurahInfo(6, "الأنعام", "Al-An'am", "سورۃ الانعام", 165, "Makkah", "The Cattle"),
    SurahInfo(7, "الأعراف", "Al-A'raf", "سورۃ الاعراف", 206, "Makkah", "The Heights"),
    SurahInfo(8, "الأنفال", "Al-Anfal", "سورۃ الانفال", 75, "Madinah", "The Spoils of War"),
    SurahInfo(9, "التوبة", "At-Tawbah", "سورۃ التوبہ", 129, "Madinah", "The Repentance"),
    SurahInfo(10, "يونس", "Yunus", "سورۃ یونس", 109, "Makkah", "Jonah"),
    SurahInfo(11, "هود", "Hud", "سورۃ ہود", 123, "Makkah", "Hud"),
    SurahInfo(12, "يوسف", "Yusuf", "سورۃ یوسف", 111, "Makkah", "Joseph"),
    SurahInfo(13, "الرعد", "Ar-Ra'd", "سورۃ الرعد", 43, "Madinah", "The Thunder"),
    SurahInfo(14, "إبراهيم", "Ibrahim", "سورۃ ابراہیم", 52, "Makkah", "Abraham"),
    SurahInfo(15, "الحجر", "Al-Hijr", "سورۃ الحجر", 99, "Makkah", "The Rocky Tract"),
    SurahInfo(16, "النحل", "An-Nahl", "سورۃ النحل", 128, "Makkah", "The Bee"),
    SurahInfo(17, "الإسراء", "Al-Isra", "سورۃ الاسراء", 111, "Makkah", "The Night Journey"),
    SurahInfo(18, "الكهف", "Al-Kahf", "سورۃ الکہف", 110, "Makkah", "The Cave"),
    SurahInfo(19, "مريم", "Maryam", "سورۃ مریم", 98, "Makkah", "Mary"),
    SurahInfo(20, "طه", "Taha", "سورۃ طہ", 135, "Makkah", "Ta-Ha"),
    SurahInfo(21, "الأنبياء", "Al-Anbiya", "سورۃ الانبیاء", 112, "Makkah", "The Prophets"),
    SurahInfo(22, "الحج", "Al-Hajj", "سورۃ الحج", 78, "Madinah", "The Pilgrimage"),
    SurahInfo(23, "المؤمنون", "Al-Mu'minun", "سورۃ المؤمنون", 118, "Makkah", "The Believers"),
    SurahInfo(24, "النور", "An-Nur", "سورۃ النور", 64, "Madinah", "The Light"),
    SurahInfo(25, "الفرقان", "Al-Furqan", "سورۃ الفرقان", 77, "Makkah", "The Criterion"),
    SurahInfo(26, "الشعراء", "Ash-Shu'ara", "سورۃ الشعراء", 227, "Makkah", "The Poets"),
    SurahInfo(27, "النمل", "An-Naml", "سورۃ النمل", 93, "Makkah", "The Ant"),
    SurahInfo(28, "القصص", "Al-Qasas", "سورۃ القصص", 88, "Makkah", "The Stories"),
    SurahInfo(29, "العنكبوت", "Al-'Ankabut", "سورۃ العنکبوت", 69, "Makkah", "The Spider"),
    SurahInfo(30, "الروم", "Ar-Rum", "سورۃ الروم", 60, "Makkah", "The Romans"),
    SurahInfo(31, "لقمان", "Luqman", "سورۃ لقمان", 34, "Makkah", "Luqman"),
    SurahInfo(32, "السجدة", "As-Sajdah", "سورۃ السجدہ", 30, "Makkah", "The Prostration"),
    SurahInfo(33, "الأحزاب", "Al-Ahzab", "سورۃ الاحزاب", 73, "Madinah", "The Combined Forces"),
    SurahInfo(34, "سبأ", "Saba", "سورۃ سبا", 54, "Makkah", "Sheba"),
    SurahInfo(35, "فاطر", "Fatir", "سورۃ فاطر", 45, "Makkah", "Originator"),
    SurahInfo(36, "يس", "Ya-Sin", "سورۃ یٰسین", 83, "Makkah", "Ya-Sin"),
    SurahInfo(37, "الصافات", "As-Saffat", "سورۃ الصافات", 182, "Makkah", "Those who set the Ranks"),
    SurahInfo(38, "ص", "Sad", "سورۃ ص", 88, "Makkah", "The Letter 'Saad'"),
    SurahInfo(39, "الزمر", "Az-Zumar", "سورۃ الزمر", 75, "Makkah", "The Troops"),
    SurahInfo(40, "غافر", "Ghafir", "سورۃ غافر", 85, "Makkah", "The Forgiver"),
    SurahInfo(55, "الرحمن", "Ar-Rahman", "سورۃ الرحمن", 78, "Madinah", "The Beneficent"),
    SurahInfo(56, "الواقعة", "Al-Waqi'ah", "سورۃ الواقعہ", 96, "Makkah", "The Inevitable"),
    SurahInfo(67, "الملك", "Al-Mulk", "سورۃ الملک", 30, "Makkah", "The Sovereignty"),
    SurahInfo(112, "الإخلاص", "Al-Ikhlas", "سورۃ الاخلاص", 4, "Makkah", "The Sincerity"),
    SurahInfo(113, "الفلق", "Al-Falaq", "سورۃ الفلق", 5, "Makkah", "The Daybreak"),
    SurahInfo(114, "الناس", "An-Nas", "سورۃ الناس", 6, "Makkah", "Mankind")
  )

  // Sample detailed Ayah data with word-by-word splits and multi-language translations
  private val ayahDatabase: List<AyahData> = listOf(
    // Surah Al-Fatiha (1:1 - 1:7)
    AyahData(
      surahId = 1,
      ayahNumber = 1,
      arabicUthmani = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ",
      urduMaududi = "اللہ کے نام سے جو رحمان اور رحیم ہے",
      urduJalandhari = "شروع اللہ کا نام لے کر جو بڑا مہربان نہایت رحم والا ہے",
      englishSahih = "In the name of Allah, the Entirely Merciful, the Especially Merciful.",
      words = listOf(
        WordData(0, "بِسْمِ", 0, 800),
        WordData(1, "ٱللَّهِ", 800, 1900),
        WordData(2, "ٱلرَّحْمَٰنِ", 1900, 3200),
        WordData(3, "ٱلرَّحِيمِ", 3200, 4800)
      )
    ),
    AyahData(
      surahId = 1,
      ayahNumber = 2,
      arabicUthmani = "ٱلْحَمْدُ لِلَّهِ رَبِّ ٱلْعَٰلَمِينَ",
      urduMaududi = "تعریف اللہ ہی کے لیے ہے جو تمام جہانوں کا پروردگار ہے",
      urduJalandhari = "سب طرح کی تعریف خدا ہی کو (سزاوار) ہے جو تمام مخلوقات کا پروردگار ہے",
      englishSahih = "[All] praise is [due] to Allah, Lord of the worlds -",
      words = listOf(
        WordData(0, "ٱلْحَمْدُ", 0, 950),
        WordData(1, "لِلَّهِ", 950, 1850),
        WordData(2, "رَبِّ", 1850, 2600),
        WordData(3, "ٱلْعَٰلَمِينَ", 2600, 4600)
      )
    ),
    AyahData(
      surahId = 1,
      ayahNumber = 3,
      arabicUthmani = "ٱلرَّحْمَٰنِ ٱلرَّحِيمِ",
      urduMaududi = "بڑا مہربان اور نہایت رحم کرنے والا ہے",
      urduJalandhari = "بڑا مہربان نہایت رحم والا",
      englishSahih = "The Entirely Merciful, the Especially Merciful,",
      words = listOf(
        WordData(0, "ٱلرَّحْمَٰنِ", 0, 1400),
        WordData(1, "ٱلرَّحِيمِ", 1400, 3200)
      )
    ),
    AyahData(
      surahId = 1,
      ayahNumber = 4,
      arabicUthmani = "مَٰلِكِ يَوْمِ ٱلدِّينِ",
      urduMaududi = "روز جزا کا مالک ہے",
      urduJalandhari = "انصاف کے دن کا حاکم",
      englishSahih = "Sovereign of the Day of Recompense.",
      words = listOf(
        WordData(0, "مَٰلِكِ", 0, 800),
        WordData(1, "يَوْمِ", 800, 1600),
        WordData(2, "ٱلدِّينِ", 1600, 3100)
      )
    ),
    AyahData(
      surahId = 1,
      ayahNumber = 5,
      arabicUthmani = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
      urduMaududi = "ہم تیری ہی عبادت کرتے ہیں اور تجھی سے مدد مانگتے ہیں",
      urduJalandhari = "(اے پروردگار) ہم تیری ہی عبادت کرتے ہیں اور تجھ ہی سے مدد مانگتے ہیں",
      englishSahih = "It is You we worship and You we ask for help.",
      words = listOf(
        WordData(0, "إِيَّاكَ", 0, 1100),
        WordData(1, "نَعْبُدُ", 1100, 2200),
        WordData(2, "وَإِيَّاكَ", 2200, 3300),
        WordData(3, "نَسْتَعِينُ", 3300, 5200)
      )
    ),
    AyahData(
      surahId = 1,
      ayahNumber = 6,
      arabicUthmani = "ٱهْدِنَا ٱلصِّرَٰطَ ٱلْمُسْتَقِيمَ",
      urduMaududi = "ہمیں سیدھا راستہ دکھا",
      urduJalandhari = "ہم کو سیدھے راستے پر چلا",
      englishSahih = "Guide us to the straight path -",
      words = listOf(
        WordData(0, "ٱهْدِنَا", 0, 900),
        WordData(1, "ٱلصِّرَٰطَ", 900, 2100),
        WordData(2, "ٱلْمُسْتَقِيمَ", 2100, 4100)
      )
    ),
    AyahData(
      surahId = 1,
      ayahNumber = 7,
      arabicUthmani = "صِرَٰطَ ٱلَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ ٱلْمَغْضُوبِ عَلَيْهِمْ وَلَا ٱلضَّآلِّينَ",
      urduMaududi = "ان لوگوں کا راستہ جن پر تو نے انعام فرمایا، جو معتوب نہیں ہوئے، جو بھٹکے ہوئے نہیں ہیں",
      urduJalandhari = "ان لوگوں کے راستے جن پر تو اپنا فضل و کرم کرتا رہا، نہ ان کے جن پر غصہ ہوتا رہا اور نہ گمراہوں کے",
      englishSahih = "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.",
      words = listOf(
        WordData(0, "صِرَٰطَ", 0, 800),
        WordData(1, "ٱلَّذِينَ", 800, 1600),
        WordData(2, "أَنْعَمْتَ", 1600, 2400),
        WordData(3, "عَلَيْهِمْ", 2400, 3200),
        WordData(4, "غَيْرِ", 3200, 4000),
        WordData(5, "ٱلْمَغْضُوبِ", 4000, 5200),
        WordData(6, "عَلَيْهِمْ", 5200, 6000),
        WordData(7, "وَلَا", 6000, 6800),
        WordData(8, "ٱلضَّآلِّينَ", 6800, 9400)
      )
    ),

    // Ayat al-Kursi (2:255)
    AyahData(
      surahId = 2,
      ayahNumber = 255,
      arabicUthmani = "ٱللَّهُ لَآ إِلَٰهَ إِلَّا هُوَ ٱلْحَىُّ ٱلْقَيُّومُ ۚ لَا تَأْخُذُهُۥ سِنَةٌ وَلَا نَوْمٌ ۚ لَّهُۥ مَا فِى ٱلسَّمَٰوَٰتِ وَمَا فِى ٱلْأَرْضِ",
      urduMaududi = "اللہ وہ زندہ جاوید ہستی ہے جو تمام کائنات کو سنبھالے ہوئے ہے، اس کے سوا کوئی معبود نہیں، نہ اسے اونگھ آتی ہے نہ نیند",
      urduJalandhari = "خدا وہ معبود برحق ہے کہ اس کے سوا کوئی معبود نہیں زندہ ہے سب کا تھامنے والا، نہ اس کو اونگھ آتی ہے نہ نیند",
      englishSahih = "Allah - there is no deity except Him, the Ever-Living, the Sustainer of [all] existence. Neither drowsiness overtakes Him nor sleep.",
      words = listOf(
        WordData(0, "ٱللَّهُ", 0, 1000),
        WordData(1, "لَآ إِلَٰهَ", 1000, 2200),
        WordData(2, "إِلَّا", 2200, 2900),
        WordData(3, "هُوَ", 2900, 3500),
        WordData(4, "ٱلْحَىُّ", 3500, 4600),
        WordData(5, "ٱلْقَيُّومُ", 4600, 6000)
      )
    ),

    // Surah Ya-Sin (36:1 - 36:4)
    AyahData(
      surahId = 36,
      ayahNumber = 1,
      arabicUthmani = "يسٓ",
      urduMaududi = "یسٰ",
      urduJalandhari = "یسٰ",
      englishSahih = "Ya, Seen.",
      words = listOf(WordData(0, "يسٓ", 0, 3200))
    ),
    AyahData(
      surahId = 36,
      ayahNumber = 2,
      arabicUthmani = "وَٱلْقُرْءَانِ ٱلْحَكِيمِ",
      urduMaududi = "قسم ہے قرآن حکیم کی",
      urduJalandhari = "حکمت والے قرآن کی قسم",
      englishSahih = "By the wise Qur'an.",
      words = listOf(
        WordData(0, "وَٱلْقُرْءَانِ", 0, 1600),
        WordData(1, "ٱلْحَكِيمِ", 1600, 3600)
      )
    ),
    AyahData(
      surahId = 36,
      ayahNumber = 3,
      arabicUthmani = "إِنَّكَ لَمِنَ ٱلْمُرْسَلِينَ",
      urduMaududi = "کہ تم یقیناً رسولوں میں سے ہو",
      urduJalandhari = "بے شک آپ پیغمبروں میں سے ہیں",
      englishSahih = "Indeed you, [O Muhammad], are from among the messengers,",
      words = listOf(
        WordData(0, "إِنَّكَ", 0, 900),
        WordData(1, "لَمِنَ", 900, 1700),
        WordData(2, "ٱلْمُرْسَلِينَ", 1700, 3800)
      )
    ),
    AyahData(
      surahId = 36,
      ayahNumber = 4,
      arabicUthmani = "عَلَىٰ صِرَٰطٍۢ مُّسْتَقِيمٍۢ",
      urduMaududi = "سیدھے راستے پر ہو",
      urduJalandhari = "سیدھے راستے پر",
      englishSahih = "On a straight path.",
      words = listOf(
        WordData(0, "عَلَىٰ", 0, 800),
        WordData(1, "صِرَٰطٍۢ", 800, 1900),
        WordData(2, "مُّسْتَقِيمٍۢ", 1900, 3900)
      )
    ),

    // Surah Ar-Rahman (55:1 - 55:4)
    AyahData(
      surahId = 55,
      ayahNumber = 1,
      arabicUthmani = "ٱلرَّحْمَٰنُ",
      urduMaududi = "رحمان نے",
      urduJalandhari = "رحمٰن نے",
      englishSahih = "The Most Merciful",
      words = listOf(WordData(0, "ٱلرَّحْمَٰنُ", 0, 2400))
    ),
    AyahData(
      surahId = 55,
      ayahNumber = 2,
      arabicUthmani = "عَلَّمَ ٱلْقُرْءَانَ",
      urduMaududi = "اس قرآن کی تعلیم دی ہے",
      urduJalandhari = "قرآن سکھایا",
      englishSahih = "Taught the Qur'an,",
      words = listOf(
        WordData(0, "عَلَّمَ", 0, 1000),
        WordData(1, "ٱلْقُرْءَانَ", 1000, 2800)
      )
    ),
    AyahData(
      surahId = 55,
      ayahNumber = 3,
      arabicUthmani = "خَلَقَ ٱلْإِنسَٰنَ",
      urduMaududi = "اسی نے انسان کو پیدا کیا",
      urduJalandhari = "اسی نے انسان کو پیدا کیا",
      englishSahih = "Created man,",
      words = listOf(
        WordData(0, "خَلَقَ", 0, 900),
        WordData(1, "ٱلْإِنسَٰنَ", 900, 2700)
      )
    ),
    AyahData(
      surahId = 55,
      ayahNumber = 4,
      arabicUthmani = "عَلَّمَهُ ٱلْبَيَانَ",
      urduMaududi = "اور اسے بولنا سکھایا",
      urduJalandhari = "اس کو بولنا سکھایا",
      englishSahih = "[And] taught him eloquence.",
      words = listOf(
        WordData(0, "عَلَّمَهُ", 0, 1100),
        WordData(1, "ٱلْبَيَانَ", 1100, 3100)
      )
    ),

    // Surah Al-Ikhlas (112:1 - 112:4)
    AyahData(
      surahId = 112,
      ayahNumber = 1,
      arabicUthmani = "قُلْ هُوَ ٱللَّهُ أَحَدٌ",
      urduMaududi = "کہو، وہ اللہ ہے، یکتا",
      urduJalandhari = "کہو کہ وہ اللہ ایک ہے",
      englishSahih = "Say, \"He is Allah, [who is] One,",
      words = listOf(
        WordData(0, "قُلْ", 0, 600),
        WordData(1, "هُوَ", 600, 1200),
        WordData(2, "ٱللَّهُ", 1200, 2100),
        WordData(3, "أَحَدٌ", 2100, 3500)
      )
    ),
    AyahData(
      surahId = 112,
      ayahNumber = 2,
      arabicUthmani = "ٱللَّهُ ٱلصَّمَدُ",
      urduMaududi = "اللہ سب کا بے نیاز اور سب اس کے محتاج ہیں",
      urduJalandhari = "معبود برحق جو بے نیاز ہے",
      englishSahih = "Allah, the Eternal Refuge.",
      words = listOf(
        WordData(0, "ٱللَّهُ", 0, 1100),
        WordData(1, "ٱلصَّمَدُ", 1100, 2900)
      )
    ),
    AyahData(
      surahId = 112,
      ayahNumber = 3,
      arabicUthmani = "لَمْ يَلِدْ وَلَمْ يُولَدْ",
      urduMaududi = "نہ اس کی کوئی اولاد ہے اور نہ وہ کسی کی اولاد ہے",
      urduJalandhari = "نہ کسی کا باپ ہے اور نہ کسی کا بیٹا",
      englishSahih = "He neither begets nor is born,",
      words = listOf(
        WordData(0, "لَمْ", 0, 600),
        WordData(1, "يَلِدْ", 600, 1500),
        WordData(2, "وَلَمْ", 1500, 2200),
        WordData(3, "يُولَدْ", 2200, 3600)
      )
    ),
    AyahData(
      surahId = 112,
      ayahNumber = 4,
      arabicUthmani = "وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌۢ",
      urduMaududi = "اور کوئی اس کا ہمسر نہیں ہے",
      urduJalandhari = "اور کوئی اس کا ہمسر نہیں",
      englishSahih = "Nor is there to Him any equivalent.\"",
      words = listOf(
        WordData(0, "وَلَمْ", 0, 700),
        WordData(1, "يَكُن", 700, 1400),
        WordData(2, "لَّهُۥ", 1400, 2100),
        WordData(3, "كُفُوًا", 2100, 3100),
        WordData(4, "أَحَدٌۢ", 3100, 4800)
      )
    )
  )

  fun getAyahsForSurah(surahId: Int): List<AyahData> {
    return ayahDatabase.filter { it.surahId == surahId }
  }

  fun getAyah(surahId: Int, ayahNumber: Int): AyahData? {
    return ayahDatabase.find { it.surahId == surahId && it.ayahNumber == ayahNumber }
  }

  fun searchVerses(query: String): List<AyahData> {
    if (query.isBlank()) return emptyList()
    val clean = query.trim().lowercase()
    return ayahDatabase.filter {
      it.arabicUthmani.contains(clean, ignoreCase = true) ||
          it.urduMaududi.contains(clean, ignoreCase = true) ||
          it.englishSahih.contains(clean, ignoreCase = true)
    }
  }

  fun getSurahById(id: Int): SurahInfo? {
    return surahs.find { it.id == id }
  }
}
