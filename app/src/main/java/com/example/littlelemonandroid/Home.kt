package com.example.littlelemonandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // First image centered
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(200.dp)
            )

            // Second image at the right end
            Image(
                painter = painterResource(id = R.drawable.profile), // Replace with your actual resource ID
                contentDescription = "another logo",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .height(70.dp)
                    .width(70.dp)
                    .padding(horizontal = 10.dp)
                    .clickable { navController.navigate(Profile.route) }
            )
        }
    }
}
