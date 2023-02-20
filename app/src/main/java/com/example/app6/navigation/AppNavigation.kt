package com.example.app6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app6.login.LoginViewModel
import com.example.app6.presentation.login.LoginScreen
import com.example.app6.screens.RegistrationScreen
import com.example.app6.register.RegisterViewModel
import com.example.app6.screens.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Login.route
    )
    {
        val viewModel = LoginViewModel()
        composable(route = AppScreens.Login.route){
//            val viewModel = LoginViewModel()
            if (viewModel.state.value.sucessLogin){
                LaunchedEffect(key1 = Unit){
                    navController.navigate(AppScreens.Home.route){
                        popUpTo(AppScreens.Login.route){
                            inclusive = true
                        }
                    }
                }
            } else {
                LoginScreen(
                    navController,
                    state = viewModel.state.value,
                    onLogin = viewModel :: Login,
                    onNavigateToRegister = { navController.navigate(AppScreens.Registration.route)},
                    onDismissDialog = viewModel :: hideErrorDialog
                )
            }
            LoginScreen(navController,
                state = viewModel.state.value,
                onLogin = viewModel :: Login,
                onNavigateToRegister = { navController.navigate(AppScreens.Registration.route)},
                onDismissDialog = viewModel :: hideErrorDialog)
        }
        val viewModel2 = RegisterViewModel()
        composable(route = AppScreens.Registration.route)
        {
//            val viewModel = RegisterViewModel()
            RegistrationScreen(navController,
                state = viewModel2.state.value,
                onRegister = viewModel2 :: register,
                onBack = {navController.popBackStack()},
                onDismissDialog = viewModel2 :: hideErrorDialog
            )
        }
        composable(
            route = AppScreens.Home.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            }
            )
        )
        {
            HomeScreen(navController, user = it.arguments?.getString("user")?: "" )
        }
    }
}