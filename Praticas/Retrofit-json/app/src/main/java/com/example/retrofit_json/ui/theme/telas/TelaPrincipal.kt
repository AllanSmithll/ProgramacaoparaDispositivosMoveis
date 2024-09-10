package com.example.retrofit_json.ui.theme.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit_json.model.dados.RetrofitClient
import com.example.retrofit_json.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    val scope = rememberCoroutineScope()

    var usuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var idParaBuscarOuRemover by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        scope.launch {
            usuarios = getUsuarios()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A237E))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray
            )
        )

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray
            )
        )

        Button(
            onClick = {
                scope.launch {
                    val novoUsuario = Usuario(
                        id = UUID.randomUUID().toString(),
                        nome = nome,
                        senha = senha
                    )
                    inserirUsuario(novoUsuario)
                    usuarios = getUsuarios()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Verde
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Adicionar Usuário", color = Color.White)
        }

        OutlinedTextField(
            value = idParaBuscarOuRemover,
            onValueChange = { idParaBuscarOuRemover = it },
            label = { Text("ID do Usuário", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    scope.launch {
                        if (idParaBuscarOuRemover.isNotEmpty()) {
                            removerUsuario(idParaBuscarOuRemover)
                            usuarios = getUsuarios()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350)),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Remover", color = Color.White)
            }

            Button(
                onClick = {
                    scope.launch {
                        if (idParaBuscarOuRemover.isNotEmpty()) {
                            val usuario = buscarUsuarioPorId(idParaBuscarOuRemover)
                            usuarios = listOf(usuario)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Buscar", color = Color.White)
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 24.dp).fillMaxWidth()
        ) {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFF00)),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "ID: ${usuario.id}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF800080)
                        )
                        Text(
                            text = "Nome: ${usuario.nome}",
                            fontSize = 14.sp,
                            color = Color(0xFF800080)
                        )
                        Text(
                            text = "Senha: ${usuario.senha}",
                            fontSize = 14.sp,
                            color = Color(0xFF800080)
                        )
                    }
                }
            }
        }
    }
}

suspend fun getUsuarios(): List<Usuario> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.listar()
    }
}

suspend fun inserirUsuario(usuario: Usuario) {
    withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.inserir(usuario)
    }
}

suspend fun removerUsuario(id: String) {
    withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.remover(id)
    }
}

suspend fun buscarUsuarioPorId(id: String): Usuario {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.buscarPorId(id)
    }
}