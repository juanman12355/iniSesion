package com.example.app6.navigation

import androidx.navigation.NamedNavArgument

sealed class AppScreens (
    val route: String,
    val arguments : List<NamedNavArgument>
)
{
    object Login : AppScreens(route = "loginScreen/{nombre}", emptyList()){
        fun createRouteLogin(nombre : String) :String{
            return "loginScreen/$nombre"
        }
    }
    object Registration : AppScreens(route = "RegistrationScreen", emptyList())
    object Home : AppScreens(route = "HomeScreen/{user}", emptyList()){
        fun createRoute(user : String) : String{

            return "HomeScreen/$user"
        }
    }
}