package com.example.app6.navigation

sealed class AppScreens (
    val route: String,
)
{
    object Login : AppScreens(route = "loginScreen/{nombre}"){
        fun createRouteLogin(nombre : String) :String{
            return "loginScreen/$nombre"
        }
    }
    object Registration : AppScreens(route = "RegistrationScreen")
    object Home : AppScreens(route = "HomeScreen/{user}"){
        fun createRoute(user : String) : String{
            return "HomeScreen/$user"
        }
    }
}