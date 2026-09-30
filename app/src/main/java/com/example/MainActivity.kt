package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.AspectRatioMode
import com.example.ui.StudioViewModel
import com.example.ui.components.EditorToolbar
import com.example.ui.components.HelpGuideDialog
import com.example.ui.components.VideoCanvasPreview
import com.example.ui.tabs.*
import com.example.ui.theme.QuranStudioTheme

class MainActivity : ComponentActivity() {

  private val studioViewModel: StudioViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      QuranStudioTheme {
        QuranStudioApp(viewModel = studioViewModel)
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranStudioApp(
  viewModel: StudioViewModel
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val context = LocalContext.current

  LaunchedEffect(uiState.toastMessage) {
    uiState.toastMessage?.let { msg ->
      Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
      viewModel.clearToast()
    }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("quran_studio_root_scaffold"),
    topBar = {
      TopAppBar(
        title = {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(
              text = "Quran AI Studio",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFD4AF37)
            )
            Surface(
              shape = androidx.compose.foundation.shape.RoundedCornerShape(6.dp),
              color = Color(0xFF0F3628)
            ) {
              Text(
                text = "قرآن اسٹوڈیو",
                color = Color(0xFF6EE7B7),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        },
        actions = {
          // Help / Guide Button
          IconButton(
            onClick = { viewModel.toggleHelpDialog(true) },
            modifier = Modifier.testTag("help_guide_action_button")
          ) {
            Icon(
              imageVector = Icons.Default.HelpOutline,
              contentDescription = "Studio Help Guide",
              tint = Color(0xFFD4AF37)
            )
          }

          // Save Project Button
          IconButton(
            onClick = {
              viewModel.saveCurrentProject()
              Toast.makeText(context, "پروجیکٹ محفوظ ہو گیا!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.testTag("save_project_action_button")
          ) {
            Icon(
              imageVector = Icons.Default.BookmarkBorder,
              contentDescription = "Save Project",
              tint = Color.White
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color(0xFF061410),
          titleContentColor = Color.White
        )
      )
    },
    containerColor = Color(0xFF07120E)
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      // 1. Live Interactive Video Canvas Preview
      VideoCanvasPreview(
        uiState = uiState,
        onTogglePlay = { viewModel.togglePlayback() },
        onSeek = { viewModel.seekTo(it) },
        onToggleAspect = {
          val nextMode = when (uiState.aspectRatio) {
            AspectRatioMode.REELS_9_16 -> AspectRatioMode.SQUARE_1_1
            AspectRatioMode.SQUARE_1_1 -> AspectRatioMode.YOUTUBE_16_9
            AspectRatioMode.YOUTUBE_16_9 -> AspectRatioMode.REELS_9_16
          }
          viewModel.setAspectRatio(nextMode)
        }
      )

      // 2. Editor Toolbar (Tabs for 9 Studio Features)
      EditorToolbar(
        selectedTab = uiState.activeEditorTab,
        onTabSelected = { viewModel.setActiveTab(it) }
      )

      // 3. Tab Content View
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f)
          .background(Color(0xFF07120E))
      ) {
        when (uiState.activeEditorTab) {
          0 -> AiCaptionTab(uiState = uiState, viewModel = viewModel)
          1 -> VoiceStudioTab(uiState = uiState, viewModel = viewModel)
          2 -> FontTextTab(uiState = uiState, viewModel = viewModel)
          3 -> AnimationTab(uiState = uiState, viewModel = viewModel)
          4 -> CaptionBarTab(uiState = uiState, viewModel = viewModel)
          5 -> ColorThemeTab(uiState = uiState, viewModel = viewModel)
          6 -> BackgroundTab(uiState = uiState, viewModel = viewModel)
          7 -> EditingToolsTab(uiState = uiState, viewModel = viewModel)
          8 -> ExportTab(uiState = uiState, viewModel = viewModel)
          else -> AiCaptionTab(uiState = uiState, viewModel = viewModel)
        }
      }
    }

    // Interactive Help & Feature Guide Dialog
    if (uiState.showHelpDialog) {
      HelpGuideDialog(
        onDismiss = { viewModel.toggleHelpDialog(false) },
        onNavigateToTab = { tab ->
          viewModel.setActiveTab(tab)
          viewModel.toggleHelpDialog(false)
        }
      )
    }
  }
}
