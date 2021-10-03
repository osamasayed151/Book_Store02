package com.example.bookstore.ui.RegistrationViewModel

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentRegisterationBinding
import com.example.bookstore.model.data.RegistrationItem
import com.example.bookstore.ui.Activities.MainActivity
import com.google.android.material.snackbar.Snackbar


class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegisterationBinding
    private lateinit var registrationViewModel : RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)

        binding.registrationRegistration.setOnClickListener {
            registration()
        }

        registrationViewModel.postLiveData.observe(viewLifecycleOwner,  {
            if (it != null) {
                Snackbar.make(binding.registrationRegistration, "Successful", Snackbar.LENGTH_LONG).show()
                binding.registrationProgressBar.visibility = View.GONE
                startActivity(Intent(context,MainActivity::class.java))
            }
            else{
                Snackbar.make(binding.registrationRegistration, "Error", Snackbar.LENGTH_LONG).show()
            }
        })


    }

    fun registration(){
        val user = RegistrationItem(
            binding.registrationEmail.text.toString(),
            binding.registrationPassword.text.toString(),
            binding.registrationPhone.text.toString(),
            binding.registrationUsername.text.toString(),
        )
        registrationViewModel.postUser(user)
        binding.registrationProgressBar.visibility = View.VISIBLE
    }


}