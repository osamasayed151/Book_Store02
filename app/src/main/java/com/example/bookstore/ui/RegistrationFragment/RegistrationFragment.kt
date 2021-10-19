package com.example.bookstore.ui.RegistrationFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentRegisterationBinding
import com.example.bookstore.model.data.Data
import com.example.bookstore.ui.Activities.MainActivity
import com.google.android.material.snackbar.Snackbar


class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegisterationBinding
    private lateinit var registrationViewModel: RegistrationViewModel
    private var tokenRegister: String? = null
    private var statusRegister: Boolean? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)

        binding.registrationRegistration.setOnClickListener {
            registration()
        }

        registrationViewModel.postLiveData.observe(viewLifecycleOwner, {
            if (it != null && it.status) {
                tokenRegister =  it.data.token
                statusRegister = true
                saveData()
                binding.registrationProgressBar.visibility = View.GONE
                startActivity(Intent(context, MainActivity::class.java))
            } else {
                if (it != null) {
                    binding.registrationProgressBar.visibility = View.GONE
                    Snackbar.make(binding.registrationRegistration, it.message, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        binding.registrationCancel.setOnClickListener {
            findNavController().navigate(R.id.action_registeationFragment_to_loginFragment)
        }
    }

    private fun registration() {
        val data = Data(
                binding.registrationEmail.text.toString(),
                binding.registrationUsername.text.toString(),
                binding.registrationPhone.text.toString(),
                binding.registrationPassword.text.toString(),
        )
        registrationViewModel.postUser(data)
        binding.registrationProgressBar.visibility = View.VISIBLE
    }
    fun saveData(){
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences("SaveData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply { putBoolean("STATUS_REGISTER", statusRegister!!) }.apply()
    }

}