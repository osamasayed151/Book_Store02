package com.example.bookstore.ui.LoginViewModel

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentLoginBinding
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

            startActivity(Intent(context, MainActivity::class.java))

            loginViewModel.loginUserLiveData.observe(viewLifecycleOwner, {
                if (it != null) {
                    Snackbar.make(binding.loginLogin, "Successful",Snackbar.LENGTH_LONG).show()
                    binding.loginProgressBar.visibility = View.GONE

                } else {
                    Snackbar.make(binding.loginLogin, "Error",Snackbar.LENGTH_LONG).show()
                }
            })

        }

        binding.loginRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.sss.setOnClickListener {
            // Snack bar
            Snackbar.make(binding.loginPassword, "No connection", Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action) {
                    findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
                }.show()


        }


    }

    fun user() {
        loginViewModel.loginUser(
            binding.loginEmail.text.toString(),
            binding.loginEmail.text.toString()
        )
        binding.loginProgressBar.visibility = View.VISIBLE
    }

}