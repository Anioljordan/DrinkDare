package com.application.drinkdare.ui.screen.carta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "cartas", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // Mostrar ubicaciones
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(cartas) { carta ->
                Text(text = carta.nombre, style = MaterialTheme.typography.bodyMedium)
                // Otros detalles de la ubicación, si es necesario
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Inputs para nueva ubicación
        var nombre by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var imagen by remember { mutableStateOf("") }

        // Formulario para crear una nueva ubicación
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("Imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val carta = Carta(
                    nombre = nombre,
                    descripcion = descripcion,
                    imagen = imagen
                )
                viewModel.crearCarta(carta)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar Carta")
        }
    }
}