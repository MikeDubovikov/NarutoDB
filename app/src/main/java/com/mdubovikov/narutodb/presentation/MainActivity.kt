package com.mdubovikov.narutodb.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.mdubovikov.narutodb.NarutoDBApp
import com.mdubovikov.narutodb.presentation.root.DefaultRootComponent
import com.mdubovikov.narutodb.presentation.root.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as NarutoDBApp).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}