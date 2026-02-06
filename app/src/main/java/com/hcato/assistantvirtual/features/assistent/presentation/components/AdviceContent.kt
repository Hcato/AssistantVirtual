package com.hcato.assistantvirtual.features.assistent.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hcato.assistantvirtual.features.assistent.presentation.screens.AdviceUiState
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModel
import dev.romainguy.kotlin.math.Float3
import io.github.sceneview.Scene
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader

@Composable
fun AdviceContent(
    modifier: Modifier = Modifier,
    viewModel: AdviceViewModel,
    uiState: AdviceUiState
) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val modelInstance = remember {
        modelLoader.createModelInstance(
            assetFileLocation = "models/orphie.glb"
        )
    }
    val assistantNode = remember(modelInstance) {
        ModelNode(
            modelInstance = modelInstance,
            autoAnimate = false,
            scaleToUnits = 0.5f,
            centerOrigin = Float3(0f, 0.5f, 0f)
        ).apply {

        }
    }
    assistantNode.apply {
        scale = Float3(1f, 1f, 1f)
        position = Float3(0f, 0f, 0f)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔹 TEXTO
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.advice != null -> {
                    Text(
                        text = uiState.advice.advice,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                }
                uiState.error != null -> {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }

        // 🔹 MODELO 3D
        Box(
            modifier = Modifier.weight(2f).fillMaxSize()
        ) {
            Scene(
                modifier = Modifier.fillMaxSize(),
                engine = engine,
                isOpaque = false,
                childNodes = listOf(assistantNode),
                onViewCreated = {
                    cameraNode.apply {
                        position = Float3(0f, 3.2f, 2.4f)
                        lookAt(Float3(0f, 3.2f, 0f))
                    }
                    mainLightNode?.intensity = 60_000f
                }
            )
        }

        // 🔹 BOTÓN
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { viewModel.loadAdvice() }) {
                Text("Dame otro consejo")
            }
        }
    }
}