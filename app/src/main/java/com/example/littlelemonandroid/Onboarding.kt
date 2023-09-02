package com.example.littlelemonandroid

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemonandroid.ui.theme.DarkGreen
import com.example.littlelemonandroid.ui.theme.Yellow

@Composable
fun OnboardingScreen(navController: NavHostController){
    val context = LocalContext.current
    val sharePreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailName by remember { mutableStateOf("") }

    Column (
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
            )

        }
        Row(
            modifier = Modifier
                .background(color = DarkGreen)
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Let\'s get to know you",
                color = Color.White,
                fontSize = 20.sp
            )

        }
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
        })
        InputField(label = "Last Name", input = lastName, onValueChange = { newValue ->
            lastName = newValue
        })
        InputField(label = "Email Name", input = emailName, onValueChange = { newValue ->
            emailName = newValue
        })

        Row(
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                onClick = {
                    if(firstName.isBlank() || lastName.isBlank() || emailName.isBlank()){
                        val message = "Registration unsuccessful. Please enter all data."
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }else{
                        sharePreferences.edit()
                            .putString("firstName", firstName)
                            .putString("lastName", lastName)
                            .putString("emailName", emailName)
                            .apply()
                        val message = "Registration successful!"
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        navController.navigate(Home.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp, horizontal = 15.dp)

                ){
                Text(text = "Register")
            }
        }
    }
}



@Composable
fun InputField(label: String, input: String, onValueChange: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.padding(2.dp)
        )
        BasicTextField(
            value = input,
            onValueChange = { newValue -> onValueChange(newValue) },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp)) // Round the corners
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Apply the rounded border
                .padding(10.dp)
                .fillMaxWidth()
        )
    }
}
