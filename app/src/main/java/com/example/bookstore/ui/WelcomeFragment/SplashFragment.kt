package com.example.bookstore.ui.WelcomeFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.ui.Activities.MainActivity



@Suppress("DEPRECATION")
class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toWhere()
    }

    private fun toWhere() {
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences("SaveData", Context.MODE_PRIVATE)
        val statusLogin = sharedPreferences.getBoolean("STATUS_LOGIN", false)
        val statusRegister = sharedPreferences.getBoolean("STATUS_REGISTER", false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (statusLogin || statusRegister) {
                startActivity(Intent(context, MainActivity::class.java))
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 3000)
    }
}


