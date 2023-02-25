package com.example.app6.login

import androidx.annotation.StringRes
import androidx.compose.compiler.plugins.kotlin.ComposeErrorMessages

data class LoginState(
    val email : String = "",
    val pass : String = "",
    val sucessLogin : Boolean = false,
    val displayProgressBar : Boolean = false,
    @StringRes val errorMessages : Int? = null
)
