package com.hcato.assistantvirtual.features.login.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hcato.assistantvirtual.core.navigation.Advice
import com.hcato.assistantvirtual.core.navigation.FeatureNavGraph
import com.hcato.assistantvirtual.core.navigation.Login
import com.hcato.assistantvirtual.features.login.di.LoginModule
import com.hcato.assistantvirtual.features.login.presentation.screens.LoginScreen
import com.hcato.assistantvirtual.features.login.presentation.viewmodels.LoginViewModel

class LoginNavGraph (
    private val loginNavGraph: LoginModule
): FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Login> {

            val viewModel: LoginViewModel = viewModel(
                factory = loginNavGraph.provideLoginViewModelFactory()
            )

            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Advice) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
                //onCharacterClick = { id -> navController.navigate(CharacterDetail(id)) }
            )
        }
    }
}