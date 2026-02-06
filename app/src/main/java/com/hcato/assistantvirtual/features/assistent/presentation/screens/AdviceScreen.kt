package com.hcato.assistantvirtual.features.assistent.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hcato.assistantvirtual.R
import com.hcato.assistantvirtual.features.assistent.presentation.components.AdviceContent
import com.hcato.assistantvirtual.features.assistent.presentation.components.AdviceDrawer
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModel
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModelFactory
import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.Scene
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdviceScreen(
    viewModel: AdviceViewModel,
    onBackClick: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    // 🔥 ESTE ES EL PUNTO CLAVE
    val userDeleted by viewModel.userDeleted.collectAsStateWithLifecycle()

    LaunchedEffect(userDeleted) {
        if (userDeleted) {
            onBackClick()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier
            .fillMaxSize()
            .zIndex(106f),
        drawerContent = {
            AdviceDrawer(
                isPremium = uiState.isPremium,
                onPremiumChange = { viewModel.updatePremium(it) },
                onDeleteUser = { viewModel.deleteUser() }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Respuesta") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_action_key_24),
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            AdviceContent(
                modifier = Modifier.padding(padding),
                viewModel = viewModel,
                uiState = uiState
            )
        }
    }
}