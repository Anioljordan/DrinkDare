package com.application.drinkdare.ui.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.drinkdare.R
import com.application.drinkdare.presentation.LoginViewModel
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painter = painterResource(id = R.drawable.topcoffecircle),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )

        Text(
            text = "TOP COFFEE",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Benvingut", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Inicia sessió amb les teves credencials",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Correu electronic", fontWeight = FontWeight.Bold, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Gray,
                            focusedIndicatorColor = Color.Black,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Icon(painter = painterResource(id = R.drawable.ic_email), contentDescription = null)
                }
                Canvas(modifier = Modifier.fillMaxWidth()) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Contrasenya", fontWeight = FontWeight.Bold, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Gray,
                            focusedIndicatorColor = Color.Black,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                    Icon(painter = painterResource(id = R.drawable.ic_password), contentDescription = null)
                }
                Canvas(modifier = Modifier.fillMaxWidth()) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
                }

                Text(
                    text = "He oblidat la meva contrasenya",
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp)
                )

                Button(
                    onClick = {
                        viewModel.login(email, password) { success ->
                            if (!success) {
                                errorMessage = "Credenciales incorrectas"
                            } else {
                                navController.navigate("Carta")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6F4E37))
                ) {
                    Text(text = "Inicia sesión", color = Color.White)
                }

                TextButton(onClick = { navController.navigate("register") }) {
                    Text(text = "No tens compte? Registra't aquí", fontSize = 15.sp, color = Color.Gray)
                }

                Text(text = "O inicia amb", color = Color.Gray, modifier = Modifier.padding(top = 20.dp))

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Sign In",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(top = 8.dp)
                )
            }
        }
    }
}
