package com.example.littlelemonandroid

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(navController: NavHostController){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    val isUserInfoAvailable = sharedPreferences.getString("firstName", null) != null &&
            sharedPreferences.getString("lastName", null) != null &&
            sharedPreferences.getString("emailName", null) != null
    val startRoute = if (isUserInfoAvailable) Home.route else Onboarding.route
    /*
    val editor = sharedPreferences.edit()
    editor.clear().apply()
    */

    NavHost(navController = navController, startDestination = startRoute){
        composable(Home.route){
            HomeScreen(navController)
        }
        composable(Onboarding.route){
            OnboardingScreen(navController)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
    }
}