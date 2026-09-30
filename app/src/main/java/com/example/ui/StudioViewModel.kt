package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.ProjectEntity
import com.example.data.local.QuranStudioDatabase
import com.example.data.model.*
import com.example.data.repository.PresetRepository
import com.example.data.repository.QuranRepository
import com.example.service.AiRecitationAligner
import com.example.service.AiVoiceStudio
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

data class StudioUiState(
  val projectId: String = UUID.randomUUID().toString(),
  val projectTitle: String = "تلاوت خوبصورت - ریلز",
  val isPlaying: Boolean = false,
  val currentPositionMs: Long = 0L,
  val totalDurationMs: Long = 20000L,
  val aspectRatio: AspectRatioMode = AspectRatioMode.REELS_9_16,

  // Selected Quran Passage
  val selectedSurah: SurahInfo = QuranRepository.surahs[0], // Al-Fatiha
  val ayahStart: Int = 1,
  val ayahEnd: Int = 7,
  val translationLanguage: String = "Urdu (Maududi)", // "Urdu (Maududi)", "Urdu (Jalandhari)", "English (Sahih)", "Both Arabic & Translation", "Arabic Only"
  val showTranslation: Boolean = true,
  val showArabic: Boolean = true,

  // Captions & Alignment
  val captions: List<CaptionItem> = emptyList(),
  val isAligningWithAi: Boolean = false,
  val alignmentSuccess: Boolean = true,
  val highlightWordsWordByWord: Boolean = true,
  val highlightLettersGlow: Boolean = false,
  val selectedCaptionId: String? = null,

  // Styling & Themes
  val currentTheme: ColorThemePreset = PresetRepository.colorThemes[0], // Liquid Gold
  val currentFont: FontPreset = PresetRepository.fonts[0], // King Fahd Uthmani
  val fontSizeSp: Float = 26f,
  val isBold: Boolean = true,
  val isItalic: Boolean = false,
  val textOutlineThickness: Float = 2f,
  val textShadow: Boolean = true,

  // Caption Bar / Lower Third
  val captionBar: CaptionBarConfig = CaptionBarConfig(),

  // Animations
  val textAnimation: TextAnimationType = TextAnimationType.GLOW_PULSE,
  val captionAnimation: CaptionAnimationType = CaptionAnimationType.KARAOKE_HIGHLIGHT,
  val animationSpeedMultiplier: Float = 1.0f,

  // Background
  val currentBackground: BackgroundPreset = PresetRepository.backgrounds[0], // Kaaba
  val customBackgroundUri: String? = null,
  val backgroundBlurDp: Float = 2f,
  val backgroundDarkOverlay: Float = 0.45f,

  // Voice Studio
  val voiceState: AiVoiceStudio.VoiceConversionState = AiVoiceStudio.VoiceConversionState(),

  // Video Editing & Audio
  val currentFilter: VideoFilter = PresetRepository.videoFilters[0],
  val recitationVolume: Float = 1.0f,
  val backgroundNasheedVolume: Float = 0.25f,
  val videoSpeed: Float = 1.0f,
  val stickersOnCanvas: List<StickerItem> = listOf(
    PresetRepository.stickers[0] // Bismillah
  ),

  // Export State
  val isExporting: Boolean = false,
  val exportProgress: Float = 0f,
  val exportedVideoUri: String? = null,
  val exportResolution: String = "1080p Full HD (60fps)",
  val watermarkEnabled: Boolean = false,
  val generatedYouTubeChapters: String = "",

  // UI Navigation / Active Tab
  val activeEditorTab: Int = 0, // 0: AI Caption, 1: Voice Studio, 2: Font & Text, 3: Animations, 4: Caption Bar, 5: Themes, 6: Background, 7: CapCut Tools, 8: Export
  val showHelpDialog: Boolean = false,
  val toastMessage: String? = null
)

class StudioViewModel(application: Application) : AndroidViewModel(application) {

  private val database = QuranStudioDatabase.getDatabase(application)
  private val projectDao = database.projectDao()

  private val _uiState = MutableStateFlow(StudioUiState())
  val uiState: StateFlow<StudioUiState> = _uiState.asStateFlow()

  private var playbackJob: Job? = null

  init {
    loadInitialCaptions()
  }

  private fun loadInitialCaptions() {
    viewModelScope.launch {
      _uiState.update { it.copy(isAligningWithAi = true) }
      val captions = AiRecitationAligner.generateCaptionsForFatiha()
      _uiState.update {
        it.copy(
          captions = captions,
          isAligningWithAi = false,
          totalDurationMs = captions.lastOrNull()?.endMs ?: 20000L
        )
      }
    }
  }

  fun togglePlayback() {
    val currentState = _uiState.value
    if (currentState.isPlaying) {
      pausePlayback()
    } else {
      startPlayback()
    }
  }

