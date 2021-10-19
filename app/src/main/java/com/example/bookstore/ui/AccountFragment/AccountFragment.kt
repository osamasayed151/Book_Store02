package com.example.bookstore.ui.AccountFragment

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentAccountBinding
import com.example.bookstore.ui.Activities.WelcomeActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import java.util.*


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel = ViewModelProvider(requireActivity()).get(AccountViewModel::class.java)

        binding.ProfileFloating.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
        binding.profileAccount.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_detailsAccountFragment)
        }
        binding.profileLanguage.setOnClickListener {
            ShowDialog() }
        binding.profileFavourite.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_myFavouriteFragment)
        }
        binding.profileAbout.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_aboutFragment)
        }
        // log out via Api
        binding.profileLogout.setOnClickListener { logout() }
        accountViewModel.logoutUserLiveData.observe(viewLifecycleOwner, {
            if (it != null && it.status) {
                binding.profileProgressBar.visibility = View.GONE
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(context,WelcomeActivity::class.java))
            } else {
                binding.profileProgressBar.visibility = View.GONE
                Snackbar.make(binding.profileLogout, it.message, Snackbar.LENGTH_LONG).show()
            }
        })





    }

    private fun logout() {
        binding.profileProgressBar.visibility = View.VISIBLE
        val sharedPreferences = requireActivity().applicationContext.getSharedPreferences(
            "SaveData",
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean("STATUS_LOGIN", false)
        }.apply()
        val token = sharedPreferences.getString("TOKENLOGIN", "null").toString()
        accountViewModel.logoutUser(token)
    }


    fun setLanguage(activity: Activity, language: String) {
        val locale = Locale(language)
        val resources = activity.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
    private fun ShowDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
        val arabicLayout = dialog.findViewById<LinearLayout>(R.id.layoutEdit)
        val englishLayout = dialog.findViewById<LinearLayout>(R.id.layoutShare)
        arabicLayout.setOnClickListener {
            dialog.dismiss()
            setLanguage(requireActivity(), "ar")
            startActivity((activity as AppCompatActivity?)!!.intent)
        }
        englishLayout.setOnClickListener {
            dialog.dismiss()
            setLanguage(requireActivity(), "en")
        }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

}