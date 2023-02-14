package com.example.app6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app6.presentation.login.LoginScreen
import com.example.app6.presentation.registration.RegistrationScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.FirstScreen.route
    ){
        composable(route = AppScreens.FirstScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route){
            RegistrationScreen(navController)
        }
    }
}