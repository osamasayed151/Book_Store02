package com.example.bookstore.ui.AccountFragment.DetailsAccountFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.databinding.FragmentDetailsAccountBinding
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
        detailsViewModel = ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)
        getData()

        detailsViewModel.profileUserLiveData.observe(viewLifecycleOwner, {
            if (it != null && it.status) {
                binding.detailsName.setText(it.data.name)
                binding.detailsEmail.setText(it.data.email)
                binding.detailsPhone.setText(it.data.phone)
                binding.detailsPoints.setText(it.data.points.toString())
                binding.detailsCredit.setText(it.data.credit.toString())
                binding.detailsProgressBar.visibility = View.GONE
            } else {
                binding.detailsProgressBar.visibility = View.GONE
                Snackbar.make(binding.detailsImage, it.message, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun getData() {
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences("SaveData", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKENLOGIN","null").toString()
        binding.detailsProgressBar.visibility = View.VISIBLE
        detailsViewModel.getDataForUser(token)


    }

}