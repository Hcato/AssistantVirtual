package com.hcato.assistantvirtual.features.updatestatus.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hcato.assistantvirtual.core.navigation.FeatureNavGraph
import com.hcato.assistantvirtual.core.navigation.Login
import com.hcato.assistantvirtual.core.navigation.Register
import com.hcato.assistantvirtual.core.navigation.UpdateStatus
import com.hcato.assistantvirtual.features.register.di.RegisterModule
import com.hcato.assistantvirtual.features.register.presentation.screens.RegisterScreen
import com.hcato.assistantvirtual.features.register.presentation.viewmodels.RegisterViewModel
import com.hcato.assistantvirtual.features.updatestatus.di.UpdateStatusModule
import com.hcato.assistantvirtual.features.updatestatus.presentation.UpdateStatusViewModel.UpdateStatusViewModel

class UpdateStatusNavGraph (
    private val updateStatusNavGraph: UpdateStatusModule
): FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<UpdateStatus> {

            val viewModel: UpdateStatusViewModel = viewModel(
                factory = updateStatusNavGraph.provideUpdateStatusViewModelFactory()
            )

        }
    }
}