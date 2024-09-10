package com.example.praticaretrofit_cep.model


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.material3.CardDefaults
import com.example.retrofit_cep.model.Endereco
import com.example.retrofit_cep.model.dados.RetrofitClient

@Composable
fun TelaPrincipal(modifier: Modifier = Modifier) {
    var cep by remember { mutableStateOf("") }
    var addressResponse by remember { mutableStateOf<Endereco?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    val modifierTextField = Modifier
        .padding(top = 24.dp)
        .fillMaxWidth()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifierTextField
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
    ) {
        Text(
            text = "Escreva o CEP",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = cep,
            onValueChange = { cep = it },
            placeholder = { Text("xxxxx-yyy") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Green,
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp, 30.dp, 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (cep.isEmpty() || cep.length != 8) {
                    errorMessage = "Erro no preenchimento do CEP"
                } else {
                    scope.launch {
                        try {
                            val endereco = getEndereco(cep)
                            addressResponse = endereco
                            errorMessage = null
                        } catch (e: Exception) {
                            errorMessage = "Falha ao buscar o endereço: ${e.message}"
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.background,
                containerColor = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 10.dp, 50.dp, 10.dp)
                .height(50.dp)
        ) {
            Text("Procurar CEP")
        }
        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        addressResponse?.let {
            AddressCard(it)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun AddressCard(
    endereco: Endereco
){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AddressField("CEP", endereco.cep)
            AddressField("Logradouro", endereco.logradouro)
            AddressField("Complemento",endereco.complemento)
            AddressField("Bairro", endereco.bairro)
            AddressField("Unidade",endereco.unidade)
            AddressField("Localidade", endereco.localidade)
            AddressField("UF", endereco.uf)
            AddressField("IBGE", endereco.ibge)
            AddressField("GIA", endereco.gia)
            AddressField("DDD", endereco.ddd)
            AddressField("SIAFI", endereco.siafi)

        }
    }
}



@Composable
fun AddressField(label: String, value: String) {
    if (value.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$label: $value")

        }
    }
}

suspend fun getEndereco(cep: String): Endereco {
    return withContext(Dispatchers.IO) {
        RetrofitClient.enderecoService.getDetailsByCep(cep)
    }
}