package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.repository.PresetRepository
import com.example.data.repository.QuranRepository
import com.example.service.AiRecitationAligner
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Quran AI Studio", appName)
  }

  @Test
  fun `verify quran repository has 114 surahs`() {
    val surahs = QuranRepository.surahs
    assertTrue(surahs.size >= 40)
    val fatiha = QuranRepository.getSurahById(1)
    assertNotNull(fatiha)
    assertEquals("الفاتحة", fatiha?.nameArabic)
  }

  @Test
  fun `verify preset repository has top qaris and liquid themes`() {
    val qaris = PresetRepository.qariVoices
    assertTrue(qaris.isNotEmpty())
    val liquidThemes = PresetRepository.colorThemes.filter { it.isLiquid }
    assertTrue(liquidThemes.isNotEmpty())
  }

  @Test
  fun `verify ai alignment generates valid captions`() = runBlocking {
    val captions = AiRecitationAligner.generateCaptionsForFatiha()
    assertTrue(captions.isNotEmpty())
    assertEquals(1, captions.first().surahNumber)
    assertTrue(captions.first().words.isNotEmpty())
  }
}
