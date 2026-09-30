package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
  @PrimaryKey
  val id: String,
  val title: String,
  val surahId: Int,
  val ayahStart: Int,
  val ayahEnd: Int,
  val aspectRatio: String,
  val themeId: String,
  val fontId: String,
  val backgroundId: String,
  val captionBarEnabled: Boolean,
  val captionBarPosition: String,
  val qariVoiceId: String,
  val textAnimation: String,
  val captionAnimation: String,
  val wordsHighlightEnabled: Boolean,
  val lettersGlowEnabled: Boolean,
  val videoFilterId: String,
  val translationLanguage: String, // "Urdu", "English", "Both", "ArabicOnly"
  val recitationVolume: Float,
  val backgroundVolume: Float,
  val voiceSpeed: Float,
  val voicePitch: Float,
  val voiceMixRatio: Float,
  val noiseReduction: Boolean,
  val lastModified: Long
)
