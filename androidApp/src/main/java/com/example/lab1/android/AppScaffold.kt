package com.example.lab1.android


import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold() {
    Scaffold { paddingValues ->
        ArticleScreen(
            onAboutButtonClick = {},
        )
    }
}