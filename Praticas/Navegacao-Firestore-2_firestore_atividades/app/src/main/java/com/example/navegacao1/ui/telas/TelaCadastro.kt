package com.example.navegacao1.ui.telas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TelaCadastro(
    modifier: Modifier = Modifier,
    onSignupSuccess: () -> Unit // Callback para navegação de volta para a tela de login após cadastro
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = senha,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { senha = it },
            label = { Text(text = "Senha") }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (nome.isNotEmpty() && senha.isNotEmpty()) {
                    val usuario = Usuario(nome = nome, senha = senha)
                    scope.launch(Dispatchers.IO) {
                        usuarioDAO.adicionar(usuario) { sucesso ->
                            if (sucesso) {
                                onSignupSuccess()
                            } else {
                                mensagemErro = "Erro ao cadastrar usuário!"
                            }
                        }
                    }
                } else {
                    mensagemErro = "Preencha todos os campos!"
                }
            }
        ) {
            Text("Cadastrar")
        }

        mensagemErro?.let {
            LaunchedEffect(it) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                mensagemErro = null
            }
        }
    }
}