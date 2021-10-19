package com.example.bookstore.ui.LoginFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentLoginBinding
import com.example.bookstore.model.data.Data
import com.example.bookstore.ui.Activities.MainActivity
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        binding.loginLogin.setOnClickListener {
            user()
            loginViewModel.loginUserLiveData.observe(viewLifecycleOwner, {
                if (it != null && it.status) {
                    val tokenLogin: String = it.data.token.toString()
                    val statusLogin = true
                    saveData(tokenLogin , statusLogin)
                    binding.loginProgressBar.visibility = View.GONE

                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    if (it != null) {
                        binding.loginProgressBar.visibility = View.GONE
                        Snackbar.make(binding.loginLogin, it.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }

        binding.loginRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.loginCancel.setOnClickListener {
            // not register
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

    fun user() {
        loginViewModel.loginUser(
            Data(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            )
        )
        binding.loginProgressBar.visibility = View.VISIBLE
    }

    fun saveData(token: String, status:Boolean) {
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences("SaveData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("TOKENLOGIN", token)
            putBoolean("STATUS_LOGIN",status)
        }.apply()
    }

}