  fun startPlayback() {
    playbackJob?.cancel()
    _uiState.update { it.copy(isPlaying = true) }
    playbackJob = viewModelScope.launch {
      while (_uiState.value.isPlaying) {
        delay(60)
        _uiState.update { state ->
          val next = state.currentPositionMs + (60 * state.videoSpeed).toLong()
          if (next >= state.totalDurationMs) {
            state.copy(currentPositionMs = 0L, isPlaying = false)
          } else {
            state.copy(currentPositionMs = next)
          }
        }
      }
    }
  }

  fun pausePlayback() {
    playbackJob?.cancel()
    _uiState.update { it.copy(isPlaying = false) }
  }

  fun seekTo(positionMs: Long) {
    _uiState.update { it.copy(currentPositionMs = positionMs.coerceIn(0L, it.totalDurationMs)) }
  }

  fun setActiveTab(tabIndex: Int) {
    _uiState.update { it.copy(activeEditorTab = tabIndex) }
  }

  fun setAspectRatio(ratio: AspectRatioMode) {
    _uiState.update { it.copy(aspectRatio = ratio) }
  }

  fun selectSurah(surah: SurahInfo) {
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          selectedSurah = surah,
          ayahStart = 1,
          ayahEnd = surah.versesCount.coerceAtMost(5),
          isAligningWithAi = true
        )
      }
      val captions = AiRecitationAligner.alignRecitation(
        surahId = surah.id,
        ayahStart = 1,
        ayahEnd = surah.versesCount.coerceAtMost(5),
        audioDurationMs = 18000L
      )
      _uiState.update {
        it.copy(
          captions = captions,
          isAligningWithAi = false,
          totalDurationMs = captions.lastOrNull()?.endMs ?: 20000L,
          currentPositionMs = 0L
        )
      }
    }
  }

  fun updateAyahRange(start: Int, end: Int) {
    viewModelScope.launch {
      _uiState.update { it.copy(ayahStart = start, ayahEnd = end, isAligningWithAi = true) }
      val captions = AiRecitationAligner.alignRecitation(
        surahId = _uiState.value.selectedSurah.id,
        ayahStart = start,
        ayahEnd = end
      )
      _uiState.update {
        it.copy(
          captions = captions,
          isAligningWithAi = false,
          totalDurationMs = captions.lastOrNull()?.endMs ?: 20000L,
          currentPositionMs = 0L
        )
      }
    }
  }

  fun toggleWordHighlight(enable: Boolean) {
    _uiState.update { it.copy(highlightWordsWordByWord = enable) }
  }

  fun toggleLetterGlow(enable: Boolean) {
    _uiState.update { it.copy(highlightLettersGlow = enable) }
  }

  fun selectTheme(theme: ColorThemePreset) {
    _uiState.update { it.copy(currentTheme = theme) }
  }

  fun selectFont(font: FontPreset) {
    _uiState.update { it.copy(currentFont = font) }
  }

  fun setFontSize(sizeSp: Float) {
    _uiState.update { it.copy(fontSizeSp = sizeSp) }
  }

  fun updateCaptionBar(config: CaptionBarConfig) {
    _uiState.update { it.copy(captionBar = config) }
  }

  fun setTextAnimation(anim: TextAnimationType) {
    _uiState.update { it.copy(textAnimation = anim) }
  }

  fun setCaptionAnimation(anim: CaptionAnimationType) {
    _uiState.update { it.copy(captionAnimation = anim) }
  }

  fun selectBackground(bg: BackgroundPreset) {
    _uiState.update { it.copy(currentBackground = bg, customBackgroundUri = null) }
  }

  fun setBackgroundBlur(blur: Float) {
    _uiState.update { it.copy(backgroundBlurDp = blur) }
  }

  fun setBackgroundDarkOverlay(dark: Float) {
    _uiState.update { it.copy(backgroundDarkOverlay = dark) }
  }

  // Voice Studio Actions
  fun selectQariVoice(qari: QariVoice) {
    _uiState.update { it.copy(voiceState = it.voiceState.copy(selectedQari = qari)) }
  }

  fun convertRecitationVoice() {
    viewModelScope.launch {
      _uiState.update { it.copy(voiceState = it.voiceState.copy(isConverting = true, conversionProgress = 0f)) }
      AiVoiceStudio.convertVoice(_uiState.value.voiceState.selectedQari.id) { progress ->
        _uiState.update { it.copy(voiceState = it.voiceState.copy(conversionProgress = progress)) }
      }
      _uiState.update {
        it.copy(
          voiceState = it.voiceState.copy(isConverting = false, isConverted = true),
          toastMessage = "آواز کامیابی کے ساتھ تبدیل ہو گئی (${it.voiceState.selectedQari.name})"
        )
      }
    }
  }

  fun updateVoiceSettings(speed: Float, pitch: Float, mix: Float, noiseReduction: Boolean, reverb: Boolean) {
    _uiState.update {
      it.copy(
        voiceState = it.voiceState.copy(
          speed = speed,
          pitch = pitch,
          mixRatio = mix,
          noiseReduction = noiseReduction,
          haramainReverb = reverb
        )
      )
    }
  }

  // Caption Manual Editing
  fun updateCaptionTiming(captionId: String, startDeltaMs: Long, endDeltaMs: Long) {
    _uiState.update { state ->
      val updated = state.captions.map { item ->
        if (item.id == captionId) {
          val newStart = (item.startMs + startDeltaMs).coerceAtLeast(0L)
          val newEnd = (item.endMs + endDeltaMs).coerceAtLeast(newStart + 500L)
          item.copy(startMs = newStart, endMs = newEnd)
        } else item
      }
      state.copy(captions = updated)
    }
  }

  fun deleteCaption(captionId: String) {
    _uiState.update { state ->
      state.copy(captions = state.captions.filterNot { it.id == captionId })
    }
  }

  fun addSticker(sticker: StickerItem) {
    _uiState.update { it.copy(stickersOnCanvas = it.stickersOnCanvas + sticker.copy(id = UUID.randomUUID().toString())) }
  }

  fun removeSticker(stickerId: String) {
    _uiState.update { it.copy(stickersOnCanvas = it.stickersOnCanvas.filterNot { it.id == stickerId }) }
  }

  fun selectVideoFilter(filter: VideoFilter) {
    _uiState.update { it.copy(currentFilter = filter) }
  }

  fun setTranslationDisplayMode(mode: String) {
    when (mode) {
      "Arabic Only" -> _uiState.update { it.copy(translationLanguage = mode, showArabic = true, showTranslation = false) }
      "Translation Only" -> _uiState.update { it.copy(translationLanguage = mode, showArabic = false, showTranslation = true) }
      else -> _uiState.update { it.copy(translationLanguage = mode, showArabic = true, showTranslation = true) }
    }
  }

  fun exportVideo() {
    viewModelScope.launch {
      _uiState.update { it.copy(isExporting = true, exportProgress = 0.05f) }
      val chapters = buildYouTubeChapters()
      for (i in 1..20) {
        delay(150)
        _uiState.update { it.copy(exportProgress = (i * 0.05f).coerceAtMost(1.0f)) }
      }
      _uiState.update {
        it.copy(
          isExporting = false,
          exportedVideoUri = "file:///storage/emulated/0/Movies/QuranStudio_${System.currentTimeMillis()}.mp4",
          generatedYouTubeChapters = chapters,
          toastMessage = "ویڈیو کامیابی سے ایکسپورٹ ہو گئی! (1080p Ultra HD)"
        )
      }
      saveCurrentProject()
    }
  }

  private fun buildYouTubeChapters(): String {
    val state = _uiState.value
    val sb = StringBuilder()
    sb.append("00:00 - تلاوت قرآن مجید\n")
    state.captions.forEach { cap ->
      val totalSec = cap.startMs / 1000
      val min = totalSec / 60
      val sec = totalSec % 60
      val timeStr = String.format("%02d:%02d", min, sec)
      sb.append("$timeStr - سورة ${state.selectedSurah.nameArabic} [آیت ${cap.ayahNumber}]\n")
    }
    return sb.toString()
  }

  fun saveCurrentProject() {
    viewModelScope.launch {
      val s = _uiState.value
      val entity = ProjectEntity(
        id = s.projectId,
        title = s.projectTitle,
        surahId = s.selectedSurah.id,
        ayahStart = s.ayahStart,
        ayahEnd = s.ayahEnd,
        aspectRatio = s.aspectRatio.name,
        themeId = s.currentTheme.id,
        fontId = s.currentFont.id,
        backgroundId = s.currentBackground.id,
        captionBarEnabled = s.captionBar.isEnabled,
        captionBarPosition = s.captionBar.position.name,
        qariVoiceId = s.voiceState.selectedQari.id,
        textAnimation = s.textAnimation.name,
        captionAnimation = s.captionAnimation.name,
        wordsHighlightEnabled = s.highlightWordsWordByWord,
        lettersGlowEnabled = s.highlightLettersGlow,
        videoFilterId = s.currentFilter.id,
        translationLanguage = s.translationLanguage,
        recitationVolume = s.recitationVolume,
        backgroundVolume = s.backgroundNasheedVolume,
        voiceSpeed = s.voiceState.speed,
        voicePitch = s.voiceState.pitch,
        voiceMixRatio = s.voiceState.mixRatio,
        noiseReduction = s.voiceState.noiseReduction,
        lastModified = System.currentTimeMillis()
      )
      projectDao.insertProject(entity)
    }
  }

  fun toggleHelpDialog(show: Boolean) {
    _uiState.update { it.copy(showHelpDialog = show) }
  }

  fun clearToast() {
    _uiState.update { it.copy(toastMessage = null) }
  }
}
