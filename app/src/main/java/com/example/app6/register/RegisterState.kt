package com.example.app6.register

import androidx.annotation.StringRes

data class RegisterState(
    val successRegister : Boolean = false,
    val displayProgressBar : Boolean = false,
    @StringRes val errorMessages : Int? = null
)
