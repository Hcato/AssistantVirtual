package com.hcato.assistantvirtual

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hcato.assistantvirtual.core.di.AppContainer
import com.hcato.assistantvirtual.features.assistent.di.AdviceModule
import com.hcato.assistantvirtual.features.assistent.presentation.screens.AdviceScreen
import com.hcato.assistantvirtual.ui.theme.AssistantVirtualTheme

class MainActivity : ComponentActivity() {
    init {
        Log.d("BASE_URL", BuildConfig.BASE_URL)
    }

    lateinit var appContainer: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(this)
        val adviceModule = AdviceModule(appContainer)
        enableEdgeToEdge()
        setContent {
            AssistantVirtualTheme {
                MaterialTheme {
                    AdviceScreen(adviceModule.provideAdviceViewModelFactory())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssistantVirtualTheme {
        Greeting("Android")
    }
}