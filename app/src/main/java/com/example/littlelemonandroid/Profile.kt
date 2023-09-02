package com.example.littlelemonandroid

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemonandroid.ui.theme.DarkGreen
import com.example.littlelemonandroid.ui.theme.Yellow

@Composable
fun ProfileScreen(navController: NavHostController){
    val context = LocalContext.current
    val sharePreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    var firstName by remember { mutableStateOf(sharePreferences.getString("firstName", "")?:"") }
    var lastName by remember { mutableStateOf(sharePreferences.getString("lastName", "")?:"") }
    var emailName by remember { mutableStateOf(sharePreferences.getString("emailName", "")?:"") }

    Column(
        Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(200.dp)
                    .clickable {
                        navController.navigate(Home.route)
                    }
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Personal Information",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }

        InputField(label = "First Name", input = firstName, onValueChange = { newValue ->
            firstName = newValue
            sharePreferences.edit().putString("firstName", newValue).apply()
        })
        InputField(label = "Last Name", input = lastName, onValueChange = { newValue ->
            lastName = newValue
            sharePreferences.edit().putString("lastName", newValue).apply()
        })
        InputField(label = "Email Name", input = emailName, onValueChange = { newValue ->
            emailName = newValue
            sharePreferences.edit().putString("emailName", newValue).apply()
        })

        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                onClick = {
                    sharePreferences.edit().clear().apply()
                    navController.navigate(Onboarding.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp, horizontal = 15.dp)

            ){
                Text(text = "Log out")
            }
        }
    }
}