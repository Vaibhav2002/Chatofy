package com.vaibhav.chatofy.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vaibhav.chatofy.R
import com.vaibhav.chatofy.databinding.ActivityAuthBinding
import com.vaibhav.chatofy.util.makeStatusBarTransparent
import com.vaibhav.chatofy.util.viewBinding

class AuthActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityAuthBinding::inflate)
    private lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(binding.root)
        navController = findNavController(R.id.fragmentContainerView)
    }
}