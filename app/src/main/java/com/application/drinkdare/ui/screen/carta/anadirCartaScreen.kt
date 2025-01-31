package com.application.drinkdare.ui.screen.carta

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.drinkdare.domain.model.Carta
import com.application.drinkdare.presentation.CartaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartaScreen(viewModel: CartaViewModel = koinViewModel()) {
    val cartas by viewModel.cartas.collectAsState()
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Cartas",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(cartas) { carta ->
                CartaItem(carta)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InputField(value = nombre, onValueChange = { nombre = it }, label = "Nombre")
                InputField(value = descripcion, onValueChange = { descripcion = it }, label = "Descripción")
                InputField(value = imagen, onValueChange = { imagen = it }, label = "Imagen URL")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val carta = Carta(nombre = nombre, descripcion = descripcion, imagen = imagen)
                        viewModel.crearCarta(carta)
                        nombre = ""
                        descripcion = ""
                        imagen = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Guardar Carta")
                }
            }
        }
    }
}

@Composable
fun CartaItem(carta: Carta) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = carta.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = carta.descripcion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    )
}
