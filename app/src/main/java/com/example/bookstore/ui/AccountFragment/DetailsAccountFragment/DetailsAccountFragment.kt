package com.example.bookstore.ui.AccountFragment.DetailsAccountFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookstore.databinding.FragmentDetailsAccountBinding
import com.example.bookstore.ui.LoginViewModel.LoginFragment
import com.google.android.material.snackbar.Snackbar


class DetailsAccountFragment : Fragment() {

    private lateinit var binding: FragmentDetailsAccountBinding
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        detailsViewModel.profileUserLiveData.observe(viewLifecycleOwner, {
            if (it != null && it.status) {
                binding.accountName.setText(it.data.name)
                binding.accountEmail.setText(it.data.email)
                binding.accountPhone.setText(it.data.phone)
                binding.accountPoints.setText(it.data.points.toString())
                binding.accountCredit.setText(it.data.credit.toString())
                binding.AccountProgressBar.visibility = View.GONE

            } else {
                binding.AccountProgressBar.visibility = View.GONE
                Snackbar.make(binding.AccountImage, it.message, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun getData() {
        binding.AccountProgressBar.visibility = View.VISIBLE
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences("SaveData", Context.MODE_PRIVATE)
       // val tokenLogin:String = sharedPreferences.getString("TOKENLOGIN", "no").toString()
        val tok = LoginFragment()
        // TODO: 04/10/2021  
        detailsViewModel.getDataForUser(tok.tokenLogin.toString())

    }

}