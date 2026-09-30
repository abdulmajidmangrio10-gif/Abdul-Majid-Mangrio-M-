package com.example.service

import com.example.data.model.QariVoice
import com.example.data.repository.PresetRepository
import kotlinx.coroutines.delay

object AiVoiceStudio {

  data class VoiceConversionState(
    val selectedQari: QariVoice = PresetRepository.qariVoices[0],
    val speed: Float = 1.0f,
    val pitch: Float = 0.0f,
    val mixRatio: Float = 0.85f, // 0 = 100% user voice, 1 = 100% Qari voice
    val noiseReduction: Boolean = true,
    val haramainReverb: Boolean = true,
    val removeWatermark: Boolean = true,
    val isConverting: Boolean = false,
    val conversionProgress: Float = 0f,
    val isConverted: Boolean = false,
    val isPreviewPlaying: Boolean = false
  )

  suspend fun convertVoice(
    qariId: String,
    onProgress: (Float) -> Unit
  ) {
    for (i in 1..10) {
      delay(200)
      onProgress(i / 10f)
    }
  }
}
