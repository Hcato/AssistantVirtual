package com.hcato.assistantvirtual.features.register.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hcato.assistantvirtual.core.navigation.Advice
import com.hcato.assistantvirtual.core.navigation.FeatureNavGraph
import com.hcato.assistantvirtual.core.navigation.Login
import com.hcato.assistantvirtual.core.navigation.Register
import com.hcato.assistantvirtual.features.assistent.di.AdviceModule
import com.hcato.assistantvirtual.features.assistent.presentation.screens.AdviceScreen
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModel
import com.hcato.assistantvirtual.features.register.di.RegisterModule
import com.hcato.assistantvirtual.features.register.presentation.screens.RegisterScreen
import com.hcato.assistantvirtual.features.register.presentation.viewmodels.RegisterViewModel
import com.hcato.assistantvirtual.features.updatestatus.di.UpdateStatusModule
import com.hcato.assistantvirtual.features.updatestatus.presentation.UpdateStatusViewModel.UpdateStatusViewModel

class RegisterNavGraph (
    private val registerNavGraph: RegisterModule
): FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Register> {

            val viewModel: RegisterViewModel = viewModel(
                factory = registerNavGraph.provideRegisterViewModelFactory()
            )

            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = {
                    navController.navigate(Login) {
                        popUpTo(Register) { inclusive = true }
                    }
                }
                //onCharacterClick = { id -> navController.navigate(CharacterDetail(id)) }
            )
        }
    }
}