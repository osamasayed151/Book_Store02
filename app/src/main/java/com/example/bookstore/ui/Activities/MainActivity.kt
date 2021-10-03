package com.example.bookstore.ui.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bookstore.R

import com.example.bookstore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.mContainer)
       // val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.basketFragment,R.id.accountFragment))

        binding.bottomNavigation.setupWithNavController(navController)

    }

}