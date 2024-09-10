package com.example.retrofit_json

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retrofit_json.ui.theme.RetrofitjsonTheme
import com.example.retrofit_json.ui.theme.telas.TelaPrincipal

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                RetrofitjsonTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()) { innerPadding ->
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "principal") {
                            composable("principal") {
                                TelaPrincipal(modifier = Modifier.padding(innerPadding), onLogoffClick = {
                                    navController.navigate("login")
                                })
                            }
                        }

                    }
            }
        }
    }
}