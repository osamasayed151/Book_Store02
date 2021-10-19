package com.example.bookstore.ui.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.bookstore.databinding.FragmentShowItemBinding
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.model.remote.RemoteBuilder
import com.example.bookstore.model.remote.Repository.RepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowItemFragment : Fragment() {

    private lateinit var binding: FragmentShowItemBinding
    private lateinit var mainViewModel : MainViewModel
    private var bookId = arguments?.getString("Book")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShowItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        getBookId()

        Toast.makeText(context, "${bookId?.toInt()}" , Toast.LENGTH_SHORT).show()
        mainViewModel.bookIdLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.ShowProgressBar.visibility = View.GONE
                Toast.makeText(context, "this" , Toast.LENGTH_SHORT).show()
                binding.showItemTitle.text = it.title
            } else {
                binding.ShowProgressBar.visibility = View.GONE
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getBookId() {
        binding.ShowProgressBar.visibility = View.VISIBLE
        mainViewModel.getBookId(1)
    }


}
