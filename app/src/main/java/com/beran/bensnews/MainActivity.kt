package com.beran.bensnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.beran.bensnews.ui.theme.BensNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BensNewsTheme {
                val navController = rememberNavController()
                AppSetup(navHostController = navController)
            }
        }
    }
}