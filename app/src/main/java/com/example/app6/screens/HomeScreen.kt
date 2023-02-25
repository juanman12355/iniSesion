package com.example.app6.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.app6.navigation.AppNavigation
import com.example.app6.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavHostController , user : String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Pagina Principal $user",
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.size(6.dp))
        Button(onClick = {navController.navigate(route = AppScreens.Login.route)}){
            Text(text = "Volver a Login")
        }
    }
}