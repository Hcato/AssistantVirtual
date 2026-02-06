package com.hcato.assistantvirtual.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hcato.assistantvirtual.core.navigation.Advice
import com.hcato.assistantvirtual.core.navigation.FeatureNavGraph
import com.hcato.assistantvirtual.core.navigation.Home
import com.hcato.assistantvirtual.core.navigation.Login
import com.hcato.assistantvirtual.core.navigation.Register
import com.hcato.assistantvirtual.features.home.presentation.HomeScreen

class HomeNavGraph: FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Home> {

            HomeScreen (
                onClickLogin = { navController.navigate(Login) },
                onClickRegister = { navController.navigate(Register) }
            )
        }
    }
}