package com.example.app6.navigation

sealed class AppScreens ( val route: String ){

    object FirstScreen : AppScreens(route = "RegistrationScreen")
    object SecondScreen : AppScreens(route = "loginScreen")
}