package com.example.bookstore.ui.AccountFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookstore.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private lateinit var binding:FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Account.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToDetailsAccountFragment("AccountProfile")
            findNavController().navigate(action)
        }

    }
}