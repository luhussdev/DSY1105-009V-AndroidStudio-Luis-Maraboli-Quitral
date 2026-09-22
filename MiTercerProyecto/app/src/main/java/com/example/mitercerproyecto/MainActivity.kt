package com.example.mitercerproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mitercerproyecto.ui.HomeScreen
import com.example.mitercerproyecto.ui.theme.MiTercerProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiTercerProyectoTheme {
                HomeScreen()
            }
        }
    }
}

