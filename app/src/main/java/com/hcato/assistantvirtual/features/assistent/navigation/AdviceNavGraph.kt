package com.hcato.assistantvirtual.features.assistent.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hcato.assistantvirtual.core.navigation.Advice
import com.hcato.assistantvirtual.core.navigation.FeatureNavGraph
import com.hcato.assistantvirtual.features.assistent.di.AdviceModule
import com.hcato.assistantvirtual.features.assistent.presentation.screens.AdviceScreen
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModel

class AdviceNavGraph (
    private val adviceNavGraph: AdviceModule
): FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Advice> {

            val viewModel: AdviceViewModel = viewModel(
                factory = adviceNavGraph.provideAdviceViewModelFactory()
            )

            AdviceScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
                //onCharacterClick = { id -> navController.navigate(CharacterDetail(id)) }
            )
        }
    }
}